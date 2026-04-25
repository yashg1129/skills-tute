package com.skills.tute.service.impl;

import com.skills.tute.entity.*;
import com.skills.tute.repository.CityRepository;
import com.skills.tute.repository.CompanyRepository;
import com.skills.tute.repository.CountryRepository;
import com.skills.tute.repository.TopicRepository;
import com.skills.tute.service.CommonService;
import com.skills.tute.service.CompanyService;
import com.skills.tute.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private CompanyRepository companyRepository;

    private CompanyService companyService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    @Cacheable("countries")
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    @Override
    @Cacheable("cities")
    public List<City> getCities() {
        return cityRepository.findAll();
    }

    @Override
    public Topic getTopicForUpdate(Topic request) {
        Topic topic;
        if (request.getId() != null) {
            topic = request;
        } else {
            topic = topicRepository.findByName(request.getName());
            if (topic == null) {
                topic = new Topic();
                topic.setName(request.getName());
                Integer id = topicRepository.findMaxId();
                topic.setDisplayOrder(id + 1);
                topic.setTutorial(false);
                topic = topicService.save(topic);
            }
        }
        return topic;
    }

    @Override
    public Company getCompanyForUpdate(Company request) {
        Company company;
        if (request.getId() != null) {
            company = request;
        } else {
            String companyName = request.getName();
            company = companyRepository.findByName(companyName);
            if (company == null) {
                company = new Company();
                company.setName(companyName);
                company = companyService.save(company);
            }
        }
        return company;
    }
}
