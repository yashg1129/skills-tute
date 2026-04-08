package com.skills.tute.utils;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.security.AuthenticatedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public final class SecurityUtils {

    public static Integer getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        return  ((AuthenticatedUser) Objects.requireNonNull(authentication.getPrincipal())).getUserId();
    }

    public static boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        String role = ((AuthenticatedUser) Objects.requireNonNull(authentication.getPrincipal())).getRole();
        return  "ADMIN".equals(role);
    }

}
