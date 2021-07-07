package com.abc.springboot.controller;

import com.abc.springboot.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping("some.do")
    public String doSome(){
        return "Hello world!";
    }

    @RequestMapping("getUser.do")
    public User getUser(int id,String name,String gender){
        return new User(id,name,gender);
    }
}
