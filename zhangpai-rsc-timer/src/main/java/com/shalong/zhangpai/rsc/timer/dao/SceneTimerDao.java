package com.shalong.zhangpai.rsc.timer.dao;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.shalong.zhangpai.rabbit.framework.cache.JedisCacheClient;
import com.shalong.zhangpai.rabbit.framework.core.Transcoder;
import com.shalong.zhangpai.rabbit.framework.core.config.GlobalConfig;
import com.shalong.zhangpai.rabbit.framework.data.database.DataContext;
import com.shalong.zhangpai.rsc.model.message.Message;
import com.shalong.zhangpai.rsc.thrift.consts.RedisKeyConstants;

/**
 * @FileName: SceneTimerDao.java
 * @Package com.shalong.zhangpai.rsc.timer.dao
 * 
 * @author Huangyunjun
 * @created 2016年7月31日 上午6:06:21
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */
@Repository("sceneTimerDao")
public class SceneTimerDao extends DataContext {
	
	Logger log = Logger.getLogger(SceneTimerDao.class);
	
	 /**
    * reids客户端
    */
	private JedisCacheClient jedis = new JedisCacheClient(GlobalConfig.getReidsHostAndPort(),"doctor");
	
	@Autowired
	public SceneTimerDao(@Qualifier("SLZP_MT_M") DruidDataSource masterDataSource,
            			@Qualifier("SLZP_MT_S")DruidDataSource slaveDataSource) {
		super(masterDataSource, slaveDataSource);
	}
	
	
	/**
	 * 
	 * 设置拍场提醒消息推送任务
	 *
	 * @author Huangyunjun
	 * @created 2016年07月31日   上午6:30:24
	 *
	 * @param  
	 * @return
	 */
	public void setSceneRemindedMessage () throws Exception {
		Message message = new Message();
		
		 int rand =  (int)(1+Math.random()*(1000000-1+1));
		try {
			String key = RedisKeyConstants.SCENE_REMIDED_MESSAGE+"_"+rand;
			byte[] bkey = Transcoder.encodeString(key);
			byte[] bvalue = jedis.get(bkey);
			 
			if(Arrays.equals(bvalue, RedisKeyConstants.NULL_VALUE.getBytes())) {
				 //Redis中拍场提醒数据为null
				log.info("Redis:"+key+"：null");
				
			} else if(bvalue != null) {
				//Redis中拍场提醒不为空
				log.info("拍场提醒Redis_"+key+"不为空!");
				
				//将Redis数据转实体 数据
				
				//实体 数据为空执行jedis.del(bkey);
				
				//重新执行setSceneRemindedMessage方法
				
			}else{
				log.info("拍场提醒Redis_"+key+"信息不存在!");
				//从MYSQL中获取拍场提醒数据
									
				//设置Redis
				String sbvalue = ""+rand;
				message.setId(rand);
				message.setMobile("18811058939");
				message.setContent("沙龙掌拍APP2.0上线！");
				
				//jedis.setex(bkey, GlobalConfig.one_hour(),bvalue);
				 
				//jedis.lpush(Transcoder.encodeString("mobileMsg"),  Transcoder.encodeObject(message));
				jedis.publish("zhangpai", "18811058939"+rand);
				
				log.info("拍场提醒Redis_"+key+"信息缓存成功!");
				//List<byte[]> list = jedis.lrange(Transcoder.encodeString("mobileMsg"), -1, -1);
				//log.info("获取Redis====mobileMsg:"+list.toString());
				
				
			}
			
		}catch(Exception e) {
			throw e;
		}finally {
			 
		}
	}

}
