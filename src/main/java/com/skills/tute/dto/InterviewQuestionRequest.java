package com.skills.tute.dto;

import lombok.Data;

@Data
public class InterviewQuestionRequest {

    private Integer id;
    private Integer userId;
    private Integer experience;
    private String question;
    private Integer topicId;
    private String topicName;
    private Integer companyId;
    private String companyName;
    private Integer countryId;
    private String countryName;
    private Integer cityId;
    private String cityName;
}
