package com.tencent.wxcloudrun.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class LoginResult {
    /** 主键 */
    private String id ;
    /** 商户名称,; */
    private String merchantName ;
    /** 商户类型,; */
    private int merchantType ;
    /** 商户地址,; */
    private String merchantAddress ;
    /** 商户简介,; */
    private String merchantDes ;
    /** 商户电话,; */
    private String merchantPhone ;
    /** 商户联系人,; */
    private String merchantManager ;

    private String loginToken;

}
