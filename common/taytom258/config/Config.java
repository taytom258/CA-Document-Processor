package taytom258.config;

import taytom258.lib.Reference;

public class Config {

	public static String chfPath, chfTest, dbPath;
	public static boolean useChf, autoSelection, debug, error;
	
	public static final String[] CONFIG_NAMES = {"S_chf_Path", "S_chf_Test", "b_use_Chf", "b_auto_Selection", "b_debug", "b_error", "S_db_Path"};
	
	//Default Config
	public static final String CONFIG_HEADER = Reference.APPLICATION_NAME+" "+Reference.APPLICATION_VERSION+"."+Reference.BUILD_NUMBER;
	public static final String CONFIG_PATH = System.getProperty("user.home").concat("\\"+Reference.AUTHOR);
	
	//TODO Insert defaults
	public static final String CHF_PATH = "\\\\ASPARAGUS\\Circuit History Folders";
	public static final String CHF_TEST = Reference.findMe();
//	public static final String DB_PATH = Reference.findMe() + "\\DB.accdb";
	public static final String DB_PATH = "\\\\ASPARAGUS\\Database\\Circuit_Database_TNT.accdb";
	
	public static final boolean USE_CHF = false;
	public static final boolean AUTO_SELECTION = false;
	public static boolean DEBUG = false;
	public static boolean ERROR = true;
}
