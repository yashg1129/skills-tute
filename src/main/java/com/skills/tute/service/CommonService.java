package com.skills.tute.service;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.*;

import java.util.List;

public interface CommonService {

    //List<Company> getCompanies();

    List<Country> getCountries();

    List<City> getCities();

    Topic getTopicForUpdate(InterviewQuestionRequest request, InterviewQuestion question);

    Company getCompanyForUpdate(InterviewQuestionRequest request, InterviewQuestionUser question);
}
