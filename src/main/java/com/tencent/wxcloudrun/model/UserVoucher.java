package com.tencent.wxcloudrun.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

/**
 * 用户消费券领取表;数据表的PO对象
 * @author : http://www.yonsum.com
 * @date : 2025-10-21
 */
@Data
public class UserVoucher implements Serializable,Cloneable{
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
}
