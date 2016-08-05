package com.shalong.zhangpai.rsc.provider.user.impl;
/**
 * @FileName: UserServiceImpl.java
 * @Package com.shalong.zhangpai.rsc.provider.user
 * 
 * @author Huangyunjun
 * @created 2016年7月16日 下午5:26:30
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shalong.zhangpai.rabbit.framework.core.exception.DataAccessException;
import com.shalong.zhangpai.rabbit.framework.core.log.RabbitLogger;
import com.shalong.zhangpai.rabbit.framework.core.util.JsonUtils;
import com.shalong.zhangpai.rsc.model.user.UserModel;
import com.shalong.zhangpai.rsc.provider.common.utils.BusinessUtils;
import com.shalong.zhangpai.rsc.provider.user.dao.UserDao;
import com.shalong.zhangpai.rsc.service.user.UserService;
import com.shalong.zhangpai.rsc.thrift.enums.ReturnCode;
 

/**
 * <p>
 * TODO
 * </p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author Huangyunjun
 * @since 1.0
 * @version 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static RabbitLogger LOGGER = RabbitLogger.getInstance(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 
	 * 根据用户名获取用户信息
	 * 
	 * @author Huangyunjun
	 * @created 2016年07月16日 上午11:34:12
	 *
	 * @param userName
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public String getUser(String paramString) {
		String returnString = null;
		Map<String,String> paramMap = JsonUtils.json2Object(paramString, Map.class);
		if (paramMap == null) {
			return BusinessUtils.errorJson2Object();
		}
		
		String userName = paramMap.get("userName");
		try {
			UserModel userModel = this.userDao.getUser(userName);
			returnString = BusinessUtils.returnString(ReturnCode.OK.getValue(), "ok", "model", userModel);
		} catch (Exception e) {
			returnString = BusinessUtils.errorBusiness();
			LOGGER.error(e.getMessage(), e);
		}
		return returnString;
	}
	
	/**
	 * 
	 * 完善用户信息-绑定手机号
	 *
	 * @author Huangyunjun
	 * @created 2016年07月16日 下午3:00:31
	 *
	 * @param paramString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String bindMobileByUserId(String paramString) {
		String returnString = null;
		Map<String,String> mapValues = JsonUtils.json2Object(paramString, Map.class);
		if (mapValues == null) {
			return BusinessUtils.errorJson2Object();
		}
		UserModel model = new UserModel();
		Object userIdObj = mapValues.get("userId");
		int userId = Integer.parseInt(userIdObj.toString());
		model.setUserId(userId);
		model.setMobile(mapValues.get("mobile"));
		if (model.isMobile()) {
			try {
				long userCount1 = this.userDao.getUserCountByMobileAndUserId(model.getUserId(), model.getMobile());
				if (userCount1 == 0) { // 用户和该手机号有没有绑定？0说明没有绑定过
					long userCount2 = this.userDao.getUserCountByMobile(model.getMobile());
					if (userCount2 == 0) { // 该手机号是不是已经被其他账号绑定了？0说明没被绑定
						int count = this.userDao.bindMobile(model);
						if (count == 0) {
							returnString = BusinessUtils.returnString(ReturnCode.S_USER_NOT_FOUND.getValue(), "没有此用户", "", "");
							LOGGER.error("没有此用户", null);
						} else {
							returnString = BusinessUtils.returnString(ReturnCode.OK.getValue(), "ok", "", "");
							//需要发送站内信
							//letterc.post(userId,userId,null,LetterPrivacyType.BINDING_CELLPHONE,new String[]{model.getMobile()});
						}
					} else {
						returnString = BusinessUtils.returnString(ReturnCode.S_RULES_ERROR.getValue(), "手机号已经被绑定", "", "");
						LOGGER.error("手机号已经被绑定", null);
					}
				} else {
					returnString = BusinessUtils.returnString(ReturnCode.OK.getValue(), "ok", "", "");
				}
			} catch (DataAccessException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		} else {
			returnString = BusinessUtils.returnString(ReturnCode.S_MOBILE_ILLEGAL.getValue(), "手机号验证失败"+model.getMobile(), "", "");
			LOGGER.error("手机号验证失败", null);
		}
		return returnString;
	}


}