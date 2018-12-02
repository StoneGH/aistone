package com.stone.ai.app.consumer.controller;

import com.stone.ai.app.consumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/v1/hello1")
    public Object hello1(@RequestParam("name") String name) {
        return this.helloService.hello1(name);
    }

    @RequestMapping(value = "/v1/hi")
    public Object hello1() {
        return this.helloService.hello1("hi");
    }
}