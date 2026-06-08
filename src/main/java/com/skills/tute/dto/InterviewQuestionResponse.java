package com.skills.tute.dto;

import com.skills.tute.entity.Like;

import java.util.List;

public class InterviewQuestionResponse {

    private Integer id;
    private String question;
    private String program;
    private String postedBy;
    private Integer askCount;
    private List<Like> likes;
    private String topicName;

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public Integer getAskCount() {
        return askCount;
    }

    public void setAskCount(Integer askCount) {
        this.askCount = askCount;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
