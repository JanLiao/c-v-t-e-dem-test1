package tmptest;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlTest {
	private static String url = "jdbc:mysql://localhost:3306/test";
	private static String root = "root";
	private static String psw = "123456";
	private static String path = "E:/第二批";
	private static Connection conn1 = null;

	public static void main(String[] args) {
		//insertData();
		//queryData();
		
		try {
			conn1 = DriverManager.getConnection(url, root, psw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		queryLabel();
	}
	
	public static void queryLabel() {
		long start = System.currentTimeMillis();
		Statement stat = null;
		try {			
			stat = conn1.createStatement();
			
			ResultSet rs = stat.executeQuery("select * from img a where a.name = '龚添禹_20180508103800476.jpg_12000';");
			while(rs.next()) {
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("label"));
			}
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("总时间 = " + (System.currentTimeMillis() - start));
	}
	
	public static void queryData() {
		long start = System.currentTimeMillis();
		Connection conn = null;
		Statement stat = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("驱动 = " + (System.currentTimeMillis() - start));
			conn = DriverManager.getConnection(url, root, psw);
			System.out.println("连接 = " + (System.currentTimeMillis() - start));
			stat = conn.createStatement();
			System.out.println("创建 = " + (System.currentTimeMillis() - start));
			ResultSet rs = stat.executeQuery("select * from img a where a.name = '龚添禹_20180508103800476.jpg';");
			while(rs.next()) {
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("data"));
			}
			
			//PreparedStatement preStat = conn.prepareStatement();
			stat.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("总时间 = " + (System.currentTimeMillis() - start));
	}

	public static void insertData() {
		Connection conn = null;
		Statement stat = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, root, psw);
			stat = conn.createStatement();
			
			String tablesql = "create table img(name varchar(255), label varchar(1000), index idx(name))";
			stat.executeUpdate(tablesql);
			
			File[] files = new File(path).listFiles();
			int len  = files.length;
			String name = "";
			for(int i = 0; i < 20000; i++) {
				if(i >= len) {
					name = files[len - 1].getName();
					name = name + "_" + i;
				}else {
					name = files[i].getName();
				}
				String label = ":0.3243574051407589,\\\"canvas_data\\\":{\\\"w\\\":530,\\\"h\\\":530}}"
						+ ":0.3243574051407589,\\\"canvas_data\\\":{\\\"w\\\":530,\\\"h\\\":530}}"
						+ ":0.3243574051407589,\\\"canvas_data\\\":{\\\"w\\\":530,\\\"h\\\":530}}"
						+ ":0.3243574051407589,\\\"canvas_data\\\":{\\\"w\\\":530,\\\"h\\\":530}}"
						+ ":0.3243574051407589,\\\"canvas_data\\\":{\\\"w\\\":530,\\\"h\\\":530}}"
						+ ":0.3243574051407589,\"canvas_data\":{\"w\":530,\"h\":530}}";
				String sql = "insert into img values("
						+ "'" + name + "', '" + label + "')";
				stat.addBatch(sql);
			}
			stat.executeBatch();
			
			stat.close();
			conn.close();
			//PreparedStatement preStat = conn.prepareStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
