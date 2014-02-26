package taytom258.show;

import java.io.File;
import java.io.IOException;

import taytom258.config.Config;
import taytom258.core.DirHandler;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window;

public class ShowCHF extends Window{

	private static String currentText = "";
	private static String creatingText = "";
	
	
	public static void show(){
		tsoName();
		rootFolder();
		if(Config.useChf && Config.chfPath != ""){
			folder(Config.chfPath);
		}else if(!Config.useChf && Config.chfTest != ""){
			folder(Config.chfTest);
		}
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
			folderExist = new File(Config.CHF_PATH+"\\"+folder);
		}else{
			folderExist = new File(Config.CHF_TEST+"\\"+folder);
		}
		
		if(folderExist.exists()){
			getRdbtn_ChfRoot().setSelected(true);
		}else{
			try {
				DirHandler.createDir(folder, "bin");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static void folder(String path){
		
		for(String element : Strings.FOLDERS){
			File folder = new File(path + "\\" + element);
			if(folder.exists()){
				currentText = currentText.concat(element);
				currentText = currentText.concat("\n");
				getTextPane_ChfCurrent().setText(currentText);
			}else if(!folder.exists() && check(element)){
				creatingText = creatingText.concat(element);
				creatingText = creatingText.concat("\n");
				getTextPane_ChfCreating().setText(creatingText);
	}
	
	private static boolean check(String folder){
		if(Collection.start){
			if(Collection.startHasSams && ){return true;}
			else if(Collection.startIsAnalog && folder.equals(Strings.FOLDERS[2])){return true;}
			else if(Collection.cmo.equals(Strings.ANDREWS_CMO) && folder.equals(Strings.FOLDERS[5])){return true;}
			else if(Collection.startIsPassthrough && folder.equals(Strings.FOLDERS[8])){return true;}
		}
		return false;
	}
}
