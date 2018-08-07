package tmptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import statistics.CircleData;
import statistics.DataUtil;
import statistics.ReadCSV;

public class CanvasView extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		long start = System.currentTimeMillis();
		HBox root = new HBox();
		root.setAlignment(Pos.CENTER);
		
		readyPaintPoint(root);
		Scene scene = new Scene(root, 1000, 600);
		stage.setScene(scene);
		stage.show();
		System.out.println("time = " + (System.currentTimeMillis() - start));
	}
	
	public void readyPaintPoint(HBox root) throws FileNotFoundException {
		Canvas canvas = new Canvas(1000, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		String filepath = "E:/第二批/云琼容_20170807104517900.jpg";
		String csvpath = "F:/eclipse-workspace-new1/LabelDemoNew/user/file/E.第二批/2___云琼容_20170807104517900";
//		String filepath = "E:/第二批/云琼容_20170807104518020.jpg";
//		String csvpath = "F:/eclipse-workspace-new1/LabelDemoNew/user/file/E.第二批/2___云琼容_20170807104518020";
		filepath = "C:/Users/CVTE/Desktop/融合数据/增补/朱晗_20180611092301209.jpg";
		FileInputStream fis = new FileInputStream(new File(filepath));
		Image img = new Image(fis, 530, 530, true, true);
		gc.drawImage(img, 0, 0);
		
		List<String[]> allList = getAllList();
		
		// view overlap
		//viewOverlap(gc);
		
		// view avg
		viewAvg(gc, allList);
		
		root.getChildren().add(canvas);
	}

	public static List<String[]> getAllList() {
		String imgname = "2___朱晗_20180611092301209";
		String csvpath = "C:/Users/CVTE/Desktop/融合数据/增补-6/";
		File[] files = new File(csvpath).listFiles();
		List<String[]> list = new ArrayList<String[]>();
		for(File f : files) {
			String user = f.getName();			
			File[] tmp = new File(csvpath + user + "/file").listFiles();
			String path = "";
			for(File ff : tmp) {
				if(ff.isDirectory()) {
					path = ff.getName();
				}
			}
			System.out.println(user + " = " + csvpath + user + "/file/" + path + "/" + imgname);
			List<String[]> list1 = ReadCSV.readCSV(csvpath + user + "/file/" + path + "/" + imgname);
			list.add(list1.get(list1.size() - 1));
		}
		return list;
	}

	public void viewAvg(GraphicsContext gc, List<String[]> allList) {
		List<CircleData> list = new ArrayList<CircleData>();
		for(String[] s : allList) {
			String label = s[7];
			CircleData circle = CanvasTest.getCircle(label);
			System.out.println("单个circle = " + circle);
			list.add(circle);
		}
		CircleData circle = DataUtil.getAvgCircleNew(list);
		System.out.println("circle = " + circle);
		Color cl = Color.BLUE;
		CanvasTest.paintAvgOval(circle, cl, gc);
	}

	public void viewOverlap(GraphicsContext gc) {
		String imgname = "2___朱晗_20180611092301209";
		String csvpath = "C:/Users/CVTE/Desktop/融合数据/增补-6/";
		File[] files = new File(csvpath).listFiles();
		List<String[]> list = new ArrayList<String[]>();
		for(File f : files) {
			String user = f.getName();			
			File[] tmp = new File(csvpath + user + "/file").listFiles();
			String path = "";
			for(File ff : tmp) {
				if(ff.isDirectory()) {
					path = ff.getName();
				}
			}
			//System.out.println(user + " = " + csvpath + user + "/file/" + path + "/" + imgname);
			List<String[]> list1 = ReadCSV.readCSV(csvpath + user + "/file/" + path + "/" + imgname);
			list.add(list1.get(list1.size() - 1));
		}
		for(int i = 0; i < list.size(); i++) {
			String label = list.get(i)[7];
			CircleData circle = CanvasTest.getCircle(label);
			Color color = null;
			String colorStr = "";
			if(i == 0) {
				color = Color.SEAGREEN;
				colorStr = "SEAGREEN";
			}else if(i == 1) {
				color = Color.RED;
				colorStr = "RED";
			}else if(i == 2) {
				color = Color.BLUE;
				colorStr = "BLUE";
			}else if(i == 3) {
				color = Color.PINK;
				colorStr = "PINK";
			}else if(i == 4) {
				color = Color.VIOLET;
				colorStr = "VIOLET";
			}else if(i == 5) {
				color = Color.CORNFLOWERBLUE;
				colorStr = "CORNFLOWERBLUE";
			}else if(i == 6) {
				color = Color.AQUA;
				colorStr = "AQUA";
			}
						
			// 描点
			System.out.println(colorStr + "  left and top=" + circle.getLeft() + "=" + circle.getTop());
			CanvasTest.paintPoint(circle, color, gc);
			
			// 画椭圆
			System.out.println(colorStr + " oval angle=" + circle.getAngle());
			CanvasTest.paintOval(circle, color, gc);
			
			// 描椭圆中心点
			System.out.println(colorStr + " center =" + circle.getCenterX() + "=" + circle.getCenterY());
			CanvasTest.paintCenterPoint(circle, color, gc);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
