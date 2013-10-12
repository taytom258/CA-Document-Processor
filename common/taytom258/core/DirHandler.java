package taytom258.core;

import java.io.File;
import java.io.IOException;

public class DirHandler {

	public static void createUserDir(final String dirName) throws IOException {
	    final File homeDir = new File(System.getProperty("user.home"));
	    final File dir = new File(homeDir, dirName);
	    if (!dir.exists() && !dir.mkdirs()) {
	        throw new IOException("Unable to create " + dir.getAbsolutePath());
	    }
	}
	
	public static void createDir(String dirName, String path) throws IOException {
	    final File dir = new File(path, dirName);
	    if (!dir.exists() && !dir.mkdirs()) {
	        throw new IOException("Unable to create " + dir.getAbsolutePath());
	    }
	}
}
