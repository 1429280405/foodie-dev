package com.imooc.service;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;

import java.util.List;

/**
 * @author liujq
 * @create 2021-10-28 11:06
 */
public interface AddressService {
    List<UserAddress> queryAll(String userId);

    public void addNewUserAddress(AddressBO addressBO);

    void updateUserAddress(AddressBO addressBO);

    void deleteUserAddress(String addressId, String userId);

    void setDefaultUserAddress(String addressId, String userId);
}
