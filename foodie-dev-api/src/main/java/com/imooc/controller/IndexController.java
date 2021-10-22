package com.imooc.controller;

import com.imooc.enums.YesOrNo;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.service.UserService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
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
 * @author liujq
 * @create 2021-10-13 9:41
 */
@Slf4j
@Api(value = "首页相关接口", tags = {"首页相关接口"})
@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "查询轮播图列表", notes = "查询轮播图列表", httpMethod = "GET")
    @GetMapping("/carousel")
    public IMOOCJSONResult carousel() {
        List<Carousel> carouselList = carouselService.queryAll(YesOrNo.YES.type);
        return IMOOCJSONResult.ok(carouselList);
    }

    @ApiOperation(value = "商品分类（一级分类）", notes = "商品分类（一级分类）", httpMethod = "GET")
    @GetMapping("/cats")
    public IMOOCJSONResult cats() {
        List<Category> categories = categoryService.queryAllRootCat();
        return IMOOCJSONResult.ok(categories);
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public IMOOCJSONResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类id不能为空！");
        }
        List<CategoryVO> catList = categoryService.getSubCatList(rootCatId);
        return IMOOCJSONResult.ok(catList);
    }
}
