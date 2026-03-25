package com.skills.tute.controller;

import com.skills.tute.entity.InterviewAnswer;
import com.skills.tute.service.InterviewAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview-answers")
public class InterviewAnswerController {

    @Autowired
    private InterviewAnswerService service;

    @PostMapping
    InterviewAnswer save(@RequestBody InterviewAnswer answer) {
        return service.save(answer);
    }

    @PutMapping
    InterviewAnswer update(@RequestBody InterviewAnswer answer) {
        return service.update(answer);
    }

    @GetMapping
    List<InterviewAnswer> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    InterviewAnswer findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }
}
