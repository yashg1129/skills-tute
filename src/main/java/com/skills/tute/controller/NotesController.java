package com.skills.tute.controller;

import com.skills.tute.dto.NotesRequest;
import com.skills.tute.entity.Notes;
import com.skills.tute.service.NotesService;
import static com.skills.tute.utils.SecurityUtils.getUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    private NotesService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    Notes save(@RequestBody Notes notes) {
        notes.getId().setUserId(getUserId());
        return service.save(notes);
    }

//    @PutMapping
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//    Notes update(@RequestBody Notes notes) {
//        notes.setUserId(getUserId());
//        return service.update(notes);
//    }

    @GetMapping("/{topicId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    Notes find(@PathVariable("topicId") Integer topicId) {
        return service.findByTopicAndUserId(topicId, getUserId());
    }
}
