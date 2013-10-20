package taytom258.window.core;

import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window;


public class WindowCore extends Window{

	public static void collect(){
		
//		if (window.getTextFieldStartDataRate().getText() != null){
//			Collection.start = true;
//		}
		
		
		//General Tab
		
		Collection.tsoSubject = getTextFieldTsoSubject().getText();
		Collection.fullCcsd = getTextFieldFullCcsd().getText();
		Collection.svcDate = getTextFieldServiceDate().getText();
		
		if (getRdbtnOther().isSelected()){
			Collection.cmo = getTextFieldOtherCmoName().getText();
			Collection.otherCmoDsn = getTextFieldOtherCmoDsn().getText();
			Collection.otherCmoComm = getTextFieldOtherCmoComm().getText();
		}else if(getRdbtnAndrews().isSelected()){
			Collection.cmo = Strings.ANDREWS_CMO;
		}else{
			LogHelper.severe("Unable to set CMO");
		}
		
		Collection.logical = getChckbxLogical().isSelected();
		Collection.comReportRequired = getChckbxComReport().isSelected();
		
		Collection.extraComments = getTextAreaExtraComments().getText();
		
		//Start Tab
		
		
		
		
		
		
	}
	
}
