package taytom258.config;

import java.io.File;
import java.io.IOException;

import taytom258.core.DirHandler;
import taytom258.core.util.LogHelper;
import taytom258.lib.Reference;

public class ConfigHandler {
	
	public static String createRootDir(){
		String temp = System.getProperty("user.home").concat("\\"+Reference.AUTHOR);
		File dir = new File(temp);
		
		if(!dir.exists()){
			try {
				DirHandler.createUserDir(Reference.AUTHOR);
			} catch (IOException e) {
				e.printStackTrace();
				LogHelper.warning("Unable to create root directory");
			}
			LogHelper.debug("Root directory created");
		}else{
			LogHelper.debug("Root directory exists");
		}
		return temp;
	}
}
