package taytom258.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.lib.Reference;
import taytom258.lib.Strings;
import taytom258.window.core.ProgressBarCore;
import taytom258.window.core.WindowCore;

public class Window {
	
	protected JFrame frmTsoHelper;
	private static JTextField textFieldOtherCmoName;
	private static JTextField textFieldOtherCmoDsn;
	private static JTextField textFieldOtherCmoComm;
	private static JTextField textFieldTsoSubject;
	private static JTextField textFieldFullCcsd;
	private static JTextField textFieldServiceDate;
	private static JRadioButton rdbtnAndrews;
	private static JRadioButton rdbtnOther;
	private static JCheckBox chckbxComReport;
	private static JCheckBox chckbxLogical;
	private static JTextArea textAreaExtraComments;
	private JCheckBox chckbxStart1539;
	private JCheckBox chckbxStartSams;
	private JCheckBox chckbxStartAnalog;
	private JCheckBox chckbxStartPassthrough;
	private JToggleButton tglbtnStartCHF;
	private static JTextField textFieldStartDataRate;
	private JTextField textFieldStartServiceAvailability;
	private JComboBox<?> comboBoxStartTsp;
	private JTextField textFieldStartTsr;
	private JTextField textFieldStartReportDate;
	private JTextArea textAreaStartTsoStatement;

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
		
		final JPanel panelGeneral = new JPanel();
		tabbedPane.addTab("General", null, panelGeneral, null);
		panelGeneral.setLayout(null);
		
		textFieldTsoSubject = new JTextField();
		textFieldTsoSubject.setBackground(Color.WHITE);
		textFieldTsoSubject.setToolTipText("Enter TSO Subject Here");
		textFieldTsoSubject.setBounds(171, 12, 114, 20);
		panelGeneral.add(textFieldTsoSubject);
		textFieldTsoSubject.setColumns(10);
		
		JLabel lblTsoSubject = new JLabel("TSO Subject");
		lblTsoSubject.setBounds(12, 14, 70, 16);
		panelGeneral.add(lblTsoSubject);
		
		textFieldFullCcsd = new JTextField();
		textFieldFullCcsd.setToolTipText("Enter Full CCSD Here");
		textFieldFullCcsd.setBounds(171, 44, 114, 20);
		panelGeneral.add(textFieldFullCcsd);
		textFieldFullCcsd.setColumns(10);
		
		JLabel lblFullCcsd = new JLabel("Full CCSD");
		lblFullCcsd.setBounds(12, 46, 55, 16);
		panelGeneral.add(lblFullCcsd);
		
