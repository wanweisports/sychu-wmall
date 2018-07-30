package com.wardrobe.controller;

import com.wardrobe.common.view.PayInputView;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.wx.WeiXinConnector;
import com.wardrobe.wx.http.HttpConnect;
import com.wardrobe.wx.util.*;
import org.apache.log4j.Logger;
import org.jdom2.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@RequestMapping("wxPay")
@Controller
public class WXPayController extends BaseController {
	
	private static Logger logger = Logger.getLogger(WXCoreController.class);
	
	//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
		private static String appid = WeiXinConnector.APP_ID;//"wx86c89d3d834ee891";
		private static String appsecret = WeiXinConnector.SECRET;//"f0ea47515f733c537d196c4017a51c6e";
		private static String partner = WeiXinConnector.MCH_ID;
		//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户中心-账户设置-api安全
		private static String partnerkey = WeiXinConnector.KEY;
		//微信支付成功后通知地址 必须要求80端口并且地址不能带参数
		private static String notifyurl = "http://tuhao.viphk.ngrok.org/front/asynNotify.action";																 // Key

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//微信支付jsApi
		/*WxPayDto tpWxPay = new WxPayDto();
		tpWxPay.setOpenId(openId);
		tpWxPay.setBody("商品信息");
		tpWxPay.setOrderId(getNonceStr());
		tpWxPay.setSpbillCreateIp("127.0.0.1");
		tpWxPay.setTotalFee("0.01");
	    getPackage(tpWxPay);*/
	    
