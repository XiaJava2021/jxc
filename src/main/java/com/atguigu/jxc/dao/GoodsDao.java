package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 商品信息
 */
public interface GoodsDao {


    String getMaxCode();


    List<Goods> queryStock(@Param("codeOrName") String codeOrName, @Param("goodsTypeId") Integer goodsTypeId);

    Integer deleteGoodsById(Integer goodsId);

    List<Goods> getNoInventoryQuantityByPage(@Param("page") Integer page, @Param("rows") Integer rows, @Param("nameOrCode") String nameOrCode);

    List<Goods> getHasInventoryQuantity(@Param("page") Integer page, @Param("rows") Integer rows, @Param("nameOrCode") String nameOrCode);
}
