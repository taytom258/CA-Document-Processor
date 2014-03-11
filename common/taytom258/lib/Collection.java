package taytom258.lib;



public class Collection{

	public static boolean start, change, disco = false;
	
	//General Tab
	public static String tsoSubject, fullCcsd, svcDate, cmo, otherCmoDsn, otherCmoComm, extraComments, chfRootFolder;
	public static boolean logical, comReportRequired, ancs;
	
	//Start Tab
	public static String dataRate, serviceAvailible, tsp, startTsrNumber, startReportDate, startTsoStatement;
	public static boolean startIs1539, startHasSams, startIsAnalog, startIsPassthrough;
	
	//Change Tab
	public static String changeTsrNumber, changeReportDate, changeTsoStatement;
	public static boolean changeIs1539, changeHasSams, changeIsAnalog, changeIsPassthrough;
	
	//Disco Tab
	public static String discoTsrNumber, discoReportDate, discoTsoStatement;
	public static boolean discoIs1539, discoHasSams, discoIsAnalog, discoIsPassthrough;
}
