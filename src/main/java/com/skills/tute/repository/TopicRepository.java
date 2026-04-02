package com.skills.tute.repository;

import com.skills.tute.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    @Query(value = "SELECT MAX(id) FROM topics", nativeQuery = true)
    Integer findMaxId();

    Topic findByName(String name);
}
