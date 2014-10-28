package taytom258.window.core;

import taytom258.config.Config;
import taytom258.config.ConfigHandler;
import taytom258.window.Settings;

public class SettingsCore extends Settings {

	public static void save() {
		lines();
		buttons();

		ConfigHandler.save();
	}

	private static void lines() {
		Config.chfPath = Settings.getTextFieldchfPath().getText().trim();
		Config.chfTest = Settings.getTextFieldchfTest().getText().trim();
		Config.dbPath = Settings.getTextFielddbPath().getText().trim();
	}

	private static void buttons() {
		Config.debug = Settings.getTglbtnDebugMode().isSelected();
		Config.autoSelection = Settings.getTglbtnAutoSelection().isSelected();
		Config.useChf = Settings.getTglbtnUseChf().isSelected();
		Config.error = Settings.getTglbtnError().isSelected();
		Config.autoCopy = Settings.getTglbtnAutoCopy().isSelected();
	}
}
