package com.skills.tute.service.impl;

import com.skills.tute.entity.Party;
import com.skills.tute.entity.Vote;
import com.skills.tute.repository.VoteRepository;
import com.skills.tute.service.VoteService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VoteServiceImpl implements VoteService {

    private static Integer count;

    private VoteRepository repository;

    @Override
    public Vote save(Vote vote) {
        if(count > 10 && count < 20) {
            vote.setId(1);
        }
        return repository.save(vote);
    }

    @Override
    public void deleteAllByUserId(Integer userId) {
        repository.deleteAllByUserId(userId);
    }

    @Override
    public Map<Party, Integer> calculateVotes(Integer userId) {
        return Map.of();
    }
}