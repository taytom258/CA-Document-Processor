package taytom258.core.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import taytom258.config.Config;
import taytom258.core.util.LogHelper;
import taytom258.lib.Strings;

public class Database {

	private static Connection con;
	private static Statement st;
	private static String db;

	protected static ArrayList<String> sqlQuery(String sql) {
		
		ArrayList<String> group = new ArrayList<String>();
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(db);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			while(rs.next()){
				for(int i=1;i<=md.getColumnCount();i++){
					group.add(rs.getString(i));
				}
			}
			con.close();
			st.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return group;
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
//			Thread.sleep(4000);
			sqlQueryNull(table, field, key, keyField);
			st.close();
			con.close();
		} catch (Exception ex) {
			if(ex.getMessage().contains("General error")){
				LogHelper.info("Record already exists in database, updating information");
				fields = field.split(",");
				values = value.replace("'", "").split(",");
				sqlUpdate(table, fields, values, key, keyField);
				sqlQueryNull(table, field, key, keyField);
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					LogHelper.severe(e.getMessage());
				}
			}else{
				ex.printStackTrace();
				LogHelper.severe(ex.getMessage());
			}
		}
	}
	
	private static void sqlUpdate(String table, String[] field, String[] value, String key, String keyField){
		try {
			con = DriverManager.getConnection(db);
			st = con.createStatement();
			int update = 0;
			if(!table.equals("TSO")){
				for(int i=0; i<field.length; i++){
					String sql = "UPDATE " + table
							+" SET " + field[i] + " = " + "'" + value[i].trim() + "'"
							+ " WHERE " + keyField +" = '" + key + "'";
//					System.out.println(sql);
					st.executeUpdate(sql);
					update++;
				}
				LogHelper.info(update + " records updated");
				st.close();
				con.close();
			}else{
				LogHelper.warning(Strings.DUPLICATE_WARN);
			}
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
			LogHelper.severe(ex.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			LogHelper.severe(e.getMessage());
		}
		LogHelper.debug("Database connection initialized");
		
	}
	
	private static void sqlQueryNull(String table, String field, String key, String keyField){
		try {
		con = DriverManager.getConnection(db);
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		String sql = "SELECT " + field
				+" FROM " + table
				+ " WHERE " + keyField +" = '" + key + "'";
//			System.out.println(sql);
		ResultSet rs = st.executeQuery(sql);
		ResultSetMetaData md = rs.getMetaData();
		int numberOfColumns = md.getColumnCount();
		int i = 1;
		rs.beforeFirst();
		rs.next();
		while (i <= numberOfColumns) {
            String text = rs.getString(i);
//	            System.out.println(text);
        	if(rs.wasNull() || text.equals(" null") || text.equals("")){
        		if(!md.getColumnName(i).equals("TrunkID")){
        			LogHelper.warning("Null value at field " + md.getColumnName(i) + " for CCSD " + key + ". Please edit in access if incorrect");
        		}
        	}
        	i++;
		}
		st.close();
		con.close();
		}catch (Exception e){
		e.printStackTrace();
		LogHelper.severe(e.getMessage());
		}
	}
}