package taytom258.show;

import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window;


public class ShowTracker {

	public static void show(){
		action();
		fullCcsd();
		chfLink();
		cmo();
		serviceDate();
		comment();
	}
	
	private static void action(){
		if(Collection.start){
			Window.getTextFieldShowAction().setText(Strings.START);
		}else if(Collection.change){
			Window.getTextFieldShowAction().setText(Strings.CHANGE);
		}else if(Collection.disco){
			Window.getTextFieldShowAction().setText(Strings.DISCO);
		}
	}
	
	private static void fullCcsd(){
		Window.getTextFieldShowTrackerFullCcsd().setText(Collection.fullCcsd.toUpperCase());
	}
	
	private static void chfLink(){
		
	}
	
	private static void cmo(){
		if(Window.getRdbtnOther().isSelected()){
			Window.getTextFieldShowCmo().setText(Collection.cmo);
			Window.getTextFieldShowCmoDsn().setText(Collection.otherCmoDsn);
			Window.getTextFieldShowCmoComm().setText(Collection.otherCmoComm);
		}else if(Window.getRdbtnAndrews().isSelected()){
			Window.getTextFieldShowCmo().setText(Collection.cmo);
			Window.getTextFieldShowCmoDsn().setText("");
			Window.getTextFieldShowCmoComm().setText("");
		}
	}
	
	private static void serviceDate(){
		String format = Collection.svcDate.toUpperCase();
		String[] month = format.split("\\d");
		String[] number = format.split("\\D");
		
		String date = number[0] + " " + month[2] + " " + number[3];
		
		Window.getTextFieldShowServiceDate().setText(date);
	}
	
	private static void comment(){
	
		String location = null;
		String complete = null;
		String extra = null;
		String tso = null;
		
		if(!Collection.logical && !Collection.ancs){
			location = Strings.PHYSICAL_FACILITY;
		}else if(!Collection.logical && Collection.ancs){
			location = Strings.PHYSICAL_ANCS;
		}else if(Collection.logical && !Collection.ancs){
			location = Strings.LOGICAL_FACILITY;
		}else if(Collection.logical && Collection.ancs){
			location = Strings.LOGICAL_ANCS;
		}else{
			LogHelper.severe("Unable to set comment format");
		}
		
		if(!Collection.comReportRequired){
			complete = Strings.COMPLETE_NOT;
		}else if(Collection.comReportRequired){
			complete = Strings.COMPLETE;
		}else{
			LogHelper.severe("Unable to set comment format");
		}
		
		if(Collection.start){
			tso = Collection.startTsoStatement;
		}else if(Collection.change){
			tso = Collection.changeTsoStatement;
		}else if(Collection.disco){
			tso = Collection.discoTsoStatement;
		}else{
			tso = "";
		}
		
		if(Collection.extraComments != null && !(Collection.extraComments.equals(""))){
			extra = Collection.extraComments;
		}else{
			extra = "";
		}
		
		location = location.trim();
		complete = complete.trim();
		tso = tso.trim();
		
		
		Window.getTextAreaShowComment().setText(tso.toUpperCase() + " " + location + " " + complete + " " + extra);
	}
}
