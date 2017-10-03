package com.jiang.util;

public class StringUtil {

	public static boolean isEmpty(String str){
		if(str==null||(str.trim()).equals("")) 
			return true;
		else return false;
	}
}
