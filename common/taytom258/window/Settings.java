package taytom258.window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import taytom258.config.Config;
import taytom258.core.util.LogHelper;
import taytom258.window.core.SettingsCore;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class Settings extends SettingsCore{

	private JFrame frmSettings;
	private static JTextField textFieldchfPath;
	private static JTextField textFieldchfTest;
	private static JTextField textFieldconfigLoc;
	private static JToggleButton tglbtnDebugMode;
	private static JToggleButton tglbtnUserStoredConfig;
	private static JToggleButton tglbtnUseChf;

	/**
	 * Launch the application.
	 */
	public static void appear() {
		LogHelper.info("Settings Window");
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Settings window = new Settings();
					window.frmSettings.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Settings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSettings = new JFrame();
		frmSettings.setTitle("Settings");
		frmSettings.setBounds(100, 100, 644, 535);
		frmSettings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frmSettings.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Save & Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsCore.save();
				frmSettings.dispose();
			}
		});
		btnNewButton.setBounds(12, 459, 109, 26);
		layeredPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmSettings.dispose();
			}
		});
		btnNewButton_1.setBounds(543, 459, 73, 26);
		layeredPane.add(btnNewButton_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		layeredPane.setLayer(tabbedPane, -1);
		tabbedPane.setBounds(12, 12, 604, 435);
		layeredPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("File", null, panel, null);
		panel.setLayout(null);
		
		textFieldchfPath = new JTextField();
		textFieldchfPath.setBounds(277, 12, 310, 20);
		panel.add(textFieldchfPath);
		textFieldchfPath.setColumns(10);
		textFieldchfPath.setText(Config.CHF_PATH);
		
		JLabel lblChfLocation = new JLabel("CHF Location");
		lblChfLocation.setBounds(12, 14, 74, 16);
		panel.add(lblChfLocation);
		
		JLabel lblTestLocation = new JLabel("CHF Test Location");
		lblTestLocation.setBounds(12, 46, 102, 16);
		panel.add(lblTestLocation);
		
		textFieldchfTest = new JTextField();
		textFieldchfTest.setColumns(10);
		textFieldchfTest.setBounds(277, 44, 310, 20);
		panel.add(textFieldchfTest);
		textFieldchfTest.setText(Config.CHF_TEST);
		
		JLabel lblOtherConfigLocation = new JLabel("Other Config Location");
		lblOtherConfigLocation.setBounds(12, 78, 123, 16);
		panel.add(lblOtherConfigLocation);
		
		textFieldconfigLoc = new JTextField();
		textFieldconfigLoc.setColumns(10);
		textFieldconfigLoc.setBounds(277, 76, 310, 20);
		panel.add(textFieldconfigLoc);
		textFieldconfigLoc.setText(Config.CONFIG_PATH);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Other", null, panel_3, null);
		panel_3.setLayout(null);
		
		tglbtnDebugMode = new JToggleButton("Debug Mode");
		tglbtnDebugMode.setBounds(12, 12, 104, 26);
		panel_3.add(tglbtnDebugMode);
		
		tglbtnUserStoredConfig = new JToggleButton("User Stored Config");
		tglbtnUserStoredConfig.setBounds(446, 12, 141, 26);
		panel_3.add(tglbtnUserStoredConfig);
		tglbtnUserStoredConfig.setSelected(Config.CONFIG_USER);
		
		tglbtnUseChf = new JToggleButton("Use CHF");
		tglbtnUseChf.setBounds(446, 50, 141, 26);
		panel_3.add(tglbtnUseChf);
		tglbtnUseChf.setSelected(Config.USE_CHF);
	}
	public static JTextField getTextFieldchfPath() {
		return textFieldchfPath;
	}
	public static JTextField getTextFieldchfTest() {
		return textFieldchfTest;
	}
	public static JTextField getTextFieldconfigLoc() {
		return textFieldconfigLoc;
	}
	public static JToggleButton getTglbtnDebugMode() {
		return tglbtnDebugMode;
	}
	public static JToggleButton getTglbtnUserStoredConfig() {
		return tglbtnUserStoredConfig;
	}
	public static JToggleButton getTglbtnUseChf() {
		return tglbtnUseChf;
	}
}
