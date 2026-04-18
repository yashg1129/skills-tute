package com.skills.tute.controller;

import com.skills.tute.entity.Topic;
import com.skills.tute.enums.ApproveStatus;
import com.skills.tute.service.TopicService;
import com.skills.tute.utils.StConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService service;

    @GetMapping
    List<Topic> findApprovedTopics(@RequestParam("tutorial") Boolean isTutorial) {
        return service.findByApprovedStatus(isTutorial);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    List<Topic> findAll(@RequestParam("approveStatus") String approveStatus) {
        return service.findByApproveStatus(approveStatus);
    }

    @PutMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    Topic update(@RequestBody Topic topic) {
        return service.update(topic);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }
}
