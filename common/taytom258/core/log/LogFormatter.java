package taytom258.core.log;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import taytom258.core.util.DateUtils;

public class LogFormatter extends Formatter {

	public LogFormatter() {
		super();
	}

	@Override
	public String format(LogRecord record) {
		StringBuffer text = new StringBuffer();

		text.append(DateUtils.now());
		text.append(" " + record.getLevel().getName());
		text.append(": ");
		text.append(formatMessage(record));
		return text.toString();
	}

}
