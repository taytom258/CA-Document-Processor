package taytom258.show;

import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window;

public class ShowFacit {

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
	}
	
	private static void ccsd(){
		Window.getTextFieldShowCcsd().setText(Collection.fullCcsd.substring(4).toUpperCase());
		Window.getTextFieldShowCcsd_1().setText(Collection.fullCcsd.substring(4).toUpperCase());
	}
	
	private static void tsp(){
		Window.getTextFieldShowTsp().setText(Collection.tsp);
	}
	
	private static void purpose(){
		Window.getTextFieldShowPurpose().setText(Collection.fullCcsd.substring(1, 3).toUpperCase());
	}
	
	private static void rate(){
		if(Collection.start){
			Window.getTextFieldShowRate().setText(Collection.dataRate.toUpperCase());
		}
	}
	
	private static void availible(){
		if(Collection.start){
			Window.getTextFieldShowServiceAvailable().setText(Collection.serviceAvailible.toUpperCase());
		}
	}
	
	private static void fullCcsd(){
		Window.getTextFieldShowFacitFullCcsd().setText(Collection.fullCcsd.toUpperCase());
	}
	
	private static void statement(){
		if(Collection.start){
			Window.getTextAreaShowTsoStatement().setText(Collection.startTsoStatement);
			Window.getTextAreaShowTsoStatement_1().setText(Collection.startTsoStatement);
		}else if(Collection.change){
			Window.getTextAreaShowTsoStatement().setText(Collection.changeTsoStatement);
			Window.getTextAreaShowTsoStatement_1().setText(Collection.changeTsoStatement);
		}else if(Collection.disco){
			Window.getTextAreaShowTsoStatement().setText(Collection.discoTsoStatement);
			Window.getTextAreaShowTsoStatement_1().setText(Collection.discoTsoStatement);
		}else{
			Window.getTextAreaShowTsoStatement().setText("");
		}
	}
	
	private static void action(){
		if(Collection.start){
			Window.getTextFieldShowAction_1().setText(Strings.START);
		}else if(Collection.change){
			Window.getTextFieldShowAction_1().setText(Strings.CHANGE);
		}else if(Collection.disco){
			Window.getTextFieldShowAction_1().setText(Strings.DISCO);
		}
	}
	
	private static void tsoNum(){
		Window.getTextFieldShowTsoNumber().setText(Collection.tsoSubject.toUpperCase().substring(4));
	}
	
	private static void tsrNum(){
		if(Collection.start){
			Window.getTextFieldShowTsrNumber().setText(Collection.startTsrNumber);
		}else if(Collection.change){
			Window.getTextFieldShowTsrNumber().setText(Collection.changeTsrNumber);
		}else if(Collection.disco){
			Window.getTextFieldShowTsrNumber().setText(Collection.discoTsrNumber);
		}else{
			Window.getTextFieldShowTsrNumber().setText("");
		}
	}
	
	private static void svcDate(){
		String format = Collection.svcDate.toUpperCase();
		String[] month = format.split("\\d");
		String[] number = format.split("\\D");
		
		String date = number[0] + " " + month[2] + " " + number[3];
		
		Window.getTextFieldShowServiceDate_1().setText(date);
	}
	
	private static void tsoSubject(){
		Window.getTextFieldShowTsoSubject().setText(Collection.tsoSubject.toUpperCase());
	}
	
	private static void reportDate(){
		if(Collection.start){
			Window.getTextFieldShowReportDate().setText(Collection.startReportDate);
		}else if(Collection.change){
			Window.getTextFieldShowReportDate().setText(Collection.changeReportDate);
		}else if(Collection.disco){
			Window.getTextFieldShowReportDate().setText(Collection.discoReportDate);
		}else{
			Window.getTextFieldShowReportDate().setText("");
		}
	}
	
	private static void comReport(){
		if(Collection.comReportRequired){
			Window.getChckbxCompletionReportRequired().setSelected(true);
		}else{
			Window.getChckbxCompletionReportRequired().setSelected(false);
		}
	}
}
