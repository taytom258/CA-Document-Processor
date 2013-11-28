package taytom258;

import taytom258.config.Config;
import taytom258.core.security.FingerprintCheck;
import taytom258.core.util.Debug;
import taytom258.core.util.LogHelper;
import taytom258.lib.Reference;
import taytom258.window.Window;



public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Testing.regEx();
		
		
		/*
		 *Initialization
		 */
		
		LogHelper.init();
		LogHelper.debug("Initialize Log Helper");
		LogHelper.info(Reference.APPLICATION_NAME+" : "+Reference.APPLICATION_VERSION+"."+Reference.BUILD_NUMBER);
		
		if (FingerprintCheck.selfIntegrityChecking()){
			LogHelper.debug("Jar Verifed");
		}else{
			LogHelper.debug("Jar Not Verified");
		}
		
		/*
		 * Load
		 */
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Window.appear();
            }
		});
	}
}
