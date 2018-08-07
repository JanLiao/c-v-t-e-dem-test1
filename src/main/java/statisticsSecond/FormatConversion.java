package statisticsSecond;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.*;
public class FormatConversion {
	public static final String JPG = "jpg";
	public static final String GIF = "gif";
	public static final String PNG = "png";
	public static final String BMP = "bmp";
	public static void main(String[] args) {
		String src = "E:\\2.";
		new FormatConversion().Conversion(JPG,PNG,src);//JPG转成PNG
		new FormatConversion().Conversion(JPG,GIF,src);//JPG转成GIF
		new FormatConversion().Conversion(JPG,BMP,src);//JPG转成BMP
		//其余格式转化只要调用Conversion函数即可
	}
	
	//inputFormat表示原格式，outputFormat表示转化后的格式
	public  void Conversion(String inputFormat,String outputFormat,String src){
		
		try {
			File input = new File(src+inputFormat);
			BufferedImage bim = ImageIO.read(input);
			File output = new File(src+outputFormat);
			ImageIO.write(bim, outputFormat, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
