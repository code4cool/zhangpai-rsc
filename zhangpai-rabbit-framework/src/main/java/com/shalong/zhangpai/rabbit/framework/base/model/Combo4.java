package com.shalong.zhangpai.rabbit.framework.base.model;
/**
 * @FileName: Combo4.java
 * @Package com.shalong.zhangpai.rabbit.framework.base.model
 * 
 * @author Huangyunjun
 * @created 2016年7月16日 下午2:28:05
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

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
public class Combo4<V1, V2, V3, V4> implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private V1 v1;
	private V2 v2;
	private V3 v3;
	private V4 v4;

	public Combo4(V1 v1, V2 v2, V3 v3, V4 v4) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.v4 = v4;
	}

	public V1 getV1() {
		return v1;
	}

	public V2 getV2() {
		return v2;
	}

	public V3 getV3() {
		return v3;
	}

	public V4 getV4() {
		return v4;
	}
}
