package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.service.GoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description 商品信息Controller
 */
@RestController
@CrossOrigin
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询商品库存信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param codeOrName 商品编码或名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    @PostMapping("goods/listInventory")
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
     * @param goods 商品信息实体
     * @return
     */

    /**
     * 删除商品信息
     * @param goodsId 商品ID
     * @return
     */

    /**
     * 分页查询无库存商品信息
     * @Auth 吴健
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    @PostMapping("goods/getNoInventoryQuantity")
    public Map<String,Object> getNoInventoryQuantity(Integer page,Integer rows,String nameOrCode){
        Map<String,Object> noInventoryGoods=goodsService.getNoInventoryQuantity(page,rows,nameOrCode);
        return noInventoryGoods;
    }


    /**
     * 分页查询有库存商品信息
     * @Auth 吴健
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    @PostMapping("goods/getHasInventoryQuantity")
    public Map<String,Object> getHasInventoryQuantity(Integer page,Integer rows,String nameOrCode){
        Map<String,Object> noInventoryGoods=goodsService.getHasInventoryQuantity(page,rows,nameOrCode);
        return noInventoryGoods;
    }

    /**
     * 添加商品期初库存
     * @param goodsId 商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice 成本价
     * @return
     */

    /**
     * 删除商品库存
     * @Auth 吴健
     * @param goodsId 商品ID
     * @return
     */
    @PostMapping("goods/delete")
    public ServiceVO deleteGoodsById(Integer goodsId){
        ServiceVO serviceVO = goodsService.deleteGoodsById(goodsId);
        return serviceVO;
    }

    /**
     * 查询库存报警商品信息
     * @return
     */

}
