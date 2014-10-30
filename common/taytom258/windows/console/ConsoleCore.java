package taytom258.windows.console;

import java.io.File;

import taytom258.core.util.IOUtils;
import taytom258.core.util.popups.NormalPop;

/**
 * Class containing code for support of console.
 * @author taytom258
 * @see Console
 */
public class ConsoleCore {

	/**
	 * Copies the given html log string to file
	 * @param log string of log data
	 * @param file location of .txt file
	 */
	protected static void logCopy(String log, File file){
		if (file.exists()) {
			file.delete();
		}
		IOUtils.fileWriter(log.trim().replaceAll("\\<.*?\\>", ""),file);
		NormalPop.appear("Saved", "Log file saved to " + file.toString());
	}
}
