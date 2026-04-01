package com.skills.tute.dto;

import com.skills.tute.entity.City;
import com.skills.tute.entity.Company;
import com.skills.tute.entity.Country;
import com.skills.tute.entity.Topic;
import lombok.Data;

@Data
public class InterviewQuestionRequest {

    private Integer id;
    private Integer userId;
    private Integer experience;
    private String question;

    private Topic topic;
    private Company company;
    private Country country;
    private City city;

}
