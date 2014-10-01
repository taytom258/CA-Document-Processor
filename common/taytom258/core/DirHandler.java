package taytom258.core;

import java.io.File;
import java.io.IOException;

import taytom258.lib.Reference;

public class DirHandler {

	public static boolean createUserDir(final String dirName)
			throws IOException {
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
}
