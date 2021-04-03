package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
<<<<<<< HEAD
import com.atguigu.jxc.entity.Unit;
=======
>>>>>>> upstream/dev
import com.atguigu.jxc.entity.vo.GoodsTypeVo;

import java.util.List;
import java.util.Map;

public interface GoodsService {


    ServiceVO getCode();


    Map<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId);



    Map<String, Object> list(Integer page, Integer rows, String goodsName, Integer goodsTypeId);

    List<GoodsTypeVo> queryAllGoodsType();
<<<<<<< HEAD

    List<Unit> queryUnitList();

=======
>>>>>>> upstream/dev
}
