package com.skills.tute.service.impl;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.*;
import com.skills.tute.enums.ApproveStatus;
import com.skills.tute.repository.*;
import com.skills.tute.service.AdminInterviewQuestionService;
import com.skills.tute.service.CommonService;
import com.skills.tute.service.ProgrammingInterviewQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class AdminInterviewQuestionServiceImpl implements AdminInterviewQuestionService {

    @Autowired
    private InterviewQuestionRepository repository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private InterviewQuestionUserRepository interviewQuestionUserRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ProgrammingInterviewQuestionService programmingInterviewQuestionService;

    @Override
    //@CacheEvict(value = "interview-questions", allEntries = true)
    public InterviewQuestion update(InterviewQuestionRequest request, String approveStatus) throws AccessDeniedException {
        InterviewQuestion question = repository.findById(request.getId()).orElse(null);
        assert question != null;
        InterviewQuestion interviewQuestion;
        if(approveStatus != null) {
            question.setApproveStatus(ApproveStatus.valueOf(approveStatus));
            interviewQuestion = repository.save(question);
        } else {
            Topic topic = commonService.getTopicForUpdate(request.getTopic());
            question.setTopic(topic);
            question.setQuestion(request.getQuestion());
            question.setApproveStatus(ApproveStatus.valueOf(request.getApproveStatus()));

            interviewQuestion = repository.save(question);
            interviewQuestion.setProgrammingQuestion(programmingInterviewQuestionService.saveOrUpdate(new ProgrammingInterviewQuestion(request.getProgram()), question));
        }

        return interviewQuestion;
    }

    @Override
    public List<InterviewQuestion> findAll(String approveStatus) {
        List<InterviewQuestion> list;
        if("ALL".equals(approveStatus) ) {
            list = repository.findAll();
        } else {
            list = repository.findByApproveStatus(ApproveStatus.valueOf(approveStatus));
        }
        return list;
    }

    @Override
    //@CacheEvict(value = "interview-questions")
    public void deleteById(Integer questionId) {
        interviewQuestionUserRepository.deleteByInterviewQuestion(questionId);
        repository.deleteById(questionId);
    }
}
