package tmp;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.io.FileUtils;

/** 
* @author: jan 
* @date: 2018年6月15日 下午10:23:45 
*/
public class ImageFlip {

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
	
	public static void writePic1(BufferedImage src, String savePath) throws FileNotFoundException, IOException {
    	Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("bmp");  
    	if (iter.hasNext()) {  
    	    ImageWriter writer = iter.next();  
    	    ImageWriteParam param = writer.getDefaultWriteParam();  
    	  
    	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);  
    	    //param.setCompressionQuality(1f);  
    	    FileImageOutputStream out = new FileImageOutputStream(new File(  
    	    		savePath));  
    	    writer.setOutput(out);  
    	    // writer.write(bi);  
    	    writer.write(null, new IIOImage(src, null, null), param);  
    	    out.close();  
    	    writer.dispose();  
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
		flip();
		
	}
	
	public static void flip() throws FileNotFoundException, IOException {
		String path = "E:/BaiduNetdiskDownload/统计平均掩码/Mask掩码图Flip4";
		String src = "E:/BaiduNetdiskDownload/统计平均掩码/Mask掩码图";
		File[] files = new File("E:/BaiduNetdiskDownload/统计平均掩码/Mask掩码图").listFiles();
		for(File f : files) {
			String[] str = f.getName().split("_");
			System.out.println(str[1]);
			if("R.bmp".equals(str[1]) || "RR.bmp".equals(str[1]) || "RRR.bmp".equals(str[1]) || "RRRR.bmp".equals(str[1])) {
				BufferedImage tpimg = flipImage(ImageIO.read(new File(src + "/" + f.getName())));
				writePic1(tpimg, path + "/" + f.getName());
			}
			else {
				File srcFile = new File(src + "/" + f.getName());
				File destFile = new File(path + "/" + f.getName());
				FileUtils.copyFile(srcFile, destFile);
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
		ImageIO.write(tpimg, "bmp", fos);
		fos.flush();
		fos.close();
	}
	
}
