package com.skills.tute.controller;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.service.AdminInterviewQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("api/admin/interview-questions")
public class AdminInterviewQuestionController {

    @Autowired
    private AdminInterviewQuestionService service;

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    InterviewQuestion update(@RequestBody InterviewQuestionRequest questionRequest) throws AccessDeniedException {
        return service.update(questionRequest, null);
    }

    @PutMapping("/{approveStatus}")
    @PreAuthorize("hasRole('ADMIN')")
    InterviewQuestion updateApproveStatus(@RequestBody InterviewQuestionRequest questionRequest, @PathVariable("approveStatus") String approveStatus) throws AccessDeniedException {
        return service.update(questionRequest, approveStatus);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    List<InterviewQuestion> findAll(@RequestParam String approval) {
        return service.findAll(approval);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }
}
