package statisticsSecond;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author: jan
 * @date: 2018年6月20日 上午10:41:34
 */
public class DataUtil {
	
	public static CircleData getAllAvgCircleData(String name) {
		List<String> list = Constant.AllLabelData;
		List<CircleData> circleList = new ArrayList<CircleData>();
		for (String s : list) {
			circleList.add(strToObj(s, name));
		}
		CircleData circle = avgCircle(circleList);
		circle.setName(name);
		return circle;
	}

	// 初始化circle 视盘、视杯
	public static CircleData getAvgCircleData(String name) {
		List<String> list = Constant.AllLabelData;
		List<CircleData> circleList = new ArrayList<CircleData>();
		String mixuser = "";
		if("shipan".equals(name)) {
			mixuser = Constant.PanUser;
		}
		else if("shibei".equals(name)) {
			mixuser = Constant.BeiUser;
		}
		else {
			mixuser = Constant.CenterUser;
		}
		String[] user = mixuser.split(",");
		for (String s : list) {
			if(checkUser(s, user)) {
				circleList.add(strToObj(s, name));
			}
		}
		CircleData circle = avgCircle(circleList);
		circle.setName(name);
		return circle;
	}

	private static boolean checkUser(String s, String[] user) {
		boolean flag = false;
		String us = s.split("=")[0];
		for(String str : user) {
			if(str.equals(us)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private static CircleData strToObj(String s, String name) {
		String label = s.split("=")[1];
		JSONObject json = JSON.parseObject(label);
		JSONArray arr = json.getJSONArray("label_data");
		JSONObject obj = null;
		for (int i = 0; i < 3; i++) {
			JSONObject tmp = arr.getJSONObject(i);
			if (name.equals(tmp.getString("name"))) {
				obj = JSON.parseObject(tmp.getString("data"));
				break;
			}
		}
		String canvas = json.getString("canvas_data");
		JSONObject canvasObj = JSON.parseObject(canvas);
		double w = Double.parseDouble(canvasObj.getString("w"));
		double h = Double.parseDouble(canvasObj.getString("h"));
		double ratio = 0.0;
		// System.out.println(canvasObj.getString("w") + "=" +
		// canvasObj.getString("h"));
		if (w >= h) {
			ratio = h / 530;
		} else {
			ratio = w / 530;
		}
		CircleData circle = new CircleData();
		circle.setAngle(Double.parseDouble(obj.getString("angle")));
		circle.setHeight(Double.parseDouble(obj.getString("height")));
		circle.setLeft(Double.parseDouble(obj.getString("left")) / ratio);
		circle.setName(name);
		circle.setOpacity(Double.parseDouble(obj.getString("opacity")));
		circle.setRadius(Double.parseDouble(obj.getString("radius")));
		circle.setScaleX(Double.parseDouble(obj.getString("scaleX")) / ratio);
		circle.setScaleY(Double.parseDouble(obj.getString("scaleY")) / ratio);
		circle.setStroke(obj.getString("stroke"));
		circle.setStrokeWidth(Double.parseDouble(obj.getString("strokeWidth")));
		circle.setTop(Double.parseDouble(obj.getString("top")) / ratio);
		circle.setWidth(Double.parseDouble(obj.getString("width")));
		String centerXY = MathUtil.getCenter(obj.getString("left"), obj.getString("top"), obj.getString("angle"),
				obj.getString("radius"), obj.getString("scaleX"), obj.getString("scaleY"), ratio);
		circle.setCenterX(Double.parseDouble(centerXY.split(",")[0]));
		circle.setCenterY(Double.parseDouble(centerXY.split(",")[1]));
		// System.out.println("circle = " + circle);
		return circle;
	}

	private static CircleData avgCircle(List<CircleData> circleList) {
		CircleData circle = new CircleData();
		int size = circleList.size();

		double angle = 0;
		List<Double> list = new ArrayList<>();
		for (CircleData data : circleList) {
			// 特殊处理
			list.add(data.getAngle());
		}
		angle = changeAngle(list);
		circle.setAngle(angle / size);

		double centerX = 0;
		double centerY = 0;
		double height = 0;
		double left = 0;
		double opacity = 0;
		double radius = 0;
		double scaleX = 0;
		double scaleY = 0;
		double top = 0;
		double width = 0;
		double strokeWidth = 0;
		for (CircleData data : circleList) {
			centerX += data.getCenterX();
			centerY += data.getCenterY();
			height += data.getHeight();
			left += data.getLeft();
			opacity += data.getOpacity();
			radius += data.getRadius();
			scaleX += data.getScaleX();
			scaleY += data.getScaleY();
			strokeWidth += data.getStrokeWidth();
			top += data.getTop();
			width += data.getWidth();
		}
		circle.setCenterX(centerX / size);
		circle.setCenterY(centerY / size);
		circle.setHeight(height / size);
		circle.setLeft(left / size);
		circle.setOpacity(opacity / size);
		circle.setRadius(radius / size);
		circle.setScaleX(scaleX / size);
		circle.setScaleY(scaleY / size);
		circle.setStrokeWidth(strokeWidth / size);
		circle.setTop(top / size);
		circle.setWidth(width / size);

		if (circleList.size() != 0) {
			circle.setStroke(circleList.get(0).getStroke());
		}
		System.out.println("avg circle = " + circle);
		return circle;
	}

	private static double changeAngle(List<Double> list) {
		if(list.size() == 0) {
			double angle = Constant.Console.getPanAvg().getAngle();
			return angle*Constant.AllLabelData.size();
		}
		else {
			double min = list.get(0);
			double max = list.get(0);
			for (Double d : list) {
				if (d < min) {
					min = d;
				}
				if (d > max) {
					max = d;
				}
			}
			if (max - min >= 180) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) < 180.00) {
						list.set(i, list.get(i) + 360);
					}
				}
			}
			double angle = 0;
			for (Double d : list) {
				angle += d;
			}
			return angle;
		}
	}
	
	
	public static LineData getAvgLineData(String name) {
		List<String> list = Constant.AllLabelData;
		List<LineData> lineList = new ArrayList<LineData>();
		String mixuser = "";
		if("shipan".equals(name)) {
			mixuser = Constant.PanUser;
		}
		else if("shibei".equals(name)) {
			mixuser = Constant.BeiUser;
		}
		else {
			mixuser = Constant.CenterUser;
		}
		String[] user = mixuser.split(",");
		for (String s : list) {
			if(checkUser(s, user)) {
				System.out.println(s);
			    lineList.add(strToLine(s, name));
			}
		}
		LineData line = new LineData();
        int size = lineList.size();
		
		double angle = 0;
		for(LineData data : lineList) {
			angle = angle + data.getAngle();
		}
		line.setAngle(angle/size);
		
		double height = 0;
		for(LineData data : lineList) {
			height += data.getHeight();
		}
		line.setHeight(height/size);
		
		double left = 0;
		for (LineData data : lineList) {
			left += data.getLeft();
		}
		line.setLeft(left/size);
		
		double opacity = 0;
		for(LineData data : lineList) {
			opacity += data.getOpacity();
		}
		line.setOpacity(opacity/size);
		
		double scaleX = 0;
		for(LineData data : lineList) {
			scaleX += data.getScaleX();
		}
		line.setScaleX(scaleX/size);
		
		double scaleY = 0;
		for(LineData data : lineList) {
			scaleY += data.getScaleY();
		}
		line.setScaleY(scaleY/size);
		
		double strokeWidth = 0;
		for(LineData data : lineList) {
			strokeWidth += data.getStrokeWidth();
		}
		line.setStrokeWidth(strokeWidth/size + 1);
		
		double top = 0;
		for(LineData data : lineList) {
			top += data.getTop();
		}
		line.setTop(top/size);
		
		double width = 0;
		for(LineData data : lineList) {
			width += data.getWidth();
		}
		line.setWidth(width/size);
		return line;
	}

	private static LineData strToLine(String s, String name) {
		String label = s.split("=")[1];
		JSONObject json = JSON.parseObject(label);
		JSONArray arr = json.getJSONArray("label_data");
		JSONObject obj = null;
		for(int i = 0; i < 3; i++) {
			JSONObject tmp = arr.getJSONObject(i);
			if(name.equals(tmp.getString("name"))) {
				obj = JSON.parseObject(tmp.getString("data"));
				break;
			}
		}
		String canvas = json.getString("canvas_data");
		JSONObject canvasObj = JSON.parseObject(canvas);
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
		LineData line = new LineData();
		line.setAngle(Double.parseDouble(obj.getString("angle")));
		line.setHeight(Double.parseDouble(obj.getString("height")));
		line.setLeft(Double.parseDouble(obj.getString("left"))/ratio);
		line.setOpacity(Double.parseDouble(obj.getString("opacity")));
		line.setScaleX(Double.parseDouble(obj.getString("scaleX"))/ratio);
		line.setScaleY(Double.parseDouble(obj.getString("scaleY"))/ratio);
		line.setStrokeWidth(Double.parseDouble(obj.getString("strokeWidth")) + 1);
		line.setTop(Double.parseDouble(obj.getString("top"))/ratio);
		line.setWidth(Double.parseDouble(obj.getString("width")));
		//System.out.println("sole line = " + line);
		return line;
	}
	
	public static CircleData getSoleLabelData(int offset, String name) {
		List<String> list = Constant.AllLabelData;
		String label = list.get(offset).split("=")[1];
		System.out.println("label = " + label);
		JSONObject json = JSON.parseObject(label);
		JSONArray arr = json.getJSONArray("label_data");
		JSONObject obj = null;
		for(int i = 0; i < 3; i++) {
			JSONObject tmp = arr.getJSONObject(i);
			if(name.equals(tmp.getString("name"))) {
				obj = JSON.parseObject(tmp.getString("data"));
				break;
			}
		}
		String canvas = json.getString("canvas_data");
		JSONObject canvasObj = JSON.parseObject(canvas);
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
		circle.setName(name);
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
		return circle;
	}

	public static LineData getSoleLineData(int offset) {
		List<String> list = Constant.AllLabelData;
		String label = list.get(offset).split("=")[1];
		JSONObject json = JSON.parseObject(label);
		JSONArray arr = json.getJSONArray("label_data");
		JSONObject obj = null;
		String name = "amd";
		for(int i = 0; i < 3; i++) {
			JSONObject tmp = arr.getJSONObject(i);
			if(name.equals(tmp.getString("name"))) {
				obj = JSON.parseObject(tmp.getString("data"));
				break;
			}
		}
		String canvas = json.getString("canvas_data");
		JSONObject canvasObj = JSON.parseObject(canvas);
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
		//JSONArray arr1 = obj.getJSONArray("objects");
		//JSONObject obj1 = arr1.getJSONObject(0);
		//JSONObject obj2 = arr1.getJSONObject(1);
		LineData line = new LineData();
		line.setAngle(Double.parseDouble(obj.getString("angle")));
		line.setHeight(Double.parseDouble(obj.getString("height")));
		line.setLeft(Double.parseDouble(obj.getString("left"))/ratio);
				//+ Double.parseDouble(obj1.getString("left")) + 
				//Double.parseDouble(obj2.getString("left")));
		line.setOpacity(Double.parseDouble(obj.getString("opacity")));
		line.setScaleX(Double.parseDouble(obj.getString("scaleX"))/ratio);
		line.setScaleY(Double.parseDouble(obj.getString("scaleY"))/ratio);
		line.setStrokeWidth(Double.parseDouble(obj.getString("strokeWidth")));
		line.setTop(Double.parseDouble(obj.getString("top"))/ratio);
				//+ Double.parseDouble(obj1.getString("top")) + 
				//Double.parseDouble(obj2.getString("top")));
		line.setWidth(Double.parseDouble(obj.getString("width")));
		System.out.println("sole line = " + line);
		return line;
	}

}
