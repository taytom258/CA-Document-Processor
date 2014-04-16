package taytom258.core.util.db;

import taytom258.core.util.Conversion;
import taytom258.lib.Collection;
import taytom258.lib.Strings;

public class TSOCommit extends Database {

	public static void run(){
		circuit();
		TSO();
	}
	
	private static void circuit(){
		String c = "', '";
		int andrewscmo, endpoint;
		
		if(Collection.andrewsCmo){
			andrewscmo = -1;
		}else{
			andrewscmo = 0;
		}
		if(Collection.endPoint){
			endpoint = -1;
		}else{
			endpoint = 0;
		}
		
		String field = "Full_CCSD, Trunk_ID, Full_TSP, TSP, To_Location, From_Location, Requesting_Department, Type_of_Service, "
				+ "Circuit_Use, Security, Data_Rate, Traffic_Flow, Term, Andrews_CMO, CMO, CMO_DSN, CMO_COMM, Signaling, "
				+ "Quality_Control_Code, EndPoint, CHF_Link, MRC, NRC";
		String value = Collection.fullCcsd+c+Collection.trunkId+c+Collection.fullTsp+c+Collection.tsp+c+Collection.toLocation+c
				+Collection.fromLocation+c+Collection.requestingDept+c+Collection.serviceType+c+Collection.circuitUse+c+Collection.security
				+c+Collection.dataRate+c+Collection.trafficFlow+c+Collection.serviceAvail+c+andrewscmo+c
				+Collection.cmo+c+Collection.cmoDsn+c+Collection.cmoComm+c+Collection.signaling+c+Collection.qcc+c+endpoint+c+Collection.chfLink
				+c+Collection.mrc+c+Collection.nrc;
		sqlInsert("Circuits", field, value, Collection.fullCcsd, Strings.KEY_FIELD);
	}
	
	private static void TSO(){
		String c = "', '";
		
		int crr;
		if(Collection.crr){
			crr = -1;
		}else{
			crr = 0;
		}
		int careq;
		if(Collection.careq){
			careq = -1;
		}else{
			careq = 0;
		}
		
		String field = "TSO_Number, TSO_Suffix, Action, Full_CCSD, Service_Date, Report_Date, Completion_Report_Req, CA_Action_Required";
		String value = Collection.tsoNum+c+Collection.tsoSuffix+c+Collection.tsoAction+c+Collection.fullCcsd+c+Conversion.dateConvert(Collection.svcDate)
				+c+Conversion.dateConvert(Collection.reportDate)+c+crr+c+careq;
		sqlInsert("TSO", field, value, Collection.tsoNum, "TSO_Number");
	}
}
