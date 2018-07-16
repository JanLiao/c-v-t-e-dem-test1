package statistics;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.commons.io.FileUtils;

public class TMPTest {

	public static void main(String[] args) {
		String savepath1 = "E:\\package\\mask\\Glaucoma";
		String savepath2 = "E:\\package\\mask\\Non-Glaucoma";
		
		String tmp = "E:\\BaiduNetdiskDownload\\统计平均掩码\\Mask掩码图Flip4";
		
		String img1 = "E:\\第一批\\非青光眼图片";
		String img2 = "E:\\第一批\\青光眼图片";
		DecimalFormat df = new DecimalFormat("0000");
		File[] files1 = new File(img1).listFiles();
		int num = 1;
		for(File f : files1) {
			String name = f.getName();
			File[] tp = new File(img1 + "/" + name).listFiles();
			for(File ff : tp) {
				String tn = name + "_" + ff.getName().split("[.]")[0];
				copyBmp(tmp + "/" + tn, savepath2, df.format(num), "n");
				num++;
			}
		}
		
		File[] files2 = new File(img2).listFiles();
		int num1 = 1;
		for(File f : files2) {
			String name = f.getName();
			File[] tp = new File(img2 + "/" + name).listFiles();
			for(File ff : tp) {
				String tn = name + "_" + ff.getName().split("[.]")[0];
				copyBmp(tmp + "/" + tn, savepath1, df.format(num1), "g");
				num1++;
			}
		}
	}

	private static void copyBmp(String src, String savepath, String num, String flag) {
		File srcFile = new File(src + ".bmp");
		File destFile = new File(savepath + "/" + flag + num + ".bmp");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
