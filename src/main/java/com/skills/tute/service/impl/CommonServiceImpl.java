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
        if(countries == null) {
            countries = countryRepository.findAll();
            Cache.setCountries(countries);
        }
        return countries;
    }

    @Override
    public List<City> getCities() {
        List<City> cities = Cache.getCities();
        if(cities == null) {
            cities = cityRepository.findAll();
            Cache.setCities(cities);
        }
        return cities;
    }

    @Override
    public Topic getTopicForUpdate(InterviewQuestionRequest request, InterviewQuestion question) {
        Topic topic;
        String topicName = request.getTopic().getName();
        if(!topicName.equals(question.getTopic().getName())) {
            topic = topicRepository.findByName(topicName);
            if(topic == null) {
                topic = new Topic();
                topic.setName(topicName);
                Integer id = topicRepository.findMaxId();
                topic.setDisplayOrder(id + 1);
                topic.setTutorial(false);
                topic = topicRepository.save(topic);
            }
        } else {
            topic = request.getTopic();
        }
        return topic;
    }

    @Override
    public Company getCompanyForUpdate(InterviewQuestionRequest request, InterviewQuestionUser question) {
        Company company;
        String companyName = request.getCompany().getName();
        if(!companyName.equals(question.getCompany().getName())) {
            company = companyRepository.findByName(companyName);
            if(company == null) {
                company = new Company();
                company.setName(companyName);
                company = companyRepository.save(company);
            }
        } else {
            company = request.getCompany();
        }
        return company;
    }
}
