package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageListGoods;

import java.util.ArrayList;

/**
 *
 */
public interface DamageListGoodsDao {
    Integer batchSave(ArrayList<DamageListGoods> damageListGoodsArrayList);
}
