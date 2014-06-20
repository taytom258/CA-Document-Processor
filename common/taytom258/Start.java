package taytom258;

import taytom258.config.Config;
import taytom258.config.ConfigHandler;
import taytom258.core.security.Check;
import taytom258.core.util.LogHelper;
import taytom258.core.util.db.Database;
import taytom258.lib.Reference;
import taytom258.window.Console;
import taytom258.window.Splash;
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
	    	Splash.appear();
	    	Console.init();
	    	Splash.incrementBar();
			LogHelper.init();
			Splash.incrementBar();
			LogHelper.debug("Console initialized");
			LogHelper.info(Reference.APPLICATION_NAME+": "+Reference.APPLICATION_VERSION+"."+Reference.BUILD_NUMBER);
			Splash.incrementBar();
			LogHelper.info("Initializing...");
			ConfigHandler.init();
			Splash.incrementBar();
			Check.call();
			Splash.incrementBar();
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
            Splash.incrementBar();
    		Window.appear();
    		Splash.dialog.dispose();
//    		Testing.init();
    		notify();
	    }
	}
}