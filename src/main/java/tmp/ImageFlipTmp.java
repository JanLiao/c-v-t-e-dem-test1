package tmp;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

/** 
* @author: jan 
* @date: 2018骞�6鏈�15鏃� 涓嬪崍10:23:45 
*/
public class ImageFlipTmp {

	private static BufferedImage flipImage(BufferedImage bufferedimage) {
		int imageWidth = bufferedimage.getWidth();
		int imageHeight = bufferedimage.getHeight();

		BufferedImage img = null;
		Graphics2D graphics2d = null;

		try {
		(graphics2d = (img = new BufferedImage(imageWidth, imageHeight, bufferedimage
		.getColorModel().getTransparency())).createGraphics())
		.drawImage(bufferedimage, 0, 0, imageWidth, imageHeight, imageWidth, 0, 0, imageHeight, null);
		} catch (Exception e) {
		throw e;
		} finally {
		graphics2d.dispose();
		}

		return img;
		}
	
	public static void main(String[] args) throws IOException {
//		File f = new File("E:\\BaiduNetdiskDownload\\鐪煎簳鐓\323\\R.jpg");
//		BufferedImage imgIO = null;
//		try {
//			imgIO = ImageIO.read(f);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		BufferedImage img = flipImage(imgIO);
//		
//		String savePath = "C:/Users/CVTE/Desktop/002.jpg";
//		FileOutputStream fos = null;
//		try {
//			fos = new FileOutputStream(new File(savePath));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		ImageIO.write(img, "jpg", fos);
//		fos.flush();
//		fos.close();
				
		int n = 1;
		DecimalFormat df = new DecimalFormat("0000");
		String s = df.format(n);
		System.out.println(s);
		
		//闈掑厜鐪�
		String glaucoma = "E:/绗竴鎵�/闈掑厜鐪煎浘鐗�";
		String glaucomapath = "E:/绗竴鎵瑰鐞�/闈掑厜鐪煎浘鐗�";
		int num1 = 1;
		File[] files = new File(glaucoma).listFiles();
		//System.out.println(files.length);
		for(File tmp : files) {
			if(tmp.isDirectory()) {
				File[] fs = new File(glaucoma + "/" + tmp.getName()).listFiles();
				//System.out.println("tmp len = " + fs.length);
				for(File tp : fs) {
					//System.out.println(tp.getName());
					if(tp.getName().equals("R.jpg") || tp.getName().equals("RR.jpg")) {  //鍙崇溂缈昏浆  杈逛笂娑傛帀
						//娑傛帀
						//ImageCut.imgCut(ImageIO.read(new File("C:/Users/CVTE/Desktop/002.jpg")), glaucomapath + "/g" + df.format(num1) + ".jpg");
						String path  = glaucoma + "/" + tmp.getName() + "/" + tp.getName();
						//BufferedImage tmpIO = ImageIO.read(new File("C:/Users/CVTE/Desktop/002.jpg"));
						//tmpIO = ImageIO.read(new File(path));
						BufferedImage iIO = 
								ImageCut.imgCut(ImageIO.read(new File(path)), glaucomapath + "/g" + df.format(num1) + ".jpg");
						//缈昏浆
						BufferedImage tpimg = flipImage(iIO);
						//鐢熸垚鍥剧墖
						//writePic(tpimg, glaucomapath + "/g" + df.format(num1) + ".jpg");
						System.out.println("orign path = " + path + "=" + num1);
						//writePic(tpimg, "E:\\绗竴鎵瑰鐞哱\闈掑厜鐪煎浘鐗嘰\g" + df.format(num1) + ".jpg");
						RepaintUtil.writePic(tpimg, "E:\\绗竴鎵瑰鐞�2\\闈掑厜鐪煎浘鐗嘰\\" + "g" + df.format(num1) + ".jpg");
						num1++;
						//System.gc();
					}
					else{  //宸︾溂娑傛帀
						String path  = glaucoma + "/" + tmp.getName() + "/" + tp.getName();
						System.out.println("orign path = " + path + "=" + num1);
						//娑傛帀
						//BufferedImage tmpIO1 = ImageIO.read(new File("C:/Users/CVTE/Desktop/002.jpg"));
						//tmpIO1 = ImageIO.read(new File(path));
						BufferedImage iIO = ImageCut.imgCut(ImageIO.read(new File(path)), glaucomapath + "/g" + df.format(num1) + ".jpg");
						//writePic(iIO, "E:\\绗竴鎵瑰鐞哱\闈掑厜鐪煎浘鐗嘰\g" + df.format(num1) + ".jpg");
						RepaintUtil.writePic(iIO, "E:\\绗竴鎵瑰鐞�2\\闈掑厜鐪煎浘鐗嘰\\g" + df.format(num1) + ".jpg");
						num1++;
						//System.gc();
					}
				}
			}
		}
		
		//闈為潚鍏夌溂
		String norm = "E:/绗竴鎵�/闈為潚鍏夌溂鍥剧墖";
		String normpath = "E:/绗竴鎵瑰鐞�/闈為潚鍏夌溂鍥剧墖";
		int num2 = 1;
		File[] files1 = new File(norm).listFiles();
		for (File tmp : files1) {
			if (tmp.isDirectory()) {
				File[] fs = new File(norm + "/" + tmp.getName()).listFiles();
				for (File tp : fs) {
					if (tp.getName().equals("R.jpg") || tp.getName().equals("RR.jpg")) { // 鍙崇溂缈昏浆 杈逛笂娑傛帀
						// 娑傛帀
						// ImageCut.imgCut(ImageIO.read(new File("C:/Users/CVTE/Desktop/002.jpg")),
						// glaucomapath + "/g" + df.format(num1) + ".jpg");
						String path = norm + "/" + tmp.getName() + "/" + tp.getName();
						BufferedImage iIO = ImageCut.imgCut(ImageIO.read(new File(path)),
								normpath + "/n" + df.format(num1) + ".jpg");
						// 缈昏浆
						BufferedImage tpimg = flipImage(iIO);
						// 鐢熸垚鍥剧墖
						// writePic(tpimg, glaucomapath + "/g" + df.format(num1) + ".jpg");
						System.out.println("orign path = " + path + "=" + num2);
						//writePic(tpimg, "E:\\绗竴鎵瑰鐞哱\闈為潚鍏夌溂鍥剧墖\\n" + df.format(num2) + ".jpg");
						RepaintUtil.writePic(tpimg, "E:\\绗竴鎵瑰鐞�2\\闈為潚鍏夌溂鍥剧墖\\n" + df.format(num2) + ".jpg");
						num2++;
					} else { // 宸︾溂娑傛帀
						String path = norm + "/" + tmp.getName() + "/" + tp.getName();
						System.out.println("orign path = " + path + "=" + num2);
						// 娑傛帀
						BufferedImage iIO = ImageCut.imgCut(ImageIO.read(new File(path)),
								glaucomapath + "/n" + df.format(num2) + ".jpg");
						//writePic(iIO, "E:\\绗竴鎵瑰鐞哱\闈為潚鍏夌溂鍥剧墖\\n" + df.format(num2) + ".jpg");
						RepaintUtil.writePic(iIO, "E:\\绗竴鎵瑰鐞�2\\闈為潚鍏夌溂鍥剧墖\\n" + df.format(num2) + ".jpg");
						num2++;
					}
				}
			}
		}
	}

	private static void writePic(BufferedImage tpimg, String savePath) throws IOException {
		//String savePath = "C:/Users/CVTE/Desktop/002.jpg";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(savePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageIO.write(tpimg, "jpg", fos);
		fos.flush();
		fos.close();
	}
	
}
