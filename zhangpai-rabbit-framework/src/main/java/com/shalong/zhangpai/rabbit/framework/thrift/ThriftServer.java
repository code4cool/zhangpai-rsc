package com.shalong.zhangpai.rabbit.framework.thrift;
/**
 * @FileName: ThriftServer.java
 * @Package com.shalong.zhangpai.rabbit.framework.thrift
 * 
 * @author Huangyunjun
 * @created 2016年7月16日 下午2:33:21
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * <p>Thrift服务端</p>
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
public class ThriftServer {
	
	/**
	 * 线程池
	 */
	private final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(30, 50, 3000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	
	 
	public ThriftServer(TProcessor processor, int port){
		init(processor, port);
	}
	
	public void init(TProcessor processor, int port){
		threadPool.execute(new SingleThread(processor, port));
	}
	
	/**
	 * 
	 * <p>启动服务的线程</p>
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
	class SingleThread implements Runnable {

		/**
		 * 默认端口号
		 */
		private int port = 8989;
		
		private TProcessor processor;
		

		public SingleThread(TProcessor processor, int port) {
			this.processor = processor;
			this.port = port;
			
		}

		public void run() {
			startServer();
		}
		
		/**
		 * 
		 * 启动thrift服务
		 *
		 * @author Huangyunjun
		 * @created 2016年07月20日 下午2:52:16
		 *
		 */
		public void startServer() {
		  try {
		      TNonblockingServerSocket socket = new TNonblockingServerSocket(port);  
	          THsHaServer.Args arg = new THsHaServer.Args(socket);
	          // 二进制编码格式进行数据传输 , 为什么不使用TCompactProtocol？ios不支持， 客户端和服务端必须一致。
	          arg.protocolFactory(new TBinaryProtocol.Factory());  
	          // 使用非阻塞方式，按块的大小进行传输，类似于 Java 中的 NIO  
	          arg.transportFactory(new TFramedTransport.Factory());  
	          arg.processorFactory(new TProcessorFactory(processor)); 
	          TServer server = new THsHaServer(arg);
	          server.serve();
		  } catch (TTransportException x) {
		      x.printStackTrace();
		      System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " ThriftServer启动异常, 端口号：" + port);
		  }
		}

	}
	
}
