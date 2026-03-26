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

    @PostMapping
    InterviewQuestion save(@RequestBody InterviewQuestion question) {
        return service.save(question);
    }

    @PutMapping
    InterviewQuestion update(@RequestBody InterviewQuestion question) {
        return service.update(question);
    }

    @GetMapping
    List<InterviewQuestion> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    InterviewQuestion findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }
}
