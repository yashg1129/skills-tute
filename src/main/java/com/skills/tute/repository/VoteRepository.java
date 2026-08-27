package com.skills.tute.repository;

import com.skills.tute.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface VoteRepository extends JpaRepository<Vote, Integer> {


    void deleteByUserId(Integer userId);

}
