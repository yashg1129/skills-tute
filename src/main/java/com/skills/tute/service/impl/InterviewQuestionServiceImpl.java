package com.skills.tute.service.impl;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.*;
import com.skills.tute.enums.ApprovalStatus;
import com.skills.tute.repository.*;
import com.skills.tute.service.InterviewQuestionService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

        Topic topic = request.getTopic();
        if (topic.getId() == null && isNotBlank(topic.getName())) {
            topic.setName(firstCharCaps(topic.getName()));
            Integer displayOrder = topicRepository.findMaxId();
            if (displayOrder == null) {
                displayOrder = 0;
            }
            topic.setDisplayOrder(++displayOrder);
            topic = topicRepository.save(topic);
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
        question.setDate(LocalDateTime.now());
        saveInterviewQuestionUser(question, request);
    }

    private void saveInterviewQuestionUser(InterviewQuestion interviewQuestion, InterviewQuestionRequest request) {
        InterviewQuestionUser interviewQuestionUser = new InterviewQuestionUser();
        interviewQuestionUser.setInterviewQuestion(interviewQuestion);
        interviewQuestionUser.setUserId(request.getUserId());

        Company company = request.getCompany();
        if (company.getId() == null && isNotBlank(company.getName())) {
            company = new Company();
            company.setName(firstCharCaps(company.getName()));
            company = companyRepository.save(company);
        } else {
            company = null;
        }
        interviewQuestionUser.setCompany(company);

        City city = request.getCity();
        if (city.getId() == null && isNotBlank(city.getName())) {
            city = new City();
            city.setName(firstCharCaps(city.getName()));
            city = cityRepository.save(city);
        } else {
            city = null;
        }
        interviewQuestionUser.setCity(city);

        Country country = request.getCountry();
        if (country.getId() == null && isNotBlank(country.getName())) {
            country = new Country();
            country.setName(firstCharCaps(country.getName()));
            countryRepository.save(country);
        } else {
            country = null;
        }
        interviewQuestionUser.setCountry(country);
        interviewQuestionUser.setDate(LocalDate.now());
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
