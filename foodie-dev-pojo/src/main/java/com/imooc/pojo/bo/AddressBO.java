package com.imooc.pojo.bo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author liujq
 * @create 2021-10-28 11:32
 */
@Data
public class AddressBO {
    private String addressId;
    @NotBlank(message = "用户id不能为空")
    @Length(max = 20, message = "用户名不能太长")
    private String userId;
    private String receiver;
    @NotBlank(message = "手机号必填")
    @Length(min = 11, max = 11, message = "手机号长度得为11位")
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detail;
}
