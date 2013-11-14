package taytom258.config;

import java.io.File;
import java.io.IOException;

import taytom258.core.DirHandler;
import taytom258.core.WriteHandler;
import taytom258.lib.Reference;

public class ConfigHandler {

	public static void write(String text){
		if(Config.configUser){
			WriteHandler.fileWriter(text, Reference.USER_HOME);
		}else{
			WriteHandler.fileWriter(text, Config.configPath);
		}
	}
	
	public static boolean createRootDir(){
		String temp = System.getProperty("user.home").concat("\\"+Reference.AUTHOR);
		File dir = new File(temp);
		
		if(!dir.exists()){
			try {
				DirHandler.createUserDir("taytom258");
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
		
	}
}
