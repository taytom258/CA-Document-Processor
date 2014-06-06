package taytom258.lib;



public class Collection{

	public static String tsoText = "";
	
//	//Start Tab
//	public static String startTsrNumber, startReportDate, startTsoStatement;
//	public static boolean startIs1539, startHasSams, startIsAnalog, startIsPassthrough;
//	
//	//Change Tab
//	public static String changeTsrNumber, changeReportDate, changeTsoStatement;
//	public static boolean changeIs1539, changeHasSams, changeIsAnalog, changeIsPassthrough;
//	
//	//Disco Tab
//	public static String discoTsrNumber, discoReportDate, discoTsoStatement;
//	public static boolean discoIs1539, discoHasSams, discoIsAnalog, discoIsPassthrough;
//	
//	//Cancel Tab
//	public static String cancelTsrNumber, cancelReportDate, cancelTsoStatement;
//	
//	//Amend Tab
//	public static String amendTsrNumber, amendReportDate, amendTsoStatement;
//	public static boolean amendIs1539, amendHasSams, amendIsAnalog, amendIsPassthrough;
	
	//Popup
	public static boolean is1539, hasSams, isAnalog, isPassthrough, isLogical, isAncs = false;
	
	//Program Info
	public static String purpose, tsoSubject, tsrNum, chfRootFolder = "";
	public static boolean runClicked = false;
	
	//Access Database (Circuits)
	public static String fullCcsd, tsp, trunkId, fullTsp, toLocation, fromLocation, requestingDept, serviceType, circuitUse,
	security, dataRate, trafficFlow, serviceAvail, signaling, qcc, cmo, cmoDsn, cmoComm, chfLink, location, toCode, fromCode = "";
	public static boolean andrewsCmo, endPoint = false;
	public static double mrc, nrc = 0;
	
	//Access Database (TSO)
	public static String tsoNum, tsoSuffix, tsoAction, svcDate, reportDate = "";
	public static boolean crr, careq = false;
	
}
