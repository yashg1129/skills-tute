package com.skills.tute.service;

import com.skills.tute.entity.InterviewAnswer;

import java.util.List;

public interface InterviewAnswerService {

    InterviewAnswer save(InterviewAnswer answer);
    InterviewAnswer update(InterviewAnswer answer, boolean isAdmin);
    InterviewAnswer findById(Integer id);
    List<InterviewAnswer> findByApprovedAnswerQuestionId(Integer questionId);
    List<InterviewAnswer> findByApproveStatus(String approveStatus);
    List<InterviewAnswer> findByUserId(Integer userId);
    void deleteByUserIdAndId(Integer userId, Integer id);
    void deleteById(Integer id);

}
