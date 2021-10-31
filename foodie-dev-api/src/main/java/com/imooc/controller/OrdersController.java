package com.imooc.controller;

import com.imooc.pojo.bo.ShopcartBO;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.service.OrderService;
import com.imooc.utils.CookieUtil;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liujinqiang
 * @create 2021-10-25 23:09
 */
@Slf4j
@Api(value = "订单接口controller", tags = {"订单接口api"})
@RestController
@RequestMapping("/orders")
public class OrdersController extends BaseController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "创建订单", notes = "创建订单", httpMethod = "POST")
    @PostMapping("/create")
    public IMOOCJSONResult create(
            @ApiParam(name = "submitOrderBO", value = "订单实体")
            @RequestBody SubmitOrderBO submitOrderBO,
            HttpServletRequest request,
            HttpServletResponse response) {
        if (submitOrderBO == null) {
            return IMOOCJSONResult.errorMsg("");
        }
        String orderId = orderService.createOrder(submitOrderBO);
        //TODO 整合redis后，完善购物车中的已结算商品清除，并且同步到前端的cookie
        CookieUtil.set(response, FOODIE_SHOPCART, "", 0);
        return IMOOCJSONResult.ok(orderId);
    }


}
