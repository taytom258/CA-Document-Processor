package taytom258.show.tso;

import taytom258.core.log.LogHelper;
import taytom258.core.util.DateUtils;
import taytom258.lib.Collection;
import taytom258.windows.main.MainWindow;
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
		getTextField_DB_Cir_tsp().setText(Collection.tsp);
		getTextField_DB_Cir_security().setText(Collection.security);
		getTextField_DB_Cir_rate().setText(Collection.dataRate);
		getTextField_DB_Cir_flow().setText(Collection.trafficFlow);
		getTextField_DB_Cir_avail().setText(Collection.serviceAvail);
		getTextField_DB_Cir_signal().setText(Collection.signaling);
		getTextField_DB_Cir_qcc().setText(Collection.qcc);
		getTextField_DB_Cir_to().setText(Collection.toLocation);
		getTextField_DB_Cir_from().setText(Collection.fromLocation);
		getTextField_DB_Cir_reqDepartment().setText(Collection.requestingDept);
		getTextField_DB_Cir_type().setText(Collection.serviceType);
		getTextField_DB_Cir_use().setText(Collection.circuitUse);
		getTextField_DB_Cir_cmo().setText(Collection.cmo);
		getTextField_DB_Cir_cmoDsn().setText(Collection.cmoDsn);
		getTextField_DB_Cir_cmoComm().setText(Collection.cmoComm);
	}

	/**
	 * Set circuit check boxes based on collected information
	 */
	private static void checkBoxes_Circuit() {
		getChckbxAndrewsCmo().setSelected(Collection.andrewsCmo);
		getChckbxAndrewsEndpoint().setSelected(Collection.endPoint);
	}

	/**
	 * Set tso text fields based on collected information
	 */
	private static void textFields_Tso() {
		getTextField_DB_TSO_TsoNum().setText(Collection.tsoNum);
		getTextField_DB_TSO_TsoSuff().setText(Collection.tsoSuffix);
		getTextField_DB_TSO_action().setText(Collection.tsoAction);
		getTextField_DB_TSO_fullCcsd().setText(Collection.fullCcsd);
		getTextField_DB_TSO_svcDate().setText(
				DateUtils.dateConvert(Collection.svcDate, false, true));
		getTextField_DB_TSO_reportDate().setText(
				DateUtils.dateConvert(Collection.reportDate, false, true));
		getTextField_DB_TSO_Mrc().setText(String.valueOf(Collection.mrc));
		getTextField_DB_TSO_Nrc().setText(String.valueOf(Collection.nrc));
	}

	/**
	 * Set tso check boxes based on collected information
	 */
	private static void checkBoxes_Tso() {
		getChckbx_DB_TSO_crr().setSelected(Collection.crr);

	}

}
