package com.skills.tute.service;

import com.skills.tute.entity.InterviewAnswer;
import com.skills.tute.entity.InterviewQuestion;

import java.util.List;

public interface InterviewAnswerService {

    InterviewAnswer save(InterviewAnswer answer);
    InterviewAnswer update(InterviewAnswer answer);
    InterviewAnswer findById(Integer id);
    List<InterviewAnswer> findByQuestionId(Integer questionId);
    List<InterviewAnswer> findAll();
    void deleteById(Integer id);

}
