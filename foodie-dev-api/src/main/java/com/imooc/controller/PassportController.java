package com.imooc.controller;

import com.google.gson.Gson;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.CookieUtil;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author liujq
 * @create 2021-10-13 9:41
 */
@Slf4j
@Api(value = "注册登录", tags = {"用户注册登录接口"})
@RestController
@RequestMapping("/passport")
public class PassportController extends BaseController {

    private static final Integer EXPIRE = 7200;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(String username) {
        if (StringUtils.isEmpty(username)) {
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        boolean exist = userService.queryUsernameIsExist(username);
        if (exist) {
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        //校验密码是否小于6位
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("密码长度不能小于6位！");
        }
        //校验两次密码是否一致
        if (!password.equals(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("两次密码不一致！");
        }
        //校验用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户已存在，注册失败！");
        }
        //入库
        Users users = userService.createUser(userBO);
        users = setNullProperties(users);
        setCookie(request, response, "user", JsonUtils.objectToJson(users), EXPIRE);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return IMOOCJSONResult.errorMsg("用户名密码不能为空!");
        }

        //用户登录
        Users users = userService.queryUsersForLogin(username, MD5Utils.toMD5(password));
        if (users == null) {
            return IMOOCJSONResult.errorMsg("用户名密码不正确！");
        }
        users = setNullProperties(users);
        setCookie(request, response, "user", JsonUtils.objectToJson(users), EXPIRE);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户退出", notes = "用户退出", httpMethod = "POST")
    @PostMapping("/logout")
    public IMOOCJSONResult logout(String userId, HttpServletRequest request, HttpServletResponse response) {
        deleteCookie(request, response, "user");
        return IMOOCJSONResult.ok();
    }


    public Users setNullProperties(Users users) {
        users.setPassword(null);
        users.setMobile(null);
        users.setEmail(null);
        users.setCreatedTime(null);
        users.setUpdatedTime(null);
        users.setBirthday(null);
        return users;
    }
}
