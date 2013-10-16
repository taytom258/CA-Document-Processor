package taytom258.core.util;

public class Conversion {

	public static String Month_StringtoInt(String month){
		
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
	
}
