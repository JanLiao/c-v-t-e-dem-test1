package tmp;

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
	
	public static BufferedImage imgCut(BufferedImage imgIO) throws IOException {
		int radius = 1062;
		int k = 0;
		
		int[] rgb = new int[3];
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
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				int len = (x - i)*(x - i) + (y - j)*(y - j);
				if(len > radius*radius) {
					imgIO.setRGB(i, j, 0x000000);
				}
			}
		}
		return imgIO;
		
//		FileOutputStream fos = new FileOutputStream(new File(savePath));
//		ImageIO.write(imgIO, "jpg", fos);
//		fos.flush();
//		fos.close();
	}
	
	public static BufferedImage imgCut(BufferedImage imgIO, String savePath) throws IOException {
		int radius = 1062;
		int k = 0;
		
		int[] rgb = new int[3];
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
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				int len = (x - i)*(x - i) + (y - j)*(y - j);
				if(len > radius*radius) {
					imgIO.setRGB(i, j, 0x000000);
				}
			}
		}
		return imgIO;
		
//		FileOutputStream fos = new FileOutputStream(new File(savePath));
//		ImageIO.write(imgIO, "jpg", fos);
//		fos.flush();
//		fos.close();
	}

	public static void main(String[] args) throws IOException {
		//String path = "C:/Users/CVTE/Desktop/img_cut/";
		String path = "C:/Users/CVTE/Desktop/tmp/";
		int radius = 1062;
		int k = 0;
		File dir = new File(path);
		File[] files = dir.listFiles();
		
		int[] rgb = new int[3];
		BufferedImage imgIO = ImageIO.read(new File(path + "/1025_L.png"));
		
		int width = imgIO.getWidth();
		int height = imgIO.getHeight();
		int minX = imgIO.getMinTileX();
		int minY = imgIO.getMinTileY();
		int x = width/2;
		int y = height/2;
		System.out.println(width + "=" + height + "=" + minX + "=" + minY);
		int offsetX = Integer.MAX_VALUE;
		int offsetY = Integer.MAX_VALUE;
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				int pixel = imgIO.getRGB(i, j);
//				rgb[0] = (pixel & 0xff0000) >> 16;
//				rgb[1] = (pixel & 0xff00) >> 8;
//				rgb[2] = (pixel & 0xff);
				int len = (x - i)*(x - i) + (y - j)*(y - j);
				if(len > radius*radius) {
					imgIO.setRGB(i, j, 0x000000);
				}
			}
		}
		System.out.println(rgb[0] + "=" + rgb[1] + "=" + rgb[2]);
		//System.out.println(offsetX + "=" + offsetY);
		
		String savePath = "C:/Users/CVTE/Desktop/tmp/006.png";
		FileOutputStream fos = new FileOutputStream(new File(savePath));
		ImageIO.write(imgIO, "png", fos);
		fos.flush();
		fos.close();
	}
	
}
