package taytom258.core.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import taytom258.config.Config;
import taytom258.core.util.LogHelper;

public class Database {

	private static Connection con;
	private static Statement st;
	private static String db;

	protected static void sqlCommand(String sql) {
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
	
	protected static void sqlInsert(String table, String field, String value, String key, String keyField){
		String[] fields = new String[19];
		String[] values = new String[19];
		try {
			con = DriverManager.getConnection(db);
			st = con.createStatement();
			String sql = "INSERT INTO " + table + " (" + field + ")" + " VALUES " + "('" + value + "')";
//			System.out.println(sql);
			st.executeUpdate(sql);
			con.close();
			st.close();
		} catch (Exception ex) {
			if(ex.getMessage().contains("General error")){
				LogHelper.info("CCSD already exists in database, updating information");
				fields = field.replace(" ", "").split(",");
				values = value.replace(" ", "").replace("'", "").split(",");
				sqlUpdate(table, fields, values, key, keyField);
			}else{
				ex.printStackTrace();
				LogHelper.severe(ex.getMessage());
			}
		}
	}
	
	
	//TODO fix this mess with updating
	private static void sqlUpdate(String table, String[] field, String[] value, String key, String keyField){
		try {
			con = DriverManager.getConnection(db);
			st = con.createStatement();
			int update = 0;
			for(int i=0; i<field.length; i++){
				String sql = "UPDATE " + table
						+" SET " + field[i] + " = " + "'" + value[i] + "'"
						+ " WHERE " + keyField +" = '" + key + "'";
//				System.out.println(sql);
				st.executeUpdate(sql);
				update++;
			}
			LogHelper.info(update + "records updated");
			con.close();
			st.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			LogHelper.severe(ex.getMessage());
		}
	}

	public static void init() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String path = Config.dbPath;
			db = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)}; DBQ=" + path;
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		LogHelper.debug("Database connection initialized");
		
	}
}