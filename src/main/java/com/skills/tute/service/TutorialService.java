package com.skills.tute.service;

import com.skills.tute.entity.Tutorial;

import java.util.List;

public interface TutorialService {

    List<Tutorial> findAll();

    Tutorial findById(Integer id);

    Tutorial save(Tutorial tutorial);

    Tutorial update(Tutorial tutorial);

    void deleteById(Integer id);
}
