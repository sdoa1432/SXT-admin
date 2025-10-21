package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MerchantVoucherManager implements Serializable,Cloneable{
    /** 消费券ID,; */
    private String id ;
    /** 消费券名称,; */
    private String voucherName ;
    /** 消费金额,; */
    private String voucherAmt ;
    /** 归属商户id,; */
    private String belongMerchantId ;
    /** 消费券介绍,; */
    private String voucherDes ;
    /** 是否上线,; */
    private boolean isShow ;
}
