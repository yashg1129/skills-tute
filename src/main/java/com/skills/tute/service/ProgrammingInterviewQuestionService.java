package com.skills.tute.service;

import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.entity.InterviewQuestionUser;
import com.skills.tute.entity.ProgrammingInterviewQuestion;

public interface ProgrammingInterviewQuestionService {
    ProgrammingInterviewQuestion saveOrUpdate(ProgrammingInterviewQuestion requestProgrammingRequest, InterviewQuestion question);
}
