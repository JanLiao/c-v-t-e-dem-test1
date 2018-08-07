package statisticsSecond;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bit24To256 {
	
	private static void Bit24_to_256Index(String src, String dest) throws IOException 
    {  
       // 将图像文件读出，数据保存在Byte数组  

       BufferedImage inputImage = ImageIO.read( new File(src) );  
 
       int width = inputImage.getWidth();  
       int height= inputImage.getHeight();  
        
       ByteArrayOutputStream bos = new ByteArrayOutputStream( width*height*4 + 54);  
               
       try  
       {  
           ImageIO.write( inputImage, "BMP", bos);  
       } catch (IOException e)  
       {  
           e.printStackTrace();  
       }  
       byte[] bSrcfile = bos.toByteArray();  
        
       // 新文件的长度(b)=数据部分+调色板(1024)+位图信息头+位图文件头  
       byte[] bDestfile = new byte[ width*height+1078 ];  
               
       // 开始构造字节数组  
       bDestfile[0] = bSrcfile[0];  // 00H : 42H  
       bDestfile[1] = bSrcfile[1];  // 01H : 4DH  
       // 文件大小(B)  
       int fileLength = width * height + 1078 ;  
       byte[] btLen = int2bytes(fileLength);  
     
       switch( btLen.length )  
       {  
       case 1:  
           bDestfile[2] = btLen[0];  
           break;  
       case 2:  
           bDestfile[3] = btLen[0];  
           bDestfile[2] = btLen[1];  
           break;  
       case 3:  
           bDestfile[4] = btLen[0];  
           bDestfile[3] = btLen[1];  
           bDestfile[2] = btLen[2];  
           break;  
       case 4:  
           bDestfile[5] = btLen[0];  
           bDestfile[4] = btLen[1];  
           bDestfile[3] = btLen[2];  
           bDestfile[2] = btLen[3];  
       }  
        
       // 数据的偏移地址固定为1078(436H)  
       bDestfile[10] = 54;   // 36H  
       bDestfile[11] = 4;    // 04H  
       for( int i = 14; i <= 27; i++ )  
       {  
           bDestfile[i] = bSrcfile[i];    
       }  
       bDestfile[28] = 8;  // 2^8 = 256  
       // 数据大小字段  
       int biSizeImage = width * height;  // 对256色图来讲，数据部分的大小为长*高  
       byte[] btSI = int2bytes(biSizeImage);  
       switch( btSI.length )  
       {  
       case 1:  
           bDestfile[34] = btSI[0];  
           break;  
       case 2:  
           bDestfile[35] = btSI[0];  
           bDestfile[34] = btSI[1];  
           break;  
       case 3:  
           bDestfile[36] = btSI[0];  
           bDestfile[35] = btSI[1];  
           bDestfile[34] = btSI[2];  
           break;  
       case 4:  
           bDestfile[37] = btSI[0];  
           bDestfile[36] = btSI[1];  
           bDestfile[35] = btSI[2];  
           bDestfile[34] = btSI[3];  
       }  
        
       for( int i = 38; i <= 53; i++ )  
       {  
           bDestfile[i] = bSrcfile[i];  
       }  
       byte bRGB = 0;  
       // 写调色板  36H(54) --> 435H(1077)  
       for( int i = 54; i <= 1077; i += 4, bRGB ++ )  
       {  
           bDestfile[i] = bRGB;  
           bDestfile[i+1] = bRGB;  
           bDestfile[i+2] = bRGB;  
           bDestfile[i+3] = 0;   // rgbReserved, 保留值为零  
       }  
               
       // 转换图像数据部分  
       for( int i = 1078, j = 54; i < bDestfile.length; i++, j += 3 )  
       {  
           bDestfile[i] = bSrcfile[j];  
       }  
       BufferedImage outputImage = new BufferedImage( width, height, BufferedImage.TYPE_BYTE_GRAY);  
       ByteArrayInputStream in = new ByteArrayInputStream( bDestfile );    //将b作为输入流；  
       outputImage = ImageIO.read( in );  
  
       try  
       {  
           ImageIO.write( outputImage, "BMP",new   File( dest ));  
       }  
       catch(Exception   ex)  
       {  
          ex.printStackTrace();  
       }  
    }  
	
	public static byte[] int2bytes(int num)  
	{  
		   byte[] b=new byte[4];  
		   int mask=0xff;  
		   for(int i=0;i<4;i++)  
		   {  
		       b[i]=(byte)(num>>>(24-i*8));  
		   }  
		   return b;  
		}
	
	public static void getImg() throws IOException {
		String path = "E:/BaiduNetdiskDownload/统计平均掩码/Mask掩码图Flip1";
		File[] files = new File("E:\\BaiduNetdiskDownload\\统计平均掩码\\Mask掩码图Flip1").listFiles();
		for(File f : files) {
			BufferedImage sourceImg = ImageIO.read(f);  
			  int h = sourceImg.getHeight();
			  int w = sourceImg.getWidth();
			  int[] pixels = new int[w * h]; // 定义一数组，用来存储图片的象素
			  int gray;
			 
			  PixelGrabber pg = new PixelGrabber(sourceImg, 0, 0, w, h, pixels,    0, w);
			  try {
			   pg.grabPixels(); // 读取像素值
			  } catch (InterruptedException e) {
			   System.err.println("处理被异常中断！请重试！");
			  }
			 
			  for (int j = 0; j < h; j++) // 扫描列  {
			   for (int i = 0; i < w; i++) // 扫描行
			   { // 由红，绿，蓝值得到灰度值
			    gray = (int) (((pixels[w * j + i] >> 16) & 0xff));
			    gray += (int) (((pixels[w * j + i] >> 8) & 0xff));
			    gray += (int) (((pixels[w * j + i]) & 0xff));
			    pixels[w * j + i] = (255 << 24) | (gray << 16) | (gray << 8) | gray;
			   }
			  MemoryImageSource s= new  MemoryImageSource(w,h,pixels,0,w);
			  Image img =Toolkit.getDefaultToolkit().createImage(s);
			  BufferedImage buf = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
			  buf.createGraphics().drawImage(img, 0, 0, null);
			  
			  ImageIO.write(buf, "BMP", new File(path + "/" + f.getName()));
		}
		
	}

	public static void main(String[] args) throws IOException {
//		String src = "C:\\Users\\CVTE\\Desktop\\tmp\\5084_L.bmp";
//		String dest = "C:\\Users\\CVTE\\Desktop\\tmp\\tmp.bmp";
//		Bit24_to_256Index(src, dest);
		
		getImg();
	}
	
}
