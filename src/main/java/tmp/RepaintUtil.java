package tmp;
import java.awt.Color;
import java.awt.Graphics2D;
/** 
* @author: jan 
* @date: 2018年6月5日 上午1:13:19 
*/
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;  
  
public class RepaintUtil {  
  
    public static double contrast = 5.0f; // default value;  
    public static double brightness = 3.5f; // default value;  
      
    public RepaintUtil() {
        // do stuff here if you need......  
    }  
    
    public static void main(String[] args) throws IOException {
    	BufferedImage bi=file2img("C:\\Users\\CVTE\\Desktop\\img_cut\\R.jpg");  //读取图片
    	BufferedImage dest = filter(bi);
    	img2file(bi,"jpg","C:\\Users\\CVTE\\Desktop\\img_cut\\test2.jpg");  //生成图片
    	repaint();
    	//repaint1();
    }
    
    public static void repaint1() throws IOException {
    	BufferedImage src=ImageIO.read(new FileImageInputStream(new File("C:/Users/CVTE/Desktop/img_cut/R.jpg")));
    	Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");  
    	if (iter.hasNext()) {  
    	    ImageWriter writer = iter.next();  
    	    ImageWriteParam param = writer.getDefaultWriteParam();  
    	  
    	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);  
    	    //param.setCompressionQuality(1f);  
    	    FileImageOutputStream out = new FileImageOutputStream(new File(  
    	            "C:/Users/CVTE/Desktop/img_cut/test5.jpg"));  
    	    writer.setOutput(out);  
    	    // writer.write(bi);  
    	    writer.write(null, new IIOImage(src, null, null), param);  
    	    out.close();  
    	    writer.dispose();  
    	}
    }
    
    public static void writePic(BufferedImage src, String savePath) throws FileNotFoundException, IOException {
    	Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");  
    	if (iter.hasNext()) {  
    	    ImageWriter writer = iter.next();  
    	    ImageWriteParam param = writer.getDefaultWriteParam();  
    	  
    	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);  
    	    param.setCompressionQuality(1f);  
    	    FileImageOutputStream out = new FileImageOutputStream(new File(  
    	    		savePath));  
    	    writer.setOutput(out);  
    	    // writer.write(bi);  
    	    writer.write(null, new IIOImage(src, null, null), param);  
    	    out.close();  
    	    writer.dispose();  
    	}
    }
    
    public static void repaint() throws IOException {
    	BufferedImage src=ImageIO.read(new FileImageInputStream(new File("C:/Users/CVTE/Desktop/img_cut/R.jpg")));
    	Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");  
    	if (iter.hasNext()) {  
    	    ImageWriter writer = iter.next();  
    	    ImageWriteParam param = writer.getDefaultWriteParam();  
    	  
    	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);  
    	    param.setCompressionQuality(1f);  
    	    FileImageOutputStream out = new FileImageOutputStream(new File(  
    	            "C:/Users/CVTE/Desktop/img_cut/test4.jpg"));  
    	    writer.setOutput(out);  
    	    // writer.write(bi);  
    	    writer.write(null, new IIOImage(src, null, null), param);  
    	    out.close();  
    	    writer.dispose();  
    	}
    	//ImageIO.write(src,"jpg",new File("C:/Users/CVTE/Desktop/img_cut/test4.jpg"));//写出文件
    }
    
  //读取图片
    public static BufferedImage file2img(String imgpath) {
        try {
            BufferedImage bufferedImage=ImageIO.read(new File(imgpath));
            return bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
      
    public static BufferedImage filter(BufferedImage src) {  
        int width = src.getWidth();  
        int height = src.getHeight();
        BufferedImage dest=new BufferedImage(src.getWidth(), src.getHeight(),BufferedImage.TYPE_INT_RGB);
        int[] inPixels = new int[width*height];  
        int[] outPixels = new int[width*height];  
        src.getRGB( 0, 0, width, height, inPixels, 0, width );  
          
        // calculate RED, GREEN, BLUE means of pixel  
        int index = 0;  
        for(int row=0; row<height; row++) {  
            int ta = 0, tr = 0, tg = 0, tb = 0;  
            for(int col=0; col<width; col++) {  
                index = row * width + col;  
                ta = (inPixels[index] >> 24) & 0xff;  
                tr = (inPixels[index] >> 16) & 0xff;  
                tg = (inPixels[index] >> 8) & 0xff;  
                tb = inPixels[index] & 0xff;   
            }  
        }  
        dest.setRGB(0, 0, width, height, outPixels, 0, width);
        return dest;  
    }
      
    public static int clamp(int value) {  
        return value > 255 ? 255 :(value < 0 ? 0 : value);  
    }  
  
    public static double getContrast() {  
        return contrast;  
    }  
  
    public void setContrast(float contrast) {  
        this.contrast = contrast;  
    }  
  
    public static double getBrightness() {  
        return brightness;  
    }  
  
    public void setBrightness(float brightness) {  
        this.brightness = brightness;  
    }  
    
  //保存图片,extent为格式，"jpg"、"png"等
    public static void img2file(BufferedImage img,String extent,String newfile) {
        try {
            ImageIO.write(img, extent, new File(newfile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}  

