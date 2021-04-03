package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.service.GoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
     *
     * @param page        当前页
     * @param rows        每页显示条数
     * @param codeOrName  商品编码或名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    @PostMapping("goods/listInventory")
    public Map<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        return goodsService.listInventory(page, rows, codeOrName, goodsTypeId);
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
     *
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

    /**
     * 删除商品库存
     * @param goodsId 商品ID
     * @return
     */

    /**
     * 查询库存报警商品信息
     * @return
     */


    /**
     * 查询报损单商品信息
     *
     * @param damageListId 报损单Id
     * @return
     */
    @PostMapping("damageListGoods/goodsList")
    public Map<String, Object> queryDamageListGoods(Integer damageListId) {
        return goodsService.queryDamageListGoods(damageListId);
    }

    /**
     * 报溢单查询
     *
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @PostMapping("overflowListGoods/list")
    public Map<String, Object> queryOverflowList(String sTime, String eTime) {
        return this.goodsService.queryOverflowList(sTime, eTime);
    }

    @PostMapping("overflowListGoods/goodsList")
    public Map<String, Object> queryOverflowListGoods(Integer overflowListId) {
        return this.goodsService.queryOverflowListGoods(overflowListId);
    }

}
