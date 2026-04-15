package com.skills.tute.controller;

import com.skills.tute.entity.InterviewAnswer;
import com.skills.tute.service.InterviewAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.skills.tute.utils.SecurityUtils.getUserId;

@RestController
@RequestMapping("/api/interview-answers")
public class InterviewAnswerController {

    @Autowired
    private InterviewAnswerService service;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    InterviewAnswer save(@RequestBody InterviewAnswer answer) {
        answer.setUserId(getUserId());
        return service.save(answer);
    }

    @PutMapping
    InterviewAnswer update(@RequestBody InterviewAnswer answer) {
        return service.update(answer);
    }

    @PutMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    InterviewAnswer updateAdmin(@RequestBody InterviewAnswer answer) {
        return service.update(answer);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    List<InterviewAnswer> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    InterviewAnswer findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @GetMapping("/question/{id}")
    List<InterviewAnswer> findByQuestionId(@PathVariable("id") Integer questionId) {
        return service.findByQuestionId(questionId);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }
}
