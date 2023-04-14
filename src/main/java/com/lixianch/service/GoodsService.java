package com.lixianch.service;

import com.lixianch.dao.entity.Goods;
import com.lixianch.dao.mapper.GoodsMapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * created by lixianch on 2023/4/14
 */
@Service
public class GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    public void save(Goods goods){
        goodsMapper.save(goods);
    }
}
