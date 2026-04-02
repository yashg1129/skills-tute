package com.skills.tute.service;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.InterviewQuestion;

import java.util.List;

public interface InterviewQuestionService {

    InterviewQuestion save(InterviewQuestionRequest question);
    InterviewQuestion update(InterviewQuestion question);
    InterviewQuestion findById(Integer id);
    List<InterviewQuestion> findAll();
    List<InterviewQuestion> findByTopicId(Integer id);
    List<InterviewQuestion> findByTopicName(String name);
    List<InterviewQuestion> findAll(String topicName);
    void deleteById(Integer id);
}
