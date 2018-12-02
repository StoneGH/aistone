package com.stone.ai.app.api;

import com.stone.ai.persist.model.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/hello")
public interface HelloService {

    @RequestMapping(value = "/api/v1/hello1", method = RequestMethod.POST)
    String hello1(@RequestParam("name") String name);

    @GetMapping(value = "/api/v1/hello2/{id}")
    String hello2(@PathVariable("id") Long id);

    @RequestMapping(value = "/api/v1/hello3", method = RequestMethod.POST)
    User hello3(@RequestBody User user);
}
