package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	public static Connection conn;
	public static Statement stmt;
	
	
	public static void init() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://114.71.137.174:61083/covid", "covid","0206");
		stmt = conn.createStatement();
		System.out.println("DB 연결성공");
		 
	}
	
	
	public static ResultSet getResultSet(String sql) throws Exception {
		stmt = conn.createStatement();
		return stmt.executeQuery(sql);
	}
	
	public static void executeQuery(String sql) {
	      try {
	         stmt.executeUpdate(sql);
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	


}
