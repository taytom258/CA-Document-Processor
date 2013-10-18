package taytom258.window.core;

import taytom258.core.util.Conversion;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window;

public class WindowCore extends Window{

	public static void collect(){
		
		//General Tab
		Collection.subject = textField.getText().replace("/", "");
		Collection.ccsd = textField_5.getText().trim();
		
		if (!textField_1.getText().equals(Strings.DATE_FORMAT)){
			String date = textField_1.getText().replaceAll("\\W", "");
			String[] split1 = date.split("\\D");
			String[] split2 = date.toUpperCase().split("\\d");
			
			Collection.svcDate_Day = split1[0];
			Collection.svcDate_Month = split2[2];
			Collection.svcDate_MonthInt = Conversion.Month_StringtoInt(split2[2]);
			Collection.svcDate_Year = split1[3];
		}
		
		if (rdbtnAndrews.isSelected()){
			Collection.cmo = Strings.ANDREWS_CMO;
		}else{
			Collection.cmo = textField_2.getText();
			Collection.dsn = textDSN.getText();
		}
		
		if (chckbxLogical.isSelected()){
			Collection.logical = true;
		}else{
			Collection.logical = false;
		}
		
		if (chckbxComReport.isSelected()){
			Collection.comReport = true;
		}else{
			Collection.comReport = false;
		}
		
		if (textArea.getText() != null){
			Collection.comments = textArea.getText();
		}
		
		//Collect Other Tab(s)
		
		if (Window.tglbtnCHF.isSelected()){
			Collection.start = true;
			Collection.chfState = true;
			
			if (Window.chckbxSams.isSelected()){
				Collection.sams = true;
			}else{
				Collection.sams = false;
			}
			
			if (Window.chckbxAnalog.isSelected()){
				Collection.analog = true;
			}else{
				Collection.analog = false;
			}
			
			if (Window.chckbxPassthrough.isSelected()){
				Collection.passthrough = true;
			}else{
				Collection.passthrough = false;
			}
			
		}else{
			Collection.start = false;
			Collection.change = false;
			Collection.disco = false;
		}
	}
	
}
