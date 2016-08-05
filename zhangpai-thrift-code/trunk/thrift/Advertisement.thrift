namespace java com.shalong.zhangpai.rsc.thrift.advertisement

namespace php application.libraries.AdvertisementService

	struct Advertisement {
	   1:i32 id,   /*广告Id*/
	   2:string adTitle, /*广告标题*/
	   3:string adDescription, /*广告描述*/
	   4:string createTime, /*创建时间*/
	   5:i32 adLocation, /*广告位置*/
	   6:i32 ipSingleNum, /*广告位置*/
	   7:i32 ipMuchNum, /*共享ip人数*/
	   8:i32 onlineStatus, /*广告状态*/
	   9:i32 auditStatus, /*审核排序*/
	   10:string adImage, /*广告图片*/
	   11:string adLink, /*广告链接*/
	   12:string adContent, /*广告详情*/
    }
    
    /* 广告信息的基本定义 */
	struct GetAdvertisementInput {
		1:i32 id,
		2:string version
	}
	
    
    
    
    struct GetAdvertisementOutput {
		1:i32 returnCode,
		2:Advertisement advertisement
	}
	
	struct GetAdvertisementShowOutput {
	   1:i32 returnCode,
	   2:i32 id,   /*广告Id*/
	   3:string adTitle, /*广告标题*/
	   4:string adDescription, /*广告描述*/
	   5:string createTime, /*创建时间*/
	   6:i32 adLocation, /*广告位置*/
	   7:i32 ipSingleNum, /*广告位置*/
	   8:i32 ipMuchNum, /*共享ip人数*/
	   9:i32 onlineStatus, /*广告状态*/
	   10:i32 auditStatus, /*审核排序*/
	   11:string adImage, /*广告图片*/
	   12:string adLink, /*广告链接*/
	   13:string adContent, /*广告详情*/
	}

	
	/* 广告服务 */
	service AdvertisementService {
		/* 获取单个广告信息 */
		GetAdvertisementOutput getAdvertisementInfo(1:GetAdvertisementInput input);
		
	}
