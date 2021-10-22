package com.imooc.pojo.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author liujq
 * @create 2021-10-21 17:30
 */
@Entity
@Data
public class SubCategoryVO {

    @Id
    private Integer subId;
    private String subName;
    private String subType;
    private Integer subFatherId;

}
