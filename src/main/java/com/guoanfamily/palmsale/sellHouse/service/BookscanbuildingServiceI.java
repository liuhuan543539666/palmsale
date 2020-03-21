package com.guoanfamily.palmsale.sellHouse.service;
import com.guoanfamily.palmsale.sellHouse.entity.CustOrder;
import com.guoanfamily.palmsale.sellHouse.mode.PayOrderResult;
import com.guoanfamily.palmsale.sellHouse.repository.CustOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BookscanbuildingServiceI{
	private final CustOrderRepository custOrderRepository;

	@Autowired
	public BookscanbuildingServiceI(CustOrderRepository custOrderRepository) {
		this.custOrderRepository = custOrderRepository;
	}
	/**
	 * 微信支付结果回调接口---新
	 */
	public void doReturnForWeiXin(HttpServletRequest req, HttpServletResponse resp,PayOrderResult result) {
		resp.setContentType("text/xml;charset=utf-8");
		resp.setCharacterEncoding("utf-8");

		String return_code =result.getReturnCode();
		String out_trade_no = result.getOutTradeNo();//订单号
		String result_code = result.getResultCode();
		try {
			doCheckPayResult(out_trade_no,return_code,result_code,req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 客户端支付结果判断---新
	 * @return
	 */
	public void doCheckPayResult(String out_trade_no,String return_code,String result_code,HttpServletRequest req,HttpServletResponse resp) {
		CustOrder order = custOrderRepository.findByHouseresourceid(out_trade_no);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		try {
			if("SUCCESS".equals(return_code)){
				if(result_code.equals("FAIL")){

				}else if(result_code.equals("SUCCESS")) {
					order.setOrderstate("2");
					order.setSigntime(java.sql.Timestamp.valueOf(String.valueOf(df.format(new Date()))));
					order.setWxmsg("支付成功，该房源已锁定！");
				}else {
					order.setWxmsg("该房源暂不可售，详情请联系相关客服人员！");
				}
			} else if("FAIL".equals(return_code)){
				order.setWxmsg("支付失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
}
