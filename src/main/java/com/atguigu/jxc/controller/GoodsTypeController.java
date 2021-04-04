package com.atguigu.jxc.controller;

import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Unit;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.GoodsTypeService;
import com.google.gson.Gson;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date:2021/4/3 -11:53
 * @Auther:浮生
 */
@Controller
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService saveType;

    @PostMapping("goodsType/save")
    @ResponseBody
    public ServiceVO<GoodsType> saveType(String goodsTypeName, Integer pId) {

        saveType.saveType(goodsTypeName, pId);
        ServiceVO<GoodsType> goodsTypeServiceVO = new ServiceVO<GoodsType>(100, "请求成功", null);
        return goodsTypeServiceVO;
    }

    @PostMapping("goodsType/delete")
    @ResponseBody
    public ServiceVO<GoodsType> deleteType(Integer goodsTypeId) {

        saveType.deleteType(goodsTypeId);

        return new ServiceVO<GoodsType>(100, "请求成功", null);
    }


    @Autowired
    GoodsService goodsService;







}