		textFieldServiceDate = new JTextField();
		textFieldServiceDate.setToolTipText("Enter Service Date of TSO Here");
		textFieldServiceDate.setText(Strings.DATE_FORMAT);
		textFieldServiceDate.setBounds(171, 76, 114, 20);
		panelGeneral.add(textFieldServiceDate);
		textFieldServiceDate.setColumns(10);
		textFieldServiceDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textFieldServiceDate.setText("");
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldServiceDate.getText().equals("")){
					textFieldServiceDate.setText(Strings.DATE_FORMAT);
				}
			}
		});
		
		JLabel lblServiceDate = new JLabel("Service Date");
		lblServiceDate.setBounds(12, 74, 72, 16);
		panelGeneral.add(lblServiceDate);
		
		textFieldOtherCmoName = new JTextField();
		textFieldOtherCmoName.setToolTipText("Enter Other CMO Here");
		textFieldOtherCmoName.setBounds(158, 137, 114, 20);
		panelGeneral.add(textFieldOtherCmoName);
		textFieldOtherCmoName.setColumns(10);
		textFieldOtherCmoName.setEnabled(false);
		textFieldOtherCmoName.setText(null);
		textFieldOtherCmoName.setBackground(Color.LIGHT_GRAY);
		
		rdbtnAndrews = new JRadioButton("Andrews");
		rdbtnAndrews.setBounds(148, 104, 76, 24);
		rdbtnAndrews.setSelected(true);
		panelGeneral.add(rdbtnAndrews);
		
		rdbtnOther = new JRadioButton("Other");
		rdbtnOther.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnOther.isSelected()){
					textFieldOtherCmoName.setEnabled(true);
					textFieldOtherCmoDsn.setEnabled(true);
					textFieldOtherCmoComm.setEnabled(true);
					
					textFieldOtherCmoName.setBackground(Color.WHITE);
					textFieldOtherCmoDsn.setBackground(Color.WHITE);
					textFieldOtherCmoComm.setBackground(Color.WHITE);
					
				}else{
					textFieldOtherCmoName.setEnabled(false);
					textFieldOtherCmoName.setText(null);
					textFieldOtherCmoName.setBackground(Color.LIGHT_GRAY);
					
					textFieldOtherCmoDsn.setEnabled(false);
					textFieldOtherCmoDsn.setText(null);
					textFieldOtherCmoDsn.setBackground(Color.LIGHT_GRAY);
					
					textFieldOtherCmoComm.setEnabled(false);
					textFieldOtherCmoComm.setText(null);
					textFieldOtherCmoComm.setBackground(Color.LIGHT_GRAY);
				}
			}
		});
		rdbtnOther.setBounds(228, 104, 57, 24);
		panelGeneral.add(rdbtnOther);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnAndrews);
		group.add(rdbtnOther);
		
		JLabel lblCmo_1 = new JLabel("CMO");
		lblCmo_1.setBounds(12, 108, 27, 16);
		panelGeneral.add(lblCmo_1);
		
		chckbxComReport = new JCheckBox("Completion Report Required?");
		chckbxComReport.setBounds(94, 236, 191, 24);
		panelGeneral.add(chckbxComReport);
		
		chckbxLogical = new JCheckBox("Logical?");
		chckbxLogical.setBounds(12, 236, 73, 24);
		panelGeneral.add(chckbxLogical);
		
		JPanel panelExtraComments = new JPanel();
		panelExtraComments.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Extra Comments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelExtraComments.setBounds(12, 268, 283, 113);
		panelGeneral.add(panelExtraComments);
		panelExtraComments.setLayout(null);
		
		textAreaExtraComments = new JTextArea();
		textAreaExtraComments.setWrapStyleWord(true);
		textAreaExtraComments.setLineWrap(true);
		textAreaExtraComments.setToolTipText("Enter Extra Comments Here");
		textAreaExtraComments.setBounds(12, 23, 259, 78);
		panelExtraComments.add(textAreaExtraComments);
		
		textFieldOtherCmoDsn = new JTextField();
		textFieldOtherCmoDsn.setBounds(158, 169, 114, 20);
		panelGeneral.add(textFieldOtherCmoDsn);
		textFieldOtherCmoDsn.setColumns(10);
		textFieldOtherCmoDsn.setEnabled(false);
		textFieldOtherCmoDsn.setText(null);
		textFieldOtherCmoDsn.setBackground(Color.LIGHT_GRAY);
		
		textFieldOtherCmoComm = new JTextField();
		textFieldOtherCmoComm.setBounds(158, 201, 114, 20);
		panelGeneral.add(textFieldOtherCmoComm);
		textFieldOtherCmoComm.setColumns(10);
		textFieldOtherCmoComm.setEnabled(false);
		textFieldOtherCmoComm.setText(null);
		textFieldOtherCmoComm.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblOtherCmoName = new JLabel("CMO Name");
		lblOtherCmoName.setFont(new Font("Dialog", Font.BOLD, 10));
		lblOtherCmoName.setBounds(85, 141, 55, 14);
		panelGeneral.add(lblOtherCmoName);
		
		JLabel lblOtherCmoDsn = new JLabel("DSN");
		lblOtherCmoDsn.setFont(new Font("Dialog", Font.BOLD, 10));
		lblOtherCmoDsn.setBounds(119, 173, 21, 14);
		panelGeneral.add(lblOtherCmoDsn);
		
		JLabel lblOtherCmoComm = new JLabel("Comm");
		lblOtherCmoComm.setFont(new Font("Dialog", Font.BOLD, 10));
		lblOtherCmoComm.setBounds(109, 205, 31, 14);
		panelGeneral.add(lblOtherCmoComm);
		
		JPanel panelStart = new JPanel();
		tabbedPane.addTab("Start", null, panelStart, null);
		panelStart.setLayout(null);
		
		chckbxStartSams = new JCheckBox("Sams?");
		chckbxStartSams.setBounds(8, 33, 65, 24);
		panelStart.add(chckbxStartSams);
		
		chckbxStartAnalog = new JCheckBox("Analog?");
		chckbxStartAnalog.setBounds(8, 61, 71, 24);
		panelStart.add(chckbxStartAnalog);
		
		chckbxStartPassthrough = new JCheckBox("Passthrough?");
		chckbxStartPassthrough.setBounds(8, 89, 105, 24);
		panelStart.add(chckbxStartPassthrough);
		
		tglbtnStartCHF = new JToggleButton("CHF Creation Active");
		tglbtnStartCHF.setBounds(140, 47, 145, 26);
		panelStart.add(tglbtnStartCHF);
		
		textFieldStartDataRate = new JTextField();
		textFieldStartDataRate.setBounds(171, 113, 114, 20);
		panelStart.add(textFieldStartDataRate);
		textFieldStartDataRate.setColumns(10);
		
		JLabel lblStartDataRate = new JLabel("Data Rate");
		lblStartDataRate.setBounds(12, 115, 55, 16);
		panelStart.add(lblStartDataRate);
		
		textFieldStartServiceAvailability = new JTextField();
		textFieldStartServiceAvailability.setBounds(171, 141, 114, 20);
		panelStart.add(textFieldStartServiceAvailability);
		textFieldStartServiceAvailability.setColumns(10);
		
		JLabel lblStartServiceAvailability = new JLabel("Service Availability");
		lblStartServiceAvailability.setBounds(12, 143, 106, 16);
		panelStart.add(lblStartServiceAvailability);
		
		JLabel lblStartTsp = new JLabel("TSP");
		lblStartTsp.setBounds(12, 171, 23, 16);
		panelStart.add(lblStartTsp);
		
		comboBoxStartTsp = new JComboBox();
		comboBoxStartTsp.setModel(new DefaultComboBoxModel(new String[] {"TSP 1", "TSP 2", "TSP 3", "TSP 4", "TSP 5", "N/A"}));
		comboBoxStartTsp.setBounds(171, 167, 114, 25);
		panelStart.add(comboBoxStartTsp);
		
		JLabel lblStartTsrNumber = new JLabel("TSR Number");
		lblStartTsrNumber.setBounds(12, 206, 71, 16);
		panelStart.add(lblStartTsrNumber);
		
		JLabel lblStartReportDate = new JLabel("Report Date");
		lblStartReportDate.setBounds(12, 238, 67, 16);
		panelStart.add(lblStartReportDate);
		
		textFieldStartTsr = new JTextField();
		textFieldStartTsr.setBounds(171, 204, 114, 20);
		panelStart.add(textFieldStartTsr);
		textFieldStartTsr.setColumns(10);
		
		textFieldStartReportDate = new JTextField();
		textFieldStartReportDate.setBounds(171, 236, 114, 20);
		panelStart.add(textFieldStartReportDate);
		textFieldStartReportDate.setColumns(10);
		
		JPanel panelStartTsoStatement = new JPanel();
		panelStartTsoStatement.setLayout(null);
		panelStartTsoStatement.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "TSO Statement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelStartTsoStatement.setBounds(8, 266, 283, 113);
		panelStart.add(panelStartTsoStatement);
		
		textAreaStartTsoStatement = new JTextArea();
		textAreaStartTsoStatement.setWrapStyleWord(true);
		textAreaStartTsoStatement.setToolTipText("Enter Extra Comments Here");
		textAreaStartTsoStatement.setLineWrap(true);
		textAreaStartTsoStatement.setBounds(12, 23, 259, 78);
		panelStartTsoStatement.add(textAreaStartTsoStatement);
		
		chckbxStart1539 = new JCheckBox("1539 Circuit?");
		chckbxStart1539.setBounds(8, 8, 112, 24);
		panelStart.add(chckbxStart1539);
		
		JPanel panelChange = new JPanel();
		panelChange.setLayout(null);
		tabbedPane.addTab("Change", null, panelChange, null);
		
		JCheckBox checkBoxChangeSams = new JCheckBox("Sams?");
		checkBoxChangeSams.setBounds(8, 33, 65, 24);
		panelChange.add(checkBoxChangeSams);
		
		JCheckBox checkBoxChangeAnalog = new JCheckBox("Analog?");
		checkBoxChangeAnalog.setBounds(8, 61, 71, 24);
		panelChange.add(checkBoxChangeAnalog);
		
		JCheckBox checkBoxChangePassthrough = new JCheckBox("Passthrough?");
		checkBoxChangePassthrough.setBounds(8, 89, 105, 24);
		panelChange.add(checkBoxChangePassthrough);
		
		JToggleButton tglbtnChangeCHF = new JToggleButton("CHF Modification Active");
		tglbtnChangeCHF.setBounds(119, 47, 166, 26);
		panelChange.add(tglbtnChangeCHF);
		
		JLabel labelChangeTsrNumber = new JLabel("TSR Number");
		labelChangeTsrNumber.setBounds(12, 206, 71, 16);
		panelChange.add(labelChangeTsrNumber);
		
		JLabel labelChangeReportNumber = new JLabel("Report Date");
		labelChangeReportNumber.setBounds(12, 238, 67, 16);
		panelChange.add(labelChangeReportNumber);
		
		JTextField textFieldChangeTsrNumber = new JTextField();
		textFieldChangeTsrNumber.setColumns(10);
		textFieldChangeTsrNumber.setBounds(171, 204, 114, 20);
		panelChange.add(textFieldChangeTsrNumber);
		
		JTextField textFieldChangeReportDate = new JTextField();
		textFieldChangeReportDate.setColumns(10);
		textFieldChangeReportDate.setBounds(171, 236, 114, 20);
		panelChange.add(textFieldChangeReportDate);
		
		JPanel panelChangeTsoStatement = new JPanel();
		panelChangeTsoStatement.setLayout(null);
		panelChangeTsoStatement.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "TSO Statement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelChangeTsoStatement.setBounds(8, 266, 283, 113);
		panelChange.add(panelChangeTsoStatement);
		
		JTextArea textAreaChangeTsoStatement = new JTextArea();
		textAreaChangeTsoStatement.setWrapStyleWord(true);
		textAreaChangeTsoStatement.setToolTipText("Enter Extra Comments Here");
		textAreaChangeTsoStatement.setLineWrap(true);
		textAreaChangeTsoStatement.setBounds(12, 23, 259, 78);
		panelChangeTsoStatement.add(textAreaChangeTsoStatement);
		
		JCheckBox checkBoxChange1539 = new JCheckBox("1539 Circuit?");
		checkBoxChange1539.setBounds(8, 8, 112, 24);
		panelChange.add(checkBoxChange1539);
		
		JPanel panelDisco = new JPanel();
		panelDisco.setLayout(null);
		tabbedPane.addTab("Disco", null, panelDisco, null);
		
		JCheckBox checkBoxDiscoSams = new JCheckBox("Sams?");
		checkBoxDiscoSams.setBounds(8, 33, 65, 24);
		panelDisco.add(checkBoxDiscoSams);
		
		JCheckBox checkBoxDiscoAnalog = new JCheckBox("Analog?");
		checkBoxDiscoAnalog.setBounds(8, 61, 71, 24);
		panelDisco.add(checkBoxDiscoAnalog);
		
		JCheckBox checkBoxDiscoPassthrough = new JCheckBox("Passthrough?");
		checkBoxDiscoPassthrough.setBounds(8, 89, 105, 24);
		panelDisco.add(checkBoxDiscoPassthrough);
		
		JToggleButton tglBtnDiscoCHF = new JToggleButton("CHF Modification Active");
		tglBtnDiscoCHF.setBounds(119, 47, 166, 26);
		panelDisco.add(tglBtnDiscoCHF);
		
		JLabel labelDiscoTsrNumber = new JLabel("TSR Number");
		labelDiscoTsrNumber.setBounds(12, 206, 71, 16);
		panelDisco.add(labelDiscoTsrNumber);
		
		JLabel labelDiscoReportDate = new JLabel("Report Date");
		labelDiscoReportDate.setBounds(12, 238, 67, 16);
		panelDisco.add(labelDiscoReportDate);
		
		JTextField  textFieldDiscoTsrNumber = new JTextField();
		textFieldDiscoTsrNumber.setColumns(10);
		textFieldDiscoTsrNumber.setBounds(171, 204, 114, 20);
		panelDisco.add(textFieldDiscoTsrNumber);
		
		JTextField textFieldDiscoReportDate = new JTextField();
		textFieldDiscoReportDate.setColumns(10);
		textFieldDiscoReportDate.setBounds(171, 236, 114, 20);
		panelDisco.add(textFieldDiscoReportDate);
		
		JPanel panelDiscoTsoStatement = new JPanel();
		panelDiscoTsoStatement.setLayout(null);
		panelDiscoTsoStatement.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "TSO Statement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDiscoTsoStatement.setBounds(8, 266, 283, 113);
		panelDisco.add(panelDiscoTsoStatement);
		
		JTextArea textAreaDiscoTsoStatement = new JTextArea();
		textAreaDiscoTsoStatement.setWrapStyleWord(true);
		textAreaDiscoTsoStatement.setToolTipText("Enter Extra Comments Here");
		textAreaDiscoTsoStatement.setLineWrap(true);
		textAreaDiscoTsoStatement.setBounds(12, 23, 259, 78);
		panelDiscoTsoStatement.add(textAreaDiscoTsoStatement);
		
		JCheckBox checkBoxDisco1539 = new JCheckBox("1539 Circuit?");
		checkBoxDisco1539.setBounds(8, 8, 112, 24);
		panelDisco.add(checkBoxDisco1539);
		
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
		
		JTextField txtFullCcsd = new JTextField();
		txtFullCcsd.setEditable(false);
		txtFullCcsd.setForeground(Color.BLACK);
		txtFullCcsd.setBackground(Color.WHITE);
		txtFullCcsd.setBounds(168, 10, 114, 20);
		panel_1.add(txtFullCcsd);
		txtFullCcsd.setColumns(10);
		
		JTextField txtAction = new JTextField();
		txtAction.setEditable(false);
		txtAction.setForeground(Color.BLACK);
		txtAction.setBackground(Color.WHITE);
		txtAction.setBounds(168, 38, 114, 20);
		panel_1.add(txtAction);
		txtAction.setColumns(10);
		
		JTextField txtChfLink = new JTextField();
		txtChfLink.setEditable(false);
		txtChfLink.setForeground(Color.BLACK);
		txtChfLink.setBackground(Color.WHITE);
		txtChfLink.setBounds(168, 66, 114, 20);
		panel_1.add(txtChfLink);
		txtChfLink.setColumns(10);
		
		JTextField txtCmo = new JTextField();
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
		
		JTextField txtCmoDsn = new JTextField();
		txtCmoDsn.setEditable(false);
		txtCmoDsn.setForeground(Color.BLACK);
		txtCmoDsn.setBackground(Color.WHITE);
		txtCmoDsn.setBounds(168, 122, 114, 20);
		panel_1.add(txtCmoDsn);
		txtCmoDsn.setColumns(10);
		
		JTextField txtCmoComm = new JTextField();
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
		
		JTextField txtTsoNamed = new JTextField();
		txtTsoNamed.setBackground(Color.WHITE);
		txtTsoNamed.setEditable(false);
		txtTsoNamed.setBounds(168, 11, 114, 20);
		panel_4.add(txtTsoNamed);
		txtTsoNamed.setColumns(10);
		
		JLabel lblTsoNamed = new JLabel("TSO Named");
		lblTsoNamed.setBounds(12, 13, 67, 16);
		panel_4.add(lblTsoNamed);
		
		JLabel lblChfCreated = new JLabel("CHF Created");
		lblChfCreated.setBounds(12, 41, 70, 16);
		panel_4.add(lblChfCreated);
		
		JTextField txtChfLink_1 = new JTextField();
		txtChfLink_1.setEditable(false);
		txtChfLink_1.setBackground(Color.WHITE);
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
		
		JTextField txtCcsdlast = new JTextField();
		txtCcsdlast.setEditable(false);
		txtCcsdlast.setBackground(Color.WHITE);
		txtCcsdlast.setBounds(168, 10, 114, 20);
		panel_5.add(txtCcsdlast);
		txtCcsdlast.setColumns(10);
		
		JTextField txtTsp = new JTextField();
		txtTsp.setEditable(false);
		txtTsp.setBackground(Color.WHITE);
		txtTsp.setBounds(168, 38, 114, 20);
		panel_5.add(txtTsp);
		txtTsp.setColumns(10);
		
		JTextField txtRate = new JTextField();
		txtRate.setEditable(false);
		txtRate.setBackground(Color.WHITE);
		txtRate.setBounds(168, 66, 114, 20);
		panel_5.add(txtRate);
		txtRate.setColumns(10);
		
		JTextField txtPupose = new JTextField();
		txtPupose.setEditable(false);
		txtPupose.setBackground(Color.WHITE);
		txtPupose.setBounds(168, 94, 114, 20);
		panel_5.add(txtPupose);
		txtPupose.setColumns(10);
		
		JTextField txtServiceavai = new JTextField();
		txtServiceavai.setEditable(false);
		txtServiceavai.setBackground(Color.WHITE);
		txtServiceavai.setBounds(168, 122, 114, 20);
		panel_5.add(txtServiceavai);
		txtServiceavai.setColumns(10);
		
		JTextField txtCcsd = new JTextField();
		txtCcsd.setEditable(false);
		txtCcsd.setBackground(Color.WHITE);
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
		
		JButton btnRun = new JButton("Run");
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
				frmTsoHelper.setVisible(false);
				LogHelper.debug("Terminating JVM");
				System.exit(0);
			}
		});
		btnExit.setBounds(530, 463, 91, 26);
		layeredPane.add(btnExit);
		
		JProgressBar progressBar = new JProgressBar(ProgressBarCore.pbMin, ProgressBarCore.pbMax);
		progressBar.setBounds(10, 438, 611, 14);
		progressBar.setValue(0);
		layeredPane.add(progressBar);
		
		JLabel lblTsoHelperV = new JLabel("TSO Helper " + Reference.APPLICATION_VERSION);
		lblTsoHelperV.setBackground(Color.LIGHT_GRAY);
		lblTsoHelperV.setBounds(378, 466, 92, 16);
		layeredPane.add(lblTsoHelperV);
	}
	public static JTextField getTextFieldOtherCmoName() {
		return textFieldOtherCmoName;
	}
	public static JTextField getTextFieldOtherCmoDsn() {
		return textFieldOtherCmoDsn;
	}
	public static JTextField getTextFieldOtherCmoComm() {
		return textFieldOtherCmoComm;
	}
	public static JTextField getTextFieldTsoSubject() {
		return textFieldTsoSubject;
	}
	public static JTextField getTextFieldFullCcsd() {
		return textFieldFullCcsd;
	}
	public static JTextField getTextFieldServiceDate() {
		return textFieldServiceDate;
	}
	public static JRadioButton getRdbtnAndrews() {
		return rdbtnAndrews;
	}
	public static JRadioButton getRdbtnOther() {
		return rdbtnOther;
	}
	public static JCheckBox getChckbxComReport() {
		return chckbxComReport;
	}
	public static JCheckBox getChckbxLogical() {
		return chckbxLogical;
	}
	public static JTextArea getTextAreaExtraComments() {
		return textAreaExtraComments;
	}
	public JCheckBox getChckbxStart1539() {
		return chckbxStart1539;
	}
	public JCheckBox getChckbxStartSams() {
		return chckbxStartSams;
	}
	public JCheckBox getChckbxStartAnalog() {
		return chckbxStartAnalog;
	}
	public JCheckBox getChckbxStartPassthrough() {
		return chckbxStartPassthrough;
	}
	public JToggleButton getTglbtnStartCHF() {
		return tglbtnStartCHF;
	}
	public static JTextField getTextFieldStartDataRate() {
		return textFieldStartDataRate;
	}
	public JTextField getTextFieldStartServiceAvailability() {
		return textFieldStartServiceAvailability;
	}
	public JComboBox getComboBoxStartTsp() {
		return comboBoxStartTsp;
	}
	public JTextField getTextFieldStartTsr() {
		return textFieldStartTsr;
	}
	public JTextField getTextFieldStartReportDate() {
		return textFieldStartReportDate;
	}
	public JTextArea getTextAreaStartTsoStatement() {
		return textAreaStartTsoStatement;
	}
}
