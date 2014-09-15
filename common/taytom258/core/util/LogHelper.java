package taytom258.core.util;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import taytom258.config.Config;
import taytom258.core.log.LogHandler;
import taytom258.lib.Collection;
import taytom258.lib.Reference;
import taytom258.lib.Strings;

public class LogHelper {

	private static Logger logger = Logger.getLogger(Reference.APPLICATION_NAME);
	private static Handler handler = new LogHandler();

	public static void init() {
		logger.setUseParentHandlers(false);
		logger.addHandler(handler);
		if (Reference.BUILD_NUMBER.equals("@BUILD_NUMBER@")) {
			log(Level.WARNING, Strings.DEVELOP_WARN);
			Collection.develop = true;
		}

	}

	private static void log(Level logLevel, Object object) {
		logger.log(logLevel, object.toString());

		if (logLevel == Level.SEVERE) {
		}
	}

	public static void severe(Object object) {
		log(Level.SEVERE, object.toString());
		if (Config.error) {
			PopHelper.appear("ERROR: SEVERE", object.toString());
		}
	}

	public static void debug(Object object) {

		if (Config.debug) {
			log(Level.INFO, "[DEBUG] " + object.toString());
		}
	}

	public static void warning(Object object) {

		log(Level.WARNING, object.toString());
		if (Config.error) {
			PopHelper.appear("ERROR: WARNING", object.toString());
		}
	}

	public static void info(Object object) {

		log(Level.INFO, object.toString());
	}

	public static void config(Object object) {

		log(Level.CONFIG, object.toString());
	}

	public static void fine(Object object) {

		log(Level.FINE, object.toString());
	}

	public static void finer(Object object) {

		log(Level.FINER, object.toString());
	}

	public static void finest(Object object) {

		log(Level.FINEST, object.toString());
	}

	public static void io(Object object) {

		log(Level.INFO, "[IO] " + object.toString());
	}

	public static void popup(Object object) {

		log(Level.INFO, object.toString());
		if (Config.error) {
			PopHelper.appear("INFO", object.toString());
		}
	}

	public static void db(Object object) {
		if (Config.debug) {
			log(Level.INFO, "[DEBUG][DB] " + object.toString());
		}
	}
}
