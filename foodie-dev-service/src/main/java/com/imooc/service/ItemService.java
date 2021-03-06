package com.imooc.service;

import com.imooc.pojo.*;
import com.imooc.pojo.bo.UserBO;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.ShopcartVO;
import com.imooc.utils.PagedGridResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liujq
 * @create 2021-10-13 9:31
 */
public interface ItemService {

    public Items queryItemById(String itemId);

    public List<ItemsImg> queryItemImgList(String itemId);

    public List<ItemsSpec> queryItemSpecList(String itemId);

    public ItemsParam queryItemsParamById(String itemId);

    public PagedGridResult queryPagedComments(String itemid, Integer level, Integer page, Integer pageSize);

    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    public List<ShopcartVO> queryItemsBySpecIds(String specIds);

    ItemsSpec queryItemsSpecById(String itemSpecId);

    String queryItemMainImgById(String itemId);

    public void decreaseItemSpecStock(int pendingCounts, String specId);

}
