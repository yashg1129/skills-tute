package com.skills.tute.cache;

import com.skills.tute.entity.City;
import com.skills.tute.entity.Company;
import com.skills.tute.entity.Country;
import com.skills.tute.entity.Topic;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public final class Cache {

    private static List<Topic> topics;
    private static List<Company> companies;
    private static List<Country> countries;
    private static List<City> cities;

    public static List<Topic> getTopics() {
        return topics;
    }

    public static void setTopics(List<Topic> topics) {
        Cache.topics = topics;
    }

    public static List<Company> getCompanies() {
        return companies;
    }

    public static void setCompanies(List<Company> companies) {
        Cache.companies = companies;
    }

    public static List<Country> getCountries() {
        return countries;
    }

    public static void setCountries(List<Country> countries) {
        Cache.countries = countries;
    }

    public static List<City> getCities() {
        return cities;
    }

    public static void setCities(List<City> cities) {
        Cache.cities = cities;
    }
}
