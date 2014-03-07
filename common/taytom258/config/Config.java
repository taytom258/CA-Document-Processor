package taytom258.config;

import taytom258.lib.Reference;

public class Config {

	public static String chfPath, chfTest;
	public static boolean useChf, autoSelection, debug;
	
	public static final String[] CONFIG_NAMES = {"S\\:chf_Path", "S\\:chf_Test", "b\\:use_Chf", "b\\:auto_Selection", "b\\:debug"};
	
	//Default Config
	public static final String CONFIG_HEADER = Reference.APPLICATION_NAME+" "+Reference.APPLICATION_VERSION+"."+Reference.BUILD_NUMBER;
	public static final String CONFIG_PATH = System.getProperty("user.home").concat("\\"+Reference.AUTHOR);
	
	public static final String CHF_PATH = "";
	public static final String CHF_TEST = "";
	
	public static final boolean USE_CHF = false;
	public static final boolean AUTO_SELECTION = false;
	public static boolean DEBUG = false;
}
