package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.DamageListDao;
import com.atguigu.jxc.dao.DamageListGoodsDao;
import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.DamageListService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 *
 */
@Service
public class DamageListServiceImpl implements DamageListService {

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private DamageListDao damageListDao;

    @Resource
    private DamageListGoodsDao damageListGoodsDao;

    @Override
    @Transactional
    public Integer save(DamageList damageList, String damageListGoodsStr) {
        // 保存报损单
        Integer count = this.damageListDao.save(damageList);
        // 解析报损商品
        Gson gson = new Gson();
        ArrayList<DamageListGoods> damageListGoodsArrayList = gson.fromJson(damageListGoodsStr,new TypeToken<ArrayList<DamageListGoods>>(){}.getType());
        if(damageListGoodsArrayList == null && CollectionUtils.isEmpty(damageListGoodsArrayList)){
            throw new RuntimeException("没有报损商品数据");
        }
        // 保存报损商品
        damageListGoodsArrayList.forEach(damageListGoods->{
            Goods goods = this.goodsDao.queryGoodsById(damageListGoods.getGoodsId());
            if(goods!=null){
//                damageListGoods.setGoodsCode(goods.getGoodsCode());
//                damageListGoods.setGoodsName(goods.getGoodsName());
//                damageListGoods.setGoodsModel(goods.getGoodsModel());
//                damageListGoods.setGoodsUnit(goods.getGoodsUnit());
//                damageListGoods.setGoodsTypeId(goods.getGoodsTypeId());
                BeanUtils.copyProperties(goods,damageListGoods);
                damageListGoods.setDamageListId(damageList.getDamageListId());
            }
        });
        this.damageListGoodsDao.batchSave(damageListGoodsArrayList);
        return count;
    }
}
