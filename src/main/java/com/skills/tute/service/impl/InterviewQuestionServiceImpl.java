package com.skills.tute.service.impl;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.*;
import com.skills.tute.enums.ApprovalStatus;
import com.skills.tute.repository.*;
import com.skills.tute.service.InterviewQuestionService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.skills.tute.utils.StStringUtils.*;

@Service
public class InterviewQuestionServiceImpl implements InterviewQuestionService {

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

        Topic topic = new Topic();
        if (request.getTopicId() == null && isNotBlank(request.getTopicName())) {
            topic.setName(firstCharCaps(request.getTopicName()));
            Integer displayOrder = topicRepository.findMaxId();
            if (displayOrder == null) {
                displayOrder = 0;
            }
            topic.setDisplayOrder(++displayOrder);
            topicRepository.save(topic);
        } else {
            topic.setId(request.getTopicId());
        }
        question.setTopic(topic);

        saveQuestion(request, question, topic);

        return question;
    }

    private void saveQuestion(InterviewQuestionRequest request, InterviewQuestion question, Topic topic) {
        question.setApprovedStatus(ApprovalStatus.PENDING.name());
        question.setQuestion(request.getQuestion());
        question.setTopic(topic);
        InterviewQuestion interviewQuestion = repository.findByQuestionAndTopic(request.getQuestion(), topic);
        if (interviewQuestion == null) {
            question.setAskCount(1);
            question = repository.save(question);
        } else {
            question.setAskCount(interviewQuestion.getAskCount() + 1);
        }
        saveInterviewQuestionUser(question, request);
    }

    private void saveInterviewQuestionUser(InterviewQuestion interviewQuestion, InterviewQuestionRequest request) {
        InterviewQuestionUser interviewQuestionUser = new InterviewQuestionUser();
        interviewQuestionUser.setInterviewQuestion(interviewQuestion);
        interviewQuestionUser.setUserId(request.getUserId());

        Company company = null;
        if (request.getCompanyId() == null && isNotBlank(request.getCompanyName())) {
            company = new Company();
            company.setName(firstCharCaps(request.getCompanyName()));
            companyRepository.save(company);
        } else if(request.getCompanyId() != null) {
            company = new Company();
            company.setId(request.getCompanyId());
        }
        interviewQuestionUser.setCompany(company);

        City city = null;
        if (request.getCityId() == null && isNotBlank(request.getCityName())) {
            city = new City();
            city.setName(firstCharCaps(request.getCityName()));
            cityRepository.save(city);
        } else if (request.getCityId() != null) {
            city = new City();
            city.setId(request.getCityId());
        }
        interviewQuestionUser.setCity(city);

        Country country = null;
        if (request.getCountryId() == null && isNotBlank(request.getCountryName())) {
            country = new Country();
            country.setName(firstCharCaps(request.getCompanyName()));
            countryRepository.save(country);
        } else if (request.getCountryId() != null) {
            country = new Country();
            country.setId(request.getCountryId());
        }
        interviewQuestionUser.setCountry(country);

        interviewQuestionUserRepository.save(interviewQuestionUser);
    }

    @Override
    public InterviewQuestion update(InterviewQuestion question) {
        return repository.save(question);
    }

    @Override
    public InterviewQuestion findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<InterviewQuestion> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
