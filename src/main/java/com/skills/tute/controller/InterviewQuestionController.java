package com.skills.tute.controller;

import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.service.InterviewQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview-questions")
public class InterviewQuestionController {

    @Autowired
    private InterviewQuestionService service;

    @GetMapping("/topic/id/{topicId}")
    List<InterviewQuestion> findByTopicId(@PathVariable("topicId") Integer topicId) {
        return service.findByTopicId(topicId);
    }

    @GetMapping("topic/name/{topicName}")
    List<InterviewQuestion> findByTopicName(@PathVariable("topicName") String name, @RequestParam String approval) {
        return service.findByTopicNameAndApproval(name, approval);
    }

}
