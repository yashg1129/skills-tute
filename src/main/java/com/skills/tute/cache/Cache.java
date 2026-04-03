package com.skills.tute.cache;

import com.skills.tute.entity.City;
import com.skills.tute.entity.Company;
import com.skills.tute.entity.Country;
import com.skills.tute.entity.Topic;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public final class Cache {

    @Getter
    private static List<Topic> topics;
    @Getter
    private static List<Company> companies;
    @Getter
    private static List<Country> countries;
    @Getter
    private static List<City> cities;

    public static void setTopics(List<Topic> topics) {
        Cache.topics = topics;
    }

    public static void setCompanies(List<Company> companies) {
        Cache.companies = companies;
    }

    public static void setCountries(List<Country> countries) {
        Cache.countries = countries;
    }

    public static void setCities(List<City> cities) {
        Cache.cities = cities;
    }

    public static void clearTopics() {
        topics.clear();
    }

    public static void clearCompanies() {
        companies = null;
    }

    public static void clearCountries() {
        countries = null;
    }

    public static void clearCities() {
        cities = null;
    }
}
