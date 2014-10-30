package taytom258.display.tso;

import taytom258.core.log.LogHelper;
import taytom258.core.util.DateUtils;
import taytom258.lib.Collection;
import taytom258.windows.main.tsoTab.TSO_Facit_Panel;

/**
 * Class for display of information on TSO Facit tab
 * @author taytom258
 *
 */
public class TSOFacit extends TSO_Facit_Panel {

	private static final long serialVersionUID = 2294372967106952036L;

	/**
	 * Display information in all text fields and check boxes based on collected information
	 */
	public static void display() {
		textFields();
		checkBoxes();
		LogHelper.info("TSO (Facit) Tab Complete");
	}

	/**
	 * Display information in text fields based on collected information.
	 */
	private static void textFields(){
		textField_Ccsd.setText(Collection.fullCcsd.substring(4));
		textField_Tsp.setText(Collection.tsp);
		textField_Purpose.setText(Collection.fullCcsd.substring(1, 3));
		textField_Rate.setText(Collection.dataRate);
		textField_SvcAvailable.setText(Collection.serviceAvail);
		textField_FullCcsd.setText(Collection.fullCcsd.toUpperCase());
		textField_Action.setText(Collection.tsoAction);
		textField_TsoNum.setText(Collection.tsoNum);
		textField_TsrNum.setText(Collection.tsrNum);
		textField_TsoSubject.setText(Collection.tsoSubject.toUpperCase());
		textField_ReportDate.setText(DateUtils.dateConvert(Collection.reportDate, true, false));
		textField_SvcDate.setText(DateUtils.dateConvert(Collection.svcDate, true, false));
		textField_Amending.setText(TSOCommon.facitAmending(Collection.tsoSuffix, Collection.tsoAction));
		textField_TrunkID.setText(Collection.trunkId);
	}
	
	/**
	 * Display information in check boxes based on collected information.
	 */
	private static void checkBoxes(){
		chckbx_IsTrunk.setSelected(TSOCommon.facitIsTrunk(Collection.trunkId));
		chckbx_Crr.setSelected(Collection.crr);
	}	
}
