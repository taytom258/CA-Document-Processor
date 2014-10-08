package taytom258.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import taytom258.config.Config;
import taytom258.core.util.LogHelper;
import taytom258.core.util.db.CircuitStatus;
import taytom258.core.util.db.Database;
import taytom258.core.util.parsers.IERParser;
import taytom258.lib.Collection;

public class IER {

	public static void run() {
		String ss = "IER ";
		String pattern = "ddkkmm'Z 'MMM' 'yy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);

		if (rootFolder().isDirectory()) {
			ArrayList<String> list = files(rootFolder());
			ArrayList<TreeMap<String, String>> iers = new ArrayList<TreeMap<String, String>>();
			TreeMap<Date, Integer> dates = new TreeMap<Date, Integer>();
			if (list.size() > 0) {
				for (String element : list) {
					if (element.contains(ss)) {
						iers.add(IERParser.parseIER(
								readFile(element.toString()), false));
						for (int i = 0; i < iers.size(); i++) {
							for (Map.Entry<String, String> entry : iers.get(i)
									.entrySet()) {
								if (entry.getKey().equals("Report Date")) {
									try {
										dates.put(
												format.parse(entry.getValue()),
												i);
									} catch (ParseException e) {
										LogHelper.severe(e.getMessage());
										e.printStackTrace();
									}
								}

							}
						}
					}
				}
			}

			if (iers.size() > 0) {
				SortedSet<Date> keys = new TreeSet<Date>(dates.keySet());
				for (Map.Entry<String, String> entry : iers.get(
						dates.get(keys.last())).entrySet()) {
					if (entry.getKey().equals("Subject")) {
						Collection.ierSubject = entry.getValue();
					} else if (entry.getKey().equals("Report Date")) {
						Collection.ierReportDate = entry.getValue();
					} else if (entry.getKey().equals("TSO Number")) {
						Collection.ierTSONum = entry.getValue();
					} else if (entry.getKey().equals("TSR Number")) {
						Collection.ierTSRNum = entry.getValue();
					} else if (entry.getKey().equals("Full CCSD")) {
						Collection.ierFullCCSD = entry.getValue();
					} else if (entry.getKey().equals("TSO Action")) {
						Collection.ierTSOAct = entry.getValue();
					} else if (entry.getKey().equals("POC Info")) {
						Collection.ierPOCInfo = entry.getValue();
					}
				}
				CircuitStatus.circuitStatusUpdateIER(Collection.ierTSONum);
			}
		}
	}

	private static File rootFolder() {
		File folder = null;
		if (Config.useChf) {
			folder = new File(Collection.chfRootFolder + "\\" + "Reports");
		} else {
			folder = new File(Collection.chfRootFolder + "\\" + "Reports");
		}
		return folder;
	}

	private static ArrayList<String> files(File folder) {
		ArrayList<String> fileList = new ArrayList<String>();
		Iterator<File> it = FileUtils.iterateFiles(folder, null, false);
		while (it.hasNext()) {
			if (folder.exists()) {
				fileList.add(folder + "\\" + it.next().getName());
			}
		}
		return fileList;
	}

	private static String readFile(String filePath) {
		BufferedReader br = null;
		String everything = "";
		try {
			br = new BufferedReader(new FileReader(filePath));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			everything = sb.toString();
		} catch (Exception e) {
			LogHelper.severe(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				LogHelper.severe(e.getMessage());
				e.printStackTrace();
			}
		}
		return everything;
	}

	@Deprecated
	public static void runOnceCleanUp() {
		Database.init(false);
		ArrayList<String> al = new ArrayList<String>();
		String query = "SELECT FullCCSD FROM Circuits";
		String as = "";
		String bs = "";
		File path = null;
		al = Database.dbQuery(query);

		if (Config.useChf) {
			path = new File(Config.chfPath);
		} else {
			path = new File(Config.chfTest);
		}

		File[] subDirs = path.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});

		if (al.size() > 0) {
			for (String element : al) {
				as = element.substring(0, 4);
				bs = element.substring(4);
				String filename = bs + " (" + as + ")";
				for (File folder : subDirs) {
					if (folder.toString().contains(filename)) {
						Collection.chfRootFolder = folder.toString();
						IER.run();
					}
				}
			}
		}
		Database.init(true);
	}

}
