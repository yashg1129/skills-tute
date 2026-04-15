package com.skills.tute.service.impl;

import com.skills.tute.entity.InterviewAnswer;
import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.repository.InterviewAnswerRepository;
import com.skills.tute.service.InterviewAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterviewAnswerServiceImpl implements InterviewAnswerService {

    @Autowired
    private InterviewAnswerRepository repository;

    @Override
    public InterviewAnswer save(InterviewAnswer answer) {
        answer.setDate(LocalDateTime.now());
        return repository.save(answer);
    }

    @Override
    public InterviewAnswer update(InterviewAnswer answer) {
        return repository.save(answer);
    }

    @Override
    public InterviewAnswer findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<InterviewAnswer> findByQuestionId(Integer questionId) {
        return repository.findByInterviewQuestion(new InterviewQuestion(questionId));
    }

    @Override
    public List<InterviewAnswer> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
