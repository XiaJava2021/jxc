package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.DamageListService;
import com.atguigu.jxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 *
 */
@RestController
public class DamageListController {

    @Autowired
    private DamageListService damageListService;

    /**
     * 保存报损单
     * @param damageNumber 单号(前端生成)
     * @param damageList 表单
     * @param damageListGoodsStr 报损商品json数组字符串
     * @return
     */
    @PostMapping("damageListGoods/save")
    public ServiceVO save(@RequestParam("damageNumber") String damageNumber,
                          DamageList damageList,
                          String damageListGoodsStr,
                          HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        if(damageNumber == null || damageList == null || damageListGoodsStr == null || currentUser == null){
            return new ServiceVO(ErrorCode.NULL_POINTER_CODE,ErrorCode.NULL_POINTER_MESS);
        }
        damageList.setUserId(currentUser.getUserId());
        damageList.setTrueName(currentUser.getTrueName());
        damageList.setDamageNumber(damageNumber);
        Integer count = 0;
        try {
            count = this.damageListService.save(damageList,damageListGoodsStr);
        } catch (Exception e) {
            // 保存报损单出错
            e.printStackTrace();
        }
        if(count > 0){
            return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        }
        return new ServiceVO(ErrorCode.REQ_ERROR_CODE,ErrorCode.REQ_ERROR_MESS);
    }

}
