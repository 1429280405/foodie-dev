package com.imooc.pojo.vo;

import lombok.Data;

/**
 * @author liujq
 * @create 2021-10-26 10:04
 */
@Data
public class CommentLevelCountsVO {
    public Integer totalCounts;
    public Integer goodCounts;
    public Integer normalCounts;
    public Integer badCounts;

}
