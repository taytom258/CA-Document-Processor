package taytom258.lib;

import java.nio.CharBuffer;

import taytom258.core.util.LogHelper;

public class Collection{

	public static boolean change = false;
	public static boolean start = false;
	public static boolean disco = false;
	
	public static String subject;
	public static String ccsd;
	public static String svcDate_Day, svcDate_Month, svcDate_Year, svcDate, svcDate_MonthInt;
	public static String cmo;
	public static boolean logical;
	public static boolean comReport;
	public static String comments;
	
	public static void debugGeneral(){
		CharBuffer group = CharBuffer.allocate(2);
		group.append("/");
		group.append(" ");
		
		LogHelper.debug("TSO Subject: " + subject);
		LogHelper.debug("Full CCSD: " + ccsd);
		LogHelper.debug("Service Date: " + svcDate);
		LogHelper.debug("CMO: " + cmo);
		LogHelper.debug("Logical: " + logical);
		LogHelper.debug("Completion Report?: " + comReport);
		LogHelper.debug("Comments: " + comments);
	}
}
