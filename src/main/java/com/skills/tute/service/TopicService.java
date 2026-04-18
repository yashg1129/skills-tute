package com.skills.tute.service;

import com.skills.tute.entity.Topic;

import java.util.List;

public interface TopicService {

    Topic update(Topic topic);

    List<Topic> findByApprovedStatus(boolean isTutorial);

    List<Topic> findByApproveStatus(String approveStatus);

    void deleteById(Integer id);
}
