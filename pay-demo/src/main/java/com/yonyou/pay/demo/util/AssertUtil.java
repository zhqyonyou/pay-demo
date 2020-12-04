/**
 * Copyright 2018-2020 yonyou.com.
 * All rights reserved.
 */
package com.yonyou.pay.demo.util;



/**
 * TODO
 * @Author:zhaoq
 * @version $id:AssertUtil.java,v0.1  2019年1月25日 上午11:56:25 zhaoq Exp$
 */
public class AssertUtil {

	public static void isTrue(boolean expression, String message) {

		if (!expression) {
			throw new RuntimeException(message);
		}
	}
	
	public static void isFalse(boolean expression, String message) {

		if (expression) {
			throw new RuntimeException(message);
		}
	}

	public static void isNull(Object object, String message) {

		if (object != null) {
			throw new RuntimeException(message);
		}
	}

	public static void notNull(Object object, String message) {

		if (object == null) {
			throw new RuntimeException(message);
		}
	}
}
