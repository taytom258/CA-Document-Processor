package taytom258.lib;

import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.jar.JarFile;

import taytom258.Start;
import taytom258.core.util.LogHelper;

public class Reference {

	// Location Code
	public static String findMe() {
		JarFile myJar;
		CodeSource codeSource = Start.class.getProtectionDomain()
				.getCodeSource();
		File jarFile = null;
		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			LogHelper.severe(e.getMessage());
		}
		String jarDir = jarFile.getParentFile().getPath();
		return jarDir;
	}

	// Constants
	public static final String APPLICATION_NAME = "TSO Helper";
	public static final String APPLICATION_VERSION = "@VERSION@";
	public static final String BUILD_NUMBER = "@BUILD_NUMBER@";
	public static final String FINGERPRINT = "@FINGERPRINT@";
	public static final String AUTHOR = "taytom258";
	public static final String DATE_FORMAT = "dd-MMM-yyyy HH:mm:ss";
	public static final int NUM_OPERATIONS = 4;
	public static final File USER_ROOT = new File(
			System.getProperty("user.home") + "\\AppData\\Roaming");
	public static final String DB_USER = "java";
	public static final String DB_PASS = "databasePassword";

	// Variables

}
