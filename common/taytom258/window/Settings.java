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

import taytom258.lib.util.LogHelper;
import taytom258.window.core.SettingsCore;

public class Settings extends SettingsCore{

	private JFrame frmSettings;

	/**
	 * Launch the application.
	 */
	public static void appear() {
		LogHelper.info("Settings Window");
		EventQueue.invokeLater(new Runnable() {
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
		btnNewButton.setBounds(12, 459, 109, 26);
		layeredPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSettings.dispose();
			}
		});
		btnNewButton_1.setBounds(543, 459, 73, 26);
		layeredPane.add(btnNewButton_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		layeredPane.setLayer(tabbedPane, -1);
		tabbedPane.setBounds(12, 12, 604, 435);
		layeredPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Tab 1", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Tab 2", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tab 3", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Tab 4", null, panel_3, null);
		panel_3.setLayout(null);
	}
}
