package com.atguigu.jxc;

import com.atguigu.jxc.dao.GoodsDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JxcApplicationTests {
	@Resource
	private GoodsDao goodsDao;

	@Test
	public void contextLoads() {
		System.out.println("goodsDao.queryGoodsById(1) = " + goodsDao.queryGoodsById(1));
	}

}
