package com.shalong.zhangpai.rsc.timer.enumer;
/**
 * @FileName: WeekReference.java
 * @Package com.shalong.zhangpai.rsc.timer.enumer
 * 
 * @author Huangyunjun
 * @created 2016年7月30日 下午8:50:14
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */

public enum WeekReference {

	MONDAY("Monday",getMonday()),
	TUESDAY("Tuesday",getTuesday()),
	WEDNESDAY("Wednesday",getWednesday()),
	THURSDAY("Thursday",getThursday()),
	FRIDAY("Friday",getFriday()),
	SATURDAY("Saturday",getSaturday()),
	SUNDAY("Sunday",getSunday()),
	
	MON_REGISTERED_FEE("MonRegisteredFee",getMonday()),
	TUES_REGISTERED_FEE("TuesRegisteredFee",getTuesday()),
	WED_REGISTERED_FEE("WedRegisteredFee",getWednesday()),
	THUR_REGISTERED_FEE("ThurRegisteredFee",getThursday()),
	FRI_REGISTERED_FEE("FriRegisteredFee",getFriday()),
	SAT_REGISTERED_FEE("SatRegisteredFee",getSaturday()),
	SUN_REGISTERED_FEE("SunRegisteredFee",getSunday())
	;
	
	public String name;
	int week;
	
	private final static int Sunday =0;
	private final static int Monday =1;
	private final static int Tuesday =2;
	private final static int Wednesday =3;
	private final static int Thursday =4;
	private final static int Friday =5;
	private final static int Saturday =6;
	
	WeekReference (String name,int week) {
		this.name = name;
		this.week = week;
	}

	public static int getSunday() {
		return Sunday;
	}
	public static int getMonday() {
		return Monday;
	}
	public static int getTuesday() {
		return Tuesday;
	}
	public static int getWednesday() {
		return Wednesday;
	}
	public static int getThursday() {
		return Thursday;
	}
	public static int getFriday() {
		return Friday;
	}
	public static int getSaturday() {
		return Saturday;
	}
	
	public static String getWeek(int i) {
		
		switch(i) {
		case Sunday:
			return SUNDAY.name;
		case Monday:
			return MONDAY.name;
		case Tuesday :
			return TUESDAY.name;
		case Wednesday :
			return WEDNESDAY.name;
		case Thursday:
			return THURSDAY.name;
		case Friday :
			return FRIDAY.name;
		case Saturday :
			return SATURDAY.name;
		default :
			return null;
		}
	}
	
	public static String getWeekFee(int i) {
		switch(i) {
		case Sunday:
			return SUN_REGISTERED_FEE.name;
		case Monday:
			return MON_REGISTERED_FEE.name;
		case Tuesday :
			return TUES_REGISTERED_FEE.name;
		case Wednesday :
			return WED_REGISTERED_FEE.name;
		case Thursday:
			return THUR_REGISTERED_FEE.name;
		case Friday :
			return FRI_REGISTERED_FEE.name;
		case Saturday :
			return SAT_REGISTERED_FEE.name;
		default :
			return null;
		}
	}
}

