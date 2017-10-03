package com.jiang.util;

import java.awt.image.BufferedImage;

public class QRCodeImage implements QRCodeImage {

	BufferedImage bufferedImage;
	
	public QRCodeImage(BufferedImage bufferedImage){
		this.bufferedImage = bufferedImage;
	}
	
	@Override
	public int getHeight() {
		return bufferedImage.getHeight();
	}

	@Override
	public int getPixel(int x, int y) {
		return bufferedImage.getRGB(x, y);
	}

	@Override
	public int getWidth() {
		return bufferedImage.getWidth();
	}

}
