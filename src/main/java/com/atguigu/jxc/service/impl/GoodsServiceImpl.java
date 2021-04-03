package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.vo.GoodsTypeVo;
import com.atguigu.jxc.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
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
    public Map<String, Object> unitList() {
        Map<String, Object> map = goodsDao.queryUnitList();
        return map;
    }

    @Override
    public Map<String, Object> list(Integer page, Integer rows, String goodsName, Integer goodsTypeId) {
        PageHelper.startPage(page,rows);
        List<Goods> list = goodsDao.queryGoods(goodsName,goodsTypeId);
        PageInfo<Goods> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        List<Goods> goods = pageInfo.getList();
        Map<String, Object> result = new HashMap<>();
        result.put("total",total);
        result.put("rows",goods);

        return result;

    }

    @Override
    public List<GoodsTypeVo> queryAllGoodsType() {


        //方法3.1
        List<GoodsType> goodsTypes = goodsDao.queryAllGoodsTypeByPid(-1);

        List<GoodsTypeVo> collect = getGoodsTypeVos(goodsTypes);

        return collect;
    }


    private List<GoodsTypeVo> getGoodsTypeVos(List<GoodsType> goodsTypes) {
        return goodsTypes.stream().map(goodsType -> {
            GoodsTypeVo vo = new GoodsTypeVo();
            vo.setId(goodsType.getGoodsTypeId());
            vo.setText(goodsType.getGoodsTypeName());
            switch (goodsType.getGoodsTypeState()) {
                case 1:
                    vo.setState("closed");
                default:
                    vo.setState("open");
            }

            vo.setIconCls("goods-type");
            HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
            stringIntegerHashMap.put("state", goodsType.getGoodsTypeState());
            vo.setAttributes(stringIntegerHashMap);

            if (goodsType.getGoodsTypeId() != null) {
                List<GoodsType> goodsTypes1 = goodsDao.queryAllGoodsTypeByPid(goodsType.getGoodsTypeId());

                List<GoodsTypeVo> goodsTypeVos = this.getGoodsTypeVos(goodsTypes1);
                vo.setChildren(goodsTypeVos);
            }
            return vo;
        }).collect(Collectors.toList());
    }



}
