package taytom258.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import taytom258.core.DirHandler;
import taytom258.core.util.LogHelper;
import taytom258.lib.Reference;
import taytom258.lib.Strings;

public class ConfigHandler {
	
	private static Properties prop = new Properties();
	private static OutputStream output = null;
	private static InputStream input = null;
	
	public static void init(){
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
	}
	
	private static void creatDefaultConfig(String path){
		try {
			output = new FileOutputStream(path+"\\"+Strings.CONFIG_NAME);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
