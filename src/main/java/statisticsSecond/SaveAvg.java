package statisticsSecond;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
* @date: 2018年6月20日 下午2:06:03 
*/
public class SaveAvg {

	public static void avgPan(String name) {
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
		
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		WritableImage wim = new WritableImage(2124, 2056);
		Scene scena = primaryStage.getScene();
		scena.snapshot(wim);

		File fileA = new File(Constant.statpath + "/" + name + "/shipan_avg.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		} catch (Exception s) {
		}
	}

	public static void avgBei(String name) {
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
		
		CircleData solebeiOther = Constant.beiAvg;
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
		
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		WritableImage wim = new WritableImage(2124, 2056);
		Scene scena = primaryStage.getScene();
		scena.snapshot(wim);

		File fileA = new File(Constant.statpath + "/" + name + "/shibei_avg.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		} catch (Exception s) {
		}
	}

	public static void avgAmd(String name) {
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
		double ratio = 2124 / 530;
		
		LineData lineOther = Constant.lineAvg;
		gc3.setLineWidth(lineOther.getStrokeWidth());
		// 两条线
		gc3.save();
		int red = Constant.ColorList.get(0).getRed();
		int green = Constant.ColorList.get(0).getGreen();
		int blue = Constant.ColorList.get(0).getBlue();
		gc3.setStroke(Color.rgb(red, green, blue));
		double relativeX = lineOther.getLeft()*ratio;
		double relativeY = lineOther.getTop()*ratio;
		gc3.strokeLine(relativeX, relativeY + lineOther.getHeight() * lineOther.getScaleY() * ratio / 2,
				relativeX + lineOther.getWidth() * lineOther.getScaleX() * ratio,
				relativeY + lineOther.getHeight() * lineOther.getScaleY() * ratio / 2);
		gc3.restore();
		gc3.save();
		gc3.setStroke(Color.rgb(red, green, blue));
		gc3.strokeLine(relativeX + lineOther.getWidth() * lineOther.getScaleX() * ratio / 2, relativeY,
				relativeX + lineOther.getWidth() * lineOther.getScaleX() * ratio / 2,
				relativeY + lineOther.getHeight() * lineOther.getScaleY() * ratio);
		gc3.restore();
		
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		WritableImage wim = new WritableImage(2124, 2056);
		Scene scena = primaryStage.getScene();
		scena.snapshot(wim);

		File fileA = new File(Constant.statpath + "/" + name + "/amd_avg.png");
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", fileA);
		} catch (Exception s) {
		}
	}

}
