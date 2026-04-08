package com.skills.tute.repository;

import com.skills.tute.entity.Company;
import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.entity.InterviewQuestionUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface InterviewQuestionUserRepository extends JpaRepository<InterviewQuestionUser, Integer> {

    InterviewQuestionUser findByInterviewQuestionAndUserIdAndCompanyAndDate(InterviewQuestion question, Integer userId, Company company, LocalDate date);
    List<InterviewQuestionUser> findByUserId(Integer userId);
    List<InterviewQuestionUser> findByUserIdAndInterviewQuestionDate(Integer userId, InterviewQuestion question);
    void deleteByInterviewQuestion(Integer questionId);
}
