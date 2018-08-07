package tmptest;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javafx.application.Application;
import javafx.stage.Stage;
import statistics.ReadCSV;

public class SqliteTest extends Application {
	private static int flag = 0;
	private static Connection conn1 = null;
	public static int shut = 0;
	
	@Override
	public void start(Stage stage) throws Exception {
		threadProcess();
		stage.show();
	}

	public static void main(String[] args) throws ClassNotFoundException {
		//createData();
		//getFileName();
		//queryByName();
		
		//threadProcess();
		launch(args);
	}
	
	public static void queryByName() {
		long start = 0;
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn1 = DriverManager.getConnection("jdbc:sqlite:admin.db");
			//conn = DriverManager.getConnection(conStr);
			
			start = System.currentTimeMillis();
			Statement stat = conn1.createStatement();
			ResultSet rs = stat.executeQuery("select a.data from img_data a where a.name = '2___候晓斌_20171024113721118.jpg';");
			System.out.println(rs.getString(1));
//			while(rs.next()) {
//				if("2___候晓斌_20171024113721118.jpg".equals(rs.getString(1))) {
//					String labelList = rs.getString(2);
//					break;
//				}
//				System.out.println("name = " + rs.getString("name") + " "
//						+ "  data = " + rs.getString(2));
//			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("耗时 = " + (System.currentTimeMillis() - start));
	}
	
	public static void threadProcess() {
		JanThread thread = new JanThread();
		thread.start();
				
//		ExecutorService executor = Executors.newSingleThreadExecutor();
//		MyThread my = new MyThread();
//		Future<Boolean>[] futures = new Future[1];
//		futures[0] = executor.submit(my);
//		boolean success = false;
//		try {
//			success = futures[0].get();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}
//		if(success) {
//			System.out.println("执行结束");
//			executor.shutdown();
//		}
	}
	
	public static void getFileName() {
		long start = System.currentTimeMillis();
		String path = "E:/第二批";
		File[] files = new File(path).listFiles();
		for(File f : files) {
			generateData("2___" + f.getName());
		}
		
		System.out.println("all time = " + (System.currentTimeMillis() - start));
		//printDB();
	}
	
	public static boolean getFileNameNew() {
		long start = System.currentTimeMillis();
		String path = "E:/第二批";
		File[] files = new File(path).listFiles();
		for(File f : files) {
			generateData("2___" + f.getName());
		}
		
		System.out.println("all time = " + (System.currentTimeMillis() - start));
		return true;
	}
	
	public static void generateData(String imgName) {
		String dir = "C:/Users/CVTE/Desktop/融合数据/第二批";
		File[] files = new File(dir).listFiles();
		List<String> allLabel = new ArrayList<String>();
		String imgname = imgName;
		//String user = "";
		StringBuffer user = new StringBuffer("");
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Future<String>[] futures = new Future[files.length];
		long star = System.currentTimeMillis();
		for(int i = 0, len = files.length; i < len; i++) {
			String name = files[i].getName();
			user.append(name);
			user.append(",");
			//当前user下单个csv 名
			String soleName = getSoleName(dir, name, imgName);
			ReadTask task = new ReadTask();
			task.setDir(dir);
			task.setImgName(imgname);
			task.setName(name);
			task.setSoleName(soleName);
			task.setStart(star);
			futures[i] = executor.submit(task);
		}
		
		StringBuffer tmp = new StringBuffer("");
		for(int i = 0, len = files.length; i < len; i++) {
			String name = files[i].getName();
			tmp.append(name);
			tmp.append("=");
			String st = "";
			try {
				st = futures[i].get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			tmp.append(st);
			allLabel.add(tmp.toString());
		}
		executor.shutdown();
		createDB(imgName, tmp.toString());
	}
	
	public static void printDB() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:admin.db");
			//conn = DriverManager.getConnection(conStr);
			
			Statement stat = conn.createStatement();
			
			ResultSet rs = stat.executeQuery("select * from img_data;");
			while(rs.next()) {
				System.out.println("name = " + rs.getString("name") + " "
						+ "  data = " + rs.getString(2));
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void createDB(String imgName, String label) {
		String root = "F:/eclipse-workspace-new1/DemoTest1/kk/jan.db";
		String conStr = "jdbc:sqlite:" + root;
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:admin.db");
			//conn = DriverManager.getConnection(conStr);
			
			Statement stat = conn.createStatement();
			if(flag == 0) {
				//  primary key
				stat.executeUpdate("create table if not exists img_data(name varchar(255), data varchar(1000));");
				stat.executeUpdate("CREATE INDEX idx ON img_data(name)");
				flag = 1;
			}
			
			stat.executeUpdate("insert into img_data values("
					+ "'" + imgName + "', '" + label + "')");
			//stat.executeUpdate("insert into img_data values(2, 'fang', 'liu')");
			
//			ResultSet rs = stat.executeQuery("select * from img_data;");
//			while(rs.next()) {
//				System.out.println("  name = " + rs.getString("name") + " "
//						+ "  data = " + rs.getString(2));
//			}
//			rs.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createData() throws ClassNotFoundException {
		String root = "F:/eclipse-workspace-new1/DemoTest1/kk/jan.db";
		String conStr = "jdbc:sqlite:" + root;
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			//conn = DriverManager.getConnection("jdbc:sqlite:jan.db");
			conn = DriverManager.getConnection(conStr);
			
			Statement stat = conn.createStatement();
			stat.executeUpdate("create table img_data(id int, name varchar(255), data varchar(255));");
			
			stat.executeUpdate("insert into img_data values(1, '晶安', 'liao')");
			stat.executeUpdate("insert into img_data values(2, 'fang', 'liu')");
			
			ResultSet rs = stat.executeQuery("select * from img_data;");
			while(rs.next()) {
				System.out.println("id = " + rs.getString("id") + "  name = " + rs.getString("name") + " "
						+ "  data = " + rs.getString(3));
			}
			rs.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private static String getSoleName(String dir, String name, String imgName) {
		List<String[]> list = ReadCSV.readCSV(dir + "/" + name + "/file/imgAllLabel");
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
}

class JanThread extends Thread{
	public void run() {
		SqliteTest.getFileName();
		SqliteTest.shut = 1;
		this.interrupt();
	}
}

class MyThread implements Callable<Boolean>{

	@Override
	public Boolean call() throws Exception {
		boolean flag = SqliteTest.getFileNameNew();
		return flag;
	}
	
}
