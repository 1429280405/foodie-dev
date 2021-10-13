package com.imooc.pojo.bo;

import lombok.Data;

/**
 * @author liujq
 * @create 2021-10-13 15:06
 */
@Data
public class UserBO {
    private String username;
    private String password;
    private String confirmPassword;
}
