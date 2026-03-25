package com.skills.tute.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "interview_answers")
public class InterviewAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String status;

    private String message;
    private Integer star;
    private Integer like;
    private Integer unlike;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private InterviewQuestion interviewQuestion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
