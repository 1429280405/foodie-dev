package com.imooc.pojo.vo;

import lombok.Data;

/**
 * @author liujinqiang
 * @create 2021-10-28 20:12
 */
@Data
public class OrderVO {

    private String orderId;
    private String merchantOrderId;
    private String merchantUserId;
    private Integer amount;
    private Integer payMethod;
    private Integer returnUrl;
}
