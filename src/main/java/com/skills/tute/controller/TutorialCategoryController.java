package com.skills.tute.controller;

import com.skills.tute.entity.TutorialCategory;
import com.skills.tute.service.TutorialCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin-tutorial-categories")
public class TutorialCategoryController {
    private final TutorialCategoryService service;

    public TutorialCategoryController(TutorialCategoryService service) { this.service = service; }

    @GetMapping
    public List<TutorialCategory> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public TutorialCategory findById(@PathVariable Integer id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public TutorialCategory save(@RequestBody TutorialCategory category) {
        return service.save(category);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TutorialCategory update(@RequestBody TutorialCategory category) {
        return service.update(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer id) { service.deleteById(id); }
}
