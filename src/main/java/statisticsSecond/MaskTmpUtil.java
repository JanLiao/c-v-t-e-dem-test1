package statisticsSecond;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MaskTmpUtil {

	public static Mask generateData() {
		Mask mask = new Mask();
		mask.setPanUser(Constant.PanUser);
		mask.setBeiUser(Constant.BeiUser);
		int[][] allPanMask = new int[1634][1634];
		int[][] allBeiMask = new int[1634][1634];
		Map<String, Integer[][]> panMap = new HashMap<String, Integer[][]>();  //单张图片图片视盘mask
		Map<String, Integer[][]> beiMap = new HashMap<String, Integer[][]>();  //单张图片图片视杯mask
		for(String s : Constant.AllLabelData) {
			String user = s.split("=")[0];
			String label = s.split("=")[1];
			System.out.println("user = " + user);
			
			Integer[][] solePan = getMask(label, "shipan");
			Integer[][] soleBei = getMask(label, "shibei");
			for(int i = 0; i < 1634; i++) {
				for(int j = 0; j < 1634; j++) {
					//System.out.println("sole pan = " + solePan[i][j].intValue());
					if(solePan[i][j].intValue() == 1) {
						//System.out.println(allPanMask[i][j]);
						allPanMask[i][j] += 1;
					}
					if(soleBei[i][j].intValue() == 1) {
						//System.out.println(allPanMask[i][j]);
						allBeiMask[i][j] += 1;
					}
				}
			}
			panMap.put(user, solePan);
			beiMap.put(user, soleBei);
		}
		mask.setAllPanMask(allPanMask);
		mask.setAllBeiMask(allBeiMask);
		mask.setAllSolePanMask(panMap);
		mask.setAllSoleBeiMask(beiMap);
		System.out.println("mask66666 = " + mask);
		
		// 设置curr mask
		int[][] curPanMask = getCurMask("pan", mask);
		int[][] curBeiMask = getCurMask("bei", mask);
		mask.setCurPanMask(curPanMask);
		mask.setCurBeiMask(curBeiMask);
		return mask;
	}
	
	private static int[][] getCurMask(String name, Mask mask) {
		int[][] curMask = new int[1634][1634];
		if("pan".equals(name)) {
			String[] s = mask.getPanUser().split(",");
			for(String st : s) {
				Integer[][] tmp = mask.getAllSolePanMask().get(st);
				for(int i = 0; i < 1634; i++) {
					for(int j = 0; j < 1634; j++) {
					   curMask[i][j] = curMask[i][j] + tmp[i][j].intValue();
					}
				}
			}
		}
		else if("bei".equals(name)) {
			String[] s = mask.getBeiUser().split(",");
			for(String st : s) {
				Integer[][] tmp = mask.getAllSoleBeiMask().get(st);
				for(int i = 0; i < 1634; i++) {
					for(int j = 0; j < 1634; j++) {
					   curMask[i][j] = curMask[i][j] + tmp[i][j].intValue();
					}
				}
			}
		}
		return curMask;
	}

	public static Integer[][] getMask(String label, String name) {
		CircleData circle = getCircle(label, name);	
		Integer[][] data = MathUtil.getMaskTmp(circle);
		return data;
	}

	private static CircleData getCircle(String label, String name) {
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
		CircleData circle = new CircleData();
		String canvas = json.getString("canvas_data");
		JSONObject canvasObj = JSON.parseObject(canvas);
		double w = Double.parseDouble(canvasObj.getString("w"));
		double h = Double.parseDouble(canvasObj.getString("h"));
		double ratio = 0.0;
		//System.out.println(canvasObj.getString("w") + "=" + canvasObj.getString("h"));
		if(w >= h) {
			ratio = h / 1634;
		}
		else {
			ratio = w / 1634;
		}
		circle.setAngle(Double.parseDouble(obj.getString("angle")));
		circle.setHeight(Double.parseDouble(obj.getString("height")));
		circle.setLeft(Double.parseDouble(obj.getString("left"))/ratio);
		circle.setOpacity(Double.parseDouble(obj.getString("opacity")));
		circle.setRadius(Double.parseDouble(obj.getString("radius")));
		circle.setScaleX(Double.parseDouble(obj.getString("scaleX"))/ratio);
		circle.setScaleY(Double.parseDouble(obj.getString("scaleY"))/ratio);
		circle.setStroke(obj.getString("stroke"));
		circle.setStrokeWidth(Double.parseDouble(obj.getString("strokeWidth")));
		circle.setTop(Double.parseDouble(obj.getString("top"))/ratio);
		double ratio1 = 1634/530;
		if("shipan".equals(name)) {
			CircleData pan = DataUtil.getAllAvgCircleData("shipan");
			circle.setCenterX(pan.getCenterX()*ratio1);
			circle.setCenterY(pan.getCenterY()*ratio1);
//			circle.setCenterX(Constant.panAvg.getCenterX());
//			circle.setCenterY(Constant.panAvg.getCenterY());
		}
		else if("shibei".equals(name)) {
			CircleData bei = DataUtil.getAllAvgCircleData("shibei");
			circle.setCenterX(bei.getCenterX()*ratio1);
			circle.setCenterY(bei.getCenterY()*ratio1);
		}
		return circle;
	}
	
	public static void paintAllMask(GraphicsContext gc, String name, int num) {
		//generateData();
		if("shipan".equals(name)) {
			Mask mask = Constant.mask;
			int[][] panMask = mask.getAllPanMask();
			//从x轴扫描
			for(int i = 1; i < 1633; i++) {
				for(int j = 1; j < 1633; j++) {
					if(panMask[i][j] >= num) {
						if(panMask[i][j - 1] < num || panMask[i][j + 1] < num
								|| panMask[i - 1][j] < num || panMask[i + 1][j] < num
								|| panMask[i - 1][j - 1] < num || panMask[i + 1][j + 1] < num
								|| panMask[i - 1][j + 1] < num || panMask[i + 1][j -1] < num
								) {
							//绘制点  从x轴扫
							paintPointTmp(gc, name, i, j, Color.BLUE);
							//重y轴扫描
							//paintPoint(gc, name, i, j);
						}
					}
				}
			}
			
			
		}
		else if("shibei".equals(name)) {
			Mask mask = Constant.mask;
			int[][] beiMask = mask.getAllBeiMask();
			
			for(int i = 1; i < 1633; i++) {
				for(int j = 1; j < 1633; j++) {
					//System.out.println("flag = " + beiMask[i][j]);
					if(beiMask[i][j] >= num) {
						//System.out.println("pm = " + beiMask[i][j]);
						if(beiMask[i][j - 1] < num || beiMask[i][j + 1] < num
								|| beiMask[i - 1][j] < num || beiMask[i + 1][j] < num
								|| beiMask[i - 1][j - 1] < num || beiMask[i + 1][j + 1] < num
								|| beiMask[i - 1][j + 1] < num || beiMask[i + 1][j -1] < num
								) {
							//绘制点
							paintPointTmp(gc, name, i, j, Color.BLUE);
							//paintPoint1(gc, name, i, 1060 - j);
						}
					}
				}
			}
		}
		
	}
	
	public static void paintThreeColor(GraphicsContext gc, String name, int num) {
		//generateData();
		if("shipan".equals(name)) {
			Mask mask = Constant.mask;
			int[][] panMask = mask.getCurPanMask();
			//从x轴扫描
			for(int i = 1; i < 1633; i++) {
				for(int j = 1; j < 1633; j++) {
					if(panMask[i][j] >= num) {
						if(panMask[i][j - 1] < num || panMask[i][j + 1] < num
								|| panMask[i - 1][j] < num || panMask[i + 1][j] < num
								|| panMask[i - 1][j - 1] < num || panMask[i + 1][j + 1] < num
								|| panMask[i - 1][j + 1] < num || panMask[i + 1][j -1] < num
								) {
							//绘制点  从x轴扫
							paintPointTmp(gc, name, i, j, Color.SEAGREEN);
							//重y轴扫描
							//paintPoint(gc, name, i, j);
						}
					}
				}
			}
			
			
		}
		else if("shibei".equals(name)) {
			Mask mask = Constant.mask;
			int[][] beiMask = mask.getCurBeiMask();
			
			for(int i = 1; i < 1633; i++) {
				for(int j = 1; j < 1633; j++) {
					//System.out.println("flag = " + beiMask[i][j]);
					if(beiMask[i][j] >= num) {
						//System.out.println("pm = " + beiMask[i][j]);
						if(beiMask[i][j - 1] < num || beiMask[i][j + 1] < num
								|| beiMask[i - 1][j] < num || beiMask[i + 1][j] < num
								|| beiMask[i - 1][j - 1] < num || beiMask[i + 1][j + 1] < num
								|| beiMask[i - 1][j + 1] < num || beiMask[i + 1][j -1] < num
								) {
							//绘制点
							paintPointTmp(gc, name, i, j, Color.SEAGREEN);
							//paintPoint1(gc, name, i, 1060 - j);
						}
					}
				}
			}
		}
		
	}
	
	public static void paintTmp(GraphicsContext gc, String name, int num) {
		//generateData();
		if("shipan".equals(name)) {
			Mask mask = Constant.mask;
			int[][] panMask = mask.getCurPanMask();
			//从x轴扫描
			for(int i = 1; i < 1633; i++) {
				for(int j = 1; j < 1633; j++) {
					if(panMask[i][j] >= num) {
						if(panMask[i][j - 1] < num || panMask[i][j + 1] < num
								|| panMask[i - 1][j] < num || panMask[i + 1][j] < num
								|| panMask[i - 1][j - 1] < num || panMask[i + 1][j + 1] < num
								|| panMask[i - 1][j + 1] < num || panMask[i + 1][j -1] < num
								) {
							//绘制点  从x轴扫
							paintPointTmp(gc, name, i, j);
							//重y轴扫描
							//paintPoint(gc, name, i, j);
						}
					}
				}
			}
			
			
		}
		else if("shibei".equals(name)) {
			Mask mask = Constant.mask;
			int[][] beiMask = mask.getCurBeiMask();
			
			for(int i = 1; i < 1633; i++) {
				for(int j = 1; j < 1633; j++) {
					//System.out.println("flag = " + beiMask[i][j]);
					if(beiMask[i][j] >= num) {
						//System.out.println("pm = " + beiMask[i][j]);
						if(beiMask[i][j - 1] < num || beiMask[i][j + 1] < num
								|| beiMask[i - 1][j] < num || beiMask[i + 1][j] < num
								|| beiMask[i - 1][j - 1] < num || beiMask[i + 1][j + 1] < num
								|| beiMask[i - 1][j + 1] < num || beiMask[i + 1][j -1] < num
								) {
							//绘制点
							paintPointTmp(gc, name, i, j);
							//paintPoint1(gc, name, i, 1060 - j);
						}
					}
				}
			}
		}
		
	}
	
	private static void paintPointTmp(GraphicsContext gc, String name, int i, int j, Color color) {
		CircleData pan = DataUtil.getAllAvgCircleData("shipan");
		CircleData bei = DataUtil.getAllAvgCircleData("shibei");
		double ratio = 1634/530;
		if("shipan".equals(name)) {
			gc.save();
			gc.setFill(color);
			double relativeX = i;
	    	double relativeY = 1634 - j;
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		else if("shibei".equals(name)) {
			gc.save();
			gc.setFill(color);
			double relativeX = i;
	    	double relativeY = 1634 - j;
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		
	}
	
	private static void paintPointTmp(GraphicsContext gc, String name, int i, int j) {
		CircleData pan = DataUtil.getAllAvgCircleData("shipan");
		CircleData bei = DataUtil.getAllAvgCircleData("shibei");
		double ratio = 1634/530;
		if("shipan".equals(name)) {
			gc.save();
			gc.setFill(Color.BLUE);
			double relativeX = i;
	    	double relativeY = 1634 - j;
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		else if("shibei".equals(name)) {
			gc.save();
			gc.setFill(Color.SEAGREEN);
			double relativeX = i;
	    	double relativeY = 1634 - j;
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		
	}
}
