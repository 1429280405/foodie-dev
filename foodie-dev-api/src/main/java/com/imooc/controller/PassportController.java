package com.imooc.controller;

import com.imooc.service.UserService;
import com.imooc.utils.IMOOCJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liujq
 * @create 2021-10-13 9:41
 */
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private UserService userService;

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
}
