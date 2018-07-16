package tmp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;

public class Test {
	private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

}
	
	public static void get() throws IOException {
		BufferedImage bufferedImage 
        = ImageIO.read(new File("E:\\package\\mask\\Glaucoma\\g0001.bmp"));
    BufferedImage grayImage = 
        new BufferedImage(bufferedImage.getWidth(), 
                          bufferedImage.getHeight(), 
                          bufferedImage.getType());
        
    
    for (int i = 0; i < bufferedImage.getWidth(); i++) {
        for (int j = 0; j < bufferedImage.getHeight(); j++) {
            final int color = bufferedImage.getRGB(i, j);
            final int r = (color >> 16) & 0xff;
            final int g = (color >> 8) & 0xff;
            final int b = color & 0xff;
            int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
            if(gray == 0 || gray == 127 || gray == 255) {
            	
            }else {
            	System.out.println(i + " : " + j + " " + gray);
            }
            //System.out.println(i + " : " + j + " " + gray);
            int newPixel = colorToRGB(255, gray, gray, gray);
            grayImage.setRGB(i, j, newPixel);
        }
    }
    File newFile = new File("E:\\package\\gray\\ok.bmp");
    ImageIO.write(grayImage, "bmp", newFile);
	}
public static void main(String[] args) throws IOException {
	//pngToJpg();
	pngToJpg1();
}

public static void pngToJpg1() {
	String path = "C:/Users/CVTE/Desktop/49101_L/49101_L_avg.png";
	String savepath = "C:/Users/CVTE/Desktop/49101_L/49101_L.jpg";
	
	BufferedImage bufferedImage = null;
    try {
      // read image file
      bufferedImage = ImageIO.read(new File(path));
      // create a blank, RGB, same width and height, and a white
      // background
      BufferedImage newBufferedImage = new BufferedImage(
          bufferedImage.getWidth(), bufferedImage.getHeight(),
          BufferedImage.TYPE_INT_RGB);
      // TYPE_INT_RGB:创建一个RBG图像，24位深度，成功将32位图转化成24位
      newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0,
          null);
      // write to jpeg file
      RepaintUtil.writePic(newBufferedImage, savepath);
      System.out.println("Done");
    } catch (IOException e) {
      e.printStackTrace();
    }
	
}

public static void pngToJpg() {
	String path = "C:/Users/CVTE/Desktop/49101_L/49101_L_avg.png";
	String savepath = "C:/Users/CVTE/Desktop/49101_L/49101_L.jpg";
	
	BufferedImage bufferedImage = null;
    try {
      // read image file
      bufferedImage = ImageIO.read(new File(path));
      // create a blank, RGB, same width and height, and a white
      // background
      BufferedImage newBufferedImage = new BufferedImage(
          bufferedImage.getWidth(), bufferedImage.getHeight(),
          BufferedImage.TYPE_INT_RGB);
      // TYPE_INT_RGB:创建一个RBG图像，24位深度，成功将32位图转化成24位
      newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0,
          null);
      // write to jpeg file
      ImageIO.write(newBufferedImage, "jpg", new File(savepath));
      System.out.println("Done");
    } catch (IOException e) {
      e.printStackTrace();
    }
}

	
}
