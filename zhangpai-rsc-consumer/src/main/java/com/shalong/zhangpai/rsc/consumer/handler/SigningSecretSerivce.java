package com.shalong.zhangpai.rsc.consumer.handler;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.shalong.zhangpai.rabbit.framework.core.util.JsonUtils;

/**
 * @FileName: SigningSecret.java
 * @Package com.shalong.zhangpai.rsc.consumer.handler
 * 
 * @author Huangyunjun
 * @created 2016年7月24日 下午5:07:36
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class SigningSecretSerivce {
	
	/**
	 * 
	 * 是否可以取消订单
	 * 
	 * 规则：
	 * 不能取消订单
	 * 
	 *
	 * @author Huangyunjun
	 * @created 2016年07月21日 下午8:23:38
	 *
	 * @param OrderDate 订单时间
	 * @return true可以取消，反之，不能取消
	 */
	@SuppressWarnings("unchecked")
	public static boolean Secret(String sign,String jsonArgs) {
		
		   boolean flag = false;
		   Map<String, Object> JsonKeyMap = JsonUtils.json2Object(jsonArgs, Map.class);
		    
		    Map<String, Object> resultMap = sortMapByKey(JsonKeyMap);	
		    
		    Set JsonKeySet = resultMap.keySet();
		    String JsonKeyValues = "";
		    for(Iterator iter = JsonKeySet.iterator(); iter.hasNext();)
		    {
		    	String key = (String)iter.next();
		    	String value = (String)resultMap.get(key);
		    	JsonKeyValues = JsonKeyValues+key+value;		   
		    }
		    
		    if(sign.equals(string2MD5(string2MD5(JsonKeyValues)).toUpperCase())){
		    	flag=true;
		    }
		    //System.out.println("sign:"+sign+"===JsonKeyValues:"+string2MD5(string2MD5(JsonKeyValues)).toUpperCase());
		
		 return flag;
	}
	

	/**
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
	
		Map<String, Object> sortMap = new TreeMap<String, Object>(
				new MapKeyComparator());
	
		sortMap.putAll(map);
	
		return sortMap;
	}
	
	/*********************
	 *比较时间戳
	 * 
	 */
	public static boolean diffTimestamp(String timestamp){
		boolean flag = true;
		String date = new java.text.SimpleDateFormat("yyyyMMddHHmm").format(new java.util.Date());
        //System.out.println("转换后的时间为：" + date);
        SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMddHHmm");
        
        long between = 0;
        try {
	        java.util.Date begin = dfs.parse(timestamp);
	        java.util.Date end = dfs.parse(date);
	        between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       
       
        
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if(between>60000){
        	flag=false;
        }
       // System.out.println(between);
        return flag;
	}
	
	
	/*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  
    
}

class MapKeyComparator implements Comparator<String>{

	@Override
	public int compare(String str1, String str2) {
		
		return str1.compareTo(str2);
	}
}
