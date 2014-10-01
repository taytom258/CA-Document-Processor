package taytom258.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import taytom258.lib.Reference;

public class DateUtils {

	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(Reference.DATE_FORMAT);
		return sdf.format(cal.getTime());
		
	}
}
