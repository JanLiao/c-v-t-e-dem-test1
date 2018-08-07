package statisticsSecond;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InitData {
	
	public static String userpath = "C:/Users/CVTE/Desktop/融合数据/第二批";

	public static void initConstant(ConsoleImg obj, String name) {
		File[] files = new File(userpath).listFiles();
		List<String> allLabel = new ArrayList<String>();
		String imgname = "2___" + name;
		String uname = "";
		for(File f : files) {
			String user = f.getName();
			String soleName = getSoleName(userpath, user, imgname);
			System.out.println("sole name = " + soleName);
			//先扫ImgAllLabel
		    String label = "";
		    String allLabelPath = userpath + "/" + user + "/file/imgAllLabel" ;
		    List<String[]> allList = ReadCSV.readCSV(allLabelPath);
		    for(String[] s : allList) {
		    	if(imgname.equals(s[3])) {
		    		label = s[7];
		    	}
		    }
		    
		    String labelPath = userpath + "/" + user + "/file/" + soleName + "/"
		    		+ imgname.split("[.]")[0];
		    System.out.println(imgname + " " + allLabelPath);
		    System.out.println(labelPath);
		    File fl = new File(labelPath);
		    if(fl.exists()) {
		    	label = getLabel(labelPath);
		    	allLabel.add(user + "=" + label);
		    	uname = uname + user + ",";
		    }
		    else {
		    	allLabel.add(user + "=" + label);
		    	uname = uname + user + ",";
		    }
		}
		
		Constant.AllLabelData = allLabel;
		for(String s : allLabel) {
			System.out.println(s);
		}
		Constant.PanUser = obj.getPanMixUser();
		Constant.BeiUser = obj.getBeiMixUser();
		Constant.CenterUser = obj.getCenterMixUser();
		Constant.beiAvg = DataUtil.getAvgCircleData("shibei");
		Constant.panAvg = DataUtil.getAvgCircleData("shipan");
		System.out.println("center data = " + Constant.CenterUser);
		if(obj.getPercent() == null || "".equals(obj.getPercent())) {
			Constant.panValue = 50;
		}else {			
			Constant.panValue = Double.parseDouble(obj.getPercent());
		}
		if(obj.getBeipercent() == null || "".equals(obj.getBeipercent())) {
			Constant.beiValue = 50;
		}else {			
			Constant.beiValue = Double.parseDouble(obj.getBeipercent());
		}
		LineData line = DataUtil.getAvgLineData("amd");
		Constant.lineAvg = line;
		
		// 初始化mask数据
		Mask mask = MaskTmpUtil.generateData();
		Constant.mask = mask;
	}
	
	private static String getSoleName(String dir, String name, String imgName) {
		List<String[]> list = ReadCSV.readCSV(dir + "/" + name + "/file/imgAllLabel");
		System.out.println("size = " + list.size() + "  " + imgName);
		String tmp = "";
		for(String[] s : list) {
			if(imgName.equals(s[3])) {
				String[] str = s[2].split("/");
				tmp = str[str.length - 1];
				break;
			}
		}
		return tmp;
	}
	
	private static String getLabel(String labelPath) {
		List<String[]> list = ReadCSV.readCSV(labelPath);
		return list.get(list.size() - 1)[7];
	}
}
