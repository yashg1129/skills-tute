package com.skills.tute.controller;

import com.skills.tute.entity.InterviewAnswer;
import com.skills.tute.enums.ApproveStatus;
import com.skills.tute.exception.PermissionDeniedException;
import com.skills.tute.service.InterviewAnswerService;
import com.skills.tute.utils.StConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

import static com.skills.tute.utils.SecurityUtils.getUserId;
import static com.skills.tute.utils.SecurityUtils.isAdmin;

@RestController
@RequestMapping("/api/admin/interview-answers")
public class AdminInterviewAnswerController {

    @Autowired
    private InterviewAnswerService service;

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    InterviewAnswer update(@RequestBody InterviewAnswer answer) {
        return service.update(answer, true);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    List<InterviewAnswer> findAll(@RequestParam("approveStatus") String approveStatus) {
        return service.findByApproveStatus(approveStatus);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    InterviewAnswer findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

}
