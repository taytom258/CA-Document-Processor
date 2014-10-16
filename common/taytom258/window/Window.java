package taytom258.window;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import taytom258.config.Config;
import taytom258.core.util.LogHelper;
import taytom258.core.util.PopHelper;
import taytom258.core.util.TSOPopHelper;
import taytom258.core.util.db.TSOCommit;
import taytom258.lib.Collection;
import taytom258.lib.Reference;
import taytom258.testing.IER;
import taytom258.testing.MassInsert;
import taytom258.window.core.WindowCore;

public class Window {

	protected static JFrame frmTsoHelper;
	protected static JTextField textField_TsoSubject;
	protected static JTextField textField_FullCcsd;
	protected static JTextField textField_ServiceDate;
	protected static JTextField textField_CmoName;
	protected static JTextField textField_Dsn;
	protected static JTextField textField_Comm;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public static JPopupMenu popup = WindowCore.createPopup();
	private static JRadioButton radioButton_Other;
	private static JRadioButton radioButton_Andrews;
	private static JCheckBox checkBox_Ancs;
	private static JCheckBox checkBox_Logical;
	private static JCheckBox checkBox_Crr;
	private static JTextArea textArea_Comments;
	private static JTabbedPane tabbedPane;
	private static JPanel panel_Action;
	private static JTextField textField_ChfTsoName;
	private static JTextField textField_ChfRoot;
	private static JTextField textField_FacitCcsd;
	private static JTextField textField_FacitTsp;
	private static JTextField textField_FacitPurpose;
	private static JTextField textField_FacitRate;
	private static JTextField textField_FacitSvcAvailable;
	private static JTextField textField_FacitFullCcsd;
	private static JTextField textField_FacitAction;
	private static JTextField textField_FacitTsoNum;
	private static JTextField textField_FacitTsrNum;
	private static JTextField textField_FacitSvcDate;
	private static JTextField textField_FacitTsoSubject;
	private static JTextField textField_FacitReportDate;
	private static JRadioButton rdbtn_ChfRoot;
	private static JTextPane textPane_ChfCurrent;
	private static JTextPane textPane_ChfCreating;
	private static JCheckBox chckbx_FacitCrr;
	private static JRadioButton rdbtn_ChfRootCreated;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private static JPanel panel_TSO_Chf;
	private static JTextField textField_DB_Ci_FullCcsd;
	private static JTextField textField_DB_Cir_trunkId;
	private static JTextField textField_DB_Cir_fullTsp;
	private static JTextField textField_DB_Cir_tsp;
	private static JTextField textField_DB_Cir_to;
	private static JTextField textField_DB_Cir_from;
	private static JTextField textField_DB_Cir_reqDepartment;
	private static JTextField textField_DB_Cir_type;
	private static JTextField textField_DB_Cir_use;
	private static JTextField textField_DB_Cir_security;
	private static JTextField textField_DB_Cir_rate;
	private static JTextField textField_DB_Cir_flow;
	private static JTextField textField_DB_Cir_avail;
	private static JTextField textField_DB_Cir_cmo;
	private static JTextField textField_DB_Cir_cmoDsn;
	private static JTextField textField_DB_Cir_cmoComm;
	private static JTextField textField_DB_Cir_signal;
	private static JTextField textField_DB_Cir_qcc;
	private static JCheckBox chckbxAndrewsCmo;
	private static JCheckBox chckbxAndrewsEndpoint;
	private static JTextField textField_DB_TSO_TsoNum;
	private static JTextField textField_DB_TSO_TsoSuff;
	private static JTextField textField_DB_TSO_action;
	private static JTextField textField_DB_TSO_fullCcsd;
	private static JTextField textField_DB_TSO_svcDate;
	private static JCheckBox chckbx_DB_TSO_crr;
	private static JTextField textField_chfLink;
	private static JTextField textField_DB_TSO_reportDate;
	private static JTextField textField_FacitAmending;
	private static JTextField textField_FacitTrunkID;
	private static JCheckBox chckbx_FacitIsTrunk;
	private static JTextField textField_DB_TSO_Mrc;
	private static JTextField textField_DB_TSO_Nrc;

