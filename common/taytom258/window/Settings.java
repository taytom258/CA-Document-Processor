package taytom258.window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import taytom258.config.Config;
import taytom258.core.util.LogHelper;
import taytom258.window.core.SettingsCore;

public class Settings{

	private JFrame frmSettings;
	private static JTextField textFieldchfPath;
	private static JTextField textFieldchfTest;
	private static JToggleButton tglbtnDebugMode;
	private static JToggleButton tglbtnUseChf;
	private static JToggleButton tglbtnAutoSelection;
	private static JToggleButton tglbtnError;
	private static JTextField textFielddbPath;

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
					LogHelper.severe(e.getMessage());
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
		frmSettings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frmSettings.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Save & Close");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
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
		tabbedPane.addTab("Settings", null, panel, null);
		panel.setLayout(null);
		
		textFieldchfPath = new JTextField();
		textFieldchfPath.setBounds(277, 12, 310, 20);
		panel.add(textFieldchfPath);
		textFieldchfPath.setColumns(10);
		textFieldchfPath.setText(Config.chfPath);
		
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
		textFieldchfTest.setText(Config.chfTest);
		
		tglbtnDebugMode = new JToggleButton("Debug Mode");
		tglbtnDebugMode.setSelected(Config.debug);
		tglbtnDebugMode.setBounds(12, 369, 104, 26);
		panel.add(tglbtnDebugMode);
		
		tglbtnUseChf = new JToggleButton("Use CHF");
		tglbtnUseChf.setSelected(Config.useChf);
		tglbtnUseChf.setBounds(12, 331, 104, 26);
		panel.add(tglbtnUseChf);
		
		tglbtnAutoSelection = new JToggleButton("Auto Selection");
		tglbtnAutoSelection.setSelected(Config.autoSelection);
		tglbtnAutoSelection.setBounds(128, 369, 116, 26);
		panel.add(tglbtnAutoSelection);
		
		tglbtnError = new JToggleButton("Error Popups");
		tglbtnError.setSelected(Config.error);
		tglbtnError.setBounds(128, 331, 116, 26);
		panel.add(tglbtnError);
		
		JLabel lblDatabasePath = new JLabel("Database Path");
		lblDatabasePath.setBounds(12, 74, 83, 16);
		panel.add(lblDatabasePath);
		
		textFielddbPath = new JTextField();
		textFielddbPath.setBounds(277, 72, 310, 20);
		textFielddbPath.setText(Config.dbPath);
		panel.add(textFielddbPath);
		textFielddbPath.setColumns(10);
		
		JButton btnConsole = new JButton("Console");
		btnConsole.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Console.getFrame().setVisible(true);
			}
		});
		btnConsole.setBounds(254, 459, 98, 26);
		layeredPane.add(btnConsole);
	}
	protected static JTextField getTextFieldchfPath() {
		return textFieldchfPath;
	}
	protected static JTextField getTextFieldchfTest() {
		return textFieldchfTest;
	}
	protected static JToggleButton getTglbtnDebugMode() {
		return tglbtnDebugMode;
	}
	protected static JToggleButton getTglbtnUseChf() {
		return tglbtnUseChf;
	}
	protected static JToggleButton getTglbtnAutoSelection() {
		return tglbtnAutoSelection;
	}
	protected static JToggleButton getTglbtnError() {
		return tglbtnError;
	}
	protected static JTextField getTextFielddbPath() {
		return textFielddbPath;
	}
}
