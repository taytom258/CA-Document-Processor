package taytom258.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import taytom258.core.util.LogHelper;

public class WriteHandler {

	// Start code copied from stack overflow
	private static void textClear(String FileName) throws IOException {
		FileWriter fstream = new FileWriter(FileName, true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write("");
		out.close();
	}

	private static void textWriter(String FileName, String Content,
			boolean NewLine) throws IOException {
		FileWriter fstream = new FileWriter(FileName, true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.append(Content);
		if (NewLine) {
			out.newLine();
		}
		out.close();
	}

	// End code copied from stack overflow

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
}
