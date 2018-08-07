package statisticsSecond;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** 
* @author: jan 
* @date: 2018年5月27日 上午12:54:24 
*/
public class MaskUtil {
	
	public static Mask generateDataNew() {
		//long start = System.currentTimeMillis();
		Mask mask = new Mask();
		mask.setPanUser(Constant.PanUser);
		mask.setBeiUser(Constant.BeiUser);
		int[][] allPanMask = new int[530][530];
		int[][] allBeiMask = new int[530][530];
		Map<String, Integer[][]> panMap = new HashMap<String, Integer[][]>();  //单张图片图片视盘mask
		Map<String, Integer[][]> beiMap = new HashMap<String, Integer[][]>();  //单张图片图片视杯mask
		for(String s : Constant.AllLabelData) {
			String user = s.split("=")[0];
			String label = s.split("=")[1];
			System.out.println("user = " + user);
			
			Integer[][] solePan = getMask(label, "shipan");
			Integer[][] soleBei = getMask(label, "shibei");
			for(int i = 0; i < 530; i++) {
				for(int j = 0; j < 530; j++) {
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
	
	public static Mask generateNewData() {
		Mask mask = new Mask();
		mask.setPanUser(Constant.PanUser);
		mask.setBeiUser(Constant.BeiUser);
		int[][] curPanMask = new int[530][530];
		int[][] curBeiMask = new int[530][530];

		Integer[][] solePan = getMask1("shipan");
		for (int i = 0; i < 530; i++) {
			for (int j = 0; j < 530; j++) {
				if (solePan[i][j].intValue() == 1) {
					curPanMask[i][j] += 1;
				}
			}
		}

		Integer[][] soleBei = getMask1("shibei");
		for (int i = 0; i < 530; i++) {
			for (int j = 0; j < 530; j++) {
				if (soleBei[i][j].intValue() == 1) {
					curBeiMask[i][j] += 1;
				}
			}
		}

		mask.setCurPanMask(curPanMask);
		mask.setCurBeiMask(curBeiMask);
		System.out.println("mask66666 = " + mask);
		return mask;
	}
	
	public static Mask generateTmpData() {
		// long start = System.currentTimeMillis();
		Mask mask = new Mask();
		mask.setPanUser(Constant.PanUser);
		mask.setBeiUser(Constant.BeiUser);
		int[][] curPanMask = new int[530][530];
		int[][] curBeiMask = new int[530][530];

		for(String s : Constant.AllLabelData) {
			String user = s.split("=")[0];
			String label = s.split("=")[1];
			
			Integer[][] solePan = getMask(label, "shipan");
			for(int i = 0; i < 530; i++) {
				for(int j = 0; j < 530; j++) {
					if(solePan[i][j].intValue() == 1) {
						curPanMask[i][j] += 1;
					}
				}
			}
		}
		
		for(String s : Constant.AllLabelData) {
			String user = s.split("=")[0];
			String label = s.split("=")[1];
			//System.out.println("user = " + user);
			
			Integer[][] soleBei = getMask(label, "shibei");
			for(int i = 0; i < 530; i++) {
				for(int j = 0; j < 530; j++) {
					if(soleBei[i][j].intValue() == 1) {
						curBeiMask[i][j] += 1;
					}
				}
			}
		}

		mask.setCurPanMask(curPanMask);
		mask.setCurBeiMask(curBeiMask);
		System.out.println("mask66666 = " + mask);
		return mask;
	}

	public static Mask generateData() {
		//long start = System.currentTimeMillis();
		Mask mask = new Mask();
		mask.setPanUser(Constant.PanUser);
		mask.setBeiUser(Constant.BeiUser);
		int[][] curPanMask = new int[530][530];
		int[][] curBeiMask = new int[530][530];
		String[] mixpan = Constant.PanUser.split(",");
		
		if(Constant.PanUser == null || Constant.PanUser.equals("")) {
			mixpan = new String[Constant.AllLabelData.size()];
			for(int i = 0; i < Constant.AllLabelData.size(); i++) {
				mixpan[i] = Constant.AllLabelData.get(i).split("=")[0];
			}
		}
		
		//System.out.println("mixpan len = " + mixpan.length);
		
		String[] mixbei = Constant.BeiUser.split(",");
		
		if(Constant.BeiUser == null || Constant.BeiUser.equals("")) {
			mixbei = new String[Constant.AllLabelData.size()];
			for(int i = 0; i < Constant.AllLabelData.size(); i++) {
				mixbei[i] = Constant.AllLabelData.get(i).split("=")[0];
			}
		}
		
		for(String s : Constant.AllLabelData) {
			String user = s.split("=")[0];
			String label = s.split("=")[1];
			//System.out.println("user = " + user);
			
			if(checkUser(user, mixpan)) {
				System.out.println("user = " + user);
				Integer[][] solePan = getMask(label, "shipan");
				for(int i = 0; i < 530; i++) {
					for(int j = 0; j < 530; j++) {
						if(solePan[i][j].intValue() == 1) {
							curPanMask[i][j] += 1;
						}
					}
				}
			}
		}
		
		for(String s : Constant.AllLabelData) {
			String user = s.split("=")[0];
			String label = s.split("=")[1];
			//System.out.println("user = " + user);
			
			if(checkUser(user, mixbei)) {
				Integer[][] soleBei = getMask(label, "shibei");
				for(int i = 0; i < 530; i++) {
					for(int j = 0; j < 530; j++) {
						if(soleBei[i][j].intValue() == 1) {
							curBeiMask[i][j] += 1;
						}
					}
				}
			}
		}
		mask.setCurPanMask(curPanMask);
		mask.setCurBeiMask(curBeiMask);
		System.out.println("mask66666 = " + mask);
		return mask;
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

	public static int[][] getCurMask(String name, Mask mask) {
		int[][] curMask = new int[530][530];
		if("pan".equals(name)) {
			String[] s = mask.getPanUser().split(",");
			for(String st : s) {
				Integer[][] tmp = mask.getAllSolePanMask().get(st);
				for(int i = 0; i < 530; i++) {
					for(int j = 0; j < 530; j++) {
					   curMask[i][j] = curMask[i][j] + tmp[i][j].intValue();
					}
				}
			}
		}
		else if("bei".equals(name)) {
			String[] s = mask.getBeiUser().split(",");
			for(String st : s) {
				Integer[][] tmp = mask.getAllSoleBeiMask().get(st);
				for(int i = 0; i < 530; i++) {
					for(int j = 0; j < 530; j++) {
					   curMask[i][j] = curMask[i][j] + tmp[i][j].intValue();
					}
				}
			}
		}
		return curMask;
	}

	private static boolean checkUser(String user, String userStr) {
		boolean flag = false;
		String[] s = userStr.split(",");
		for(String st : s) {
			if(st.equals(user)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static Integer[][] getMask1(String name){
		Integer[][] data = null;
		if("shipan".equals(name)) {
			CircleData circle = Constant.panAvg;
			data = MathUtil.getMask(circle);
		}else if("shibei".equals(name)) {
			CircleData circle = Constant.beiAvg;
			data = MathUtil.getMask(circle);
		}
		return data;
	}
	
	private static Integer[][] getMask(String label, String name) {
		CircleData circle = getCircle(label, name);	
		Integer[][] data = MathUtil.getMask(circle);
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
			ratio = h / 530;
		}
		else {
			ratio = w / 530;
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
		if("shipan".equals(name)) {
			CircleData pan = DataUtil.getAllAvgCircleData("shipan");
			circle.setCenterX(pan.getCenterX());
			circle.setCenterY(pan.getCenterY());
//			circle.setCenterX(Constant.panAvg.getCenterX());
//			circle.setCenterY(Constant.panAvg.getCenterY());
		}
		else if("shibei".equals(name)) {
			CircleData bei = DataUtil.getAllAvgCircleData("shibei");
			circle.setCenterX(bei.getCenterX());
			circle.setCenterY(bei.getCenterY());
		}
		return circle;
	}
	
	public static void paintNew(GraphicsContext gc, String name, int num) {
		//generateData();
		if("shipan".equals(name)) {
			Mask mask = Constant.mask;
			int[][] panMask = mask.getAllPanMask();
			//从x轴扫描
			for(int i = 1; i < 529; i++) {
				for(int j = 1; j < 529; j++) {
					if(panMask[i][j] >= num) {
						if(panMask[i][j - 1] < num || panMask[i][j + 1] < num
								|| panMask[i - 1][j] < num || panMask[i + 1][j] < num
								|| panMask[i - 1][j - 1] < num || panMask[i + 1][j + 1] < num
								|| panMask[i - 1][j + 1] < num || panMask[i + 1][j -1] < num
								) {
							//绘制点  从x轴扫
							paintPoint2(gc, name, i, j);
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
			
			for(int i = 1; i < 529; i++) {
				for(int j = 1; j < 529; j++) {
					//System.out.println("flag = " + beiMask[i][j]);
					if(beiMask[i][j] >= num) {
						//System.out.println("pm = " + beiMask[i][j]);
						if(beiMask[i][j - 1] < num || beiMask[i][j + 1] < num
								|| beiMask[i - 1][j] < num || beiMask[i + 1][j] < num
								|| beiMask[i - 1][j - 1] < num || beiMask[i + 1][j + 1] < num
								|| beiMask[i - 1][j + 1] < num || beiMask[i + 1][j -1] < num
								) {
							//绘制点
							paintPoint2(gc, name, i, j);
							//paintPoint1(gc, name, i, 1060 - j);
						}
					}
				}
			}
		}
		else {
			System.out.println("待使用");
		}
		
	}
	
	public static void paintTmp(GraphicsContext gc, String name, int num) {
		//generateData();
		if("shipan".equals(name)) {
			Mask mask = Constant.mask;
			int[][] panMask = mask.getCurPanMask();
			//从x轴扫描
			for(int i = 1; i < 529; i++) {
				for(int j = 1; j < 529; j++) {
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
			
			for(int i = 1; i < 529; i++) {
				for(int j = 1; j < 529; j++) {
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

	public static void paint(GraphicsContext gc, String name, int num) {
		//generateData();
		if("shipan".equals(name)) {
			Mask mask = Constant.mask;
			int[][] panMask = mask.getCurPanMask();
			//从x轴扫描
			for(int i = 1; i < 529; i++) {
				for(int j = 1; j < 529; j++) {
					if(panMask[i][j] >= num) {
						if(panMask[i][j - 1] < num || panMask[i][j + 1] < num
								|| panMask[i - 1][j] < num || panMask[i + 1][j] < num
								|| panMask[i - 1][j - 1] < num || panMask[i + 1][j + 1] < num
								|| panMask[i - 1][j + 1] < num || panMask[i + 1][j -1] < num
								) {
							//绘制点  从x轴扫
							paintPoint(gc, name, i, j);
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
			
			for(int i = 1; i < 529; i++) {
				for(int j = 1; j < 529; j++) {
					//System.out.println("flag = " + beiMask[i][j]);
					if(beiMask[i][j] >= num) {
						//System.out.println("pm = " + beiMask[i][j]);
						if(beiMask[i][j - 1] < num || beiMask[i][j + 1] < num
								|| beiMask[i - 1][j] < num || beiMask[i + 1][j] < num
								|| beiMask[i - 1][j - 1] < num || beiMask[i + 1][j + 1] < num
								|| beiMask[i - 1][j + 1] < num || beiMask[i + 1][j -1] < num
								) {
							//绘制点
							paintPoint(gc, name, i, j);
							//paintPoint1(gc, name, i, 1060 - j);
						}
					}
				}
			}
		}
		else {
			System.out.println("待使用");
		}
		
	}

	private static void paintPoint1(GraphicsContext gc, String name, int i, int j) {
		//final int NUM_IMGS = 5;
		//final double opacity = 0.6;
        //System.out.println(opacity);
        //gc.setGlobalAlpha(opacity);
		if("shipan".equals(name)) {
			gc.save();
			gc.setFill(Color.BLUE);
			double relativeX = (Constant.panAvg.getCenterX() - 53 + 0.1*i)*4 - (Constant.panAvg.getCenterX()*4 - 265);
	    	double relativeY = (Constant.panAvg.getCenterY() + 53 - 0.1*j)*4 - (Constant.panAvg.getCenterY()*4 - 265);
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
			
//			gc.save();
//			gc.setFill(Color.BLUE);
//			double relativeX1 = (Constant.panAvg.getCenterX() - 53 + 0.1*i)*4 - (Constant.panAvg.getCenterX()*4 - 265);
//	    	double relativeY1 = (Constant.panAvg.getCenterY() - 53 - 0.1*j)*4 - (Constant.panAvg.getCenterY()*4 - 265);
//			//System.out.println("x = " + relativeX + "  " + relativeY);
//	    	gc.fillOval(relativeX1, relativeY1, 2, 2);
//			gc.restore();
		}
		else if("shibei".equals(name)) {
			gc.save();
			gc.setFill(Color.BLUE);
			double relativeX = (Constant.beiAvg.getCenterX() - 53 + 0.1*i)*4 - (Constant.beiAvg.getCenterX()*4 - 265);
	    	double relativeY = (Constant.beiAvg.getCenterY() + 53 - 0.1*j)*4 - (Constant.beiAvg.getCenterY()*4 - 265);
			System.out.println("x = " + relativeX + "  " + relativeY);
	    	//gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		
	}
	
	private static void paintPoint2(GraphicsContext gc, String name, int i, int j) {
		//final int NUM_IMGS = 5;
		//final double opacity = 0.6;
        //System.out.println(opacity);
        //gc.setGlobalAlpha(opacity);
		
		
//		double ratio = 2124/530;
		double ratio = 1634/530;
		if("shipan".equals(name)) {
			gc.save();
			gc.setFill(Color.BLUE);
			double relativeX = (Constant.panAvg.getCenterX() - 53 + 0.2*i)*ratio;
	    	double relativeY = (Constant.panAvg.getCenterY() + 53 - 0.2*j)*ratio;
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		else if("shibei".equals(name)) {
			gc.save();
			gc.setFill(Color.BLUE);
			double relativeX = (Constant.beiAvg.getCenterX() - 53 + 0.2*i)*ratio;
	    	double relativeY = (Constant.beiAvg.getCenterY() + 53 - 0.2*j)*ratio;
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		
	}
	
	private static void paintPoint(GraphicsContext gc, String name, int i, int j) {
		//final int NUM_IMGS = 5;
		//final double opacity = 0.6;
        //System.out.println(opacity);
        //gc.setGlobalAlpha(opacity);
		
		// 第一批
		//double ratio = 2124/530;
		double ratio = 1634/530;
		if("shipan".equals(name)) {
			gc.save();
			gc.setFill(Color.SEAGREEN);
			double relativeX = (Constant.panAvg.getCenterX() - 53 + 0.2*i)*ratio;
	    	double relativeY = (Constant.panAvg.getCenterY() + 53 - 0.2*j)*ratio;
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		else if("shibei".equals(name)) {
			gc.save();
			gc.setFill(Color.SEAGREEN);
			double relativeX = (Constant.beiAvg.getCenterX() - 53 + 0.2*i)*ratio;
	    	double relativeY = (Constant.beiAvg.getCenterY() + 53 - 0.2*j)*ratio;
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		
	}
	
	private static void paintPointTmp(GraphicsContext gc, String name, int i, int j) {
		//final int NUM_IMGS = 5;
		//final double opacity = 0.6;
        //System.out.println(opacity);
        //gc.setGlobalAlpha(opacity);
		
		// 第一批
		//double ratio = 2124/530;
		CircleData pan = DataUtil.getAllAvgCircleData("shipan");
		CircleData bei = DataUtil.getAllAvgCircleData("shibei");
		double ratio = 1634/530;
		if("shipan".equals(name)) {
			gc.save();
			gc.setFill(Color.SEAGREEN);
			double relativeX = (pan.getCenterX() - 53 + 0.2*i)*ratio;
	    	double relativeY = (pan.getCenterY() + 53 - 0.2*j)*ratio;
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		else if("shibei".equals(name)) {
			gc.save();
			gc.setFill(Color.SEAGREEN);
			double relativeX = (bei.getCenterX() - 53 + 0.2*i)*ratio;
	    	double relativeY = (bei.getCenterY() + 53 - 0.2*j)*ratio;
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		
	}

}
