package com.skills.tute.service.impl;

import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.repository.InterviewQuestionRepository;
import com.skills.tute.service.InterviewQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterviewQuestionServiceImpl implements InterviewQuestionService {

    @Autowired
    private InterviewQuestionRepository repository;

    @Override
    public InterviewQuestion save(InterviewQuestion question) {
        question.setDate(LocalDateTime.now());
        return repository.save(question);
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
