package com.skills.tute.repository;

import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Integer> {

    InterviewQuestion findByQuestionAndTopic(String question, Topic topic);

    List<InterviewQuestion> findByTopic(Topic topic);

    List<InterviewQuestion> findByTopicAndApprovedStatus(Topic topic, String approval);

    List<InterviewQuestion> findByApprovedStatus(String approval);
}
