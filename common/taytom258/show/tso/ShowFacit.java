package taytom258.show.tso;

import taytom258.core.util.Conversion;
import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.window.Window;

public class ShowFacit extends Window{

	public static void show(){
		ccsd();
		tsp();
		purpose();
		rate();
		availible();
		fullCcsd();
		action();
		tsoNum();
		tsrNum();
		svcDate();
		tsoSubject();
		reportDate();
		comReport();
		amending();
		isTrunk();
		LogHelper.info("TSO (Facit) Tab Complete");
	}
	
	private static void ccsd(){
		Window.getTextField_FacitCcsd().setText(Collection.fullCcsd.substring(4).toUpperCase());
	}
	
	private static void tsp(){
		Window.getTextField_FacitTsp().setText(Collection.tsp);
	}
	
	private static void purpose(){
		Window.getTextField_FacitPurpose().setText(Collection.fullCcsd.substring(1, 3).toUpperCase());
	}
	
	private static void rate(){
		Window.getTextField_FacitRate().setText(Collection.dataRate.toUpperCase());
	}
	
	private static void availible(){
		Window.getTextField_FacitSvcAvailable().setText(Collection.serviceAvail.toUpperCase());
	}
	
	private static void fullCcsd(){
		Window.getTextField_FacitFullCcsd().setText(Collection.fullCcsd.toUpperCase());
	}
	
	private static void action(){
		Window.getTextField_FacitAction().setText(Collection.tsoAction);
	}
	
	private static void tsoNum(){
		Window.getTextField_FacitTsoNum().setText(Collection.tsoNum);
	}
	
	private static void tsrNum(){
		Window.getTextField_FacitTsrNum().setText(Collection.tsrNum);
	}
	
	private static void svcDate(){
		if(!Collection.svcDate.equals("")){
			Window.getTextField_FacitSvcDate().setText(Conversion.dateConvert(Collection.svcDate, true, false));
		}
	}
	
	private static void tsoSubject(){
		Window.getTextField_FacitTsoSubject().setText(Collection.tsoSubject.toUpperCase());
	}
	
	private static void reportDate(){
		Window.getTextField_FacitReportDate().setText(Conversion.dateConvert(Collection.reportDate, true, false));
	}
	
	private static void comReport(){
		if(Collection.crr){
			Window.getChckbx_FacitCrr().setSelected(true);
		}else{
			Window.getChckbx_FacitCrr().setSelected(false);
		}
	}
	
	private static void amending(){
		if(Collection.tsoAction.equals("AMEND")){
			String temp = Collection.tsoSuffix.substring(0, 2);
			if(temp.equals("01")){
				Window.getTextField_FacitAmending().setText("START");
			}else if(temp.equals("99")){
				Window.getTextField_FacitAmending().setText("DISCO");
			}else{
				Window.getTextField_FacitAmending().setText("CHANGE");
			}
		}else{
			Window.getTextField_FacitAmending().setText("N/A");
		}
	}
	
	private static void isTrunk(){
		if(Collection.trunkId.trim().length() == 6 || !Collection.trunkId.equals("N/A")){
			Window.getChckbx_FacitIsTrunk().setSelected(true);
		}else{
			Window.getChckbx_FacitIsTrunk().setSelected(false);
		}
		Window.getTextField_FacitTrunkID().setText(Collection.trunkId);
	}
}
