package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        for(int i = 4;i > intCode.toString().length();i--){

            unitCode = "0"+unitCode;

        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, unitCode);
    }

    @Override
    public Map<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        PageHelper.startPage(page,rows);
        List<Goods> list = goodsDao.queryStock(codeOrName,goodsTypeId);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(list);
        long total = pageInfo.getTotal();
        List<Goods> goods = pageInfo.getList();
        Map<String, Object> result = new HashMap<>();
        result.put("total",total);
        result.put("rows",goods);
        return result;
    }

    @Override
    public ServiceVO deleteGoodsById(Integer goodsId) {

        Integer rows = goodsDao.deleteGoodsById(goodsId);
        if (rows==1){
            return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        }else{
            return new ServiceVO(-1,"商品有入库、有进货和销售单据");
        }
    }

    @Override
    public Map<String, Object> getNoInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        Map<String, Object> pageMap=new HashMap<>();

        PageHelper.startPage(page,rows);
        List<Goods>goods=goodsDao.getNoInventoryQuantityByPage((page-1)*rows,rows,nameOrCode);
        PageInfo pageInfo=new PageInfo(goods);
        pageMap.put("total",pageInfo.getTotal());
        pageMap.put("rows",pageInfo.getList());
        return pageMap;
    }

    @Override
    public Map<String, Object> getHasInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        Map<String, Object> pageMap=new HashMap<>();

        PageHelper.startPage(page,rows);
        List<Goods>goods=goodsDao.getHasInventoryQuantity((page-1)*rows,rows,nameOrCode);
        PageInfo pageInfo=new PageInfo(goods);
        pageMap.put("total",pageInfo.getTotal());
        pageMap.put("rows",pageInfo.getList());
        return pageMap;
    }


}
