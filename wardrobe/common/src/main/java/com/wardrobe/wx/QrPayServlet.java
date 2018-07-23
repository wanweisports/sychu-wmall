package com.wardrobe.wx;

import com.wardrobe.wx.http.HttpConnect;
import com.wardrobe.wx.util.ConfigUtil;
import com.wardrobe.wx.util.PayCommonUtil;
import com.wardrobe.wx.util.QrUtil;
import com.wardrobe.wx.util.XMLUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class QrPayServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(QrPayServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			//商户订单号(唯一订单号后加随机码，不加会出现重复提交订单问题，如：第一个用户扫码输入错误密码，第二个用户扫码会出现[该单已被其他账号发起支付 您无权再发起])
			String out_trade_no = "123456_"+System.currentTimeMillis();//商户网站订单系统中唯一订单号，必填		

			NumberFormat df = new DecimalFormat("###0.00");
			//付款金额(必填)
			int price = (int)1;

			//订单描述
			String body =  "测试body"; 
			
			out_trade_no = out_trade_no + "Test";

	         SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
	         parameters.put("appid", WeiXinConnector.CORP_ID);                    
	         parameters.put("mch_id", WeiXinConnector.MCH_ID);           
	         parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
	         parameters.put("body", body);
	         parameters.put("out_trade_no", out_trade_no);
	         parameters.put("total_fee", String.valueOf(price));                             
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
	            System.out.println("codeUrl===>"+codeUrl);
	            QrUtil.initQr(codeUrl, 300, response);
	            //request.setAttribute("codeUrl", codeUrl);
	         }else{
	        	System.out.println("returnCode===>"+returnCode+", resultCode===>"+resultCode);
	         }
		}catch(Exception ex){
			logger.error("pay insurance order union infomation error!", ex);
			ex.printStackTrace();
		}
		//request.getRequestDispatcher("wxPay.jsp").forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
