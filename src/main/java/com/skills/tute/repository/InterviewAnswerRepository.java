package com.skills.tute.repository;

import com.skills.tute.entity.InterviewAnswer;
import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.enums.ApproveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewAnswerRepository extends JpaRepository<InterviewAnswer, Integer> {

    List<InterviewAnswer> findByApproveStatusAndInterviewQuestion(ApproveStatus approveStatus, InterviewQuestion question);

    List<InterviewAnswer> findByApproveStatus(ApproveStatus approveStatus);

    List<InterviewAnswer> findByUserId(Integer userId);

    List<InterviewAnswer> deleteByIdAndUserId(Integer id, Integer userId);
}
