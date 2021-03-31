/**
 * Copyright 2018-2020 yonyou.com.
 * All rights reserved.
 */
package com.yonyou.pay.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.yonyou.pay.demo.common.BaseParam;
import com.yonyou.pay.demo.common.ResponseData;
import com.yonyou.pay.demo.util.ReqFactory;
import com.yonyou.pay.demo.util.TestUtil;

/**
 * TODO
 * 支付demo
 * 注意：以下代码均为demo，请根据具体业务参考，不要直接复制粘贴
 * xxx
 * @Author:zhaoq
 * @version $id:PayDemo.java,v0.1 2019年1月25日 上午10:10:44 zhaoq Exp$
 */
public class PayDemo {

	private static PayDemo instance = new PayDemo();

	public static PayDemo getInstace() {

		return instance;
	}

	/**
	 * TODO
	 * 
	 * @param args
	 * @author: zhaoq v0.1 2019年1月25日
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// POS下单
		// getInstace().placeOrderForPos();
		// Map<String, String> map = new HashMap<>();
		// System.out.println(map.isEmpty());
		// 订单查询
		// getInstace().orderQuery();
		// 订单退款
		getInstace().orderRefund("100001506507", "0.01", null, null, null);
		// 订单取消
		// getInstace().cancleOrder("100000383957");
		// 重新下单
		// getInstace().alignOrder("0YONYOUJ11A001ARO190100055");

	}

	/**
	 * TODO
	 * POS重新下单
	 * 
	 * @param paySeq
	 * @throws IOException
	 * @author: zhaoq v0.1 2019年2月11日
	 */
	public void alignOrder(String paySeq) throws IOException {

		Map<String, String> reqMap = new HashMap<>();
		reqMap.put("paySeq", paySeq);
		reqMap.put("isQrcode", "1");
		reqMap.put("dealerCode", "");
		reqMap.put("dealerType", "");
		reqMap.put("msgSrc", "");

		ResponseData responseData = ReqFactory.reqStart(BaseParam.POS_ALIGN_ORDER, reqMap);
		System.out.println("返回业务参数：" + responseData.outputSucData());
	}

	/**
	 * TODO
	 * POS下单demo
	 * 
	 * @throws IOException
	 * @author: zhaoq v0.1 2019年1月25日
	 */
	public void placeOrderForPos() throws IOException {

		String paySeq = ReqFactory.getPaySeq(BaseParam.BILL_TYPE_WXD, System.currentTimeMillis() + "");

		Map<String, String> reqMap = new HashMap<>();
		// 付款流水号
		reqMap.put("paySeq", paySeq);
		// 下单金额
		reqMap.put("amount", "0.02");
		// 下单人
		reqMap.put("operator", "小明");
		// 车主姓名
		reqMap.put("motormasterName", TestUtil.getChineseName());
		// 车牌号
		reqMap.put("plateNumber", TestUtil.car());
		// 是否成功二维码
		reqMap.put("isQrcode", "1");
		// 是否成功二维码
		reqMap.put("notifyUrl", "http://www.baidu.com");

		ResponseData responseData = ReqFactory.reqStart(BaseParam.TEST_YYPAY_URL + BaseParam.POS_ORDER, reqMap);
		System.out.println("返回业务参数：" + responseData.outputSucData());
	}

	/**
	 * TODO
	 * 订单查询：批量查询
	 * 
	 * @author: zhaoq v0.1 2019年1月25日
	 * @throws IOException
	 */
	public void orderQuery() throws IOException {

		Map<String, Object> tradeMap1 = new HashMap<>();
		tradeMap1.put("tradeId", 100001267292L);

		Map<String, Object> tradeMap2 = new HashMap<>();
		tradeMap2.put("tradeId", 100001267292L);
		JSONArray ja = new JSONArray(Arrays.asList(tradeMap1, tradeMap2));

		Map<String, String> reqMap = new HashMap<>();
		reqMap.put("tradeIds", ja.toJSONString());
		ResponseData responseData = ReqFactory.reqStart(BaseParam.ORDER_QUERY, reqMap);
		System.out.println("返回业务参数：" + responseData.outputSucData());
	}

	/**
	 * TODO
	 * 退款:大部分订单只支持当天退款
	 * 
	 * @param tradeId 交易号
	 * @param orderAmount 交易金额
	 * @param orderNote 退款说明
	 * @param remark 备注
	 * @param refundPerson 退款人
	 * @author: zhaoq v0.1 2019年1月25日
	 * @throws IOException
	 */
	public void orderRefund(String tradeId, String orderAmount, String orderNote, String remark, String refundPerson)
			throws IOException {

		Map<String, String> reqMap = new HashMap<>();
		reqMap.put("tradeId", tradeId);
		reqMap.put("orderAmount", orderAmount);
		reqMap.put("orderNote", orderNote);
		reqMap.put("remark", remark);
		reqMap.put("refundPerson", refundPerson);
		ResponseData responseData = ReqFactory.reqStart(BaseParam.PRO_YYPAY_URL + BaseParam.ORDER_REFUND, reqMap);
		System.out.println("返回业务参数：" + responseData.outputSucData());
	}

	/**
	 * TODO
	 * 取消订单
	 * 
	 * @param tradeId 交易号
	 * @author: zhaoq v0.1 2019年1月25日
	 * @throws IOException
	 */
	public void cancleOrder(String tradeId) throws IOException {

		Map<String, String> reqMap = new HashMap<>();
		reqMap.put("tradeId", tradeId);
		ResponseData responseData = ReqFactory.reqStart(BaseParam.CANCLE_ORDER, reqMap);
		System.out.println("返回业务参数：" + responseData.outputSucData());
	}

}
