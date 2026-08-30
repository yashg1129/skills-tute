package com.skills.tute.controller;

import com.skills.tute.dto.VoteRequest;
import com.skills.tute.dto.VoteResponse;
import com.skills.tute.entity.Vote;
import com.skills.tute.service.VoteService;
import com.skills.tute.service.i18.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService service;
    private final MessageService messageService;

    public VoteController(VoteService service, MessageService messageService) {
        this.service = service;
        this.messageService = messageService;
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

    @GetMapping("/verify/{id}")
    public Vote verifyVote(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @GetMapping("/{userid}")
    public List<VoteResponse> calculateUserVotes(@PathVariable("userid") Integer userId) {
        return service.calculateVotes(userId);
    }

    @GetMapping("/message")
    public Map<String, String> getVotingMessage() {
        return Map.ofEntries(
                Map.entry("votingReady", messageService.getMessage("evm.voting.ready")),
                Map.entry("current", messageService.getMessage("evm.current")),
                Map.entry("new", messageService.getMessage("evm.new")),
                Map.entry("viewVotes", messageService.getMessage("evm.view.votes")),
                Map.entry("deleteVotes", messageService.getMessage("evm.delete.votes")),
                Map.entry("totalVotes", messageService.getMessage("evm.total.votes")),
                Map.entry("voteCounts", messageService.getMessage("evm.vote.counts")),
                Map.entry("wait", messageService.getMessage("evm.wait")),
                Map.entry("voteCasted", messageService.getMessage("evm.vote.casted")),
                Map.entry("backToVoting", messageService.getMessage("evm.back.to.voting")),
                Map.entry("loadingVotesCounts", messageService.getMessage("evm.loading.votes.counts")),
                Map.entry("voteClearing", messageService.getMessage("evm.vote.clearing")),
                Map.entry("voteCleared", messageService.getMessage("evm.vote.cleared")),
                Map.entry("errorNotFound", messageService.getMessage("error.not.found")),
                Map.entry("errorValidId", messageService.getMessage("error.valid.id")),
                Map.entry("txtEnterId", messageService.getMessage("txt.enter.id")),
                Map.entry("btnLoading", messageService.getMessage("btn.loading")),
                Map.entry("btnVerify", messageService.getMessage("btn.verify"))
        );
    }

}
