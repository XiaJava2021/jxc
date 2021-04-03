package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Date:2021/4/3 -11:53
 * @Auther:浮生
 */
@Controller
public class GoodsTypeController {



    @PostMapping("goodsType/save")
    public ServiceVO saveType(String goodsTypeName, Integer pId) {
        return null;
    }
}
