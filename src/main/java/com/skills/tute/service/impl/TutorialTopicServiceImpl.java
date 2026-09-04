package com.skills.tute.service.impl;

import com.skills.tute.entity.TutorialTopic;
import com.skills.tute.repository.TutorialTopicRepository;
import com.skills.tute.service.TutorialTopicService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TutorialTopicServiceImpl implements TutorialTopicService {
    private final TutorialTopicRepository repository;

    public TutorialTopicServiceImpl(TutorialTopicRepository repository) { this.repository = repository; }

    @Override
    public List<TutorialTopic> findAll(Integer categoryId) {
        return categoryId == null ? repository.findAll() : repository.findByCategoryIdOrderByName(categoryId);
    }

    @Override
    public TutorialTopic findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> notFound(id));
    }

    @Override
    public TutorialTopic save(TutorialTopic topic) { return repository.save(topic); }

    @Override
    public TutorialTopic update(TutorialTopic topic) {
        //findById(id);
       // topic.setId(id);
        return repository.save(topic);
    }

    @Override
    public void deleteById(Integer id) {
        //findById(id);
        repository.deleteById(id);
    }

    private ResponseStatusException notFound(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutorial topic not found: " + id);
    }
}
