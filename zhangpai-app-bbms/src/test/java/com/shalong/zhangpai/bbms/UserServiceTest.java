package com.shalong.zhangpai.bbms;


import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shalong.zhangpai.rsc.thrift.service.CommonService;
import com.shalong.zhangpai.rsc.thrift.service.ServiceMainInput;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:./conf/zhangpaibbms-spring.xml"})
public class UserServiceTest {
	
	@Test
	public void getUserServiceTest(){
		
		String address = "127.0.0.1";  
        int port = 9096;  
        int clientTimeout = 3000000;  
        TTransport transport = new TFramedTransport(new TSocket(address, port,clientTimeout));  
        TProtocol protocol = new TBinaryProtocol(transport);  
        CommonService.Client client = new CommonService.Client(protocol);
         
        ServiceMainInput input = new ServiceMainInput();
        String jsonArgs="{'userName':'huangyunjun'}";
        String secretArgs="{'appkey':'key','timestamp':'201607261010','sign':'aaaa'}";
      

        input.setServiceName("userService");
        
        input.setMethodName("getUser");
        
        input.setJsonArgs(jsonArgs);
        input.setSecretArgs(secretArgs);
        String result =  "";
        try {
        	 transport.open();  
		 
			
			result =  client.serviceMain(input);
			transport.close();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        System.out.println("读取服务端信息:" + result);  
 
		
	}

}
