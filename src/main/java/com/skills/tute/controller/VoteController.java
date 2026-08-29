package com.skills.tute.controller;

import com.skills.tute.dto.VoteRequest;
import com.skills.tute.dto.VoteResponse;
import com.skills.tute.entity.Vote;
import com.skills.tute.service.VoteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService service;

    public VoteController(VoteService service) {
        this.service = service;
    }

    @PostMapping
    public Vote save(@RequestBody VoteRequest vote) {
        return service.save(vote);
    }

    @DeleteMapping("/{userid}")
    public void deleteMyVotes(@PathVariable("userid") Integer userId) {
        service.deleteAllByUserId(userId);
    }

    @GetMapping("/total/{userid}")
    public Integer totalVote(@PathVariable("userid") Integer userId) {
        return service.countTotalVotes(userId);
    }

    @GetMapping("/{userid}")
    public List<VoteResponse> calculateUserVotes(@PathVariable("userid") Integer userId) {
        return service.calculateVotes(userId);
    }

}
