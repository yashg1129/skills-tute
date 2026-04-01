package com.skills.tute.service;

import com.skills.tute.entity.City;
import com.skills.tute.entity.Company;
import com.skills.tute.entity.Country;
import com.skills.tute.entity.Topic;

import java.util.List;

public interface CommonService {

    List<Topic> getTopics();

    List<Company> getCompanies();

    List<Country> getCountries();

    List<City> getCities();
}
