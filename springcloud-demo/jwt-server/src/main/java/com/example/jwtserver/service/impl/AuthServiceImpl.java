package com.example.jwtserver.service.impl;

import com.example.apicommon.model.Account;
import com.example.apicommon.model.CommonResult;
import com.example.apicommon.util.JwtUtils;
import com.example.jwtserver.service.AuthService;
import com.example.jwtserver.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.UUID;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Resource
    RedisUtil redisTemplate;

    private static JwtUtils jwtUtils = new JwtUtils();

    @Override
    public CommonResult login(String username, String password) {
        Account account = Account.builder()
                .username(username)
                .build();
        String token = jwtUtils.token(account);
        log.info("token is :{}",token);
        account.setToken(token);
        String refreshToken = UUID.randomUUID().toString();
        System.out.println("refreshToken is :" + refreshToken);
        account.setRefreshToken(refreshToken);
        redisTemplate.set(account.getRefreshToken(), account);
        return new CommonResult(200, "登录成功 ", account);
    }

    @Override
    public CommonResult vertify(String token, String username) {
        log.info("开始验证.............");
        boolean vertify = jwtUtils.vertify(username, token);
        log.info("结果验证:{}",vertify);
        return new CommonResult(200, "校验成功 ", vertify);
    }

    @Override
    public CommonResult refresh(String refreshToekn) {

        Account account = (Account)  redisTemplate.get(refreshToekn);;
        if (account == null) {
            return new CommonResult(200, "user not found ", null);
        }
        //重新申请一个token
        String token = jwtUtils.token(account);
        log.info("new applay token is :{}",token);
        account.setToken(token);
        account.setRefreshToken(UUID.randomUUID().toString());
        redisTemplate.del(refreshToekn);
        redisTemplate.set(account.getRefreshToken(), account);
        return new CommonResult(200, "token刷新成功 ", account);
    }
}
