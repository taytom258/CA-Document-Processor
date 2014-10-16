package taytom258.core.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import taytom258.config.Config;
import taytom258.core.util.Conversion;
import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.lib.Reference;

public class Database {

	private static Connection con;
	private static Statement st;
	private static String db;

	public static ArrayList<String> dbQuery(String sql) {

		ArrayList<String> group = new ArrayList<String>();
		ResultSet rs = null;
		try {

			rs = st.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			while (rs.next()) {
				for (int i = 1; i <= md.getColumnCount(); i++) {
					group.add(rs.getString(i));
				}
			}
			con.commit();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			LogHelper.severe(ex.getMessage());
			try {
				con.rollback();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LogHelper.severe(e.getMessage());
			}

		}
		LogHelper.db("DB Query Complete");
		return group;
	}

	public static boolean dbUpdate(String sql) {
		try {
			st.executeUpdate(sql);
			con.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			LogHelper.severe(ex.getMessage());
			try {
				con.rollback();
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				LogHelper.severe(e.getMessage());
			}

		}
		LogHelper.db("DB Update Complete");
		return true;
	}

	protected static void TSOInsert(String table, String field, String value,
			String key, String keyField) {
		String[] fields = new String[19];
		String[] values = new String[19];
		try {
			String sql = "INSERT INTO " + table + " (" + field + ")"
					+ " VALUES " + "('" + value + "')";
			// System.out.println(sql);
			st.executeUpdate(sql);
			// Thread.sleep(4000);
			// sqlQueryNull(table, field, key, keyField);
			con.commit();
		} catch (Exception ex) {
			if (ex.getMessage().contains("General error")) {
				fields = field.split(",");
				values = value.replace("'", "").split(",");
				TSOUpdate(table, fields, values, key, keyField);
				// sqlQueryNull(table, field, key, keyField);
				// ex.printStackTrace();
				// LogHelper.severe(ex.getMessage());
			} else {
				ex.printStackTrace();
				LogHelper.severe(ex.getMessage());
			}
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				LogHelper.severe(e.getMessage());
			}
		}
		LogHelper.db("TSO Insert Complete");
	}

	protected static void TSOUpdate(String table, String[] field,
			String[] value, String key, String keyField) {
		try {
			int update = 0;
			boolean newer = false;
			String string = Conversion.dateConvert(Collection.reportDate,
					false, true);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
					Locale.ENGLISH);
			Date report = format.parse(string);

			ArrayList<String> rs = dbQuery("SELECT ReportDate " + "FROM TSO "
					+ "WHERE FullCCSD = " + "'" + Collection.fullCcsd + "'");
			Collections.sort(rs);
			for (String element : rs) {
				String target = element;
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
						Locale.ENGLISH);
				Date result = df.parse(target);
				// System.out.println(report + " : " + result);
				if (report.compareTo(result) > 0) {
					// System.out.println("newer");
					newer = true;
				} else {
					newer = false;
				}
			}

			if (newer) {
				LogHelper.info("Record already exists in table " + table
						+ ", updating information");
				for (int i = 0; i < field.length; i++) {
					String sql = "UPDATE " + table + " SET " + field[i] + " = "
							+ "'" + value[i].trim() + "'" + " WHERE "
							+ keyField + " = '" + key + "'";
					// System.out.println(sql);
					st.executeUpdate(sql);
					update++;
				}
				LogHelper.info(update + " fields updated");
				con.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LogHelper.severe(ex.getMessage());
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				LogHelper.severe(ex.getMessage());
			}

		}
		LogHelper.db("TSO Update Complete");
	}

	protected static void TSOUpdate(String table, String field, String value,
			String key, String keyField) {
		try {
			int update = 0;
			boolean newer = false;
			String string = Conversion.dateConvert(Collection.reportDate,
					false, true);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
					Locale.ENGLISH);
			Date report = format.parse(string);

			ArrayList<String> rs = dbQuery("SELECT ReportDate " + "FROM TSO "
					+ "WHERE FullCCSD = " + "'" + Collection.fullCcsd + "'");
			Collections.sort(rs);
			for (String element : rs) {
				String target = element;
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
						Locale.ENGLISH);
				Date result = df.parse(target);
				// System.out.println(report + " : " + result);
				if (report.compareTo(result) > 0) {
					// System.out.println("newer");
					newer = true;
				} else {
					newer = false;
				}
			}

			if (newer) {
				LogHelper.info("Record already exists in table " + table
						+ ", updating information");
				String sql = "UPDATE " + table + " SET " + field + " = " + "'"
						+ value.trim() + "'" + " WHERE " + keyField + " = '"
						+ key + "'";
				// System.out.println(sql);
				st.executeUpdate(sql);
				update++;
				LogHelper.info(update + " fields updated");
				con.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LogHelper.severe(ex.getMessage());
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				LogHelper.severe(ex.getMessage());
			}

		}
		LogHelper.db("TSO Update Complete");
	}

	protected static void TSOUpdate(String table, String field, int value,
			String key, String keyField) {
		try {
			int update = 0;
			boolean newer = false;
			String string = Conversion.dateConvert(Collection.reportDate,
					false, true);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
					Locale.ENGLISH);
			Date report = format.parse(string);

			ArrayList<String> rs = dbQuery("SELECT ReportDate " + "FROM TSO "
					+ "WHERE FullCCSD = " + "'" + Collection.fullCcsd + "'");
			Collections.sort(rs);
			for (String element : rs) {
				String target = element;
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
						Locale.ENGLISH);
				Date result = df.parse(target);
				// System.out.println(report + " : " + result);
				if (report.compareTo(result) > 0) {
					// System.out.println("newer");
					newer = true;
				} else {
					newer = false;
				}
			}

			if (newer) {
				LogHelper.info("Record already exists in table " + table
						+ ", updating information");
				String sql = "UPDATE " + table + " SET " + field + " = " + "'"
						+ value + "'" + " WHERE " + keyField + " = '" + key
						+ "'";
				// System.out.println(sql);
				st.executeUpdate(sql);
				update++;
				LogHelper.info(update + " fields updated");
				con.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LogHelper.severe(ex.getMessage());
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				LogHelper.severe(ex.getMessage());
			}

		}
		LogHelper.db("TSO Update Complete");
	}

	public static void init(boolean close) {
		if (close) {
			try {
				con.close();
				st.close();
				LogHelper.debug("Database connection closed");
			} catch (SQLException e) {
				LogHelper.severe(e.getMessage());
				e.printStackTrace();
			}
			return;
		}
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String path = Config.dbPath;
			db = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)}; DBQ="
					+ path;
			con = DriverManager.getConnection(db, Reference.DB_USER, Reference.DB_PASS);
			con.setAutoCommit(false);
			st = con.createStatement();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			LogHelper.severe(ex.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			LogHelper.severe(e.getMessage());
		} catch (SQLException e) {
			LogHelper.severe(e.getMessage());
			e.printStackTrace();
		}
		LogHelper.debug("Database connection initialized");

	}

	@Deprecated
	private static void sqlQueryNull(String table, String field, String key,
			String keyField) {
		try {
			con = DriverManager.getConnection(db);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT " + field + " FROM " + table + " WHERE "
					+ keyField + " = '" + key + "'";
			// System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			int numberOfColumns = md.getColumnCount();
			int i = 1;
			rs.beforeFirst();
			rs.next();
			while (i <= numberOfColumns) {
				String text = rs.getString(i);
				// System.out.println(text);
				if (rs.wasNull() || text.equals(" null") || text.equals("")) {
					if (!md.getColumnName(i).equals("TrunkID")) {
						LogHelper.warning("Null value at field "
								+ md.getColumnName(i) + " for CCSD " + key
								+ ". Please edit in access if incorrect");
					}
				}
				i++;
			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.severe(e.getMessage());
			try {
				st.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				LogHelper.severe(e1.getMessage());
			}

		}
	}
}