package com.example.feginserver.controller;

import com.example.apicommon.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    WelcomeInterface welcomeInterface;

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public CommonResult welcome(@RequestParam("username") String username, @RequestParam("password") String password){
        return welcomeInterface.login(username,password);
    }
}



