package taytom258.core.util.db;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import taytom258.core.log.LogHelper;
import taytom258.core.util.DateUtils;
import taytom258.core.util.parsers.collections.TSOCollection;
import taytom258.lib.Strings;

public class TSOCommit extends DatabaseUtils {

	public static void run() throws SQLException {
		init(false);
		CMO();
		circuit();
		TSO();
		CircuitStatus.circuitStatusUpdateTSO(TSOCollection.fullCcsd);

		// String[] temp = Config.dbPath.split("[.]");
		// FileUtils.deleteQuietly(new File(temp[0] + "." + temp[1]));
		init(true);
	}

	private static void circuit() throws SQLException {
		String c = "', '";
		int andrewscmo, endpoint, userLetter, qc;

		if (TSOCollection.andrewsCmo) {
			andrewscmo = -1;
		} else {
			andrewscmo = 0;
		}
		if (TSOCollection.andrewsCmo
				&& !TSOCollection.location.equals(Strings.LOCATIONS[2])) {
			userLetter = -1;
			qc = -1;
		} else if (TSOCollection.andrewsCmo
				&& TSOCollection.location.equals(Strings.LOCATIONS[2])) {
			userLetter = 0;
			qc = -1;
		} else {
			userLetter = 0;
			qc = 0;
		}
		if (TSOCollection.endPoint) {
			endpoint = -1;
		} else {
			endpoint = 0;
		}

		String field = "FullCCSD, TrunkID, FullTSP, TSP, ToLocation, ToCode, FromLocation, FromCode, RequestingDepartment, TypeofService, "
				+ "CircuitUse, Security, DataRate, TrafficFlow, Term, AndrewsCMO, CMO, Signaling, "
				+ "QualityControlCode, EndPoint, CHFLink, UserLetterRequired, QCRequired, Location, MRC, NRC";
		String value = TSOCollection.fullCcsd + c + TSOCollection.trunkId + c
				+ TSOCollection.fullTsp + c + TSOCollection.tsp + c
				+ TSOCollection.toLocation + c + TSOCollection.toCode + c
				+ TSOCollection.fromLocation + c + TSOCollection.fromCode + c
				+ TSOCollection.requestingDept + c + TSOCollection.serviceType + c
				+ TSOCollection.circuitUse + c + TSOCollection.security + c
				+ TSOCollection.dataRate + c + TSOCollection.trafficFlow + c
				+ TSOCollection.serviceAvail + c + andrewscmo + c + TSOCollection.cmo
				+ c + TSOCollection.signaling + c + TSOCollection.qcc + c + endpoint
				+ c + TSOCollection.chfLink + c + userLetter + c + qc + c
				+ TSOCollection.location + c + TSOCollection.mrc + c + TSOCollection.nrc;
		TSOInsert("Circuits", field, value, TSOCollection.fullCcsd, "FullCCSD");
	}

	private static void TSO() throws SQLException {
		String c = "', '";

		int crr;
		if (TSOCollection.crr) {
			crr = -1;
		} else {
			crr = 0;
		}
		int careq;
		if (TSOCollection.careq) {
			careq = -1;
		} else {
			careq = 0;
		}

		boolean exists = false;
		ArrayList<String> rs = dbQuery("SELECT TSONumber " + "FROM	TSO");
		for (String element : rs) {
			if (element.equals(TSOCollection.tsoNum)) {
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
		String value = TSOCollection.tsoNum + c + TSOCollection.tsoSuffix + c
				+ TSOCollection.action + c + TSOCollection.fullCcsd + c
				+ DateUtils.dateConvert(TSOCollection.svcDate, false, true) + c
				+ DateUtils.dateConvert(TSOCollection.reportDate, false, true)
				+ c + crr + c + careq + c + ignore;
		if (exists) {
			LogHelper.info("TSO already exists in database, skipping");
		} else {
			TSOInsert("TSO", field, value, TSOCollection.tsoNum, "TSONumber");
		}
	}

	private static void CMO() throws SQLException {
		String c = "', '";

		String field = "CMO, CMODsn, CMOComm";
		String value = TSOCollection.cmo + c + TSOCollection.cmoDsn + c
				+ TSOCollection.cmoComm;
		TSOInsert("CMO", field, value, TSOCollection.cmo, "CMO");

	}

	private static boolean isLatest() {
		boolean latest = true;
		int t = -1;

		String query = "SELECT FullCCSD, ReportDate, TSONumber FROM TSO WHERE FullCCSD = "
				+ "'" + TSOCollection.fullCcsd + "'";
		ArrayList<String> al = dbQuery(query);

		if (al.size() > 0) {
			for (int i = 1; i < al.size(); i += 3) {
				Date current = null;
				Date pulled = null;
				try {
					current = new SimpleDateFormat("yyyy-MM-dd H:m:s")
					.parse(DateUtils.dateConvert(
							TSOCollection.reportDate, false, true));
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
					TSOUpdate("TSO", "Ignore", t, TSOCollection.fullCcsd,
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
