package com.skills.tute.controller;

import com.skills.tute.dto.InterviewQuestionResponse;
import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.service.InterviewQuestionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview-questions")
public class InterviewQuestionController {

    public static final String USER_ID = "userId";
    @Autowired
    private InterviewQuestionService service;

    @GetMapping("/topic/id/{topicId}")
    List<InterviewQuestion> findByTopicId(@PathVariable("topicId") Integer topicId) {
        return service.findByTopicId(topicId);
    }

    @GetMapping("topic/name/{topicName}")
    List<InterviewQuestionResponse> findByTopicName(@PathVariable("topicName") String name, HttpSession session) {
        System.out.println("res: "+session.getAttribute(USER_ID));
        return service.findByTopicNameAndApproval(name, (Integer)session.getAttribute(USER_ID));
    }

}
