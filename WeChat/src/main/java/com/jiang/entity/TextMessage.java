package com.jiang.entity;

/**
 * 文本消息
 * @author JH
 *
 */
public class TextMessage extends BaseMessage{
	private String Content;		//消息内容
	private String MsgId;		//消息id
	
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
}
