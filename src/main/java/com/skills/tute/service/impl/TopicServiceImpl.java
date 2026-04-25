package com.skills.tute.service.impl;

import com.skills.tute.entity.Topic;
import com.skills.tute.enums.ApproveStatus;
import com.skills.tute.repository.TopicRepository;
import com.skills.tute.service.TopicService;
import com.skills.tute.utils.StConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository repository;

    @Override
    //@CacheEvict(value = "topics", allEntries = true)
    public Topic save(Topic topic) {
        return repository.save(topic);
    }

    @Override
    //@CacheEvict(value = "topics", allEntries = true)
    public Topic update(Topic topic) {
        return repository.save(topic);
    }

    @Override
    @Cacheable("topics")
    public List<Topic> findByApprovedStatus(boolean isTutorial) {
        List<Topic> list;
        if(isTutorial) {
            list = repository.findByApproveStatusAndTutorialOrderByDisplayOrder(ApproveStatus.APPROVED, true);
        } else {
            list = repository.findByApproveStatusOrderByName(ApproveStatus.APPROVED);
        }
        return list;
    }

    @Override
    public List<Topic> adminFindByApproveStatus(String approveStatus) {
        List<Topic> list;
        if(StConstant.ALL.equals(approveStatus)) {
            list = repository.findAll();
        } else {
            list = repository.findByApproveStatus(ApproveStatus.valueOf(approveStatus));
        }
        return list;
    }

    @Override
    //@CacheEvict(value = "topics", allEntries = true)
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
