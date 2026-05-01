package com.skills.tute.entity;

import com.skills.tute.composite.key.TopicUserId;
import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class Notes {

    @EmbeddedId
    private TopicUserId id;

    @Column(nullable = false)
    private String contents;

    public TopicUserId getId() {
        return id;
    }

    public void setId(TopicUserId id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
