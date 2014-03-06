package taytom258.window.core;

import taytom258.config.Config;
import taytom258.config.ConfigHandler;
import taytom258.window.Settings;

public class SettingsCore extends Settings{

	public static void save(){
		chfPath();
		chfTest();
		buttons();
		
		ConfigHandler.save();
	}
	
	private static void chfPath(){
		Config.chfPath = Settings.getTextFieldchfPath().getText().trim();
	}
	
	private static void chfTest(){
		Config.chfTest = Settings.getTextFieldchfTest().getText().trim();
	}
	
	private static void buttons(){
		Config.debug = Settings.getTglbtnDebugMode().isSelected();
		Config.autoSelection = Settings.getTglbtnAutoSelection().isSelected();
		Config.useChf = Settings.getTglbtnUseChf().isSelected();
	}
}
