package taytom258.show;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import taytom258.config.Config;
import taytom258.core.DirHandler;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window;

public class ShowCHF extends Window{

	private static String currentText, creatingText;
	private static ArrayList<String> create = new ArrayList<String>();
	
	
	public static void show(){
		tsoName();
		list(rootFolder());
	}
	
	private static void list(File path){
		folder(path.toString());
	}
	
	private static void tsoName(){
		String text = Collection.tsoSubject.toUpperCase();
		text = text.replace("/", "");
		text = text.replace(" ", "");
		
		getTextField_ChfTsoName().setText(text + ".txt");
	}
	
	private static File rootFolder(){
		File folderExist = null;
		
		if(Collection.disco){
			getTextField_ChfChfLink().setText(Collection.chfRootFolder + " " + Strings.DISCO_PEND);
		}else{
			getTextField_ChfChfLink().setText(Collection.chfRootFolder);
		}
		
		
		if(Config.useChf){
			folderExist = new File(Config.chfPath + "\\" + Collection.chfRootFolder);
		}else{
			folderExist = new File(Config.chfTest + "\\" + Collection.chfRootFolder);
		}
		
		File discoPend = new File(folderExist.toString() + " " + Strings.DISCO_PEND);
		
		if((folderExist.exists() || discoPend.exists()) && (folderExist.isDirectory() || discoPend.isDirectory())){
			getRdbtn_ChfRoot().setSelected(true);
			if(Collection.disco){
				folderExist.renameTo(discoPend);
				folderExist = discoPend;
			}
		}else{
			try {
				if(Config.useChf){
					DirHandler.createDir(Collection.chfRootFolder, Config.chfPath);
				}else{
					DirHandler.createDir(Collection.chfRootFolder, Config.chfTest);
				}
				getRdbtn_ChfRootCreated().setSelected(true);
				if(Collection.disco){
					folderExist.renameTo(discoPend);
					folderExist = discoPend;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return folderExist;
		
	}
	
	private static void folder(String path){
		
		create.clear();
		
		currentText = "";
		getTextPane_ChfCurrent().setText("");
		creatingText = "";
		getTextPane_ChfCreating().setText("");
		
		for(String element : Strings.FOLDERS){
			File folder = new File(path + "\\" + element);
			if(folder.exists() && folder.isDirectory()){
				currentText = currentText.concat(element);
				currentText = currentText.concat("\n");
				getTextPane_ChfCurrent().setText(currentText);
			}else if(!folder.exists() && check(element)){
				creatingText = creatingText.concat(element);
				creatingText = creatingText.concat("\n");
				getTextPane_ChfCreating().setText(creatingText);
				create.add(element);
			}
		}
		createFolders(path);
	}
	
	private static boolean check(String folder){
		if(Collection.start){
			if(Collection.startHasSams && folder.equals(Strings.FOLDERS[4])){return true;}
			else if(Collection.startIsAnalog && folder.equals(Strings.FOLDERS[2])){return true;}
			else if(Collection.cmo.equals(Strings.ANDREWS_CMO) && folder.equals(Strings.FOLDERS[5])){return true;}
			else if(!Collection.startIsPassthrough && folder.equals(Strings.FOLDERS[8])){return true;}
		}
		if(Collection.change){
			if(Collection.changeHasSams && folder.equals(Strings.FOLDERS[4])){return true;}
			else if(Collection.changeIsAnalog && folder.equals(Strings.FOLDERS[2])){return true;}
			else if(Collection.cmo.equals(Strings.ANDREWS_CMO) && folder.equals(Strings.FOLDERS[5])){return true;}
			else if(!Collection.changeIsPassthrough && folder.equals(Strings.FOLDERS[8])){return true;}
		}
		if(Collection.disco){
			if(Collection.discoHasSams && folder.equals(Strings.FOLDERS[4])){return true;}
			else if(Collection.discoIsAnalog && folder.equals(Strings.FOLDERS[2])){return true;}
			else if(Collection.cmo.equals(Strings.ANDREWS_CMO) && folder.equals(Strings.FOLDERS[5])){return true;}
			else if(!Collection.discoIsPassthrough && folder.equals(Strings.FOLDERS[8])){return true;}
		}
		if(folder.equals(Strings.FOLDERS[0])){return true;}
		else if(folder.equals(Strings.FOLDERS[1])){return true;}
		else if(folder.equals(Strings.FOLDERS[3])){return true;}
		else if(folder.equals(Strings.FOLDERS[6])){return true;}
		else if(folder.equals(Strings.FOLDERS[7])){return true;}
		return false;
	}
	
	private static void createFolders(String path){
		
		if(Config.useChf)
			for(String s : create){
				try {
					DirHandler.createDir(s, path);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}else{
			for(String s : create){
				try {
					DirHandler.createDir(s, path);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
