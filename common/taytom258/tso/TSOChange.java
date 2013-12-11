package taytom258.tso;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import taytom258.window.Window2;

public class TSOChange {
	
	public static void buildPanel(){
		
		JPanel action = Window2.getPanel_Action();
		
		JPanel panel_Change = new JPanel();
		panel_Change.setLayout(null);
		action.add(panel_Change, "panel_Change");
		
		JCheckBox checkBox_Sams = new JCheckBox("Sams?");
		checkBox_Sams.setBounds(8, 33, 65, 24);
		panel_Change.add(checkBox_Sams);
		
		JCheckBox checkBox_Analog = new JCheckBox("Analog?");
		checkBox_Analog.setBounds(8, 61, 71, 24);
		panel_Change.add(checkBox_Analog);
		
		JCheckBox checkBox_Pass = new JCheckBox("Passthrough?");
		checkBox_Pass.setBounds(8, 89, 105, 24);
		panel_Change.add(checkBox_Pass);
		
		JCheckBox checkBox_1539 = new JCheckBox("1539 Circuit?");
		checkBox_1539.setBounds(8, 8, 112, 24);
		panel_Change.add(checkBox_1539);
		
		JToggleButton toggleButton_Chf = new JToggleButton("CHF Creation Active");
		toggleButton_Chf.setBounds(140, 47, 145, 26);
		panel_Change.add(toggleButton_Chf);
		
		JLabel label_Tsr = new JLabel("TSR Number");
		label_Tsr.setBounds(12, 222, 71, 16);
		panel_Change.add(label_Tsr);
		
		JLabel label_RptDate = new JLabel("Report Date");
		label_RptDate.setBounds(12, 252, 67, 16);
		panel_Change.add(label_RptDate);
		
		final JTextField textField_Tsr = new JTextField();
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
					Window2.popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		final JTextField textField_RptDate = new JTextField();
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
					Window2.popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		JPanel panel_TsoState = new JPanel();
		panel_TsoState.setLayout(null);
		panel_TsoState.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "TSO Statement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TsoState.setBounds(8, 282, 283, 113);
		panel_Change.add(panel_TsoState);
		
		final JTextArea textArea = new JTextArea();
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
					Window2.popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});	
	}
}
