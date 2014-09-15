package taytom258.core.util.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import taytom258.core.util.Conversion;
import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.lib.Strings;

public class TSOCommit extends Database {

	public static void run() {
		init(false);
		CMO();
		circuit();
		TSO();
		CircuitStatus.circuitStatusUpdateTSO(Collection.fullCcsd);

		// String[] temp = Config.dbPath.split("[.]");
		// FileUtils.deleteQuietly(new File(temp[0] + "." + temp[1]));
		init(true);
	}

	private static void circuit() {
		String c = "', '";
		int andrewscmo, endpoint, userLetter, qc;

		if (Collection.andrewsCmo) {
			andrewscmo = -1;
		} else {
			andrewscmo = 0;
		}
		if (Collection.andrewsCmo
				&& !Collection.location.equals(Strings.LOCATIONS[2])) {
			userLetter = -1;
			qc = -1;
		} else if (Collection.andrewsCmo
				&& Collection.location.equals(Strings.LOCATIONS[2])) {
			userLetter = 0;
			qc = -1;
		} else {
			userLetter = 0;
			qc = 0;
		}
		if (Collection.endPoint) {
			endpoint = -1;
		} else {
			endpoint = 0;
		}

		String field = "FullCCSD, TrunkID, FullTSP, TSP, ToLocation, ToCode, FromLocation, FromCode, RequestingDepartment, TypeofService, "
				+ "CircuitUse, Security, DataRate, TrafficFlow, Term, AndrewsCMO, CMO, Signaling, "
				+ "QualityControlCode, EndPoint, CHFLink, UserLetterRequired, QCRequired, Location, MRC, NRC";
		String value = Collection.fullCcsd + c + Collection.trunkId + c
				+ Collection.fullTsp + c + Collection.tsp + c
				+ Collection.toLocation + c + Collection.toCode + c
				+ Collection.fromLocation + c + Collection.fromCode + c
				+ Collection.requestingDept + c + Collection.serviceType + c
				+ Collection.circuitUse + c + Collection.security + c
				+ Collection.dataRate + c + Collection.trafficFlow + c
				+ Collection.serviceAvail + c + andrewscmo + c + Collection.cmo
				+ c + Collection.signaling + c + Collection.qcc + c + endpoint
				+ c + Collection.chfLink + c + userLetter + c + qc + c
				+ Collection.location + c + Collection.mrc + c + Collection.nrc;
		TSOInsert("Circuits", field, value, Collection.fullCcsd, "FullCCSD");
	}

	private static void TSO() {
		String c = "', '";

		int crr;
		if (Collection.crr) {
			crr = -1;
		} else {
			crr = 0;
		}
		int careq;
		if (Collection.careq) {
			careq = -1;
		} else {
			careq = 0;
		}

		boolean exists = false;
		ArrayList<String> rs = dbQuery("SELECT TSONumber " + "FROM	TSO");
		for (String element : rs) {
			if (element.equals(Collection.tsoNum)) {
				exists = true;
			}
		}
		int ignore = 0;
		if (!exists) {
			if (isLatest()) {
				ignore = 0;
			} else {
				ignore = -1;
			}
		}

		String field = "TSONumber, TSOSuffix, Action, FullCCSD, ServiceDate, ReportDate, CompletionReportReq, CAActionRequired, Ignore";
		String value = Collection.tsoNum + c + Collection.tsoSuffix + c
				+ Collection.tsoAction + c + Collection.fullCcsd + c
				+ Conversion.dateConvert(Collection.svcDate, false, true) + c
				+ Conversion.dateConvert(Collection.reportDate, false, true)
				+ c + crr + c + careq + c + ignore;
		if (exists) {
			LogHelper.info("TSO already exists in database, skipping");
		} else {
			TSOInsert("TSO", field, value, Collection.tsoNum, "TSONumber");
		}
	}

	private static void CMO() {
		String c = "', '";

		String field = "CMO, CMODsn, CMOComm";
		String value = Collection.cmo + c + Collection.cmoDsn + c
				+ Collection.cmoComm;
		TSOInsert("CMO", field, value, Collection.cmo, "CMO");

	}

	private static boolean isLatest() {
		boolean latest = true;
		int t = -1;

		String query = "SELECT FullCCSD, ReportDate, TSONumber FROM TSO WHERE FullCCSD = "
				+ "'" + Collection.fullCcsd + "'";
		ArrayList<String> al = dbQuery(query);

		if (al.size() > 0) {
			for (int i = 1; i < al.size(); i += 3) {
				Date current = null;
				Date pulled = null;
				try {
					current = new SimpleDateFormat("yyyy-MM-dd H:m:s")
							.parse(Conversion.dateConvert(
									Collection.reportDate, false, true));
					pulled = new SimpleDateFormat("yyyy-MM-dd H:m:s").parse(al
							.get(i));
					// System.out.println(current);
					// System.out.println(pulled);
				} catch (ParseException e) {
					e.printStackTrace();
					LogHelper.severe(e.getMessage());
				}
				if (!current.after(pulled)) {
					latest = false;
				} else if (current.after(pulled)) {
					TSOUpdate("TSO", "Ignore", t, Collection.fullCcsd,
							"FullCCSD");
					// update = "UPDATE TSO SET Ignore = "+ t
					// +" WHERE FullCCSD = '"+Collection.fullCcsd+"'";
					// System.out.println(update);
					latest = true;
				}
			}
			// for(int i=0;i<al.size();i++){
			// System.out.println(al.get(i));
			// }
		}
		return latest;
	}
}
