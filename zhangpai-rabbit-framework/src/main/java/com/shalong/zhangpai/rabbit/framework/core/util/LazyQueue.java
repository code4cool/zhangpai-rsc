package com.shalong.zhangpai.rabbit.framework.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 用于并发入列和批量出列的Queue结构 线程安全
 * 
 * @author Huangyunjun
 */
@SuppressWarnings("all")
public class LazyQueue<T> {
	/**
	 * 队列名称
	 */
	private String name;
	/**
	 * 最大延迟毫秒数
	 */
	private int lazyMs;
	/**
	 * 最大出列长度
	 */
	private int batchCount;
	/**
	 * 出列回调
	 */
	private Action<List<T>> dequeueAction;
	/**
	 * 队列容量
	 */
	private int capacity;
	/**
	 * 队列
	 */
	private Queue<T> queue;
	/**
	 * 当前线程
	 */
	private ThreadProc thread;

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	/**
	 * 参数构造器
	 * 
	 * @param name
	 *            队列名,用于监控
	 * @param lazyMs
	 *            最大延迟毫秒数
	 * @param batchCount
	 *            最大出列长度
	 * @param dequeueAction
	 *            出列回调
	 */
	public LazyQueue(String name, int lazyMs, int batchCount,
			Action<List<T>> dequeueAction) {
		this.name = name;
		this.lazyMs = lazyMs;
		this.batchCount = batchCount;
		this.dequeueAction = dequeueAction;
		this.capacity = 65536;
		this.queue = new ConcurrentLinkedQueue<T>();
		// this.thread = new Thread(new
		// ThreadProc(queue,batchCount,lazyMs,dequeueAction));
		this.thread = new ThreadProc(queue, batchCount, lazyMs, dequeueAction);
		this.thread.start();
	}

	/**
	 * 进队列方法
	 * 
	 * @param a
	 *            要加入的队列元素
	 */
	public void enQueue(T a) {
		if (queue.size() > capacity) {
			return;
		}
		queue.add(a);
	}

	/**
	 * 无条件清空队列
	 */
	public void flush() {
		queue.clear();
	}

	/**
	 * 返回此队列中的元素数量
	 */
	public int size() {
		return this.queue.size();
	}

	/**
	 * 返回在此队列元素上以恰当顺序进行迭代的迭代器
	 */
	public Iterator<T> iterator() {
		return this.queue.iterator();
	}

	/**
	 * 设置队列线程运行状态 true-运行 false-停止
	 */
	public boolean setRunStatus(boolean runStatus) {
		return this.thread.setRunFlag(runStatus);
	}
}

/**
 * 监控队列的线程类
 */
class ThreadProc<T> extends Thread {
	/**
	 * 队列
	 */
	private Queue<T> queue;
	/**
	 * 最大出列长度
	 */
	private int batchCount;
	/**
	 * 最大延迟毫秒数
	 */
	private int lazyMs;
	/**
	 * 队列元素上次出队列时间
	 */
	private long lastMs;
	/**
	 * 出列回调
	 */
	private Action<List<T>> dequeueAction;
	/**
	 * 线程运行标志 ： true-运行 false-停止
	 */
	private boolean runFlag;

	/**
	 * 参数构造器
	 * 
	 * @param queue
	 *            队列
	 * @param batchCount
	 *            最大出列长度
	 * @param lazyMs
	 *            最大延迟毫秒数
	 * @param dequeueAction
	 *            出列回调
	 */
	public ThreadProc(Queue<T> queue, int batchCount, int lazyMs,
			Action<List<T>> dequeueAction) {
		this.queue = queue;
		this.batchCount = batchCount;
		this.lazyMs = lazyMs;
		this.dequeueAction = dequeueAction;
		this.lastMs = System.currentTimeMillis();
		this.runFlag = true;
	}

	/**
	 * 重写线程的run方法
	 */
	public void run() {
		while (runFlag) {
			long iniMs = System.currentTimeMillis();
			int dequeueCount = 0;
			// 当队列元素个数大于指定出列长度时,队列元素进行出列且出列个数为指定的出列长度
			if (queue.size() >= batchCount) {
				dequeueCount = batchCount;
			} else {
				long diffMs = iniMs - lastMs;
				// 当队列元素上次出队列时间,超出指定的毫秒数时,队列元素进行出列且出列个数为队列元素个数
				if ((diffMs > lazyMs || diffMs < -lastMs) && queue.size() > 0) {
					dequeueCount = queue.size();
				}
			}
			if (dequeueCount > 0) {
				// 队列元素出列,并返回出列的元素集合
				List<T> items = dequeueItems(dequeueCount);
				// 对出列元素进行回调处理
				dequeueAction(items);
				// 重新记录队列元素出列时间
				lastMs = System.currentTimeMillis();
			} else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					flushCache();
					e.printStackTrace();
					return;
				}
			}
		}
	}

	/**
	 * 队列元素出列
	 * 
	 * @param count
	 *            出列元素个数
	 * @return 返回所出列元素的集合
	 */
	private List<T> dequeueItems(int count) {
		// 当出列元素个数大于实际队列元素个数时,出列个数以队列实际元素为准
		if (count > queue.size()) {
			count = queue.size();
		}
		List<T> items = new ArrayList<T>(count);
		for (int i = 0; i < count; i++) {
			T item = queue.poll();
			items.add(item);
		}
		return items;
	}

	/**
	 * 对出列元素进行回调处理
	 * 
	 * @param items
	 *            出列元素集合
	 */
	private void dequeueAction(List<T> items) {
		// 队列空时,直接返回
		if (items.size() == 0)
			return;
		try {
			dequeueAction.run(items);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 刷新缓存
	 */
	public void flushCache() {
		while (queue.size() > 0) {
			int queueCount = queue.size();
			int dequeueCount = queueCount > batchCount ? batchCount
					: queueCount;
			// 元素出列
			List<T> items = dequeueItems(dequeueCount);
			// 对出列元素进行回调处理
			dequeueAction(items);
		}
	}

	/**
	 * 设置线程运行标志 true-运行 false-停止
	 */
	public boolean setRunFlag(boolean runFlag) {
		this.runFlag = runFlag;
		return this.runFlag;
	}
}
