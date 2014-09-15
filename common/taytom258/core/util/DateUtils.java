package taytom258.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import taytom258.config.Config;
import taytom258.lib.Reference;

public class DateUtils {

	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(Reference.DATE_FORMAT);
		return sdf.format(cal.getTime());
	}
	
	//TODO finish date handling for circuit status repair
	public static boolean testDate(int numberDays){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date today = c.getTime();
		
		
		
	}
	
	public static void setDate(){
		Calendar c = Calendar.getInstance();
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String month = String.valueOf(c.get(Calendar.mon))
		Config.repair = ""
	}
}
