package taytom258.lib;

import java.util.ArrayList;

public class Collection {
	
	/**
	 * Was the CCSD changed according to the TSO (are two CCSDs listed on the TSO)
	 */
	public static boolean ccsdChange;
	/**
	 * List of facilities collected
	 */
	public static ArrayList<String> facilities;

	@Deprecated
	public static void init() {
		facilities = new ArrayList<String>();
		enrCode = new ArrayList<String>();
		inputNeeded = new ArrayList<String>();
		userENRInput = new ArrayList<String>();
	}

	/**
	 * Set TSO Parser default values on parser start
	 */
	private static void TSOParserDefault(){
		
	}
	
	
//	// Popup
//	public static boolean is1539, hasSams, isAnalog, isPassthrough, isLogical,
//	isAncs = false;
//
//	// Program Info
//	public static String purpose, tsoSubject, tsrNum, chfRootFolder = "";
//	public static boolean runClicked, develop, ccsdChange = false;
//	public static String[] ccsdList;
//
//	// IER Info
//	public static String ierSubject, ierReportDate, ierTSONum, ierTSRNum,
//	ierFullCCSD, ierTSOAct, ierPOCInfo = "";
//
//	// Access Database (Circuits)
//	public static String fullCcsd, tsp, trunkId, fullTsp, toLocation,
//	fromLocation, requestingDept, serviceType, circuitUse, security,
//	dataRate, trafficFlow, serviceAvail, signaling, qcc, cmo, cmoDsn,
//	cmoComm, chfLink, location, toCode, fromCode = "";
//	public static boolean andrewsCmo, endPoint = false;
//	public static double mrc, nrc = 0;
//
//	// Access Database (TSO)
//	public static String tsoNum, tsoSuffix, tsoAction, svcDate, reportDate,
//	tsoText = "";
//	public static boolean crr, careq = false;
//	public static ArrayList<String> facilities, enrCode, inputNeeded,
//	userENRInput = null;

}
