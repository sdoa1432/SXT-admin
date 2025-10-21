package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class VoucherRequest {

  private String id ;
  /** 消费券名称,; */
  private String voucherName ;
  /** 消费金额,; */
  private String voucherAmt ;
  /** 归属商户id,; */
  private String belongMerchantId ;
  /** 消费券介绍,; */
  private String voucherDes ;
}
