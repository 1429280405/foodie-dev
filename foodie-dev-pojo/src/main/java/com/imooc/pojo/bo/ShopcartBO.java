package com.imooc.pojo.bo;

import lombok.Data;

/**
 * @author liujinqiang
 * @create 2021-10-27 19:58
 */
@Data
public class ShopcartBO {

    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private String buyCounts;
    private String priceDiscount;
    private String priceNormal;

}
