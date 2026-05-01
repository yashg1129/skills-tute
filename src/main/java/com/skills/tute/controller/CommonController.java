package com.skills.tute.controller;

import com.skills.tute.entity.City;
import com.skills.tute.entity.Company;
import com.skills.tute.entity.Country;
import com.skills.tute.entity.Topic;
import com.skills.tute.service.CommonService;
import com.skills.tute.service.CompanyService;
import com.skills.tute.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommonController {

    @Autowired
    private CommonService service;

    @Autowired
    private TopicService topicService;

    @Autowired
    private CompanyService commonService;

    @GetMapping("/topics")
    List<Topic> findTopics(@RequestParam("type") String type) {
        return topicService.findTopics(type);
    }

    @GetMapping("/companies")
    public List<Company> getCompanies(@RequestParam String approveStatus) {
        return commonService.getCompanies(approveStatus);
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
