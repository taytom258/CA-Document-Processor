package taytom258.core.util;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import taytom258.core.util.db.Database;
import taytom258.core.util.db.TSOCommit;
import taytom258.lib.Collection;
import taytom258.lib.Strings;

public class TSOPopHelper extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2605535464107680020L;
	public static TSOPopHelper dialog = null;
	private final JPanel panel = new JPanel();
	private final JButton btnCancel = new JButton("Cancel");
	private JTextField textField_locationCode;
	private JTextField textField_ENRCode;
	private JComboBox<String> comboBox_location;

	/**
	 * Launch the application.
	 */
	public void appear() {
		dialog.setTitle(Strings.DATA_TITLE);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public TSOPopHelper(String ENR, String LocationCode) {
		dialog = this;
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 262, 194);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Database.init(false);
					save();
					if (Collection.userENRInput.size() != Collection.inputNeeded
							.size()) {
						String[] temp = Collection.inputNeeded.get(
								Collection.userENRInput.size()).split(":");
						clear();
						TSOPopHelper pop = new TSOPopHelper(temp[1], temp[0]);
						pop.appear();
					} else if (Collection.userENRInput.size() == Collection.inputNeeded
							.size()) {
						for (String element : Collection.userENRInput) {
							String first = element.substring(0,
									element.indexOf(':'));
							String second = element.substring(element
									.indexOf(':') + 1, element.indexOf(':',
									element.indexOf(':') + 1));
							String third = element.substring(element.indexOf(
									':', element.indexOf(':') + 1) + 1);

							int code = -1, code2 = -1;
							for (int i = 0; i < Strings.LOCATIONS.length; i++) {
								if (third.equals(Strings.LOCATIONS[i])) {
									code = i;
								}
								if (Collection.location
										.equals(Strings.LOCATIONS[i])) {
									code2 = i;
								}
							}
							if (code2 > code || code2 == -1) {
								Collection.location = third;
							}

							String sql = "INSERT INTO " + Strings.ENRCODE_TABLE
									+ "(ENR, Location, Sublocation) VALUES ('"
									+ second + "', '" + first + "', '" + third
									+ "')";
							Database.dbUpdate(sql);
						}
						clear();
					}
					TSOCommit.run();
					Database.init(true);
				}
			});
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			{
				buttonPane.add(btnCancel);
				btnCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						clear();
					}
				});
			}
		}
		{
			panel.setBackground(UIManager.getColor("Button.background"));
			getContentPane().add(panel, BorderLayout.CENTER);
		}
		panel.setLayout(null);

		JLabel lblLocationCode = new JLabel("Location CODE");
		lblLocationCode.setBounds(27, 12, 84, 16);
		panel.add(lblLocationCode);

		JLabel lblEnrCode = new JLabel("ENR Code");
		lblEnrCode.setBounds(189, 12, 55, 16);
		panel.add(lblEnrCode);

		textField_locationCode = new JTextField();
		textField_locationCode.setEditable(false);
		textField_locationCode.setBounds(12, 40, 114, 20);
		panel.add(textField_locationCode);
		textField_locationCode.setColumns(10);
		textField_locationCode.setText(LocationCode);

		textField_ENRCode = new JTextField();
		textField_ENRCode.setEditable(false);
		textField_ENRCode.setBounds(189, 40, 55, 20);
		panel.add(textField_ENRCode);
		textField_ENRCode.setColumns(10);
		textField_ENRCode.setText(ENR);

		comboBox_location = new JComboBox<String>();
		comboBox_location.setModel(new DefaultComboBoxModel<String>(
				Strings.LOCATIONS));
		comboBox_location.setBounds(12, 93, 114, 25);
		panel.add(comboBox_location);

		JLabel lblSelectLocation = new JLabel("Select Location...");
		lblSelectLocation.setBounds(18, 72, 97, 16);
		panel.add(lblSelectLocation);
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
	}

	private void save() {
		Collection.userENRInput.add(textField_locationCode.getText() + ":"
				+ textField_ENRCode.getText() + ":"
				+ comboBox_location.getSelectedItem());
	}

	private void clear() {
		dialog.dispose();
		dialog = null;
	}
}
