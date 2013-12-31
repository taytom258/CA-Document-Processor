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

import taytom258.lib.Collection;
import taytom258.window.Window;

public class TSODisco {
	
	public static JPanel panel_Disco = new JPanel();
	public static JCheckBox checkBox_Sams = new JCheckBox("Sams?");
	public static JCheckBox checkBox_Analog = new JCheckBox("Analog?");
	public static JCheckBox checkBox_Pass = new JCheckBox("Passthrough?");
	public static JCheckBox checkBox_1539 = new JCheckBox("1539 Circuit?");
	public static JToggleButton toggleButton_Chf = new JToggleButton("CHF Creation Active");
	public static JLabel label_Tsr = new JLabel("TSR Number");
	public static JLabel label_RptDate = new JLabel("Report Date");
	public static final JTextField textField_Tsr = new JTextField();
	public static final JTextField textField_RptDate = new JTextField();
	public static JPanel panel_TsoState = new JPanel();
	public static final JTextArea textArea = new JTextArea();
	
	
	public static void buildPanel(){
		
		JPanel action = Window.getPanel_Action();
		
		panel_Disco.setLayout(null);
		action.add(panel_Disco, "panel_Disco");
		
		checkBox_Sams.setBounds(8, 33, 65, 24);
		panel_Disco.add(checkBox_Sams);
		
		checkBox_Analog.setBounds(8, 61, 71, 24);
		panel_Disco.add(checkBox_Analog);
		
		checkBox_Pass.setBounds(8, 89, 105, 24);
		panel_Disco.add(checkBox_Pass);
		
		checkBox_1539.setBounds(8, 8, 112, 24);
		panel_Disco.add(checkBox_1539);
		
		toggleButton_Chf.setBounds(140, 47, 145, 26);
		panel_Disco.add(toggleButton_Chf);
		
		label_Tsr.setBounds(12, 222, 71, 16);
		panel_Disco.add(label_Tsr);
		
		label_RptDate.setBounds(12, 252, 67, 16);
		panel_Disco.add(label_RptDate);
		
		textField_Tsr.setColumns(10);
		textField_Tsr.setBounds(171, 220, 114, 20);
		panel_Disco.add(textField_Tsr);
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
		panel_Disco.add(textField_RptDate);
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
		panel_Disco.add(panel_TsoState);
		
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
		
		Collection.discoIs1539 = checkBox_1539.isSelected();
		Collection.discoHasSams = checkBox_Sams.isSelected();
		Collection.discoIsAnalog = checkBox_Analog.isSelected();
		Collection.discoIsPassthrough = checkBox_Pass.isSelected();
		
		Collection.discoChf = toggleButton_Chf.isSelected();
		
		Collection.discoTsrNumber = textField_Tsr.getText().toUpperCase();
		Collection.discoReportDate = textField_RptDate.getText().toUpperCase();
		Collection.discoTsoStatement = textArea.getText().toUpperCase();
		
	}

}
