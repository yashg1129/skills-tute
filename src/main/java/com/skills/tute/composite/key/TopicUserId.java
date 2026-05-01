package com.skills.tute.composite.key;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class TopicUserId {

    private Integer topicId;
    private Integer userId;

    public TopicUserId() {}

    public TopicUserId(Integer topicId, Integer userId) {
        this.topicId = topicId;
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicUserId that = (TopicUserId) o;
        return Objects.equals(topicId, that.topicId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicId, userId);
    }
}
