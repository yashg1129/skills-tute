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
    private ApproveStatus approveStatus;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @OneToOne(mappedBy = "interviewQuestion")
    private ProgrammingInterviewQuestion programmingQuestion;

    @Column(name = "ask_count", nullable = false)
    private Integer askCount;

    private Integer points;

    private LocalDateTime date;

    public InterviewQuestion() {
    }

    public InterviewQuestion(Integer id) {
        this.id = id;
    }

    public ProgrammingInterviewQuestion getProgrammingQuestion() {
        return programmingQuestion;
    }

    public void setProgrammingQuestion(ProgrammingInterviewQuestion programmingQuestion) {
        this.programmingQuestion = programmingQuestion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ApproveStatus getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(ApproveStatus approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Integer getAskCount() {
        return askCount;
    }

    public void setAskCount(Integer askCount) {
        this.askCount = askCount;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
