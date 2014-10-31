package taytom258.display.tso;

import taytom258.core.log.LogHelper;
import taytom258.core.util.DateUtils;
import taytom258.core.util.parsers.collections.TSOCollection;
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
		textField_Ccsd.setText(TSOCollection.fullCcsd.substring(4));
		textField_Tsp.setText(TSOCollection.tsp);
		textField_Purpose.setText(TSOCollection.fullCcsd.substring(1, 3));
		textField_Rate.setText(TSOCollection.dataRate);
		textField_SvcAvailable.setText(TSOCollection.serviceAvail);
		textField_FullCcsd.setText(TSOCollection.fullCcsd.toUpperCase());
		textField_Action.setText(TSOCollection.action);
		textField_TsoNum.setText(TSOCollection.tsoNum);
		textField_TsrNum.setText(TSOCollection.tsrNum);
		textField_TsoSubject.setText(TSOCollection.tsoSubject.toUpperCase());
		textField_ReportDate.setText(DateUtils.dateConvert(TSOCollection.reportDate, true, false));
		textField_SvcDate.setText(DateUtils.dateConvert(TSOCollection.svcDate, true, false));
		textField_Amending.setText(TSOCommon.facitAmending(TSOCollection.tsoSuffix, TSOCollection.action));
		textField_TrunkID.setText(TSOCollection.trunkId);
	}
	
	/**
	 * Display information in check boxes based on collected information.
	 */
	private static void checkBoxes(){
		chckbx_IsTrunk.setSelected(TSOCommon.facitIsTrunk(TSOCollection.trunkId));
		chckbx_Crr.setSelected(TSOCollection.crr);
	}	
}
