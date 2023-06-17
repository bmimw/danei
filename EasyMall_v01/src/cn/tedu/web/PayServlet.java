package cn.tedu.web;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.alipay.config.AlipayConfig;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
 
public class PayServlet extends HttpServlet {
 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//初始化
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,
															AlipayConfig.APPID,
															AlipayConfig.RSA_PRIVATE_KEY,
															AlipayConfig.FORMAT,
															AlipayConfig.CHARSET,
															AlipayConfig.ALIPAY_PUBLIC_KEY,
															AlipayConfig.SIGNTYPE);
	        //创建API对应的request
	        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
	        //在公共参数中设置回跳和通知地址
	        alipayRequest.setReturnUrl(AlipayConfig.return_url);
	        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
	        
	        //填充业务参数
	        
	        //必填
	        //商户订单号，需保证在商户端不重复
	        String out_trade_no = "8468411564";
	        //销售产品码，与支付宝签约的产品码名称。目前仅支持FAST_INSTANT_TRADE_PAY 
	        String product_code = "FAST_INSTANT_TRADE_PAY";
			int number = Integer.parseInt(request.getParameter("quantity"));
			int price =  Integer.parseInt(request.getParameter("hiddenPrice"));
			String num =String.valueOf(number*price);
	        //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]。
	        String total_amount = num;
	        //订单标题
	        String subject = "支付宝测试";
	        
	        //选填
	        //商品描述，可空
	        String body = "商品描述";
	        
	        alipayRequest.setBizContent("{" +
	                "\"out_trade_no\":\"" + out_trade_no + "\"," +
	                "\"product_code\":\"" + product_code + "\"," +
	                "\"total_amount\":\"" + total_amount + "\"," +
	                "\"subject\":\"" + subject + "\"," +
	                "\"body\":\"" + body + "\"}" );
	        //请求
	        String form = "";
			try {
				form = alipayClient.pageExecute(alipayRequest).getBody();//调用SDK生成表单
			} catch (AlipayApiException e) {
				e.printStackTrace();
				response.getWriter().write("捕获异常出错");
		        response.getWriter().flush();
		        response.getWriter().close();
			}
			response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
	        response.getWriter().write(form);//直接将完整的表单html输出到页面
	        response.getWriter().flush();
	        response.getWriter().close();
 
	}
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
 
}