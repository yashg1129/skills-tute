package com.skills.tute.service.impl;

import com.skills.tute.entity.Topic;
import com.skills.tute.repository.TopicRepository;
import com.skills.tute.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository repository;

    @Override
    public Topic save(Topic topic) {
        return repository.save(topic);
    }

    @Override
    public Topic update(Topic topic) {
        return repository.save(topic);
    }

    @Override
    public List<Topic> findAll() {
        return repository.findAll();
    }

    @Override
    public Topic findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
