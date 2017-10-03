package com.jiang.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码生成和读取
 * @author JH
 *
 */
public class QRCodeUtil {

	/**
	 * 生成二维码
	 * @param format 图片格式（png）
	 * @param content 二维码内容
	 * @param path 文件路径
	 * @param width 宽度
	 * @param height 高度
	 */
	public static void createQRCode(String format, String content, String path, int width, int height){
		
		//定义二维码参数
		HashMap hints = new HashMap();
		//设置编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		//设置容错等级，等级越高，容量越小
		hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);
		//设置边距
		hints.put(EncodeHintType.MARGIN,2);
	
		BitMatrix bitmatrix;
		try {
			bitmatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width,height,hints);
			Path file = new File(path).toPath();	//文件路径  "E:/img.png"
			MatrixToImageWriter.writeToPath(bitmatrix, format, file); //format 图片格式（png即可）	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 读取二维码
	 */
	public void readQRCode(String path){
		try{
			MultiFormatReader formatReader = new MultiFormatReader();
			File file = new File(path);  //文件路径  "E:/img.png"
			BufferedImage image = ImageIO.read(file);
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
			
			HashMap hints = new HashMap();						
			hints.put(EncodeHintType.CHARACTER_SET,"utf-8");		//设置支持中文编码
			
			Result result = formatReader.decode(binaryBitmap,hints);
			
			System.out.println("解析结果"+result.toString());
			System.out.println("二维码格式"+result.getBarcodeFormat());
			System.out.println("二维码文本内容"+result.getText());
		}catch(Exception e){
				e.printStackTrace();
			}
		}
	
}
