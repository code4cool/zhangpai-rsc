package com.shalong.zhangpai.rsc.provider.user;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shalong.zhangpai.rabbit.framework.core.util.JsonUtils;
import com.shalong.zhangpai.rsc.service.user.UserService;
 
 
/**
 * @FileName: UserServiceTest.java
 * @Package com.shalong.zhangpai.rsc.prvider.user
 * 
 * @author Huangyunjun
 * @created 2016年7月16日 下午8:00:53
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */
/* 
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
public class UserServiceTest {
	
	@Autowired 
	private UserService userService;
 	
	@Test
	public void getUser() {
		String paramString = "{\"userName\":\"huangyunjun\"}"; 
		Map<String,String> paramMap = JsonUtils.json2Object(paramString, Map.class);
		System.out.println(paramMap);
		
		String username = paramMap.get("userName");
		System.out.println(username);
		
		System.out.println(this.userService.getUser(paramString));
	}
}
