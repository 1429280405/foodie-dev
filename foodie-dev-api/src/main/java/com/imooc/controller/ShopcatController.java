package com.imooc.controller;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.bo.ShopcartBO;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.service.CategoryService;
import com.imooc.service.ItemService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author liujinqiang
 * @create 2021-10-25 23:09
 */
@Slf4j
@Api(value = "购物车接口controller", tags = {"购物车接口api"})
@RestController
@RequestMapping("/shopcart")
public class ShopcatController {


    @ApiOperation(value = "添加商品至购物车", notes = "添加商品至购物车", httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "shopcartBO", value = "购物车对象", required = true)
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response) {
        if (userId == null) {
            return IMOOCJSONResult.errorMsg("");
        }
        //TODO 需要添加商品至购物车
        return IMOOCJSONResult.ok();
    }


    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public IMOOCJSONResult del(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "itemSpecId", value = "商品规格id", required = true)
            @RequestBody String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(itemSpecId)) {
            return IMOOCJSONResult.errorMsg("");
        }
        //TODO 用户删除购物车商品数据
        return IMOOCJSONResult.ok();
    }
}
