package com.skills.tute.service;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.entity.InterviewQuestionUser;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface InterviewQuestionService {

    InterviewQuestion save(InterviewQuestionRequest question);
    InterviewQuestion update(InterviewQuestionRequest question) throws AccessDeniedException;
    InterviewQuestion findById(Integer id);
    List<InterviewQuestionUser> findAll(String approval, Integer userId);
    List<InterviewQuestion> findByTopicId(Integer id);
    List<InterviewQuestion> findByTopicNameAndApproval(String name, String approval);
    void deleteById(Integer questionId, Integer questionUserId);

}
