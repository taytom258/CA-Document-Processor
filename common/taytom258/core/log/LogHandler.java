package taytom258.core.log;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import taytom258.window.Console;

public class LogHandler extends Handler {

	public static ArrayList<String> consoleList = new ArrayList<String>();

	public LogHandler() {
		super();

		LogFormatter format = new LogFormatter();
		setFormatter(format);
	}

	@Override
	public void close() throws SecurityException {

	}

	@Override
	public void flush() {

	}

	@Override
	public void publish(final LogRecord record) {

		String write = getFormatter().format(record);
		if (!isLoggable(record)) {
			return;
		}
		System.out.println(getFormatter().format(record));
		try {
			if (write.contains("WARNING")) {
				Console.insertHTML(write, "#FFA500");
			} else if (write.contains("SEVERE")) {
				Console.insertHTML(write, "red");
			} else {
				Console.insertHTML(write, "black");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.severe(e.getMessage());
		}
		// Console.getTextArea().setText(console(getFormatter().format(record),
		// consoleList));
	}

	private static String console(String record, ArrayList<String> array) {
		array.add(record);
		String temp = "";
		for (String element : array) {
			temp += element.trim() + "\n";
		}
		return temp;
	}
}
