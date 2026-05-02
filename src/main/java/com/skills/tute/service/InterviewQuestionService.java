package com.skills.tute.service;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.dto.InterviewQuestionResponse;
import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.entity.InterviewQuestionUser;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface InterviewQuestionService {

    InterviewQuestion save(InterviewQuestionRequest question);
    InterviewQuestionUser update(InterviewQuestionUser question) throws AccessDeniedException;
    InterviewQuestion findById(Integer id);
    List<InterviewQuestionUser> findAll(String approval, Integer userId);
    List<InterviewQuestion> findByTopicId(Integer id);
    List<InterviewQuestionResponse> findByTopicNameAndApproval(String name, Integer userId);
    void deleteById(Integer questionId, Integer questionUserId);

}
