package statistics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

/**
 * @author: jan
 * @date: 2018年6月20日 下午10:21:14
 */
public class ImageToBMP {

	public static void main(String[] args) throws IOException {
//		System.out.println(888);
		String path = "C:/Users/CVTE/Desktop/img_cut/";
//		BufferedImage imgIO = ImageIO.read(new File(path + "48388_L_mask.jpg"));
//		int width = imgIO.getWidth();
//		int height = imgIO.getHeight();
//		BufferedImage img_1bit = new BufferedImage(width, height,BufferedImage.TYPE_BYTE_BINARY);  //默认1bit
//		Graphics g = img_1bit.getGraphics();  
//		g.drawImage(imgIO, 0, 0, null);  
//		g.dispose();
//	    ImageIO.write(img_1bit,"BMP", new File(path + "test.bmp"));//保存
		
//		String input2 = "d:/img/a.tif";  
//        String output2 = "d:/img/a.jpg";  
//        RenderedOp src2 = JAI.create("fileload", input2);  
//        OutputStream os2 = new FileOutputStream(output2);  
//        JPEGEncodeParam param2 = new JPEGEncodeParam();  
//        //指定格式类型，jpg 属于 JPEG 类型  
//        ImageEncoder enc2 = ImageCodec.createImageEncoder("JPEG", os2, param2);  
//        enc2.encode(src2);  
//        os2.close();  
          
          
        /*tif转换到bmp格式*/  
//        String inputFile = "d:/img/b.tif";  
//        String outputFile = "d:/img/b.bmp";  
//        RenderedOp src = JAI.create("fileload", inputFile);  
//        OutputStream os = new FileOutputStream(outputFile);  
//        BMPEncodeParam param = new BMPEncodeParam();  
//        ImageEncoder enc = ImageCodec.createImageEncoder("BMP", os,param);  
//        enc.encode(src);  
//        os.close();//关闭流
		
		FileImageInputStream fiis=new FileImageInputStream(new File(path + "48388_L_mask.jpg"));
        FileImageOutputStream fios=new FileImageOutputStream(new File(path + "004.bmp"));
         
        ImageReader jpegReader = null;
        Iterator<ImageReader> it1 = ImageIO.getImageReadersByFormatName("jpg");
        if(it1.hasNext())
        {
              jpegReader = it1.next();                           
        }
        jpegReader.setInput(fiis);
         
          ImageWriter bmpWriter = null;
        Iterator<ImageWriter> it2 = ImageIO.getImageWritersByFormatName("bmp");
        if(it2.hasNext())
       {
             bmpWriter = it2.next();      
       }
          bmpWriter.setOutput(fios);
          BufferedImage br = jpegReader.read(0);
          bmpWriter.write(br);
         fiis.close();
         fios.close();
         System.out.println("Jpeg到bmp图片转换完成.");
	}

}
