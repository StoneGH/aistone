package com.stone.ai.app.provider.controller;

import com.stone.ai.app.api.HelloService;
import com.stone.ai.persist.model.User;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppHelloController implements HelloService {

    @Override
    public String hello1(String name) {
        return "Hello " + name;
    }

    @Override
    public String hello2(Long id) {
        return "Hello id" + id;
    }

    @Override
    public User hello3(User user) {
        return user;
    }
}