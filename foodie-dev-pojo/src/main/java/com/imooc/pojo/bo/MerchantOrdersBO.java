package com.imooc.pojo.bo;

import lombok.Data;

/**
 * @author liujinqiang
 * @create 2021-11-01 20:12
 */
@Data
public class MerchantOrdersBO {
    private String merchantOrderId;
    private String merchantUserId;
    private Integer amount;
    private Integer payMethod;
    private Integer returnUrl;
}
