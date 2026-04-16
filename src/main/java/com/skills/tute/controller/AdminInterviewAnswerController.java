package com.skills.tute.controller;

import com.skills.tute.entity.InterviewAnswer;
import com.skills.tute.enums.ApproveStatus;
import com.skills.tute.exception.PermissionDeniedException;
import com.skills.tute.service.InterviewAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.skills.tute.utils.SecurityUtils.getUserId;
import static com.skills.tute.utils.SecurityUtils.isAdmin;

@RestController
@RequestMapping("/api/interview-answers/admin")
public class AdminInterviewAnswerController {

    @Autowired
    private InterviewAnswerService service;

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    InterviewAnswer update(@RequestBody InterviewAnswer answer) {
        return service.update(answer, isAdmin());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    List<InterviewAnswer> findAll(@RequestParam("approveStatus") String approveStatus) {
        return service.findByApproveStatus(approveStatus);
    }

    @GetMapping("/{id}")
    InterviewAnswer findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

//    @GetMapping("/question/{id}")
//    List<InterviewAnswer> findByQuestionId(@PathVariable("id") Integer questionId) {
//        return service.findByQuestionId(questionId);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

}
