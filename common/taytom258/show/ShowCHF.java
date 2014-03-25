package taytom258.show;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import taytom258.config.Config;
import taytom258.core.DirHandler;
import taytom258.core.WriteHandler;
import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window;

public class ShowCHF extends Window {

	private static String currentText, creatingText;
	private static ArrayList<String> create = new ArrayList<String>();

	public static void show() {
		tsoName();
		IOOperations(rootFolder());
		LogHelper.info("CHF Tab Complete");
	}

	private static void IOOperations(File path) {
		folder(path.toString());
		createTso(path.toString());
		create1539(path.toString());
	}

	private static void tsoName() {
		String text = Collection.tsoSubject.toUpperCase();
		text = text.replace("/", "");

		getTextField_ChfTsoName().setText(text + ".txt");
	}

	private static File rootFolder() {
		File folderExist = null;
		File only = null;

		if (Collection.disco) {
			getTextField_ChfChfLink().setText(
					Collection.chfRootFolder + " " + Strings.DISCO_PEND);
		} else {
			getTextField_ChfChfLink().setText(Collection.chfRootFolder);
		}

		if (Config.useChf) {
			folderExist = new File(Config.chfPath + "\\"
					+ Collection.chfRootFolder);
			only = new File(Config.chfPath + "\\"
					+ Strings.ONLY_1539 + "\\"
					+ Collection.chfRootFolder);
		} else {
			folderExist = new File(Config.chfTest + "\\"
					+ Collection.chfRootFolder);
			only = new File(Config.chfTest + "\\"
					+ Strings.ONLY_1539 + "\\"
					+ Collection.chfRootFolder);
		}

		File discoPend = new File(folderExist.toString() + " "
				+ Strings.DISCO_PEND);
		
		if (Collection.disco) {
			folderExist.renameTo(discoPend);
			folderExist = discoPend;
		}else if(Collection.startIs1539 || Collection.changeIs1539 || Collection.amendIs1539){
			folderExist = only;
		}

		if ((folderExist.exists() || discoPend.exists())
				&& (folderExist.isDirectory() || discoPend.isDirectory())) {
			getRdbtn_ChfRoot().setSelected(true);
		} else {
			try {
				if (Config.useChf && !only.equals(folderExist)) {
					if(DirHandler.createDir(Collection.chfRootFolder,
							Config.chfPath)){
					LogHelper.io("Created Root in: " + Config.chfPath);}
					getRdbtn_ChfRootCreated().setSelected(true);
				} else if (!only.equals(folderExist)) {
					if(DirHandler.createDir(Collection.chfRootFolder,
							Config.chfTest)){
					LogHelper.io("Created Root in: " + Config.chfTest);}
					getRdbtn_ChfRootCreated().setSelected(true);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return folderExist;

	}

	private static void folder(String path) {

		create.clear();

		currentText = "";
		getTextPane_ChfCurrent().setText("");
		creatingText = "";
		getTextPane_ChfCreating().setText("");

		for (String element : Strings.FOLDERS) {
			File folder = new File(path + "\\" + element);
			if (folder.exists() && folder.isDirectory()) {
				currentText = currentText.concat(element);
				currentText = currentText.concat("\n");
				getTextPane_ChfCurrent().setText(currentText);
			} else if (!folder.exists() && check(element)) {
				creatingText = creatingText.concat(element);
				creatingText = creatingText.concat("\n");
				getTextPane_ChfCreating().setText(creatingText);
				create.add(element);
			}
		}
		createFolders(path);
	}

	private static boolean check(String folder) {
		if (Collection.start) {
			if (Collection.startHasSams && folder.equals(Strings.FOLDERS[4])) {
				return true;
			} else if (Collection.startIsAnalog
					&& folder.equals(Strings.FOLDERS[2])) {
				return true;
			} else if (Collection.cmo.equals(Strings.ANDREWS_CMO)
					&& folder.equals(Strings.FOLDERS[5])) {
				return true;
			} else if (!Collection.startIsPassthrough
					&& folder.equals(Strings.FOLDERS[8])) {
				return true;
			}
		}
		if (Collection.change) {
			if (Collection.changeHasSams && folder.equals(Strings.FOLDERS[4])) {
				return true;
			} else if (Collection.changeIsAnalog
					&& folder.equals(Strings.FOLDERS[2])) {
				return true;
			} else if (Collection.cmo.equals(Strings.ANDREWS_CMO)
					&& folder.equals(Strings.FOLDERS[5])) {
				return true;
			} else if (!Collection.changeIsPassthrough
					&& folder.equals(Strings.FOLDERS[8])) {
				return true;
			}
		}
		if (Collection.disco) {
			if (Collection.discoHasSams && folder.equals(Strings.FOLDERS[4])) {
				return true;
			} else if (Collection.discoIsAnalog
					&& folder.equals(Strings.FOLDERS[2])) {
				return true;
			} else if (Collection.cmo.equals(Strings.ANDREWS_CMO)
					&& folder.equals(Strings.FOLDERS[5])) {
				return true;
			} else if (!Collection.discoIsPassthrough
					&& folder.equals(Strings.FOLDERS[8])) {
				return true;
			}
		}
		if (Collection.amend) {
			if (Collection.amendHasSams && folder.equals(Strings.FOLDERS[4])) {
				return true;
			} else if (Collection.amendIsAnalog
					&& folder.equals(Strings.FOLDERS[2])) {
				return true;
			} else if (Collection.cmo.equals(Strings.ANDREWS_CMO)
					&& folder.equals(Strings.FOLDERS[5])) {
				return true;
			} else if (!Collection.amendIsPassthrough
					&& folder.equals(Strings.FOLDERS[8])) {
				return true;
			}
		}
		if (folder.equals(Strings.FOLDERS[0])) {
			return true;
		} else if (folder.equals(Strings.FOLDERS[1])) {
			return true;
		} else if (folder.equals(Strings.FOLDERS[3])) {
			return true;
		} else if (folder.equals(Strings.FOLDERS[6])) {
			return true;
		} else if (folder.equals(Strings.FOLDERS[7])) {
			return true;
		}
		return false;
	}

	private static void createFolders(String path) {

		for (String s : create) {
			try {
				if(DirHandler.createDir(s, path)){
					LogHelper.io("Created " + s + " in: " + path);}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void createTso(String path) {

		String text = Collection.tsoSubject.toUpperCase();
		text = text.replace("/", "");
		text = text + ".txt";

		File file = new File(path + "\\" + Strings.FOLDERS[6] + "\\" + text);

		if (!file.exists()) {
			if (WriteHandler.fileWriter(Collection.tsoText, file)) {
				LogHelper.io("Created file " + text + " in " + path + "\\"
						+ Strings.FOLDERS[6]);
			}
		} else {
			LogHelper.info("TSO File Already Exists");
		}
	}
	
	private static void create1539(String path){
		
		
		if(Collection.startIs1539 || Collection.changeIs1539 || Collection.amendIs1539){
			try{
				if (Config.useChf) {
					File folder = new File(Config.chfPath + "\\" + Strings.ONLY_1539);
					File root = new File(path);
					File newRoot = new File(folder.toString() + "\\" + Collection.chfRootFolder);
					
					if (!folder.exists()){
						if(DirHandler.createDir(Strings.ONLY_1539, Config.chfPath)){
							LogHelper.io("Created 1539 only root folder in: " + Config.chfPath);}
					}
					if(!root.equals(newRoot)){
						FileUtils.copyDirectory(root, newRoot, false);
						FileUtils.deleteQuietly(root);
						LogHelper.io("Moved folder from: " + root + " to: " + newRoot);
					}
					
				} else {
					File folder = new File(Config.chfTest + "\\" + Strings.ONLY_1539);
					File root = new File(path);
					File newRoot = new File(folder.toString() + "\\" + Collection.chfRootFolder);
					
					if (!folder.exists()){
						if(DirHandler.createDir(Strings.ONLY_1539, Config.chfTest)){
							LogHelper.io("Created 1539 only root folder in: " + Config.chfTest);}
					}
					if(!root.equals(newRoot)){
						FileUtils.copyDirectory(root, newRoot, false);
						FileUtils.deleteQuietly(root);
						LogHelper.io("Moved folder from: " + root + " to: " + newRoot);
					}
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
