package com.jiang.util;

import com.jiang.menu.Button;
import com.jiang.menu.ClickButton;
import com.jiang.menu.Menu;
import com.jiang.menu.ViewButton;
import com.jiang.po.AccessToken;
import net.sf.json.JSONObject;
import org.apache.http.ParseException;

import java.io.IOException;

/**
 * 微信菜单相关工具类
 */
public class MenuUtil {

	public static void main(String[] args) {
		try {
			//设置微信公众号菜单
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("票据"+token.getToken());
			System.out.println("有效时间"+token.getExpiresIn());

//			JSONObject queryMenu = MenuUtil.queryMenu(token.getToken());	//微信公众号菜单查询
//			System.out.println(queryMenu);									//微信公众号菜单查询

//			MenuUtil.deleteMenu(token.getToken());	//微信公众号菜单删除

			Menu menu = MenuUtil.initMenu();												//微信公众号菜单创建
			MenuUtil.createMenu(token.getToken(), JSONObject.fromObject(menu).toString());	//微信公众号菜单创建
			
			//String path = "D:/imooc.jpg";
			//String mediaId = WeixinUtil.upload(path, token.getToken(), "thumb");
			//System.out.println(mediaId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	private static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**
	 * 组装菜单
	 * @return menu
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		ClickButton button11 = new ClickButton();
		button11.setName("111");
		button11.setType("click");
		button11.setKey("11");

		ViewButton button21 = new ViewButton();
		button21.setName("换乘查询");
		button21.setType("view");
		button21.setUrl("http://www.hncsmtr.com/mtr-hr/app/common/subway/manage");

		ViewButton button22 = new ViewButton();
		button22.setName("站点及周边信息");
		button22.setType("view");
		button22.setUrl("http://www.hncsmtr.com/mtr-hr/app/common/subway/stationList");

		ViewButton button23 = new ViewButton();
		button23.setName("运营时刻表");
		button23.setType("view");
		button23.setUrl("http://www.hncsmtr.com/yyfw/lxcx/yyskb/");

		ViewButton button24 = new ViewButton();
		button24.setName("换乘查询");
		button24.setType("view");
		button24.setUrl("http://www.hncsmtr.com/yyfw/lxcx/yylxt/index.html");

		Button button2 = new Button();
		button2.setName("乘车指南");
		button2.setSub_button(new Button[]{button21,button22,button23,button24});

		ClickButton button31 = new ClickButton();
		button31.setName("扫码事件");
		button31.setType("scancode_push");
		button31.setKey("31");

		ClickButton button32 = new ClickButton();
		button32.setName("地理位置");
		button32.setType("location_select");
		button32.setKey("32");

		ViewButton button33 = new ViewButton();
		button33.setName("失物招领");
		button33.setType("view");
		button33.setUrl("http://www.hncsmtr.com/yyfw/swzl/index.html");

		ViewButton button34 = new ViewButton();
		button34.setName("文明乘车");
		button34.setType("view");
		button34.setUrl("http://www.hncsmtr.com/yyfw/wmcc/fwfg/index.html");

		Button button3 = new Button();
		button3.setName("菜单");
		button3.setSub_button(new Button[]{button31,button32,button33,button34});

		menu.setButton(new Button[]{button11,button2,button3});
		return menu;
	}

	/**
     * 创建菜单
	 * @param token
     * @param menu
     * @throws ParseException
     * @throws IOException
	 */
	public static int createMenu(String token, String menu) throws ParseException, IOException {
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = WeixinUtil.doPostStr(url, menu);
		if(jsonObject != null){
			result = jsonObject.getInt("errcode");
		}
		return result;
	}

	/**
	 * 查询菜单
	 * @param token
	 * @return jsonObject
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject queryMenu(String token) throws ParseException, IOException{
		String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = WeixinUtil.doGetStr(url);
		return jsonObject;
	}

	/**
     * 删除菜单
	 * @param token
     * @return result
     * @throws ParseException
     * @throws IOException
	 */
	public static int deleteMenu(String token) throws ParseException, IOException{
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = WeixinUtil.doGetStr(url);
		int result = 0;
		if(jsonObject != null){
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
}
