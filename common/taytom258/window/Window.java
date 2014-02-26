package taytom258.window;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import taytom258.config.Config;
import taytom258.core.util.LogHelper;
import taytom258.lib.Reference;
import taytom258.show.ShowCHF;
import taytom258.show.ShowTracker;
import taytom258.tso.TSOChange;
import taytom258.tso.TSODisco;
import taytom258.tso.TSOStart;
import taytom258.window.core.WindowCore;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class Window {

	protected JFrame frmTsoHelper;
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
	private static JTextField textField_TrackerFullCcsd;
	private static JTextField textField_TrackerChfLink;
	private static JTextField textField_TrackerCmo;
	private static JTextField textField_TrackerCmoDsn;
	private static JTextField textField_TrackerCmoComm;
	private static JTextField textField_TrackerAction;
	private static JTextField textField_TrackerSvcDate;
	private static JTextField textField_ChfTsoName;
	private static JTextField textField_ChfChfLink;
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
	private static JTextPane textPane_TrackerComment;
	private static JRadioButton rdbtn_ChfRoot;
	private static JTextPane textPane_ChfCurrent;
	private static JTextPane textPane_ChfCreating;
	private static JTextArea textArea_FacitTsoState;
	private static JCheckBox chckbx_FacitCrr;
	
	/**
	 * Launch the application.
	 */
	public static void appear() {
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
		frmTsoHelper.setTitle(Reference.APPLICATION_NAME+" "+Reference.APPLICATION_VERSION);
		frmTsoHelper.setBounds(100, 100, 647, 535);
		frmTsoHelper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTsoHelper.getContentPane().setLayout(null);
		frmTsoHelper.setResizable(false);
		
		JButton button_EnterTso = new JButton("Enter TSO");
		button_EnterTso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Text.appear();
			}
		});
		button_EnterTso.setBounds(10, 460, 91, 26);
		frmTsoHelper.getContentPane().add(button_EnterTso);
		
		JButton button_Settings = new JButton("Settings");
		button_Settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings.appear();
			}
		});
		button_Settings.setBounds(109, 460, 81, 26);
		frmTsoHelper.getContentPane().add(button_Settings);
		
		JButton button_Run = new JButton("Run");
		button_Run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WindowCore.collect.init();
				ShowTracker.show();
				ShowCHF.show();
			}
		});
		button_Run.setBounds(202, 460, 56, 26);
		frmTsoHelper.getContentPane().add(button_Run);
		
		JButton button_Exit = new JButton("Exit");
		button_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogHelper.debug("Terminate JVM");
				System.exit(0);
			}
		});
		button_Exit.setBounds(530, 460, 91, 26);
		frmTsoHelper.getContentPane().add(button_Exit);
		
		JProgressBar progressBar = new JProgressBar(0, 1000);
		progressBar.setValue(0);
		progressBar.setBounds(10, 435, 611, 14);
		frmTsoHelper.getContentPane().add(progressBar);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 641, 423);
		frmTsoHelper.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane);
		
		JPanel panel_general = new JPanel();
		panel_general.setLayout(null);
		tabbedPane.addTab("General", null, panel_general, null);
		
		textField_TsoSubject = new JTextField();
		textField_TsoSubject.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
					WindowCore.collect.getAction();
			}
		});
		textField_TsoSubject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.isPopupTrigger()){
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}else if(e.getButton() == 1 && Config.autoSelection){
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
				if(e.isPopupTrigger()){
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}else if(e.getButton() == 1 && Config.autoSelection){
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
				if(e.isPopupTrigger()){
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}else if(e.getButton() == 1 && Config.autoSelection){
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
				if(e.isPopupTrigger() && textField_CmoName.isEnabled()){
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}else if(e.getButton() == 1 && Config.autoSelection && textField_CmoName.isEnabled()){
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
		panel_6.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Extra Comments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(8, 266, 283, 113);
		panel_general.add(panel_6);
		
		textArea_Comments = new JTextArea();
		textArea_Comments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.isPopupTrigger()){
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
				if(e.isPopupTrigger() && textField_Dsn.isEnabled()){
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}else if(e.getButton() == 1 && Config.autoSelection && textField_Dsn.isEnabled()){
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
				if(e.isPopupTrigger() && textField_Comm.isEnabled()){
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}else if(e.getButton() == 1 && Config.autoSelection && textField_Comm.isEnabled()){
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
		TSOStart.buildPanel();
		TSOChange.buildPanel();
		TSODisco.buildPanel();

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane_1);
		
		JPanel panel_Tracker = new JPanel();
		panel_Tracker.setBackground(Color.WHITE);
		tabbedPane_1.addTab("Tracker", null, panel_Tracker, null);
		tabbedPane_1.setEnabledAt(0, true);
		panel_Tracker.setLayout(null);
		
		JLabel label_TrackerFullCcsd = new JLabel("Full CCSD");
		label_TrackerFullCcsd.setBounds(17, 46, 55, 16);
		panel_Tracker.add(label_TrackerFullCcsd);
		
		JLabel label_TrackerChfLink = new JLabel("CHF Link");
		label_TrackerChfLink.setBounds(17, 78, 55, 16);
		panel_Tracker.add(label_TrackerChfLink);
		
		JLabel label_2 = new JLabel("CMO");
		label_2.setBounds(17, 108, 27, 16);
		panel_Tracker.add(label_2);
		
		textField_TrackerFullCcsd = new JTextField();
		textField_TrackerFullCcsd.setForeground(Color.BLACK);
		textField_TrackerFullCcsd.setEditable(false);
		textField_TrackerFullCcsd.setColumns(10);
		textField_TrackerFullCcsd.setBackground(Color.WHITE);
		textField_TrackerFullCcsd.setBounds(178, 44, 114, 20);
		panel_Tracker.add(textField_TrackerFullCcsd);
		
		textField_TrackerChfLink = new JTextField();
		textField_TrackerChfLink.setForeground(Color.BLACK);
		textField_TrackerChfLink.setEditable(false);
		textField_TrackerChfLink.setColumns(10);
		textField_TrackerChfLink.setBackground(Color.WHITE);
		textField_TrackerChfLink.setBounds(178, 76, 114, 20);
		panel_Tracker.add(textField_TrackerChfLink);
		
		textField_TrackerCmo = new JTextField();
		textField_TrackerCmo.setForeground(Color.BLACK);
		textField_TrackerCmo.setEditable(false);
		textField_TrackerCmo.setColumns(10);
		textField_TrackerCmo.setBackground(Color.WHITE);
		textField_TrackerCmo.setBounds(178, 106, 114, 20);
		panel_Tracker.add(textField_TrackerCmo);
		
		JLabel label_TrackerCmoDsn = new JLabel("CMO DSN");
		label_TrackerCmoDsn.setBounds(17, 140, 55, 16);
		panel_Tracker.add(label_TrackerCmoDsn);
		
		JLabel label_TrackerCmoComm = new JLabel("CMO Comm");
		label_TrackerCmoComm.setBounds(17, 172, 67, 16);
		panel_Tracker.add(label_TrackerCmoComm);
		
		textField_TrackerCmoDsn = new JTextField();
		textField_TrackerCmoDsn.setForeground(Color.BLACK);
		textField_TrackerCmoDsn.setEditable(false);
		textField_TrackerCmoDsn.setColumns(10);
		textField_TrackerCmoDsn.setBackground(Color.WHITE);
		textField_TrackerCmoDsn.setBounds(178, 138, 114, 20);
		panel_Tracker.add(textField_TrackerCmoDsn);
		
		textField_TrackerCmoComm = new JTextField();
		textField_TrackerCmoComm.setForeground(Color.BLACK);
		textField_TrackerCmoComm.setEditable(false);
		textField_TrackerCmoComm.setColumns(10);
		textField_TrackerCmoComm.setBackground(Color.WHITE);
		textField_TrackerCmoComm.setBounds(178, 170, 114, 20);
		panel_Tracker.add(textField_TrackerCmoComm);
		
		JPanel panel_TrackerComment = new JPanel();
		panel_TrackerComment.setLayout(null);
		panel_TrackerComment.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Comment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TrackerComment.setBackground(Color.WHITE);
		panel_TrackerComment.setBounds(12, 234, 280, 147);
		panel_Tracker.add(panel_TrackerComment);
		
		textPane_TrackerComment = new JTextPane();
		textPane_TrackerComment.setEditable(false);
		textPane_TrackerComment.setBackground(Color.WHITE);
		textPane_TrackerComment.setBounds(12, 22, 256, 113);
		panel_TrackerComment.add(textPane_TrackerComment);
		
		JLabel label_TrackerAction = new JLabel("Action");
		label_TrackerAction.setBounds(17, 14, 36, 16);
		panel_Tracker.add(label_TrackerAction);
		
		textField_TrackerAction = new JTextField();
		textField_TrackerAction.setForeground(Color.BLACK);
		textField_TrackerAction.setEditable(false);
		textField_TrackerAction.setColumns(10);
		textField_TrackerAction.setBackground(Color.WHITE);
		textField_TrackerAction.setBounds(178, 12, 114, 20);
		panel_Tracker.add(textField_TrackerAction);
		
		JLabel label_TrackerSvcDate = new JLabel("Service Date");
		label_TrackerSvcDate.setBounds(17, 202, 72, 16);
		panel_Tracker.add(label_TrackerSvcDate);
		
		textField_TrackerSvcDate = new JTextField();
		textField_TrackerSvcDate.setForeground(Color.BLACK);
		textField_TrackerSvcDate.setEditable(false);
		textField_TrackerSvcDate.setColumns(10);
		textField_TrackerSvcDate.setBackground(Color.WHITE);
		textField_TrackerSvcDate.setBounds(178, 200, 114, 20);
		panel_Tracker.add(textField_TrackerSvcDate);
		
		JPanel panel_Chf = new JPanel();
		panel_Chf.setLayout(null);
		panel_Chf.setBackground(Color.WHITE);
		tabbedPane_1.addTab("CHF", null, panel_Chf, null);
		tabbedPane_1.setEnabledAt(1, true);
		
		JLabel label_ChfTsoName = new JLabel("TSO Name");
		label_ChfTsoName.setBounds(12, 12, 60, 16);
		panel_Chf.add(label_ChfTsoName);
		
		JLabel label_ChfChfLink = new JLabel("CHF Link");
		label_ChfChfLink.setBounds(12, 40, 49, 16);
		panel_Chf.add(label_ChfChfLink);
		
		textField_ChfTsoName = new JTextField();
		textField_ChfTsoName.setBackground(Color.WHITE);
		textField_ChfTsoName.setEditable(false);
		textField_ChfTsoName.setBounds(102, 12, 201, 20);
		panel_Chf.add(textField_ChfTsoName);
		textField_ChfTsoName.setColumns(10);
		
		textField_ChfChfLink = new JTextField();
		textField_ChfChfLink.setBackground(Color.WHITE);
		textField_ChfChfLink.setEditable(false);
		textField_ChfChfLink.setBounds(102, 40, 201, 20);
		panel_Chf.add(textField_ChfChfLink);
		textField_ChfChfLink.setColumns(10);
		
		JButton button_ChfCreate = new JButton("Create");
		button_ChfCreate.setBounds(102, 125, 111, 26);
		panel_Chf.add(button_ChfCreate);
		
		JPanel panel_ChfCreating = new JPanel();
		panel_ChfCreating.setBackground(Color.WHITE);
		panel_ChfCreating.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Creating", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_ChfCreating.setBounds(175, 164, 133, 224);
		panel_Chf.add(panel_ChfCreating);
		panel_ChfCreating.setLayout(null);
		
		textPane_ChfCreating = new JTextPane();
		textPane_ChfCreating.setEditable(false);
		textPane_ChfCreating.setBounds(12, 22, 109, 190);
		panel_ChfCreating.add(textPane_ChfCreating);
		
		JPanel panel_ChfCurrent = new JPanel();
		panel_ChfCurrent.setBackground(Color.WHITE);
		panel_ChfCurrent.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Current", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_ChfCurrent.setBounds(7, 164, 133, 224);
		panel_Chf.add(panel_ChfCurrent);
		panel_ChfCurrent.setLayout(null);
		
		textPane_ChfCurrent = new JTextPane();
		textPane_ChfCurrent.setEditable(false);
		textPane_ChfCurrent.setBounds(12, 22, 109, 190);
		panel_ChfCurrent.add(textPane_ChfCurrent);
		
		rdbtn_ChfRoot = new JRadioButton("Root Exists");
		rdbtn_ChfRoot.setEnabled(false);
		rdbtn_ChfRoot.setBackground(Color.WHITE);
		rdbtn_ChfRoot.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtn_ChfRoot.setBounds(113, 93, 89, 24);
		panel_Chf.add(rdbtn_ChfRoot);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tabbedPane_1.addTab("Facit", null, scrollPane, null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		panel_1.setPreferredSize(new Dimension(0, 584));
		
		JLabel label_FacitCcsd = new JLabel("CCSD");
		label_FacitCcsd.setBounds(12, 12, 32, 16);
		panel_1.add(label_FacitCcsd);
		
		JLabel label_FacitTsp = new JLabel("TSP");
		label_FacitTsp.setBounds(12, 40, 23, 16);
		panel_1.add(label_FacitTsp);
		
		JLabel label_FacitPurpose = new JLabel("Purpose");
		label_FacitPurpose.setBounds(12, 68, 48, 16);
		panel_1.add(label_FacitPurpose);
		
		JLabel label_FacitRate = new JLabel("Rate");
		label_FacitRate.setBounds(12, 96, 26, 16);
		panel_1.add(label_FacitRate);
		
		JLabel label_FacitSvcAvail = new JLabel("Service Availability");
		label_FacitSvcAvail.setBounds(12, 124, 106, 16);
		panel_1.add(label_FacitSvcAvail);
		
		JLabel label_FacitFullCcsd = new JLabel("Full CCSD");
		label_FacitFullCcsd.setBounds(12, 152, 54, 16);
		panel_1.add(label_FacitFullCcsd);
		
		JLabel label_FacitAction = new JLabel("Action");
		label_FacitAction.setBounds(12, 180, 36, 16);
		panel_1.add(label_FacitAction);
		
		JLabel label_FacitTsoNum = new JLabel("TSO Number");
		label_FacitTsoNum.setBounds(12, 208, 72, 16);
		panel_1.add(label_FacitTsoNum);
		
		JLabel label_FacitTsrNum = new JLabel("TSR Number");
		label_FacitTsrNum.setBounds(12, 236, 71, 16);
		panel_1.add(label_FacitTsrNum);
		
		JLabel label_FacitServiceDate = new JLabel("Service Date");
		label_FacitServiceDate.setBounds(12, 264, 72, 16);
		panel_1.add(label_FacitServiceDate);
		
		JLabel label_FacitTsoSubject = new JLabel("TSO Subject");
		label_FacitTsoSubject.setBounds(12, 292, 70, 16);
		panel_1.add(label_FacitTsoSubject);
		
		JLabel label_FacitReportDate = new JLabel("Report Date");
		label_FacitReportDate.setBounds(12, 320, 67, 16);
		panel_1.add(label_FacitReportDate);
		
		JButton btn_FacitTsoText = new JButton("TSO Text");
		btn_FacitTsoText.setBounds(99, 376, 98, 26);
		panel_1.add(btn_FacitTsoText);
		
		JPanel panel_FacitTsoState = new JPanel();
		panel_FacitTsoState.setBackground(Color.WHITE);
		panel_FacitTsoState.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "TSO Statement", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_FacitTsoState.setBounds(5, 430, 283, 126);
		panel_1.add(panel_FacitTsoState);
		panel_FacitTsoState.setLayout(null);
		
		textArea_FacitTsoState = new JTextArea();
		textArea_FacitTsoState.setBounds(5, 21, 273, 100);
		panel_FacitTsoState.add(textArea_FacitTsoState);
		
		textField_FacitCcsd = new JTextField();
		textField_FacitCcsd.setBackground(Color.WHITE);
		textField_FacitCcsd.setEditable(false);
		textField_FacitCcsd.setBounds(174, 10, 114, 20);
		panel_1.add(textField_FacitCcsd);
		textField_FacitCcsd.setColumns(10);
		
		textField_FacitTsp = new JTextField();
		textField_FacitTsp.setBackground(Color.WHITE);
		textField_FacitTsp.setEditable(false);
		textField_FacitTsp.setBounds(174, 38, 114, 20);
		panel_1.add(textField_FacitTsp);
		textField_FacitTsp.setColumns(10);
		
		textField_FacitPurpose = new JTextField();
		textField_FacitPurpose.setBackground(Color.WHITE);
		textField_FacitPurpose.setEditable(false);
		textField_FacitPurpose.setBounds(174, 66, 114, 20);
		panel_1.add(textField_FacitPurpose);
		textField_FacitPurpose.setColumns(10);
		
		textField_FacitRate = new JTextField();
		textField_FacitRate.setBackground(Color.WHITE);
		textField_FacitRate.setEditable(false);
		textField_FacitRate.setBounds(174, 94, 114, 20);
		panel_1.add(textField_FacitRate);
		textField_FacitRate.setColumns(10);
		
		textField_FacitSvcAvailable = new JTextField();
		textField_FacitSvcAvailable.setBackground(Color.WHITE);
		textField_FacitSvcAvailable.setEditable(false);
		textField_FacitSvcAvailable.setBounds(174, 122, 114, 20);
		panel_1.add(textField_FacitSvcAvailable);
		textField_FacitSvcAvailable.setColumns(10);
		
		textField_FacitFullCcsd = new JTextField();
		textField_FacitFullCcsd.setBackground(Color.WHITE);
		textField_FacitFullCcsd.setEditable(false);
		textField_FacitFullCcsd.setBounds(174, 150, 114, 20);
		panel_1.add(textField_FacitFullCcsd);
		textField_FacitFullCcsd.setColumns(10);
		
		textField_FacitAction = new JTextField();
		textField_FacitAction.setBackground(Color.WHITE);
		textField_FacitAction.setEditable(false);
		textField_FacitAction.setBounds(174, 178, 114, 20);
		panel_1.add(textField_FacitAction);
		textField_FacitAction.setColumns(10);
		
		textField_FacitTsoNum = new JTextField();
		textField_FacitTsoNum.setBackground(Color.WHITE);
		textField_FacitTsoNum.setEditable(false);
		textField_FacitTsoNum.setBounds(174, 206, 114, 20);
		panel_1.add(textField_FacitTsoNum);
		textField_FacitTsoNum.setColumns(10);
		
		textField_FacitTsrNum = new JTextField();
		textField_FacitTsrNum.setBackground(Color.WHITE);
		textField_FacitTsrNum.setEditable(false);
		textField_FacitTsrNum.setBounds(174, 234, 114, 20);
		panel_1.add(textField_FacitTsrNum);
		textField_FacitTsrNum.setColumns(10);
		
		textField_FacitSvcDate = new JTextField();
		textField_FacitSvcDate.setBackground(Color.WHITE);
		textField_FacitSvcDate.setEditable(false);
		textField_FacitSvcDate.setBounds(174, 262, 114, 20);
		panel_1.add(textField_FacitSvcDate);
		textField_FacitSvcDate.setColumns(10);
		
		textField_FacitTsoSubject = new JTextField();
		textField_FacitTsoSubject.setBackground(Color.WHITE);
		textField_FacitTsoSubject.setEditable(false);
		textField_FacitTsoSubject.setBounds(174, 290, 114, 20);
		panel_1.add(textField_FacitTsoSubject);
		textField_FacitTsoSubject.setColumns(10);
		
		textField_FacitReportDate = new JTextField();
		textField_FacitReportDate.setBackground(Color.WHITE);
		textField_FacitReportDate.setEditable(false);
		textField_FacitReportDate.setBounds(174, 318, 114, 20);
		panel_1.add(textField_FacitReportDate);
		textField_FacitReportDate.setColumns(10);
		
		chckbx_FacitCrr = new JCheckBox("Completion Report Required");
		chckbx_FacitCrr.setEnabled(false);
		chckbx_FacitCrr.setBackground(Color.WHITE);
		chckbx_FacitCrr.setBounds(58, 349, 184, 24);
		panel_1.add(chckbx_FacitCrr);
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
	protected static JTextField getTextField_TrackerAction() {
		return textField_TrackerAction;
	}
	protected static JTextField getTextField_TrackerFullCcsd() {
		return textField_TrackerFullCcsd;
	}
	protected static JTextField getTextField_TrackerChfLink() {
		return textField_TrackerChfLink;
	}
	protected static JTextField getTextField_TrackerCmo() {
		return textField_TrackerCmo;
	}
	protected static JTextField getTextField_TrackerCmoDsn() {
		return textField_TrackerCmoDsn;
	}
	protected static JTextField getTextField_TrackerCmoComm() {
		return textField_TrackerCmoComm;
	}
	protected static JTextField getTextField_TrackerSvcDate() {
		return textField_TrackerSvcDate;
	}
	protected static JTextPane getTextPane_TrackerComment() {
		return textPane_TrackerComment;
	}
	protected static JTextField getTextField_ChfTsoName() {
		return textField_ChfTsoName;
	}
	protected static JTextField getTextField_ChfChfLink() {
		return textField_ChfChfLink;
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
	protected static JTextArea getTextArea_FacitTsoState() {
		return textArea_FacitTsoState;
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
}
