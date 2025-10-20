package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.dto.CurdRequest;
import com.tencent.wxcloudrun.dto.VoucherRequest;
import com.tencent.wxcloudrun.result.LoginResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.LoginRequest;
import com.tencent.wxcloudrun.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * counter控制器
 */
@RestController(value = "/sxt")
public class MainController {

  final MainService mainService;
  final Logger logger;

  public MainController(@Autowired MainService mainService) {
    this.mainService = mainService;
    this.logger = LoggerFactory.getLogger(MainController.class);
  }

  @PostMapping(value = "/merchant/login")
  ApiResponse postMerchantLogin(@RequestBody LoginRequest loginRequest) {
    logger.info("/merchant/login");
    return mainService.login(loginRequest);
  }

  @PostMapping(value = "/voucher/add")
  ApiResponse addVoucher(@RequestBody VoucherRequest voucherRequest) {
    logger.info("/voucher/add");




    return ApiResponse.ok("成功");
  }

  @PostMapping(value = "/voucher/edit")
  ApiResponse updateVoucher(@RequestBody VoucherRequest voucherRequest) {
    logger.info("/voucher/edit");



    return ApiResponse.ok("成功");
  }

  @PostMapping(value = "/voucher/toggle")
  ApiResponse toggleVoucher(@RequestBody CurdRequest curdRequest) {
    logger.info("/voucher/toggle");



    return ApiResponse.ok("成功");
  }

  @PostMapping(value = "/voucher/get")
  ApiResponse getVoucher(@RequestBody CurdRequest curdRequest) {
    logger.info("/voucher/get");



    return ApiResponse.ok("成功");
  }

  @PostMapping(value = "/voucher/detail")
  ApiResponse queryVoucherUseDetail(@RequestBody CurdRequest curdRequest) {
    logger.info("/voucher/detail ");

    return ApiResponse.ok("成功");
  }

}