namespace java com.shalong.zhangpai.rsc.thrift.service

namespace php application.libraries.CommonService

struct ServiceMainInput {
	1:string serviceName, /* 服务名 */
	2:string methodName, /* 方法名 */
	3:string secretArgs, /* 方法名 */
	4:string jsonArgs /* 参数， json格式， 一维数组*/
}


/* 所有的服务都走这一个接口， 从订单服务开始测试  */
service CommonService {

	 	/* 
		入口 
		例子：
		input值
		serviceName = orderService
		methodName = searchPatientInfosByUserId
		secretArgs = {'appkey':'zhangpaidaf4a81a3fd8e12d','timestamp':'20160919154623','sign':'BCC7C71CF93F9CDBDB88671B701D8A35'} 
		jsonArgs = {'userId':'123'}
		返回值
		{'data':{'models':[{'relationship':1,'id':1,'idType':1,'registeredName':'','isDefault':2,'status':1,'userId':10003,'gender':1,'createDate':null,'idNumber':'','mobile':''},{'relationship':1,'id':2,'idType':1,'registeredName':'','isDefault':2,'status':1,'userId':10003,'gender':2,'createDate':null,'idNumber':'','mobile':''}]},'code':200,'msg':''}
		
	 */
	string serviceMain(1:ServiceMainInput input)
	
	
	
/*             接口描述

----------绑定手机号----------------
input值：
serviceName = userService
methodName = bindMobileByUserId
secretArgs = {'appkey':'zhangpaidaf4a81a3fd8e12d','timestamp':'20160919154623','sign':'BCC7C71CF93F9CDBDB88671B701D8A35'} 
jsonArgs = {'userId':'123', 'mobile':'18600000000'}
返回值：{'data':'','code':200,'msg':'ok'}


----------根据用户名获取用户信息----------------
input值：
serviceName = userService
methodName = getUser
secretArgs = {'appkey':'zhangpaidaf4a81a3fd8e12d','timestamp':'20160919154623','sign':'BCC7C71CF93F9CDBDB88671B701D8A35'} 
jsonArgs = {'userName':'binbin'}
返回值：
{'code':'200','msg':'','data':{'registerSum':'4'}}

----------订单列表----------------
input值：
serviceName = orderService
methodName = searchMyOrderList
secretArgs = {'appkey':'zhangpaidaf4a81a3fd8e12d','timestamp':'20160919154623','sign':'BCC7C71CF93F9CDBDB88671B701D8A35'} 
jsonArgs = {'buyerId':'','status':'','pageSize':'','page':''}
返回值：{'data':{'rows':总数量,'models':[{},{}]},'code':200,'msg':''}


----------取消订单----------------
input值：
serviceName = orderService
methodName = cancelOrder
secretArgs = {'appkey':'zhangpaidaf4a81a3fd8e12d','timestamp':'20160919154623','sign':'BCC7C71CF93F9CDBDB88671B701D8A35'} 
jsonArgs = {'buyerId':'9999','orderNumber':'123243252342342343'}
返回值：{'data':'','code':200,'msg':'ok'}

----------获取订单详情----------------
input值：
serviceName = orderService
methodName = getOrderDetail
secretArgs = {'appkey':'zhangpaidaf4a81a3fd8e12d','timestamp':'20160919154623','sign':'BCC7C71CF93F9CDBDB88671B701D8A35'} 
jsonArgs = {'buyerId':'9999','orderNumber':'123243252342342343'}
返回值：{'data':'','code':200,'msg':'ok'}



----------商品列表----------------
input值：
serviceName = commodityService
methodName = searchCommodityList
secretArgs = {'appkey':'zhangpaidaf4a81a3fd8e12d','timestamp':'20160919154623','sign':'BCC7C71CF93F9CDBDB88671B701D8A35'} 
jsonArgs = {'ownerId':'','classify':'','pageSize':'','page':''}
返回值：{'data':{'rows':总数量,'models':[{},{}]},'code':200,'msg':''}


 

----------获取商品详情----------------
input值：
serviceName = commodityService
methodName = getCommodityDetail
secretArgs = {'appkey':'zhangpaidaf4a81a3fd8e12d','timestamp':'20160919154623','sign':'BCC7C71CF93F9CDBDB88671B701D8A35'} 
jsonArgs = {'ownerId':'32','commId':'7'}
返回值：{'data':'','code':200,'msg':'ok'}


*/ 

}





