package taytom258.show;

import taytom258.config.Config;
import taytom258.core.util.Conversion;
import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window;


public class ShowTracker extends Window{

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
			getTextField_TrackerAction().setText(Strings.START);
		}else if(Collection.change){
			getTextField_TrackerAction().setText(Strings.CHANGE);
		}else if(Collection.disco){
			getTextField_TrackerAction().setText(Strings.DISCO);
		}
	}
	
	private static void fullCcsd(){
		getTextField_TrackerFullCcsd().setText(Collection.fullCcsd);
	}
	
	private static void chfLink(){
		if(Config.useChf && Collection.disco){
			getTextField_TrackerChfLink().setText(Config.chfPath + "\\" + Collection.chfRootFolder + " " + Strings.DISCO_PEND);
		}else if(!Config.useChf && Collection.disco){
			getTextField_TrackerChfLink().setText(Config.chfTest + "\\" + Collection.chfRootFolder + " " + Strings.DISCO_PEND);
		}else if(Config.useChf){
			getTextField_TrackerChfLink().setText(Config.chfPath + "\\" + Collection.chfRootFolder);
		}else{
			getTextField_TrackerChfLink().setText(Config.chfTest + "\\" + Collection.chfRootFolder);
		}
	}
	
	private static void cmo(){
		if(getRadioButton_Other().isSelected()){
			getTextField_TrackerCmo().setText(Collection.cmo);
			getTextField_TrackerCmoDsn().setText(Collection.otherCmoDsn);
			getTextField_TrackerCmoComm().setText(Collection.otherCmoComm);
		}else if(getRadioButton_Andrews().isSelected()){
			getTextField_TrackerCmo().setText(Strings.ANDREWS_CMO);
			getTextField_TrackerCmoDsn().setText("");
			getTextField_TrackerCmoComm().setText("");
		}
	}
	
	private static void serviceDate(){
		if(!getTextField_TrackerSvcDate().getText().equals("")){
			getTextField_TrackerSvcDate().setText(Conversion.dateConvert(Collection.svcDate));
		}
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
		
		
		getTextPane_TrackerComment().setText(tso.toUpperCase() + " " + location + " " + complete + " " + extra);
	}
}
