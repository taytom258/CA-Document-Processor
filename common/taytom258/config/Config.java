package taytom258.config;

import taytom258.lib.Reference;

public class Config {

	public static String chfPath, chfTest;
	public static boolean useChf, autoSelection, debug, error;
	
	public static final String[] CONFIG_NAMES = {"S_chf_Path", "S_chf_Test", "b_use_Chf", "b_auto_Selection", "b_debug", "b_error"};
	
	//Default Config
	public static final String CONFIG_HEADER = Reference.APPLICATION_NAME+" "+Reference.APPLICATION_VERSION+"."+Reference.BUILD_NUMBER;
	public static final String CONFIG_PATH = System.getProperty("user.home").concat("\\"+Reference.AUTHOR);
	
	public static final String CHF_PATH = "";
	public static final String CHF_TEST = "";
	
	public static final boolean USE_CHF = false;
	public static final boolean AUTO_SELECTION = false;
	public static boolean DEBUG = false;
	public static boolean ERROR = true;
}
