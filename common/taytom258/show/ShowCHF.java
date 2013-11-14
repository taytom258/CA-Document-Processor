package taytom258.show;

import java.awt.Color;
import java.io.File;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import taytom258.config.Config;
import taytom258.lib.Collection;
import taytom258.window.Window;

public class ShowCHF {

	
	
	
	public static void show(){
		tsoName();
		rootFolder();
	}
	
	private static void tsoName(){
		String text = Collection.tsoSubject.toUpperCase();
		text = text.replace("/", "");
		
		Window.getTextFieldShowTsoName().setText(text);
	}
	
	private static void rootFolder(){
		String text1, text2;
		File folderExist;
		text1 = Collection.fullCcsd.toUpperCase().replace(" ", "").substring(0, 4);
		text2 = Collection.fullCcsd.toUpperCase().replace(" ", "").substring(4);
		
		String folder = text2+" ("+text1+")";
		
		Window.getTextFieldShowRootFolder().setText(folder);
		
		Window.getPanelRootFolder().setVisible(true);
		Window.getPanelRootFolder().setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), folder, TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		if(Config.useChf){
			folderExist = new File(Config.CHF_PATH+"/"+folder);
		}else{
			folderExist = new File(Config.CHF_TEST+"/"+folder);
		}
		
		if(folderExist.exists()){
			Window.getChckbxShowFolderExists().setSelected(true);
		}
		
	}
}
