package com.imooc.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author liujq
 * @create 2021-10-21 16:59
 * 二级分类VO
 */
@Data
public class CategoryVO {

    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;

    //三级分类vo list
    private List<SubCategoryVO> subCatList;

}
