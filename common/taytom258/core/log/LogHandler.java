package taytom258.core.log;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LogHandler extends Handler {

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
		if(!isLoggable(record)){
			return;
		}
		System.out.println(getFormatter().format(record));
	}

}
