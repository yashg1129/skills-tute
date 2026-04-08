package com.skills.tute.service;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.InterviewQuestion;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface AdminInterviewQuestionService {

    InterviewQuestion update(InterviewQuestionRequest question) throws AccessDeniedException;
    List<InterviewQuestion> findAll(String approval);
    void deleteById(Integer questionId);

}
