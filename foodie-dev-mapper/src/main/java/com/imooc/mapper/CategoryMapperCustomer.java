package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapperCustomer {

    public List<CategoryVO> getSubCatList(Integer rootCatId);
        
}