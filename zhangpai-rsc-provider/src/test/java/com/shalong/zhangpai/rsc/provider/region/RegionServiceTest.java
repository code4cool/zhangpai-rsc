/**
 * @FileName: MessageServiceTest.java
 * @Package com.hyde.testing
 * 
 * @author Huangyunjun
 * @created 2016年07月15日 下午4:41:10
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */
package com.shalong.zhangpai.rsc.provider.region;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shalong.zhangpai.rabbit.framework.base.model.Combo2;
import com.shalong.zhangpai.rsc.model.region.CountyModel;
import com.shalong.zhangpai.rsc.service.region.RegionService;
/**
 * <p>TODO</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * 
 * </PRE>
 * 
 * @author Huangyunjun
 * @since 1.0
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:./META-INF/spring/application-context.xml"})

public class RegionServiceTest {
	
	@Autowired
	private RegionService regionService;
	
	@Test
	public void getCountyByCityId(){
		try {
			Combo2<Integer, List<CountyModel>> combo2 = regionService.getCountyByCityId(110100, 3);
			System.out.println(combo2.getV2().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
