package statistics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;

/** 
* @author: jan 
* @date: 2018年6月20日 下午5:09:36 
*/
public class SaveBMP {

	public static void imgAvg(String name) throws IOException {
        BufferedImage imgIO = ImageIO.read(new File("E:\\BaiduNetdiskDownload\\tmp\\test.bmp"));
		
		int width = imgIO.getWidth();
		int height = imgIO.getHeight();
		
		int num = 1;
		
		int num1 = 1;
		
		Mask mask = Constant.mask;
		int[][] panMask = mask.getCurPanMask();
		int[][] beiMask = mask.getCurBeiMask();
		double ratio = 2124/530;
		//System.out.println(size + "=" + size1);
		//System.out.println(percent + "=" + percent1);
		System.out.println(num + "=" + num1);
		int[][] tmp = new int[2124][2056];
		for(int i = 0; i < 529; i++) {
			for(int j = 0; j < 529; j++) {
				int relativeX = (int) ((Constant.panAvg.getCenterX() - 53 + 0.2*i)*ratio);
		    	int relativeY = (int) ((Constant.panAvg.getCenterY() + 53 - 0.2*j)*ratio);
		    	//tmp[relativeX][relativeY] = 1;
				if(panMask[i][j] >= num) {
					tmp[relativeX][relativeY] += 1;
				}
			}
		}
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				//imgIO.setRGB(i, j, 128);
				imgIO.setRGB(i, j, 0x808080);
				if(tmp[i][j] == 0) {
					imgIO.setRGB(i, j, 0xffffff);
					//imgIO.setRGB(i, j, 255);
				}
//				else if(tmp[i][j] == 1) {
//					System.out.println("sdf = " + tmp[i][j]);
//					imgIO.setRGB(i, j, 0x808080);
//				}
//				else {
//					//imgIO.setRGB(i, j, 0x000000);
//				}
			}
		}
		
		for(int i = 0; i < 529; i++) {
			for(int j = 0; j < 529; j++) {
				int relativeX = (int) ((Constant.beiAvg.getCenterX() - 53 + 0.2*i)*ratio);
				int relativeY = (int) ((Constant.beiAvg.getCenterY() + 53 - 0.2*j)*ratio);
				//tmp[relativeX][relativeY] = 1;
				if(beiMask[i][j] >= num1) {
					imgIO.setRGB(relativeX, relativeY, 0x000000);
					//imgIO.setRGB(relativeX, relativeY, 0);
				}
			}
		}
		
		//BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		//buf.createGraphics().drawImage(img, 0, 0, null);
		
		//File fileA = new File("C:\\Users\\CVTE\\Desktop\\HP打印\\sole" + "/" + name + ".bmp");
		File fileA = new File("C:/Users/CVTE/Desktop/49101_L/" + name + ".bmp");
		try {
			ImageIO.write(imgIO, "BMP", fileA);
		} catch (Exception s) {
		}
	}

	public static void imgMask(String name) throws IOException {
		String[] str = name.split("_");
		int[] rgb = new int[3];
		BufferedImage imgIO = ImageIO.read(new File("E:\\BaiduNetdiskDownload\\tmp\\test.bmp"));
		
		int width = imgIO.getWidth();
		int height = imgIO.getHeight();
		
		double percent = Constant.panValue;
		int size = Constant.PanUser.split(",").length;
		int num = (int) (size*percent/100);
		if(num == 0) {
			num = 1;
		}
		
		double percent1 = Constant.beiValue;
		int size1 = Constant.BeiUser.split(",").length;
		int num1 = (int) (size1*percent1/100);
		if(num1 == 0) {
			num1 = 1;
		}
		
		Mask mask = Constant.mask;
		int[][] panMask = mask.getCurPanMask();
		int[][] beiMask = mask.getCurBeiMask();
		double ratio = 2124/530;
		//System.out.println(size + "=" + size1);
		//System.out.println(percent + "=" + percent1);
		System.out.println(num + "=" + num1);
		int[][] tmp = new int[2124][2056];
		for(int i = 0; i < 529; i++) {
			for(int j = 0; j < 529; j++) {
				int relativeX = (int) ((Constant.panAvg.getCenterX() - 53 + 0.2*i)*ratio);
		    	int relativeY = (int) ((Constant.panAvg.getCenterY() + 53 - 0.2*j)*ratio);
		    	//tmp[relativeX][relativeY] = 1;
				if(panMask[i][j] >= num) {
					tmp[relativeX][relativeY] += 1;
				}
			}
		}
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				//imgIO.setRGB(i, j, 128);
				imgIO.setRGB(i, j, 0x808080);
				if(tmp[i][j] == 0) {
					imgIO.setRGB(i, j, 0xffffff);
					//imgIO.setRGB(i, j, 255);
				}
//				else if(tmp[i][j] == 1) {
//					System.out.println("sdf = " + tmp[i][j]);
//					imgIO.setRGB(i, j, 0x808080);
//				}
//				else {
//					//imgIO.setRGB(i, j, 0x000000);
//				}
			}
		}
		
		for(int i = 0; i < 529; i++) {
			for(int j = 0; j < 529; j++) {
				int relativeX = (int) ((Constant.beiAvg.getCenterX() - 53 + 0.2*i)*ratio);
				int relativeY = (int) ((Constant.beiAvg.getCenterY() + 53 - 0.2*j)*ratio);
				//tmp[relativeX][relativeY] = 1;
				if(beiMask[i][j] >= num1) {
					imgIO.setRGB(relativeX, relativeY, 0x000000);
					//imgIO.setRGB(relativeX, relativeY, 0);
				}
			}
		}
		
		//BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		//buf.createGraphics().drawImage(img, 0, 0, null);
		
		File fileA = new File("C:\\Users\\CVTE\\Desktop\\HP打印\\sole" + "/" + name + ".bmp");
		//File fileA = new File(Constant.statpath + "/" + name + ".bmp");
		try {
			ImageIO.write(imgIO, "BMP", fileA);
		} catch (Exception s) {
		}
	}

	public static void imgMaskTmp(String name) throws IOException {
        BufferedImage imgIO = ImageIO.read(new File("E:\\BaiduNetdiskDownload\\tmp\\test.bmp"));
		
		int width = imgIO.getWidth();
		int height = imgIO.getHeight();
		
		int num = 4;
		
		int num1 = 4;
		
		Mask mask = Constant.mask;
		int[][] panMask = mask.getCurPanMask();
		int[][] beiMask = mask.getCurBeiMask();
		double ratio = 2124/530;
		//System.out.println(size + "=" + size1);
		//System.out.println(percent + "=" + percent1);
		System.out.println(num + "=" + num1);
		int[][] tmp = new int[2124][2056];
		for(int i = 0; i < 529; i++) {
			for(int j = 0; j < 529; j++) {
				int relativeX = (int) ((Constant.panAvg.getCenterX() - 53 + 0.2*i)*ratio);
		    	int relativeY = (int) ((Constant.panAvg.getCenterY() + 53 - 0.2*j)*ratio);
		    	//tmp[relativeX][relativeY] = 1;
				if(panMask[i][j] >= num) {
					tmp[relativeX][relativeY] += 1;
				}
			}
		}
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				//imgIO.setRGB(i, j, 128);
				imgIO.setRGB(i, j, 0x808080);
				if(tmp[i][j] == 0) {
					imgIO.setRGB(i, j, 0xffffff);
					//imgIO.setRGB(i, j, 255);
				}
//				else if(tmp[i][j] == 1) {
//					System.out.println("sdf = " + tmp[i][j]);
//					imgIO.setRGB(i, j, 0x808080);
//				}
//				else {
//					//imgIO.setRGB(i, j, 0x000000);
//				}
			}
		}
		
		for(int i = 0; i < 529; i++) {
			for(int j = 0; j < 529; j++) {
				int relativeX = (int) ((Constant.beiAvg.getCenterX() - 53 + 0.2*i)*ratio);
				int relativeY = (int) ((Constant.beiAvg.getCenterY() + 53 - 0.2*j)*ratio);
				//tmp[relativeX][relativeY] = 1;
				if(beiMask[i][j] >= num1) {
					imgIO.setRGB(relativeX, relativeY, 0x000000);
					//imgIO.setRGB(relativeX, relativeY, 0);
				}
			}
		}
		
		//BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		//buf.createGraphics().drawImage(img, 0, 0, null);
		
		//File fileA = new File("C:\\Users\\CVTE\\Desktop\\HP打印\\sole" + "/" + name + ".bmp");
		//File fileA = new File("C:/Users/CVTE/Desktop/49101_L/kkk/" + name + ".bmp");
		File fileA = new File("C:/Users/CVTE/Desktop/HP打印/sole/tm/" + name + ".bmp");
		try {
			ImageIO.write(imgIO, "BMP", fileA);
		} catch (Exception s) {
		}
	}

}
