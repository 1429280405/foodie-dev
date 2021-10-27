package com.imooc.pojo.vo;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @author liujq
 * @create 2021-10-27 15:18
 */
@Data
@Entity
public class SearchItemsVO {
    private String itemid;
    private String itemName;
    private int sellCounts;
    private String imgUrl;
    private int price;
}
