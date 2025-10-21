package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class ClaimVoucherRequest {

    private String userId;
    private String voucherId;
}
