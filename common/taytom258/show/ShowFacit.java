package taytom258.show;

import taytom258.core.util.Conversion;
import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window;

public class ShowFacit extends Window{

	public static void show(){
		ccsd();
		tsp();
		purpose();
		rate();
		availible();
		fullCcsd();
		statement();
		action();
		tsoNum();
		tsrNum();
		svcDate();
		tsoSubject();
		reportDate();
		comReport();
		LogHelper.info("Facit Tab Complete");
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
		if(Collection.start || Collection.amend){
			Window.getTextField_FacitRate().setText(Collection.dataRate.toUpperCase());
		}
	}
	
	private static void availible(){
		if(Collection.start || Collection.amend){
			Window.getTextField_FacitSvcAvailable().setText(Collection.serviceAvailible.toUpperCase());
		}
	}
	
	private static void fullCcsd(){
		Window.getTextField_FacitFullCcsd().setText(Collection.fullCcsd.toUpperCase());
	}
	
	private static void statement(){
		if(Collection.start && !Collection.startTsoStatement.equals("")){
			Window.getTextArea_FacitTsoState().setText(Collection.startTsoStatement);
		}else if(Collection.change && !Collection.changeTsoStatement.equals("")){
			Window.getTextArea_FacitTsoState().setText(Collection.changeTsoStatement);
		}else if(Collection.disco && !Collection.discoTsoStatement.equals("")){
			Window.getTextArea_FacitTsoState().setText(Collection.discoTsoStatement);
		}else if(Collection.amend && !Collection.amendTsoStatement.equals("")){
			Window.getTextArea_FacitTsoState().setText(Collection.amendTsoStatement);
		}else if(Collection.cancel && !Collection.cancelTsoStatement.equals("")){
			Window.getTextArea_FacitTsoState().setText(Collection.cancelTsoStatement);
		}else{
			Window.getTextArea_FacitTsoState().setText("");
		}
	}
	
	private static void action(){
		if(Collection.start){
			Window.getTextField_FacitAction().setText(Strings.START);
		}else if(Collection.change){
			Window.getTextField_FacitAction().setText(Strings.CHANGE);
		}else if(Collection.disco){
			Window.getTextField_FacitAction().setText(Strings.DISCO);
		}else if(Collection.amend){
			Window.getTextField_FacitAction().setText(Strings.AMEND);
		}else if(Collection.cancel){
			Window.getTextField_FacitAction().setText(Strings.CANCEL);
		}
	}
	
	private static void tsoNum(){
		Window.getTextField_FacitTsoNum().setText(Collection.tsoSubject.toUpperCase().substring(4));
	}
	
	private static void tsrNum(){
		if(Collection.start){
			Window.getTextField_FacitTsrNum().setText(Collection.startTsrNumber);
		}else if(Collection.change){
			Window.getTextField_FacitTsrNum().setText(Collection.changeTsrNumber);
		}else if(Collection.disco){
			Window.getTextField_FacitTsrNum().setText(Collection.discoTsrNumber);
		}else if(Collection.amend){
			Window.getTextField_FacitTsrNum().setText(Collection.amendTsrNumber);
		}else if(Collection.cancel){
			Window.getTextField_FacitTsrNum().setText(Collection.cancelTsrNumber);
		}else{
			Window.getTextField_FacitTsrNum().setText("");
		}
	}
	
	private static void svcDate(){
		if(!Collection.svcDate.equals("")){
			Window.getTextField_FacitSvcDate().setText(Conversion.dateConvert(Collection.svcDate));
		}
	}
	
	private static void tsoSubject(){
		Window.getTextField_FacitTsoSubject().setText(Collection.tsoSubject.toUpperCase());
	}
	
	private static void reportDate(){
		if(Collection.start && !Collection.startReportDate.equals("")){
			Window.getTextField_FacitReportDate().setText(Conversion.dateConvert(Collection.startReportDate));
		}else if(Collection.change && !Collection.changeReportDate.equals("")){
			Window.getTextField_FacitReportDate().setText(Conversion.dateConvert(Collection.changeReportDate));
		}else if(Collection.disco && !Collection.discoReportDate.equals("")){
			Window.getTextField_FacitReportDate().setText(Conversion.dateConvert(Collection.discoReportDate));
		}else if(Collection.amend && !Collection.amendReportDate.equals("")){
			Window.getTextField_FacitReportDate().setText(Conversion.dateConvert(Collection.amendReportDate));
		}else if(Collection.cancel && !Collection.cancelReportDate.equals("")){
			Window.getTextField_FacitReportDate().setText(Conversion.dateConvert(Collection.cancelReportDate));
		}else{
			Window.getTextField_FacitReportDate().setText("");
			}
	}
	
	private static void comReport(){
		if(Collection.comReportRequired){
			Window.getChckbx_FacitCrr().setSelected(true);
		}else{
			Window.getChckbx_FacitCrr().setSelected(false);
		}
	}
}
