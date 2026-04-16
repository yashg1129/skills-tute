package com.skills.tute.service.impl;

import com.skills.tute.cache.Cache;
import com.skills.tute.entity.Topic;
import com.skills.tute.enums.ApproveStatus;
import com.skills.tute.repository.TopicRepository;
import com.skills.tute.service.TopicService;
import com.skills.tute.utils.StConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository repository;

    @Override
    public Topic update(Topic topic) {
        Cache.clearTopics();
        return repository.save(topic);
    }

    @Override
    public List<Topic> findByApproveStatus(String approveStatus) {
        List<Topic> list;
        if(StConstant.ALL.equals(approveStatus)) {
            list = Cache.getTopics();
            if(list == null) {
                list = repository.findAll();
                Cache.setTopics(list);
            }
        } else {
            list = repository.findByApproveStatus(ApproveStatus.valueOf(approveStatus));
        }
        return list;
    }

    @Override
    public void deleteById(Integer id) {
        Cache.clearTopics();
        repository.deleteById(id);
    }
}
