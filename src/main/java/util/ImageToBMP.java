package util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author: jan
 * @date: 2018年6月20日 下午10:21:14
 */
public class ImageToBMP {

	public static void main(String[] args) throws IOException {
		String path = "C:/Users/CVTE/Desktop/img_cut/";
		BufferedImage imgIO = ImageIO.read(new File(path + "R.jpg"));
		int width = imgIO.getWidth();
		int height = imgIO.getHeight();
		BufferedImage img_1bit = new BufferedImage(width, height,BufferedImage.TYPE_BYTE_BINARY);  //默认1bit
		Graphics g = img_1bit.getGraphics();  
		g.drawImage(imgIO, 0, 0, null);  
		g.dispose();
	    ImageIO.write(img_1bit,"BMP", new File(path + "test.bmp"));//保存
	}

}
