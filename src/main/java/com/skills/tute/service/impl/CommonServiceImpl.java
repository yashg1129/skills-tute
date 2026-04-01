package com.skills.tute.service.impl;

import com.skills.tute.cache.Cache;

import com.skills.tute.entity.City;
import com.skills.tute.entity.Company;
import com.skills.tute.entity.Country;
import com.skills.tute.entity.Topic;
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
    public List<Topic> getTopics() {
        List<Topic> topics = Cache.getTopics();
        if(topics == null) {
           topics = topicRepository.findAll();
           Cache.setTopics(topics);
        }
        return topics;
    }

    @Override
    public List<Company> getCompanies() {
        List<Company> companies = Cache.getCompanies();
        if(companies == null) {
            companies = companyRepository.findAll();
            Cache.setCompanies(companies);
        }
        return companies;
    }

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
}
