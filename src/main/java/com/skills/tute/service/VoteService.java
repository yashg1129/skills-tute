package com.skills.tute.service;

import com.skills.tute.entity.Party;
import com.skills.tute.entity.Vote;

import java.util.Map;

public interface VoteService {

    Vote save(Vote vote);

    void deleteAllByUserId(Integer userId);

    Map<Party, Integer> calculateVotes(Integer userId);
}
