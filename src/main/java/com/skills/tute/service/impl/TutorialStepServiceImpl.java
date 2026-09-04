package com.skills.tute.service.impl;

import com.skills.tute.entity.TutorialStep;
import com.skills.tute.repository.TutorialStepRepository;
import com.skills.tute.service.TutorialStepService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TutorialStepServiceImpl implements TutorialStepService {
    private final TutorialStepRepository repository;

    public TutorialStepServiceImpl(TutorialStepRepository repository) { this.repository = repository; }

    @Override
    public List<TutorialStep> findAll(Integer tutorialId) {
        return tutorialId == null ? repository.findAll() : repository.findByTutorialIdOrderByDisplayOrder(tutorialId);
    }

    @Override
    public TutorialStep findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> notFound(id));
    }

    @Override
    public TutorialStep save(TutorialStep step) { return repository.save(step); }

    @Override
    public TutorialStep update(TutorialStep step) {
        findById(step.getId());
        return repository.save(step);
    }

    @Override
    public void deleteById(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private ResponseStatusException notFound(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutorial step not found: " + id);
    }
}
