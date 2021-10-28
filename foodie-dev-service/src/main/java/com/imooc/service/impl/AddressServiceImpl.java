package com.imooc.service.impl;

import com.imooc.mapper.UserAddressMapper;
import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.service.AddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author liujq
 * @create 2021-10-28 11:07
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryAll(String userId) {
        UserAddress ua = new UserAddress();
        ua.setUserId(userId);
        return userAddressMapper.select(ua);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewUserAddress(AddressBO addressBO) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, userAddress);
        int isDefault = 0;
        List<UserAddress> addressList = queryAll(addressBO.getUserId());
        if (CollectionUtils.isEmpty(addressList)) {
            isDefault = 1;
        }
        userAddress.setId(sid.nextShort());
        userAddress.setIsDefault(isDefault);
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        userAddressMapper.insert(userAddress);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserAddress(AddressBO addressBO) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, userAddress);
        userAddress.setId(addressBO.getAddressId());
        userAddress.setUpdatedTime(new Date());
        userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUserAddress(String addressId, String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setId(addressId);
        userAddressMapper.delete(userAddress);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void setDefaultUserAddress(String addressId, String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setIsDefault(1);
        List<UserAddress> list = userAddressMapper.select(userAddress);
        for (UserAddress ua : list) {
            ua.setIsDefault(0);
            userAddressMapper.updateByPrimaryKeySelective(ua);
        }
        userAddress.setId(addressId);
        userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }


}
