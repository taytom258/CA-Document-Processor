package taytom258.core.util.db;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import taytom258.config.Config;
import taytom258.core.util.DateUtils;
import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.testing.IER;

public class CircuitStatus {

	public static void circuitStatusRepair() {
		Database.init(false);
		String circuitStatus = "";

		String query = "SELECT FullCCSD, Ignore, TSONumber, Installed FROM TSO";
		ArrayList<String> al = Database.dbQuery(query);
		if (al.size() > 0) {
			for (int i = 1; i < al.size(); i += 4) {
				// System.out.println(al.get(i-1)+" : "+al.get(i));
				if (Integer.parseInt(al.get(i)) == 0) {
					String[] sa = al.get(i + 1).split("-");
					if (sa[1].equals("01")) {
						circuitStatus = "Inactive";
						if (Integer.parseInt(al.get(i + 2)) != 0) {
							circuitStatus = "Active";
						}
					} else if (sa[1].equals("01Z")) {
						circuitStatus = "Cancelled";
					} else if (sa[1].equals("99")) {
						circuitStatus = "Pending Disco";
						if (Integer.parseInt(al.get(i + 2)) != 0) {
							circuitStatus = "Discontinued";
							String update = "UPDATE Circuits SET DeactivationDate = '"
									+ DateUtils.deactivateDate(true)
									+ "' WHERE FullCCSD = '"
									+ al.get(i - 1)
									+ "'";
							Database.dbUpdate(update);
							File path = null;
							if (Config.useChf) {
								path = new File(Config.chfPath);
							} else {
								path = new File(Config.chfTest);
							}
							File[] list = path.listFiles(new FileFilter() {

								@Override
								public boolean accept(File pathname) {
									return pathname.isDirectory();
								}
							});

							String root = "";
							for (File element : list) {
								if (element.toString().contains(
										al.get(i - 1).substring(4))) {
									root = element.toString();
								}
							}

							if(root.indexOf("Disco Pending)") > -1){
								//"\\\\ASPARAGUS\\Circuit History Folders\\(DELETE LATER)"
								String newDir = root.substring(0, root.indexOf("\\", root.indexOf("Folders")))+"\\(DELETE LATER)\\"+root.substring(root.indexOf("\\", root.indexOf("Folders")), root.indexOf("Disco Pending)"))
										+ "Discontinued ["
										+ DateUtils.deactivateDate(false) + "])";
//								String newDir = root.substring(0,
//										root.indexOf("Disco Pending)"))
//										+ "Discontinued ["
//										+ DateUtils.deactivateDate(false) + "])";
								try {
									FileUtils.copyDirectory(new File(root),
											new File(newDir));
									FileUtils.deleteDirectory(new File(root));
								} catch (IOException e) {
									LogHelper.severe(e.getMessage());
									e.printStackTrace();
								}
							}
						}
					} else if (sa[1].equals("99Z")) {
						circuitStatus = "Active";
					} else {
						circuitStatus = "Pending Change";
						if (Integer.parseInt(al.get(i + 2)) != 0) {
							circuitStatus = "Active";
						}
					}
					String update = "UPDATE Circuits SET CircuitStatus = '"
							+ circuitStatus + "' WHERE FullCCSD = '"
							+ al.get(i - 1) + "'";
					Database.dbUpdate(update);
					// dbUpdate("Circuits", "CircuitStatus", circuitStatus,
					// al.get(i-1), "FullCCSD");
				}
			}
		}
		Database.init(true);
	}

