package com.skills.tute.controller;

import ch.qos.logback.core.status.StatusUtil;
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
    void save(@RequestBody Like like) {
        like.setUserId(SecurityUtils.getUserId());
        service.save(like);
    }

//    @GetMapping
//    Long likesCount(@PathVariable("likes") Boolean like) {
//        return service.countByLike(like);
//    }

//    @GetMapping
//    Long checkLikes() {
//        return service.countByLike(like);
//    }

}
