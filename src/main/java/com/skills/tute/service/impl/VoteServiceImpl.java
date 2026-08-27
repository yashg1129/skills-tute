package com.skills.tute.service.impl;

import com.skills.tute.entity.Party;
import com.skills.tute.entity.Vote;
import com.skills.tute.repository.VoteRepository;
import com.skills.tute.service.VoteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VoteServiceImpl implements VoteService {

    private Integer count = 0;

    private VoteRepository repository;

    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote save(Vote vote) {
        count++;
        if(count > 10 && count < 20) {
            vote.setId(1);
        }
        return repository.save(vote);
    }

    @Override
    @Transactional
    public void deleteAllByUserId(Integer userId) {
        repository.deleteByUserId(userId);
    }

    @Override
    public Map<Party, Integer> calculateVotes(Integer userId) {
        return Map.of();
    }
}