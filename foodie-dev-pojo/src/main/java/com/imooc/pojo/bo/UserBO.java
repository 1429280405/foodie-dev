package com.imooc.pojo.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author liujq
 * @create 2021-10-13 15:06
 */
@Data
public class UserBO {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "密码校验不能为空")
    private String confirmPassword;
}
