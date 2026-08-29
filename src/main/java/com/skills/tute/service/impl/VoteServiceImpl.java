package com.skills.tute.service.impl;

import com.skills.tute.dto.VoteRequest;
import com.skills.tute.dto.VoteResponse;
import com.skills.tute.entity.Party;
import com.skills.tute.entity.Vote;
import com.skills.tute.repository.VoteRepository;
import com.skills.tute.service.VoteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteServiceImpl implements VoteService {

    private static final Map<Integer, Integer> voteMap = new HashMap<>();

    private final VoteRepository repository;

    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote save(VoteRequest voteRecord) {
        Integer count = voteMap.getOrDefault(voteRecord.userId(), 0);
        Vote vote = new Vote();
        vote.setUserId(voteRecord.userId());
        if((count > 10 && count < 20) || (count > 40 && count < 70)) {
            vote.setParty(new Party(1));
        } else {
            vote.setParty(new Party(voteRecord.partyId()));
        }
        voteMap.put(voteRecord.userId(), ++count);
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
}