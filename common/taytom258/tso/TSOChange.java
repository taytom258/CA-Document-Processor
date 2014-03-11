package taytom258.tso;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import taytom258.lib.Collection;
import taytom258.window.Window;

public class TSOChange {
	
	private static JPanel panel_Change = new JPanel();
	private static JCheckBox checkBox_Sams = new JCheckBox("Sams?");
	private static JCheckBox checkBox_Analog = new JCheckBox("Analog?");
	private static JCheckBox checkBox_Pass = new JCheckBox("Passthrough?");
	private static JCheckBox checkBox_1539 = new JCheckBox("1539 Circuit?");
	private static JLabel label_Tsr = new JLabel("TSR Number");
	private static JLabel label_RptDate = new JLabel("Report Date");
	private static final JTextField textField_Tsr = new JTextField();
	private static final JTextField textField_RptDate = new JTextField();
	private static JPanel panel_TsoState = new JPanel();
	private static final JTextArea textArea = new JTextArea();
	
	
	
	public static void buildPanel(){
		
		JPanel action = Window.getPanel_Action();
		
		panel_Change.setLayout(null);
		action.add(panel_Change, "panel_Change");
		
		checkBox_Sams.setBounds(8, 33, 65, 24);
		panel_Change.add(checkBox_Sams);
		
		checkBox_Analog.setBounds(8, 61, 71, 24);
		panel_Change.add(checkBox_Analog);
		
		checkBox_Pass.setBounds(8, 89, 105, 24);
		panel_Change.add(checkBox_Pass);
		
		checkBox_1539.setBounds(8, 8, 112, 24);
		panel_Change.add(checkBox_1539);
		
		label_Tsr.setBounds(12, 222, 71, 16);
		panel_Change.add(label_Tsr);
		
		label_RptDate.setBounds(12, 252, 67, 16);
		panel_Change.add(label_RptDate);
		
		textField_Tsr.setColumns(10);
		textField_Tsr.setBounds(171, 220, 114, 20);
		panel_Change.add(textField_Tsr);
		textField_Tsr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == 1){
					e.getComponent().requestFocus();
					textField_Tsr.selectAll();
				}else if(e.isPopupTrigger()){
					e.getComponent().requestFocus();
					Window.popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		textField_RptDate.setColumns(10);
		textField_RptDate.setBounds(171, 250, 114, 20);
		panel_Change.add(textField_RptDate);
		textField_RptDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == 1){
					e.getComponent().requestFocus();
					textField_RptDate.selectAll();
				}else if(e.isPopupTrigger()){
					e.getComponent().requestFocus();
					Window.popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		panel_TsoState.setLayout(null);
		panel_TsoState.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "TSO Statement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TsoState.setBounds(8, 282, 283, 113);
		panel_Change.add(panel_TsoState);
		
		textArea.setWrapStyleWord(true);
		textArea.setToolTipText("Enter Extra Comments Here");
		textArea.setLineWrap(true);
		textArea.setBounds(12, 23, 259, 78);
		panel_TsoState.add(textArea);
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == 1){
					e.getComponent().requestFocus();
					textArea.selectAll();
				}else if(e.isPopupTrigger()){
					e.getComponent().requestFocus();
					Window.popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});	
	}
	
	public static void collect(){
		
		Collection.changeIs1539 = checkBox_1539.isSelected();
		Collection.changeHasSams = checkBox_Sams.isSelected();
		Collection.changeIsAnalog = checkBox_Analog.isSelected();
		Collection.changeIsPassthrough = checkBox_Pass.isSelected();
		
		Collection.changeTsrNumber = textField_Tsr.getText().toUpperCase();
		Collection.changeReportDate = textField_RptDate.getText().toUpperCase();
		Collection.changeTsoStatement = textArea.getText().toUpperCase();
	}
}
