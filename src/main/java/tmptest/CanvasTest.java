package tmptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import statistics.CircleData;
import statistics.MathUtil;
import statistics.ReadCSV;
import statistics.DataUtil;

public class CanvasTest extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		long start = System.currentTimeMillis();
		VBox box = new VBox();
		HBox root = new HBox();
		box.setAlignment(Pos.CENTER);
		Label lb = new Label("43");
		box.getChildren().add(lb);
		readyPaintPoint(root);
		Scene scene = new Scene(root, 1000, 600);
		stage.setScene(scene);
		stage.show();
		System.out.println("time = " + (System.currentTimeMillis() - start));
	}

	private void readyPaintPoint(HBox root) throws FileNotFoundException {
		Canvas canvas = new Canvas(1000, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		String filepath = "E:/第二批/云琼容_20170807104517900.jpg";
		String csvpath = "F:/eclipse-workspace-new1/LabelDemoNew/user/file/E.第二批/2___云琼容_20170807104517900";
//		String filepath = "E:/第二批/云琼容_20170807104518020.jpg";
//		String csvpath = "F:/eclipse-workspace-new1/LabelDemoNew/user/file/E.第二批/2___云琼容_20170807104518020";
		List<String[]> list = ReadCSV.readCSV(csvpath);
		FileInputStream fis = new FileInputStream(new File(filepath));
		Image img = new Image(fis, 530, 530, true, true);
		gc.drawImage(img, 0, 0);
		for(int i = 0; i < list.size(); i++) {
			String label = list.get(i)[7];
			CircleData circle = getCircle(label);
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
			}
						
			// 描点
			System.out.println(colorStr + "  left and top=" + circle.getLeft() + "=" + circle.getTop());
			paintPoint(circle, color, gc);
			
			// 画椭圆
			System.out.println(colorStr + " oval angle=" + circle.getAngle());
			paintOval(circle, color, gc);
			
			// 描椭圆中心点
			System.out.println(colorStr + " center =" + circle.getCenterX() + "=" + circle.getCenterY());
			paintCenterPoint(circle, color, gc);
		}
		
		List<CircleData> list1 = new ArrayList<CircleData>();
		for(int i = 0; i < list.size(); i++) {
			String label = list.get(i)[7];
			String colorStr = "";
			if(i == 0) {
				colorStr = "SEAGREEN"; 
			}else if(i == 1) {
				colorStr = "RED";
			}else if(i == 2) {
				colorStr = "BLUE";
			}else if(i == 3) {
				colorStr = "PINK";
			}
			CircleData circle = getCircle(label);
			System.out.println(colorStr + " self rotate = " + circle.getRotateAngle());
			list1.add(circle);
		}
		
		CircleData circle = DataUtil.getAvgCircleNew(list1);
		Color cl = Color.BLUE;
		paintAvgOval(circle, cl, gc);
		System.out.println(circle.getRotateAngle() + " = now angle = " + circle.getAngle());
		root.getChildren().add(canvas);
	}
	
	public static void paintAvgOval(CircleData circle, Color color, GraphicsContext gc) {
		// 描中心点
		paintCenterPoint(circle, color, gc);
		
		// 描点
		gc.save();
		gc.setFill(color);
		gc.fillOval(circle.getLeft(), circle.getTop(), 5, 5);
		gc.restore();
		
		// 描椭圆
		gc.save();
		gc.setStroke(color);
		gc.setTransform(new Affine(new Rotate(circle.getAngle(), circle.getLeft(), circle.getTop())));
		gc.strokeOval(circle.getLeft(), circle.getTop(), 
				circle.getRadius()*circle.getScaleX()*2, 
				circle.getRadius()*circle.getScaleY()*2);
		gc.restore();
	}

	public static void paintOval(CircleData circle, Color color, GraphicsContext gc) {
		gc.save();
		gc.setStroke(color);
		gc.setTransform(new Affine(new Rotate(circle.getAngle(), circle.getLeft(), circle.getTop())));
		gc.strokeOval(circle.getLeft(), circle.getTop(), 
				circle.getRadius()*circle.getScaleX()*2, 
				circle.getRadius()*circle.getScaleY()*2);
		gc.restore();
	}

	public static void paintPoint(CircleData circle, Color color, GraphicsContext gc) {
		gc.save();
		gc.setFill(color);
		gc.fillOval(circle.getLeft(), circle.getTop(), 5, 5);
		gc.restore();
	}
	
	public static void paintCenterPoint(CircleData circle, Color color, GraphicsContext gc) {
		gc.save();
		gc.setFill(color);
		gc.fillOval(circle.getCenterX(), circle.getCenterY(), 5, 5);
		gc.restore();
	}

	public static CircleData getCircle(String label) {
		JSONObject json = JSON.parseObject(label);
		JSONArray arr = json.getJSONArray("label_data");
		JSONObject obj = null;
		for(int i = 0; i < 3; i++) {
			JSONObject tmp = arr.getJSONObject(i);
			if("shipan".equals(tmp.getString("name"))) {
				obj = JSON.parseObject(tmp.getString("data"));
				break;
			}
		}
		String canvasData = json.getString("canvas_data");
		JSONObject canvasObj = JSON.parseObject(canvasData);
		double w = Double.parseDouble(canvasObj.getString("w"));
		double h = Double.parseDouble(canvasObj.getString("h"));
		double ratio = 0.0;
		//System.out.println(canvasObj.getString("w") + "=" + canvasObj.getString("h"));
		if(w >= h) {
			ratio = h / 530;
		}
		else {
			ratio = w / 530;
		}
		CircleData circle = new CircleData();
		circle.setAngle(Double.parseDouble(obj.getString("angle")));
		circle.setHeight(Double.parseDouble(obj.getString("height")));
		circle.setLeft(Double.parseDouble(obj.getString("left"))/ratio);
		circle.setName("shipan");
		circle.setOpacity(Double.parseDouble(obj.getString("opacity")));
		circle.setRadius(Double.parseDouble(obj.getString("radius")));
		circle.setScaleX(Double.parseDouble(obj.getString("scaleX"))/ratio);
		circle.setScaleY(Double.parseDouble(obj.getString("scaleY"))/ratio);
		circle.setStroke(obj.getString("stroke"));
		circle.setStrokeWidth(Double.parseDouble(obj.getString("strokeWidth")));
		circle.setTop(Double.parseDouble(obj.getString("top"))/ratio);
		circle.setWidth(Double.parseDouble(obj.getString("width")));
		String centerXY = MathUtil.getCenter(obj.getString("left"), obj.getString("top"),
				obj.getString("angle"), obj.getString("radius"), obj.getString("scaleX"),
				obj.getString("scaleY"), ratio);
		circle.setCenterX(Double.parseDouble(centerXY.split(",")[0]));
		circle.setCenterY(Double.parseDouble(centerXY.split(",")[1]));
		String point = MathUtil.getPoint(obj.getString("left"), obj.getString("top"),
				obj.getString("angle"), obj.getString("radius"), obj.getString("scaleX"),
				obj.getString("scaleY"), ratio);
		circle.setPointX(Double.parseDouble(point.split(",")[0]));
		circle.setPointY(Double.parseDouble(point.split(",")[1]));
		double rotateAngle = MathUtil.getAngle(centerXY, point);
		circle.setRotateAngle(rotateAngle);
		
		return circle;
	}
}
