package com.shalong.zhangpai.bbms.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shalong.zhangpai.bbms.common.PublicData;
import com.shalong.zhangpai.bbms.service.MainService;
import com.shalong.zhangpai.rsc.thrift.region.GetCountyByCityIdInput;
import com.shalong.zhangpai.rsc.thrift.region.RegionService;
import com.shalong.zhangpai.rsc.thrift.service.CommonService;
import com.shalong.zhangpai.rsc.thrift.service.ServiceMainInput;
import com.shalong.zhangpai.rsc.thrift.user.UserClassService;
import com.shalong.zhangpai.rsc.thrift.user.UserInput;

/**
 * 主页面控制器
 */
@Controller
@RequestMapping("/main")
public class MainController extends PublicData{
	@Autowired
	MainService mainService;
	
 

	// 登录
	@RequestMapping("/index")
	public String index(ModelMap modelMap, HttpServletRequest request) throws TException {
		 
		return "/main/index";
	}
	@RequestMapping("/region")
	public String region(ModelMap modelMap, HttpServletRequest request) throws TException {
		 
		
		String address = "127.0.0.1";  
        int port = 9094;  
        int clientTimeout = 3000000;  
        TTransport transport = new TFramedTransport(new TSocket(address, port,clientTimeout));  
        TProtocol protocol = new TBinaryProtocol(transport);  
        RegionService.Client client = new RegionService.Client(protocol);
        GetCountyByCityIdInput input = new GetCountyByCityIdInput();
        input.setCityId(110100);
        input.setSize(100);
        transport.open();  
        String result = client.getCountyByCityId(input).toString();
        System.out.println("result:" + result);  
        transport.close();  
        
        modelMap.addAttribute("getCountyByCityId", result);
		return "/main/region";
	}
	
	@RequestMapping("/user")
	public String user(ModelMap modelMap, HttpServletRequest request) throws TException {
		 
		
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
        
        
      
        
       
         
        System.out.println("��ȡ�������Ϣ:" + result);
        
        modelMap.addAttribute("user", "result:"+result);
		return "/main/user";
	}
	
	@RequestMapping("/order")
	public String order(ModelMap modelMap, HttpServletRequest request) throws TException {
		 
		
		String address = "127.0.0.1";  
        int port = 9096;  
        int clientTimeout = 3000000;  
        TTransport transport = new TFramedTransport(new TSocket(address, port,clientTimeout));  
        TProtocol protocol = new TBinaryProtocol(transport);  
        CommonService.Client client = new CommonService.Client(protocol);
         
        ServiceMainInput input = new ServiceMainInput();
        String jsonArgs="{'buyerId:100010,'status':0,'pageSize':10,'page':1}";
        String secretArgs="{'appkey':'key','timestamp':'201607261010','sign':'aaaa'}";
      

        input.setServiceName("orderService");
        
        input.setMethodName("searchMyOrderList");
        
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
        
        
      
        
       
         
        System.out.println("��ȡ�������Ϣ:" + result);
        
        modelMap.addAttribute("user", "result:"+result);
		return "/main/order";
	}
	@RequestMapping("/commodity")
	public String commodity(ModelMap modelMap, HttpServletRequest request) throws TException {
		 
		
		String address = "127.0.0.1";  
        int port = 9096;  
        int clientTimeout = 3000000;  
        TTransport transport = new TFramedTransport(new TSocket(address, port,clientTimeout));  
        TProtocol protocol = new TBinaryProtocol(transport);  
        CommonService.Client client = new CommonService.Client(protocol);
         
        ServiceMainInput input = new ServiceMainInput();
        String jsonArgs="{'ownerId':32,'classify':0,'pageSize':10,'page':1}";
        String secretArgs="{'appkey':'key','timestamp':'201607261010','sign':'aaaa'}";
      

        input.setServiceName("commodityService");
        
        input.setMethodName("searchCommodityList");
        
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
        
     
         
        System.out.println("��ȡ�������Ϣ:" + result);
        
        modelMap.addAttribute("user", "result:"+result);
		return "/main/commodity";
	}

}
