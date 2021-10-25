package com.imooc.pojo.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * @author liujq
 * @create 2021-10-21 16:59
 * 查询最新商品
 */
@Entity
@Data
public class NewItemsVO {
    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bg_color;
    private List<SimpleItemVO> simpleItemVOList;

}
