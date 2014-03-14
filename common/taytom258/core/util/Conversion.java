package taytom258.core.util;


public class Conversion {

	public static String MMM_StringtoInt(String month){
		
		String x = "0";
		
		if (month.equals("JAN")){
			x = "1";
		}else if (month.equals("FEB")){
			x = "2";
		}else if (month.equals("MAR")){
			x = "3";
		}else if (month.equals("APR")){
			x = "4";
		}else if (month.equals("MAY")){
			x = "5";
		}else if (month.equals("JUN")){
			x = "6";
		}else if (month.equals("JUL")){
			x = "7";
		}else if (month.equals("AUG")){
			x = "8";
		}else if (month.equals("SEP")){
			x = "9";
		}else if (month.equals("OCT")){
			x = "10";
		}else if (month.equals("NOV")){
			x = "11";
		}else if (month.equals("DEC")){
			x = "12";
		}
		return x;
	}
	
	public static String dateConvert(String format){
		
		format.toUpperCase().trim();
		
		String day = format.substring(0, 2);
		
		String[] temp = format.split("\\s");
		String month = temp[1];
		
		String year = temp[2];
		
		String time = format.substring(2, 7);
		
		String date = day + " " + month + " " + year + " " + time;
		
		return date;
	}
	
}
