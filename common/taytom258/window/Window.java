package taytom258.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;

import taytom258.lib.Reference;
import taytom258.lib.util.LogHelper;
import taytom258.window.core.WindowCore;

public class Window extends WindowCore {

	private JFrame frmTsoHelper;

	/**
	 * Launch the application.
	 */
	public static void appear() {
		LogHelper.info("Main Window");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmTsoHelper.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTsoHelper = new JFrame();
		frmTsoHelper.setTitle("TSO Helper");
		frmTsoHelper.setBounds(100, 100, 647, 535);
		frmTsoHelper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frmTsoHelper.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 302, 416);
		layeredPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Tab 1", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Tab 2", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Tab 3", null, panel_3, null);
		panel_3.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(322, 11, 299, 416);
		layeredPane.add(tabbedPane_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("Tab 1", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_1.addTab("Tab 2", null, panel_4, null);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane_1.addTab("Tab 3", null, panel_5, null);
		panel_5.setLayout(null);
		
		JButton btnEnterTso = new JButton("Enter TSO");
		btnEnterTso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Text.appear();
			}
		});
		btnEnterTso.setBounds(10, 463, 91, 26);
		layeredPane.add(btnEnterTso);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings.appear();
			}
		});
		btnSettings.setBounds(109, 463, 81, 26);
		layeredPane.add(btnSettings);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(202, 463, 56, 26);
		layeredPane.add(btnRun);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogHelper.info("Closing Program");
				frmTsoHelper.dispose();
			}
		});
		btnExit.setBounds(530, 463, 91, 26);
		layeredPane.add(btnExit);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 438, 611, 14);
		layeredPane.add(progressBar);
		
		JLabel lblTsoHelperV = new JLabel("TSO Helper " + Reference.APPLICATION_VERSION);
		lblTsoHelperV.setBackground(Color.LIGHT_GRAY);
		lblTsoHelperV.setBounds(378, 466, 92, 16);
		layeredPane.add(lblTsoHelperV);
	}
}
