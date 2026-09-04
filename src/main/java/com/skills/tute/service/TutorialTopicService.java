package com.skills.tute.service;

import com.skills.tute.entity.TutorialTopic;

import java.util.List;

public interface TutorialTopicService {

    List<TutorialTopic> findAll(Integer categoryId);

    TutorialTopic findById(Integer id);

    TutorialTopic save(TutorialTopic topic);

    TutorialTopic update(TutorialTopic topic);

    void deleteById(Integer id);
}
