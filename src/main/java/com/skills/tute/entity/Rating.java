package com.skills.tute.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private InterviewQuestion interviewQuestion;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    private String message;
    private Integer star;
    private Integer like;
    private Integer unlike;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate date;
}
