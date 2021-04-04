package com.atguigu.jxc.entity.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Date:2021/4/2 -22:33
 * @Auther:浮生
 */
@Data
public class GoodsTypeVo {

    private Integer id;
    private String text;
    private String state;
    private String iconCls;
    Map<String ,Integer> attributes;

    List<GoodsTypeVo> children;

}
