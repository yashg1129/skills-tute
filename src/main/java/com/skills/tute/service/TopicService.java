package com.skills.tute.service;

import com.skills.tute.entity.InterviewAnswer;
import com.skills.tute.entity.Topic;

import java.util.List;

public interface TopicService {

  Topic save(Topic topic);
  Topic update(Topic topic);
  Topic findById(Integer id);
  List<Topic> findAll();
  void deleteById(Integer id);

}
