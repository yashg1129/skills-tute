package com.skills.tute.controller;

import com.skills.tute.entity.City;
import com.skills.tute.entity.Company;
import com.skills.tute.entity.Country;
import com.skills.tute.entity.Topic;
import com.skills.tute.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommonController {

    @Autowired
    private CommonService service;

    @GetMapping("/topics")
    List<Topic> getTopics() {
        return service.getTopics();
    }

    @GetMapping("/companies")
    List<Company> getCompanies() {
        return service.getCompanies();
    }

    @GetMapping("/countries")
    List<Country> getCountries() {
        return service.getCountries();
    }

    @GetMapping("/cities")
    List<City> getCities() {
        return service.getCities();
    }

}
