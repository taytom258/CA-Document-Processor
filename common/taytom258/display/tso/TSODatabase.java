package taytom258.display.tso;

import taytom258.core.log.LogHelper;
import taytom258.core.util.DateUtils;
import taytom258.core.util.parsers.collections.TSOCollection;
import taytom258.windows.main.tsoTab.TSO_DB_TabPane;

/**
 * Class for display of information on DB tab
 * @author taytom258
 *
 */
public class TSODatabase extends TSO_DB_TabPane {

	private static final long serialVersionUID = -3884967648371445162L;

	/**
	 * Display information based on collected data
	 */
	public static void display() {
		circuit();
		tso();
		LogHelper.info("TSO (Database) Tab Complete");
	}

	/**
	 * Display information for circuit tab based on collected information
	 */
	private static void circuit() {
		textFields_Circuit();
		checkBoxes_Circuit();
	}

	/**
	 * Display information for tso tab based on collected information
	 */
	private static void tso() {
		textFields_Tso();
		checkBoxes_Tso();
	}

	/**
	 * Set circuit text fields based on collected information
	 */
	private static void textFields_Circuit() {
		textFieldFullCcsdCircuit.setText(TSOCollection.fullCcsd);
		textFieldtrunkId.setText(TSOCollection.trunkId);
		textFieldfullTsp.setText(TSOCollection.fullTsp);
		textFieldtsp.setText(TSOCollection.tsp);
		textFieldsecurity.setText(TSOCollection.security);
		textFieldrate.setText(TSOCollection.dataRate);
		textFieldflow.setText(TSOCollection.trafficFlow);
		textFieldavail.setText(TSOCollection.serviceAvail);
		textFieldsignal.setText(TSOCollection.signaling);
		textFieldqcc.setText(TSOCollection.qcc);
		textFieldto.setText(TSOCollection.toLocation);
		textFieldfrom.setText(TSOCollection.fromLocation);
		textFieldreqDepartment.setText(TSOCollection.requestingDept);
		textFieldtype.setText(TSOCollection.serviceType);
		textFielduse.setText(TSOCollection.circuitUse);
		textFieldcmo.setText(TSOCollection.cmo);
		textFieldcmoDsn.setText(TSOCollection.cmoDsn);
		textFieldcmoComm.setText(TSOCollection.cmoComm);
	}

	/**
	 * Set circuit check boxes based on collected information
	 */
	private static void checkBoxes_Circuit() {
		chckbxAndrewsCmo.setSelected(TSOCollection.andrewsCmo);
		chckbxAndrewsEndpoint.setSelected(TSOCollection.endPoint);
	}

	/**
	 * Set tso text fields based on collected information
	 */
	private static void textFields_Tso() {
		textFieldTsoNum.setText(TSOCollection.tsoNum);
		textFieldTsoSuff.setText(TSOCollection.tsoSuffix);
		textFieldaction.setText(TSOCollection.action);
		textFieldFullCcsdTso.setText(TSOCollection.fullCcsd);
		textFieldsvcDate.setText(DateUtils.dateConvert(TSOCollection.svcDate, false, true));
		textFieldreportDate.setText(DateUtils.dateConvert(TSOCollection.reportDate, false, true));
		textFieldMrc.setText(String.valueOf(TSOCollection.mrc));
		textFieldNrc.setText(String.valueOf(TSOCollection.nrc));
	}

	/**
	 * Set tso check boxes based on collected information
	 */
	private static void checkBoxes_Tso() {
		chckbxcrr.setSelected(TSOCollection.crr);

	}

}
