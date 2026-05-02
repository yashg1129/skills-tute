package com.skills.tute.controller;

import com.skills.tute.utils.SecurityUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserSessionController {

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public void setUserSession(HttpSession session) {
        session.setAttribute("userId", SecurityUtils.getUserId());
        System.out.println(session.getAttribute("userId"));
    }
}
