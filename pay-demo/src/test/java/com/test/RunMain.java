/**
 * Copyright 2018-2020 yonyou.com.
 * All rights reserved.
 */
package com.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 * 
 * @Author:zhaoq
 * @version $id:RunMain.java,v0.1 2019年6月20日 下午4:04:57 zhaoq Exp$
 */
public class RunMain {

	private static final int FIVE = 1000000;

	public static void main(String[] args) throws InterruptedException {

		// CustomCache.put("wang", "wwwwww", 10);
		// System.out.println(CustomCache.get("wang"));
		// TimeUnit.SECONDS.sleep(10);
		// System.out.println(CustomCache.get("wang"));
		// Double transAmt = Double.parseDouble("188.00");
		// Double payAmt = Double.parseDouble(String.valueOf(188.00D));
		// if (transAmt.equals(payAmt)) {
		// System.out.println("000000000000");
		// } else {
		// System.out.println("1111111111111");
		// }

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= FIVE; i++) {
			list.add(i);
		}
		// ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>(list);

		System.out.println("数据已经准备好，开始测试");
		Long startTime = System.currentTimeMillis();

		List<FutureTask<String>> listFutureTask = new LinkedList<>();
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		list.forEach(id -> {
			TestTask task = new TestTask(id);
			FutureTask<String> futureTask = new FutureTask<>(task);
			cachedThreadPool.execute(new Thread(futureTask));
			listFutureTask.add(futureTask);
		});
		List<String> lstr = new ArrayList<>();
		listFutureTask.forEach(futureTask -> {
			try {
				String str = futureTask.get();
				if (str.equals("S")) {
					lstr.add(str);
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		Long endTime = System.currentTimeMillis();
		System.out.println(
				"一共处理了 " + list.size() + " 条数据，成功了 " + lstr.size() + " 条, 总计耗时 " + (endTime - startTime) / 1000D + "秒");
	}

}

class TestTask implements Callable<String> {

	private Integer id;

	/**
	 * @param id
	 */
	public TestTask(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	@Override
	public String call() throws Exception {

		TimeUnit.SECONDS.sleep(3);
		// System.out.println(Thread.currentThread().getName() + "正在被执行，处理时间为3秒");
		if (Thread.currentThread().getName().endsWith("1")) {
			return "E";
		}
		return "S";
	}

}
