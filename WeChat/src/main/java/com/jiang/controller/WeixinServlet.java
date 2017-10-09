package com.jiang.controller;

import com.jiang.po.MessageMenu;
import com.jiang.util.CheckUtil;
import com.jiang.util.MessageUtil;
import com.jiang.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class WeixinServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 接入验证
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
			if(CheckUtil.checkSignature(signature, timestamp, nonce)){
				out.print(echostr);
		}
	}
	
	
	/**
	 * 消息的接收与响应
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
//		try {
//			Map<String, String> map = MessageUtil.xmlToMap(request);
//			String fromUserName = map.get("FromUserName");
//			String toUserName = map.get("ToUserName");
//			String msgType = map.get("MsgType");
//			String content = map.get("Content");
//
//			String message = null;
//			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
//				if("1".equals(content)){
//					message = MessageUtil.initText(toUserName, fromUserName, MessageMenu.firstMenu());
//				}else if("2".equals(content)){
//					message = MessageUtil.initNewsMessage(toUserName, fromUserName);
//				}else if("3".equals(content)){
//					message = MessageUtil.initText(toUserName, fromUserName, MessageMenu.threeMenu());
//				}else if("?".equals(content) || "？".equals(content)){
//					message = MessageUtil.initText(toUserName, fromUserName, MessageMenu.menuText());
//				}
//			}else if(MessageUtil.MESSAGE_EVNET.equals(msgType)){
//				String eventType = map.get("Event");
//				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
//					message = MessageUtil.initText(toUserName, fromUserName, MessageMenu.menuText());
//				}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
//					message = MessageUtil.initText(toUserName, fromUserName, MessageMenu.menuText());
//				}else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
//					String url = map.get("EventKey");
//					message = MessageUtil.initText(toUserName, fromUserName, url);
//				}else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
//					String key = map.get("EventKey");
//					message = MessageUtil.initText(toUserName, fromUserName, key);
//				}
//			}else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
//				String label = map.get("Label");
//				message = MessageUtil.initText(toUserName, fromUserName, label);
//			}
//
//			System.out.println(message);
//
//			out.print(message);
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}finally{
//			out.close();
//		}

		String code = request.getParameter("code");
		System.out.println(code);
		JSONObject json = WeixinUtil.getOAuthOpenId(code);
		if(json != null){
			System.out.println(json.get("openid"));
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
