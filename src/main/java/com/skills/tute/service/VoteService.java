package com.skills.tute.service;

import com.skills.tute.dto.VoteRequest;
import com.skills.tute.dto.VoteResponse;
import com.skills.tute.entity.Vote;

import java.util.List;

public interface VoteService {

    Vote save(VoteRequest vote);

    void deleteAllByUserId(Integer userId);

    List<VoteResponse> calculateVotes(Integer userId);

    Integer countTotalVotes(Integer userId);
}
