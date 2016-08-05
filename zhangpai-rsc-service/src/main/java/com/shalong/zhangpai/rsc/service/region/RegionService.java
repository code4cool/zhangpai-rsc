/**
 * @FileName: RegionService.java
 * @Package com.hyde.rsc.api.region
 * 
 * @author Huangyunjun
 * @created 2016年07月15日 上午11:35:19
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */
package com.shalong.zhangpai.rsc.service.region;

import java.util.List;

import com.shalong.zhangpai.rabbit.framework.base.model.Combo2;
import com.shalong.zhangpai.rsc.model.region.CountyModel;


/**
 * <p>区域服务接口</p>
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
public interface RegionService {
	
	/**
	 * 
	 * 根据城市id获取所有的区域信息
	 *
	 * @author Huangyunjun
	 * @created 2016年07月20日 下午8:12:54
	 *
	 * @param cityId 城市id
	 * @param size 返回区域数据数量
	 * @return
	 */

	public Combo2<Integer, String> getCountyByCityId(int cityId);
	
	/**
	 * 
	 * 根据城市id获取区域信息
	 *
	 * @author Huangyunjun
	 * @created 2016年07月20日  下午8:12:54
	 *
	 * @param cityId 城市id
	 * @param size 返回区域数据数量
	 * @return
	 */
	public Combo2<Integer, List<CountyModel>> getCountyByCityId(int cityId, int size);
	

}
