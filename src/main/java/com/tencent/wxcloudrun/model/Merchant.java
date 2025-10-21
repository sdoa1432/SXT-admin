package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Merchant implements Serializable,Cloneable{
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
    /** 商户角色,; */
    private int merchantRole ;
    /** 商户登录密码(MD5加密后),; */
    private String merchantPassword ;
    /** 商户登录账户,; */
    private String merchantAccount ;
    /** 登录凭据,; */
    private String loginToken ;
}
