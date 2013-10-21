package taytom258.core.util;

import taytom258.config.Config;
import taytom258.window.Window;

public class Debug {

	public static void init(){
		if(Config.debug){
			Window.getTextFieldTsoSubject().setText("TSO T125847859");
			Window.getTextFieldFullCcsd().setText("bhdvf258");
			Window.getTextFieldServiceDate().setText("11dec2013");
			Window.getTextAreaExtraComments().setText("This is a comment.");
		}
	}
}
