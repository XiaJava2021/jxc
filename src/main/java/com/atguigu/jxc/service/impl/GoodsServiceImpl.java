package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public ServiceVO getCode() {

        // 获取当前商品最大编码
        String code = goodsDao.getMaxCode();

        // 在现有编码上加1
        Integer intCode = Integer.parseInt(code) + 1;

        // 将编码重新格式化为4位数字符串形式
        String unitCode = intCode.toString();

        for (int i = 4; i > intCode.toString().length(); i--) {

            unitCode = "0" + unitCode;

        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, unitCode);
    }

    @Override
    public Map<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        PageHelper.startPage(page, rows);
        List<Goods> list = goodsDao.queryStock(codeOrName, goodsTypeId);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(list);
        long total = pageInfo.getTotal();
        List<Goods> goods = pageInfo.getList();
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("rows", goods);
        return result;
    }

    @Override
    public Map<String, Object> queryDamageListGoods(Integer damageListId) {
        HashMap<String, Object> map = new HashMap<>();
        List<DamageListGoods> damageListGoodsList = this.goodsDao.queryDamageListGoods(damageListId);
        map.put("rows", damageListGoodsList);
        return map;
    }

    @Override
    public Map<String, Object> queryOverflowList(String sTime, String eTime) {
        HashMap<String, Object> map = new HashMap<>();
        List<OverflowList> overflowLists = this.goodsDao.queryOverflowList(sTime, eTime);
        map.put("rows", overflowLists);
        return map;
    }

    @Override
    public Map<String, Object> queryOverflowListGoods(Integer overflowListId) {
        HashMap<String, Object> map = new HashMap<>();
        List<OverflowListGoods> overflowListGoods = this.goodsDao.queryOverflowListGoods(overflowListId);
        map.put("rows", overflowListGoods);
        return map;
    }
}
