package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
<<<<<<< HEAD
import com.atguigu.jxc.entity.Unit;
=======
>>>>>>> upstream/dev
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

<<<<<<< HEAD
    Integer deleteGoodsById(Integer goodsId);

    List<Goods> getNoInventoryQuantityByPage(@Param("page") Integer page, @Param("rows") Integer rows, @Param("nameOrCode") String nameOrCode);

    List<Goods> getHasInventoryQuantity(@Param("page") Integer page, @Param("rows") Integer rows, @Param("nameOrCode") String nameOrCode);
=======
    List<Goods> queryGoods(@Param("goodsName") String goodsName,@Param("goodsTypeId") Integer goodsTypeId);

    String queryGoodsTypes();


    List<GoodsType> queryAllGoodsTypeByPid(Integer goodsTypeId);
<<<<<<< HEAD

    List<Unit> queryUnitList();
=======
>>>>>>> upstream/dev
>>>>>>> upstream/dev
}
