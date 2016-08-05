package com.shalong.zhangpai.rsc.model.user;
/**
 * @FileName: UserModel.java
 * @Package com.shalong.zhangpai.rsc.model.user
 * 
 * @author Huangyunjun
 * @created 2016年7月16日 下午5:29:07
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shalong.zhangpai.rabbit.framework.base.model.BaseModel;
import com.shalong.zhangpai.rabbit.framework.data.database.Column;

/**
 * <p>TODO</p>
 * <p> * 用户对象 * </p>
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
public class UserModel extends BaseModel {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8299731338379951649L;
	
	
	private int userId; // 自增id
	private int shardId; // 用户分片id
	private String userName; // 用户名称
	private String eMail; // 用户邮件
	private String mobile; // 用户手机号
	private String cardHolderName; // 用户真实姓名
	private String idNumber; // 用户证件号码
	private String password; // 用户登录密码
	private String passSalt; // 用户密码产生盐
	private int gender; // 性别
	private int userType; // 会员类型
	private int status; // 用户状态 1-正常 2-逻辑删除 4-冻结 8-未激活
	private String portrait; // 用户头像
	private int channelId; // 用户渠道来源
	private String createDate; // 用户注册账号时的时间
	private String lastUpdateDate; // 用户最后一次修改账号信息的时间
	private String lastLoginDate; // 用户最后一次登录账号的时间
	
	
	/**
	 * 
	 * 验证手机号合法性
	 *
	 * @author Huangyunjun
	 * @created 2016年07月17日 下午12:20:38
	 *
	 * @return
	 */
	public boolean isMobile() {
		String regex = "^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(this.mobile);
    	return matcher.matches();
	}
	

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	@Column("UserId")
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the shardId
	 */
	public int getShardId() {
		return shardId;
	}

	@Column("ShardId")
	public void setShardId(int shardId) {
		this.shardId = shardId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	@Column("UserName")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the eMail
	 */
	public String getEMail() {
		return eMail;
	}

	@Column("EMail")
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	@Column("Mobile")
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the cardHolderName
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}

	@Column("CardHolderName")
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	@Column("IDNumber")
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	@Column("Password")
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the passSalt
	 */
	public String getPassSalt() {
		return passSalt;
	}

	@Column("PassSalt")
	public void setPassSalt(String passSalt) {
		this.passSalt = passSalt;
	}

	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	@Column("Gender")
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return the userType
	 */
	public int getUserType() {
		return userType;
	}

	@Column("UserType")
	public void setUserType(int userType) {
		this.userType = userType;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	@Column("Status")
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the portrait
	 */
	public String getPortrait() {
		return portrait;
	}

	/**
	 * @param portrait the portrait to set
	 */
	@Column("Portrait")
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	/**
	 * @return the channelId
	 */
	public int getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId the channelId to set
	 */
	@Column("ChannelId")
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	@Column("CreateDate")
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the lastUpdateDate
	 */
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	@Column("LastUpdateDate")
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * @return the lastLoginDate
	 */
	public String getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * @param lastLoginDate the lastLoginDate to set
	 */
	@Column("LastLoginDate")
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
}
