package com.imooc.controller;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.pojo.bo.ShopcartBO;
import com.imooc.service.AddressService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author liujinqiang
 * @create 2021-10-25 23:09
 */
@Slf4j
@Api(value = "收货地址controller", tags = {"收货地址api"})
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @ApiOperation(value = "查询用户收货地址列表", notes = "查询用户收货地址列表", httpMethod = "POST")
    @PostMapping("/list")
    public IMOOCJSONResult list(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) {
        if (userId == null) {
            return IMOOCJSONResult.errorMsg("");
        }
        List<UserAddress> addressList = addressService.queryAll(userId);
        return IMOOCJSONResult.ok(addressList);
    }


    @ApiOperation(value = "用户新增收货地址", notes = "用户新增收货地址", httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(
            @ApiParam(name = "addressBO", value = "收货地址对象")
            @RequestBody @Valid AddressBO addressBO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return IMOOCJSONResult.errorMap(bindingResult);
        }
        addressService.addNewUserAddress(addressBO);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "修改用户收货地址", notes = "修改用户收货地址", httpMethod = "POST")
    @PostMapping("/update")
    public IMOOCJSONResult update(
            @ApiParam(name = "addressBO", value = "收货地址对象")
            @RequestBody @Valid AddressBO addressBO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return IMOOCJSONResult.errorMap(bindingResult);
        }
        addressService.updateUserAddress(addressBO);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "删除用户收货地址", notes = "删除用户收货地址", httpMethod = "POST")
    @PostMapping("/delete")
    public IMOOCJSONResult delete(
            @ApiParam(name = "addressId", value = "地址记录id")
            @RequestParam("addressId") String addressId,
            @ApiParam(name = "userId", value = "用户id")
            @RequestParam("userId") String userId) {
        if (StringUtils.isEmpty(addressId) || StringUtils.isEmpty(userId)) {
            return IMOOCJSONResult.errorMsg("");
        }
        addressService.deleteUserAddress(addressId,userId);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "设置默认用户收货地址", notes = "设置默认用户收货地址", httpMethod = "POST")
    @PostMapping("/setDefault")
    public IMOOCJSONResult setDefault(
            @ApiParam(name = "addressId", value = "地址记录id")
            @RequestParam("addressId") String addressId,
            @ApiParam(name = "userId", value = "用户id")
            @RequestParam("userId") String userId) {
        if (StringUtils.isEmpty(addressId) || StringUtils.isEmpty(userId)) {
            return IMOOCJSONResult.errorMsg("");
        }
        addressService.setDefaultUserAddress(addressId,userId);
        return IMOOCJSONResult.ok();
    }

}
