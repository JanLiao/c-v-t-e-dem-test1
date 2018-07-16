package statistics;

import java.util.*;

/** 
* @author: jan 
* @date: 2018年6月20日 上午9:58:29 
*/
public class Constant {
	
	public static String imgpath = "E:/BaiduNetdiskDownload/眼底照";
	
	//public static String statpath = "C:/Users/CVTE/Desktop/kk";
	//public static String statpath = "C:/Users/CVTE/Desktop/tmp";
	//public static String statpath = "E:/BaiduNetdiskDownload/统计-alloverlap-userlap";
	public static String statpath = "E:/BaiduNetdiskDownload/统计-avg-mask";
	//public static String statpath = "C:/Users/CVTE/Desktop/img_cut/统计";
	//public static String statpath = "E:/BaiduNetdiskDownload/统计平均掩码/mask掩码图";
	//public static String statpath = "E:/BaiduNetdiskDownload/统计平均掩码/平均掩码图";

	public static List<String> AllLabelData = new ArrayList<String>();
	
	public static ConsoleImg Console = null;
	
	public static String PanUser = "";
	
	public static String BeiUser = "";
	
	public static String CenterUser = "";
	
	public static CircleData panAvg = null;
	
	public static CircleData beiAvg = null;
	
	public static LineData lineAvg = null;
	
	public static Mask mask = null;
	
	//初始化颜色
	public static List<LabelColor> ColorList = new ArrayList<LabelColor>();
	
	public static double panValue = 0;
	
	public static double beiValue = 0;
		
}
