package tmp;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BMPG {

	public static void main(String[] args) {
		BufferedImage bi = new BufferedImage(2124,2056,BufferedImage.TYPE_INT_RGB);
		try {
		    ImageIO.write(bi, "BMP", new File("E:\\BaiduNetdiskDownload\\tmp\\test.bmp"));
		    //ImageIO.write(bi, "PNG", new File("C:\\test.png"));
		} catch (IOException e) {
		    System.out.println("error "+e.getMessage());
		}
	}

}
