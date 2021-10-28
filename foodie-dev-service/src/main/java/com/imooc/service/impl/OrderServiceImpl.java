package com.imooc.service.impl;

import com.imooc.enums.YesOrNo;
import com.imooc.mapper.OrderItemsMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.pojo.*;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.service.AddressService;
import com.imooc.service.ItemService;
import com.imooc.service.OrderService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liujinqiang
 * @create 2021-10-28 20:34
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AddressService addressService;

    @Autowired
    private Sid sid;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public void createOrder(SubmitOrderBO submitOrderBO) {
        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        String payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();
        //包邮设置油费为0
        int postAmount = 0;
        String orderId = sid.nextShort();
        UserAddress address = addressService.queryUserAddress(userId, addressId);

        //新订单保存数据
        Orders newOrder = new Orders();
        newOrder.setId(orderId);
        newOrder.setUserId(userId);
        newOrder.setReceiverName(address.getReceiver());
        newOrder.setReceiverMobile(address.getMobile());
        newOrder.setReceiverAddress(address.getProvince() + " " +
                address.getCity() + " " +
                address.getDistrict() + " " +
                address.getDetail());
        newOrder.setPostAmount(postAmount);
        newOrder.setLeftMsg(leftMsg);
        newOrder.setIsComment(YesOrNo.NO.type);
        newOrder.setIsDelete(YesOrNo.NO.type);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());


        //循环根据itemSpecIds保存订单商品信息表
        String[] itemSpecIdArr = itemSpecIds.split(",");
        Integer totalAmount = 0;//商品原价累计
        Integer realPayAmount = 0;//优惠后实际价格累计
        for (String itemSpecId : itemSpecIdArr) {
            //TODO 整合redis后，商品购买数量从redis中获取
            int buyCounts = 1;
            //根据规格id，获取规格具体信息
            ItemsSpec itemsSpec = itemService.queryItemsSpecById(itemSpecId);
            totalAmount += itemsSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemsSpec.getPriceDiscount() * buyCounts;

            //根据商品id，获取商品信息及图片
            String itemId = itemsSpec.getItemId();
            Items items = itemService.queryItemById(itemId);
            String imgUrl = itemService.queryItemMainImgById(itemId);

            //循环保存子订单到数据库
            String subOrderId = sid.nextShort();
            OrderItems orderItems = new OrderItems();
            orderItems.setId(subOrderId);
            orderItems.setOrderId(orderId);
            orderItems.setItemId(itemId);
            orderItems.setItemName(items.getItemName());
            orderItems.setItemImg(imgUrl);
            orderItems.setBuyCounts(buyCounts);
            orderItems.setItemSpecId(itemSpecId);
            orderItems.setItemSpecName(itemsSpec.getName());
            orderItems.setPrice(itemsSpec.getPriceDiscount());
            orderItemsMapper.insert(orderItems);
        }

        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);
        ordersMapper.insert(newOrder);

    }
}
