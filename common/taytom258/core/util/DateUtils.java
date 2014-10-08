package taytom258.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import taytom258.lib.Reference;

public class DateUtils {

	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(Reference.DATE_FORMAT);
		return sdf.format(cal.getTime());

	}

	public static String deactivateDate(boolean includeTime) {
		String all = "";
		Date date = new Date();
		DateFormat yearF = new SimpleDateFormat("yyyy");
		DateFormat dayF = new SimpleDateFormat("dd");
		DateFormat intMonthF = new SimpleDateFormat("MM");
		DateFormat timeF = new SimpleDateFormat("HH:mm:ss");
		String year = yearF.format(date);
		String day = dayF.format(date);
		String month = intMonthF.format(date);
		String time = timeF.format(date);
		if (includeTime) {
			all = year + "-" + month + "-" + day + " " + time;
		} else {
			all = year + "-" + month + "-" + day;
		}
		return all;
	}

	public static String fileDate() {
		String all = "";
		Date date = new Date();
		DateFormat yearF = new SimpleDateFormat("yyyy");
		DateFormat dayF = new SimpleDateFormat("dd");
		DateFormat intMonthF = new SimpleDateFormat("MM");
		DateFormat timeF = new SimpleDateFormat("HHmmss");
		String year = yearF.format(date);
		String day = dayF.format(date);
		String month = intMonthF.format(date);
		String time = timeF.format(date);
		all = year + "-" + month + "-" + day + " " + time;
		return all;
	}
}
