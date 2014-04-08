package taytom258.show.tso;

import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.window.Window;

//TODO Make fields match access database (v3)
//TODO Finish database preview

public class ShowDatabase extends Window{

	public static void show(){
		circuit();
		LogHelper.info("TSO (Database) Tab Complete");
	}
	
	private static void circuit(){
		textFields();
		checkBoxes();
	}
	
	private static void textFields(){
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
	
	private static void checkBoxes(){
		getChckbxAndrewsCmo().setSelected(Collection.andrewsCmo);
		getChckbxAndrewsEndpoint().setSelected(Collection.endPoint);
	}
	
	
}
