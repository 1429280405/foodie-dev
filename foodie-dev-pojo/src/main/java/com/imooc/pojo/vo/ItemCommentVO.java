package com.imooc.pojo.vo;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author liujinqiang
 * @create 2021-10-26 21:59
 */
@Data
@Entity
public class ItemCommentVO {

    private Integer commentLevel;
    private String content;
    private String sepcName;
    private Date createdTime;
    private String userFace;
    private String nickname;

}
