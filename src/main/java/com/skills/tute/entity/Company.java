package com.skills.tute.entity;

import com.skills.tute.enums.ApproveStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApproveStatus approveStatus = ApproveStatus.PENDING;
}
