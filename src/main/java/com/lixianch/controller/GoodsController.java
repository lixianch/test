package com.lixianch.controller;

import com.lixianch.common.ResultModel;
import com.lixianch.dao.entity.Goods;
import com.lixianch.service.GoodsService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by lixianch on 2023/4/14
 */
@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @Resource
    private GoodsService goodsService;

    @PostMapping(value = "/add")
    public ResultModel add(@RequestBody Goods goods){
        goodsService.save(goods);
        return ResultModel.success();
    }
}
