package taytom258.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.lib.Reference;
import taytom258.lib.Strings;
import taytom258.tso.start.FolderTree;
import taytom258.window.core.ProgressBarCore;
import taytom258.window.core.WindowCore;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollBar;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

public class Window {

	private JProgressBar progressBar;
	private JButton btnRun;
	
	public static JRadioButton rdbtnOther;
	public static JRadioButton rdbtnAndrews;
	public static JCheckBox chckbxLogical;
	public static JCheckBox chckbxComReport;
	
	public static JToggleButton tglbtnCHF;
	public static JCheckBox chckbxSams;
	public static JCheckBox chckbxAnalog;
	public static JCheckBox chckbxPassthrough;
	
	
	private JFrame frmTsoHelper;
	public static JTextField textField;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_5;
	public static JTextArea textArea;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textDSN;
	private JTextField txtCommercial;
	private JTextField txtFullCcsd;
	private JTextField txtAction;
	private JTextField txtChfLink;
	private JTextField txtCmo;
	private JTextField txtCmoDsn;
	private JTextField txtCmoComm;
	private JTextField txtTsoNamed;
	private JTextField txtCcsdlast;
	private JTextField txtTsp;
	private JTextField txtRate;
	private JTextField txtPupose;
	private JTextField txtServiceavai;
	private JTextField txtCcsd;
	private JTextField txtChfLink_1;
	
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
		frmTsoHelper.setResizable(false);
		frmTsoHelper.setTitle("TSO Helper");
		frmTsoHelper.setBounds(100, 100, 647, 535);
		frmTsoHelper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frmTsoHelper.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setBounds(10, 11, 302, 416);
		layeredPane.add(tabbedPane);
		
		final JPanel panel = new JPanel();
		tabbedPane.addTab("General", null, panel, null);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setToolTipText("Enter TSO Subject Here");
		textField.setBounds(171, 12, 114, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("TSO Subject");
		lblNewLabel.setBounds(12, 14, 70, 16);
		panel.add(lblNewLabel);
		
		textField_5 = new JTextField();
		textField_5.setToolTipText("Enter Full CCSD Here");
		textField_5.setBounds(171, 44, 114, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblFullCcsd = new JLabel("Full CCSD");
		lblFullCcsd.setBounds(12, 46, 55, 16);
		panel.add(lblFullCcsd);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Enter Service Date of TSO Here");
		textField_1.setText(Strings.DATE_FORMAT);
		textField_1.setBounds(171, 76, 114, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_1.setText("");
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (textField_1.getText().equals("")){
					textField_1.setText(Strings.DATE_FORMAT);
				}
			}
		});
		
