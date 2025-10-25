package com.tencent.wxcloudrun.result;

import lombok.Data;

@Data
public class VoucherIssueDetail {

    private String voucherName;
    /** 消费金额,; */
    private String voucherAmt ;
    /** 消费券介绍,; */
    private String voucherDes ;
    private int issueNum;
    private int verifyNum;
}
