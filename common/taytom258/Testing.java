package taytom258;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Testing {

	Connection con;
	Statement st;
	ResultSet rs;
	String db;

	public Testing() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String path = "D:\\Test.accdb";
			db = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)}; DBQ=" + path;
			doConnection();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void doConnection() {
		try {
			con = DriverManager.getConnection(db);
			st = con.createStatement();
			st.execute("INSERT INTO Java VALUES ('Los Angeles',20,'10/15/2005')");
			con.close();
			st.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());

		}

	}

	public static void init() {
		new Testing();
		
	}
}