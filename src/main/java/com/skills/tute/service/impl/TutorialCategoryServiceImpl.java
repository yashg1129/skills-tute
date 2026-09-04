package com.skills.tute.service.impl;

import com.skills.tute.entity.TutorialCategory;
import com.skills.tute.repository.TutorialCategoryRepository;
import com.skills.tute.service.TutorialCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TutorialCategoryServiceImpl implements TutorialCategoryService {
    private final TutorialCategoryRepository repository;

    public TutorialCategoryServiceImpl(TutorialCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TutorialCategory> findAll() { return repository.findAll(); }

    @Override
    public TutorialCategory findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> notFound(id));
    }

    @Override
    public TutorialCategory save(TutorialCategory category) { return repository.save(category); }

    @Override
    public TutorialCategory update(TutorialCategory category) {
        return repository.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        //findById(id);
        repository.deleteById(id);
    }

    private ResponseStatusException notFound(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutorial category not found: " + id);
    }
}
