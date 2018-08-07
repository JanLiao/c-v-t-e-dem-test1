package statisticsSecond;

public class CoverUtil {

	public static double calCoverPan() {
		CircleData sole1 = Constant.panAvg;
		double sin1 = Math.sin(Math.toRadians(sole1.getAngle()));
		double cos1 = Math.cos(Math.toRadians(sole1.getAngle()));
		double a1 = sole1.getRadius() * sole1.getScaleX();
		double b1 = sole1.getRadius() * sole1.getScaleY();
		double p1 = sole1.getLeft();
		double q1 = -sole1.getTop();
		double m1 = sole1.getLeft() + a1;
		double n1 = -sole1.getTop() - b1;

		int len1 = 0;
		int len2 = 0;
		Mask mask = Constant.mask;
		int[][] panMask = mask.getCurPanMask();
		double percent = Constant.panValue;
		int size = 0;
		if (Constant.PanUser == null || Constant.PanUser.equals("")) {
			size = Constant.AllLabelData.size();
		} else {
			size = Constant.PanUser.split(",").length;
		}
		int num = (int) (size * percent / 100);
		if (num == 0) {
			num = 1;
		}

		for (int i = 0; i < 529; i++) {
			for (int j = 0; j < 529; j++) {
				double relativeX = (Constant.panAvg.getCenterX() - 53 + 0.2 * i);
				double relativeY = (Constant.panAvg.getCenterY() + 53 - 0.2 * j);
				double tmp1 = p1 + ((relativeX - p1) * cos1 - (-relativeY - q1) * sin1);
				double tmp2 = q1 + ((relativeX - p1) * sin1 + (-relativeY - q1) * cos1);

				double x1 = (tmp1 - m1) / a1;
				double y1 = (tmp2 - n1) / b1;
				if (panMask[i][j] >= num || x1 * x1 + y1 * y1 <= 1) {
					len1++;
				}

				if (panMask[i][j] >= num && x1 * x1 + y1 * y1 <= 1) {
					len2++;
				}
			}
		}

		double cover = (double) len2 / len1;
		cover = (double) Math.round(cover * 100) / 100;
		return cover;
	}

	public static double calCoverBei() {
		CircleData sole1 = Constant.beiAvg;
		double sin1 = Math.sin(Math.toRadians(sole1.getAngle()));
		double cos1 = Math.cos(Math.toRadians(sole1.getAngle()));
		double a1 = sole1.getRadius() * sole1.getScaleX();
		double b1 = sole1.getRadius() * sole1.getScaleY();
		double p1 = sole1.getLeft();
		double q1 = -sole1.getTop();
		double m1 = sole1.getLeft() + a1;
		double n1 = -sole1.getTop() - b1;

		int len1 = 0;
		int len2 = 0;
		Mask mask = Constant.mask;
		int[][] beiMask = mask.getCurBeiMask();

		double percent = Constant.beiValue;
		int size = 0;
		if (Constant.BeiUser == null || Constant.BeiUser.equals("")) {
			size = Constant.AllLabelData.size();
		} else {
			size = Constant.BeiUser.split(",").length;
		}
		int num = (int) (size * percent / 100);
		if (num == 0) {
			num = 1;
		}

		for (int i = 0; i < 529; i++) {
			for (int j = 0; j < 529; j++) {
				double relativeX = (Constant.beiAvg.getCenterX() - 53 + 0.2 * i);
				double relativeY = (Constant.beiAvg.getCenterY() + 53 - 0.2 * j);
				double tmp1 = p1 + ((relativeX - p1) * cos1 - (-relativeY - q1) * sin1);
				double tmp2 = q1 + ((relativeX - p1) * sin1 + (-relativeY - q1) * cos1);

				double x1 = (tmp1 - m1) / a1;
				double y1 = (tmp2 - n1) / b1;
				if (beiMask[i][j] >= num || x1 * x1 + y1 * y1 <= 1) {
					len1++;
				}

				if (beiMask[i][j] >= num && x1 * x1 + y1 * y1 <= 1) {
					len2++;
				}
			}
		}

		double cover = (double) len2 / len1;
		cover = (double) Math.round(cover * 100) / 100;
		return cover;
	}
	
	public static double calAmd(String name) {
		LineData line = Constant.lineAvg;
		double ratio = 2124/530;
		//double center = 1056;
		double x = line.getLeft() * ratio + line.getWidth() * line.getScaleX() * ratio / 2;
		if("R.jpg".equals(name) || "RR.jpg".equals(name) || "RRR.jpg".equals(name) || "RRRR.jpg".equals(name)) {
			x = 2125 - x;
		}
		x = (double) Math.round(x * 100) / 100;
		return x;
	}
	
	public static double calFovea(String name) {
		LineData line = Constant.lineAvg;
		double ratio = 2124/530;
		//double center = 1056;
		double y = line.getTop() * ratio + line.getHeight() * line.getScaleY() * ratio / 2;
		y = (double) Math.round(y * 100) / 100;
		return y;
	}

	public static double calY(String name) {
		LineData line = Constant.lineAvg;
		double ratio = 1634/530;
		//double center = 1056;
		double y = line.getTop() * ratio + line.getHeight() * line.getScaleY() * ratio / 2;
		y = (double) Math.round(y * 100) / 100;
		return y;
	}

	public static double calX(String name) {
		LineData line = Constant.lineAvg;
		double ratio = 1634/530;
		//double center = 1056;
		double x = line.getLeft() * ratio + line.getWidth() * line.getScaleX() * ratio / 2;
		double center = Constant.beiAvg.getCenterX();
		double r = 1634/530;
		if(center*r > 817) {
			x = 1635 - x;
		}
		x = (double) Math.round(x * 100) / 100;
		return x;
	}

}
