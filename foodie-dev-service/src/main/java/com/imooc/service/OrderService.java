package com.imooc.service;

import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.OrderVO;

/**
 * @author liujinqiang
 * @create 2021-10-28 20:33
 */
public interface OrderService {

    public OrderVO createOrder(SubmitOrderBO submitOrderBO);

    public void updateOrderStatus(String orderId, Integer orderStatus);

}
