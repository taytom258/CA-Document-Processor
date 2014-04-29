package taytom258.show.tso;

import taytom258.core.util.Conversion;
import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.window.Window;

public class ShowDatabase extends Window{

	public static void show(){
		circuit();
		tso();
		LogHelper.info("TSO (Database) Tab Complete");
	}
	
	private static void circuit(){
		textFields_Circuit();
		checkBoxes_Circuit();
	}
	
	private static void tso(){
		textFields_Tso();
		checkBoxes_Tso();	
	}
	
	private static void textFields_Circuit(){
		getTextField_DB_Ci_FullCcsd().setText(Collection.fullCcsd);
		getTextField_DB_Cir_trunkId().setText(Collection.trunkId);
		getTextField_DB_Cir_fullTsp().setText(Collection.fullTsp);
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
	
	private static void checkBoxes_Circuit(){
		getChckbxAndrewsCmo().setSelected(Collection.andrewsCmo);
		getChckbxAndrewsEndpoint().setSelected(Collection.endPoint);
	}
	
	private static void textFields_Tso(){
		getTextField_DB_TSO_TsoNum().setText(Collection.tsoNum);
		getTextField_DB_TSO_TsoSuff().setText(Collection.tsoSuffix);
		getTextField_DB_TSO_action().setText(Collection.tsoAction);
		getTextField_DB_TSO_fullCcsd().setText(Collection.fullCcsd);
		getTextField_DB_TSO_svcDate().setText(Conversion.dateConvert(Collection.svcDate, false, true));
		getTextField_DB_TSO_reportDate().setText(Conversion.dateConvert(Collection.reportDate, false, true));
	}
	
	private static void checkBoxes_Tso(){
		getChckbx_DB_TSO_crr().setSelected(Collection.crr);
	}
	
	
}
