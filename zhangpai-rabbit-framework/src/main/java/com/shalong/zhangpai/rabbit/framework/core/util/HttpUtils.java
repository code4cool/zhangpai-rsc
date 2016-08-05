/**
 * @FileName: HttpUtils.java
 * @Package com.rabbit.framework.core.util
 * 
 * @author narisu
 * @created 2014年1月14日 上午10:56:43
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */
package com.shalong.zhangpai.rabbit.framework.core.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

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
public class HttpUtils {

	public HttpUtils() {

	}

	public String doGet(String url, String params) throws Exception {
		String result = null;
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpGet httpGet = new HttpGet("http://www.gxnu.edu.cn/default.html");
		System.out.println(httpGet.getRequestLine());
		try {
			// 执行get请求
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			// 响应状态
			System.out.println("status:" + httpResponse.getStatusLine());
			// 判断响应实体是否为空
			if (entity != null) {
				System.out.println("contentEncoding:"
						+ entity.getContentEncoding());
				System.out.println("response content:"
						+ EntityUtils.toString(entity));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流并释放资源
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Map<String,Object> doPost(String url,String params) throws HttpException, IOException, Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpPost httpPost = new HttpPost(url);
		StringEntity stringEntity = new StringEntity(params,ContentType.create("plain/text", Consts.UTF_8));
		httpPost.setEntity(stringEntity);
		httpPost.addHeader("client_id","RegSysWeb");
		try {
			// 执行get请求
			HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			// 响应状态
			result.put("code", httpResponse.getStatusLine().getStatusCode());
			// 判断响应实体是否为空
			if (entity != null) {
				result.put("message", EntityUtils.toString(entity));
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				// 关闭流并释放资源
				closeableHttpClient.close();
			} catch (IOException e) {
				throw e;
			}
		}
		return result;
	}

	public void doAsyncGet(String url,final Action<HttpResponse> callback) throws IOException, InterruptedException {
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(3000)
				.setConnectTimeout(3000).build();
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
				.setDefaultRequestConfig(requestConfig).build();
		try {
			httpclient.start();
			final HttpGet request = new HttpGet(url);
			final CountDownLatch latch = new CountDownLatch(1);
			httpclient.execute(request, new FutureCallback<HttpResponse>() {
				public void completed(final HttpResponse response) {
					latch.countDown();
					System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
					if(callback != null) {
						callback.run(response);
					}
				}

				public void failed(final Exception ex) {
					latch.countDown();
					System.out.println(request.getRequestLine() + "->" + ex);
				}

				public void cancelled() {
					latch.countDown();
					System.out.println(request.getRequestLine() + " cancelled");
				}

			});
			latch.await();
			System.out.println("Shutting down");
		} finally {
			httpclient.close();
		}
		System.out.println("Done");
	}

	public void doAsyncPost(String url, String params,final Action<HttpResponse> callback) throws InterruptedException, IOException {
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(3000)
				.setConnectTimeout(3000).build();
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
				.setDefaultRequestConfig(requestConfig).build();
		try {
			httpclient.start();
			final HttpPost request = new HttpPost(url);
			StringEntity stringEntity = new StringEntity(params);//,ContentType.create("plain/text", Consts.UTF_8));
			request.setEntity(stringEntity);
			
			final CountDownLatch latch = new CountDownLatch(1);
			
			httpclient.execute(request, new FutureCallback<HttpResponse>() {
				public void completed(final HttpResponse response) {
					latch.countDown();
					System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
					callback.run(response);
				}

				public void failed(final Exception ex) {
					latch.countDown();
					System.out.println(request.getRequestLine() + "->" + ex);
				}

				public void cancelled() {
					latch.countDown();
					System.out.println(request.getRequestLine() + " cancelled");
				}

			});
			latch.await();
			System.out.println("Shutting down");
		} finally {
			httpclient.close();
		}
		System.out.println("Done");
	}

	public static void main(String[] args) {
		/*
		HttpUtils client = new HttpUtils();
		try {
			for(byte i = 0; i < 200; i++) {
				System.out.println(i);
				client.urlConnectionPost("http://127.0.0.1:8080/hyde-web-ihealth/ihealth","1");;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		HttpUtils client = new HttpUtils();
		client.urlConnectionPost("http://127.0.0.1:8080/hyde-web-ihealth/ihealth","hello");
	}
	
	public  void urlConnectionPost(String urlString,String params) {
		 URL url;
		    try {
		        url = new URL(urlString);
		        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		        conn.addRequestProperty("client_id","RegSysWeb");
		        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
		        conn.setRequestProperty("Content-Type", "plain/text; charset=UTF-8");
		        conn.setRequestMethod("POST");
		        conn.setDoOutput(true);
		        conn.setDoInput(true);
		        conn.setConnectTimeout(1000 * 5);
		        conn.getOutputStream().write(params.getBytes("utf8"));
		        
		        DataOutputStream os = new DataOutputStream(conn.getOutputStream());
		        
		        
		        os.writeUTF(params);
		        
		        os.flush();
		        os.close();
		        
		        os.flush();
		        os.close();
		   

		        byte[] buffer = new byte[1024];
		        StringBuffer sb = new StringBuffer();
		        InputStream in = conn.getInputStream();
		        int httpCode = conn.getResponseCode();
		        System.out.println(httpCode);
		        while(in.read(buffer,0,buffer.length) != -1) {
		        	sb.append(new String(buffer));
		        }
		        in.close();
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }

	}
}
