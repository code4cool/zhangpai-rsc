namespace java com.shalong.zhangpai.rsc.thrift.enums

namespace php application.libraries.ReturnCode

enum ReturnCode{
	UNKNOWN = 100,
	OK = 200,
	CLINET_ERROR = 400,
	SERVER_ERROR = 500,
	SERVER_BUSY = 501,
	MISSING_ARG = 401,
	/* -----------消费者端异常编码------------ */
	C_EXCEPTION = 600,
	C_DATANULL = 601,
	C_IOEXCEPTION = 602,
	
	/* -----------提供者端异常编码----------- */
	P_EXCEPTION = 900,
	
	/* ------------业务异常编码------------- */
	/* 用户规则错误 */
	S_RULES_ERROR = 800,
	/* 没有此用户 */
	S_USER_NOT_FOUND = 801,
	/* 手机号不合法 */
	S_MOBILE_ILLEGAL = 802
	
	/* ------------签名验证异常编码------------- */
	/* 签名不合法 */
	S_SECRET_ILLEGAL = 903
	/* 时间戳不合法 */
	S_SECRET_TIMEOUT = 902
	/* APPKEY不合法 */
	S_APPKEY_NOT_FOUND = 904
	
	
	
}