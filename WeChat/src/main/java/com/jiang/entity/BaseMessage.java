package com.jiang.entity;

/**
 * 消息父类
 * @author JH
 *
 */
public class BaseMessage {
	private String ToUserName;		//接收方微信号
	private String FromUserName;	//发送方微信号
	private String CreateTime;		//创建时间
	private String MsgType;			//消息类型
	
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
}