	public static void circuitStatusUpdateTSO(String fullccsd) {
		Database.init(false);
		String circuitStatus = "";

		String query = "SELECT FullCCSD, Ignore, TSONumber FROM TSO WHERE FullCCSD = '"
				+ fullccsd + "'";
		ArrayList<String> al = Database.dbQuery(query);
		if (al.size() > 0) {
			for (int i = 1; i < al.size(); i += 3) {
				// System.out.println(al.get(i-1)+" : "+al.get(i));
				if (Integer.parseInt(al.get(i)) == 0) {
					String[] sa = al.get(i + 1).split("-");
					if (sa[1].equals("01")) {
						circuitStatus = "Inactive";
					} else if (sa[1].equals("01Z")) {
						circuitStatus = "Cancelled";
					} else if (sa[1].equals("99")) {
						circuitStatus = "Pending Disco";
					} else if (sa[1].equals("99Z")) {
						circuitStatus = "Active";
					} else {
						circuitStatus = "Pending Change";
					}
					String update = "UPDATE Circuits SET CircuitStatus = '"
							+ circuitStatus + "' WHERE FullCCSD = '"
							+ al.get(i - 1) + "'";
					Database.dbUpdate(update);
					// dbUpdate("Circuits", "CircuitStatus", circuitStatus,
					// al.get(i-1), "FullCCSD");
				}
			}
		}
		Database.init(true);
	}

	public static void circuitStatusUpdateIER(String TSONum) {
		Database.init(false);
		String circuitStatus = "";

		String query = "SELECT FullCCSD, Ignore, Installed FROM TSO WHERE TSONumber = '"
				+ TSONum + "'";
		ArrayList<String> al = Database.dbQuery(query);
		if (al.size() > 0) {
			for (int i = 0; i < al.size(); i += 3) {
				if (Integer.parseInt(al.get(i)) == 0) {
					String[] sa = TSONum.split("-");
					if (sa[1].equals("01")) {
						circuitStatus = "Active";
					} else if (sa[1].equals("99")) {
						circuitStatus = "Discontinued";
						String update = "UPDATE Circuits SET DeactivationDate = '"
								+ DateUtils.deactivateDate(true)
								+ "' WHERE FullCCSD = '" + al.get(i - 1) + "'";
						Database.dbUpdate(update);
						File path = null;
						if (Config.useChf) {
							path = new File(Config.chfPath);
						} else {
							path = new File(Config.chfTest);
						}
						File[] list = path.listFiles(new FileFilter() {

							@Override
							public boolean accept(File pathname) {
								return pathname.isDirectory();
							}
						});

						String root = "";
						for (File element : list) {
							if (element.toString().contains(
									al.get(i - 1).substring(4))) {
								root = element.toString();
							}
						}

						String newDir = root.substring(0,
								root.indexOf("Disco Pending)"))
								+ "Discontinued ["
								+ DateUtils.deactivateDate(false) + "])";
						try {
							FileUtils.copyDirectory(new File(root), new File(
									newDir));
							FileUtils.deleteDirectory(new File(root));
						} catch (IOException e) {
							LogHelper.severe(e.getMessage());
							e.printStackTrace();
						}
					} else if (sa[1].equals("99Z")) {
						circuitStatus = "Active";
					} else {
						circuitStatus = "Active";
					}
				}
				String update = "UPDATE Circuits SET CircuitStatus = '"
						+ circuitStatus + "' WHERE FullCCSD = '" + al.get(i)
						+ "'";
				Database.dbUpdate(update);
				update = "UPDATE TSO SET Installed = '" + -1
						+ "' WHERE TSONumber = '" + TSONum + "'";
				Database.dbUpdate(update);

			}
		}
		Database.init(true);
	}

	// TODO finish ignore repair
	public static void tsoIgnoreRepair() {
		Database.init(false);
		String query = "SELECT FullCCSD FROM Circuits";
		String CCSD = "";
		ArrayList<String> alCircuits = Database.dbQuery(query);
		for (String element : alCircuits) {
			CCSD = element;
			query = "SELECT ReportDate, Ignore FROM TSO WHERE FullCCSD = "
					+ "'" + CCSD + "'";
			ArrayList<String> alTso = Database.dbQuery(query);
			for (int i = 1; i < alTso.size(); i = +3) {

			}
		}
	}

	public static void databasePurge() {

	}
}
