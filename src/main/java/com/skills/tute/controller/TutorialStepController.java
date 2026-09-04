package com.skills.tute.controller;

import com.skills.tute.entity.TutorialStep;
import com.skills.tute.service.TutorialStepService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutorial-steps")
public class TutorialStepController {
    private final TutorialStepService service;

    public TutorialStepController(TutorialStepService service) { this.service = service; }

    @GetMapping
    public List<TutorialStep> findAll(@RequestParam(required = false) Integer tutorialId) {
        return service.findAll(tutorialId);
    }

    @GetMapping("/{id}")
    public TutorialStep findById(@PathVariable Integer id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public TutorialStep save(@RequestBody TutorialStep step) { return service.save(step); }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TutorialStep update(@RequestBody TutorialStep step) {
        return service.update(step);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer id) { service.deleteById(id); }
}
