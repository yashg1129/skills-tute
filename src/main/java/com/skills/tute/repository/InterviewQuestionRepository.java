package com.skills.tute.repository;

import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.entity.ProgrammingInterviewQuestion;
import com.skills.tute.entity.Topic;
import com.skills.tute.enums.ApproveStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Integer> {

    @Query(value = "select q from InterviewQuestion q where q.topic.name=:topicName and q.question=:question and q.programmingQuestion.program=:program")
    InterviewQuestion findByTopicAndQuestionAndProgram(@Param("topicName") String topicName, @Param("question") String question, @Param("program") String program);

    List<InterviewQuestion> findByTopic(Topic topic);

    List<InterviewQuestion> findByTopicAndApproveStatusOrderByPointsDesc(Topic topic, ApproveStatus approveStatus);

    List<InterviewQuestion> findByApproveStatus(ApproveStatus approveStatus);

    @Modifying
    @Transactional
    @Query(value = "update interview_questions set points = points - 1 where approve_status='APPROVED';", nativeQuery = true)
    int reducePoint();
}
