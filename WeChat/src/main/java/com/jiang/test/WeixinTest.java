package com.jiang.test;

import com.jiang.menu.Menu;
import com.jiang.po.AccessToken;
import com.jiang.util.WeixinUtil;
import net.sf.json.JSONObject;

public class WeixinTest {
	public static void main(String[] args) {
		try {
			//设置微信公众号菜单
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("票据"+token.getToken());
			System.out.println("有效时间"+token.getExpiresIn());
			Menu menu = WeixinUtil.initMenu();
			WeixinUtil.createMenu(token.getToken(), JSONObject.fromObject(menu).toString());
			
			//String path = "D:/imooc.jpg";
			//String mediaId = WeixinUtil.upload(path, token.getToken(), "thumb");
			//System.out.println(mediaId);
			
//			String result = WeixinUtil.translate("my name is laobi");
			//String result = WeixinUtil.translateFull("");
//			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
