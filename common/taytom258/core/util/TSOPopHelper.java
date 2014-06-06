package taytom258.core.util;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import taytom258.core.util.db.TSOCommit;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TSOPopHelper extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2605535464107680020L;
	private static TSOPopHelper dialog = new TSOPopHelper();
	private final JPanel panel = new JPanel();
	private final JLabel label = new JLabel("MRC");
	private final JTextField textField_nrc = new JTextField();
	private final JTextField textField_mrc = new JTextField();
	private final JLabel label_1 = new JLabel("NRC");
	private final JCheckBox checkBox_careq = new JCheckBox("CA Action Required?");
	private JComboBox comboBox = new JComboBox();
	private final JButton btnCancel = new JButton("Cancel");
	
	/**
	 * Launch the application.
	 */
	public static void appear() {
		
		dialog.setTitle(Strings.DATA_TITLE);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public TSOPopHelper() {
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 400, 194);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					save();
					clear();
					dialog.dispose();
					TSOCommit.run();
				}
			});
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			{
				btnCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				buttonPane.add(btnCancel);
			}
		}
		{
			panel.setBackground(UIManager.getColor("Button.background"));
			getContentPane().add(panel, BorderLayout.CENTER);
		}
		panel.setLayout(null);
		{
			label.setBounds(10, 97, 26, 16);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(label);
		}
		{
			textField_nrc.setBounds(42, 64, 86, 20);
			textField_nrc.setColumns(10);
			textField_nrc.setText("0.00");
			panel.add(textField_nrc);
		}
		{
			textField_mrc.setBounds(42, 94, 86, 20);
			textField_mrc.setText("0.00");
			textField_mrc.setColumns(10);
			panel.add(textField_mrc);
		}
		{
			label_1.setBounds(10, 66, 24, 16);
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(label_1);
		}
		{
			checkBox_careq.setBounds(245, 61, 141, 24);
			checkBox_careq.setBackground(UIManager.getColor("Button.background"));
			panel.add(checkBox_careq);
		}
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Dialog", Font.PLAIN, 14));
		textPane.setEditable(false);
		textPane.setText(Strings.DATA_HEADER);
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setBounds(10, 11, 374, 44);
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		panel.add(textPane);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Andrews", "Bolling", "Andrews 1539"}));
		comboBox.setBounds(228, 93, 154, 25);
		comboBox.setEditable(false);
		comboBox.setSelectedIndex(0);
		panel.add(comboBox);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(155, 97, 55, 16);
		panel.add(lblLocation);
	}
	
	private void save() {
		double z = 0;
		if (!textField_mrc.getText().equals("")) {
			Collection.mrc = Double.valueOf(textField_mrc.getText().trim());
		}else{
			Collection.mrc = z;
		}
		if (!textField_nrc.getText().equals("")) {
			Collection.nrc = Double.valueOf(textField_nrc.getText().trim());
		}else{
			Collection.nrc = z;
		}
		if(comboBox.getSelectedIndex() != -1){
			Collection.location = comboBox.getSelectedItem().toString();
		}else{
			Collection.location = "Not Set";
		}
		Collection.careq = checkBox_careq.isSelected();
	}
	
	private void clear(){
		textField_mrc.setText("0.00");
		textField_nrc.setText("0.00");
		comboBox.setSelectedIndex(0);
		checkBox_careq.setSelected(false);
	}
}
