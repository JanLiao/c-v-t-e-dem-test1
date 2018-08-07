package statisticsSecond;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

import javafx.application.Application;
import javafx.stage.Stage;

public class GenerateImage extends Application {

	private static String imgpath = "E:/第二批";
	//private static String mixpath = "F:/eclipse-workspace-new1/LabelViewNew/admin/mixlabel/sole";
	private static String mixpath = "C:/Users/CVTE/Desktop/融合数据/第二批融合结果/admin825/mixlabel/sole";
	private static List<IOU> list = new ArrayList<IOU>();
	private static int num = 1;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// maskGenerateNew2();
		// save();
		// threeColor();
		allImage();
		System.exit(0);
	}

	public static void allImage() throws IOException {
		String imgpath = "E:/第二批";
		String threepath = "E:/第二批融合图片-all/三色";
		String bmppath = "E:/第二批融合图片-all/Disc_Cup_Masks-all";
		String jpgpath = "E:/第二批融合图片-all/Disc_Cup_Fovea_Illustration-all";
		
		getAllMask(imgpath, threepath, bmppath, jpgpath, "g");

		// 保存到Excel
		String title[] = { "ID", "Orgin_ImgName", "Fovea_X", "Fovea_Y" };
		String path = "E:/第二批融合图片-all";
		String fileName = "Fovea_location";
		String fileType = "xlsx";
		//WriteExcel.writerSecond(path, fileName, fileType, list, title);
	}
	
	public static void getAllMask(String imgpath, String threepath, String bmppath, String jpgpath, 
			String flag) throws FileNotFoundException, IOException {
		
		ColorUtil.initColor();
		DecimalFormat df = new DecimalFormat("0000");
		File[] files = new File(imgpath).listFiles();
		
		for(File f : files) {
			String name = f.getName();
			String csvPath = mixpath + "/2___" + name.split("[.]")[0];
			List<String[]> listStr = ReadCSV.readCSV(csvPath);
			String imgMixData = listStr.get(listStr.size() - 1)[2];
			ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
			//initConstant(obj);
			InitData.initConstant(obj, name);
			
			// 画mask及bmp图
			//SaveMask.allmasknew3("2___" + name.split("[.]")[0], flag + df.format(num), jpgpath, obj);
			//SaveMask.allBmp("2___" + name.split("[.]")[0], flag + df.format(num), bmppath, obj);
			
			// 画三色图
		    SaveMask.threeColor("2___" + name.split("[.]")[0], threepath);
			
			System.out.println("2___" + name.split("[.]")[0] + "=" + flag + df.format(num));
			IOU iou = new IOU();
			iou.setId(num);
			iou.setOldName(name);
			double x = CoverUtil.calX(name);
			double y = CoverUtil.calY(name);
			iou.setFoveaX(x);
			iou.setFoveaY(y);
			list.add(iou);
			
			num++;
			
		}
	}

	public static void threeColor() {
		String imgpath = "E:\\第二批validate\\validation-new\\G";
		String imgpath1 = "E:\\第二批validate\\validation-new\\N";
		String savepath = "E:\\第二批融合图片\\三色";
		getThreeColor(imgpath, savepath);
		getThreeColor(imgpath1, savepath);
	}

	private void maskGenerateNew2() throws FileNotFoundException, IOException {
		String imgpath = "E:\\第二批validate\\validation-new\\G";
		String imgpath1 = "E:\\第二批validate\\validation-new\\N";
		String savepath = "E:\\package\\第一批mask图1";
		savepath = Constant.statpath;
		getMaskNew2(imgpath, savepath, "g");
		getMaskNew2(imgpath1, savepath, "n");

		// 保存到Excel
		String title[] = { "ID", "Orgin_ImgName", "Fovea_X", "Fovea_Y" };
		String path = "E:/第二批融合图片";
		String fileName = "Fovea_location";
		String fileType = "xlsx";
		WriteExcel.writerSecond(path, fileName, fileType, list, title);
	}

	public static void getThreeColor(String imgpath, String savepath) {
		ColorUtil.initColor();
		File[] files = new File(imgpath).listFiles();

		for (File f : files) {
			String name = f.getName();
			String csvPath = mixpath + "/2___" + name.split("[.]")[0];
			List<String[]> listStr = ReadCSV.readCSV(csvPath);
			String imgMixData = listStr.get(listStr.size() - 1)[2];
			ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
			InitData.initConstant(obj, name);
			// 画三色图
			SaveMask.threeColor("2___" + name.split("[.]")[0], savepath);
		}
	}

	public static void getMaskNew2(String imgpath, String savepath, String flag)
			throws FileNotFoundException, IOException {

		ColorUtil.initColor();
		DecimalFormat df = new DecimalFormat("0000");
		File[] files = new File(imgpath).listFiles();

		for (File f : files) {
			String name = f.getName();
			String csvPath = mixpath + "/2___" + name.split("[.]")[0];
			List<String[]> listStr = ReadCSV.readCSV(csvPath);
			String imgMixData = listStr.get(listStr.size() - 1)[2];
			ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
			// initConstant(obj);
			InitData.initConstant(obj, name);

			// 画mask及bmp图
			SaveMask.allmasknew3("2___" + name.split("[.]")[0], flag + df.format(num), savepath, obj);
			SaveMask.allBmp("2___" + name.split("[.]")[0], flag + df.format(num), savepath, obj);

			// 画三色图
			SaveMask.threeColor("2___" + name.split("[.]")[0], savepath);

			System.out.println("2___" + name.split("[.]")[0] + "=" + flag + df.format(num));
			IOU iou = new IOU();
			iou.setId(num);
			iou.setOldName(name);
			double x = CoverUtil.calX(name);
			double y = CoverUtil.calY(name);
			iou.setFoveaX(x);
			iou.setFoveaY(y);
			list.add(iou);

			num++;

		}
	}

}
