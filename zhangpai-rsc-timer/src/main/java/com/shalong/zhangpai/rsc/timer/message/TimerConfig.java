package com.shalong.zhangpai.rsc.timer.message;
/**
 * @FileName: TimerConfig.java
 * @Package com.shalong.zhangpai.rsc.timer.message
 * 
 * @author Huangyunjun
 * @created 2016年7月30日 下午8:30:47
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class TimerConfig {

	public int id;// 自增主键
	public String timerTaskName;//定时器名称
	public int timerType;//定时器类型 1-重复执行 2-单次
	public long timerDelay;//延迟（秒）
	public long timerPeriod;//重复间隔（秒）
	public int isExecute;//是否执行 1-已经执行 0-未执行
	public int status;//是否有效 1-有效 0-失效
}

