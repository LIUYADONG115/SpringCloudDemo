package com.example.feginserver.error;

import com.example.apicommon.model.CommonResult;
import com.example.feginserver.controller.WelcomeInterface;
import org.springframework.stereotype.Component;

@Component
public class WelcomeError implements WelcomeInterface {
    @Override
    public CommonResult login(String username, String password) {
        CommonResult result = new CommonResult();
        result.setCode(-1);
        result.setMessage("哎呀呀,不好意思"+username+",出错了呀!");
        return result;
    }

    @Override
    public CommonResult vertify(String token, String username) {
        CommonResult result = new CommonResult();
        result.setCode(-1);
        result.setMessage("哎呀呀,不好意思"+username+",出错了呀!");
        return result;
    }

    @Override
    public CommonResult refresh(String refreshToekn) {
        CommonResult result = new CommonResult();
        result.setCode(-1);
        result.setMessage("哎呀呀,不好意思,出错了呀!");
        return result;
    }
}
