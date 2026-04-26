package com.skills.tute.repository;

import com.skills.tute.entity.Notes;
import com.skills.tute.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Integer> {
    Notes findByTopicAndUserId(Topic topic, Integer userId);
}
