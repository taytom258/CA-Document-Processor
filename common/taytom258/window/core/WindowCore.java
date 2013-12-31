package taytom258.window.core;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.DefaultEditorKit;

import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.tso.TSOChange;
import taytom258.tso.TSODisco;
import taytom258.tso.TSOStart;
import taytom258.window.Window;


public class WindowCore extends Window{

	public static void enableCmoFields(boolean boo){
		if (boo){
			textField_CmoName.setEnabled(true);
			textField_CmoName.setBackground(Color.WHITE);
			textField_Dsn.setEnabled(true);
			textField_Dsn.setBackground(Color.WHITE);
			textField_Comm.setEnabled(true);
			textField_Comm.setBackground(Color.WHITE);
		}else{
			textField_CmoName.setEnabled(false);
			textField_CmoName.setBackground(Color.LIGHT_GRAY);
			textField_CmoName.setText("");
			textField_Dsn.setEnabled(false);
			textField_Dsn.setBackground(Color.LIGHT_GRAY);
			textField_Dsn.setText("");
			textField_Comm.setEnabled(false);
			textField_Comm.setBackground(Color.LIGHT_GRAY);
			textField_Comm.setText("");
		}
	}
	
	public static JPopupMenu createPopup() {
        JMenuItem menuItem = null;
        JPopupMenu popup = new JPopupMenu();

        menuItem = new JMenuItem(new DefaultEditorKit.CutAction());
        menuItem.setText("Cut");
        popup.add(menuItem);

        menuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
        menuItem.setText("Copy");
        popup.add(menuItem);

        menuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
        menuItem.setText("Paste");
        popup.add(menuItem);
        
		new DefaultEditorKit();
		menuItem = new JMenuItem(DefaultEditorKit.selectAllAction);
		menuItem.setText("Select All");
		popup.add(menuItem);

        return popup;
	}
	
	public static void cardSwap(String name){
		CardLayout cl = (CardLayout) getPanel_Action().getLayout();
		cl.show(getPanel_Action(), name);
	}
	
	public static class collect extends Window{
		
		public static void init(){
			genCollect();
			TSOStart.collect();
			TSOChange.collect();
			TSODisco.collect();
			
		}
		
		private static void reset(){
			String[] str1 = {"Action", "Start", "Change", "Disco"};
			cardSwap("Blank");
			Collection.start = false;
			Collection.change = false;
			Collection.disco = false;
			
			for(String element : str1){
				if(getTabbedPane().indexOfTab(element) != -1){
					getTabbedPane().setTitleAt(getTabbedPane().indexOfTab(element), "Action");
					getTabbedPane().setEnabledAt(getTabbedPane().indexOfTab("Action"), false);
					getTabbedPane().validate();
				}
			}
		}
		
		private static void genCollect(){
			Collection.tsoSubject = textField_TsoSubject.getText().toUpperCase().trim();
			Collection.fullCcsd = textField_FullCcsd.getText().toUpperCase().trim();
			Collection.svcDate = textField_ServiceDate.getText().toUpperCase().trim();
			
			if(getRadioButton_Other().isSelected()){
				Collection.cmo = textField_CmoName.getText().toUpperCase().trim();
				Collection.otherCmoDsn = textField_Dsn.getText().toUpperCase().trim();
				Collection.otherCmoComm = textField_Comm.getText().toUpperCase().trim();
			}else if(getRadioButton_Andrews().isSelected()){
				Collection.cmo = Strings.ANDREWS_CMO;
			}
			
			Collection.ancs = getCheckBox_Ancs().isSelected();
			Collection.logical = getCheckBox_Logical().isSelected();
			Collection.comReportRequired = getCheckBox_Crr().isSelected();
			
			Collection.extraComments = getTextArea_Comments().getText().toUpperCase().trim();
			
		}

		public static void getAction(){
			
			String[] str1 = textField_TsoSubject.getText().toUpperCase().split("-");
			if(str1.length > 1){
				String str2 = str1[1];
			
				reset();
				getTabbedPane().setEnabledAt(getTabbedPane().indexOfTab("Action"), true);
				if(str2.equals("01")){
					getTabbedPane().setTitleAt(getTabbedPane().indexOfTab("Action"), "Start");
					cardSwap("panel_Start");
					Collection.start = true;
				}else if(str2.equals("99")){
					getTabbedPane().setTitleAt(getTabbedPane().indexOfTab("Action"), "Disco");
					cardSwap("panel_Disco");
					Collection.disco = true;
				}else{
					getTabbedPane().setTitleAt(getTabbedPane().indexOfTab("Action"), "Change");
					cardSwap("panel_Change");
					Collection.change = true;
				}
					getTabbedPane().validate();
			}else{
				reset();
			}
		}
		
	}
}
