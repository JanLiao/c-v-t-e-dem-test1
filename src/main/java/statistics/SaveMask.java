package statistics;

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
	
	

}
