package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
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


    List<DamageListGoods> queryDamageListGoods(Integer damageListId);

    List<OverflowList> queryOverflowList(@Param("sTime") String sTime, @Param("eTime") String eTime);

    List<OverflowListGoods> queryOverflowListGoods(Integer overflowListId);

    List<Goods> queryGoods(@Param("goodsName") String goodsName,@Param("goodsTypeId") Integer goodsTypeId);

    String queryGoodsTypes();


    List<GoodsType> queryAllGoodsTypeByPid(Integer goodsTypeId);

    List<Unit> queryUnitList();

    Integer updateGoods(@Param("goods") Goods goods);

    Integer saveGoods(@Param("goods") Goods goods);

    List<DamageList> queryDamageListGoodsByTime(String sTime, String eTime);

    List<Goods> queryListAlarm();

    void saveOverflowList(OverflowList overflowList);

    void saveOverflowListGoods(OverflowListGoods overflowListGoods);
}
