package com.imooc.pojo.bo;

import lombok.Data;

/**
 * @author liujinqiang
 * @create 2021-10-28 20:12
 */
@Data
public class SubmitOrderBO {

    private String userId;
    private String itemSpecIds;
    private String addressId;
    private String payMethod;
    private String leftMsg;
}
