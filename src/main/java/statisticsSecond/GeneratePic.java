package statisticsSecond;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;

import javafx.application.Application;
import javafx.stage.Stage;

/** 
* @author: jan 
* @date: 2018年6月20日 上午9:28:59 
*/
public class GeneratePic extends Application {
	
	private static String imgpath = "E:/第二批";
	//private static String imgpath = "E:/BaiduNetdiskDownload/眼底照";
	//C:/Users/CVTE/Desktop/demoAnalysis/label-view-v1.3-1/admin/mixlabel/sole
	//private static String mixpath = "C:/Users/CVTE/Desktop/tmp";
	//private static String mixpath = "C:/Users/CVTE/Desktop/demoAnalysis/label-view-v1.3-1/admin/mixlabel/sole";
	private static String mixpath = "F:/eclipse-workspace-new1/LabelViewNew/admin/mixlabel/sole";
	//private static String statpath = "E:/BaiduNetdiskDownload/统计平均掩码/掩码图";
	//private static String statpath = "C:/Users/CVTE/Desktop/img_cut/统计";

	public static void main(String[] args) {
		launch(args);
	}
	
	public static void initTmpConstant(ConsoleImg obj) {
		ColorUtil.initColor();
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
	
	private static void initNewConstant(ConsoleImg obj) {
		ColorUtil.initColor();
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
		Mask mask = MaskUtil.generateNewData();
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

	private static void initConstant(ConsoleImg obj) {
		ColorUtil.initColor();
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
		Mask mask = MaskUtil.generateData();
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
		
		System.out.println(Constant.panValue + "===" + Constant.beiValue);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		//Excelexport(mixpath);
		//maskGenerate();
		//maskGenerateNew1();
		save();
		System.exit(0);
	}
	
	private void maskGenerateNew() throws FileNotFoundException, IOException {
		String imgpath = "E:\\第一批\\青光眼图片";
		String imgpath1 = "E:\\第一批\\非青光眼图片";
		String savepath = "E:\\package\\第一批mask图1";
		getMaskNew(imgpath, savepath, "g");
		getMaskNew(imgpath1, savepath, "n");
	}
	
	private void maskGenerateNew1() throws FileNotFoundException, IOException {
		String imgpath = "E:\\第一批\\青光眼图片";
		//String imgpath1 = "E:\\第一批\\非青光眼图片";
		String imgpath1 = "C:\\Users\\CVTE\\Desktop\\HP打印\\sole\\tm1";
		//String savepath = "E:\\package\\第一批mask图2";
		String savepath = "C:/Users/CVTE/Desktop/HP打印/sole/tm";
		//getMaskNew1(imgpath, savepath, "g");
		getMaskNew1(imgpath1, savepath, "n");
	}
	
	private void maskGenerate() throws FileNotFoundException, IOException {
		String imgpath = "E:\\第一批\\青光眼图片";
		String imgpath1 = "E:\\第一批\\非青光眼图片";
		String savepath = "E:\\package\\第一批mask图";
		getMask(imgpath, savepath, "g");
		getMask(imgpath1, savepath, "n");
	}
	
	private void getMaskNew1(String imgpath, String savepath, String flag) throws FileNotFoundException, IOException {
		int num = 1;
		DecimalFormat df = new DecimalFormat("0000");
		File[] files = new File(imgpath).listFiles();
		for(File f : files) {
			String name = f.getName();
			File[] tmp = new File(imgpath + "/" + name).listFiles();
			for(File ff : tmp) {
				String csvPath = mixpath + "/" + name + "_" + ff.getName().split("[.]")[0];
				List<String[]> listStr = ReadCSV.readCSV(csvPath);
				String imgMixData = listStr.get(listStr.size() - 1)[2];
				ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
				initConstant(obj);
				
				SaveAll.AllMaskNew1(name + "_" + ff.getName().split("[.]")[0], flag + df.format(num), savepath, obj);
				
				System.out.println(name + "_" + ff.getName().split("[.]")[0] + "=" + flag + df.format(num));
				
				num++;
			}
		}
	}
	
	private void getMaskNew(String imgpath, String savepath, String flag) throws FileNotFoundException, IOException {
		int num = 1;
		DecimalFormat df = new DecimalFormat("0000");
		File[] files = new File(imgpath).listFiles();
		for(File f : files) {
			String name = f.getName();
			File[] tmp = new File(imgpath + "/" + name).listFiles();
			for(File ff : tmp) {
				String csvPath = mixpath + "/" + name + "_" + ff.getName().split("[.]")[0];
				List<String[]> listStr = ReadCSV.readCSV(csvPath);
				String imgMixData = listStr.get(listStr.size() - 1)[2];
				ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
				initTmpConstant(obj);
				
				SaveAll.AllMaskNew(name + "_" + ff.getName().split("[.]")[0], flag + df.format(num), savepath);
				
				System.out.println(name + "_" + ff.getName().split("[.]")[0] + "=" + flag + df.format(num));
				
				num++;
			}
		}
	}

	private void getMask(String imgpath, String savepath, String flag) throws FileNotFoundException, IOException {
		int num = 1;
		DecimalFormat df = new DecimalFormat("0000");
		File[] files = new File(imgpath).listFiles();
		for(File f : files) {
			String name = f.getName();
			File[] tmp = new File(imgpath + "/" + name).listFiles();
			for(File ff : tmp) {
				String csvPath = mixpath + "/" + name + "_" + ff.getName().split("[.]")[0];
				List<String[]> listStr = ReadCSV.readCSV(csvPath);
				String imgMixData = listStr.get(listStr.size() - 1)[2];
				ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
				initConstant(obj);
				
				SaveAll.AllMask(name + "_" + ff.getName().split("[.]")[0], flag + df.format(num), savepath);
				
				System.out.println(name + "_" + ff.getName().split("[.]")[0] + "=" + flag + df.format(num));
				
				num++;
			}
		}
	}

	public static void save() throws IOException {
		//File[] files = new File("C:\\Users\\CVTE\\Desktop\\HP打印\\sole").listFiles();
		File[] files = new File(mixpath).listFiles();
		List<IOU> list = new ArrayList<IOU>();
		int offset = 1;
		for(File f : files) {
			//String[] name = f.getName().split("_");
			String csvPath = mixpath + "/" + f.getName();
			List<String[]> listStr = ReadCSV.readCSV(csvPath);
			String imgMixData = listStr.get(listStr.size() - 1)[2];
			ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
			System.out.println(obj);
			initConstant(obj);  //正常mask avg initConstant
			//initNewConstant(obj);
			//initTmpConstant(obj);   //全部mask  percent=num取4
			
			File tmp = new File(Constant.statpath + "/" + f.getName());
			if(!tmp.exists()) {
				//tmp.mkdirs();
			}
			//SaveToImage.overlapPan(f.getName(), "shipan");
			//SaveToImage.overlapPan(f.getName(), "shibei");
			//SaveToImage.overlapAmd(f.getName());
			
			//SaveAvg.avgPan(f.getName());
			//SaveAvg.avgBei(f.getName());
			//SaveAvg.avgAmd(f.getName());
			
			//SaveMask.maskPan(f.getName());
			//SaveMask.maskBei(f.getName());
			
			//SaveAll.imgAll(f.getName());  //所有overlap
			//SaveAll.imgUserAll(f.getName());  //所选用户overlap
			//SaveAll.imgAvg(f.getName());
			//SaveAll.imgMask(f.getName());
			//SaveAll.imgAvgMask(f.getName());
			//String savepath = "C:/Users/CVTE/Desktop/HP打印/sole/tm";
			//SaveAll.AllMask(f.getName(), "n_" + f.getName(), savepath);
			
			//SaveBMP.imgAvg(f.getName());  // 未要求
			SaveBMP.imgMask(f.getName());  //mask图片生成
			//SaveBMP.imgMaskTmp(f.getName());
			
//			IOU iou = new IOU(offset, f.getName(), CoverUtil.calCoverPan(), CoverUtil.calCoverBei());
//			list.add(iou);
//			offset++;
			
		}
		
		//保存到Excel
//		String title[] = {"ID","imgName","discIOU","cupIOU"};
//		String path = "E:/BaiduNetdiskDownload";
//        String fileName = "mask_avg_iou";
//        String fileType = "xlsx";
//        WriteExcel.writer(path, fileName, fileType, list, title);
	}

	private void Excelexport(String mixpath) throws IOException {
		String imgpath = "E:\\第一批\\青光眼图片";
		File[] files = new File(imgpath).listFiles();
		List<IOU> list = new ArrayList<IOU>();
		int num = 1;
		int num1 = 1;
		DecimalFormat df = new DecimalFormat("0000");
		for(File f : files) {
			String name = f.getName();
			if(f.isDirectory()) {
				File[] tmp = new File(imgpath + "/" + name).listFiles();
				for(File ff : tmp) {
					//String imgname = imgpath + "/" + name + "_" + ff.getName().split("[.]")[0];
					String csvPath = mixpath + "/" + name + "_" + ff.getName().split("[.]")[0];
					List<String[]> listStr = ReadCSV.readCSV(csvPath);
					String imgMixData = listStr.get(listStr.size() - 1)[2];
					ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
					initConstant(obj);
					//System.out.println("name = " + ff.getName());
					//String amdxy = CoverUtil.calAmd(ff.getName());
					//System.out.println("amdxy = " + amdxy);
					IOU iou = new IOU(num1, "g" + df.format(num) + ".jpg", 
							CoverUtil.calCoverPan(), CoverUtil.calCoverBei(), 
							CoverUtil.calAmd(ff.getName()), CoverUtil.calFovea(ff.getName()));
					list.add(iou);
					num++;
					num1++;
				}
			}
		}
		
		num = 1;
		imgpath = "E:\\第一批\\非青光眼图片";
		File[] files1 = new File(imgpath).listFiles();
		for(File f : files1) {
			String name = f.getName();
			if(f.isDirectory()) {
				File[] tmp = new File(imgpath + "/" + name).listFiles();
				for(File ff : tmp) {
					//String imgname = imgpath + "/" + name + "_" + ff.getName().split("[.]")[0];
					String csvPath = mixpath + "/" + name + "_" + ff.getName().split("[.]")[0];
					List<String[]> listStr = ReadCSV.readCSV(csvPath);
					String imgMixData = listStr.get(listStr.size() - 1)[2];
					ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
					initConstant(obj);
					//String amdxy = CoverUtil.calAmd(ff.getName());
					IOU iou = new IOU(num1, "n" + df.format(num) + ".jpg", 
							CoverUtil.calCoverPan(), CoverUtil.calCoverBei(), 
							CoverUtil.calAmd(ff.getName()), CoverUtil.calFovea(ff.getName()));
					list.add(iou);
					num++;
					num1++;
				}
			}
		}
		
		//保存到Excel
		String title[] = { "ID", "imgName", "discIOU", "cupIOU", "foveaX", "foveaY" };
		String path = "E:/";
		String fileName = "mask_iou";
		String fileType = "xlsx";
		WriteExcel.writer(path, fileName, fileType, list, title);
	}
	
}
