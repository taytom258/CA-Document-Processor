package taytom258.tso;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import taytom258.config.Config;
import taytom258.lib.Collection;
import taytom258.window.Window;

public class TSOStart {
	
	private static JPanel panel_Start = new JPanel();
	private static JCheckBox checkBox_Sams = new JCheckBox("Sams?");
	private static JCheckBox checkBox_Analog = new JCheckBox("Analog?");
	private static JCheckBox checkBox_Pass = new JCheckBox("Passthrough?");
	private static JCheckBox checkBox_1539 = new JCheckBox("1539 Circuit?");
	private static final JTextField textField_DataRate = new JTextField();
	private static JLabel label_DataRate = new JLabel("Data Rate");
	private static final JTextField textField_SvcAvail = new JTextField();
	private static JLabel label_SvcAvail = new JLabel("Service Availability");
	private static JLabel label_Tsp = new JLabel("TSP");
	private static JLabel label_TsrNum = new JLabel("TSR Number");
	private static JLabel label_RptDate = new JLabel("Report Date");
	private static JComboBox comboBox_Tsp = new JComboBox();
	private static final JTextField textField_Tsr = new JTextField();
	private static final JTextField textField_RptDate = new JTextField();
	private static JPanel panel_TsoState = new JPanel();
	private static final JTextArea textArea_TsoState = new JTextArea();
	
	
	
	
	public static void buildPanel(){
		
		JPanel action = Window.getPanel_Action();
		
		panel_Start.setLayout(null);
		action.add(panel_Start, "panel_Start");
		
		checkBox_Sams.setBounds(8, 33, 65, 24);
		panel_Start.add(checkBox_Sams);
		
		checkBox_Analog.setBounds(8, 61, 71, 24);
		panel_Start.add(checkBox_Analog);
		
		
		checkBox_Pass.setBounds(8, 89, 105, 24);
		panel_Start.add(checkBox_Pass);
		
		textField_DataRate.setColumns(10);
		textField_DataRate.setBounds(171, 125, 114, 20);
		panel_Start.add(textField_DataRate);
		textField_DataRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == 1 && Config.autoSelection){
					e.getComponent().requestFocus();
					textField_DataRate.selectAll();
				}else if(e.isPopupTrigger()){
					e.getComponent().requestFocus();
					Window.popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		label_DataRate.setBounds(12, 127, 55, 16);
		panel_Start.add(label_DataRate);
		
		textField_SvcAvail.setColumns(10);
		textField_SvcAvail.setBounds(171, 157, 114, 20);
		panel_Start.add(textField_SvcAvail);
		textField_SvcAvail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == 1 && Config.autoSelection){
					e.getComponent().requestFocus();
					textField_SvcAvail.selectAll();
				}else if(e.isPopupTrigger()){
					e.getComponent().requestFocus();
					Window.popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		label_SvcAvail.setBounds(12, 159, 106, 16);
		panel_Start.add(label_SvcAvail);
		
		label_Tsp.setBounds(12, 189, 23, 16);
		panel_Start.add(label_Tsp);
		
		comboBox_Tsp.setBounds(171, 185, 114, 25);
		String[] list = {"TSP 1", "TSP 2", "TSP 3", "TSP 4", "TSP 5", "NA"};
		for(String element : list){
			comboBox_Tsp.addItem(element);
		}
		panel_Start.add(comboBox_Tsp);
		
		label_TsrNum.setBounds(12, 222, 71, 16);
		panel_Start.add(label_TsrNum);
		
		label_RptDate.setBounds(12, 252, 67, 16);
		panel_Start.add(label_RptDate);
		
		textField_Tsr.setColumns(10);
		textField_Tsr.setBounds(171, 220, 114, 20);
		panel_Start.add(textField_Tsr);
		textField_Tsr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == 1 && Config.autoSelection){
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
		panel_Start.add(textField_RptDate);
		textField_RptDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == 1 && Config.autoSelection){
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
		panel_Start.add(panel_TsoState);
		
		textArea_TsoState.setWrapStyleWord(true);
		textArea_TsoState.setToolTipText("Enter Extra Comments Here");
		textArea_TsoState.setLineWrap(true);
		textArea_TsoState.setBounds(12, 23, 259, 78);
		panel_TsoState.add(textArea_TsoState);
		textArea_TsoState.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == 1 && Config.autoSelection){
					e.getComponent().requestFocus();
					textArea_TsoState.selectAll();
				}else if(e.isPopupTrigger()){
					e.getComponent().requestFocus();
					Window.popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		checkBox_1539.setBounds(8, 8, 112, 24);
		panel_Start.add(checkBox_1539);
		
	}

	public static void collect(){
		
		Collection.startIs1539 = checkBox_1539.isSelected();
		Collection.startHasSams = checkBox_Sams.isSelected();
		Collection.startIsAnalog = checkBox_Analog.isSelected();
		Collection.startIsPassthrough = checkBox_Pass.isSelected();
		
		Collection.dataRate = textField_DataRate.getText().toUpperCase();
		Collection.serviceAvailible = textField_SvcAvail.getText().toUpperCase();
		Collection.tsp = comboBox_Tsp.getSelectedItem().toString().toUpperCase();
		Collection.startTsrNumber = textField_Tsr.getText().toUpperCase();
		Collection.startReportDate = textField_RptDate.getText().toUpperCase();
		
		Collection.startTsoStatement = textArea_TsoState.getText().toUpperCase();
	}


}
