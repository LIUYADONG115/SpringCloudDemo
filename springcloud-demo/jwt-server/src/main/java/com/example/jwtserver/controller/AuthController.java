package com.example.jwtserver.controller;

import com.example.apicommon.model.CommonResult;
import com.example.jwtserver.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RestController
@RequestMapping("tw")
public class AuthController {

    @Autowired
    private AuthService authService;

    //http://localhost:8100/login?username=zcy&password=123456
    @GetMapping("/login")
    public CommonResult login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return authService.login(username,password);
    }

    @GetMapping("/vertify")
    public CommonResult vertify(@RequestParam("token") String token, @RequestParam("username") String username) {
        return authService.vertify(token,username);
    }

    @PostMapping("/refresh")
    public CommonResult refresh(@RequestParam("refreshToekn") String refreshToekn) {
        return authService.refresh(refreshToekn);
    }

}

