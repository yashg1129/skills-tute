package com.skills.tute.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "default_notes")
public class DefaultNotes {

    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
