package statistics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/** 
* @author: jan 
* @date: 2018年5月5日 上午9:37:03 
*/
public class ImageCut {
	
	public void imgCut() {
		
	}

	public static void main(String[] args) throws IOException {
		//String path = "C:/Users/CVTE/Desktop/img_cut/";
		String path = "C:/Users/CVTE/Desktop/tmp/";
		int radius = 1062;
		int k = 3;
		File dir = new File(path);
		File[] files = dir.listFiles();
		
		int[] rgb = new int[3];
		BufferedImage imgIO = ImageIO.read(new File(path + "267_L.bmp"));
		
		int width = imgIO.getWidth();
		int height = imgIO.getHeight();
		//int minX = imgIO.getMinTileX();
		//int minY = imgIO.getMinTileY();
		int x = width/2;
		int y = height/2;
		//System.out.println(width + "=" + height + "=" + minX + "=" + minY);
		//int offsetX = Integer.MAX_VALUE;
		//int offsetY = Integer.MAX_VALUE;
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				int pixel = imgIO.getRGB(i, j);
				System.out.println(pixel);
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				int len = (x - i)*(x - i) + (y - j)*(y - j);
				if(len > radius*radius) {
					//System.out.println(len + "=" + radius*radius);
					imgIO.setRGB(i, j, 0x808080);
				}
			}
		}
		System.out.println(rgb[0] + "=" + rgb[1] + "=" + rgb[2]);
		//System.out.println(offsetX + "=" + offsetY);
		
		String savePath = path + "6.jpg";
		FileOutputStream fos = new FileOutputStream(new File(savePath));
		ImageIO.write(imgIO, "jpg", fos);
		fos.flush();
		fos.close();
	}
	
}
