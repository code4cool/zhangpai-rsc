package com.shalong.zhangpai.rsc.consumer.main;


/**
 * @FileName: Service.java
 * @Package com.shalong.zhangpai.rsc.consumer.main
 * 
 * @author Huangyunjun
 * @created 2016年7月17日 上午9:35:23
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.thrift.TProcessor;

import com.shalong.zhangpai.rabbit.framework.thrift.ThriftServer;
import com.shalong.zhangpai.rsc.consumer.handler.CommonServiceHandler;
import com.shalong.zhangpai.rsc.consumer.handler.RegionServiceHandler;
import com.shalong.zhangpai.rsc.thrift.region.RegionService;
import com.shalong.zhangpai.rsc.thrift.service.CommonService;


public class Server {
 
    
	public static void init(){
		createServer(RegionService.Processor.class, RegionService.Iface.class, new RegionServiceHandler(), 9094);	
		//createServer(UserService.Processor.class, UserService.Iface.class, new UserServiceHandler(), 9090);
		createServer(CommonService.Processor.class, CommonService.Iface.class, new CommonServiceHandler(), 9096); 
	}
	
	/**
	 * 
	 * 生成thrift服务端对象
	 *
	 * @author Huangyunjun
	 * @created 2016年07月17日 下午9:43:23
	 *
	 * @param Processor 处理器
	 * @param Iface 接口
	 * @param serviceImplObject 服务实现类对象
	 * @param port 端口号
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void createServer(Class Processor, Class Iface, Object serviceImplObject, int port) {  
        try {
			Constructor con = Processor.getConstructor(Iface);  
			TProcessor processor = (TProcessor) con.newInstance(serviceImplObject);  
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " ThriftServer start，  " + serviceImplObject.getClass().getName() + " port：" + port);
			new ThriftServer(processor, port);
		} catch (SecurityException e) {
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " ThriftServer start exception：SecurityException，  " + serviceImplObject.getClass().getName() + " port：" + port);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " ThriftServer start exception：IllegalArgumentException，  " + serviceImplObject.getClass().getName() + " port：" + port);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " ThriftServer start exception：NoSuchMethodException，  " + serviceImplObject.getClass().getName() + " port：" + port);
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " ThriftServer start exception：InstantiationException，  " + serviceImplObject.getClass().getName() + " port：" + port);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " ThriftServer start exception：IllegalAccessException，  " + serviceImplObject.getClass().getName() + " port：" + port);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " ThriftServer start exception：InvocationTargetException，  " + serviceImplObject.getClass().getName() + " port：" + port);
			e.printStackTrace();
		}  
    } 
	

}
