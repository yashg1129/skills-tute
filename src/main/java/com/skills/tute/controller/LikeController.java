package com.skills.tute.controller;

import ch.qos.logback.core.status.StatusUtil;
import com.skills.tute.dto.LikeRequest;
import com.skills.tute.entity.Like;
import com.skills.tute.service.LikeService;
import com.skills.tute.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService service;

    public LikeController(LikeService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    void save(@RequestBody LikeRequest request) {
        Like like = new Like();
        like.setUserId(SecurityUtils.getUserId());
        like.setInterviewQuestion(request.interviewQuestion());
        like.setUserLike(request.userLike());
        service.save(like);
    }

}
