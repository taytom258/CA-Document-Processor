package taytom258;

import taytom258.config.Config;
import taytom258.config.ConfigHandler;
import taytom258.core.security.Check;
import taytom258.core.util.LogHelper;
import taytom258.core.util.db.Database;
import taytom258.lib.Reference;
import taytom258.window.Console;
import taytom258.window.Window;



public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		ThreadA a = new ThreadA();
		ThreadB b = new ThreadB();
	    a.start();
	    
	    synchronized(a){
	        try{
	            a.wait();
	            b.start();
	            synchronized(b){
	            	b.wait();
	            	/*
	            	 * Post-Initialization
	            	 */
	            	LogHelper.info("Post-Loading...");
	            	Database.init();
	            	if(Config.debug){
	            		Console.getFrame().setVisible(true);
	            	}
	            }
	        }catch(InterruptedException e){
	            e.printStackTrace();
	            LogHelper.severe(e.getMessage());
	        }
	    }
	    
	}
}
	
class ThreadA extends Thread{
	@Override
	public void run(){
	    synchronized(this){
	    	/*
			 *Initialization
			 */
	    	Console.init();
			LogHelper.init();
			LogHelper.debug("Console initialized");
			LogHelper.info(Reference.APPLICATION_NAME+": "+Reference.APPLICATION_VERSION+"."+Reference.BUILD_NUMBER);
			LogHelper.info("Initializing...");
			ConfigHandler.init();
			Check.call();
			notify();
	    }
	}
}

class ThreadB extends Thread{
	@Override
	public void run(){
	    synchronized(this){
	    	/*
    		 * Load
    		 */
            LogHelper.info("Loading...");
    		Window.appear();
//    		Testing.init();
    		notify();
	    }
	}
}