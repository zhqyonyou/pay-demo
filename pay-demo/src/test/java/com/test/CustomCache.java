/**
 * Copyright 2018-2020 yonyou.com.
 * All rights reserved.
 */
package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 * 自定义缓存
 * 
 * @Author:zhaoq
 * @version $id:CustomCache.java,v0.1 2019年6月20日 下午4:24:21 zhaoq Exp$
 */
public class CustomCache {

	private final static Map<String, Entity>		map			= new HashMap<>();

	private final static ScheduledExecutorService	executor	= Executors.newSingleThreadScheduledExecutor();

	public synchronized static void put(String key, Object data, long expire) {

		map.remove(key);
		if (expire > 0) {
			ScheduledFuture<?> future = executor.schedule(new Runnable() {

				@Override
				public void run() {

					map.remove(key);
				}
			}, expire, TimeUnit.SECONDS);
			map.put(key, new Entity(data, future));
		}
	}

	public synchronized static Object get(String key) {

		Entity entity = map.get(key);
		return entity == null ? null : entity.getValue();
	}

	public synchronized static <T> T get(String key, Class<T> clazz) {

		return clazz.cast(get(key));
	}

	/**
	 * 清除缓存
	 *
	 * @param key
	 * @return
	 */
	public synchronized static Object remove(String key) {

		// 清除原缓存数据
		Entity entity = map.remove(key);
		if (entity == null)
			return null;
		// 清除原键值对定时器
		Future<?> future = entity.getFuture();
		if (future != null) {
			future.cancel(true);
		}
		return entity.getValue();
	}

	private static class Entity {

		private Object		value;

		private Future<?>	future;

		/**
		 * @param value
		 * @param future
		 */
		public Entity(Object value, Future<?> future) {
			super();
			this.value = value;
			this.future = future;
		}

		public Object getValue() {

			return value;
		}

		public Future<?> getFuture() {

			return future;
		}

	}

}
