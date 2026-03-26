package com.skills.tute.service.impl;

import com.skills.tute.entity.User;
import com.skills.tute.exception.BadCredentialsException;
import com.skills.tute.repository.UserRepository;
import com.skills.tute.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(User user) {
        User res = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (res != null) {
            return res;
        }
        throw new BadCredentialsException("Invalid mobile/email or password");
    }
}
