package com.skills.tute.controller;

import com.skills.tute.entity.Tutorial;
import com.skills.tute.service.TutorialService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {
    private final TutorialService service;

    public TutorialController(TutorialService service) { this.service = service; }

    @GetMapping
    public List<Tutorial> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Tutorial findById(@PathVariable Integer id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Tutorial save(@RequestBody Tutorial tutorial) { return service.save(tutorial); }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Tutorial update(@RequestBody Tutorial tutorial) {
        return service.update(tutorial);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer id) { service.deleteById(id); }
}
