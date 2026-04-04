package com.skills.tute.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/api/user")
    public ResponseEntity<String> userApi() {
        return ResponseEntity.ok("User API accessed");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin")
    public ResponseEntity<String> adminApi() {
        return ResponseEntity.ok("Admin API accessed");
    }
}