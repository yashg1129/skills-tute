package com.skills.tute.service.impl;

import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.entity.InterviewQuestionUser;
import com.skills.tute.entity.ProgrammingInterviewQuestion;
import com.skills.tute.repository.ProgrammingInterviewQuestionRepository;
import com.skills.tute.service.ProgrammingInterviewQuestionService;
import com.skills.tute.utils.StStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgrammingInterviewQuestionImpl implements ProgrammingInterviewQuestionService {

    @Autowired
    private ProgrammingInterviewQuestionRepository repository;

    @Override
    public ProgrammingInterviewQuestion saveOrUpdate(ProgrammingInterviewQuestion requestProgrammingRequest, InterviewQuestion question) {
        ProgrammingInterviewQuestion programmingQuestion = repository.findById(question.getId()).orElse(null);
       // ProgrammingInterviewQuestion requestProgramming = request.getInterviewQuestion().getProgrammingQuestion();
        if(programmingQuestion != null || (requestProgrammingRequest != null && StStringUtils.isNotBlank(requestProgrammingRequest.getProgram()))) {
            if(programmingQuestion == null) {
                programmingQuestion = new ProgrammingInterviewQuestion();
            }
            if(requestProgrammingRequest == null || StStringUtils.isBlank(requestProgrammingRequest.getProgram())) {
                question.setProgrammingQuestion(null);
                repository.deleteById(programmingQuestion.getId());
                programmingQuestion = null;
            } else {
                programmingQuestion.setProgram(requestProgrammingRequest.getProgram());
                programmingQuestion.setInterviewQuestion(question);
                programmingQuestion = repository.save(programmingQuestion);
            }
        }
        return programmingQuestion;
    }
}
