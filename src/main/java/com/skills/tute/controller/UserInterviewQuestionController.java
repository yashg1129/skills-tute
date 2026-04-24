package com.skills.tute.controller;

import com.skills.tute.dto.InterviewQuestionRequest;
import com.skills.tute.entity.InterviewQuestion;
import com.skills.tute.entity.InterviewQuestionUser;
import com.skills.tute.enums.ApproveStatus;
import com.skills.tute.service.InterviewQuestionService;
import com.skills.tute.utils.StConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

import static com.skills.tute.utils.SecurityUtils.getUserId;

@RestController
@RequestMapping("/api/user/interview-questions")
public class UserInterviewQuestionController {

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
    InterviewQuestionUser update(@RequestBody InterviewQuestionUser interviewQuestionUser) throws AccessDeniedException {
        if(ApproveStatus.APPROVED.equals(interviewQuestionUser.getInterviewQuestion().getApproveStatus()))  {
            throw new AccessDeniedException(StConstant.FORBIDDEN_EXCEPTION);
        }
        interviewQuestionUser.setUserId(getUserId());
        return service.update(interviewQuestionUser);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    List<InterviewQuestionUser> findAll(@RequestParam String approveStatus) {
        return service.findAll(approveStatus, getUserId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    void deleteById(@PathVariable("id") Integer userQuestionId) {
        service.deleteById(userQuestionId, getUserId());
    }
}
