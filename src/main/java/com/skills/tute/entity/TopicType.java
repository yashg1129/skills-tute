package com.skills.tute.entity;

import com.skills.tute.enums.TopicTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "topic_types")
public class TopicType {

    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TopicTypeEnum type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TopicTypeEnum getType() {
        return type;
    }

    public void setType(TopicTypeEnum type) {
        this.type = type;
    }
}
