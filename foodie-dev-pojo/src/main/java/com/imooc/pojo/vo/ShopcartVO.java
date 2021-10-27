package com.imooc.pojo.vo;

import lombok.Data;

/**
 * @author liujinqiang
 * @create 2021-10-27 19:58
 */
@Data
public class ShopcartVO {

    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private String priceDiscount;
    private String priceNormal;

}
