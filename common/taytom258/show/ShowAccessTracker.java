package taytom258.show;

import taytom258.config.Config;
import taytom258.core.util.Conversion;
import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window;

//TODO Make fields match access database (v3)

public class ShowAccessTracker extends Window{

	public static void show(){
		action();
		fullCcsd();
		chfLink();
		cmo();
		serviceDate();
		comment();
		LogHelper.info("Access Tracker Tab Complete");
	}
	
	private static void action(){
		if(Collection.start){
			getTextField_TrackerAction().setText(Strings.START);
		}else if(Collection.change){
			getTextField_TrackerAction().setText(Strings.CHANGE);
		}else if(Collection.disco){
			getTextField_TrackerAction().setText(Strings.DISCO);
		}else if(Collection.amend){
			Window.getTextField_FacitAction().setText(Strings.AMEND);
		}else if(Collection.cancel){
			Window.getTextField_FacitAction().setText(Strings.CANCEL);
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
		if(!Collection.svcDate.equals("")){
			getTextField_TrackerSvcDate().setText(Conversion.dateConvert(Collection.svcDate));
		}
	}
	
	private static void comment(){
	
		String location = null;
		String complete = null;
		String extra = null;
		
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
		
		if(Collection.extraComments != null && !(Collection.extraComments.equals(""))){
			extra = Collection.extraComments;
		}else{
			extra = "";
		}
		
		location = location.trim();
		complete = complete.trim();
		
		
		getTextPane_TrackerComment().setText(location + " " + complete + " " + extra);
	}
}
