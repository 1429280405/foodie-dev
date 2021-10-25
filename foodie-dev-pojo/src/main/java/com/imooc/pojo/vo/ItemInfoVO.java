package com.imooc.pojo.vo;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import lombok.Data;

import java.util.List;

/**
 * @author liujinqiang
 * @create 2021-10-25 23:08
 */
@Data
public class ItemInfoVO {
    private Items items;
    private List<ItemsImg> itemsImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParam;
}
