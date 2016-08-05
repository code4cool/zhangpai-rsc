package com.shalong.zhangpai.rsc.consumer.handler;

import org.apache.thrift.TException;

import com.shalong.zhangpai.rabbit.framework.core.log.RabbitLogger;
import com.shalong.zhangpai.rsc.thrift.user.GetUserInput;
import com.shalong.zhangpai.rsc.thrift.user.GetUserOutput;
import com.shalong.zhangpai.rsc.thrift.user.LoginInput;
import com.shalong.zhangpai.rsc.thrift.user.LoginOutput;
import com.shalong.zhangpai.rsc.thrift.user.UserService.Iface;

/**
 * @FileName: UserServiceHandler.java
 * @Package com.shalong.zhangpai.rsc.consumer.handler
 * 
 * @author Huangyunjun
 * @created 2016年7月20日 上午2:22:51
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class UserServiceHandler implements Iface  {
	private static RabbitLogger LOGGER = RabbitLogger.getInstance(UserServiceHandler.class);

	@Override
	public LoginOutput login(LoginInput input) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetUserOutput getUser(GetUserInput input) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
