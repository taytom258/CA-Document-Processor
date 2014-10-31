package taytom258.core.util.parsers.collections;

import java.util.ArrayList;

public class TSOCollection {
	
	//Strings
	
	/**
	 * TSO text to parse
	 */
	public static String tsoText;
	/**
	 * Local location collected from TSO
	 */
	public static String location;
	/**
	 * Full 8 character CCSD collected from TSO
	 */
	public static String fullCcsd;
	/**
	 * CHF root folder name for circuit collected from TSO
	 */
	public static String chfRootFolder;
	/**
	 * Trunk ID collected from TSO
	 */
	public static String trunkId;
	/**
	 * TSP restoration priority number (2 character) collected from TSO
	 */
	public static String tsp;
	/**
	 * Full TSP code collected from TSO
	 */
	public static String fullTsp;
	/**
	 * Human readable to location pulled from to code
	 */
	public static String toLocation;
	/**
	 * Non human readable to location code collected from TSO
	 */
	public static String toCode;
	/**
	 * Human readable from location pulled from to code
	 */
	public static String fromLocation;
	/**
	 * Non human readable from location code collected from TSO
	 */
	public static String fromCode;
	/**
	 * Department that requested the circuit order collected from TSO
	 */
	public static String requestingDept;
	/**
	 * Type of service the circuit provides collected from TSO
	 */
	public static String serviceType;
	/**
	 * What is this circuit used for? Collected from TSO.
	 */
	public static String circuitUse;
	/**
	 * Type of security applied to this circuit collected from TSO
	 */
	public static String security;
	/**
	 * Data rate of circuit collected from TSO
	 */
	public static String dataRate;
	/**
	 * Direction for traffic flow collected from TSO
	 */
	public static String trafficFlow;
	/**
	 * Availability of circuit collected from TSO
	 */
	public static String serviceAvail;
	/**
	 * Circuit management office (CMO) or Circuit control office (CCO) collected from TSO
	 */
	public static String cmo;
	/**
	 * Any signaling provided for analog circuits collected from TSO
	 */
	public static String signaling;
	/**
	 * Quality control code (QCC) collected from TSO
	 */
	public static String qcc;
	/**
	 * TSO number from collected TSO
	 */
	public static String tsoNum;
	/**
	 * 2 to 3 character TSO suffix collected from TSO
	 */
	public static String tsoSuffix;
	/**
	 * Action of collected TSO
	 */
	public static String action;
	/**
	 * Expected in service date of collected TSO
	 */
	public static String svcDate;
	/**
	 * Stated purpose of circuit collected from TSO
	 */
	public static String purpose;
	/**
	 * Subject line of collected TSO
	 */
	public static String tsoSubject;
	/**
	 * TSR number collected from TSO
	 */
	public static String tsrNum;
	/**
	 * Date TSO was generated collected from TSO
	 */
	public static String reportDate;
	/**
	 * Commercial number for CMO collected from TSO
	 */
	public static String cmoComm;
	/**
	 * DSN number for CMO collected from TSO
	 */
	public static String cmoDsn;
	/**
	 * Circuit location in CHF
	 */
	public static String chfLink;
	
	
	//ArrayLists
	
	/**
	 * ArrayList of facilities collected
	 */
	public static ArrayList<String> facilities;
	/**
	 * ArrayList of ENR codes collected from facilities
	 */
	public static ArrayList<String> enrCode;
	/**
	 * ArrayList of ENR codes requiring user input
	 */
	public static ArrayList<String> inputNeeded;
	/**
	 * ArrayList of ENR details input by user
	 */
	public static ArrayList<String> userENRInput;
	
	//Booleans
	
	/**
	 * Was the CCSD changed according to the TSO (are two CCSDs listed on the TSO)
	 */
	public static boolean ccsdChange;
	/**
	 * Is any action required on collected TSO
	 */
	public static boolean careq;
	/**
	 * Are we (Andrews) the CMO for this circuit
	 */
	public static boolean andrewsCmo;
	/**
	 * Are we (Andrews) an end point for this circuit collected from TSO
	 */
	public static boolean endPoint;
	/**
	 * Are we (Andrews) a pass through for this circuit collected from TSO
	 */
	public static boolean passthrough;
	/**
	 * Is a completion report (IER) required for this circuit collected from TSO
	 */
	public static boolean crr;
	
	//Doubles
	
	/**
	 * Monthly recurring cost (MRC), monthly payment, of circuit in TSO collected
	 */
	public static double mrc;
	/**
	 * Non recurring cost (NRC), one time fee, of circuit in TSO collected
	 */
	public static double nrc;
	
	
	/**
	 * Initialize the ArrayLists
	 */
	public static void init() {
		facilities = new ArrayList<String>();
		enrCode = new ArrayList<String>();
		inputNeeded = new ArrayList<String>();
		userENRInput = new ArrayList<String>();
	}
}
