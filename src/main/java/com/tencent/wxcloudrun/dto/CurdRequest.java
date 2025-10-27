package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class CurdRequest {
    private String merchantId;
    private String voucherId;
    private String userId;
    private int type;
    private String name;
}
