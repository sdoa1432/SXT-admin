package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.ClaimVoucherRequest;
import com.tencent.wxcloudrun.dto.CurdRequest;
import com.tencent.wxcloudrun.dto.LoginRequest;
import com.tencent.wxcloudrun.dto.VoucherRequest;
import com.tencent.wxcloudrun.result.LoginResult;

import javax.servlet.http.HttpServletRequest;

public interface MainService {


    ApiResponse login(LoginRequest loginRequest);

    boolean checklogin(String token);

    void addOrUpdateVoucher(VoucherRequest voucherRequest);

    void toggleVoucher(CurdRequest curdRequest);

    ApiResponse claimVoucher(ClaimVoucherRequest claimVoucherRequest);

    ApiResponse queryVoucherIssueDetail(CurdRequest curdRequest);

    ApiResponse queryMerchantVoucherDetail(CurdRequest curdRequest);

    ApiResponse claimDetail(CurdRequest curdRequest);

    ApiResponse queryMerchantList(CurdRequest request);

}
