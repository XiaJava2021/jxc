package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Unit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 商品类别
 */
public interface GoodsTypeDao {



    Integer updateGoodsTypeState(GoodsType parentGoodsType);


    void saveType(@Param("goodsType") GoodsType goodsType);

    void delete(Integer goodsTypeId);




}
