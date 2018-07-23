package com.wardrobe.wx;

import com.wardrobe.wx.util.XMLUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Map;

public class PayNotifyServlet extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(PayNotifyServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		<xml><appid><![CDATA[wx1cfcdf89afb2c7c9]]></appid><bank_type><![CDATA[CFT]]></bank_type>
//		<cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type>
//		<is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1262530601]]></mch_id>
//		<nonce_str><![CDATA[Z6TsbnccCzoML4j7]]></nonce_str>
//		<openid><![CDATA[o_tHYjpTf9Ch_UdY_-p4UaSaZ7q4]]></openid>
//		<out_trade_no><![CDATA[95r]]></out_trade_no>
//		<result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code>
//		<sign><![CDATA[DFA257FBFA56C6E0206A3DAA088975BD]]></sign>
//		<time_end><![CDATA[20160418011229]]></time_end>
//		<total_fee>1</total_fee><trade_type><![CDATA[NATIVE]]></trade_type>
//		<transaction_id><![CDATA[4003282001201604184948404076]]></transaction_id></xml>
		String inputLine;
		String notityXml = "";
		String resXml = "";

		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("notifyXml:" + notityXml);
		
		Map m = XMLUtil.parseXmlToList(notityXml);
		/*WxpayNotifyInfo wxpayNotifyInfo = new WxpayNotifyInfo();
		wxpayNotifyInfo.setAppId(m.get("appid").toString());
		wxpayNotifyInfo.setBankType(m.get("bank_type").toString());
		wxpayNotifyInfo.setCashFee(m.get("cash_fee").toString());
		wxpayNotifyInfo.setFeeType(m.get("fee_type").toString());
		wxpayNotifyInfo.setIssubscribe(m.get("is_subscribe").toString());
		wxpayNotifyInfo.setMchId(m.get("mch_id").toString());
		wxpayNotifyInfo.setNonceStr(m.get("nonce_str").toString());
		wxpayNotifyInfo.setOpenId(m.get("openid").toString());
		wxpayNotifyInfo.setOutTradeNo(m.get("out_trade_no").toString());
		wxpayNotifyInfo.setResultCode(m.get("result_code").toString());
		wxpayNotifyInfo.setReturnCode(m.get("return_code").toString());
		wxpayNotifyInfo.setSign(m.get("sign").toString());
		wxpayNotifyInfo.setTimeEnd(m.get("time_end").toString());
		wxpayNotifyInfo.setTotalFee(m.get("total_fee").toString());
		wxpayNotifyInfo.setTradeType(m.get("trade_type").toString());
		wxpayNotifyInfo.setTransactionId(m.get("transaction_id").toString());
		wxpayNotifyInfo.setAddedTime(new Date());
		wxpayNotifyInfo.setPayServiceType("INSURANCE_ORDER");
		
		int payType = IConstant.PAY_TYPE_FULL_PAYMENT_NEW;
		
		String out_trade_no = wxpayNotifyInfo.getOutTradeNo().substring(0, wxpayNotifyInfo.getOutTradeNo().length() - 1);
		
		wxpayNotifyInfo.setOrderId(Integer.parseInt(out_trade_no));
		wxpayNotifyInfo.setPayType(String.valueOf(payType));
		
		wxpayNotifyInfoService.save(wxpayNotifyInfo);
		
		if("SUCCESS".equals(wxpayNotifyInfo.getResultCode())){
			InsuranceOrderInfo insuranceOrderInfo = insuranceOrderInfoService.load(new Integer(out_trade_no).intValue());
			insuranceOrderInfoService.updateInsuranceOrderPayStatusByInsuranceOrderId(new Integer(out_trade_no), IConstant.ORDER_PAY_STATUS_OK);	
			//支付成功
			resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
			+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
			
		}else{
			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
			+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}
		 */
		logger.info("resXml:" + resXml);
		
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		out.write(resXml.getBytes());
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
