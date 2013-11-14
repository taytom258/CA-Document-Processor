package taytom258.lib;

import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.jar.JarFile;

import taytom258.Start;

public class Reference {

	//Location Code
	@SuppressWarnings("unused")
	public Reference(){
	JarFile myJar;
	CodeSource codeSource = Start.class.getProtectionDomain().getCodeSource();
	File jarFile = null;
	try {
		jarFile = new File(codeSource.getLocation().toURI().getPath());
	} catch (URISyntaxException e) {
		e.printStackTrace();
	}
	String jarDir = jarFile.getParentFile().getPath();
	}
	
	
	//Constants
	public static final String APPLICATION_NAME = "TSO Helper";
	public static final String APPLICATION_VERSION = "@VERSION@";
	public static final String BUILD_NUMBER = "@BUILD_NUMBER@";
	public static final String FINGERPRINT = "@FINGERPRINT@";
	public static final String AUTHOR = "taytom258";
	public static final String USER_HOME = System.getProperty("user.home").concat("\\"+AUTHOR);
	
}
