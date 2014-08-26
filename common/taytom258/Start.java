package taytom258;

import taytom258.config.ConfigHandler;
import taytom258.core.security.Check;
import taytom258.core.util.LogHelper;
import taytom258.core.util.db.CircuitStatus;
import taytom258.lib.Reference;
import taytom258.threads.ThreadC;
import taytom258.window.Console;
import taytom258.window.Splash;
import taytom258.window.Window;



public class Start {

	public static ThreadA a = new ThreadA();
	public static ThreadB b = new ThreadB();
	public static ThreadC c = new ThreadC();
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		
		try{
	    a.start();
	    synchronized(a){
	    	a.wait();
	        b.start();
	        synchronized(b){
	        	b.wait();
	        	c.start();
	        }
	    }
	        }catch(InterruptedException e){
	            e.printStackTrace();
	            LogHelper.severe(e.getMessage());
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
			Splash.progressBar.setIndeterminate(true);
			CircuitStatus.circuitStatusRepair();
			Splash.progressBar.setIndeterminate(false);
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