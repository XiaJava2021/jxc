package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.DamageListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsService {


    ServiceVO getCode();


    Map<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId);

    Map<String, Object> queryDamageListGoods(Integer damageListId);

    Map<String, Object> queryOverflowList(String sTime, String eTime);

    Map<String, Object> queryOverflowListGoods(Integer overflowListId);
}
