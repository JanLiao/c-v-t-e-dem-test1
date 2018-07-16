package statistics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
import tmp.ImageCut;
import tmp.RepaintUtil;

/** 
* @author: jan 
* @date: 2018年6月20日 下午3:46:11 
*/
public class SaveAll {

	public static void imgAll(String name) {
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
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(img3, 0, 0);
		int len = Constant.AllLabelData.size();
		double ratio = 2124 / 530;
		for (int i = 0; i < len; i++) {
			CircleData solebeiOther = DataUtil.getSoleLabelData(i, "shipan");
			gc.save();
			gc.setLineWidth(solebeiOther.getStrokeWidth());
			int red = Constant.ColorList.get(i).getRed();
			int green = Constant.ColorList.get(i).getGreen();
			int blue = Constant.ColorList.get(i).getBlue();
			gc.setStroke(Color.rgb(red, green, blue));
			double relativeX = solebeiOther.getLeft() * ratio;
			double relativeY = solebeiOther.getTop() * ratio;
			gc.setTransform(new Affine(new Rotate(solebeiOther.getAngle(), relativeX, relativeY)));
			gc.strokeOval(relativeX, relativeY, solebeiOther.getRadius() * solebeiOther.getScaleX() * 2 * ratio,
					solebeiOther.getRadius() * solebeiOther.getScaleY() * 2 * ratio);
			gc.restore();
		}
		
		for (int i = 0; i < len; i++) {
			CircleData solebeiOther = DataUtil.getSoleLabelData(i, "shibei");
			gc.save();
			gc.setLineWidth(solebeiOther.getStrokeWidth());
			int red = Constant.ColorList.get(i).getRed();
			int green = Constant.ColorList.get(i).getGreen();
			int blue = Constant.ColorList.get(i).getBlue();
			gc.setStroke(Color.rgb(red, green, blue));
			double relativeX = solebeiOther.getLeft() * ratio;
			double relativeY = solebeiOther.getTop() * ratio;
			gc.setTransform(new Affine(new Rotate(solebeiOther.getAngle(), relativeX, relativeY)));
			gc.strokeOval(relativeX, relativeY, solebeiOther.getRadius() * solebeiOther.getScaleX() * 2 * ratio,
					solebeiOther.getRadius() * solebeiOther.getScaleY() * 2 * ratio);
			gc.restore();
		}

		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		WritableImage wim = new WritableImage(2124, 2056);
		Scene scena = primaryStage.getScene();
		scena.snapshot(wim);

		File fileA = new File(Constant.statpath + "/" + name + "/alloverlap.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		} catch (Exception s) {
		}
	}
	
	public static void imgUserAll(String name) {
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
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(img3, 0, 0);
		int len = Constant.AllLabelData.size();
		double ratio = 2124 / 530;
		for (int i = 0; i < len; i++) {
			if(checkUser(Constant.AllLabelData.get(i).split("=")[0], Constant.PanUser)) {
				CircleData solebeiOther = DataUtil.getSoleLabelData(i, "shipan");
				gc.save();
				gc.setLineWidth(solebeiOther.getStrokeWidth());
				int red = Constant.ColorList.get(i).getRed();
				int green = Constant.ColorList.get(i).getGreen();
				int blue = Constant.ColorList.get(i).getBlue();
				gc.setStroke(Color.rgb(red, green, blue));
				double relativeX = solebeiOther.getLeft() * ratio;
				double relativeY = solebeiOther.getTop() * ratio;
				gc.setTransform(new Affine(new Rotate(solebeiOther.getAngle(), relativeX, relativeY)));
				gc.strokeOval(relativeX, relativeY, solebeiOther.getRadius() * solebeiOther.getScaleX() * 2 * ratio,
						solebeiOther.getRadius() * solebeiOther.getScaleY() * 2 * ratio);
				gc.restore();
			}
		}
		
		for (int i = 0; i < len; i++) {
			if(checkUser(Constant.AllLabelData.get(i).split("=")[0], Constant.BeiUser)) {
				CircleData solebeiOther = DataUtil.getSoleLabelData(i, "shibei");
				gc.save();
				gc.setLineWidth(solebeiOther.getStrokeWidth());
				int red = Constant.ColorList.get(i).getRed();
				int green = Constant.ColorList.get(i).getGreen();
				int blue = Constant.ColorList.get(i).getBlue();
				gc.setStroke(Color.rgb(red, green, blue));
				double relativeX = solebeiOther.getLeft() * ratio;
				double relativeY = solebeiOther.getTop() * ratio;
				gc.setTransform(new Affine(new Rotate(solebeiOther.getAngle(), relativeX, relativeY)));
				gc.strokeOval(relativeX, relativeY, solebeiOther.getRadius() * solebeiOther.getScaleX() * 2 * ratio,
						solebeiOther.getRadius() * solebeiOther.getScaleY() * 2 * ratio);
				gc.restore();
			}
		}

		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		WritableImage wim = new WritableImage(2124, 2056);
		Scene scena = primaryStage.getScene();
		scena.snapshot(wim);

		File fileA = new File(Constant.statpath + "/" + name + "/useroverlap.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		} catch (Exception s) {
		}
	}

