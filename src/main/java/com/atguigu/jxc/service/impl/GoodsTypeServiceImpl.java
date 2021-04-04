package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Unit;
import com.atguigu.jxc.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date:2021/4/3 -15:07
 * @Auther:浮生
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeDao goodsTypeDao;


    @Override
    public void saveType(String goodsTypeName, Integer pId) {

        GoodsType goodsType = new GoodsType(goodsTypeName, 0, pId);

        goodsTypeDao.saveType(goodsType);
    }

    @Override
    public void deleteType(Integer goodsTypeId) {
        goodsTypeDao.delete(goodsTypeId);
    }




}
