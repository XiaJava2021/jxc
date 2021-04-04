package com.atguigu.jxc.service;

import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date:2021/4/3 -14:54
 * @Auther:浮生
 */

public interface GoodsTypeService {


    void saveType(String goodsTypeName, Integer pId);

    void deleteType(Integer goodsTypeId);


}