	private static boolean checkUser(String user, String panUser) {
		boolean flag = false;
		String[] s = panUser.split(",");
		for(String t : s) {
			if(user.equals(t)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static void imgAvg(String name) {
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
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(img3, 0, 0);
		double ratio = 2124 / 530;
		
		CircleData solebeiOther = Constant.panAvg;
		gc.save();
		gc.setLineWidth(solebeiOther.getStrokeWidth());
		int red = Constant.ColorList.get(0).getRed();
		int green = Constant.ColorList.get(0).getGreen();
		int blue = Constant.ColorList.get(0).getBlue();
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
		int red1 = Constant.ColorList.get(1).getRed();
		int green1 = Constant.ColorList.get(1).getGreen();
		int blue1 = Constant.ColorList.get(1).getBlue();
		gc.setStroke(Color.rgb(red1, green1, blue1));
		double relativeX1 = solebeiOther1.getLeft() * ratio;
		double relativeY1 = solebeiOther1.getTop() * ratio;
		gc.setTransform(new Affine(new Rotate(solebeiOther1.getAngle(), relativeX1, relativeY1)));
		gc.strokeOval(relativeX1, relativeY1, solebeiOther1.getRadius() * solebeiOther1.getScaleX() * 2 * ratio,
				solebeiOther1.getRadius() * solebeiOther1.getScaleY() * 2 * ratio);
		gc.restore();
		
		LineData lineOther = Constant.lineAvg;
		gc.setLineWidth(lineOther.getStrokeWidth());
		// 两条线
		gc.save();
		int red2 = Constant.ColorList.get(2).getRed();
		int green2 = Constant.ColorList.get(2).getGreen();
		int blue2 = Constant.ColorList.get(2).getBlue();
		gc.setStroke(Color.rgb(red2, green2, blue2));
		double relativeX2 = lineOther.getLeft()*ratio;
		double relativeY2 = lineOther.getTop()*ratio;
		gc.strokeLine(relativeX2, relativeY2 + lineOther.getHeight() * lineOther.getScaleY() * ratio / 2,
				relativeX2 + lineOther.getWidth() * lineOther.getScaleX() * ratio,
				relativeY2 + lineOther.getHeight() * lineOther.getScaleY() * ratio / 2);
		gc.restore();
		gc.save();
		gc.setStroke(Color.rgb(red2, green2, blue2));
		gc.strokeLine(relativeX2 + lineOther.getWidth() * lineOther.getScaleX() * ratio / 2, relativeY2,
				relativeX2 + lineOther.getWidth() * lineOther.getScaleX() * ratio / 2,
				relativeY2 + lineOther.getHeight() * lineOther.getScaleY() * ratio);
		gc.restore();
		
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		WritableImage wim = new WritableImage(2124, 2056);
		Scene scena = primaryStage.getScene();
		scena.snapshot(wim);

		File fileA = new File(Constant.statpath + "/" + name + "/" + name + "_avg.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		} catch (Exception s) {
		}
	}

	public static void imgMask(String name) {
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
		
        double beipercent = Constant.panValue;
		
        int size1 = 0;
		if(Constant.BeiUser == null || "".equals(Constant.BeiUser)) {
			size1 = Constant.AllLabelData.size();
		}
		else {
			size1 = Constant.BeiUser.split(",").length;
		}
		int num1 = (int) (size1*beipercent/100);
		if(num1 == 0) {
			num1 = 1;
		}
		MaskUtil.paint(gc3, "shibei", num1);
		
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		WritableImage wim = new WritableImage(2124, 2056);
		Scene scena = primaryStage.getScene();
		scena.snapshot(wim);

		File fileA = new File(Constant.statpath + "/" + name + "/" + name + "_mask.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		} catch (Exception s) {
		}
	}

	public static void imgAvgMask(String name) {
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
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(img3, 0, 0);
		double ratio = 2124 / 530;
		
		CircleData solebeiOther = Constant.panAvg;
		gc.save();
		gc.setLineWidth(solebeiOther.getStrokeWidth());
		int red = Constant.ColorList.get(0).getRed();
		int green = Constant.ColorList.get(0).getGreen();
		int blue = Constant.ColorList.get(0).getBlue();
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
		int red1 = Constant.ColorList.get(1).getRed();
		int green1 = Constant.ColorList.get(1).getGreen();
		int blue1 = Constant.ColorList.get(1).getBlue();
		gc.setStroke(Color.rgb(red1, green1, blue1));
		double relativeX1 = solebeiOther1.getLeft() * ratio;
		double relativeY1 = solebeiOther1.getTop() * ratio;
		gc.setTransform(new Affine(new Rotate(solebeiOther1.getAngle(), relativeX1, relativeY1)));
		gc.strokeOval(relativeX1, relativeY1, solebeiOther1.getRadius() * solebeiOther1.getScaleX() * 2 * ratio,
				solebeiOther1.getRadius() * solebeiOther1.getScaleY() * 2 * ratio);
		gc.restore();
		
		LineData lineOther = Constant.lineAvg;
		gc.setLineWidth(lineOther.getStrokeWidth());
		// 两条线
		gc.save();
		int red2 = Constant.ColorList.get(2).getRed();
		int green2 = Constant.ColorList.get(2).getGreen();
		int blue2 = Constant.ColorList.get(2).getBlue();
		gc.setStroke(Color.rgb(red2, green2, blue2));
		double relativeX2 = lineOther.getLeft()*ratio;
		double relativeY2 = lineOther.getTop()*ratio;
		gc.strokeLine(relativeX2, relativeY2 + lineOther.getHeight() * lineOther.getScaleY() * ratio / 2,
				relativeX2 + lineOther.getWidth() * lineOther.getScaleX() * ratio,
				relativeY2 + lineOther.getHeight() * lineOther.getScaleY() * ratio / 2);
		gc.restore();
		gc.save();
		gc.setStroke(Color.rgb(red2, green2, blue2));
		gc.strokeLine(relativeX2 + lineOther.getWidth() * lineOther.getScaleX() * ratio / 2, relativeY2,
				relativeX2 + lineOther.getWidth() * lineOther.getScaleX() * ratio / 2,
				relativeY2 + lineOther.getHeight() * lineOther.getScaleY() * ratio);
		gc.restore();
		
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
		MaskUtil.paint(gc, "shipan", num);
		
        double beipercent = Constant.panValue;
		
        int size1 = 0;
		if(Constant.BeiUser == null || "".equals(Constant.BeiUser)) {
			size1 = Constant.AllLabelData.size();
		}
		else {
			size1 = Constant.BeiUser.split(",").length;
		}
		int num1 = (int) (size1*beipercent/100);
		if(num1 == 0) {
			num1 = 1;
		}
		MaskUtil.paint(gc, "shibei", num1);
		
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		WritableImage wim = new WritableImage(2124, 2056);
		Scene scena = primaryStage.getScene();
		scena.snapshot(wim);

		//File fileA = new File(Constant.statpath + "/" + name + "_all.png");
		File fileA = new File("C:\\Users\\CVTE\\Desktop\\HP打印\\sole" + "/" + name + ".png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		} catch (Exception s) {
		}
	}
	
	public static void AllMaskNew(String name, String flagname, String savepath) throws FileNotFoundException, IOException {
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
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(img3, 0, 0);
		double ratio = 2124 / 530;
		
		LineData lineOther = Constant.lineAvg;
		gc.setLineWidth(lineOther.getStrokeWidth());
		// 两条线
		gc.save();
		int red2 = Constant.ColorList.get(2).getRed();
		int green2 = Constant.ColorList.get(2).getGreen();
		int blue2 = Constant.ColorList.get(2).getBlue();
		gc.setStroke(Color.rgb(red2, green2, blue2));
		double relativeX2 = lineOther.getLeft()*ratio + lineOther.getWidth() * lineOther.getScaleX() * ratio / 2;
		double relativeY2 = lineOther.getTop()*ratio + lineOther.getHeight() * lineOther.getScaleY() * ratio / 2;
		gc.strokeLine(relativeX2 - 50, relativeY2,
				relativeX2 + 50,
				relativeY2);
		gc.restore();
		gc.save();
		gc.setStroke(Color.rgb(red2, green2, blue2));
		gc.strokeLine(relativeX2, relativeY2 - 50,
				relativeX2,
				relativeY2 + 50);
		gc.restore();
		
		//CircleData circle = Constant.panAvg;
        //初始化视盘avg
		int num = 4;
		MaskUtil.paint(gc, "shipan", num);
		int num1 = 4;
		MaskUtil.paint(gc, "shibei", num1);
		
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		WritableImage wim = new WritableImage(2124, 2056);
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
	      // write to jpeg file
	      ImageIO.write(newBufferedImage, "jpg", new File(tmppath + "/" + name + ".jpg"));
	      System.out.println("Done");
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
		
		if("R".equals(str[1]) || "RR".equals(str[1]) || "RRR".equals(str[1]) || "RRRR".equals(str[1])) {
			BufferedImage iIO = 
					ImageCut.imgCut(ImageIO.read(new File(tmppath + "/" + name + ".jpg")));
			BufferedImage tpimg = flipImage(iIO);
			RepaintUtil.writePic(tpimg, savepath + "\\" + flagname + ".jpg");
		}
		else {
			BufferedImage iIO = ImageCut.imgCut(ImageIO.read(new File(tmppath + "/" + name + ".jpg")));
			RepaintUtil.writePic(iIO, savepath + "\\" + flagname + ".jpg");
		}
	}
	
	public static void AllMaskNew1(String name, String flagname, 
			String savepath, ConsoleImg obj) throws FileNotFoundException, IOException {
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
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(img3, 0, 0);
		double ratio = 2124 / 530;
		
		// avg画图
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
		
		LineData lineOther = Constant.lineAvg;
		gc.setLineWidth(lineOther.getStrokeWidth());
		// 两条线
		gc.save();
		int red2 = Constant.ColorList.get(2).getRed();
		int green2 = Constant.ColorList.get(2).getGreen();
		int blue2 = Constant.ColorList.get(2).getBlue();
		gc.setStroke(Color.rgb(red2, green2, blue2));
		double relativeX2 = lineOther.getLeft()*ratio + lineOther.getWidth() * lineOther.getScaleX() * ratio / 2;
		double relativeY2 = lineOther.getTop()*ratio + lineOther.getHeight() * lineOther.getScaleY() * ratio / 2;
		gc.strokeLine(relativeX2 - 50, relativeY2,
				relativeX2 + 50,
				relativeY2);
		gc.restore();
		gc.save();
		gc.setStroke(Color.rgb(red2, green2, blue2));
		gc.strokeLine(relativeX2, relativeY2 - 50,
				relativeX2,
				relativeY2 + 50);
		gc.restore();
		
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
		MaskUtil.paint(gc, "shipan", num);
		
        double beipercent = Constant.beiValue;
		
        int size1 = 0;
		if(Constant.BeiUser == null || "".equals(Constant.BeiUser)) {
			size1 = Constant.AllLabelData.size();
		}
		else {
			size1 = Constant.BeiUser.split(",").length;
		}
		int num1 = (int) (size1*beipercent/100);
		if(num1 == 0) {
			num1 = 1;
		}
		MaskUtil.paint(gc, "shibei", num1);
		
		initTmpConstant(obj);
		MaskUtil.paintNew(gc, "shipan", 4);
		MaskUtil.paintNew(gc, "shibei", 4);
		
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		WritableImage wim = new WritableImage(2124, 2056);
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
	      // write to jpeg file
	      ImageIO.write(newBufferedImage, "jpg", new File(tmppath + "/" + name + ".jpg"));
	      System.out.println("Done");
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
		
		if("R".equals(str[1]) || "RR".equals(str[1]) || "RRR".equals(str[1]) || "RRRR".equals(str[1])) {
			BufferedImage iIO = 
					ImageCut.imgCut(ImageIO.read(new File(tmppath + "/" + name + ".jpg")));
			BufferedImage tpimg = flipImage(iIO);
			RepaintUtil.writePic(tpimg, savepath + "\\" + flagname + ".jpg");
		}
		else {
			BufferedImage iIO = ImageCut.imgCut(ImageIO.read(new File(tmppath + "/" + name + ".jpg")));
			RepaintUtil.writePic(iIO, savepath + "\\" + flagname + ".jpg");
		}
	}

	private static void initTmpConstant(ConsoleImg obj) {
		String[] s = obj.getAllLabel().split("=");
		int offset = s.length / 2;
		//System.out.println("len = " + s.length);
		List<String> allLabel = new ArrayList<String>();
		for(int i = 0; i < offset; i++) {
			System.out.println(s[2*i] + " k=k " + s[2*i + 1]);
			allLabel.add(s[2*i] + "=" + s[2*i + 1]);
		}
		Constant.AllLabelData = allLabel;
		Constant.Console = obj;
		
		if(obj.getPanMixUser() == null || "".equals(obj.getPanMixUser())) {
			String ss = "";
			for(int i = 0; i < Constant.AllLabelData.size(); i++) {
				ss += Constant.AllLabelData.get(i).split("=")[0] + ",";
			}
			Constant.PanUser = ss;
		}
		else {
			Constant.PanUser = obj.getPanMixUser();
		}
		
		if(obj.getBeiMixUser() == null || "".equals(obj.getBeiMixUser())) {
			String ss = "";
			for(int i = 0; i < Constant.AllLabelData.size(); i++) {
				ss += Constant.AllLabelData.get(i).split("=")[0] + ",";
			}
			Constant.BeiUser = ss;
		}
		else {
			Constant.BeiUser = obj.getBeiMixUser();
		}
		
		if(obj.getCenterMixUser() == null || "".equals(obj.getCenterMixUser())) {
			String ss = "";
			for(int i = 0; i < Constant.AllLabelData.size(); i++) {
				ss += Constant.AllLabelData.get(i).split("=")[0] + ",";
			}
			Constant.CenterUser = ss;
		}
		else {
			Constant.CenterUser = obj.getCenterMixUser();
		}
		
		CircleData pan = DataUtil.getAvgCircleData("shipan");
		CircleData bei = DataUtil.getAvgCircleData("shibei");
		LineData line = DataUtil.getAvgLineData("amd");
		Constant.panAvg = pan;
		Constant.beiAvg = bei;
		Constant.lineAvg = line;
		Mask mask = MaskUtil.generateTmpData();
		Constant.mask = mask;
		if(obj.getPercent() == null || "".equals(obj.getPercent())) {			
			Constant.panValue = 50;
		}
		else {
			Constant.panValue = Double.parseDouble(obj.getPercent());
		}
		if(obj.getBeipercent() == null || "".equals(obj.getBeipercent())) {			
			Constant.beiValue = 50;
		}
		else {
			Constant.beiValue = Double.parseDouble(obj.getBeipercent());
		}
	}

	public static void AllMask(String name, String flagname, String savepath) throws FileNotFoundException, IOException {
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
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(img3, 0, 0);
		double ratio = 2124 / 530;
		
		LineData lineOther = Constant.lineAvg;
		gc.setLineWidth(lineOther.getStrokeWidth());
		// 两条线
		gc.save();
		int red2 = Constant.ColorList.get(2).getRed();
		int green2 = Constant.ColorList.get(2).getGreen();
		int blue2 = Constant.ColorList.get(2).getBlue();
		gc.setStroke(Color.rgb(red2, green2, blue2));
		double relativeX2 = lineOther.getLeft()*ratio + lineOther.getWidth() * lineOther.getScaleX() * ratio / 2;
		double relativeY2 = lineOther.getTop()*ratio + lineOther.getHeight() * lineOther.getScaleY() * ratio / 2;
		gc.strokeLine(relativeX2 - 50, relativeY2,
				relativeX2 + 50,
				relativeY2);
		gc.restore();
		gc.save();
		gc.setStroke(Color.rgb(red2, green2, blue2));
		gc.strokeLine(relativeX2, relativeY2 - 50,
				relativeX2,
				relativeY2 + 50);
		gc.restore();
		
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
		MaskUtil.paint(gc, "shipan", num);
		
        double beipercent = Constant.beiValue;
		
        int size1 = 0;
		if(Constant.BeiUser == null || "".equals(Constant.BeiUser)) {
			size1 = Constant.AllLabelData.size();
		}
		else {
			size1 = Constant.BeiUser.split(",").length;
		}
		int num1 = (int) (size1*beipercent/100);
		if(num1 == 0) {
			num1 = 1;
		}
		MaskUtil.paint(gc, "shibei", num1);
		
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		WritableImage wim = new WritableImage(2124, 2056);
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
	      // write to jpeg file
	      ImageIO.write(newBufferedImage, "jpg", new File(tmppath + "/" + name + ".jpg"));
	      System.out.println("Done");
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
		
		if("R".equals(str[1]) || "RR".equals(str[1]) || "RRR".equals(str[1]) || "RRRR".equals(str[1])) {
			BufferedImage iIO = 
					ImageCut.imgCut(ImageIO.read(new File(tmppath + "/" + name + ".jpg")));
			BufferedImage tpimg = flipImage(iIO);
			RepaintUtil.writePic(tpimg, savepath + "\\" + flagname + ".jpg");
		}
		else {
			BufferedImage iIO = ImageCut.imgCut(ImageIO.read(new File(tmppath + "/" + name + ".jpg")));
			RepaintUtil.writePic(iIO, savepath + "\\" + flagname + ".jpg");
		}
	}
	
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

}
