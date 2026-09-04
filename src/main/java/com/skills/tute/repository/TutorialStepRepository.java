package com.skills.tute.repository;

import com.skills.tute.entity.TutorialStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialStepRepository extends JpaRepository<TutorialStep, Integer> {

    List<TutorialStep> findByTutorialIdOrderByDisplayOrder(Integer tutorialId);
}
