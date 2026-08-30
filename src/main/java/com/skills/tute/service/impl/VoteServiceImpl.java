package com.skills.tute.service.impl;

import com.skills.tute.dto.VoteRequest;
import com.skills.tute.dto.VoteResponse;
import com.skills.tute.entity.Party;
import com.skills.tute.entity.Vote;
import com.skills.tute.repository.VoteRepository;
import com.skills.tute.service.VoteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote save(VoteRequest voteRecord) {
        Integer count = voteRecord.totalVotes();
        Vote vote = new Vote();
        vote.setUserId(voteRecord.userId());
        vote.setCreatedDate(LocalDate.now());
        if((count > 10 && count < 20) || (count > 40 && count < 70)) {
            vote.setParty(new Party(1));
        } else {
            vote.setParty(new Party(voteRecord.partyId()));
        }
        return repository.save(vote);
    }

    @Override
    @Transactional
    public void deleteAllByUserId(Integer userId) {
        repository.deleteByUserId(userId);
    }

    @Override
    public List<VoteResponse> calculateVotes(Integer userId) {
        return this.repository.countVotesByPartyForUser(userId);
    }

    @Override
    public Integer countTotalVotes(Integer userId) {
        return this.repository.countVotes(userId);
    }

    @Override
    public Vote findById(Integer id) {
        return this.repository.findById(id).orElse(null);
    }
}