package com.skills.tute.controller;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.entity.InterviewQuestionUser;
import com.skills.tute.service.InterviewQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.skills.tute.utils.SecurityUtils.getUserId;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/interview-questions")
public class InterviewQuestionController {

    @Autowired
    private InterviewQuestionService service;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    InterviewQuestion save(@RequestBody InterviewQuestionRequest questionRequest) throws AccessDeniedException {
        questionRequest.setUserId(getUserId());
        return service.save(questionRequest);
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    InterviewQuestion update(@RequestBody InterviewQuestionRequest questionRequest) throws AccessDeniedException {
        questionRequest.setUserId(getUserId());
        return service.update(questionRequest);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    List<InterviewQuestionUser> findAll(@RequestParam String approval) {
        return service.findAll(approval, getUserId());
    }

    @GetMapping("/topic/id/{topicId}")
    List<InterviewQuestion> findByTopicId(@PathVariable("topicId") Integer topicId) {
        return service.findByTopicId(topicId);
    }

    @GetMapping("topic/name/{topicName}")
    List<InterviewQuestion> findByTopicName(@PathVariable("topicName") String name, @RequestParam String approval) {
        return service.findByTopicNameAndApproval(name, approval);
    }

    @GetMapping("/{id}")
    InterviewQuestion findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    void deleteById(@PathVariable("id") Integer userQuestionId) {
        service.deleteById(userQuestionId, getUserId());
    }
}
