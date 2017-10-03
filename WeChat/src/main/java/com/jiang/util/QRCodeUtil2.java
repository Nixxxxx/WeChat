package com.jiang.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * 二维码生成和读取
 * @author JH
 *
 */
public class QRCodeUtil2 {
	
	/**
	 * 生成二维码
	 * @param version 版本号
	 * @param content 二维码内容
	 * @param path 路径
	 * @throws IOException
	 */
	public static void createQRCode(int version, String content, String path) throws IOException{
		
		Qrcode x = new Qrcode();
		x.setQrcodeErrorCorrect('M');  //纠错等级
		x.setQrcodeEncodeMode('B');    //N代表数字，A代表a-Z，B代表其他字符
		x.setQrcodeVersion(version);         //版本
//		String content = "www.imooc.com";  //内容
		int width = 67 + 12 * (version-1);   //7-1  版本减一
		int height = 67 + 12 * (version-1);
		
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D gs = bufferedImage.createGraphics();
		
		gs.setBackground(Color.WHITE);   //设置颜色
		gs.setColor(Color.BLACK);
		gs.clearRect(0, 0, width, height);
		
		int pixoff = 2;   //偏移量
		
		byte[] d = content.getBytes("gb2312");
		if(d.length > 0 && d.length < 120){
			boolean[][] s = x.calQrcode(d);
			
			for(int i = 0;i < s.length;i++){
				for(int j = 0;j < s.length;j++){
					if(s[j][i]){
						gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);
					}
				}
			}
		}
		
		gs.dispose();
		bufferedImage.flush();
		
		ImageIO.write(bufferedImage, "png", new File(path));  //后缀名、文件路径    "E:/qr.png"
	}
	
	/**
	 * 读取二维码
	 * @throws IOException
	 */
	public static void readQRCode(String path) throws IOException{
		
		File file = new File(path);   //文件路径    "E:/qr.png"
		BufferedImage bufferedImage = ImageIO.read(file);
		QRCodeDecoder codeDecoder = new QRCodeDecoder();
		String result = new String(codeDecoder.decode(new QRCodeImage(bufferedImage)),"gb2312");
		System.out.println(result);
	}
}
