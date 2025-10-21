package com.tencent.wxcloudrun.result;

import com.tencent.wxcloudrun.model.Merchant;
import com.tencent.wxcloudrun.model.MerchantVoucherManager;
import lombok.Data;

import java.util.List;

@Data
public class MerchantVoucherResult {

    private Merchant merchant;

    private List<MerchantVoucherManager> voucherList;

}
