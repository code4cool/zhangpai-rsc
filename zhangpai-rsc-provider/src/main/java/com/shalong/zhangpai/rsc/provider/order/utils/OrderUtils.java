package com.shalong.zhangpai.rsc.provider.order.utils;

import java.util.Date;

import com.shalong.zhangpai.rabbit.framework.core.util.DateUtil;
import com.shalong.zhangpai.rsc.model.order.OrderModel;

/**
 * @FileName: OrderUtils.java
 * @Package com.shalong.zhangpai.rsc.provider.order.utils
 * 
 * @author Huangyunjun
 * @created 2016年7月20日 下午5:19:55
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public class OrderUtils {
 
		public final static int ORDER_STATUS_OVER = 1; // 订单结束
		public final static int ORDER_STATUS_SUCCESS = 2; // 订单已完成
		public final static int ORDER_STATUS_CANCEL = 3; // 取消 
		public final static int ORDER_STATUS_MISS = 4; // 支付超时
		public final static int SORDER_STATUS_PROCESS = 1; // 已正常处理
		public final static int SORDER_STATUS_WAITE = 2; // 待处理
		public final static int SORDER_STATUS_USERCANCEL = 3; // 用户取消订单
		public final static int SORDER_STATUS_SYSCANCEL = 4; // 系统操作人员取消订单
 
	 
		
		public static void main(String []args) {
			System.out.println(DateUtil.currentTimeSecond());// 1387197455
			System.out.println(DateUtil.getCurrentTime());// 1387197502272
			System.out.println(DateUtil.formatTimestampToLong(DateUtil.dateFormat(new Date())+" 15:00:00"));
			System.out.println(DateUtil.formatTimestampToLong(DateUtil.dateFormat(new Date())+" 00:00:00"));
			System.out.println(DateUtil.formatTimestampToLong("2016-07-21"+" 23:59:59"));
			System.out.println(DateUtil.formatTimestampToLong("2016-07-21"+" 23:59:59"));
		}
		
		
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
		public static boolean isCancel(String OrderDate) {
			 return true;
		}
		

		/**
		 * 
		 * 完善流水订单对象
		 * 
		 * @author Huangyunjun
		 * @created 2016年07月21日 下午5:28:32
		 * 
		 * @param model
		 * @param paramString
		 * @param doctorDutyId
		 */
		public static void serialOrderPerfect(OrderModel model, String paramString, int orderId) {
			// 生成订单号
			model.setOrderNo(OrderUtils.generateOrderNumber(model.getServiceChannelId() + ""));
			model.setDescription(paramString);
		}

		/**
		 * 
		 * 订单号生成 规则：14位时间戳+4位渠道码+随机6位数字 4位渠道码：默认1000来自IOS
		 * 
		 * @author Huangyunjun
		 * @created 2016年07月21日 下午7:48:56
		 * 
		 * @param channelCode
		 *            频道码 
		 * 
		 * @return
		 */
		public static String generateOrderNumber(String channelCode) {
			String dateStr = DateUtil.replaceAllSeparator(DateUtil.timestampFormat(new Date()));
			return dateStr + channelCode + generateIdentifyingCode(6);
		}

		/**
		 * 生成指定位数的随机数
		 * 
		 * @param length
		 *            位数
		 * @return
		 */
		private static long generateIdentifyingCode(int length) {
			int max = 1;
			int min = 1;
			switch (length) {
			case 2:
				max = 99;
				min = 10;
				break;
			case 3:
				max = 999;
				min = 100;
				break;
			case 4:
				max = 9999;
				min = 1000;
				break;
			case 5:
				max = 99999;
				min = 10000;
				break;
			case 6:
				max = 999999;
				min = 100000;
				break;
			case 7:
				max = 9999999;
				min = 1000000;
				break;
			case 8:
				max = 99999999;
				min = 10000000;
				break;
			case 9:
				max = 999999999;
				min = 100000000;
				break;
			default:
				max = 999999;
				min = 100000;
				break;
			}
			long ll = Math.round(Math.random() * (max - min) + min);
			return ll;
		}

	 
}
 
