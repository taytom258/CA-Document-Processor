package taytom258.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import taytom258.core.log.LogHelper;
import taytom258.lib.Reference;

public class IOUtils {

	private static void textClear(String FileName) throws IOException {
		FileWriter fstream = new FileWriter(FileName, true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("");
		out.close();
	}

	private static void textWriter(String FileName, String Content, boolean NewLine) throws IOException {
		FileWriter fstream = new FileWriter(FileName, true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.append(Content);
		if (NewLine) {
			out.newLine();
		}
		out.close();
	}

	public static boolean fileWriter(String line, String path) {
		String[] array = line.split("\\n");
		for (int i = 0; i < array.length; i++) {
			boolean notLastLine = true;
			if (i == array.length - 1) {
				notLastLine = false;
			}
			try {
				textClear(path);
				textWriter(path, array[i], notLastLine);
			} catch (Exception e) {
				LogHelper.severe("Could not print file to location");
				return false;
			}
		}
		return true;
	}

	public static boolean fileWriter(String line, File path) {
		String[] array = line.split("\\n");
		for (int i = 0; i < array.length; i++) {
			boolean notLastLine = true;
			if (i == array.length - 1) {
				notLastLine = false;
			}
			try {
				textClear(path.toString());
				textWriter(path.toString(), array[i], notLastLine);
			} catch (Exception e) {
				LogHelper.severe("Could not print file to location");
				return false;
			}
		}
		return true;
	}

	public static boolean createUserDir(String dirName) throws IOException {
		final File homeDir = Reference.USER_ROOT;
		final File dir = new File(homeDir, dirName);
		if (!dir.exists() && !dir.mkdirs()) {
			throw new IOException("Unable to create " + dir.getAbsolutePath());
		}
		return true;
	}

	public static boolean createDir(String dirName, String path)
			throws IOException {
		final File dir = new File(path, dirName);
		if (!dir.exists() && !dir.mkdirs()) {
			throw new IOException("Unable to create " + dir.getAbsolutePath());
		}
		return true;
	}
	
	public static String textReader(String filePath) {
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
