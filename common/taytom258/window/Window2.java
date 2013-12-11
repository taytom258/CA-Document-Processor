package taytom258.window;

import java.awt.CardLayout;
import java.awt.Color;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import taytom258.core.util.LogHelper;
import taytom258.lib.Reference;
import taytom258.tso.*;
import taytom258.window.core.WindowCore;
import javax.swing.JTextPane;

public class Window2 {

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
	private JTextField textField_TrackerFullCcsd;
	private JTextField textField_TrackerChfLink;
	private JTextField textField_TrackerCmo;
	private JTextField textField_TrackerCmoDsn;
	private JTextField textField_TrackerCmoComm;
	private JTextField textField_TrackerAction;
	private JTextField textField_TrackerSvcDate;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	/**
	 * Launch the application.
	 */
	public static void appear() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window2 window = new Window2();
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
	public Window2() {
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
				WindowCore.collect.genCollect();
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
		textField_TsoSubject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.isPopupTrigger()){
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}else if(e.getButton() == 1){
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
				}else if(e.getButton() == 1){
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
				}else if(e.getButton() == 1){
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
				}else if(e.getButton() == 1 && textField_CmoName.isEnabled()){
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
				}else if(e.getButton() == 1 && textField_Dsn.isEnabled()){
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
				}else if(e.getButton() == 1 && textField_Comm.isEnabled()){
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
		
		JTextPane textPane_TrackerComment = new JTextPane();
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
		tabbedPane_1.addTab("New tab", null, panel_Chf, null);
		
		JLabel label = new JLabel("Full CCSD");
		label.setBounds(17, 46, 55, 16);
		panel_Chf.add(label);
		
		JLabel label_1 = new JLabel("CHF Link");
		label_1.setBounds(17, 78, 55, 16);
		panel_Chf.add(label_1);
		
		JLabel label_3 = new JLabel("CMO");
		label_3.setBounds(17, 108, 27, 16);
		panel_Chf.add(label_3);
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(178, 44, 114, 20);
		panel_Chf.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.BLACK);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(178, 76, 114, 20);
		panel_Chf.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setForeground(Color.BLACK);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(178, 106, 114, 20);
		panel_Chf.add(textField_2);
		
		JLabel label_7 = new JLabel("CMO DSN");
		label_7.setBounds(17, 140, 55, 16);
		panel_Chf.add(label_7);
		
		JLabel label_8 = new JLabel("CMO Comm");
		label_8.setBounds(17, 172, 67, 16);
		panel_Chf.add(label_8);
		
		textField_3 = new JTextField();
		textField_3.setForeground(Color.BLACK);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBackground(Color.WHITE);
		textField_3.setBounds(178, 138, 114, 20);
		panel_Chf.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setForeground(Color.BLACK);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBackground(Color.WHITE);
		textField_4.setBounds(178, 170, 114, 20);
		panel_Chf.add(textField_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Comment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(12, 234, 280, 147);
		panel_Chf.add(panel_2);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(Color.WHITE);
		textPane.setBounds(12, 22, 256, 113);
		panel_2.add(textPane);
		
		JLabel label_9 = new JLabel("Action");
		label_9.setBounds(17, 14, 36, 16);
		panel_Chf.add(label_9);
		
		textField_5 = new JTextField();
		textField_5.setForeground(Color.BLACK);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBackground(Color.WHITE);
		textField_5.setBounds(178, 12, 114, 20);
		panel_Chf.add(textField_5);
		
		JLabel label_10 = new JLabel("Service Date");
		label_10.setBounds(17, 202, 72, 16);
		panel_Chf.add(label_10);
		
		textField_6 = new JTextField();
		textField_6.setForeground(Color.BLACK);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBackground(Color.WHITE);
		textField_6.setBounds(178, 200, 114, 20);
		panel_Chf.add(textField_6);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane_1.addTab("Facit", null, panel_5, null);
		tabbedPane_1.setEnabledAt(2, true);
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
}
