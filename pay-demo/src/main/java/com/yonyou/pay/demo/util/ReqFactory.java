/**
 * Copyright 2018-2020 yonyou.com.
 * All rights reserved.
 */
package com.yonyou.pay.demo.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.yonyou.pay.demo.common.BaseParam;
import com.yonyou.pay.demo.common.ResponseData;
import com.yonyou.util.ReqUtil;

/**
 * TODO
 * 
 * @Author:zhaoq
 * @version $id:ReqFactory.java,v0.1 2019年2月11日 下午2:20:15 zhaoq Exp$
 */
public class ReqFactory {

	/**
	 * TODO
	 * 请求开始
	 * 
	 * @param paySeq 付款流水号
	 * @param amount 金额，单位元
	 * @param billType 来源单据类型 10101001 DMS维修保养预约单 10101002 DMS维修工单
	 * @param billCode 来源单据编号
	 * @param isQrcode 是否生成二维码 1 生成 ，0 不生成
	 * @return
	 * @author: zhaoq v0.1 2019年1月25日
	 * @throws IOException
	 */
	public static ResponseData reqStart(String interfaceCode, Map<String, String> reqMap) throws IOException {

		if (interfaceCode != null && !interfaceCode.startsWith("http")) {
			// 默认测试环境，若要使用正式环境，删除if中此段代码即可
			interfaceCode = BaseParam.PRO_YYPAY_URL + interfaceCode;
		}

		Map<String, String> baseMap = new HashMap<>();
		if (!reqMap.containsKey("channel")) {
			baseMap.put("channel", BaseParam.CHANNEL);
		}
		if (!reqMap.containsKey("dealerType")) {
			baseMap.put("dealerType", BaseParam.DEALERTYPE);
		}
		if (!reqMap.containsKey("dealerCode")) {
			baseMap.put("dealerCode", BaseParam.DEALERCODE);
		}
		if (!reqMap.containsKey("groupCode")) {
			baseMap.put("groupCode", BaseParam.GROUPCODE);
		}
		if (!reqMap.containsKey("msgSrc")) {
			baseMap.put("msgSrc", BaseParam.MSG_SRC_DMS);
		}
		reqMap.putAll(baseMap);
		System.out.println("请求接口地址：" + interfaceCode + "\n请求接口参数：" + reqMap + "\n请求接口密钥：" + BaseParam.KEY);
		String retData = ReqUtil.DefaultReqInterfaceByPost(interfaceCode, reqMap, BaseParam.KEY);
		System.out.println("返回参数：" + retData);
		ResponseData responseData = JSONObject.parseObject(retData, ResponseData.class);
		return responseData;
	}

	/**
	 * TODO
	 * 流水号规则：渠道号_品牌代码_经销商代码_流水号（建议单据类型+单据号）
	 * 
	 * @param billType : 单据类型
	 * @param billCode : 单据编号
	 * @return
	 * @author: zhaoq v0.1 2019年1月25日
	 */
	public static String getPaySeq(String billType, String billCode) {

		return BaseParam.CHANNEL.concat("_").concat(BaseParam.GROUPCODE).concat(BaseParam.DEALERCODE).concat(billType)
				+ billCode;
	}

	/**
	 * 获取通知参数
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getParams(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();
		Map<String, String[]> reqMap = request.getParameterMap();
		for (String key : reqMap.keySet()) {
			map.put(key.toString(), ((String[]) reqMap.get(key))[0]);
		}
		return map;
	}
}
