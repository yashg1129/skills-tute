package com.skills.tute.service;

import com.skills.tute.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);
    User update(User user);
    User findById(Integer id);
    List<User> findAll();
    void deleteById(Integer id);
}
