package com.skills.tute.service.impl;

import com.skills.tute.entity.User;
import com.skills.tute.repository.UserRepository;
import com.skills.tute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        user.setDate(LocalDate.now());
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
