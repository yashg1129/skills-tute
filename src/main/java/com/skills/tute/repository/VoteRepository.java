package com.skills.tute.repository;

import com.skills.tute.dto.VoteResponse;
import com.skills.tute.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    void deleteByUserId(Integer userId);

    @Query(value = "select party_id, count(*) as counts from votes where user_id = :userId group by party_id order by counts desc", nativeQuery = true)
    List<VoteResponse> countVotesByPartyForUser(@Param("userId") Integer userId);

    @Query(value = "select count(*) as counts from votes where user_id = :userId", nativeQuery = true)
    Integer countVotes(@Param("userId") Integer userId);
}
