package com.skills.tute.repository;

import com.skills.tute.entity.InterviewAnswer;
import com.skills.tute.entity.InterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewAnswerRepository extends JpaRepository<InterviewAnswer, Integer> {

    List<InterviewAnswer> findByInterviewQuestion(InterviewQuestion question);
}
