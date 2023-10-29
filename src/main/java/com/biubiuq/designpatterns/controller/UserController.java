package com.biubiuq.designpatterns.controller;

import com.biubiuq.designpatterns.adapter.Login3rdAdapter;
import com.biubiuq.designpatterns.entity.User;
import com.biubiuq.designpatterns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author biubiuQ
 * @description UserController
 * @date 2023/10/29
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Login3rdAdapter login3rdAdapter;

    @PostMapping("/login")
    public String login(String account, String password) {
        return userService.login(account,password);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/gitee")
    public String gitee( String code,String state) {
        return login3rdAdapter.loginByGitee(code, state);
    }
}
