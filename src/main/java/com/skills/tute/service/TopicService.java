package com.skills.tute.service;

import com.skills.tute.entity.Topic;

import java.util.List;

public interface TopicService {

    Topic save(Topic topic);

    Topic update(Topic topic);

    List<Topic> findTopics(String type);

    List<Topic> adminFindByApproveStatus(String approveStatus);

    void deleteById(Integer id);
}
