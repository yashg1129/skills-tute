package com.skills.tute.service;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.InterviewQuestion;

import java.util.List;

public interface InterviewQuestionService {

    InterviewQuestion save(InterviewQuestionRequest question);
    InterviewQuestion update(InterviewQuestion question);
    InterviewQuestion findById(Integer id);
    List<InterviewQuestion> findAll();
    void deleteById(Integer id);
}
