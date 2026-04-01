package com.skills.tute.entity;

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
}
