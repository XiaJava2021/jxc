package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.Goods;

import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;

import com.atguigu.jxc.entity.GoodsType;

import com.atguigu.jxc.entity.Unit;


import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description 商品信息
 */
public interface GoodsDao {


    String getMaxCode();


    List<Goods> queryStock(@Param("codeOrName") String codeOrName, @Param("goodsTypeId") Integer goodsTypeId);


    List<DamageListGoods> queryDamageListGoods(Integer damageListId);

    List<OverflowList> queryOverflowList(@Param("sTime") String sTime, @Param("eTime") String eTime);

    List<OverflowListGoods> queryOverflowListGoods(Integer overflowListId);

    List<Goods> queryGoods(@Param("goodsName") String goodsName,@Param("goodsTypeId") Integer goodsTypeId);

    String queryGoodsTypes();


    List<GoodsType> queryAllGoodsTypeByPid(Integer goodsTypeId);


    List<Unit> queryUnitList();

}
