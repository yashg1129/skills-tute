package com.skills.tute.entity;

import com.skills.tute.enums.ApproveStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "interview_questions")
public class InterviewQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApproveStatus approveStatus = ApproveStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Column(name = "ask_count", nullable = false)
    private Integer askCount;

    private Integer points;

    private LocalDateTime date;

    public InterviewQuestion() {
    }

    public InterviewQuestion(Integer id) {
        this.id = id;
    }
}
