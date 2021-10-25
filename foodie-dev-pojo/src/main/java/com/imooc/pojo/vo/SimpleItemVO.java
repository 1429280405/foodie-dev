package com.imooc.pojo.vo;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @author liujq
 * @create 2021-10-25 15:13
 */
@Entity
@Data
public class SimpleItemVO {
    private Integer itemId;
    private String itemName;
    private String itemUrl;
}
