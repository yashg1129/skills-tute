package com.skills.tute.controller;

import com.skills.tute.entity.User;
import com.skills.tute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    User save(@RequestBody User user) {
        return service.save(user);
    }

    @PutMapping
    User update(@RequestBody User user) {
        return service.update(user);
    }

    @GetMapping
    List<User> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    User findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }
}
