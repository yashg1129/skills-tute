package com.skills.tute.repository;

import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.entity.Topic;
import com.skills.tute.enums.ApproveStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Integer> {

    InterviewQuestion findByQuestionAndTopic(String question, Topic topic);

    List<InterviewQuestion> findByTopic(Topic topic);

    List<InterviewQuestion> findByTopicAndApproveStatusOrderByPointsDesc(Topic topic, ApproveStatus approveStatus);

    List<InterviewQuestion> findByApproveStatus(ApproveStatus approveStatus);

    @Modifying
    @Transactional
    @Query(value = "update interview_questions set points = points - 1 where approve_status='APPROVED';", nativeQuery = true)
    int reducePoint();
}
