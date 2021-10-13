package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;

/**
 * @author liujq
 * @create 2021-10-13 9:31
 */
public interface UserService {

    public boolean queryUsernameIsExist(String username);

    /**
     * 新建用户
     * @param userBO
     * @return
     */
    public Users createUser(UserBO userBO);









}
