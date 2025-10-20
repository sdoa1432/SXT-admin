package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dao.MerchantMapper;
import com.tencent.wxcloudrun.dto.LoginRequest;
import com.tencent.wxcloudrun.model.MerchantPO;
import com.tencent.wxcloudrun.result.LoginResult;
import com.tencent.wxcloudrun.service.MainService;
import com.tencent.wxcloudrun.utils.DateUtil;
import com.tencent.wxcloudrun.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class MainServiceImpl implements MainService {

  @Autowired
  MerchantMapper merchantMapper;


  @Override
  public ApiResponse login(LoginRequest loginRequest) {

    MerchantPO merchantPO = merchantMapper.queryByAccountAndPassword(loginRequest.getAccount(), loginRequest.getPassword());
    if (merchantPO != null){
      String token = JwtUtil.signToken(merchantPO.getId());
      merchantPO.setLoginToken(token);
      merchantMapper.update(merchantPO);
      log.info("登录成功 返回token以及登录超时时间");
      LoginResult result = new LoginResult();
      BeanUtils.copyProperties(merchantPO,result);
      return ApiResponse.ok(result);
    }else{
      return ApiResponse.error("账号密码错误，请检查后重试");
    }
  }

  @Override
  public boolean checklogin(String token) {
    try {
      JwtUtil.verify(token);
      return true;
    }catch (Exception e) {
      return false;
    }
  }
}
