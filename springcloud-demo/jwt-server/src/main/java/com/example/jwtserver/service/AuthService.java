package com.example.jwtserver.service;

import com.example.apicommon.model.CommonResult;

public interface AuthService {
     CommonResult login(String username, String password);

     CommonResult vertify(String token, String username);

     CommonResult refresh(String refreshToekn);
}
