namespace java com.shalong.zhangpai.rsc.thrift.user

namespace php application.libraries.UserClassService

/*移动终端登录接口*/
struct LoginInput {
	1:string userName,
	2:string password,
	3:string version
}

struct LoginOutput {
	1:i32 userId,
	2:i32 shardId,
	3:string userName,
	4:string email,
	5:string mobile,
	6:string cardHolderName,
	7:string idNumber,
	8:string password,
	9:i32 gender,
	10:i32 userType,
	11:i32 status,
	12:i32 returnCode
}

/*获取用户信息*/
struct GetUserInput {
	1:i32 userId,
	2:string version
}

struct GetUserOutput {
	1:i32 userId,
	2:i32 shardId,
	3:string userName,
	4:string email,
	5:string mobile,
	6:string cardHolderName,
	7:string idNumber,
	8:string password,
	9:i32 gender,
	10:i32 userType,
	11:i32 status,
	12:i32 returnCode
}

/*用户服务接口*/
service UserService {

	/* 登录接口 */
	LoginOutput login(1:LoginInput input);

	/* 获取用户信息 */
	GetUserOutput getUser(1:GetUserInput input);
}