		JLabel lblServiceDate = new JLabel("Service Date");
		lblServiceDate.setBounds(12, 74, 72, 16);
		panel.add(lblServiceDate);
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("Enter Other CMO Here");
		textField_2.setBounds(158, 137, 114, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setEnabled(false);
		textField_2.setText(null);
		textField_2.setBackground(Color.LIGHT_GRAY);
		
		rdbtnAndrews = new JRadioButton("Andrews");
		rdbtnAndrews.setBounds(148, 104, 76, 24);
		rdbtnAndrews.setSelected(true);
		panel.add(rdbtnAndrews);
		
		rdbtnOther = new JRadioButton("Other");
		rdbtnOther.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnOther.isSelected()){
					textField_2.setEnabled(true);
					textField_2.setBackground(Color.WHITE);
					textDSN.setEnabled(true);
					txtCommercial.setEnabled(true);
					textDSN.setBackground(Color.WHITE);
					txtCommercial.setBackground(Color.WHITE);
					
				}else{
					textField_2.setEnabled(false);
					textField_2.setText(null);
					textField_2.setBackground(Color.LIGHT_GRAY);
					textDSN.setEnabled(false);
					textDSN.setText(null);
					textDSN.setBackground(Color.LIGHT_GRAY);
					txtCommercial.setEnabled(false);
					txtCommercial.setText(null);
					txtCommercial.setBackground(Color.LIGHT_GRAY);
				}
			}
		});
		rdbtnOther.setBounds(228, 104, 57, 24);
		panel.add(rdbtnOther);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnAndrews);
		group.add(rdbtnOther);
		
		JLabel lblNewLabel_1 = new JLabel("CMO");
		lblNewLabel_1.setBounds(12, 108, 27, 16);
		panel.add(lblNewLabel_1);
		
		chckbxComReport = new JCheckBox("Completion Report Required?");
		chckbxComReport.setBounds(94, 236, 191, 24);
		panel.add(chckbxComReport);
		
		chckbxLogical = new JCheckBox("Logical?");
		chckbxLogical.setBounds(12, 236, 73, 24);
		panel.add(chckbxLogical);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Extra Comments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(7, 268, 283, 113);
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setToolTipText("Enter Extra Comments Here");
		textArea.setBounds(12, 23, 259, 78);
		panel_7.add(textArea);
		
		textDSN = new JTextField();
		textDSN.setBounds(158, 169, 114, 20);
		panel.add(textDSN);
		textDSN.setColumns(10);
		textDSN.setEnabled(false);
		textDSN.setText(null);
		textDSN.setBackground(Color.LIGHT_GRAY);
		
		txtCommercial = new JTextField();
		txtCommercial.setBounds(158, 201, 114, 20);
		panel.add(txtCommercial);
		txtCommercial.setColumns(10);
		txtCommercial.setEnabled(false);
		txtCommercial.setText(null);
		txtCommercial.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblOtherCmoName = new JLabel("CMO Name");
		lblOtherCmoName.setFont(new Font("Dialog", Font.BOLD, 10));
		lblOtherCmoName.setBounds(85, 141, 55, 14);
		panel.add(lblOtherCmoName);
		
		JLabel lblOtherCmoDsn = new JLabel("DSN");
		lblOtherCmoDsn.setFont(new Font("Dialog", Font.BOLD, 10));
		lblOtherCmoDsn.setBounds(119, 173, 21, 14);
		panel.add(lblOtherCmoDsn);
		
		JLabel lblComm = new JLabel("Comm");
		lblComm.setFont(new Font("Dialog", Font.BOLD, 10));
		lblComm.setBounds(109, 205, 31, 14);
		panel.add(lblComm);
		
		FolderTree.defaultInit();
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Start", null, panel_2, null);
		panel_2.setLayout(null);
		
		chckbxSams = new JCheckBox("Sams?");
		chckbxSams.setBounds(12, 25, 65, 24);
		panel_2.add(chckbxSams);
		
		chckbxAnalog = new JCheckBox("Analog?");
		chckbxAnalog.setBounds(12, 53, 71, 24);
		panel_2.add(chckbxAnalog);
		
		chckbxPassthrough = new JCheckBox("Passthrough?");
		chckbxPassthrough.setBounds(12, 81, 105, 24);
		panel_2.add(chckbxPassthrough);
		
		tglbtnCHF = new JToggleButton("CHF Creation Active");
		tglbtnCHF.setBounds(140, 12, 145, 26);
		panel_2.add(tglbtnCHF);
		
		textField_6 = new JTextField();
		textField_6.setBounds(171, 113, 114, 20);
		panel_2.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Data Rate");
		lblNewLabel_3.setBounds(12, 115, 55, 16);
		panel_2.add(lblNewLabel_3);
		
		textField_7 = new JTextField();
		textField_7.setBounds(171, 141, 114, 20);
		panel_2.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblServiceAvailability = new JLabel("Service Availability");
		lblServiceAvailability.setBounds(12, 143, 106, 16);
		panel_2.add(lblServiceAvailability);
		
		JLabel lblTsp = new JLabel("TSP");
		lblTsp.setBounds(12, 171, 23, 16);
		panel_2.add(lblTsp);
		
		JComboBox<?> comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TSP 1", "TSP 2", "TSP 3", "TSP 4", "TSP 5", "N/A"}));
		comboBox.setBounds(171, 167, 114, 25);
		panel_2.add(comboBox);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Change", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNyi = new JLabel("NYI");
		lblNyi.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNyi.setBounds(138, 158, 39, 32);
		panel_3.add(lblNyi);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Disco", null, panel_6, null);
		panel_6.setLayout(null);
		
		JLabel label_1 = new JLabel("NYI");
		label_1.setFont(new Font("Dialog", Font.BOLD, 24));
		label_1.setBounds(142, 153, 39, 32);
		panel_6.add(label_1);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(SwingConstants.TOP);
		tabbedPane_1.setBounds(322, 11, 299, 416);
		layeredPane.add(tabbedPane_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane_1.addTab("Tracker", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblFullCcsd_1 = new JLabel("Full CCSD");
		lblFullCcsd_1.setBounds(12, 12, 55, 16);
		panel_1.add(lblFullCcsd_1);
		
		JLabel lblAction = new JLabel("Action");
		lblAction.setBounds(12, 40, 55, 16);
		panel_1.add(lblAction);
		
		JLabel lblChfLink = new JLabel("CHF Link");
		lblChfLink.setBounds(12, 68, 55, 16);
		panel_1.add(lblChfLink);
		
		JLabel lblCmo = new JLabel("CMO");
		lblCmo.setBounds(12, 96, 55, 16);
		panel_1.add(lblCmo);
		
		txtFullCcsd = new JTextField();
		txtFullCcsd.setEditable(false);
		txtFullCcsd.setForeground(Color.BLACK);
		txtFullCcsd.setBackground(Color.WHITE);
		txtFullCcsd.setBounds(168, 10, 114, 20);
		panel_1.add(txtFullCcsd);
		txtFullCcsd.setColumns(10);
		
		txtAction = new JTextField();
		txtAction.setEditable(false);
		txtAction.setForeground(Color.BLACK);
		txtAction.setBackground(Color.WHITE);
		txtAction.setBounds(168, 38, 114, 20);
		panel_1.add(txtAction);
		txtAction.setColumns(10);
		
		txtChfLink = new JTextField();
		txtChfLink.setEditable(false);
		txtChfLink.setForeground(Color.BLACK);
		txtChfLink.setBackground(Color.WHITE);
		txtChfLink.setBounds(168, 66, 114, 20);
		panel_1.add(txtChfLink);
		txtChfLink.setColumns(10);
		
		txtCmo = new JTextField();
		txtCmo.setEditable(false);
		txtCmo.setForeground(Color.BLACK);
		txtCmo.setBackground(Color.WHITE);
		txtCmo.setBounds(168, 94, 114, 20);
		panel_1.add(txtCmo);
		txtCmo.setColumns(10);
		
		JLabel lblCmoDsn = new JLabel("CMO DSN");
		lblCmoDsn.setBounds(12, 124, 55, 16);
		panel_1.add(lblCmoDsn);
		
		JLabel lblCmoComm = new JLabel("CMO Comm");
		lblCmoComm.setBounds(12, 152, 67, 16);
		panel_1.add(lblCmoComm);
		
		txtCmoDsn = new JTextField();
		txtCmoDsn.setEditable(false);
		txtCmoDsn.setForeground(Color.BLACK);
		txtCmoDsn.setBackground(Color.WHITE);
		txtCmoDsn.setBounds(168, 122, 114, 20);
		panel_1.add(txtCmoDsn);
		txtCmoDsn.setColumns(10);
		
		txtCmoComm = new JTextField();
		txtCmoComm.setEditable(false);
		txtCmoComm.setForeground(Color.BLACK);
		txtCmoComm.setBackground(Color.WHITE);
		txtCmoComm.setBounds(168, 150, 114, 20);
		panel_1.add(txtCmoComm);
		txtCmoComm.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_8.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Comment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(7, 234, 280, 147);
		panel_1.add(panel_8);
		panel_8.setLayout(null);
		
		JTextPane txtpnComment = new JTextPane();
		txtpnComment.setEditable(false);
		txtpnComment.setBackground(Color.WHITE);
		txtpnComment.setBounds(5, 18, 270, 124);
		panel_8.add(txtpnComment);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		tabbedPane_1.addTab("CHF", null, panel_4, null);
		panel_4.setLayout(null);
		
		txtTsoNamed = new JTextField();
		txtTsoNamed.setBounds(168, 11, 114, 20);
		panel_4.add(txtTsoNamed);
		txtTsoNamed.setColumns(10);
		
		JLabel lblTsoNamed = new JLabel("TSO Named");
		lblTsoNamed.setBounds(12, 13, 67, 16);
		panel_4.add(lblTsoNamed);
		
		JLabel lblChfCreated = new JLabel("CHF Created");
		lblChfCreated.setBounds(12, 41, 70, 16);
		panel_4.add(lblChfCreated);
		
		txtChfLink_1 = new JTextField();
		txtChfLink_1.setBounds(168, 39, 114, 20);
		panel_4.add(txtChfLink_1);
		txtChfLink_1.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_9.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Root", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(44, 180, 199, 95);
		panel_4.add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(5, 74, 55, 16);
		panel_9.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(5, 46, 55, 16);
		panel_9.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(5, 18, 55, 16);
		panel_9.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setBounds(72, 74, 55, 16);
		panel_9.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setBounds(72, 46, 55, 16);
		panel_9.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setBounds(72, 18, 55, 16);
		panel_9.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		lblNewLabel_10.setBounds(139, 74, 55, 16);
		panel_9.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		lblNewLabel_11.setBounds(139, 46, 55, 16);
		panel_9.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("New label");
		lblNewLabel_12.setBounds(139, 18, 55, 16);
		panel_9.add(lblNewLabel_12);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.setBounds(44, 314, 199, 48);
		panel_4.add(btnNewButton);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane_1.addTab("Facit", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblCcsdlastFour = new JLabel("CCSD (last four)");
		lblCcsdlastFour.setBounds(12, 12, 90, 16);
		panel_5.add(lblCcsdlastFour);
		
		JLabel lblRate = new JLabel("Rate");
		lblRate.setBounds(12, 68, 26, 16);
		panel_5.add(lblRate);
		
		JLabel lblNewLabel_4 = new JLabel("TSP");
		lblNewLabel_4.setBounds(12, 40, 55, 16);
		panel_5.add(lblNewLabel_4);
		
		JLabel lblPurpose = new JLabel("Purpose");
		lblPurpose.setBounds(12, 96, 55, 16);
		panel_5.add(lblPurpose);
		
		JLabel lblServiceAvaliability = new JLabel("Service Avaliability");
		lblServiceAvaliability.setBounds(12, 124, 106, 16);
		panel_5.add(lblServiceAvaliability);
		
		JLabel lblFullCcsd_2 = new JLabel("Full CCSD");
		lblFullCcsd_2.setBounds(12, 152, 106, 16);
		panel_5.add(lblFullCcsd_2);
		
		txtCcsdlast = new JTextField();
		txtCcsdlast.setBounds(168, 10, 114, 20);
		panel_5.add(txtCcsdlast);
		txtCcsdlast.setColumns(10);
		
		txtTsp = new JTextField();
		txtTsp.setBounds(168, 38, 114, 20);
		panel_5.add(txtTsp);
		txtTsp.setColumns(10);
		
		txtRate = new JTextField();
		txtRate.setBounds(168, 66, 114, 20);
		panel_5.add(txtRate);
		txtRate.setColumns(10);
		
		txtPupose = new JTextField();
		txtPupose.setBounds(168, 94, 114, 20);
		panel_5.add(txtPupose);
		txtPupose.setColumns(10);
		
		txtServiceavai = new JTextField();
		txtServiceavai.setBounds(168, 122, 114, 20);
		panel_5.add(txtServiceavai);
		txtServiceavai.setColumns(10);
		
		txtCcsd = new JTextField();
		txtCcsd.setBounds(168, 150, 114, 20);
		panel_5.add(txtCcsd);
		txtCcsd.setColumns(10);
		
		JButton btnEnterTso = new JButton("Enter TSO");
		btnEnterTso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Text.appear();
			}
		});
		btnEnterTso.setBounds(10, 463, 91, 26);
		layeredPane.add(btnEnterTso);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.appear();
			}
		});
		btnSettings.setBounds(109, 463, 81, 26);
		layeredPane.add(btnSettings);
		
		btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WindowCore.collect();
				Collection.debugGeneral();
			}
		});
		btnRun.setBounds(202, 463, 56, 26);
		layeredPane.add(btnRun);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LogHelper.info("Closing Program");
				frmTsoHelper.dispose();
			}
		});
		btnExit.setBounds(530, 463, 91, 26);
		layeredPane.add(btnExit);
		
		progressBar = new JProgressBar(ProgressBarCore.pbMin, ProgressBarCore.pbMin);
		progressBar.setBounds(10, 438, 611, 14);
		progressBar.setValue(0);
		layeredPane.add(progressBar);
		
		JLabel lblTsoHelperV = new JLabel("TSO Helper " + Reference.APPLICATION_VERSION);
		lblTsoHelperV.setBackground(Color.LIGHT_GRAY);
		lblTsoHelperV.setBounds(378, 466, 92, 16);
		layeredPane.add(lblTsoHelperV);
	}
}
