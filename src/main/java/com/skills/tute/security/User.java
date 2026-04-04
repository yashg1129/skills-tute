package com.skills.tute.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    private Integer id;

    private String email;
    private String password;
    private String role;

    public User(String mail, String password, String roleUser) {

    }

    public User() {}
}
