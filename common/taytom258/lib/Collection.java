package taytom258.lib;

import taytom258.core.util.LogHelper;


public class Collection{

	public static boolean start, change, disco;
	
	//General Tab
	public static String tsoSubject, fullCcsd, svcDate, cmo, otherCmoDsn, otherCmoComm, extraComments;
	public static boolean logical, comReportRequired;
	
	public static void debugGeneral(){
		LogHelper.debug(tsoSubject);
		LogHelper.debug(fullCcsd);
		LogHelper.debug(svcDate);
		LogHelper.debug(cmo);
//		LogHelper.debug(otherCmoDsn);
//		LogHelper.debug(otherCmoComm);
		LogHelper.debug(extraComments);
		
		LogHelper.debug(logical);
		LogHelper.debug(comReportRequired);
	}
}
