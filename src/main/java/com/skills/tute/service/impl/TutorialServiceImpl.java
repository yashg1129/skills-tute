package com.skills.tute.service.impl;

import com.skills.tute.entity.Tutorial;
import com.skills.tute.repository.TutorialRepository;
import com.skills.tute.service.TutorialService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TutorialServiceImpl implements TutorialService {
    private final TutorialRepository repository;

    public TutorialServiceImpl(TutorialRepository repository) { this.repository = repository; }

    @Override
    public List<Tutorial> findAll() { return repository.findAll(); }

    @Override
    public Tutorial findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> notFound(id));
    }

    @Override
    public Tutorial save(Tutorial tutorial) { return repository.save(tutorial); }

    @Override
    public Tutorial update(Tutorial tutorial) {
        //findById(tutorial.getId());
        return repository.save(tutorial);
    }

    @Override
    public void deleteById(Integer id) {
        //findById(id);
        repository.deleteById(id);
    }

    private ResponseStatusException notFound(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutorial not found: " + id);
    }
}
