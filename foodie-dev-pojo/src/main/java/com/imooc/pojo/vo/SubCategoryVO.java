package com.imooc.pojo.vo;

import lombok.Data;

/**
 * @author liujq
 * @create 2021-10-21 17:30
 */
@Data
public class SubCategoryVO {

    private Integer subId;
    private String subName;
    private String subType;
    private Integer subFatherId;

}
