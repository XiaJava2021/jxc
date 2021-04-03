package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.vo.GoodsTypeVo;

import java.util.List;
import java.util.Map;

public interface GoodsService {


    ServiceVO getCode();


    Map<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId);


    Map<String, Object> unitList();

    Map<String, Object> list(Integer page, Integer rows, String goodsName, Integer goodsTypeId);

    List<GoodsTypeVo> queryAllGoodsType();
}
