package com.skills.tute;

import com.skills.tute.entity.Topic;
import com.skills.tute.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService service;

    @PostMapping
    Topic save(@RequestBody Topic topic) {
        return service.save(topic);
    }

    @PutMapping
    Topic update(@RequestBody Topic topic) {
        return service.save(topic);
    }

    @GetMapping
    List<Topic> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    Topic findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }
}
