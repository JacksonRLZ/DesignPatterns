package com.biubiuq.designpatterns.service;

import com.biubiuq.designpatterns.entity.User;
import com.biubiuq.designpatterns.repo.UserRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author biubiuQ
 * @description UserService
 * @date 2023/10/29
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            return "login success";
        } else {
            return "account / password error";
        }
    }

    public String register(User user) {
        if (checkUserExists(user.getUsername())) {
            throw new RuntimeException("User already registered");
        }
        user.setCreateDate(new Date());
        userRepository.save(user);
        return "Register success";
    }


    protected boolean checkUserExists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }
}
