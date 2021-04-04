package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.entity.Unit;
import com.atguigu.jxc.entity.vo.GoodsTypeVo;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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

    @Autowired
    private UserService userService;


    /**
     * 查询商品所有分类
     *
     * @return
     */
    @ResponseBody
    @PostMapping("goodsType/loadGoodsType")
    public String queryAllGoodsType() {
        List<GoodsTypeVo> list = goodsService.queryAllGoodsType();

        Gson gson = new Gson();
        String goodsTypes = gson.toJson(list);
        return goodsTypes;
    }

    /**
     * 查询所有商品单位
     *
     * @return
     */
    @PostMapping("unit/list")
    @ResponseBody
    public Map<String, Object> queryUnitList() {

        List<Unit> list = goodsService.queryUnitList();
        HashMap<String, Object> unitMap = new HashMap<>();
        unitMap.put("rows", list);
        return unitMap;
    }
//    @PostMapping("unit/list")
//    public Map<String,Object> unitList(){
//        return goodsService.unitList();
//    }


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
    @ResponseBody
    public Map<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        return goodsService.listInventory(page, rows, codeOrName, goodsTypeId);
    }

    /**
     * 分页查询商品信息
     *
     * @param page        当前页
     * @param rows        每页显示条数
     * @param goodsName   商品名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    @ResponseBody
    @PostMapping("goods/list")
    public Map<String, Object> list(Integer page, Integer rows, String goodsName, Integer goodsTypeId) {
        return goodsService.list(page, rows, goodsName, goodsTypeId);
    }


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
     *
     * @param goods 商品信息实体
     * @return
     */
    @ResponseBody
    @PostMapping("goods/save")
    public ServiceVO saveOrUpdateGoods(Goods goods, Integer goodsId) {
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
     *  @Auth 吴健
     *  @Date 4.3
     * @param goodsId 商品ID
     * @return
     */
    @PostMapping("goods/delete")
    public ServiceVO deleteGoodsById(Integer goodsId){
        ServiceVO serviceVO = goodsService.deleteGoodsById(goodsId);
        return serviceVO;
    }

    /**
     * 分页查询无库存商品信息
     * @Auth 吴健
     * @Date 4.3
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
     * @Date 4.3
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
     * @param goodsId 商品ID
     * @return
     */

    /**
     * 查询库存报警商品信息
     * @return
     */
    @PostMapping("goods/listAlarm")
    @ResponseBody
    public Map<String,Object> listAlarm(){
        return goodsService.listAlarm();
    }

    /**
     * 新增报溢单
     * @param overflowNumber
     * @param overflowList
     * @param overflowListGoodsStr
     * @param session
     * @return
     */
    @PostMapping("overflowListGoods/save")
    @ResponseBody
    public ServiceVO saveOverflowListGoods(@RequestParam("overflowNumber")String overflowNumber,
                                           OverflowList overflowList,
                                           String overflowListGoodsStr,
                                           HttpSession session){
        try {
            Map<String, Object> map = userService.loadUserInfo(session);
            Gson gson = new Gson();
            List<OverflowListGoods> overflowListGoods = gson.fromJson(overflowListGoodsStr, new TypeToken<List<OverflowListGoods>>(){}.getType());//gson.fromJson(overflowListGoodsStr, List.class);
            overflowList.setUserId(Integer.valueOf(map.get("userId").toString()));
            goodsService.saveOverflowListGoods(overflowNumber,overflowList,overflowListGoods);
            return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return new ServiceVO(ErrorCode.REQ_ERROR_CODE,ErrorCode.REQ_ERROR_MESS);
    }

    /**
     * 报损单查询
     * @param sTime
     * @param eTime
     * @return
     */
    @PostMapping("damageListGoods/list")
    @ResponseBody
    public Map<String,Object> queryDamageListGoodsByTime(String  sTime, String  eTime){
        return goodsService.queryDamageListGoodsByTime(sTime,eTime);
    }

    /**
     * 查询报损单商品信息
     *
     * @param damageListId 报损单Id
     * @return
     */
    @PostMapping("damageListGoods/goodsList")
    @ResponseBody
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
    @ResponseBody
    public Map<String, Object> queryOverflowList(String sTime, String eTime) {
        return this.goodsService.queryOverflowList(sTime, eTime);
    }

    /**
     * 报溢单商品信息
     * @param overflowListId
     * @return
     */
    @PostMapping("overflowListGoods/goodsList")
    @ResponseBody
    public Map<String, Object> queryOverflowListGoods(Integer overflowListId) {
        return this.goodsService.queryOverflowListGoods(overflowListId);
    }
}
