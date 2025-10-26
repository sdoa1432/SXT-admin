package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dao.MerchantMapper;
import com.tencent.wxcloudrun.dao.MerchantVoucherManagerMapper;
import com.tencent.wxcloudrun.dao.UserVoucherMapper;
import com.tencent.wxcloudrun.dto.ClaimVoucherRequest;
import com.tencent.wxcloudrun.dto.CurdRequest;
import com.tencent.wxcloudrun.dto.LoginRequest;
import com.tencent.wxcloudrun.dto.VoucherRequest;
import com.tencent.wxcloudrun.model.Merchant;
import com.tencent.wxcloudrun.model.MerchantVoucherManager;
import com.tencent.wxcloudrun.model.UserVoucher;
import com.tencent.wxcloudrun.result.LoginResult;
import com.tencent.wxcloudrun.result.MerchantResult;
import com.tencent.wxcloudrun.result.VoucherIssueDetail;
import com.tencent.wxcloudrun.service.MainService;
import com.tencent.wxcloudrun.utils.DateUtil;
import com.tencent.wxcloudrun.utils.JwtUtil;
import com.tencent.wxcloudrun.utils.RandomStringUtils;
import com.tencent.wxcloudrun.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MainServiceImpl implements MainService {

  @Autowired
  MerchantMapper merchantMapper;
  @Autowired
  MerchantVoucherManagerMapper voucherManagerMapper;
  @Autowired
  private UserVoucherMapper userVoucherMapper;
  @Autowired
  JwtUtil jwtUtil;


  @Override
  public ApiResponse login(LoginRequest loginRequest) {

    Merchant merchant = merchantMapper.queryByAccountAndPassword(loginRequest.getAccount(), loginRequest.getPassword());
    log.info("当前登录用户 -> " + JSON.toJSONString(merchant));
    if (merchant != null){
      String token = jwtUtil.signToken(merchant.getId());
      merchant.setLoginToken(token);
      merchantMapper.update(merchant);
      log.info("登录成功 返回token以及登录超时时间");
      LoginResult result = new LoginResult();
      BeanUtils.copyProperties(merchant,result);
      return ApiResponse.ok(result);
    }else{
      return ApiResponse.error("账号密码错误，请检查后重试");
    }
  }

  @Override
  public boolean checklogin(String token) {
    try {
      jwtUtil.verify(token);
      return true;
    }catch (Exception e) {
      return false;
    }
  }

  @Override
  public void addOrUpdateVoucher(VoucherRequest voucherRequest) {
    MerchantVoucherManager voucherManager = new MerchantVoucherManager();
    BeanUtils.copyProperties(voucherRequest, voucherManager);
    if (StringUtils.isBlank(voucherRequest.getId())){
      voucherManager.setId(RandomStringUtils.generate32());
      voucherManagerMapper.insert(voucherManager);
    }else {
      MerchantVoucherManager updateVoucher = voucherManagerMapper.queryById(voucherRequest.getId());
      if (updateVoucher != null){
        voucherManagerMapper.update(updateVoucher);
      }
    }
  }


  @Override
  public void toggleVoucher(CurdRequest curdRequest) {
    MerchantVoucherManager voucherManager = voucherManagerMapper.queryById(curdRequest.getId());
    if (voucherManager != null){
      voucherManager.setShow(!voucherManager.isShow());
      voucherManagerMapper.update(voucherManager);
    }
  }

  @Override
  public ApiResponse claimVoucher(ClaimVoucherRequest claimVoucherRequest) {
    MerchantVoucherManager voucherManager = voucherManagerMapper.queryById(claimVoucherRequest.getVoucherId());
    if (voucherManager != null){
      UserVoucher userVoucher = new UserVoucher();
      userVoucher.setId(RandomStringUtils.generate32());
      userVoucher.setVoucherId(claimVoucherRequest.getVoucherId());
      userVoucher.setVerify(false);
      userVoucher.setObtainedTime(new Timestamp(DateUtil.now().getTime()));
      userVoucher.setUserId(claimVoucherRequest.getUserId());
      userVoucherMapper.insert(userVoucher);
      return ApiResponse.error("领取成功！");
    }else {
      return ApiResponse.error("优惠券信息错误！");
    }
  }

  @Override
  public ApiResponse queryVoucherIssueDetail(CurdRequest curdRequest) {
    MerchantVoucherManager query = new MerchantVoucherManager();
    query.setBelongMerchantId(curdRequest.getId());
    List<MerchantVoucherManager> voucherManagerList = voucherManagerMapper.queryAll(query);
    List<VoucherIssueDetail> result = new ArrayList<>();
    for (MerchantVoucherManager voucherManager : voucherManagerList) {
      VoucherIssueDetail detail = new VoucherIssueDetail();
      UserVoucher queryUserVoucher = new UserVoucher();
      queryUserVoucher.setVoucherId(voucherManager.getId());
      List<UserVoucher> userVouchers = userVoucherMapper.queryAll(queryUserVoucher);
      int verifyNum = 0;
      for (UserVoucher userVoucher : userVouchers) {
        if (userVoucher.isVerify()){
          verifyNum++;
        }
      }
      detail.setVoucherName(voucherManager.getVoucherName());
      detail.setVoucherAmt(voucherManager.getVoucherAmt());
      detail.setVoucherDes(voucherManager.getVoucherDes());
      detail.setIssueNum(userVouchers.size());
      detail.setVerifyNum(verifyNum);
      result.add(detail);
    }
    return ApiResponse.ok(result);
  }

  @Override
  public ApiResponse queryMerchantVoucherDetail(CurdRequest curdRequest) {
    MerchantVoucherManager queryMerchantVoucher = new MerchantVoucherManager();
    queryMerchantVoucher.setBelongMerchantId(curdRequest.getId());
    queryMerchantVoucher.setShow(true);
    List<MerchantVoucherManager> voucherManagerList = voucherManagerMapper.queryAll(queryMerchantVoucher);
    return ApiResponse.ok(voucherManagerList);
  }

  @Override
  public ApiResponse claimDetail(String openId) {
    return ApiResponse.ok();
  }

  @Override
  public ApiResponse queryMerchantList(CurdRequest request) {
    Merchant queryMerchant = new Merchant();
    if (StringUtils.isNotBlank(request.getName())){
      queryMerchant.setMerchantName(request.getName());
    }
    if (request.getType() != 0){
      queryMerchant.setMerchantType(request.getType());
    }
    List<Merchant> data = merchantMapper.queryAll(queryMerchant);
    List<MerchantResult> back = new ArrayList<>();
    for (Merchant merchant : data) {
      MerchantResult result = new MerchantResult();
      BeanUtils.copyProperties(merchant,result);
      back.add(result);
    }
    return ApiResponse.ok(back);
  }
}
