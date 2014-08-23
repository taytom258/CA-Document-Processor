package taytom258.threads;

import taytom258.Start;
import taytom258.config.Config;
import taytom258.core.util.LogHelper;
import taytom258.window.Console;

public class ThreadC extends Thread{
	public static Thread t = null;
	
	@Override
	public void run(){
	    synchronized(this){
	    	/*
        	 * Post-Initialization
        	 */
        	LogHelper.info("Post-Loading...");
        	if(Config.debug){
        		Console.getFrame().setVisible(true);
        	}
	    }
	}
}
