/**
 * Copyright 2018-2020 yonyou.com.
 * All rights reserved.
 */
package com.yonyou.pay.demo.common;

/**
 * TODO
 * 基础参数
 * 以下参数除了环境地址，其他参数皆为测试参数，要正式上线请联系我们的开发人员开通
 * 
 * @Author:zhaoq
 * @version $id:BaseParam.java,v0.1 2019年1月25日 上午10:11:25 zhaoq Exp$
 */
public interface BaseParam {

	/** BASE_PRO_URL:正式环境地址 */
	String	BASE_PRO_URL		= "https://pay.idealerss.com";

	/** BASE_TEST_URL:测试环境地址 */
	String	BASE_TEST_URL		= "https://test1-pay-api.ops.yonyouauto.com";
	 //String BASE_TEST_URL = "https://beta.pay.idealerss.com.ops.yonyouauto.com";

	/** PRO_YYPAY_URL 正式yypay */
	String	PRO_YYPAY_URL		= BASE_PRO_URL + "/yonYouPay";

	/** TEST_YYPAY_URL 流水模式测试yypay */
	String	TEST_YYPAY_URL		= BASE_TEST_URL + "/yonYouPay";

	/** TEST_ORDERMODEL_URL：订单模式 */
	String	TEST_ORDERMODEL_URL	= BASE_TEST_URL + "/orderModelPay";

	/** WG_CODE 网关响应代码：成功 */
	Integer	WG_CODE_SUC			= 91200;

	/** YW_CODE_SUC 业务响应代码:成功 */
	String	YW_CODE_SUC			= "S";

	/** YW_CODE_ERR 业务响应代码:失败 */
	String	YW_CODE_ERR			= "E";

	/** CHANNEL:渠道号 */
	String	CHANNEL				= "0";

	/** DEALERTYPE：经销商类型 */
	String	DEALERTYPE			= "10081002";

	/** DEALERCODE：经销商代码 */
	String	DEALERCODE			= "YYQCPAY";

	/** GROUPCODE：品牌代码 */
	String	GROUPCODE			= "YONYOU";

	/** MSGSRC:消息来源 默认10091001：经销商 */
	String	MSG_SRC_DMS			= "10091001";

	/** BILL_TYPE:单据类型 ：维修单 */
	String	BILL_TYPE_WXD		= "10101002";

	/** BILL_TYPE:单据类型 ：预约单 */
	String	BILL_TYPE_YYD		= "10101001";

	/** KEY:请求加密密钥 */
	String	KEY					= "2016071278a5e9794ad8639df4c93db686fb929c";

	/** POS_ORDER:POS下单接口 */
	String	POS_ORDER			= "/pos/requestPayJson";

	/** POS_ALIGN_ORDER pos重新下单 */
	String	POS_ALIGN_ORDER		= "/pos/alignPayJson";

	/** ORDER_QUERY 订单查询 */
	String	ORDER_QUERY			= "/pay/queryTradeForToday";

	/** ORDER_QUERY 订单退款 */
	String	ORDER_REFUND		= "/pay/cancelTrans";

	/** CANCLE_ORDER 取消订单 */
	String	CANCLE_ORDER		= "/pay/cancelOrder";
}
