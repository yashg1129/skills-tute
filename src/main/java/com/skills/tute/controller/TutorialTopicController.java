package com.skills.tute.controller;

import com.skills.tute.entity.TutorialTopic;
import com.skills.tute.service.TutorialTopicService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutorial-topics")
public class TutorialTopicController {
    private final TutorialTopicService service;

    public TutorialTopicController(TutorialTopicService service) { this.service = service; }

    @GetMapping
    public List<TutorialTopic> findAll(@RequestParam(required = false) Integer categoryId) {
        return service.findAll(categoryId);
    }

    @GetMapping("/{id}")
    public TutorialTopic findById(@PathVariable Integer id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public TutorialTopic save(@RequestBody TutorialTopic topic) { return service.save(topic); }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TutorialTopic update(@RequestBody TutorialTopic topic) {
        return service.update(topic);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer id) { service.deleteById(id); }
}
