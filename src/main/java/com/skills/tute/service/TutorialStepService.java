package com.skills.tute.service;

import com.skills.tute.entity.TutorialStep;
import java.util.List;

public interface TutorialStepService {
    List<TutorialStep> findAll(Integer tutorialId);
    TutorialStep findById(Integer id);
    TutorialStep save(TutorialStep step);
    TutorialStep update(TutorialStep step);
    void deleteById(Integer id);
}
