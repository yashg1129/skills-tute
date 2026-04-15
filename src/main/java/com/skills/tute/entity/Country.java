package com.skills.tute.entity;

import com.skills.tute.enums.ApproveStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApproveStatus approveStatus = ApproveStatus.PENDING;

}
