package com.imooc.controller;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.pojo.vo.NewItemsVO;
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

import java.util.List;

/**
 * @author liujinqiang
 * @create 2021-10-25 23:09
 */
@Slf4j
@Api(value = "商品相关接口", tags = {"商品相关接口"})
@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "查询商品明细", notes = "查询商品明细", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public IMOOCJSONResult info(@ApiParam(name = "itemId", value = "商品id", required = true) @PathVariable String itemId) {
        if (StringUtils.isEmpty(itemId)) {
            return IMOOCJSONResult.errorMsg("商品id不能为空！");
        }
        Items items = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgs = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemsParamById(itemId);
        ItemInfoVO infoVO = new ItemInfoVO();
        infoVO.setItems(items);
        infoVO.setItemsImgList(itemsImgs);
        infoVO.setItemParam(itemsParam);
        infoVO.setItemSpecList(itemsSpecs);
        return IMOOCJSONResult.ok(infoVO);
    }

    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public IMOOCJSONResult commentLevel(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequestParam String itemId) {
        if (itemId == null) {
            return IMOOCJSONResult.errorMsg("分类id不能为空！");
        }
        CommentLevelCountsVO countsVO = categoryService.queryCommentLevel(itemId);
        return IMOOCJSONResult.ok(countsVO);
    }

    @ApiOperation(value = "查询商品评论", notes = "查询商品评论", httpMethod = "GET")
    @GetMapping("/commons")
    public IMOOCJSONResult commons(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level", value = "商品级别", required = false)
            @RequestParam Integer level,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false, defaultValue = "1")
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "页面显示数量", required = false, defaultValue = "10")
            @RequestParam Integer pageSize) {
        if (itemId == null) {
            return IMOOCJSONResult.errorMsg("分类id不能为空！");
        }
        PagedGridResult gridResult = itemService.queryPagedComments(itemId, level, page, pageSize);
        return IMOOCJSONResult.ok(gridResult);
    }

}
