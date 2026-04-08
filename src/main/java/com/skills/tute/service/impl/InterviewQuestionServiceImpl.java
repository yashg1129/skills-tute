package com.skills.tute.service.impl;

import com.skills.tute.cache.Cache;
import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.*;
import com.skills.tute.enums.ApprovalStatus;
import com.skills.tute.exception.DuplicateResourceException;
import com.skills.tute.exception.InvalidQuestionStateException;
import com.skills.tute.repository.*;
import com.skills.tute.service.InterviewQuestionService;

import com.skills.tute.utils.StConstant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.skills.tute.utils.StStringUtils.*;

@Service
public class InterviewQuestionServiceImpl implements InterviewQuestionService {

    public static final String DUPLICATION_QUESTION = "Duplication question";
    @Autowired
    private InterviewQuestionRepository repository;

    @Autowired
    private InterviewQuestionUserRepository interviewQuestionUserRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    @Override
    public InterviewQuestion save(InterviewQuestionRequest request) {
        return copy(request);
    }

    private InterviewQuestion copy(InterviewQuestionRequest request) {
        InterviewQuestion question = new InterviewQuestion();
        question.setId(request.getId());

        Topic topic = request.getTopic();
        if (topic.getId() == null && isNotBlank(topic.getName())) {
            topic.setName(topic.getName());
            Topic res = topicRepository.findByName(topic.getName());
            if(res != null) {
                topic = res;
            } else {
                Integer displayOrder = topicRepository.findMaxId();
                if (displayOrder == null) {
                    displayOrder = 0;
                }
                topic.setDisplayOrder(++displayOrder);
                topic = topicRepository.save(topic);

                Cache.clearTopics();
            }
        }
        question.setTopic(topic);

        saveQuestion(request, topic);

        return question;
    }

    private void saveQuestion(InterviewQuestionRequest request, Topic topic) {
        InterviewQuestion interviewQuestion;
        if(request.getId() != null) {
            interviewQuestion = repository.findById(request.getId()).orElse(null);
        } else {
            interviewQuestion = repository.findByQuestionAndTopic(request.getQuestion(), topic);
        }

        if (interviewQuestion == null) {
            interviewQuestion = new InterviewQuestion();
            interviewQuestion.setApproveStatus(ApprovalStatus.PENDING.name());
            interviewQuestion.setQuestion(request.getQuestion());
            interviewQuestion.setTopic(topic);
            interviewQuestion.setAskCount(1);
            interviewQuestion.setDate(LocalDateTime.now());
            interviewQuestion = repository.save(interviewQuestion);
        } else {
            interviewQuestion.setAskCount(interviewQuestion.getAskCount() + 1);
            repository.save(interviewQuestion);
        }

        saveInterviewQuestionUser(interviewQuestion, request);
    }

    private void saveInterviewQuestionUser(InterviewQuestion interviewQuestion, InterviewQuestionRequest request) {
        InterviewQuestionUser interviewQuestionUser = new InterviewQuestionUser();
        interviewQuestionUser.setInterviewQuestion(interviewQuestion);
        interviewQuestionUser.setUserId(request.getUserId());

        Company company = request.getCompany();
        if (company.getId() == null && isNotBlank(company.getName())) {
            Company com = companyRepository.findByName(company.getName());
            if(com != null) {
                company = com;
            } else {
                company = new Company();
                company.setName(request.getCompany().getName());
                company = companyRepository.save(company);

                Cache.clearCompanies();
            }
        }
        interviewQuestionUser.setCompany(company);

        City city = request.getCity();
        if (city.getId() == null && isNotBlank(city.getName())) {
            city = new City();
            city.setName(firstCharCaps(city.getName()));
            city = cityRepository.save(city);
            Cache.clearCities();
        } else {
            city = null;
        }
        interviewQuestionUser.setCity(city);

        Country country = request.getCountry();
        if (country.getId() == null && isNotBlank(country.getName())) {
            country = new Country();
            country.setName(firstCharCaps(country.getName()));
            countryRepository.save(country);
            Cache.clearCountries();
        } else {
            country = null;
        }
        interviewQuestionUser.setCountry(country);
        interviewQuestionUser.setDate(LocalDate.now());

        InterviewQuestionUser questionUser = interviewQuestionUserRepository.findByInterviewQuestionAndUserIdAndCompanyAndDate(interviewQuestion, interviewQuestionUser.getUserId(), interviewQuestionUser.getCompany(), LocalDate.now());
        if(questionUser != null) {
            throw new DuplicateResourceException(DUPLICATION_QUESTION);
        }
        interviewQuestionUserRepository.save(interviewQuestionUser);
    }

    @Override
    public InterviewQuestion update(InterviewQuestionRequest request) throws AccessDeniedException {
        if(request.getApproveStatus() != null)  {
            throw new AccessDeniedException(StConstant.FORBIDDEN_EXCEPTION);
        }
        InterviewQuestion question = repository.findById(request.getId()).orElse(null);
        assert question != null;
        question.setQuestion(request.getQuestion());
        question.setTopic(request.getTopic());
        question.setApproveStatus(request.getApproveStatus());

        return repository.save(question);
    }

    @Override
    public InterviewQuestion findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<InterviewQuestionUser> findAll(String approval, Integer userId) {
        return interviewQuestionUserRepository.findByUserId(userId);
    }

    @Override
    public List<InterviewQuestion> findByTopicId(Integer id) {
        Topic topic = topicRepository.findById(id).orElse(null);
        return repository.findByTopic(topic);
    }

    @Override
    public List<InterviewQuestion> findByTopicNameAndApproval(String name, String approval) {
        Topic topic = topicRepository.findByName(name);
        return repository.findByTopicAndApproveStatus(topic, approval);
    }

    @Override
    public void deleteById(Integer userQuestionId, Integer userId) {
        InterviewQuestionUser interviewQuestionUser = interviewQuestionUserRepository.findById(userQuestionId).orElse(null);
        assert interviewQuestionUser != null;
        InterviewQuestion question = repository.findById(interviewQuestionUser.getInterviewQuestion().getId()).orElse(null);
        assert question != null;
        if("APPROVED".equals(question.getApproveStatus())) {
            throw new InvalidQuestionStateException("You cannot delete an approved question.");
        }
        repository.deleteById(userQuestionId);
    }
}
