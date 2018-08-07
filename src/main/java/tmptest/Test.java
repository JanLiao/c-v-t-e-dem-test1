package tmptest;

public class Test {

	public static void main(String[] args) {
		double x1 = 5;
		double y1 = 1;
		double x2 = 3;
		double y2 = 2;
		double k = Math.atan((y1 - y2)/(x1 - x2));
		double kk = k*180/Math.PI;
		System.out.println(k + " = " + kk);
		
		String s = "E:/第二批/云琼容_20170807104518020.jpg";
		int index = s.lastIndexOf('/');
		System.out.println(s.substring(0, index));
	}

}
