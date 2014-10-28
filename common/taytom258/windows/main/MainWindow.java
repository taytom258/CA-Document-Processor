package taytom258.windows.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import taytom258.config.Config;
import taytom258.core.log.LogHelper;
import taytom258.core.util.db.TSOCommit;
import taytom258.core.util.popups.NormalPop;
import taytom258.core.util.popups.TSOInputNeededPop;
import taytom258.lib.Collection;
import taytom258.lib.Reference;
import taytom258.testing.Test;
import taytom258.windows.Help;
import taytom258.windows.Settings;
import taytom258.windows.TSO;
import taytom258.windows.main.tsoTab.TSO_CHF_Panel;
import taytom258.windows.main.tsoTab.TSO_DB_TabPane;
import taytom258.windows.main.tsoTab.TSO_Facit_Panel;

public class MainWindow {

	public static JFrame frmMain;
	private JButton btnSettings;
	private JButton btnExit;
	private JButton btnHelp;
	private JButton btnCommit;
	private JButton btnTso;
	private JButton btnIer;
	private JButton btnDsr;
	private JButton btnTsr;
	private JButton btnExp;
	private JButton btnTest;
	private JTabbedPane tabGroupMain;
	private JTabbedPane tabPaneTso;
	private JTabbedPane tabPaneIer;
	
	
	/**
	 * Initialize and show the main window. <br>
	 * It is not intended behavior to hide this window.
	 */
	public static void show() {
		LogHelper.info("Main Window");
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow();
				frmMain.setVisible(true);
			}
		});
	}

	/**
	 * Create the main window.
	 */
	public MainWindow() {
		frmMain = new JFrame();
		frmMain.setTitle(Reference.APPLICATION_NAME + " "+ Reference.APPLICATION_VERSION);
		frmMain.setBounds(100, 100, 647, 535);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		frmMain.setResizable(false);
		frmMain.getContentPane().add(mainPanel());
		
		/*
		 * Create and layout main buttons with actions
		 */
		btnSettings = new JButton("Settings");
		btnSettings.setToolTipText("Program Settings");
		btnSettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.appear();
			}
		});
		btnSettings.setBounds(10, 435, 91, 60);
		frmMain.getContentPane().add(btnSettings);

		btnExit = new JButton("Exit");
		btnExit.setToolTipText("Close the Program");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LogHelper.debug("Terminate JVM");
				System.exit(0);
			}
		});
		btnExit.setBounds(538, 435, 91, 60);
		frmMain.getContentPane().add(btnExit);
		
		//TODO remove commit button?
		btnCommit = new JButton("Commit to DB");
		btnCommit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Collection.runClicked && Collection.inputNeeded.size() > 0) {
					String[] temp = Collection.inputNeeded.get(0).split(":");
					TSOInputNeededPop pop = new TSOInputNeededPop(temp[1], temp[0]);
					pop.appear();
				} else if (Collection.runClicked) {
					try {
						TSOCommit.run();
					} catch (SQLException e) {
						LogHelper.severe(e.getMessage());
						e.printStackTrace();
					}
				} else {
					LogHelper.warning("Please run the program first");
				}
				NormalPop.appear("Database", "Database commit complete");

			}
		});
		btnCommit.setToolTipText("Commit to the Database");
		btnCommit.setBounds(267, 435, 111, 60);
		frmMain.getContentPane().add(btnCommit);
	}

	/**
	 * Create the main panel
	 */
	private JPanel mainPanel() {

		JPanel panelMain = new JPanel();
		panelMain.setBounds(0, 0, 641, 423);
		panelMain.setLayout(null);

		/*
		 * Create and layout document buttons with actions
		 */
		btnTso = new JButton("TSO");
		btnTso.setToolTipText("Telecommunication Service Order");
		btnTso.setBounds(12, 12, 91, 26);
		panelMain.add(btnTso);
		btnTso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TSO.appear();
			}
		});
		
		btnIer = new JButton("IER");
		btnIer.setBounds(12, 50, 91, 26);
		panelMain.add(btnIer);
		
		btnDsr = new JButton("DSR");
		btnDsr.setBounds(12, 88, 91, 26);
		panelMain.add(btnDsr);
		
		btnTsr = new JButton("TSR");
		btnTsr.setBounds(12, 126, 91, 26);
		panelMain.add(btnTsr);
		
		btnExp = new JButton("EXP");
		btnExp.setBounds(12, 164, 91, 26);
		panelMain.add(btnExp);

		/*
		 * Create and layout special debug testing button
		 */
		btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Test.run();
			}
		});
		if (Config.debug) {
			btnTest.setVisible(true);
		} else {
			btnTest.setVisible(false);
		}
		btnTest.setToolTipText("Test");
		btnTest.setBounds(429, 452, 59, 26);
		frmMain.getContentPane().add(btnTest);
		
		/*
		 * Create and layout help button
		 */
		btnHelp = new JButton("Help");
		btnHelp.setToolTipText("Need support?");
		btnHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Help.appear();
			}
		});
		btnHelp.setBounds(12, 385, 91, 26);
		panelMain.add(btnHelp);
		
		/*
		 * Create and layout tab panes
		 */

		tabGroupMain = new JTabbedPane(SwingConstants.TOP);
		tabGroupMain.setBounds(115, 0, 526, 423);
		panelMain.add(tabGroupMain);

		/*
		 * Create and add TSO tab to main tab group
		 */
		tabPaneTso = new JTabbedPane(SwingConstants.TOP);
		tabPaneTso.setToolTipText("TSO Document Tab");
		tabGroupMain.addTab("TSO", null, tabPaneTso, null);
		
		tabPaneTso.addTab("CHF", null, new TSO_CHF_Panel(), "CHF Info Tab");
		tabPaneTso.addTab("FACIT", null, new TSO_Facit_Panel(), "Facit Info Tab");
		tabPaneTso.addTab("DB", null, new TSO_DB_TabPane(), "Database Preview");
		
		/*
		 * Create and add IER tab to main tab group
		 */
		tabPaneIer = new JTabbedPane(SwingConstants.TOP);
		tabPaneIer.setToolTipText("IER Document Tab");
		tabGroupMain.addTab("IER", null, tabPaneIer, null);
		
//		tabPaneIer.addTab("CHF", null, new TSO_CHF_Panel(), "CHF Info Tab");
//		tabPaneIer.addTab("FACIT", null, new TSO_Facit_Panel(), "Facit Info Tab");
//		tabPaneIer.addTab("DB", null, new TSO_DB_TabPane(), "Database Preview");
		
		return panelMain;

	}
}
