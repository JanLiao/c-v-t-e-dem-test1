package statistics;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Rename {

	public static void main(String[] args) throws IOException {
//		String path = "E:\\BaiduNetdiskDownload\\统计平均掩码\\Mask掩码图Flip2";
//		String path1 = "E:\\BaiduNetdiskDownload\\统计平均掩码\\Mask掩码图Flip3";
//		File[] files = new File(path).listFiles();
//		for(File f : files) {
//			File src = new File(path + "/" + f.getName());
//			File dest = new File(path1 + "/" + f.getName().split("[.]")[0] + ".bmp");
//			src.renameTo(dest);
//		}
		String mixpath = "E:\\BaiduNetdiskDownload\\统计平均掩码\\Mask掩码图Flip3";
		String imgpath = "E:\\第一批\\青光眼图片";
		String imgpath1 = "E:\\第一批\\非青光眼图片";
		String savepath = "E:\\第一批掩码图1\\青光眼图片";
		String savepath1 = "E:\\第一批掩码图1\\非青光眼图片";
		
		generateImg(imgpath, savepath, mixpath, "g");
		generateImg(imgpath1, savepath1, mixpath, "n");
	}

	private static void generateImg(String imgpath, String savepath, String mixpath, String flag) throws IOException {
		DecimalFormat df = new DecimalFormat("0000");
		File[] files = new File(imgpath).listFiles();
		int num = 1;
		for(File f : files) {
			String name = f.getName();
			if(f.isDirectory()) {
				File[] tmp = new File(imgpath + "/" + name).listFiles();
				for(File ff : tmp) {
					String imgname = name + "_" + ff.getName().split("[.]")[0];
					//System.out.println(imgname);
					File srcFile = new File(mixpath + "/" + imgname + ".bmp");
					File destFile = new File(savepath + "/" + flag + df.format(num) + ".bmp");
					FileUtils.copyFile(srcFile, destFile);
					num++;
				}
			}
		}
	}
	
}
