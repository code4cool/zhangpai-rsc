package com.shalong.zhangpai.rsc.service.user;
/**
 * @FileName: UserService.java
 * @Package com.shalong.zhangpai.rsc.service
 * 
 * @author Huangyunjun
 * @created 2016年7月16日 下午5:21:33
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司 版权所有
 */

public interface UserService {

	/**
	 * 
	 * 根据用户名获取用户信息
	 * 
	 * 参数：{'userName':'binbin'}
	 * 返回值：{'data':{'model':{'cardHolderName':'身份证','lastLoginDate':null,'channelId':0,'status':1,'lastUpdateDate':null,'userType':1,'shardId':0,'passSalt':null,'userId':10000,'userName':'Tom','gender':1,'createDate':null,'idNumber':'13222198201010018','mobile':'18600000101'}},'code':200,'msg':'ok'}
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月16日 上午11:34:12
	 *
	 * @param userName
	 * @return
	 */
	public String getUser(String paramString);
	
	/**
	 * 
	 * 绑定手机号
	 * 参数：{'userId':'123', 'mobile':'18600000000'}
	 * 返回值：{'data':'','code':200,'msg':'ok'}
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午3:00:31
	 *
	 * @param paramString
	 * @return
	 */
	public String bindMobileByUserId(String paramString);

}