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

	private static String MMM_StringtoInt(String month) {

		String x = "0";

		if (month.equals("JAN")) {
			x = "01";
		} else if (month.equals("FEB")) {
			x = "02";
		} else if (month.equals("MAR")) {
			x = "03";
		} else if (month.equals("APR")) {
			x = "04";
		} else if (month.equals("MAY")) {
			x = "05";
		} else if (month.equals("JUN")) {
			x = "06";
		} else if (month.equals("JUL")) {
			x = "07";
		} else if (month.equals("AUG")) {
			x = "08";
		} else if (month.equals("SEP")) {
			x = "09";
		} else if (month.equals("OCT")) {
			x = "10";
		} else if (month.equals("NOV")) {
			x = "11";
		} else if (month.equals("DEC")) {
			x = "12";
		}
		return x;
	}

	public static String dateConvert(String format, boolean stringM, boolean db) {

		format.toUpperCase().trim();

		String day = format.substring(0, 2);

		String[] temp = format.split("\\s");
		String month = temp[1];

		String year = temp[2];

		String time = format.substring(2, 7);

		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String yearpre = dateFormat.format(date).substring(0, 2);

		String timeFormat = time.substring(0, 2);
		timeFormat += ":" + time.substring(2, 4) + ":00";

		String date2 = day + " " + month + " " + year + " " + time;
		String date3 = MMM_StringtoInt(month) + "/" + day + "/" + year + " "
				+ time;
		// String dateDB = MMM_StringtoInt(month) + "/" + day + "/" + yearpre +
		// year;
		String dateDB = yearpre + year + "-" + MMM_StringtoInt(month) + "-"
				+ day + " " + timeFormat;

		if (stringM) {
			return date2;
		} else if (db) {
			return dateDB;
		} else {
			return date3;
		}
	}
}
