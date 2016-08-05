package com.shalong.zhangpai.rsc.timer.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RabbitLock {

	private static ReentrantLock lock = new ReentrantLock();
	
	private static Condition condition = getLock().newCondition();
	
	private static CountDownLatch countLock = new CountDownLatch(1);

	public static ReentrantLock getLock() {
		return lock;
	}
	
	public static Condition getCondition() {
		return condition;
	}
	
	public static CountDownLatch getCountLock() {
		return countLock;
	}
}
