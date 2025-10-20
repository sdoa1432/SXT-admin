package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.LoginRequest;
import com.tencent.wxcloudrun.result.LoginResult;

public interface MainService {


    ApiResponse login(LoginRequest loginRequest);

    boolean checklogin(String token);
}
