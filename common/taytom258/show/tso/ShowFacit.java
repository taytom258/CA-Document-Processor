package taytom258.show.tso;

import taytom258.core.log.LogHelper;
import taytom258.core.util.DateUtils;
import taytom258.lib.Collection;
import taytom258.windows.main.MainWindow;
import taytom258.windows.main.tsoTab.TSO_Facit_Panel;

/**
 * Class for display of information on TSO Facit tab
 * @author taytom258
 *
 */
public class ShowFacit extends TSO_Facit_Panel {

	private static final long serialVersionUID = 2294372967106952036L;

	public static void display() {
//		ccsd();
//		tsp();
//		purpose();
//		rate();
//		availible();
//		fullCcsd();
//		action();
//		tsoNum();
//		tsrNum();
//		svcDate();
//		tsoSubject();
//		reportDate();
//		comReport();
//		amending();
//		isTrunk();
		LogHelper.info("TSO (Facit) Tab Complete");
	}

	private void textFields(){
		textField_FullCcsd.setText(Collection.fullCcsd.substring(4).toUpperCase());
		
	}
	private static void ccsd() {
		MainWindow.getTextField_FacitCcsd().setText(Collection.fullCcsd.substring(4).toUpperCase());
	}

	private static void tsp() {
		MainWindow.getTextField_FacitTsp().setText(Collection.tsp);
	}

	private static void purpose() {
		MainWindow.getTextField_FacitPurpose().setText(
				Collection.fullCcsd.substring(1, 3).toUpperCase());
	}

	private static void rate() {
		MainWindow.getTextField_FacitRate().setText(
				Collection.dataRate.toUpperCase());
	}

	private static void availible() {
		MainWindow.getTextField_FacitSvcAvailable().setText(
				Collection.serviceAvail.toUpperCase());
	}

	private static void fullCcsd() {
		MainWindow.getTextField_FacitFullCcsd().setText(
				Collection.fullCcsd.toUpperCase());
	}

	private static void action() {
		MainWindow.getTextField_FacitAction().setText(Collection.tsoAction);
	}

	private static void tsoNum() {
		MainWindow.getTextField_FacitTsoNum().setText(Collection.tsoNum);
	}

	private static void tsrNum() {
		MainWindow.getTextField_FacitTsrNum().setText(Collection.tsrNum);
	}

	private static void svcDate() {
		if (!Collection.svcDate.equals("")) {
			MainWindow.getTextField_FacitSvcDate().setText(
					DateUtils.dateConvert(Collection.svcDate, true, false));
		}
	}

	private static void tsoSubject() {
		MainWindow.getTextField_FacitTsoSubject().setText(
				Collection.tsoSubject.toUpperCase());
	}

	private static void reportDate() {
		MainWindow.getTextField_FacitReportDate().setText(
				DateUtils.dateConvert(Collection.reportDate, true, false));
	}

	private static void comReport() {
		if (Collection.crr) {
			MainWindow.getChckbx_FacitCrr().setSelected(true);
		} else {
			MainWindow.getChckbx_FacitCrr().setSelected(false);
		}
	}

	private static void amending() {
		if (Collection.tsoAction.equals("AMEND")) {
			String temp = Collection.tsoSuffix.substring(0, 2);
			if (temp.equals("01")) {
				MainWindow.getTextField_FacitAmending().setText("START");
			} else if (temp.equals("99")) {
				MainWindow.getTextField_FacitAmending().setText("DISCO");
			} else {
				MainWindow.getTextField_FacitAmending().setText("CHANGE");
			}
		} else {
			MainWindow.getTextField_FacitAmending().setText("N/A");
		}
	}

	private static void isTrunk() {
		if (Collection.trunkId.trim().length() == 6
				|| !Collection.trunkId.equals("N/A")) {
			MainWindow.getChckbx_FacitIsTrunk().setSelected(true);
		} else {
			MainWindow.getChckbx_FacitIsTrunk().setSelected(false);
		}
		MainWindow.getTextField_FacitTrunkID().setText(Collection.trunkId);
	}
}
