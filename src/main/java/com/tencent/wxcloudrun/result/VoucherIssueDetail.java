package com.tencent.wxcloudrun.result;

import lombok.Data;

@Data
public class VoucherIssueDetail {

    private String voucherName;
    private int issueNum;
    private int verifyNum;
}
