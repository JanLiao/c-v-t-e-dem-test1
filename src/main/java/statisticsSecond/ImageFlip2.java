package statisticsSecond;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.imageio.ImageIO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/** 
* @author: jan 
* @date: 2018年6月15日 下午10:23:45 
*/
public class ImageFlip2 {

	public static BufferedImage flipImage(BufferedImage bufferedimage) {
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
	
	public static BufferedImage imgCut1(BufferedImage imgIO) throws IOException {
		int radius = 783;
		
		int[] rgb = new int[3];
		int width = imgIO.getWidth();
		int height = imgIO.getHeight();
		int x = width/2;
		int y = height/2;
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
	}
	
	public static void thirdProcess() throws IOException {
		//翻转
		BufferedImage tpimg = flipImage(ImageIO.read(new File("C:\\Users\\CVTE\\Desktop\\HP打印\\sole\\22343_R.bmp")));
		
		File fileA = new File("C:/Users/CVTE/Desktop/HP打印/sole/22343.bmp");
		try {
			ImageIO.write(tpimg, "BMP", fileA);
		} catch (Exception s) {
		}
	}
	
	public static void main(String[] args) throws IOException {
//		File f = new File("E:\\BaiduNetdiskDownload\\眼底照\\323\\R.jpg");
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
		
		
				
		//firstProcess();
		//secondProcess();
		thirdProcess();
		
	}
	public static boolean strToObj(String label, String name) {
		JSONObject json = JSON.parseObject(label);
		JSONArray arr = json.getJSONArray("label_data");
		JSONObject obj = null;
		for (int i = 0; i < 3; i++) {
			JSONObject tmp = arr.getJSONObject(i);
			if (name.equals(tmp.getString("name"))) {
				obj = JSON.parseObject(tmp.getString("data"));
				break;
			}
		}
		
		double s = Double.parseDouble(obj.getString("left"));
		if(s > 265) {
			return true;
		}else {
			return false;
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
