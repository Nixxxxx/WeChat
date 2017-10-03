package com.jiang.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jiang.entity.TextMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {

	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLIKE = "CLIKE";
	public static final String MESSAGE_VIEW = "VIEW";
	
	
	/**
	 * xml转为Map集合
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
	        Map<String,String> map = new HashMap<String, String>();
	        SAXReader saxReader = new SAXReader();
	        
	        InputStream ins  = request.getInputStream();
	        Document document = saxReader.read(ins);
	        Element element = document.getRootElement();
	        @SuppressWarnings("unchecked")
			List<Element> list = element.elements();
	        for (Element key: list) {
	            map.put(key.getName(),key.getText());
	        }
	        ins.close();
	        return map;
	}
	
	/**
	 * 将文本消息对象改为xml
	 * @param textMassage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMassage){
		XStream xstream  =  new XStream();
		xstream.alias("xml", textMassage.getClass());
		return xstream.toXML(textMassage);
	}
	
	public static String initText(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType("text");
		text.setCreateTime(new Date().getTime()+"");
		text.setContent(content);
		return MessageUtil.textMessageToXml(text);
	}
	
	
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("welcome\n\n");
		sb.append("1:introduce\n");
		sb.append("2:hello\n");
		sb.append("?:return this");
		return sb.toString();
	}
	
	public static String firstText(){
		StringBuffer sb = new StringBuffer();
		sb.append("introduce");
		return sb.toString();
	}
}
