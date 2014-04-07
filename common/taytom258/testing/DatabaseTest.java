package taytom258.testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import taytom258.config.Config;

public class DatabaseTest {

	private static Connection con;
	private static Statement st;
	private static String db;

	public DatabaseTest() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String path = Config.DB_PATH;
			db = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)}; DBQ=" + path;
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void sqlCommand(String sql) {
		try {
			con = DriverManager.getConnection(db);
			st = con.createStatement();
			st.execute(sql);
			con.close();
			st.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void sqlInsert(String table, String field, String value){
		try {
			con = DriverManager.getConnection(db);
			st = con.createStatement();
			String sql = "INSERT INTO " + table + " (" + field + ")" + " VALUES " + "('" + value + "')";
			System.out.println(sql);
			st.executeUpdate(sql);
			con.close();
			st.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void init() {
		new DatabaseTest();
		
	}
}