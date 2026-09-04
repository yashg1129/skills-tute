package com.skills.tute.entity;

import com.skills.tute.enums.ApproveStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tutorials")
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String slug;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApproveStatus  approveStatus;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    @ManyToOne
    @JoinColumn(name = "tutorial_topic_id")
    private TutorialTopic tutorialTopic;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "you_tube_url")
    private String youtubeUrl;

    @Column(name = "git_hub_url")
    private String gitHubUrl;

    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }

}
