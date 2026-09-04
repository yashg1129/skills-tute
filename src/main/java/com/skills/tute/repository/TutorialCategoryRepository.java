package com.skills.tute.repository;

import com.skills.tute.entity.TutorialCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialCategoryRepository extends JpaRepository<TutorialCategory, Integer> {
}
