package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.Unit;
import com.atguigu.jxc.entity.vo.GoodsTypeVo;
import com.atguigu.jxc.service.GoodsService;
import com.google.gson.Gson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 商品信息Controller
 */
@Controller
//@CrossOrigin
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    /**
     * 查询商品所有分类
     * @return
     */
    @ResponseBody
    @PostMapping("goodsType/loadGoodsType")
    public String queryAllGoodsType(){
        List<GoodsTypeVo> list = goodsService.queryAllGoodsType();

        Gson gson = new Gson();
        String goodsTypes = gson.toJson(list);
        return goodsTypes;
    }

    /**
     * 查询所有商品单位
     * @return
     */
    @PostMapping("unit/list")
    @ResponseBody
    public Map<String,Object> queryUnitList(){

        List<Unit> list = goodsService.queryUnitList();
        HashMap<String, Object> unitMap = new HashMap<>();
        unitMap.put("rows",list);
        return unitMap;
    }
//    @PostMapping("unit/list")
//    public Map<String,Object> unitList(){
//        return goodsService.unitList();
//    }



    /**
     * 分页查询商品库存信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param codeOrName 商品编码或名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    @PostMapping("goods/listInventory")
    @ResponseBody
    public Map<String,Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId){
        return goodsService.listInventory(page,rows,codeOrName,goodsTypeId);
    }

    /**
     * 分页查询商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param goodsName 商品名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    @ResponseBody
    @PostMapping("goods/list")
    public Map<String,Object> list(Integer page, Integer rows, String goodsName, Integer goodsTypeId){
        return goodsService.list(page,rows,goodsName,goodsTypeId);
    }



    /**
     * 生成商品编码
     * @return
     */
    @RequestMapping("/getCode")
    @RequiresPermissions(value = "商品管理")
    public ServiceVO getCode() {
        return goodsService.getCode();
    }

    /**
     * 添加或修改商品信息
     *
     * @param goods 商品信息实体
     * @return
     */
    @ResponseBody
    @PostMapping("goods/save")
    public ServiceVO saveOrUpdateGoods( Goods goods, Integer goodsId) {
        goodsService.saveOrUpdateGoods(goods, goodsId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }
//
//    @ResponseBody
//    @PostMapping("goods/save")
//    public void saveOrUpdateGoods(Integer goodsId) {
//
//        System.out.println(goodsId);
//
//    }


    /**
     * 删除商品信息
     * @param goodsId 商品ID
     * @return
     */

    /**
     * 分页查询无库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */


    /**
     * 分页查询有库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */


    /**
     * 添加商品期初库存
     * @param goodsId 商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice 成本价
     * @return
     */
    @PostMapping("goods/saveStock")
    public ServiceVO saveStock(@RequestParam("goodsId") Integer goodsId,Integer inventoryQuantity, Double purchasingPrice){
        if(goodsId == null || inventoryQuantity == null || purchasingPrice == null){
            return new ServiceVO(ErrorCode.NULL_POINTER_CODE,ErrorCode.NULL_POINTER_MESS);
        }
        if(inventoryQuantity < -1 || purchasingPrice < 0){
            return new ServiceVO(ErrorCode.PARA_TYPE_ERROR_CODE,ErrorCode.PARA_TYPE_ERROR_MESS);
        }
        Integer count = 0;
        try {
            count = this.goodsService.saveStock(goodsId,inventoryQuantity,purchasingPrice);
        } catch (Exception e) {
            // 修改库存出错
        }
        if(count == 1){
            return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        }
        return new ServiceVO(ErrorCode.NO_EXIST_GOODS_CODE,ErrorCode.NO_EXIST_GOODS_MESS);
    }

    /**
     * 删除商品库存
     * @param goodsId 商品ID
     * @return
     */
    @PostMapping("goods/deleteStock")
    public ServiceVO deleteStock(Integer goodsId){
        if(goodsId == null){
            return new ServiceVO(ErrorCode.NULL_POINTER_CODE,ErrorCode.NULL_POINTER_MESS);
        }
        Integer state = -1;
        try {
            state = this.goodsService.deleteStock(goodsId);
        } catch (Exception e) {
            // 删除库存出错
        }
        switch (state){
            case 0: return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
            case 1: return new ServiceVO(ErrorCode.STORED_ERROR_CODE,ErrorCode.STORED_ERROR_MESS);
            case 2: return new ServiceVO(ErrorCode.HAS_FORM_ERROR_CODE,ErrorCode.HAS_FORM_ERROR_MESS);
            default: return new ServiceVO(ErrorCode.NO_EXIST_GOODS_CODE,ErrorCode.NO_EXIST_GOODS_MESS);
        }
    }

    /**
     * 查询库存报警商品信息
     * @return
     */

}
