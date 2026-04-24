package com.skills.tute.service.impl;

import com.skills.tute.cache.Cache;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.*;
import com.skills.tute.repository.CityRepository;
import com.skills.tute.repository.CompanyRepository;
import com.skills.tute.repository.CountryRepository;
import com.skills.tute.repository.TopicRepository;
import com.skills.tute.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<Country> getCountries() {
        List<Country> countries = Cache.getCountries();
        if (countries == null) {
            countries = countryRepository.findAll();
            Cache.setCountries(countries);
        }
        return countries;
    }

    @Override
    public List<City> getCities() {
        List<City> cities = Cache.getCities();
        if (cities == null) {
            cities = cityRepository.findAll();
            Cache.setCities(cities);
        }
        return cities;
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
                topic = topicRepository.save(topic);
            }
            Cache.clearTopics();
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
                company = companyRepository.save(company);
            }
            Cache.clearCompanies();
        }
        return company;
    }
}
