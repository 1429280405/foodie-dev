package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author liujq
 * @create 2021-10-13 15:06
 */
@Data
@ApiModel(value = "用户对象BO",description = "客户端传入用户数据封装在此entity中")
public class UserBO {
    @ApiModelProperty(value = "用户名",name = "username",example = "imooc",required = true)
    private String username;
    @ApiModelProperty(value = "密码",name = "password",example = "123456",required = true)
    private String password;
    @ApiModelProperty(value = "确认密码",name = "confirmPassword",example = "123456",required = false)
    private String confirmPassword;
}
