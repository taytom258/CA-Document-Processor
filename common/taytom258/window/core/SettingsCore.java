package taytom258.window.core;

import taytom258.config.Config;
import taytom258.config.ConfigHandler;
import taytom258.window.Settings;

public class SettingsCore {

	public static void save(){
		chfPath();
		chfTest();
		configLoc();
		debug();
		userConfig();
		useChf();
		
		ConfigHandler.createRootDir();
		ConfigHandler.write(Config.chfPath+"\n"+Config.chfTest+"\n"+Config.configPath+"\n"+Config.debug+"\n"+Config.configUser+"\n"+
				Config.useChf+"\n");
	}
	
	private static void chfPath(){
		Config.chfPath = Settings.getTextFieldchfPath().getText();
	}
	
	private static void chfTest(){
		Config.chfTest = Settings.getTextFieldchfTest().getText();
	}
	
	private static void configLoc(){
		Config.configPath = Settings.getTextFieldconfigLoc().getText();
	}
	
	private static void debug(){
		Config.debug = Settings.getTglbtnDebugMode().isSelected();
	}
	
	private static void userConfig(){
		Config.configUser = Settings.getTglbtnUserStoredConfig().isSelected();
	}
	
	private static void useChf(){
		Config.useChf = Settings.getTglbtnUseChf().isSelected();
	}
}
