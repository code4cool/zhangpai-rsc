package com.shalong.zhangpai.rsc.consumer;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.shalong.zhangpai.rsc.thrift.region.GetCountyByCityIdInput;
import com.shalong.zhangpai.rsc.thrift.region.RegionService;
import com.shalong.zhangpai.rsc.thrift.user.UserClassService;
import com.shalong.zhangpai.rsc.thrift.user.UserInput;

/**
 * @FileName: ThriftClient.java
 * @Package com.shalong.zhangpai.rsc.consumer
 * 
 * @author Huangyunjun
 * @created 2016年7月20日 上午2:33:23
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class ThriftClient {
	private static final int USER_SERVICE_PORT = 9090;
	private static final int RERION_SERVICE_PORT = 9094;
	
	public static void main(String []args){
		try {
			
			getRegion();
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static void login() throws Throwable{
		String address = "127.0.0.1";  
        int port = USER_SERVICE_PORT;  
        int clientTimeout = 3000000;  
        TTransport transport = new TFramedTransport(new TSocket(address, port,  
                clientTimeout));  
        TProtocol protocol = new TBinaryProtocol(transport);  
        UserClassService.Client client = new UserClassService.Client(protocol);
        UserInput user = new UserInput();
        user.setUserId(111);
        user.setUserName("kk");
        transport.open();  
        System.out.println("读取服务端信息:" + client.login(user));  
        transport.close();  
	}
	
	public static void getRegion() throws Throwable{
		String address = "127.0.0.1";  
        int port = RERION_SERVICE_PORT;  
        int clientTimeout = 3000000;  
        TTransport transport = new TFramedTransport(new TSocket(address, port,clientTimeout));  
        TProtocol protocol = new TBinaryProtocol(transport);  
        RegionService.Client client = new RegionService.Client(protocol);
        GetCountyByCityIdInput input = new GetCountyByCityIdInput();
        input.setCityId(110100);
        input.setSize(100);
        transport.open();  
        System.out.println("读取服务端信息:" + client.getCountyByCityId(input));  
        transport.close();  
	}
	
	

}
