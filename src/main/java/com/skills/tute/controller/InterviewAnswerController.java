package com.skills.tute.controller;

import com.skills.tute.entity.InterviewAnswer;
import com.skills.tute.enums.ApproveStatus;
import com.skills.tute.service.InterviewAnswerService;
import com.skills.tute.utils.StConstant;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

import static com.skills.tute.utils.SecurityUtils.getUserId;
import static com.skills.tute.utils.SecurityUtils.isAdmin;

@RestController
@RequestMapping("/api/interview-answers")
public class InterviewAnswerController {

    @Autowired
    private InterviewAnswerService service;

    @GetMapping("/question/{id}")
    List<InterviewAnswer> findByQuestionId(@PathVariable("id") Integer questionId) {
        return service.findByApprovedAnswerQuestionId(questionId);
    }

}
