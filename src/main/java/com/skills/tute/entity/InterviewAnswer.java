package com.skills.tute.entity;

import com.skills.tute.enums.ApproveStatus;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApproveStatus approveStatus = ApproveStatus.PENDING;

    private String message;
    private Integer star;
    private Integer likes;
    private Integer dislikes;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private InterviewQuestion interviewQuestion;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

}
