package taytom258.window.core;

import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window;


public class WindowCore extends Window{

	public static void collect(){
				
		//General Tab
		
		Collection.tsoSubject = getTextFieldTsoSubject().getText().trim();
		Collection.fullCcsd = getTextFieldFullCcsd().getText().trim();
		Collection.svcDate = getTextFieldServiceDate().getText().trim();
		
		if (getRdbtnOther().isSelected()){
			Collection.cmo = getTextFieldOtherCmoName().getText().trim();
			Collection.otherCmoDsn = getTextFieldOtherCmoDsn().getText().trim();
			Collection.otherCmoComm = getTextFieldOtherCmoComm().getText().trim();
		}else if(getRdbtnAndrews().isSelected()){
			Collection.cmo = Strings.ANDREWS_CMO;
		}else{
			LogHelper.severe("Unable to set CMO");
		}
		
		Collection.logical = getChckbxLogical().isSelected();
		Collection.comReportRequired = getChckbxComReport().isSelected();
		Collection.ancs = getChckbxAncs().isSelected();
		
		Collection.extraComments = getTextAreaExtraComments().getText().trim();
		
		//Start Tab
		
		if(getTextFieldStartDataRate().getText() != null && !(getTextFieldStartDataRate().getText().equals(""))){
			Collection.start = true;
			
			Collection.startIs1539 = getChckbxStart1539().isSelected();
			Collection.startHasSams = getChckbxStartSams().isSelected();
			Collection.startIsAnalog = getChckbxStartAnalog().isSelected();
			Collection.startIsPassthrough = getChckbxStartPassthrough().isSelected();
			Collection.startChf = getTglbtnStartCHF().isSelected();
			
			Collection.dataRate = getTextFieldStartDataRate().getText().trim();
			Collection.serviceAvailible = getTextFieldStartServiceAvailability().getText().trim();
			Collection.tsp = getComboBoxStartTsp().getSelectedItem().toString().trim();
			Collection.startTsrNumber = getTextFieldStartTsr().getText().trim();
			Collection.startReportDate = getTextFieldStartReportDate().getText().trim();
			
			Collection.startTsoStatement = getTextAreaStartTsoStatement().getText().trim().replaceAll("\\s+", " ");
		}else{
			Collection.start = false;
		}
		
		//Change Tab
		
		if(getTextFieldChangeTsrNumber() != null && !(getTextFieldChangeTsrNumber().getText().equals(""))){
			Collection.change = true;
			
			Collection.changeIs1539 = getChckbxChange1539().isSelected();
			Collection.changeHasSams = getChckbxChangeSams().isSelected();
			Collection.changeIsAnalog = getChckbxChangeAnalog().isSelected();
			Collection.changeIsPassthrough = getChckbxChangePassthrough().isSelected();
			Collection.changeChf = getTglbtnChangeCHF().isSelected();
			
			Collection.changeTsrNumber = getTextFieldChangeTsrNumber().getText().trim();
			Collection.changeReportDate = getTextFieldChangeReportDate().getText().trim();
			
			Collection.changeTsoStatement = getTextAreaChangeTsoStatement().getText().trim();
		}else{
			Collection.change = false;
		}
		
		//Disco Tab
		
		if(getTextFieldDiscoTsrNumber() != null && !(getTextFieldDiscoTsrNumber().getText().equals(""))){
			Collection.disco = true;
			
			Collection.discoIs1539 = getChckbxDisco1539().isSelected();
			Collection.discoHasSams = getChckbxDiscoSams().isSelected();
			Collection.discoIsAnalog = getChckbxDiscoAnalog().isSelected();
			Collection.discoIsPassthrough = getChckbxDiscoPassthrough().isSelected();
			Collection.discoChf = getTglBtnDiscoCHF().isSelected();
			
			Collection.discoTsrNumber = getTextFieldDiscoTsrNumber().getText().trim();
			Collection.discoReportDate = getTextFieldDiscoReportDate().getText().trim();
			
			Collection.discoTsoStatement = getTextAreaDiscoTsoStatement().getText().trim();		
		}else{
			Collection.disco = false;
		}
		
		
		
		
		
		
		
		
	}
	
}
