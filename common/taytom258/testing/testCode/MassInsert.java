package taytom258.testing.testCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

import taytom258.core.log.LogHelper;

public class MassInsert {

	public static int currentNum = 0;
	//	public static String root = "\\\\ASPARAGUS\\Systems Control\\Circuit Actions\\CA Continuity"
	//			+ "\\Roles\\Document Tracker\\Document Processor\\TSOs";
	public static String root = "C:\\Java Development\\Work Programs\\Files\\TSOs";

	private static File[] readFolder(File path) {
		File[] files = path.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return !pathname.isDirectory();
			}
		});
		return files;
	}

	public static void test() {
		File path = new File(root + "\\Process");
		File[] files = readFolder(path);
		currentNum = 0;
		if (files.length != 0) {
			// InsertPop.appear(files);
			Quick.show(files);
		}
	}

	public static String readFile(String filePath) {
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
}
