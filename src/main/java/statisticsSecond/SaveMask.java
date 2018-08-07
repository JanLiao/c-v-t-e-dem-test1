package statisticsSecond;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/** 
* @author: jan 
* @date: 2018年6月20日 下午2:17:01 
*/
public class SaveMask {

	public static void maskPan(String name) {
		double width3 = 2124;
		double height3 = 2056;
		Group root = new Group();
		Stage primaryStage = new Stage();

		String[] str = name.split("_");
		File filePan = new File(Constant.imgpath + "/" + str[0] + "/" + str[1] + ".jpg");
		InputStream fisPan = null;
		try {
			fisPan = new FileInputStream(filePan);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image img3 = new Image(fisPan, width3, height3, true, true);
		Canvas canvas = new Canvas(2124, 2124);
		GraphicsContext gc3 = canvas.getGraphicsContext2D();
		gc3.drawImage(img3, 0, 0);
		//double ratio = 2124 / 530;
		
		//CircleData circle = Constant.panAvg;
        //初始化视盘avg
		double percent = Constant.panValue;
		
		int size = 0;
		if(Constant.PanUser == null || Constant.PanUser.equals("")) {
			size = Constant.AllLabelData.size();
		}
		else {
			size = Constant.PanUser.split(",").length;
		}
		int num = (int) (size*percent/100);
		if(num == 0) {
			num = 1;
		}
		MaskUtil.paint(gc3, "shipan", num);
		
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		WritableImage wim = new WritableImage(2124, 2056);
		Scene scena = primaryStage.getScene();
		scena.snapshot(wim);

		File fileA = new File(Constant.statpath + "/" + name + "/shipan_mask.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		} catch (Exception s) {
		}
	}

	public static void maskBei(String name) {
		double width3 = 2124;
		double height3 = 2056;
		Group root = new Group();
		Stage primaryStage = new Stage();

		String[] str = name.split("_");
		File filePan = new File(Constant.imgpath + "/" + str[0] + "/" + str[1] + ".jpg");
		InputStream fisPan = null;
		try {
			fisPan = new FileInputStream(filePan);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image img3 = new Image(fisPan, width3, height3, true, true);
		Canvas canvas = new Canvas(2124, 2124);
		GraphicsContext gc3 = canvas.getGraphicsContext2D();
		gc3.drawImage(img3, 0, 0);
		//double ratio = 2124 / 530;
		
		//CircleData circle = Constant.beiAvg;
        //初始化视盘avg
		double percent = Constant.beiValue;
		
		int size = 0;
		if(Constant.BeiUser == null || "".equals(Constant.BeiUser)) {
			size = Constant.AllLabelData.size();
		}
		else {
			size = Constant.BeiUser.split(",").length;
		}
		int num = (int) (size*percent/100);
		if(num == 0) {
			num = 1;
		}
		MaskUtil.paint(gc3, "shibei", num);
		
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		WritableImage wim = new WritableImage(2124, 2056);
		Scene scena = primaryStage.getScene();
		scena.snapshot(wim);

		File fileA = new File(Constant.statpath + "/" + name + "/shibei_mask.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		} catch (Exception s) {
		}
	}

	public static void allmasknew3(String name, String flagname, 
			String savepath, ConsoleImg obj) throws FileNotFoundException, IOException {
		double width3 = 1634;
		double height3 = 1634;
		double ratio = width3/530;
		Group root = new Group();
		Stage primaryStage = new Stage();
		String[] str = name.split("___");
		File filePan = new File(Constant.imgpath + "/" + str[1] + ".jpg");
		InputStream fisPan = null;
		try {
			fisPan = new FileInputStream(filePan);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image img3 = new Image(fisPan, width3, height3, true, true);
		Canvas canvas = new Canvas(1634, 1634);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(img3, 0, 0);
        gc.save();
     // 初始化视盘avg
     		double percent = Constant.panValue;

     		int size = 0;
     		if (Constant.PanUser == null || Constant.PanUser.equals("")) {
     			size = Constant.AllLabelData.size();
     		} else {
     			size = Constant.PanUser.split(",").length;
     		}
     		int num = (int) (size * percent / 100);
     		if (num == 0) {
     			num = 1;
     		}
     		MaskTmpUtil.paintTmp(gc, "shipan", num);

     		double beipercent = Constant.beiValue;

     		int size1 = 0;
     		if (Constant.BeiUser == null || "".equals(Constant.BeiUser)) {
     			size1 = Constant.AllLabelData.size();
     		} else {
     			size1 = Constant.BeiUser.split(",").length;
     		}
     		int num1 = (int) (size1 * beipercent / 100);
     		if (num1 == 0) {
     			num1 = 1;
     		}
     		MaskTmpUtil.paintTmp(gc, "shibei", num1);
     		
     		//获取当前图片label 黄斑中心数据
        	LineData allLine = Constant.lineAvg;
        	gc.setLineWidth(1);
        	//两条线
        	//gc3.save();
        	gc.setStroke(Color.rgb(255, 0, 255));
        	double relativeX8 = allLine.getLeft()*ratio + allLine.getWidth()*allLine.getScaleX()*ratio/2;
        	double relativeY8 = allLine.getTop()*ratio + allLine.getHeight()*allLine.getScaleY()*ratio/2;
        	gc.strokeLine(relativeX8 - 50, relativeY8, 
        			relativeX8 + 50, relativeY8);
            //gc3.restore();
            //gc3.save();
        	gc.strokeLine(relativeX8, relativeY8 - 50, 
        			relativeX8, relativeY8 + 50);
            //gc3.restore();
     		
     		root.getChildren().add(canvas);
    		Scene scene = new Scene(root);
    		primaryStage.setScene(scene);

    		//WritableImage wim = new WritableImage(2124, 2056);
    		WritableImage wim = new WritableImage(1634, 1634);
    		Scene scena = primaryStage.getScene();
    		scena.snapshot(wim);

    		String tmppath = "C:\\Users\\CVTE\\Desktop\\tmp";
    		File fileA = new File(tmppath + "/" + name + ".png");
    		try {
    			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
    		} catch (Exception s) {
    			
    		}
    		
    		BufferedImage bufferedImage = null;
    	    try {
    	      // read image file
    	      bufferedImage = ImageIO.read(new File(tmppath + "/" + name + ".png"));
    	      // create a blank, RGB, same width and height, and a white
    	      // background
    	      BufferedImage newBufferedImage = new BufferedImage(
    	          bufferedImage.getWidth(), bufferedImage.getHeight(),
    	          BufferedImage.TYPE_INT_RGB);
    	      // TYPE_INT_RGB:创建一个RBG图像，24位深度，成功将32位图转化成24位
    	      newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0,
    	          null);
    	      double center = Constant.beiAvg.getCenterX();
      		double r = 1634/530;
      		if(center*r < 817) {
      			
      		// write to jpeg file
      	      ImageIO.write(ImageFlip2.imgCut1(newBufferedImage), "jpg", new File(savepath + "/" + name.split("___")[1] + ".jpg"));
      	      System.out.println("Done");
      		}else {
      		// write to jpeg file
      	      ImageIO.write(ImageFlip2.flipImage(ImageFlip2.imgCut1(newBufferedImage)), "jpg", new File(savepath + "/" + name.split("___")[1] + ".jpg"));
      	      System.out.println("Done");
      		}
    	      // write to jpeg file
//    	      ImageIO.write(newBufferedImage, "jpg", new File(savepath + "/" + name.split("___")[1] + ".jpg"));
//    	      System.out.println("Done");
    	    } catch (IOException e) {
    	      e.printStackTrace();
    	    }
    	    
    	    
	}

	public static void allBmp(String name, String flagname, 
			String savepath, ConsoleImg obj) throws FileNotFoundException, IOException {
        BufferedImage imgIO = ImageIO.read(new File("E:\\BaiduNetdiskDownload\\tmp\\test1.bmp"));
		
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
		int[][] tmp = new int[1634][1634];
		for(int i = 0; i < 1634; i++) {
			for(int j = 0; j < 1634; j++) {
				int relativeX = i;
		    	int relativeY = 1633 - j;
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
		
		for(int i = 0; i < 1634; i++) {
			for(int j = 0; j < 1634; j++) {
				int relativeX = i;
		    	int relativeY = 1633 - j;
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
		//File fileA = new File(Constant.statpath + "/" + name + ".bmp");
		
		double center = Constant.beiAvg.getCenterX();
		double r = 1634/530;
		if(center*r < 817) {
			File fileA = new File(savepath + "/" + name.split("___")[1] + ".bmp");
			try {
				ImageIO.write(imgIO, "BMP", fileA);
			} catch (Exception s) {
			}			
		}else {
			File fileA = new File(savepath + "/" + name.split("___")[1] + ".bmp");
			try {
				ImageIO.write(ImageFlip2.flipImage(imgIO), "BMP", fileA);
			} catch (Exception s) {
			}
		}
		
	}
	
	public static void threeColor(String name, String savepath) {
		double width3 = 1634;
		double height3 = 1634;
		double ratio = width3/530;
		Group root = new Group();
		Stage primaryStage = new Stage();
		String[] str = name.split("___");
		File filePan = new File(Constant.imgpath + "/" + str[1] + ".jpg");
		InputStream fisPan = null;
		try {
			fisPan = new FileInputStream(filePan);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image img3 = new Image(fisPan, width3, height3, true, true);
		Canvas canvas = new Canvas(1634, 1634);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(img3, 0, 0);
		gc.save();
		// 初始化视盘avg
		double percent = Constant.panValue;

		int size = 0;
		if (Constant.PanUser == null || Constant.PanUser.equals("")) {
			size = Constant.AllLabelData.size();
		} else {
			size = Constant.PanUser.split(",").length;
		}
		int num = (int) (size * percent / 100);
		if (num == 0) {
			num = 1;
		}
		MaskTmpUtil.paintThreeColor(gc, "shipan", num);

		double beipercent = Constant.beiValue;

		int size1 = 0;
		if (Constant.BeiUser == null || "".equals(Constant.BeiUser)) {
			size1 = Constant.AllLabelData.size();
		} else {
			size1 = Constant.BeiUser.split(",").length;
		}
		int num1 = (int) (size1 * beipercent / 100);
		if (num1 == 0) {
			num1 = 1;
		}
		MaskTmpUtil.paintThreeColor(gc, "shibei", num1);

		// 画all-mask
		MaskTmpUtil.paintAllMask(gc, "shipan", 4);
		MaskTmpUtil.paintAllMask(gc, "shibei", 4);

		// 画视盘-视杯 avg
		CircleData solebeiOther = Constant.panAvg;
		gc.save();
		gc.setLineWidth(solebeiOther.getStrokeWidth());
		int red = Constant.ColorList.get(2).getRed();
		int green = Constant.ColorList.get(2).getGreen();
		int blue = Constant.ColorList.get(2).getBlue();
		gc.setStroke(Color.rgb(red, green, blue));
		double relativeX = solebeiOther.getLeft() * ratio;
		double relativeY = solebeiOther.getTop() * ratio;
		gc.setTransform(new Affine(new Rotate(solebeiOther.getAngle(), relativeX, relativeY)));
		gc.strokeOval(relativeX, relativeY, solebeiOther.getRadius() * solebeiOther.getScaleX() * 2 * ratio,
				solebeiOther.getRadius() * solebeiOther.getScaleY() * 2 * ratio);
		gc.restore();

		CircleData solebeiOther1 = Constant.beiAvg;
		gc.save();
		gc.setLineWidth(solebeiOther1.getStrokeWidth());
		int red1 = Constant.ColorList.get(2).getRed();
		int green1 = Constant.ColorList.get(2).getGreen();
		int blue1 = Constant.ColorList.get(2).getBlue();
		gc.setStroke(Color.rgb(red1, green1, blue1));
		double relativeX1 = solebeiOther1.getLeft() * ratio;
		double relativeY1 = solebeiOther1.getTop() * ratio;
		gc.setTransform(new Affine(new Rotate(solebeiOther1.getAngle(), relativeX1, relativeY1)));
		gc.strokeOval(relativeX1, relativeY1, solebeiOther1.getRadius() * solebeiOther1.getScaleX() * 2 * ratio,
				solebeiOther1.getRadius() * solebeiOther1.getScaleY() * 2 * ratio);
		gc.restore();

		// 获取当前图片label 黄斑中心数据
		LineData allLine = Constant.lineAvg;
		gc.setLineWidth(1);
		// 两条线
		// gc3.save();
		gc.setStroke(Color.rgb(255, 0, 255));
		double relativeX8 = allLine.getLeft() * ratio + allLine.getWidth() * allLine.getScaleX() * ratio / 2;
		double relativeY8 = allLine.getTop() * ratio + allLine.getHeight() * allLine.getScaleY() * ratio / 2;
		gc.strokeLine(relativeX8 - 50, relativeY8, relativeX8 + 50, relativeY8);
		// gc3.restore();
		// gc3.save();
		gc.strokeLine(relativeX8, relativeY8 - 50, relativeX8, relativeY8 + 50);
		// gc3.restore();

		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		// WritableImage wim = new WritableImage(2124, 2056);
		WritableImage wim = new WritableImage(1634, 1634);
		Scene scena = primaryStage.getScene();
		scena.snapshot(wim);

		String tmppath = "C:\\Users\\CVTE\\Desktop\\tmp";
		File fileA = new File(tmppath + "/" + name + ".png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		} catch (Exception s) {

		}

		BufferedImage bufferedImage = null;
		try {
			// read image file
			bufferedImage = ImageIO.read(new File(tmppath + "/" + name + ".png"));
			// create a blank, RGB, same width and height, and a white
			// background
			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			// TYPE_INT_RGB:创建一个RBG图像，24位深度，成功将32位图转化成24位
			newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, null);
			double center = Constant.beiAvg.getCenterX();
			double r = 1634 / 530;
			if (center * r < 817) {

				// write to jpeg file
				ImageIO.write(ImageFlip2.imgCut1(newBufferedImage), "jpg",
						new File(savepath + "/" + name.split("___")[1] + ".jpg"));
				System.out.println("Done");
			} else {
				// write to jpeg file
				ImageIO.write(ImageFlip2.flipImage(ImageFlip2.imgCut1(newBufferedImage)), "jpg",
						new File(savepath + "/" + name.split("___")[1] + ".jpg"));
				System.out.println("Done");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
