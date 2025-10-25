package com.tencent.wxcloudrun.controller;

import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.dto.ClaimVoucherRequest;
import com.tencent.wxcloudrun.dto.CurdRequest;
import com.tencent.wxcloudrun.dto.VoucherRequest;
import com.tencent.wxcloudrun.result.LoginResult;
import com.tencent.wxcloudrun.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.LoginRequest;
import com.tencent.wxcloudrun.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * counter控制器
 */
@Slf4j
@RestController
public class MainController {

  final MainService mainService;

  private Utils utils;

  public MainController(@Autowired MainService mainService,@Autowired Utils utils) {
    this.mainService = mainService;
    this.utils = utils;
  }

  @PostMapping(value = "/merchant/login")
  ApiResponse postMerchantLogin(@RequestBody LoginRequest loginRequest) {
    log.info("/merchant/login");
    return mainService.login(loginRequest);
  }

  @PostMapping(value = "/voucher/addAndUpdate")
  ApiResponse addVoucher(@RequestBody VoucherRequest voucherRequest) {
    log.info("/voucher/addAndUpdate");
    mainService.addOrUpdateVoucher(voucherRequest);
    return ApiResponse.ok("成功");
  }

  @PostMapping(value = "/voucher/toggle")
  ApiResponse toggleVoucher(@RequestBody CurdRequest curdRequest) {
    log.info("/voucher/toggle");
    mainService.toggleVoucher(curdRequest);
    return ApiResponse.ok("成功");
  }

  @PostMapping(value = "/voucher/claim")
  ApiResponse claimVoucher(@RequestBody ClaimVoucherRequest claimVoucherRequest, HttpServletRequest head) {
    log.info("/voucher/claim ");
    utils.printHead(head);
    return mainService.claimVoucher(claimVoucherRequest);
  }

  @PostMapping(value = "/voucher/claimDetail")
  ApiResponse claimDetail(@RequestBody ClaimVoucherRequest claimVoucherRequest, HttpServletRequest head) {
    log.info("/voucher/claimDetail ");
    utils.printHead(head);
    return mainService.claimDetail(head);
  }

  @PostMapping(value = "/voucher/issueDetail")
  ApiResponse queryVoucherIssueDetail(@RequestBody CurdRequest curdRequest) {
    log.info("/voucher/detail ");
    return mainService.queryVoucherIssueDetail(curdRequest);
  }

  @PostMapping(value = "/voucher/merchantDetail")
  ApiResponse queryMerchantVoucherDetail(@RequestBody CurdRequest curdRequest) {
    log.info("/voucher/detail ");
    return mainService.queryMerchantVoucherDetail(curdRequest);
  }

}