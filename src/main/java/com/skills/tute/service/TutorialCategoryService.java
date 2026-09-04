package com.skills.tute.service;

import com.skills.tute.entity.TutorialCategory;

import java.util.List;

public interface TutorialCategoryService {

    List<TutorialCategory> findAll();

    TutorialCategory findById(Integer id);

    TutorialCategory save(TutorialCategory category);

    TutorialCategory update(TutorialCategory category);

    void deleteById(Integer id);
}
