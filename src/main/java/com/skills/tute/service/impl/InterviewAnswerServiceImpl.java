package com.skills.tute.service.impl;

import com.skills.tute.entity.InterviewAnswer;
import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.enums.ApproveStatus;
import com.skills.tute.exception.InvalidStateException;
import com.skills.tute.repository.InterviewAnswerRepository;
import com.skills.tute.service.InterviewAnswerService;
import com.skills.tute.utils.StConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterviewAnswerServiceImpl implements InterviewAnswerService {

    public static final String YOU_CANNOT_DELETE_AN_APPROVED_ANSWER = "You cannot delete an approved answer.";
    public static final String YOU_CANNOT_UPDATE_AN_APPROVED_ANSWER = "You cannot update an approved answer.";

    @Autowired
    private InterviewAnswerRepository repository;

    @Override
    public InterviewAnswer save(InterviewAnswer answer) {
        answer.setDate(LocalDateTime.now());
        return repository.save(answer);
    }

    @Override
    public InterviewAnswer update(InterviewAnswer answer, boolean isAdmin) {
        InterviewAnswer ans = repository.findById(answer.getId()).orElse(null);
        if(!isAdmin && ans != null && ApproveStatus.APPROVED.equals(ans.getApproveStatus())) {
            throw new InvalidStateException(YOU_CANNOT_UPDATE_AN_APPROVED_ANSWER);
        }
        return repository.save(answer);
    }

    @Override
    public InterviewAnswer findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<InterviewAnswer> findByQuestionId(Integer questionId) {
        return repository.findByInterviewQuestion(new InterviewQuestion(questionId));
    }

    @Override
    public List<InterviewAnswer> findByApproveStatus(String approveStatus) {
        List<InterviewAnswer> list;
        if(StConstant.ALL.equals(approveStatus)) {
            list = repository.findAll();
        } else {
            list = repository.findByApproveStatus(ApproveStatus.valueOf(approveStatus));
        }
        return list;
    }

    @Override
    public List<InterviewAnswer> findByUserId(Integer userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public void deleteByUserIdAndId(Integer userId, Integer id) {
        InterviewAnswer answer = repository.findById(id).orElse(null);
        if(answer != null && ApproveStatus.APPROVED.equals(answer.getApproveStatus())) {
            throw new InvalidStateException(YOU_CANNOT_DELETE_AN_APPROVED_ANSWER);
        }
        repository.deleteByIdAndUserId(id, userId);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
