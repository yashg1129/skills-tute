package com.skills.tute.repository;

import com.skills.tute.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    void deleteAllByUserId(Integer userId);

}
