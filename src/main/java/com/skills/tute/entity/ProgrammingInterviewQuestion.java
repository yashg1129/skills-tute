package com.skills.tute.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "programming_interview_questions")
public class ProgrammingInterviewQuestion {

    @Id
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String program;

    @OneToOne
    @JsonIgnore
    @MapsId
    @JoinColumn(name = "id")
    private InterviewQuestion interviewQuestion;

    public ProgrammingInterviewQuestion() {
    }

    public ProgrammingInterviewQuestion(String program) {
        this.setProgram(program);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public InterviewQuestion getInterviewQuestion() {
        return interviewQuestion;
    }

    public void setInterviewQuestion(InterviewQuestion interviewQuestion) {
        this.interviewQuestion = interviewQuestion;
    }
}
