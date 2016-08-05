package com.shalong.zhangpai.rabbit.framework.base.model;
/**
 * @FileName: Combo3.java
 * @Package com.shalong.zhangpai.rabbit.framework.base.model
 * 
 * @author Huangyunjun
 * @created 2016年7月16日 下午2:28:22
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
public class Combo3<V1, V2, V3> implements java.io.Serializable {
	private static final long serialVersionUID = 1235932292203214047L;
	private V1 v1;
	private V2 v2;
	private V3 v3;

	public Combo3(V1 v1, V2 v2, V3 v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
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
}
