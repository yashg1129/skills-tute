package com.skills.tute.repository;

import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Integer> {

    Like findByInterviewQuestionAndUserId(InterviewQuestion interviewQuestion, Integer userId);
    List<Like> findByInterviewQuestion(InterviewQuestion interviewQuestion);
}
