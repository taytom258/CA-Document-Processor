package taytom258.window.core;

import java.awt.Color;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.DefaultEditorKit;

import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window2;


public class WindowCore extends Window2{

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

        return popup;
	}
	
	public class collect extends Window2{
		
		public collect(){
			genCollect();
			
		}
		public void genCollect(){
			Collection.tsoSubject = textField_TsoSubject.getText().toUpperCase();
			Collection.fullCcsd = textField_FullCcsd.getText().toUpperCase();
			Collection.svcDate = textField_ServiceDate.getText().toUpperCase();
			
			if(getRadioButton_Other().isSelected()){
				Collection.cmo = textField_CmoName.getText().toUpperCase();
				Collection.otherCmoDsn = textField_Dsn.getText().toUpperCase();
				Collection.otherCmoComm = textField_Comm.getText().toUpperCase();
			}else if(getRadioButton_Andrews().isSelected()){
				Collection.cmo = Strings.ANDREWS_CMO;
			}
			
			Collection.ancs = getCheckBox_Ancs().isSelected();
			Collection.logical = getCheckBox_Logical().isSelected();
			Collection.comReportRequired = getCheckBox_Crr().isSelected();
			
			Collection.extraComments = getTextArea_Comments().getText().toUpperCase();
			
			if(Collection.tsoSubject.c
		}
	}
}
