package tmp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeTest {

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		
		SimpleDateFormat format = new SimpleDateFormat("HHHH:mm:ss");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = "2018-6-30";
		Date d1 = format1.parse(s);
		long time = (new Date().getTime() - d1.getTime())/1000;
		System.out.println("time = " + time);
		String s1 = "2018-07-02 12:10:00";
		System.out.println((new Date().getTime() - format2.parse(s1).getTime())/1000);
		
		int hh = (int) (time/3600);
		int mm = (int) ((time%3600)/60);
		int ss = (int) (time%60);
		System.out.println(hh + ":" + mm + ":" + ss);
		
		calculateTime(System.currentTimeMillis());
	}
	
	public static void calculateTime(long start) {
		DecimalFormat dfh = new DecimalFormat("0000");
		DecimalFormat dfm = new DecimalFormat("00");
		DecimalFormat dfs = new DecimalFormat("00");
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				long end = System.currentTimeMillis();
				long inter = (end - start)/1000;
				int hh = (int) (inter/3600);
				int mm = (int) ((inter%3600)/60);
				int ss = (int) (inter%60);
				System.out.println(dfh.format(hh) + ":" + dfm.format(mm) + ":" + dfs.format(ss));
			}
			
		};
		
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}

}
