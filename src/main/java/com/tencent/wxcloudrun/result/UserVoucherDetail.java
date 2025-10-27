package com.tencent.wxcloudrun.result;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户消费券领取表;数据表的PO对象
 * @author : http://www.yonsum.com
 * @date : 2025-10-21
 */
@Data
public class UserVoucherDetail implements Serializable,Cloneable{
    /** 用户领取消费券ID,; */
    private String id ;
    /** 商户消费券ID,; */
    private String voucherId ;
    /** 用户唯一编码,; */
    private String userId ;
    /** 是否核销,; */
    private boolean isVerify ;
    /** 核销时间,; */
    private Timestamp verifyTime ;
    /** 领取时间,; */
    private Timestamp obtainedTime ;
    /** 消费券名称,; */
    private String voucherName ;
    /** 消费金额,; */
    private String voucherAmt ;
    /** 消费券介绍,; */
    private String voucherDes ;
}
