package taytom258;

import taytom258.lib.Reference;
import taytom258.lib.util.LogHelper;
import taytom258.window.Window;



public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 *Initialization
		 */
		
		LogHelper.init();
		LogHelper.debug("Initialize Log Helper");
		LogHelper.info(Reference.APPLICATION_NAME+" : "+Reference.APPLICATION_VERSION+"."+Reference.BUILD_NUMBER);
		
		
		/*
		 * Load
		 */
		
		Window.appear();
		
		
	}
}
