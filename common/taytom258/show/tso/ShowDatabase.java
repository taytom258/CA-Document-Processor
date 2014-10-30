package taytom258.show.tso;

import taytom258.core.log.LogHelper;
import taytom258.core.util.DateUtils;
import taytom258.lib.Collection;
import taytom258.windows.main.tsoTab.TSO_DB_TabPane;

/**
 * Class for display of information on DB tab
 * @author taytom258
 *
 */
public class ShowDatabase extends TSO_DB_TabPane {

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
		textFieldFullCcsdCircuit.setText(Collection.fullCcsd);
		textFieldtrunkId.setText(Collection.trunkId);
		textFieldfullTsp.setText(Collection.fullTsp);
		textFieldtsp.setText(Collection.tsp);
		textFieldsecurity.setText(Collection.security);
		textFieldrate.setText(Collection.dataRate);
		textFieldflow.setText(Collection.trafficFlow);
		textFieldavail.setText(Collection.serviceAvail);
		textFieldsignal.setText(Collection.signaling);
		textFieldqcc.setText(Collection.qcc);
		textFieldto.setText(Collection.toLocation);
		textFieldfrom.setText(Collection.fromLocation);
		textFieldreqDepartment.setText(Collection.requestingDept);
		textFieldtype.setText(Collection.serviceType);
		textFielduse.setText(Collection.circuitUse);
		textFieldcmo.setText(Collection.cmo);
		textFieldcmoDsn.setText(Collection.cmoDsn);
		textFieldcmoComm.setText(Collection.cmoComm);
	}

	/**
	 * Set circuit check boxes based on collected information
	 */
	private static void checkBoxes_Circuit() {
		chckbxAndrewsCmo.setSelected(Collection.andrewsCmo);
		chckbxAndrewsEndpoint.setSelected(Collection.endPoint);
	}

	/**
	 * Set tso text fields based on collected information
	 */
	private static void textFields_Tso() {
		textFieldTsoNum.setText(Collection.tsoNum);
		textFieldTsoSuff.setText(Collection.tsoSuffix);
		textFieldaction.setText(Collection.tsoAction);
		textFieldFullCcsdTso.setText(Collection.fullCcsd);
		textFieldsvcDate.setText(DateUtils.dateConvert(Collection.svcDate, false, true));
		textFieldreportDate.setText(DateUtils.dateConvert(Collection.reportDate, false, true));
		textFieldMrc.setText(String.valueOf(Collection.mrc));
		textFieldNrc.setText(String.valueOf(Collection.nrc));
	}

	/**
	 * Set tso check boxes based on collected information
	 */
	private static void checkBoxes_Tso() {
		chckbxcrr.setSelected(Collection.crr);

	}

}
