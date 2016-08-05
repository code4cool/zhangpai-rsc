namespace java com.shalong.zhangpai.rsc.thrift.region

namespace php application.libraries.RegionService

struct GetCountyByCityIdInput {
	1:i32 cityId = 110100,/* 城市id */
	2:i32 size = 6,/* 返回个数*/
	3:i32 orderValue, /* 1升序，0降序 */
	4:string version
}

struct CountyOutput {
	1:i32 id,
	2:i32 cityId,
	3:string name
}

struct GetCountyByCityIdOutput {
	1:list<CountyOutput> values,
	2:i32 returnCode
}

struct GetRedisCountyAllByCityIdInput {
	1:i32 cityId = 110100, /* 城市id */
	2:string version
}

struct GetRedisCountyAllByCityIdOutput {
	1:string json,
	2:i32 returnCode
}

/* 区域服务  */
service RegionService {

	/* 根据城市id获取区域信息 */
	GetCountyByCityIdOutput getCountyByCityId(1:GetCountyByCityIdInput input);
	
	/* 根据城市id获取所有区域, 第一次从数据库中获取以后直接从缓存中获取*/
	GetRedisCountyAllByCityIdOutput getRedisCountyAllByCityId(1:GetRedisCountyAllByCityIdInput input)
	

}
