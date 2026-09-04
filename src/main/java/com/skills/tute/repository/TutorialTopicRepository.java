package com.skills.tute.repository;

import com.skills.tute.entity.TutorialTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialTopicRepository extends JpaRepository<TutorialTopic, Integer> {

    List<TutorialTopic> findByCategoryIdOrderByName(Integer categoryId);
}
