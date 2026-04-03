package com.skills.tute.service;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.InterviewQuestion;

import java.util.List;

public interface InterviewQuestionService {

    InterviewQuestion save(InterviewQuestionRequest question);
    InterviewQuestion update(InterviewQuestion question);
    InterviewQuestion findById(Integer id);
    List<InterviewQuestion> findAll(String approval);
    List<InterviewQuestion> findByTopicId(Integer id);
    List<InterviewQuestion> findByTopicNameAndApproval(String name, String approval);
    void deleteById(Integer id);
}
