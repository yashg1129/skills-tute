package com.skills.tute.controller;

import com.skills.tute.entity.InterviewAnswer;
import com.skills.tute.enums.ApproveStatus;
import com.skills.tute.exception.PermissionDeniedException;
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

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    InterviewAnswer save(@RequestBody InterviewAnswer answer) throws BadRequestException {
        if(answer.getId() != null) {
            throw new BadRequestException("Invalid request");
        }
        answer.setUserId(getUserId());
        return service.save(answer);
    }

    @PutMapping
    InterviewAnswer update(@RequestBody InterviewAnswer answer) throws AccessDeniedException {
        if(answer.getApproveStatus() != null)  {
            throw new AccessDeniedException(StConstant.FORBIDDEN_EXCEPTION);
        }
        return service.update(answer, isAdmin());
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    List<InterviewAnswer> findAll() {
        return service.findByUserId(getUserId());
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
        service.deleteByUserIdAndId(getUserId(), id);
    }
}
