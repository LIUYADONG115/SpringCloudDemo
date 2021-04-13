package com.example.feginserver.controller;

import com.example.apicommon.model.CommonResult;
import com.example.feginserver.error.WelcomeError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "jwt-auth-server-service",fallback = WelcomeError.class)
public interface WelcomeInterface {
    @RequestMapping(value = "/tw/login",method = RequestMethod.GET)
    public CommonResult login(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping(value = "/tw/vertify",method = RequestMethod.GET)
    public CommonResult vertify(@RequestParam("token") String token, @RequestParam("username") String username);

    @RequestMapping(value = "/tw/refresh",method = RequestMethod.POST)
    public CommonResult refresh(@RequestParam("refreshToekn") String refreshToekn);

}
