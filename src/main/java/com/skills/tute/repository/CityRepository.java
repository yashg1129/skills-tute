package com.skills.tute.repository;

import com.skills.tute.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {

    boolean existsByName(String name);

}
