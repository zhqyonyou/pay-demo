/**
 * demo
 * Copyright 2018-2020 yonyou.com.
 * All rights reserved.
 */
package com.yonyou.pay.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.alibaba.fastjson.JSONObject;
import com.yonyou.pay.demo.common.BaseParam;
import com.yonyou.pay.demo.util.AssertUtil;
import com.yonyou.pay.demo.util.ReqFactory;
import com.yonyou.util.Common;

/**
 * TODO
 * 调用接口方需编写通知处理接口，并将接口地址提供给我方开发人员，在订单支付成功后后收到我方推送的成功交易通知
 * 模拟通知服务，此代码只是用来测试，仅作参考，请勿直接使用
 * @Author zhaoq
 * @version $id:NotifyController.java,v0.1  2019年1月25日 下午5:02:49 zhaoq Exp$
 */
public class NotifyController extends AbstractHandler {

	private final static String JETTERY_ICO = "/favicon.ico";

	/**
	 * TODO
	 * 
	 * @param args
	 * @author zhaoq v0.1 2019年1月25日
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//创建一个应用服务监听8080端口
        Server server = new Server(8080);
        server.setHandler(new NotifyController());
        
        //启动应用服务并等待请求
        server.start();
        server.join();
	}

	/* 
	 * 说明：调用接口方在订单支付成功后，我们的支付服务会推送交易成功的数据给调用方，此处模拟调用方收到通知的处理逻辑
	 */
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		if(!JETTERY_ICO.equals(target)) {
			Map<String, String> notifyMap = ReqFactory.getParams(request);
			System.out.println("支付通知参数：" + notifyMap);
			
			//验证签名
			verificationSign(notifyMap);
			
			//处理业务开始
			System.out.println("订单更新完成或者订单不存在或者订单处理失败等等业务逻辑");
			//处理业务结束
			
			Map<String, String> retMap = new HashMap<>();
			retMap.put("code", "S");
			retMap.put("message", "收到通知数据");
			
			// 声明response的编码和文件类型
	        response.setContentType("application/json;charset=UTF-8");
	        
	        // 声明返回状态码
	        response.setStatus(HttpServletResponse.SC_OK);

	        // 请求的返回值
	        response.getWriter().println(JSONObject.toJSONString(retMap));

	        // 通知Jettyrequest使用此处理器处理请求
	        baseRequest.setHandled(true);
		}
		
	}
	
	/**
	 * TODO
	 * 验证签名
	 * @param reqMap
	 * @author zhaoq v0.1 2019年1月25日
	 */
	private static void verificationSign(Map<String, String> reqMap) {
		String sign = reqMap.get("sign");
		reqMap.remove("sign");
		AssertUtil.isTrue(sign.equals(Common.getSign(reqMap, BaseParam.KEY)), "验签失败");
	}

}