	/**
	 * Launch the application.
	 */
	public static void appear() {
		LogHelper.info("Main Window");
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Window window = new Window();
					Window.frmTsoHelper.setVisible(true);
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
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTsoHelper = new JFrame();
		frmTsoHelper.setTitle(Reference.APPLICATION_NAME + " "
				+ Reference.APPLICATION_VERSION);
		frmTsoHelper.setBounds(100, 100, 647, 535);
		frmTsoHelper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTsoHelper.getContentPane().setLayout(null);
		frmTsoHelper.setResizable(false);

		JButton button_Settings = new JButton("Settings");
		button_Settings.setToolTipText("Program Settings");
		button_Settings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.appear();
			}
		});
		button_Settings.setBounds(10, 435, 91, 60);
		frmTsoHelper.getContentPane().add(button_Settings);

		JButton button_Exit = new JButton("Exit");
		button_Exit.setToolTipText("Close the Program");
		button_Exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LogHelper.debug("Terminate JVM");
				System.exit(0);
			}
		});
		button_Exit.setBounds(538, 435, 91, 60);
		frmTsoHelper.getContentPane().add(button_Exit);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 641, 423);
		frmTsoHelper.getContentPane().add(panel);

		tabbedPane = new JTabbedPane(SwingConstants.TOP);
		// panel.add(tabbedPane);

		JPanel panel_general = new JPanel();
		panel_general.setLayout(null);
		tabbedPane.addTab("General", null, panel_general, null);

		textField_TsoSubject = new JTextField();
		textField_TsoSubject.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent arg0) {
				WindowCore.collect.getAction();
			}
		});
		textField_TsoSubject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.getButton() == 1 && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_TsoSubject.selectAll();
				}
			}
		});
		textField_TsoSubject.setToolTipText("Enter TSO Subject Here");
		textField_TsoSubject.setColumns(10);
		textField_TsoSubject.setBackground(Color.WHITE);
		textField_TsoSubject.setBounds(116, 12, 187, 20);
		panel_general.add(textField_TsoSubject);

		JLabel label_TsoSubject = new JLabel("TSO Subject");
		label_TsoSubject.setBounds(12, 14, 70, 16);
		panel_general.add(label_TsoSubject);

		textField_FullCcsd = new JTextField();
		textField_FullCcsd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.getButton() == 1 && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FullCcsd.selectAll();
				}
			}
		});
		textField_FullCcsd.setToolTipText("Enter Full CCSD Here");
		textField_FullCcsd.setColumns(10);
		textField_FullCcsd.setBounds(116, 44, 187, 20);
		panel_general.add(textField_FullCcsd);

		JLabel label_FullCcsd = new JLabel("Full CCSD");
		label_FullCcsd.setBounds(12, 46, 55, 16);
		panel_general.add(label_FullCcsd);

		textField_ServiceDate = new JTextField();
		textField_ServiceDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.getButton() == 1 && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_ServiceDate.selectAll();
				}
			}
		});
		textField_ServiceDate.setToolTipText("Enter Service Date of TSO Here");
		textField_ServiceDate.setColumns(10);
		textField_ServiceDate.setBounds(116, 76, 187, 20);
		panel_general.add(textField_ServiceDate);

		JLabel label_ServiceDate = new JLabel("Service Date");
		label_ServiceDate.setBounds(12, 74, 72, 16);
		panel_general.add(label_ServiceDate);

		textField_CmoName = new JTextField();
		textField_CmoName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && textField_CmoName.isEnabled()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.getButton() == 1 && Config.autoSelection
						&& textField_CmoName.isEnabled()) {
					e.getComponent().requestFocus();
					textField_CmoName.selectAll();
				}
			}
		});
		textField_CmoName.setToolTipText("Enter Other CMO Here");
		textField_CmoName.setText((String) null);
		textField_CmoName.setEnabled(false);
		textField_CmoName.setColumns(10);
		textField_CmoName.setBackground(Color.LIGHT_GRAY);
		textField_CmoName.setBounds(189, 136, 114, 20);
		panel_general.add(textField_CmoName);

		radioButton_Andrews = new JRadioButton("Andrews");
		radioButton_Andrews.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowCore.enableCmoFields(false);
			}
		});
		buttonGroup.add(radioButton_Andrews);
		radioButton_Andrews.setSelected(true);
		radioButton_Andrews.setBounds(148, 104, 76, 24);
		panel_general.add(radioButton_Andrews);

		radioButton_Other = new JRadioButton("Other");
		radioButton_Other.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowCore.enableCmoFields(true);
			}
		});
		buttonGroup.add(radioButton_Other);
		radioButton_Other.setBounds(228, 104, 57, 24);
		panel_general.add(radioButton_Other);

		JLabel label_Cmo = new JLabel("CMO");
		label_Cmo.setBounds(12, 108, 27, 16);
		panel_general.add(label_Cmo);

		checkBox_Crr = new JCheckBox("Completion Report Required?");
		checkBox_Crr.setBounds(12, 234, 191, 24);
		panel_general.add(checkBox_Crr);

		checkBox_Logical = new JCheckBox("Logical?");
		checkBox_Logical.setBounds(12, 206, 73, 24);
		panel_general.add(checkBox_Logical);

		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(new LineBorder(new Color(128, 128,
				128), 1, true), "Extra Comments", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_6.setBounds(8, 266, 283, 113);
		panel_general.add(panel_6);

		textArea_Comments = new JTextArea();
		textArea_Comments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textArea_Comments.setWrapStyleWord(true);
		textArea_Comments.setToolTipText("Enter Extra Comments Here");
		textArea_Comments.setLineWrap(true);
		textArea_Comments.setBounds(12, 23, 259, 78);
		panel_6.add(textArea_Comments);

		textField_Dsn = new JTextField();
		textField_Dsn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && textField_Dsn.isEnabled()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.getButton() == 1 && Config.autoSelection
						&& textField_Dsn.isEnabled()) {
					e.getComponent().requestFocus();
					textField_Dsn.selectAll();
				}
			}
		});
		textField_Dsn.setText((String) null);
		textField_Dsn.setEnabled(false);
		textField_Dsn.setColumns(10);
		textField_Dsn.setBackground(Color.LIGHT_GRAY);
		textField_Dsn.setBounds(189, 169, 114, 20);
		panel_general.add(textField_Dsn);

		textField_Comm = new JTextField();
		textField_Comm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && textField_Comm.isEnabled()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.getButton() == 1 && Config.autoSelection
						&& textField_Comm.isEnabled()) {
					e.getComponent().requestFocus();
					textField_Comm.selectAll();
				}
			}
		});
		textField_Comm.setText((String) null);
		textField_Comm.setEnabled(false);
		textField_Comm.setColumns(10);
		textField_Comm.setBackground(Color.LIGHT_GRAY);
		textField_Comm.setBounds(189, 201, 114, 20);
		panel_general.add(textField_Comm);

		JLabel label_4 = new JLabel("CMO Name");
		label_4.setFont(new Font("Dialog", Font.BOLD, 10));
		label_4.setBounds(116, 140, 55, 14);
		panel_general.add(label_4);

		JLabel label_5 = new JLabel("DSN");
		label_5.setFont(new Font("Dialog", Font.BOLD, 10));
		label_5.setBounds(150, 173, 21, 14);
		panel_general.add(label_5);

		JLabel label_6 = new JLabel("Comm");
		label_6.setFont(new Font("Dialog", Font.BOLD, 10));
		label_6.setBounds(140, 205, 31, 14);
		panel_general.add(label_6);

		checkBox_Ancs = new JCheckBox("ANCS?");
		checkBox_Ancs.setBounds(12, 178, 64, 24);
		panel_general.add(checkBox_Ancs);

		panel_Action = new JPanel();
		tabbedPane.addTab("Action", null, panel_Action, null);
		tabbedPane.setEnabledAt(1, false);
		panel_Action.setLayout(new CardLayout(0, 0));
		JPanel panel_Blank = new JPanel();
		panel_Action.add(panel_Blank, "panel_Blank");
		// TSOStart.buildPanel();
		// TSOChange.buildPanel();
		// TSODisco.buildPanel();
		// TSOAmend.buildPanel();
		// TSOCancel.buildPanel();
		panel.setLayout(null);

		JTabbedPane tabbedPane_1 = new JTabbedPane(SwingConstants.TOP);
		tabbedPane_1.setBounds(115, 0, 526, 423);
		panel.add(tabbedPane_1);

		JTabbedPane tabbedPane_2 = new JTabbedPane(SwingConstants.TOP);
		tabbedPane_2.setToolTipText("TSO Document Tab");
		tabbedPane_1.addTab("TSO", null, tabbedPane_2, null);

		panel_TSO_Chf = new JPanel();
		panel_TSO_Chf.setToolTipText("");
		tabbedPane_2.addTab("CHF", null, panel_TSO_Chf, "CHF Info Tab");
		panel_TSO_Chf.setLayout(null);
		panel_TSO_Chf.setBackground(Color.WHITE);

		JLabel label_ChfTsoName = new JLabel("TSO Name");
		label_ChfTsoName.setBounds(12, 14, 60, 16);
		panel_TSO_Chf.add(label_ChfTsoName);

		JLabel lblChfRoot = new JLabel("CHF Root");
		lblChfRoot.setBounds(12, 42, 51, 16);
		panel_TSO_Chf.add(lblChfRoot);

		textField_ChfTsoName = new JTextField();
		textField_ChfTsoName.setToolTipText("TSO file name");
		textField_ChfTsoName.setBackground(Color.WHITE);
		textField_ChfTsoName.setEditable(false);
		textField_ChfTsoName.setBounds(102, 12, 407, 20);
		panel_TSO_Chf.add(textField_ChfTsoName);
		textField_ChfTsoName.setColumns(10);

		textField_ChfRoot = new JTextField();
		textField_ChfRoot.setToolTipText("Root folder name");
		textField_ChfRoot.setBackground(Color.WHITE);
		textField_ChfRoot.setEditable(false);
		textField_ChfRoot.setBounds(102, 40, 407, 20);
		panel_TSO_Chf.add(textField_ChfRoot);
		textField_ChfRoot.setColumns(10);

		JPanel panel_ChfCreating = new JPanel();
		panel_ChfCreating.setBackground(Color.WHITE);
		panel_ChfCreating.setBorder(new TitledBorder(new LineBorder(new Color(
				128, 128, 128), 1, true), "Created", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_ChfCreating.setBounds(376, 164, 133, 191);
		panel_TSO_Chf.add(panel_ChfCreating);
		panel_ChfCreating.setLayout(null);

		textPane_ChfCreating = new JTextPane();
		textPane_ChfCreating
				.setToolTipText("Folders created inside root folder");
		textPane_ChfCreating.setEditable(false);
		textPane_ChfCreating.setBounds(12, 22, 109, 157);
		panel_ChfCreating.add(textPane_ChfCreating);

		JPanel panel_ChfCurrent = new JPanel();
		panel_ChfCurrent.setBackground(Color.WHITE);
		panel_ChfCurrent.setBorder(new TitledBorder(new LineBorder(new Color(
				128, 128, 128), 1, true), "Current", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_ChfCurrent.setBounds(7, 164, 133, 191);
		panel_TSO_Chf.add(panel_ChfCurrent);
		panel_ChfCurrent.setLayout(null);

		textPane_ChfCurrent = new JTextPane();
		textPane_ChfCurrent
				.setToolTipText("Folders currently in the root folder");
		textPane_ChfCurrent.setEditable(false);
		textPane_ChfCurrent.setBounds(12, 22, 109, 157);
		panel_ChfCurrent.add(textPane_ChfCurrent);

		rdbtn_ChfRoot = new JRadioButton("Root Exists");
		rdbtn_ChfRoot.setToolTipText("Does the root folder exist?");
		buttonGroup_1.add(rdbtn_ChfRoot);
		rdbtn_ChfRoot.setEnabled(false);
		rdbtn_ChfRoot.setBackground(Color.WHITE);
		rdbtn_ChfRoot.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtn_ChfRoot.setBounds(27, 132, 89, 24);
		panel_TSO_Chf.add(rdbtn_ChfRoot);

		rdbtn_ChfRootCreated = new JRadioButton("Root Created");
		rdbtn_ChfRootCreated.setToolTipText("Was the root folder created?");
		buttonGroup_1.add(rdbtn_ChfRootCreated);
		rdbtn_ChfRootCreated.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtn_ChfRootCreated.setEnabled(false);
		rdbtn_ChfRootCreated.setBackground(Color.WHITE);
		rdbtn_ChfRootCreated.setBounds(394, 132, 99, 24);
		panel_TSO_Chf.add(rdbtn_ChfRootCreated);

		JLabel lblChfLink = new JLabel("CHF Link");
		lblChfLink.setBounds(12, 68, 55, 16);
		panel_TSO_Chf.add(lblChfLink);

		textField_chfLink = new JTextField();
		textField_chfLink.setToolTipText("Root folder location");
		textField_chfLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_chfLink.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_chfLink.setBackground(Color.WHITE);
		textField_chfLink.setEditable(false);
		textField_chfLink.setBounds(102, 66, 407, 20);
		panel_TSO_Chf.add(textField_chfLink);
		textField_chfLink.setColumns(10);

		JScrollPane scrollPane_TSO_Facit = new JScrollPane();
		scrollPane_TSO_Facit.setToolTipText("");
		tabbedPane_2.addTab("FACIT", null, scrollPane_TSO_Facit,
				"Facit Info Tab");
		scrollPane_TSO_Facit.getVerticalScrollBar().setUnitIncrement(20);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		scrollPane_TSO_Facit.setViewportView(panel_1);
		panel_1.setLayout(null);
		// panel_1.setPreferredSize(new Dimension(0, 350));

		JLabel label_FacitCcsd = new JLabel("CCSD");
		label_FacitCcsd.setBounds(12, 12, 32, 16);
		panel_1.add(label_FacitCcsd);

		JLabel label_FacitTsp = new JLabel("TSP");
		label_FacitTsp.setBounds(12, 68, 23, 16);
		panel_1.add(label_FacitTsp);

		JLabel label_FacitPurpose = new JLabel("Purpose");
		label_FacitPurpose.setBounds(12, 96, 48, 16);
		panel_1.add(label_FacitPurpose);

		JLabel label_FacitRate = new JLabel("Rate");
		label_FacitRate.setBounds(12, 124, 26, 16);
		panel_1.add(label_FacitRate);

		JLabel label_FacitSvcAvail = new JLabel("Service Availability");
		label_FacitSvcAvail.setBounds(12, 152, 106, 16);
		panel_1.add(label_FacitSvcAvail);

		JLabel label_FacitFullCcsd = new JLabel("Full CCSD");
		label_FacitFullCcsd.setBounds(261, 14, 54, 16);
		panel_1.add(label_FacitFullCcsd);

		JLabel label_FacitAction = new JLabel("Action");
		label_FacitAction.setBounds(261, 42, 36, 16);
		panel_1.add(label_FacitAction);

		JLabel label_FacitTsoNum = new JLabel("TSO Number");
		label_FacitTsoNum.setBounds(12, 228, 72, 16);
		panel_1.add(label_FacitTsoNum);

		JLabel label_FacitTsrNum = new JLabel("TSR Number");
		label_FacitTsrNum.setBounds(261, 98, 71, 16);
		panel_1.add(label_FacitTsrNum);

		JLabel label_FacitServiceDate = new JLabel("Service Date");
		label_FacitServiceDate.setBounds(261, 126, 72, 16);
		panel_1.add(label_FacitServiceDate);

		JLabel label_FacitTsoSubject = new JLabel("TSO Subject");
		label_FacitTsoSubject.setBounds(12, 258, 70, 16);
		panel_1.add(label_FacitTsoSubject);

		JLabel label_FacitReportDate = new JLabel("Report Date");
		label_FacitReportDate.setBounds(261, 154, 67, 16);
		panel_1.add(label_FacitReportDate);

		textField_FacitCcsd = new JTextField();
		textField_FacitCcsd.setToolTipText("4 Character circuit ID");
		textField_FacitCcsd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitCcsd.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitCcsd.setBackground(Color.WHITE);
		textField_FacitCcsd.setEditable(false);
		textField_FacitCcsd.setBounds(136, 10, 106, 20);
		panel_1.add(textField_FacitCcsd);
		textField_FacitCcsd.setColumns(10);

		textField_FacitTsp = new JTextField();
		textField_FacitTsp.setToolTipText("Circuit Priority");
		textField_FacitTsp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitTsp.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitTsp.setBackground(Color.WHITE);
		textField_FacitTsp.setEditable(false);
		textField_FacitTsp.setBounds(136, 66, 106, 20);
		panel_1.add(textField_FacitTsp);
		textField_FacitTsp.setColumns(10);

		textField_FacitPurpose = new JTextField();
		textField_FacitPurpose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitPurpose.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitPurpose.setBackground(Color.WHITE);
		textField_FacitPurpose.setEditable(false);
		textField_FacitPurpose.setBounds(136, 94, 106, 20);
		panel_1.add(textField_FacitPurpose);
		textField_FacitPurpose.setColumns(10);

		textField_FacitRate = new JTextField();
		textField_FacitRate.setToolTipText("Data Rate");
		textField_FacitRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitRate.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitRate.setBackground(Color.WHITE);
		textField_FacitRate.setEditable(false);
		textField_FacitRate.setBounds(136, 122, 106, 20);
		panel_1.add(textField_FacitRate);
		textField_FacitRate.setColumns(10);

		textField_FacitSvcAvailable = new JTextField();
		textField_FacitSvcAvailable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitSvcAvailable.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitSvcAvailable.setBackground(Color.WHITE);
		textField_FacitSvcAvailable.setEditable(false);
		textField_FacitSvcAvailable.setBounds(136, 150, 106, 20);
		panel_1.add(textField_FacitSvcAvailable);
		textField_FacitSvcAvailable.setColumns(10);

		textField_FacitFullCcsd = new JTextField();
		textField_FacitFullCcsd.setToolTipText("8 Character circuit ID");
		textField_FacitFullCcsd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitFullCcsd.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitFullCcsd.setBackground(Color.WHITE);
		textField_FacitFullCcsd.setEditable(false);
		textField_FacitFullCcsd.setBounds(385, 12, 106, 20);
		panel_1.add(textField_FacitFullCcsd);
		textField_FacitFullCcsd.setColumns(10);

		textField_FacitAction = new JTextField();
		textField_FacitAction.setToolTipText("Action of TSO");
		textField_FacitAction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitAction.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitAction.setBackground(Color.WHITE);
		textField_FacitAction.setEditable(false);
		textField_FacitAction.setBounds(385, 40, 106, 20);
		panel_1.add(textField_FacitAction);
		textField_FacitAction.setColumns(10);

		textField_FacitTsoNum = new JTextField();
		textField_FacitTsoNum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitTsoNum.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitTsoNum.setBackground(Color.WHITE);
		textField_FacitTsoNum.setEditable(false);
		textField_FacitTsoNum.setBounds(136, 226, 355, 20);
		panel_1.add(textField_FacitTsoNum);
		textField_FacitTsoNum.setColumns(10);

		textField_FacitTsrNum = new JTextField();
		textField_FacitTsrNum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitTsrNum.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitTsrNum.setBackground(Color.WHITE);
		textField_FacitTsrNum.setEditable(false);
		textField_FacitTsrNum.setBounds(385, 96, 106, 20);
		panel_1.add(textField_FacitTsrNum);
		textField_FacitTsrNum.setColumns(10);

		textField_FacitSvcDate = new JTextField();
		textField_FacitSvcDate.setToolTipText("");
		textField_FacitSvcDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitSvcDate.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitSvcDate.setBackground(Color.WHITE);
		textField_FacitSvcDate.setEditable(false);
		textField_FacitSvcDate.setBounds(385, 124, 106, 20);
		panel_1.add(textField_FacitSvcDate);
		textField_FacitSvcDate.setColumns(10);

		textField_FacitTsoSubject = new JTextField();
		textField_FacitTsoSubject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitTsoSubject.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitTsoSubject.setBackground(Color.WHITE);
		textField_FacitTsoSubject.setEditable(false);
		textField_FacitTsoSubject.setBounds(136, 256, 355, 20);
		panel_1.add(textField_FacitTsoSubject);
		textField_FacitTsoSubject.setColumns(10);

		textField_FacitReportDate = new JTextField();
		textField_FacitReportDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitReportDate.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitReportDate.setBackground(Color.WHITE);
		textField_FacitReportDate.setEditable(false);
		textField_FacitReportDate.setBounds(385, 152, 106, 20);
		panel_1.add(textField_FacitReportDate);
		textField_FacitReportDate.setColumns(10);

		chckbx_FacitCrr = new JCheckBox("Completion Report Required");
		chckbx_FacitCrr.setEnabled(false);
		chckbx_FacitCrr.setBackground(Color.WHITE);
		chckbx_FacitCrr.setBounds(307, 327, 184, 24);
		panel_1.add(chckbx_FacitCrr);

		JButton btnCopyTsoTo = new JButton("TSO Text to Clipboard");
		btnCopyTsoTo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection ss = new StringSelection(Collection.tsoText);
				Clipboard clpbrd = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				clpbrd.setContents(ss, null);
			}
		});
		btnCopyTsoTo.setBounds(12, 288, 160, 26);
		panel_1.add(btnCopyTsoTo);

		JButton button = new JButton("TSO Statement to Clipboard");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection ss = new StringSelection(Collection.purpose);
				Clipboard clpbrd = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				clpbrd.setContents(ss, null);
			}
		});
		button.setBounds(12, 326, 191, 26);
		panel_1.add(button);

		JLabel label = new JLabel("Amending");
		label.setBounds(261, 68, 57, 16);
		panel_1.add(label);

		textField_FacitAmending = new JTextField();
		textField_FacitAmending.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitAmending.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitAmending.setToolTipText("");
		textField_FacitAmending.setEditable(false);
		textField_FacitAmending.setColumns(10);
		textField_FacitAmending.setBackground(Color.WHITE);
		textField_FacitAmending.setBounds(385, 66, 106, 20);
		panel_1.add(textField_FacitAmending);

		JLabel label_1 = new JLabel("Trunk ID");
		label_1.setBounds(12, 40, 47, 16);
		panel_1.add(label_1);

		textField_FacitTrunkID = new JTextField();
		textField_FacitTrunkID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FacitTrunkID.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FacitTrunkID.setToolTipText("");
		textField_FacitTrunkID.setEditable(false);
		textField_FacitTrunkID.setColumns(10);
		textField_FacitTrunkID.setBackground(Color.WHITE);
		textField_FacitTrunkID.setBounds(136, 40, 106, 20);
		panel_1.add(textField_FacitTrunkID);

		chckbx_FacitIsTrunk = new JCheckBox("Circuit is Trunk");
		chckbx_FacitIsTrunk.setEnabled(false);
		chckbx_FacitIsTrunk.setBackground(Color.WHITE);
		chckbx_FacitIsTrunk.setBounds(307, 299, 112, 24);
		panel_1.add(chckbx_FacitIsTrunk);

		JTabbedPane tabbedPane_TSO_DB = new JTabbedPane(SwingConstants.TOP);
		tabbedPane_TSO_DB.setToolTipText("Database Info Tab");
		tabbedPane_2.addTab("DB", null, tabbedPane_TSO_DB, "Database Preview");

		JPanel panel_Circuit = new JPanel();
		panel_Circuit.setToolTipText("");
		panel_Circuit.setBackground(Color.WHITE);
		tabbedPane_TSO_DB.addTab("Circuit", null, panel_Circuit,
				"Circuit Table");
		panel_Circuit.setLayout(null);

		JLabel lblFullCcsd = new JLabel("Full CCSD");
		lblFullCcsd.setHorizontalAlignment(SwingConstants.LEFT);
		lblFullCcsd.setBounds(12, 12, 55, 16);
		panel_Circuit.add(lblFullCcsd);

		textField_DB_Ci_FullCcsd = new JTextField();
		textField_DB_Ci_FullCcsd.setBackground(Color.WHITE);
		textField_DB_Ci_FullCcsd.setEditable(false);
		textField_DB_Ci_FullCcsd.setBounds(85, 10, 114, 20);
		panel_Circuit.add(textField_DB_Ci_FullCcsd);
		textField_DB_Ci_FullCcsd.setColumns(10);

		JLabel lblTrunkId = new JLabel("Trunk ID");
		lblTrunkId.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrunkId.setBounds(12, 40, 55, 16);
		panel_Circuit.add(lblTrunkId);

		textField_DB_Cir_trunkId = new JTextField();
		textField_DB_Cir_trunkId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.getButton() == 1 && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_DB_Cir_trunkId.selectAll();
				}
			}
		});
		textField_DB_Cir_trunkId.setEditable(false);
		textField_DB_Cir_trunkId.setBackground(Color.WHITE);
		textField_DB_Cir_trunkId.setBounds(85, 38, 114, 20);
		panel_Circuit.add(textField_DB_Cir_trunkId);
		textField_DB_Cir_trunkId.setColumns(10);

		JLabel lblFullTsp = new JLabel("Full TSP");
		lblFullTsp.setBounds(12, 68, 55, 16);
		panel_Circuit.add(lblFullTsp);

		textField_DB_Cir_fullTsp = new JTextField();
		textField_DB_Cir_fullTsp.setEditable(false);
		textField_DB_Cir_fullTsp.setBackground(Color.WHITE);
		textField_DB_Cir_fullTsp.setBounds(85, 66, 114, 20);
		panel_Circuit.add(textField_DB_Cir_fullTsp);
		textField_DB_Cir_fullTsp.setColumns(10);

		JLabel lblTsp = new JLabel("TSP");
		lblTsp.setBounds(12, 96, 55, 16);
		panel_Circuit.add(lblTsp);

		textField_DB_Cir_tsp = new JTextField();
		textField_DB_Cir_tsp.setEditable(false);
		textField_DB_Cir_tsp.setBackground(Color.WHITE);
		textField_DB_Cir_tsp.setBounds(85, 94, 114, 20);
		panel_Circuit.add(textField_DB_Cir_tsp);
		textField_DB_Cir_tsp.setColumns(10);

		JLabel lblToLocation = new JLabel("To");
		lblToLocation.setToolTipText("To Location");
		lblToLocation.setBounds(217, 12, 66, 16);
		panel_Circuit.add(lblToLocation);

		textField_DB_Cir_to = new JTextField();
		textField_DB_Cir_to.setEditable(false);
		textField_DB_Cir_to.setBackground(Color.WHITE);
		textField_DB_Cir_to.setBounds(301, 10, 203, 20);
		panel_Circuit.add(textField_DB_Cir_to);
		textField_DB_Cir_to.setColumns(10);

		JLabel lblFromLocation = new JLabel("From");
		lblFromLocation.setToolTipText("From Location");
		lblFromLocation.setBounds(217, 40, 81, 16);
		panel_Circuit.add(lblFromLocation);

		textField_DB_Cir_from = new JTextField();
		textField_DB_Cir_from.setEditable(false);
		textField_DB_Cir_from.setBackground(Color.WHITE);
		textField_DB_Cir_from.setBounds(301, 38, 203, 20);
		panel_Circuit.add(textField_DB_Cir_from);
		textField_DB_Cir_from.setColumns(10);

		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setToolTipText("Requesting Department");
		lblDepartment.setBounds(12, 294, 67, 16);
		panel_Circuit.add(lblDepartment);

		textField_DB_Cir_reqDepartment = new JTextField();
		textField_DB_Cir_reqDepartment.setEditable(false);
		textField_DB_Cir_reqDepartment.setBackground(Color.WHITE);
		textField_DB_Cir_reqDepartment.setBounds(96, 292, 403, 20);
		panel_Circuit.add(textField_DB_Cir_reqDepartment);
		textField_DB_Cir_reqDepartment.setColumns(10);

		JLabel lblType = new JLabel("Type");
		lblType.setToolTipText("Type of Service");
		lblType.setBounds(217, 70, 55, 16);
		panel_Circuit.add(lblType);

		textField_DB_Cir_type = new JTextField();
		textField_DB_Cir_type.setEditable(false);
		textField_DB_Cir_type.setBackground(Color.WHITE);
		textField_DB_Cir_type.setBounds(301, 68, 203, 20);
		panel_Circuit.add(textField_DB_Cir_type);
		textField_DB_Cir_type.setColumns(10);

		JLabel lblUse = new JLabel("Use");
		lblUse.setToolTipText("Circuit Use");
		lblUse.setBounds(217, 98, 55, 16);
		panel_Circuit.add(lblUse);

		textField_DB_Cir_use = new JTextField();
		textField_DB_Cir_use.setEditable(false);
		textField_DB_Cir_use.setBackground(Color.WHITE);
		textField_DB_Cir_use.setBounds(301, 96, 203, 20);
		panel_Circuit.add(textField_DB_Cir_use);
		textField_DB_Cir_use.setColumns(10);

		JLabel lblSecurity = new JLabel("Security");
		lblSecurity.setBounds(12, 124, 55, 16);
		panel_Circuit.add(lblSecurity);

		textField_DB_Cir_security = new JTextField();
		textField_DB_Cir_security.setEditable(false);
		textField_DB_Cir_security.setBackground(Color.WHITE);
		textField_DB_Cir_security.setBounds(85, 122, 114, 20);
		panel_Circuit.add(textField_DB_Cir_security);
		textField_DB_Cir_security.setColumns(10);

		JLabel lblDataRate = new JLabel("Data Rate");
		lblDataRate.setBounds(12, 152, 55, 16);
		panel_Circuit.add(lblDataRate);

		textField_DB_Cir_rate = new JTextField();
		textField_DB_Cir_rate.setEditable(false);
		textField_DB_Cir_rate.setBackground(Color.WHITE);
		textField_DB_Cir_rate.setBounds(85, 150, 114, 20);
		panel_Circuit.add(textField_DB_Cir_rate);
		textField_DB_Cir_rate.setColumns(10);

		JLabel lblFlow = new JLabel("Flow");
		lblFlow.setToolTipText("Traffic Flow");
		lblFlow.setBounds(12, 180, 55, 16);
		panel_Circuit.add(lblFlow);

		textField_DB_Cir_flow = new JTextField();
		textField_DB_Cir_flow.setEditable(false);
		textField_DB_Cir_flow.setEnabled(true);
		textField_DB_Cir_flow.setBackground(Color.WHITE);
		textField_DB_Cir_flow.setText("");
		textField_DB_Cir_flow.setBounds(85, 178, 114, 20);
		panel_Circuit.add(textField_DB_Cir_flow);
		textField_DB_Cir_flow.setColumns(10);

		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setToolTipText("Service Avaliability");
		lblAvailable.setBounds(12, 208, 55, 16);
		panel_Circuit.add(lblAvailable);

		textField_DB_Cir_avail = new JTextField();
		textField_DB_Cir_avail.setEditable(false);
		textField_DB_Cir_avail.setBackground(Color.WHITE);
		textField_DB_Cir_avail.setBounds(85, 206, 114, 20);
		panel_Circuit.add(textField_DB_Cir_avail);
		textField_DB_Cir_avail.setColumns(10);

		JLabel lblCmo = new JLabel("CMO");
		lblCmo.setToolTipText("CMO/CCO");
		lblCmo.setBounds(217, 126, 55, 16);
		panel_Circuit.add(lblCmo);

		textField_DB_Cir_cmo = new JTextField();
		textField_DB_Cir_cmo.setEditable(false);
		textField_DB_Cir_cmo.setBackground(Color.WHITE);
		textField_DB_Cir_cmo.setBounds(301, 124, 203, 20);
		panel_Circuit.add(textField_DB_Cir_cmo);
		textField_DB_Cir_cmo.setColumns(10);

		JLabel lblCmoDsn = new JLabel("CMO DSN");
		lblCmoDsn.setBounds(217, 154, 55, 16);
		panel_Circuit.add(lblCmoDsn);

		textField_DB_Cir_cmoDsn = new JTextField();
		textField_DB_Cir_cmoDsn.setEditable(false);
		textField_DB_Cir_cmoDsn.setBackground(Color.WHITE);
		textField_DB_Cir_cmoDsn.setBounds(301, 152, 203, 20);
		panel_Circuit.add(textField_DB_Cir_cmoDsn);
		textField_DB_Cir_cmoDsn.setColumns(10);

		JLabel lblCmoComm = new JLabel("CMO Comm");
		lblCmoComm.setBounds(217, 182, 67, 16);
		panel_Circuit.add(lblCmoComm);

		textField_DB_Cir_cmoComm = new JTextField();
		textField_DB_Cir_cmoComm.setEditable(false);
		textField_DB_Cir_cmoComm.setBackground(Color.WHITE);
		textField_DB_Cir_cmoComm.setBounds(301, 180, 203, 20);
		panel_Circuit.add(textField_DB_Cir_cmoComm);
		textField_DB_Cir_cmoComm.setColumns(10);

		JLabel lblSignaling = new JLabel("Signaling");
		lblSignaling.setBounds(12, 236, 55, 16);
		panel_Circuit.add(lblSignaling);

		textField_DB_Cir_signal = new JTextField();
		textField_DB_Cir_signal.setEditable(false);
		textField_DB_Cir_signal.setBackground(Color.WHITE);
		textField_DB_Cir_signal.setBounds(85, 234, 114, 20);
		panel_Circuit.add(textField_DB_Cir_signal);
		textField_DB_Cir_signal.setColumns(10);

		JLabel lblQcc = new JLabel("QCC");
		lblQcc.setToolTipText("Quality Control Code");
		lblQcc.setBounds(12, 264, 55, 16);
		panel_Circuit.add(lblQcc);

		textField_DB_Cir_qcc = new JTextField();
		textField_DB_Cir_qcc.setEditable(false);
		textField_DB_Cir_qcc.setBackground(Color.WHITE);
		textField_DB_Cir_qcc.setBounds(85, 262, 114, 20);
		panel_Circuit.add(textField_DB_Cir_qcc);
		textField_DB_Cir_qcc.setColumns(10);

		chckbxAndrewsCmo = new JCheckBox("Andrews or Bolling CMO");
		chckbxAndrewsCmo.setEnabled(false);
		chckbxAndrewsCmo.setToolTipText("Are we the CMO?");
		chckbxAndrewsCmo.setBackground(Color.WHITE);
		chckbxAndrewsCmo.setBounds(217, 208, 166, 24);
		panel_Circuit.add(chckbxAndrewsCmo);

		chckbxAndrewsEndpoint = new JCheckBox("Andrews or Bolling Endpoint");
		chckbxAndrewsEndpoint.setEnabled(false);
		chckbxAndrewsEndpoint.setBackground(Color.WHITE);
		chckbxAndrewsEndpoint
				.setToolTipText("Are we an endpoint of this circuit?");
		chckbxAndrewsEndpoint.setBounds(217, 236, 184, 24);
		panel_Circuit.add(chckbxAndrewsEndpoint);

		JPanel panel_2 = new JPanel();
		panel_2.setToolTipText("");
		panel_2.setBackground(Color.WHITE);
		tabbedPane_TSO_DB.addTab("TSO", null, panel_2, "TSO Table");
		panel_2.setLayout(null);

		JLabel lblTsoNumber = new JLabel("TSO Number");
		lblTsoNumber.setBounds(12, 12, 72, 16);
		panel_2.add(lblTsoNumber);

		textField_DB_TSO_TsoNum = new JTextField();
		textField_DB_TSO_TsoNum.setBackground(Color.WHITE);
		textField_DB_TSO_TsoNum.setEditable(false);
		textField_DB_TSO_TsoNum.setBounds(102, 10, 153, 20);
		panel_2.add(textField_DB_TSO_TsoNum);
		textField_DB_TSO_TsoNum.setColumns(10);

		JLabel lblTsoSuffix = new JLabel("TSO Suffix");
		lblTsoSuffix.setBounds(12, 40, 60, 16);
		panel_2.add(lblTsoSuffix);

		textField_DB_TSO_TsoSuff = new JTextField();
		textField_DB_TSO_TsoSuff.setBackground(Color.WHITE);
		textField_DB_TSO_TsoSuff.setEditable(false);
		textField_DB_TSO_TsoSuff.setBounds(102, 38, 153, 20);
		panel_2.add(textField_DB_TSO_TsoSuff);
		textField_DB_TSO_TsoSuff.setColumns(10);

		JLabel lblAction = new JLabel("Action");
		lblAction.setBounds(12, 68, 36, 16);
		panel_2.add(lblAction);

		textField_DB_TSO_action = new JTextField();
		textField_DB_TSO_action.setBackground(Color.WHITE);
		textField_DB_TSO_action.setEditable(false);
		textField_DB_TSO_action.setBounds(102, 66, 153, 20);
		panel_2.add(textField_DB_TSO_action);
		textField_DB_TSO_action.setColumns(10);

		JLabel lblFullCcsd_1 = new JLabel("Full CCSD");
		lblFullCcsd_1.setBounds(12, 96, 58, 16);
		panel_2.add(lblFullCcsd_1);

		textField_DB_TSO_fullCcsd = new JTextField();
		textField_DB_TSO_fullCcsd.setBackground(Color.WHITE);
		textField_DB_TSO_fullCcsd.setEditable(false);
		textField_DB_TSO_fullCcsd.setBounds(102, 94, 153, 20);
		panel_2.add(textField_DB_TSO_fullCcsd);
		textField_DB_TSO_fullCcsd.setColumns(10);

		JLabel lblSvcDate = new JLabel("Svc Date");
		lblSvcDate.setToolTipText("Service Date");
		lblSvcDate.setBounds(12, 152, 55, 16);
		panel_2.add(lblSvcDate);

		textField_DB_TSO_svcDate = new JTextField();
		textField_DB_TSO_svcDate.setBackground(Color.WHITE);
		textField_DB_TSO_svcDate.setEditable(false);
		textField_DB_TSO_svcDate.setBounds(102, 150, 153, 20);
		panel_2.add(textField_DB_TSO_svcDate);
		textField_DB_TSO_svcDate.setColumns(10);

		chckbx_DB_TSO_crr = new JCheckBox("Completion Report Required?");
		chckbx_DB_TSO_crr.setEnabled(false);
		chckbx_DB_TSO_crr.setBackground(Color.WHITE);
		chckbx_DB_TSO_crr.setBounds(12, 176, 191, 24);
		panel_2.add(chckbx_DB_TSO_crr);

		JLabel lblReportDate = new JLabel("Report Date");
		lblReportDate.setBounds(12, 124, 67, 16);
		panel_2.add(lblReportDate);

		textField_DB_TSO_reportDate = new JTextField();
		textField_DB_TSO_reportDate.setBackground(Color.WHITE);
		textField_DB_TSO_reportDate.setEditable(false);
		textField_DB_TSO_reportDate.setBounds(102, 122, 153, 20);
		panel_2.add(textField_DB_TSO_reportDate);
		textField_DB_TSO_reportDate.setColumns(10);

		textField_DB_TSO_Mrc = new JTextField();
		textField_DB_TSO_Mrc.setForeground(Color.BLACK);
		textField_DB_TSO_Mrc.setBackground(Color.WHITE);
		textField_DB_TSO_Mrc.setEditable(false);
		textField_DB_TSO_Mrc.setBounds(317, 10, 72, 20);
		panel_2.add(textField_DB_TSO_Mrc);
		textField_DB_TSO_Mrc.setColumns(10);

		textField_DB_TSO_Nrc = new JTextField();
		textField_DB_TSO_Nrc.setForeground(Color.BLACK);
		textField_DB_TSO_Nrc.setBackground(Color.WHITE);
		textField_DB_TSO_Nrc.setEditable(false);
		textField_DB_TSO_Nrc.setColumns(10);
		textField_DB_TSO_Nrc.setBounds(317, 38, 72, 20);
		panel_2.add(textField_DB_TSO_Nrc);

		JLabel lblMrc = new JLabel("MRC");
		lblMrc.setToolTipText("Monthly Recurring Cost");
		lblMrc.setBounds(273, 12, 27, 16);
		panel_2.add(lblMrc);

		JLabel lblNrc = new JLabel("NRC");
		lblNrc.setToolTipText("Non Recurring Cost");
		lblNrc.setBounds(273, 40, 27, 16);
		panel_2.add(lblNrc);

		JButton btnTso = new JButton("TSO");
		btnTso.setToolTipText("Telecommunication Service Order");
		btnTso.setBounds(12, 12, 91, 26);
		panel.add(btnTso);
		btnTso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TSO_Text.appear();
			}
		});

		JButton btnHelp = new JButton("Help");
		btnHelp.setToolTipText("Need support?");
		btnHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Help.appear();
			}
		});
		btnHelp.setBounds(12, 385, 91, 26);
		panel.add(btnHelp);

		JButton btnNewButton = new JButton("Commit to DB");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Collection.runClicked && Collection.inputNeeded.size() > 0) {
					String[] temp = Collection.inputNeeded.get(0).split(":");
					TSOPopHelper pop = new TSOPopHelper(temp[1], temp[0]);
					pop.appear();
				} else if (Collection.runClicked) {
					TSOCommit.run();
				} else {
					LogHelper.warning("Please run the program first");
				}
				PopHelper.appear("Database", "Database commit complete");

			}
		});
		btnNewButton.setToolTipText("Commit to the Database");
		btnNewButton.setBounds(267, 435, 111, 60);
		frmTsoHelper.getContentPane().add(btnNewButton);

		JButton btnT = new JButton("Test");
		btnT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// MARK Test mode

				MassInsert.test();

			}
		});
		if (Config.debug) {
			btnT.setVisible(true);
		} else {
			btnT.setVisible(false);
		}
		btnT.setToolTipText("Test");
		btnT.setBounds(429, 452, 59, 26);
		frmTsoHelper.getContentPane().add(btnT);

	}

	protected static JRadioButton getRadioButton_Other() {
		return radioButton_Other;
	}

	protected static JRadioButton getRadioButton_Andrews() {
		return radioButton_Andrews;
	}

	protected static JCheckBox getCheckBox_Ancs() {
		return checkBox_Ancs;
	}

	protected static JCheckBox getCheckBox_Logical() {
		return checkBox_Logical;
	}

	protected static JCheckBox getCheckBox_Crr() {
		return checkBox_Crr;
	}

	protected static JTextArea getTextArea_Comments() {
		return textArea_Comments;
	}

	public static JPanel getPanel_Action() {
		return panel_Action;
	}

	protected static JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	protected static JTextField getTextField_ChfTsoName() {
		return textField_ChfTsoName;
	}

	protected static JTextField getTextField_ChfRoot() {
		return textField_ChfRoot;
	}

	protected static JRadioButton getRdbtn_ChfRoot() {
		return rdbtn_ChfRoot;
	}

	protected static JTextPane getTextPane_ChfCurrent() {
		return textPane_ChfCurrent;
	}

	protected static JTextPane getTextPane_ChfCreating() {
		return textPane_ChfCreating;
	}

	protected static JTextField getTextField_FacitCcsd() {
		return textField_FacitCcsd;
	}

	protected static JTextField getTextField_FacitTsp() {
		return textField_FacitTsp;
	}

	protected static JTextField getTextField_FacitPurpose() {
		return textField_FacitPurpose;
	}

	protected static JTextField getTextField_FacitRate() {
		return textField_FacitRate;
	}

	protected static JTextField getTextField_FacitSvcAvailable() {
		return textField_FacitSvcAvailable;
	}

	protected static JTextField getTextField_FacitFullCcsd() {
		return textField_FacitFullCcsd;
	}

	protected static JTextField getTextField_FacitAction() {
		return textField_FacitAction;
	}

	protected static JTextField getTextField_FacitTsoNum() {
		return textField_FacitTsoNum;
	}

	protected static JTextField getTextField_FacitTsrNum() {
		return textField_FacitTsrNum;
	}

	protected static JTextField getTextField_FacitSvcDate() {
		return textField_FacitSvcDate;
	}

	protected static JTextField getTextField_FacitTsoSubject() {
		return textField_FacitTsoSubject;
	}

	protected static JTextField getTextField_FacitReportDate() {
		return textField_FacitReportDate;
	}

	protected static JCheckBox getChckbx_FacitCrr() {
		return chckbx_FacitCrr;
	}

	protected static JRadioButton getRdbtn_ChfRootCreated() {
		return rdbtn_ChfRootCreated;
	}

	protected static JTextField getTextField_DB_Ci_FullCcsd() {
		return textField_DB_Ci_FullCcsd;
	}

	protected static JTextField getTextField_DB_Cir_trunkId() {
		return textField_DB_Cir_trunkId;
	}

	protected static JTextField getTextField_DB_Cir_fullTsp() {
		return textField_DB_Cir_fullTsp;
	}

	protected static JTextField getTextField_DB_Cir_tsp() {
		return textField_DB_Cir_tsp;
	}

	protected static JTextField getTextField_DB_Cir_security() {
		return textField_DB_Cir_security;
	}

	protected static JTextField getTextField_DB_Cir_rate() {
		return textField_DB_Cir_rate;
	}

	protected static JTextField getTextField_DB_Cir_avail() {
		return textField_DB_Cir_avail;
	}

	protected static JTextField getTextField_DB_Cir_signal() {
		return textField_DB_Cir_signal;
	}

	protected static JTextField getTextField_DB_Cir_qcc() {
		return textField_DB_Cir_qcc;
	}

	protected static JTextField getTextField_DB_Cir_to() {
		return textField_DB_Cir_to;
	}

	protected static JTextField getTextField_DB_Cir_from() {
		return textField_DB_Cir_from;
	}

	protected static JTextField getTextField_DB_Cir_reqDepartment() {
		return textField_DB_Cir_reqDepartment;
	}

	protected static JTextField getTextField_DB_Cir_type() {
		return textField_DB_Cir_type;
	}

	protected static JTextField getTextField_DB_Cir_use() {
		return textField_DB_Cir_use;
	}

	protected static JTextField getTextField_DB_Cir_cmo() {
		return textField_DB_Cir_cmo;
	}

	protected static JTextField getTextField_DB_Cir_cmoDsn() {
		return textField_DB_Cir_cmoDsn;
	}

	protected static JTextField getTextField_DB_Cir_cmoComm() {
		return textField_DB_Cir_cmoComm;
	}

	protected static JCheckBox getChckbxAndrewsCmo() {
		return chckbxAndrewsCmo;
	}

	protected static JCheckBox getChckbxAndrewsEndpoint() {
		return chckbxAndrewsEndpoint;
	}

	protected static JTextField getTextField_DB_Cir_flow() {
		return textField_DB_Cir_flow;
	}

	protected static JTextField getTextField_DB_TSO_TsoNum() {
		return textField_DB_TSO_TsoNum;
	}

	protected static JTextField getTextField_DB_TSO_TsoSuff() {
		return textField_DB_TSO_TsoSuff;
	}

	protected static JTextField getTextField_DB_TSO_action() {
		return textField_DB_TSO_action;
	}

	protected static JTextField getTextField_DB_TSO_fullCcsd() {
		return textField_DB_TSO_fullCcsd;
	}

	protected static JTextField getTextField_DB_TSO_svcDate() {
		return textField_DB_TSO_svcDate;
	}

	protected static JCheckBox getChckbx_DB_TSO_crr() {
		return chckbx_DB_TSO_crr;
	}

	protected static JTextField getTextField_chfLink() {
		return textField_chfLink;
	}

	protected static JTextField getTextField_DB_TSO_reportDate() {
		return textField_DB_TSO_reportDate;
	}

	protected static JFrame getFrmTsoHelper() {
		return frmTsoHelper;
	}

	protected static JTextField getTextField_FacitAmending() {
		return textField_FacitAmending;
	}

	protected static JTextField getTextField_FacitTrunkID() {
		return textField_FacitTrunkID;
	}

	protected static JCheckBox getChckbx_FacitIsTrunk() {
		return chckbx_FacitIsTrunk;
	}

	protected static JTextField getTextField_DB_TSO_Mrc() {
		return textField_DB_TSO_Mrc;
	}

	protected static JTextField getTextField_DB_TSO_Nrc() {
		return textField_DB_TSO_Nrc;
	}
}
