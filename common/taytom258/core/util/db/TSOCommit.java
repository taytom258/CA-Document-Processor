package taytom258.core.util.db;

import java.util.ArrayList;

import taytom258.core.util.Conversion;
import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;

public class TSOCommit extends Database {

	public static void run(){
		init(false);
		CMO();
		circuit();
		TSO();
		
//		String[] temp = Config.dbPath.split("[.]");
//		FileUtils.deleteQuietly(new File(temp[0] + "." + temp[1]));
		init(true);
	}
	
	private static void circuit(){
		String c = "', '";
		int andrewscmo, endpoint, userLetter, qc;
		
		if(Collection.andrewsCmo){
			andrewscmo = -1;
		}else{
			andrewscmo = 0;
		}
		if(Collection.andrewsCmo && !Collection.location.equals("Andrews 1539")){
			userLetter = -1;
			qc = -1;
		}else if(Collection.andrewsCmo && Collection.location.equals("Andrews 1539")){
			userLetter = 0;
			qc = -1;
		}else{
			userLetter = 0;
			qc = 0;
		}
		if(Collection.endPoint){
			endpoint = -1;
		}else{
			endpoint = 0;
		}
		
		String field = "FullCCSD, TrunkID, FullTSP, TSP, ToLocation, ToCode, FromLocation, FromCode, RequestingDepartment, TypeofService, "
				+ "CircuitUse, Security, DataRate, TrafficFlow, Term, AndrewsCMO, CMO, Signaling, "
				+ "QualityControlCode, EndPoint, CHFLink, UserLetterRequired, QCRequired, Location, MRC, NRC";
		String value = Collection.fullCcsd+c+Collection.trunkId+c+Collection.fullTsp+c+Collection.tsp+c+Collection.toLocation+c+Collection.toCode+c
				+Collection.fromLocation+c+Collection.fromCode+c+Collection.requestingDept+c+Collection.serviceType+c+Collection.circuitUse+c+Collection.security
				+c+Collection.dataRate+c+Collection.trafficFlow+c+Collection.serviceAvail+c+andrewscmo+c
				+Collection.cmo+c+Collection.signaling+c+Collection.qcc+c+endpoint+c+Collection.chfLink
				+c+userLetter+c+qc+c+Collection.location+c+Collection.mrc+c+Collection.nrc;
		sqlInsert("Circuits", field, value, Collection.fullCcsd, "FullCCSD");
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
		
		boolean exists = false;
		ArrayList<String> rs = sqlQuery("SELECT TSONumber "
				+ "FROM	TSO");
		for(String element : rs){
			if (element.equals(Collection.tsoNum)){
				exists = true;
			}
		}
		String field = "TSONumber, TSOSuffix, Action, FullCCSD, ServiceDate, ReportDate, CompletionReportReq, CAActionRequired";
		String value = Collection.tsoNum+c+Collection.tsoSuffix+c+Collection.tsoAction+c+Collection.fullCcsd+c+Conversion.dateConvert(Collection.svcDate, false, true)
				+c+Conversion.dateConvert(Collection.reportDate, false, true)+c+crr+c+careq;
		if (exists){
			LogHelper.info("TSO already exists in database, skipping");
		}else{
			sqlInsert("TSO", field, value, Collection.tsoNum, "TSONumber");
		}
	}
	
	private static void CMO(){
		String c = "', '";
		
		String field = "CMO, CMODsn, CMOComm";
		String value = Collection.cmo+c+Collection.cmoDsn+c+Collection.cmoComm;
		sqlInsert("CMO", field, value, Collection.cmo, "CMO");
		
	}
}
