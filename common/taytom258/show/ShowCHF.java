package taytom258.show;

import java.io.File;
import java.io.IOException;

import taytom258.config.Config;
import taytom258.core.DirHandler;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window;

public class ShowCHF extends Window{

	
	
	
	public static void show(){
		tsoName();
		rootFolder();
	}
	
	private static void tsoName(){
		String text = Collection.tsoSubject.toUpperCase();
		text = text.replace("/", "");
		
		getTextField_ChfTsoName().setText(text);
	}
	
	private static void rootFolder(){
		String text1, text2;
		File folderExist;
		text1 = Collection.fullCcsd.toUpperCase().replace(" ", "").substring(0, 4);
		text2 = Collection.fullCcsd.toUpperCase().replace(" ", "").substring(4);
		
		String folder = text2+" ("+text1+")";
		
		getTextField_ChfChfLink().setText(folder);
		
		if(Config.useChf){
			folderExist = new File(Config.CHF_PATH+"/"+folder);
		}else{
			folderExist = new File(Config.CHF_TEST+"/"+folder);
		}
		
		if(folderExist.exists()){
			getRdbtn_ChfRoot().setSelected(true);
		}else{
			try {
				DirHandler.createDir(folder, Config.CHF_PATH);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static void folderCurrent(String path){
		
		String text = null;
		
		for(String element : Strings.FOLDERS){
			File folder = new File(path + "/" + element);
			if(folder.exists()){
				text = text.concat(element);
				text = text.concat("\n");
			}
		}
	}
}
