package com.skills.tute.service.impl;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.*;
import com.skills.tute.repository.*;
import com.skills.tute.service.AdminInterviewQuestionService;
import com.skills.tute.utils.StConstant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class AdminInterviewQuestionServiceImpl implements AdminInterviewQuestionService {

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

    @Override
    public InterviewQuestion update(InterviewQuestionRequest request) throws AccessDeniedException {
        InterviewQuestion question = repository.findById(request.getId()).orElse(null);
        assert question != null;
        question.setQuestion(request.getQuestion());
        question.setTopic(request.getTopic());
        question.setApproveStatus(request.getApproveStatus());

        return repository.save(question);
    }

    @Override
    public List<InterviewQuestion> findAll(String approval) {
        List<InterviewQuestion> list;
        if("ALL".equals(approval) ) {
            list = repository.findAll();
        } else {
            list = repository.findByApproveStatus(approval);
        }
        return list;
    }

    @Transactional
    @Override
    public void deleteById(Integer questionId) {
        interviewQuestionUserRepository.deleteByInterviewQuestion(questionId);
        repository.deleteById(questionId);
    }
}
