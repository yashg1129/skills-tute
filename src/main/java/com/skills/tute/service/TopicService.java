package com.skills.tute.service;

import com.skills.tute.entity.Topic;
import com.skills.tute.enums.ApproveStatus;

import java.util.List;

public interface TopicService {

    Topic update(Topic topic);

    List<Topic> findByApproveStatus(String approveStatus);

    void deleteById(Integer id);
}
