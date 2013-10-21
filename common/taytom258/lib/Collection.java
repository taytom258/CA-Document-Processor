package taytom258.lib;

import taytom258.core.util.LogHelper;
import taytom258.window.Window;


public class Collection{

	public static boolean start, change, disco;
	
	//General Tab
	public static String tsoSubject, fullCcsd, svcDate, cmo, otherCmoDsn, otherCmoComm, extraComments;
	public static boolean logical, comReportRequired, ancs;
	
	//Start Tab
	public static String dataRate, serviceAvailible, tsp, startTsrNumber, startReportDate, startTsoStatement;
	public static boolean startIs1539, startHasSams, startIsAnalog, startIsPassthrough, startChf;
	
	//Change Tab
	public static String changeTsrNumber, changeReportDate, changeTsoStatement;
	public static boolean changeIs1539, changeHasSams, changeIsAnalog, changeIsPassthrough, changeChf;
	
	//Disco Tab
	public static String discoTsrNumber, discoReportDate, discoTsoStatement;
	public static boolean discoIs1539, discoHasSams, discoIsAnalog, discoIsPassthrough, discoChf;
	
	public static void debugGeneral(){
		LogHelper.debug("TSO Subject: " + tsoSubject);
		LogHelper.debug("Full CCSD: " + fullCcsd);
		LogHelper.debug("Service Date: " + svcDate);
		LogHelper.debug("CMO: " + cmo);
		
		if(Window.getRdbtnOther().isSelected()){
			LogHelper.debug("CMO DSN: " + otherCmoDsn);
			LogHelper.debug("CMO Comm: " + otherCmoComm);
		}
		
		LogHelper.debug("Logical?: " + logical);
		LogHelper.debug("Completion Report Required?: " + comReportRequired);
		
		LogHelper.debug("Extra Comments: " + extraComments);
		
	}
	
	public static void debugTabs(){
		if(start){
			LogHelper.debug("1539?: " + startIs1539);
			LogHelper.debug("Sams?: " + startHasSams);
			LogHelper.debug("Analog?: " + startIsAnalog);
			LogHelper.debug("Passthrough?: " + startIsPassthrough);
			LogHelper.debug("CHF?: " + startChf);
		
			LogHelper.debug("Data Rate: " + dataRate);
			LogHelper.debug("Service Availiability: " + serviceAvailible);
			LogHelper.debug("TSP: " + tsp);
			LogHelper.debug("Start TSR Number: " + startTsrNumber);
			LogHelper.debug("Start Report Date: " + startReportDate);
			LogHelper.debug("Start TSO Statement: " + startTsoStatement);
		}else if(change){
			LogHelper.debug("1539?: " + changeIs1539);
			LogHelper.debug("Sams?: " + changeHasSams);
			LogHelper.debug("Analog?: " + changeIsAnalog);
			LogHelper.debug("Passthrough?: " + changeIsPassthrough);
			LogHelper.debug("CHF?: " + changeChf);
		
			LogHelper.debug("Change TSR Number: " + changeTsrNumber);
			LogHelper.debug("Change Report Date: " + changeReportDate);
			LogHelper.debug("Change TSO Statement: " + changeTsoStatement);
		}else if(disco){
			LogHelper.debug("1539?: " + discoIs1539);
			LogHelper.debug("Sams?: " + discoHasSams);
			LogHelper.debug("Analog?: " + discoIsAnalog);
			LogHelper.debug("Passthrough?: " + discoIsPassthrough);
			LogHelper.debug("CHF?: " + discoChf);
			
			LogHelper.debug("Change TSR Number: " + discoTsrNumber);
			LogHelper.debug("Change Report Date: " + discoReportDate);
			LogHelper.debug("Change TSO Statement: " + discoTsoStatement);
		}else{
			LogHelper.severe("Unable to resolve tso type");
		}
	}
	
}
