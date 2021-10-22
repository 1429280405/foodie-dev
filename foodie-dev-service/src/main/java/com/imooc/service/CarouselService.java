package com.imooc.service;

import com.imooc.pojo.Carousel;

import java.util.List;

/**
 * @author liujq
 * @create 2021-10-21 11:47
 */
public interface CarouselService {

    public List<Carousel> queryAll(Integer isShow);

}
