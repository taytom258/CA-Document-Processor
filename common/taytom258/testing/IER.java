package taytom258.testing;

import java.io.BufferedReader;
import java.io.File;
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
import taytom258.core.util.parsers.IERParser;
import taytom258.lib.Collection;

public class IER {

	public static void run() {
		String ss = "IER ";
		String pattern = "ddkkmm'Z 'MMM' 'yy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		
		//TODO fix "reports" not being recognized as folder
		if(rootFolder().isDirectory()){
			ArrayList<String> list = files(rootFolder());
			ArrayList<TreeMap<String, String>> iers = new ArrayList<TreeMap<String, String>>();
			TreeMap<Date, Integer> dates = new TreeMap<Date, Integer>();
			if (list.size() > 0) {
				for (String element : list) {
					if (element.contains(ss)) {
						iers.add(IERParser.parseIER(readFile(element.toString()),
								false));
						for (int i = 0; i < iers.size(); i++) {
							for (Map.Entry<String, String> entry : iers.get(i)
									.entrySet()) {
								if (entry.getKey().equals("Report Date")) {
									try {
										dates.put(format.parse(entry.getValue()), i);
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
			
			if(iers.size() > 0){
				SortedSet<Date> keys = new TreeSet<Date>(dates.keySet());
				for (Map.Entry<String, String> entry : iers.get(dates.get(keys.last()))
						.entrySet()) {
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
			folder = new File(Config.chfPath + "\\" + Collection.chfRootFolder
					+ "\\" + "Reports");
		} else {
			folder = new File(Config.chfTest + "\\" + Collection.chfRootFolder
					+ "\\" + "Reports");
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
		File path = null;
		if (Config.useChf) {
			path = new File(Config.chfPath);
		} else {
			path = new File(Config.chfTest);
		}

		Iterator<File> it = FileUtils.iterateFiles(path,
				TrueFileFilter.INSTANCE, null);
		while (it.hasNext()) {
			if (it.next().isDirectory()) {
				Collection.chfRootFolder = it.next().toString();
				// run();
			}
		}
	}

}