	    //扫码支付
	    /*WxPayDto tpWxPay1 = new WxPayDto();
	    tpWxPay1.setBody("商品信息");
	    tpWxPay1.setOrderId(getNonceStr());
	    tpWxPay1.setSpbillCreateIp("127.0.0.1");
	    tpWxPay1.setTotalFee("0.01");
		getCodeurl(tpWxPay1);*/

	}
	@RequestMapping("aaa")
	public String aaa(String username, Model model, HttpServletResponse response){
		System.out.println(username);
		model.addAttribute("username", username);
		return "login";
	}
	/**
	 * 获取微信扫码支付二维码连接
	 */
	@RequestMapping("payQrUrl")
	public void payQrUrl(PayInputView payInputView, Model model, HttpServletResponse response){
		try{
			//商户订单号(唯一订单号后加随机码，不加会出现重复提交订单问题，如：第一个用户扫码输入错误密码，第二个用户扫码会出现[该单已被其他账号发起支付 您无权再发起])
			String out_trade_no = "123456_"+System.currentTimeMillis();//商户网站订单系统中唯一订单号，必填		

			//付款金额(必填)
			String price = StrUtil.getMoney("1");

			//订单描述
			String body =  "测试body"; 
			
			out_trade_no = out_trade_no + "Test";

	         SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
	         parameters.put("appid", WeiXinConnector.CORP_ID);                    
	         parameters.put("mch_id", WeiXinConnector.MCH_ID);           
	         parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
	         parameters.put("body", body);
	         parameters.put("out_trade_no", out_trade_no);
	         parameters.put("total_fee", price);                             
	         parameters.put("spbill_create_ip", "182.92.102.209");
	         parameters.put("notify_url", "https://www.baidu.com");//支付成功后回调的action，与JSAPI相同
	         parameters.put("trade_type", "NATIVE");
	         parameters.put("product_id", out_trade_no);//商品号要唯一
	         String sign = PayCommonUtil.createSign("UTF-8", parameters);
	         parameters.put("sign", sign);
	         logger.info("parameters:" + parameters);
	         
	         String requestXML = PayCommonUtil.getRequestXml(parameters);
	         String result = HttpConnect.httpsRequestStr(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
	         logger.info("result:"+result);
	         Map<String, String> map = XMLUtil.doXMLParse(result);
	         String returnCode = map.get("return_code");
	         String resultCode = map.get("result_code");
	         if(returnCode.equalsIgnoreCase("SUCCESS")&&resultCode.equalsIgnoreCase("SUCCESS")){
	            String codeUrl = map.get("code_url");
	            //TODO 拿到codeUrl，写代码生成二维码
	            logger.info("codeUrl===>"+codeUrl);
	            QrUtil.initQr(codeUrl, 300, response);
	            //request.setAttribute("codeUrl", codeUrl);
	         }else{
	        	logger.info("returnCode===>"+returnCode+", resultCode===>"+resultCode);
	         }
		}catch(Exception ex){
			logger.error("pay insurance order union infomation error!", ex);
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * 获取请求预支付id报文
	 */
	@RequestMapping("wxPayPackage")
	public String wxPayPackage(PayInputView payInputView, Model model) throws JDOMException, IOException {
		
		String openId = payInputView.getOpenId(); //从session中获取
		// 1 参数
		// 订单号
		String orderId = payInputView.getOrderId();
		// 附加数据 原样返回
		String attach = "cxs";
		// 总金额以分为单位，不带小数点
		String totalFee = StrUtil.getMoney(payInputView.getTotalFee());
		
		// 订单生成的机器 IP
		String spbill_create_ip = payInputView.getSpbillCreateIp();
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = notifyurl;
		String trade_type = "JSAPI";

		// ---必须参数
		// 商户号
		String mch_id = partner;
		// 随机字符串
		String nonce_str = StrUtil.getNonceStr();

		// 商品描述根据情况修改
		String body = payInputView.getBody();

		// 商户订单号
		String out_trade_no = orderId;

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", totalFee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openId);
		String sign = PayCommonUtil.createSign("UTF-8", packageParams);
		packageParams.put("sign", sign);
		
		String requestXML = PayCommonUtil.getRequestXml(packageParams);
		logger.info("requestXML：" + requestXML);
		Map map = XMLUtil.doXMLParse(HttpConnect.httpsRequestStr(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML));

		String prepay_id = map.get("prepay_id").toString();
		logger.info("获取到的预支付ID：" + prepay_id);
		
		//获取prepay_id后，拼接最后请求支付所需要的package
		
		SortedMap<Object, Object> finalpackage = new TreeMap<Object, Object>();
		String timestamp = SignUtil.getTimeStamp();
		String packages = "prepay_id="+prepay_id;
		finalpackage.put("appId", appid);  
		finalpackage.put("timeStamp", timestamp);  
		finalpackage.put("nonceStr", nonce_str);  
		finalpackage.put("package", packages);  
		finalpackage.put("signType", "MD5");
		//要签名
		String finalsign = PayCommonUtil.createSign("UTF-8", finalpackage);
		
		String finaPackage = "\"appId\":\"" + appid + "\",\"timeStamp\":\"" + timestamp
		+ "\",\"nonceStr\":\"" + nonce_str + "\",\"package\":\""
		+ packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
		+ finalsign + "\"";

		logger.info("V3 jsApi package:"+finaPackage);
		model.addAttribute("package", finaPackage);
		return "";
	}
	
	//微信支付异步通知接口
	@RequestMapping("asynNotify")
    public void asynNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		response.setContentType("text/xml");
		String msgxml = this.getStreamResult(request, response);//xml数据
        Map map = XMLUtil.doXMLParse(msgxml);
        
        String result_code=(String) map.get("result_code");
        String out_trade_no  = (String) map.get("out_trade_no");
        String total_fee  = (String) map.get("total_fee");
        String sign  = (String) map.get("sign");
        Double amount = new Double(total_fee) / 100;//获取订单金额
        if(result_code.equals("SUCCESS")){
            //验证签名
            String fee_type  = (String) map.get("fee_type");
            String bank_type  = (String) map.get("bank_type");
            String cash_fee  = (String) map.get("cash_fee");
            String is_subscribe  = (String) map.get("is_subscribe");
            String nonce_str  = (String) map.get("nonce_str");
            String openid  = (String) map.get("openid");
            String return_code  = (String) map.get("return_code");
            String sub_mch_id  = (String) map.get("sub_mch_id");
            String time_end  = (String) map.get("time_end");
            String trade_type  = (String) map.get("trade_type");
            String transaction_id  = (String) map.get("transaction_id");
            String attach  = (String) map.get("attach");
            
            //需要对以下字段进行签名
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            packageParams.put("appid", appid);
            packageParams.put("bank_type", bank_type);
            packageParams.put("cash_fee", cash_fee);
            packageParams.put("fee_type", fee_type);
            packageParams.put("is_subscribe", is_subscribe);
            packageParams.put("mch_id", partner);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("openid", openid);
            packageParams.put("out_trade_no", out_trade_no);
            packageParams.put("result_code", result_code);
            packageParams.put("return_code", return_code);
            packageParams.put("sub_mch_id", sub_mch_id);
            packageParams.put("time_end", time_end); 
            packageParams.put("total_fee", total_fee); //用自己系统的数据：订单金额
            packageParams.put("trade_type", trade_type);
            packageParams.put("transaction_id", transaction_id);
            packageParams.put("attach", attach);

            String endsign = PayCommonUtil.createSign("UTF-8", packageParams);
            if(sign.equals(endsign)){//验证签名是否正确  官方签名工具地址：https://pay.weixin.qq.com/wiki/tools/signverify/
                //处理自己的业务逻辑
            	logger.info("支付成功!");
            	//response.getWriter().write(setXML("SUCCESS", "OK"));    //告诉微信已经收到通知了
            	BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                out.write(setXML("SUCCESS", "OK").getBytes());
                out.flush();
                out.close();
            }else{
            	logger.info("sign===>" + sign);
            	logger.info("endsign===>" + endsign);
            	logger.info("验证签名不正确");
            }
        }else{
        	response.getWriter().write(setXML("FAIL", "报文为空")); 
		}
    }
	
	public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }

}
