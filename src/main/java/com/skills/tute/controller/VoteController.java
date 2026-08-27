package com.skills.tute.controller;

import com.skills.tute.entity.Party;
import com.skills.tute.entity.Vote;
import com.skills.tute.service.VoteService;
import com.skills.tute.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService service;

    public VoteController(VoteService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Vote save(@RequestBody Vote vote) {
        vote.setId(null);
        vote.setUserId(SecurityUtils.getUserId());
        return service.save(vote);
    }

    @DeleteMapping("/{userid}")
    @PreAuthorize("hasRole('USER')")
    public void deleteMyVotes(@PathVariable("userid") Integer userId) {
        service.deleteAllByUserId(userId);
    }

    @GetMapping("/{userid}")
    @PreAuthorize("hasRole('USER')")
    public Map<Party, Integer> calculateMyVotes(@PathVariable("userid") Integer userId) {
        return service.calculateVotes(userId);
    }

}
