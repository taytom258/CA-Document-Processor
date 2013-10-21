package taytom258.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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

import taytom258.core.util.Debug;
import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.lib.Reference;
import taytom258.lib.Strings;
import taytom258.show.ShowFacit;
import taytom258.show.ShowTracker;
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
	private static JCheckBox chckbxStart1539;
	private static JCheckBox chckbxStartSams;
	private static JCheckBox chckbxStartAnalog;
	private static JCheckBox chckbxStartPassthrough;
	private static JToggleButton tglbtnStartCHF;
	private static JTextField textFieldStartDataRate;
	private static JTextField textFieldStartServiceAvailability;
	private static JComboBox<?> comboBoxStartTsp;
	private static JTextField textFieldStartTsr;
	private static JTextField textFieldStartReportDate;
	private static JTextArea textAreaStartTsoStatement;
	private static JCheckBox chckbxChange1539;
	private static JCheckBox chckbxChangeSams;
	private static JCheckBox chckbxChangeAnalog;
	private static JCheckBox chckbxChangePassthrough;
	private static JTextField textFieldChangeTsrNumber;
	private static JTextField textFieldChangeReportDate;
	private static JTextArea textAreaChangeTsoStatement;
	private static JToggleButton tglbtnChangeCHF;
	private static JCheckBox chckbxDisco1539;
	private static JCheckBox chckbxDiscoSams;
	private static JCheckBox chckbxDiscoAnalog;
	private static JCheckBox chckbxDiscoPassthrough;
	private static JToggleButton tglBtnDiscoCHF;
	private static JTextField textFieldDiscoTsrNumber;
	private static JTextField textFieldDiscoReportDate;
	private static JTextArea textAreaDiscoTsoStatement;
	private static JTextField textFieldShowAction;
	private static JTextField textFieldShowTsoName;
	private static JTextField textFieldShowRootFolder;
	private static JTextField textFieldShowFacitFullCcsd;
	private static JTextField textFieldShowTsp;
	private static JTextField textFieldShowPurpose;
	private static JTextField textFieldShowRate;
	private static JTextField textFieldShowServiceAvailable;
	private static JTextField textFieldShowCcsd;
	private static JTextField textFieldShowServiceDate;
	private static JTextField textFieldShowTsoSubject;
	private static JTextField textFieldShowAction_1;
	private static JTextField textFieldShowTsoNumber;
	private static JTextField textFieldShowTsrNumber;
	private static JTextField textFieldShowServiceDate_1;
	private static JTextField textFieldShowCcsd_1;
	private static JTextField textFieldShowReportDate;
	private static JTextField textFieldShowTrackerFullCcsd;
	private static JTextField textFieldShowChfLink;
	private static JTextField textFieldShowCmo;
	private static JTextField textFieldShowCmoDsn;
	private static JTextField textFieldShowCmoComm;
	private static JTextPane textAreaShowComment;
	private static JCheckBox chckbxAncs;
	private static JTextArea textAreaShowTsoStatement;
	private static JCheckBox chckbxCompletionReportRequired;
	private static JTextArea textAreaShowTsoStatement_1;

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
					Debug.init();
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
		tabbedPane.setEnabledAt(0, true);
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
		panelExtraComments.setBounds(8, 266, 283, 113);
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
		
		chckbxAncs = new JCheckBox("ANCS?");
		chckbxAncs.setBounds(12, 208, 64, 24);
		panelGeneral.add(chckbxAncs);
		
		JPanel panelStart = new JPanel();
		tabbedPane.addTab("Start", null, panelStart, null);
		tabbedPane.setEnabledAt(1, true);
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
		textFieldStartReportDate.setText(Strings.DATE_FORMAT);
		panelStart.add(textFieldStartReportDate);
		textFieldStartReportDate.setColumns(10);
		textFieldStartReportDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textFieldStartReportDate.setText("");
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldStartReportDate.getText().equals("")){
					textFieldStartReportDate.setText(Strings.DATE_FORMAT);
				}
			}
		});
		
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
		tabbedPane.setEnabledAt(2, true);
		
		chckbxChangeSams = new JCheckBox("Sams?");
		chckbxChangeSams.setBounds(8, 33, 65, 24);
		panelChange.add(chckbxChangeSams);
		
		chckbxChangeAnalog = new JCheckBox("Analog?");
		chckbxChangeAnalog.setBounds(8, 61, 71, 24);
		panelChange.add(chckbxChangeAnalog);
		
		chckbxChangePassthrough = new JCheckBox("Passthrough?");
		chckbxChangePassthrough.setBounds(8, 89, 105, 24);
		panelChange.add(chckbxChangePassthrough);
		
		tglbtnChangeCHF = new JToggleButton("CHF Modification Active");
		tglbtnChangeCHF.setBounds(119, 47, 166, 26);
		panelChange.add(tglbtnChangeCHF);
		
		JLabel labelChangeTsrNumber = new JLabel("TSR Number");
		labelChangeTsrNumber.setBounds(12, 206, 71, 16);
		panelChange.add(labelChangeTsrNumber);
		
		JLabel labelChangeReportNumber = new JLabel("Report Date");
		labelChangeReportNumber.setBounds(12, 238, 67, 16);
		panelChange.add(labelChangeReportNumber);
		
		textFieldChangeTsrNumber = new JTextField();
		textFieldChangeTsrNumber.setColumns(10);
		textFieldChangeTsrNumber.setBounds(171, 204, 114, 20);
		panelChange.add(textFieldChangeTsrNumber);
		
		textFieldChangeReportDate = new JTextField();
		textFieldChangeReportDate.setColumns(10);
		textFieldChangeReportDate.setText(Strings.DATE_FORMAT);
		textFieldChangeReportDate.setBounds(171, 236, 114, 20);
		panelChange.add(textFieldChangeReportDate);
		textFieldChangeReportDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textFieldChangeReportDate.setText("");
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldChangeReportDate.getText().equals("")){
					textFieldChangeReportDate.setText(Strings.DATE_FORMAT);
				}
			}
		});
		
		JPanel panelChangeTsoStatement = new JPanel();
		panelChangeTsoStatement.setLayout(null);
		panelChangeTsoStatement.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "TSO Statement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelChangeTsoStatement.setBounds(8, 266, 283, 113);
		panelChange.add(panelChangeTsoStatement);
		
		textAreaChangeTsoStatement = new JTextArea();
		textAreaChangeTsoStatement.setWrapStyleWord(true);
		textAreaChangeTsoStatement.setToolTipText("Enter Extra Comments Here");
		textAreaChangeTsoStatement.setLineWrap(true);
		textAreaChangeTsoStatement.setBounds(12, 23, 259, 78);
		panelChangeTsoStatement.add(textAreaChangeTsoStatement);
		
		chckbxChange1539 = new JCheckBox("1539 Circuit?");
		chckbxChange1539.setBounds(8, 8, 112, 24);
		panelChange.add(chckbxChange1539);
		
		JPanel panelDisco = new JPanel();
		panelDisco.setLayout(null);
		tabbedPane.addTab("Disco", null, panelDisco, null);
		tabbedPane.setEnabledAt(3, true);
		
		chckbxDiscoSams = new JCheckBox("Sams?");
		chckbxDiscoSams.setBounds(8, 33, 65, 24);
		panelDisco.add(chckbxDiscoSams);
		
		chckbxDiscoAnalog = new JCheckBox("Analog?");
		chckbxDiscoAnalog.setBounds(8, 61, 71, 24);
		panelDisco.add(chckbxDiscoAnalog);
		
		chckbxDiscoPassthrough = new JCheckBox("Passthrough?");
		chckbxDiscoPassthrough.setBounds(8, 89, 105, 24);
		panelDisco.add(chckbxDiscoPassthrough);
		
		tglBtnDiscoCHF = new JToggleButton("CHF Modification Active");
		tglBtnDiscoCHF.setBounds(119, 47, 166, 26);
		panelDisco.add(tglBtnDiscoCHF);
		
		JLabel labelDiscoTsrNumber = new JLabel("TSR Number");
		labelDiscoTsrNumber.setBounds(12, 206, 71, 16);
		panelDisco.add(labelDiscoTsrNumber);
		
		JLabel labelDiscoReportDate = new JLabel("Report Date");
		labelDiscoReportDate.setBounds(12, 238, 67, 16);
		panelDisco.add(labelDiscoReportDate);
		
		textFieldDiscoTsrNumber = new JTextField();
		textFieldDiscoTsrNumber.setColumns(10);
		textFieldDiscoTsrNumber.setBounds(171, 204, 114, 20);
		panelDisco.add(textFieldDiscoTsrNumber);
		
		textFieldDiscoReportDate = new JTextField();
		textFieldDiscoReportDate.setColumns(10);
		textFieldDiscoReportDate.setText(Strings.DATE_FORMAT);
		textFieldDiscoReportDate.setBounds(171, 236, 114, 20);
		panelDisco.add(textFieldDiscoReportDate);
		textFieldDiscoReportDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textFieldDiscoReportDate.setText("");
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldDiscoReportDate.getText().equals("")){
					textFieldDiscoReportDate.setText(Strings.DATE_FORMAT);
				}
			}
		});
		
		JPanel panelDiscoTsoStatement = new JPanel();
		panelDiscoTsoStatement.setLayout(null);
		panelDiscoTsoStatement.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "TSO Statement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDiscoTsoStatement.setBounds(8, 266, 283, 113);
		panelDisco.add(panelDiscoTsoStatement);
		
		textAreaDiscoTsoStatement = new JTextArea();
		textAreaDiscoTsoStatement.setWrapStyleWord(true);
		textAreaDiscoTsoStatement.setToolTipText("Enter Extra Comments Here");
		textAreaDiscoTsoStatement.setLineWrap(true);
		textAreaDiscoTsoStatement.setBounds(12, 23, 259, 78);
		panelDiscoTsoStatement.add(textAreaDiscoTsoStatement);
		
		chckbxDisco1539 = new JCheckBox("1539 Circuit?");
		chckbxDisco1539.setBounds(8, 8, 112, 24);
		panelDisco.add(chckbxDisco1539);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(SwingConstants.TOP);
		tabbedPane_1.setBounds(322, 11, 299, 416);
		layeredPane.add(tabbedPane_1);
		
		JPanel panelTracker = new JPanel();
		panelTracker.setBackground(Color.WHITE);
		tabbedPane_1.addTab("Tracker", null, panelTracker, null);
		tabbedPane_1.setEnabledAt(0, true);
		panelTracker.setLayout(null);
		
		JLabel lblFullCcsd_1 = new JLabel("Full CCSD");
		lblFullCcsd_1.setBounds(12, 46, 55, 16);
		panelTracker.add(lblFullCcsd_1);
		
		JLabel lblChfLink = new JLabel("CHF Link");
		lblChfLink.setBounds(12, 78, 55, 16);
		panelTracker.add(lblChfLink);
		
		JLabel lblCmo = new JLabel("CMO");
		lblCmo.setBounds(12, 108, 27, 16);
		panelTracker.add(lblCmo);
		
		textFieldShowTrackerFullCcsd = new JTextField();
		textFieldShowTrackerFullCcsd.setEditable(false);
		textFieldShowTrackerFullCcsd.setForeground(Color.BLACK);
		textFieldShowTrackerFullCcsd.setBackground(Color.WHITE);
		textFieldShowTrackerFullCcsd.setBounds(173, 44, 114, 20);
		panelTracker.add(textFieldShowTrackerFullCcsd);
		textFieldShowTrackerFullCcsd.setColumns(10);
		
		textFieldShowChfLink = new JTextField();
		textFieldShowChfLink.setEditable(false);
		textFieldShowChfLink.setForeground(Color.BLACK);
		textFieldShowChfLink.setBackground(Color.WHITE);
		textFieldShowChfLink.setBounds(173, 76, 114, 20);
		panelTracker.add(textFieldShowChfLink);
		textFieldShowChfLink.setColumns(10);
		
		textFieldShowCmo = new JTextField();
		textFieldShowCmo.setEditable(false);
		textFieldShowCmo.setForeground(Color.BLACK);
		textFieldShowCmo.setBackground(Color.WHITE);
		textFieldShowCmo.setBounds(173, 106, 114, 20);
		panelTracker.add(textFieldShowCmo);
		textFieldShowCmo.setColumns(10);
		
		JLabel lblCmoDsn = new JLabel("CMO DSN");
		lblCmoDsn.setBounds(12, 140, 55, 16);
		panelTracker.add(lblCmoDsn);
		
		JLabel lblCmoComm = new JLabel("CMO Comm");
		lblCmoComm.setBounds(12, 172, 67, 16);
		panelTracker.add(lblCmoComm);
		
		textFieldShowCmoDsn = new JTextField();
		textFieldShowCmoDsn.setEditable(false);
		textFieldShowCmoDsn.setForeground(Color.BLACK);
		textFieldShowCmoDsn.setBackground(Color.WHITE);
		textFieldShowCmoDsn.setBounds(173, 138, 114, 20);
		panelTracker.add(textFieldShowCmoDsn);
		textFieldShowCmoDsn.setColumns(10);
		
		textFieldShowCmoComm = new JTextField();
		textFieldShowCmoComm.setEditable(false);
		textFieldShowCmoComm.setForeground(Color.BLACK);
		textFieldShowCmoComm.setBackground(Color.WHITE);
		textFieldShowCmoComm.setBounds(173, 170, 114, 20);
		panelTracker.add(textFieldShowCmoComm);
		textFieldShowCmoComm.setColumns(10);
		
		JPanel panelComment = new JPanel();
		panelComment.setBackground(Color.WHITE);
		panelComment.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Comment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelComment.setBounds(7, 234, 280, 147);
		panelTracker.add(panelComment);
		panelComment.setLayout(null);
		
		textAreaShowComment = new JTextPane();
		textAreaShowComment.setEditable(false);
		textAreaShowComment.setBackground(Color.WHITE);
		textAreaShowComment.setBounds(12, 22, 256, 113);
		panelComment.add(textAreaShowComment);
		
		JLabel lblAction = new JLabel("Action");
		lblAction.setBounds(12, 14, 36, 16);
		panelTracker.add(lblAction);
		
		textFieldShowAction = new JTextField();
		textFieldShowAction.setForeground(Color.BLACK);
		textFieldShowAction.setEditable(false);
		textFieldShowAction.setColumns(10);
		textFieldShowAction.setBackground(Color.WHITE);
		textFieldShowAction.setBounds(173, 12, 114, 20);
		panelTracker.add(textFieldShowAction);
		
		JLabel lblServiceDate_1 = new JLabel("Service Date");
		lblServiceDate_1.setBounds(12, 202, 72, 16);
		panelTracker.add(lblServiceDate_1);
		
		textFieldShowServiceDate = new JTextField();
		textFieldShowServiceDate.setForeground(Color.BLACK);
		textFieldShowServiceDate.setEditable(false);
		textFieldShowServiceDate.setColumns(10);
		textFieldShowServiceDate.setBackground(Color.WHITE);
		textFieldShowServiceDate.setBounds(173, 200, 114, 20);
		panelTracker.add(textFieldShowServiceDate);
		
		JPanel panelChf = new JPanel();
		panelChf.setBackground(Color.WHITE);
		tabbedPane_1.addTab("CHF", null, panelChf, null);
		tabbedPane_1.setEnabledAt(1, true);
		panelChf.setLayout(null);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_9.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Root", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(44, 180, 199, 95);
		panelChf.add(panel_9);
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
		panelChf.add(btnNewButton);
		
		JCheckBox chckbxShowFolderExists = new JCheckBox("Root Folder Exists");
		chckbxShowFolderExists.setEnabled(false);
		chckbxShowFolderExists.setBackground(Color.WHITE);
		chckbxShowFolderExists.setBounds(81, 283, 127, 24);
		panelChf.add(chckbxShowFolderExists);
		
		JLabel lblTsoName = new JLabel("TSO Name");
		lblTsoName.setBounds(12, 14, 60, 16);
		panelChf.add(lblTsoName);
		
		JLabel lblRootFolderName = new JLabel("Root Folder Name");
		lblRootFolderName.setBounds(12, 50, 100, 16);
		panelChf.add(lblRootFolderName);
		
		textFieldShowTsoName = new JTextField();
		textFieldShowTsoName.setForeground(Color.BLACK);
		textFieldShowTsoName.setEditable(false);
		textFieldShowTsoName.setColumns(10);
		textFieldShowTsoName.setBackground(Color.WHITE);
		textFieldShowTsoName.setBounds(168, 12, 114, 20);
		panelChf.add(textFieldShowTsoName);
		
		textFieldShowRootFolder = new JTextField();
		textFieldShowRootFolder.setForeground(Color.BLACK);
		textFieldShowRootFolder.setEditable(false);
		textFieldShowRootFolder.setColumns(10);
		textFieldShowRootFolder.setBackground(Color.WHITE);
		textFieldShowRootFolder.setBounds(168, 48, 114, 20);
		panelChf.add(textFieldShowRootFolder);
		
		JPanel panelFacit = new JPanel();
		panelFacit.setLayout(null);
		panelFacit.setBackground(Color.WHITE);
		tabbedPane_1.addTab("Facit", null, panelFacit, null);
		tabbedPane_1.setEnabledAt(2, true);
		
		JLabel lblShowFullCcsd = new JLabel("Full CCSD");
		lblShowFullCcsd.setBounds(12, 168, 55, 16);
		panelFacit.add(lblShowFullCcsd);
		
		JLabel labelTsp = new JLabel("TSP");
		labelTsp.setBounds(12, 44, 55, 16);
		panelFacit.add(labelTsp);
		
		JLabel lblPurpose = new JLabel("Purpose");
		lblPurpose.setBounds(12, 74, 48, 16);
		panelFacit.add(lblPurpose);
		
		textFieldShowFacitFullCcsd = new JTextField();
		textFieldShowFacitFullCcsd.setForeground(Color.BLACK);
		textFieldShowFacitFullCcsd.setEditable(false);
		textFieldShowFacitFullCcsd.setColumns(10);
		textFieldShowFacitFullCcsd.setBackground(Color.WHITE);
		textFieldShowFacitFullCcsd.setBounds(173, 166, 114, 20);
		panelFacit.add(textFieldShowFacitFullCcsd);
		
		textFieldShowTsp = new JTextField();
		textFieldShowTsp.setForeground(Color.BLACK);
		textFieldShowTsp.setEditable(false);
		textFieldShowTsp.setColumns(10);
		textFieldShowTsp.setBackground(Color.WHITE);
		textFieldShowTsp.setBounds(173, 42, 114, 20);
		panelFacit.add(textFieldShowTsp);
		
		textFieldShowPurpose = new JTextField();
		textFieldShowPurpose.setForeground(Color.BLACK);
		textFieldShowPurpose.setEditable(false);
		textFieldShowPurpose.setColumns(10);
		textFieldShowPurpose.setBackground(Color.WHITE);
		textFieldShowPurpose.setBounds(173, 72, 114, 20);
		panelFacit.add(textFieldShowPurpose);
		
		JLabel lblRate = new JLabel("Rate");
		lblRate.setBounds(12, 106, 26, 16);
		panelFacit.add(lblRate);
		
		JLabel lblServiceAvailiability = new JLabel("Service Availiability");
		lblServiceAvailiability.setBounds(12, 138, 109, 16);
		panelFacit.add(lblServiceAvailiability);
		
		textFieldShowRate = new JTextField();
		textFieldShowRate.setForeground(Color.BLACK);
		textFieldShowRate.setEditable(false);
		textFieldShowRate.setColumns(10);
		textFieldShowRate.setBackground(Color.WHITE);
		textFieldShowRate.setBounds(173, 104, 114, 20);
		panelFacit.add(textFieldShowRate);
		
		textFieldShowServiceAvailable = new JTextField();
		textFieldShowServiceAvailable.setForeground(Color.BLACK);
		textFieldShowServiceAvailable.setEditable(false);
		textFieldShowServiceAvailable.setColumns(10);
		textFieldShowServiceAvailable.setBackground(Color.WHITE);
		textFieldShowServiceAvailable.setBounds(173, 136, 114, 20);
		panelFacit.add(textFieldShowServiceAvailable);
		
		JLabel lblCcsd = new JLabel("CCSD");
		lblCcsd.setBounds(12, 14, 36, 16);
		panelFacit.add(lblCcsd);
		
		textFieldShowCcsd = new JTextField();
		textFieldShowCcsd.setForeground(Color.BLACK);
		textFieldShowCcsd.setEditable(false);
		textFieldShowCcsd.setColumns(10);
		textFieldShowCcsd.setBackground(Color.WHITE);
		textFieldShowCcsd.setBounds(173, 12, 114, 20);
		panelFacit.add(textFieldShowCcsd);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "TSO Statement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(8, 266, 283, 113);
		panelFacit.add(panel_1);
		
		textAreaShowTsoStatement = new JTextArea();
		textAreaShowTsoStatement.setEditable(false);
		textAreaShowTsoStatement.setWrapStyleWord(true);
		textAreaShowTsoStatement.setToolTipText("Enter Extra Comments Here");
		textAreaShowTsoStatement.setLineWrap(true);
		textAreaShowTsoStatement.setBounds(12, 23, 259, 78);
		panel_1.add(textAreaShowTsoStatement);
		
		JButton btnTsoText = new JButton("TSO Text");
		btnTsoText.setBounds(94, 214, 98, 26);
		panelFacit.add(btnTsoText);
		
		JPanel panelFacit_2 = new JPanel();
		panelFacit_2.setLayout(null);
		panelFacit_2.setBackground(Color.WHITE);
		tabbedPane_1.addTab("Facit", null, panelFacit_2, null);
		tabbedPane_1.setEnabledAt(3, true);
		
		JLabel lblTsoSubject_1 = new JLabel("TSO Subject");
		lblTsoSubject_1.setBounds(12, 168, 70, 16);
		panelFacit_2.add(lblTsoSubject_1);
		
		JLabel lblAction_1 = new JLabel("Action");
		lblAction_1.setBounds(12, 44, 55, 16);
		panelFacit_2.add(lblAction_1);
		
		JLabel lblTsoNumber = new JLabel("TSO Number");
		lblTsoNumber.setBounds(12, 74, 72, 16);
		panelFacit_2.add(lblTsoNumber);
		
		textFieldShowTsoSubject = new JTextField();
		textFieldShowTsoSubject.setForeground(Color.BLACK);
		textFieldShowTsoSubject.setEditable(false);
		textFieldShowTsoSubject.setColumns(10);
		textFieldShowTsoSubject.setBackground(Color.WHITE);
		textFieldShowTsoSubject.setBounds(173, 166, 114, 20);
		panelFacit_2.add(textFieldShowTsoSubject);
		
		textFieldShowAction_1 = new JTextField();
		textFieldShowAction_1.setForeground(Color.BLACK);
		textFieldShowAction_1.setEditable(false);
		textFieldShowAction_1.setColumns(10);
		textFieldShowAction_1.setBackground(Color.WHITE);
		textFieldShowAction_1.setBounds(173, 42, 114, 20);
		panelFacit_2.add(textFieldShowAction_1);
		
		textFieldShowTsoNumber = new JTextField();
		textFieldShowTsoNumber.setForeground(Color.BLACK);
		textFieldShowTsoNumber.setEditable(false);
		textFieldShowTsoNumber.setColumns(10);
		textFieldShowTsoNumber.setBackground(Color.WHITE);
		textFieldShowTsoNumber.setBounds(173, 72, 114, 20);
		panelFacit_2.add(textFieldShowTsoNumber);
		
		JLabel lblTsrNumber = new JLabel("TSR Number");
		lblTsrNumber.setBounds(12, 106, 71, 16);
		panelFacit_2.add(lblTsrNumber);
		
		JLabel lblServiceDate_2 = new JLabel("Service Date");
		lblServiceDate_2.setBounds(12, 138, 109, 16);
		panelFacit_2.add(lblServiceDate_2);
		
		textFieldShowTsrNumber = new JTextField();
		textFieldShowTsrNumber.setForeground(Color.BLACK);
		textFieldShowTsrNumber.setEditable(false);
		textFieldShowTsrNumber.setColumns(10);
		textFieldShowTsrNumber.setBackground(Color.WHITE);
		textFieldShowTsrNumber.setBounds(173, 104, 114, 20);
		panelFacit_2.add(textFieldShowTsrNumber);
		
		textFieldShowServiceDate_1 = new JTextField();
		textFieldShowServiceDate_1.setForeground(Color.BLACK);
		textFieldShowServiceDate_1.setEditable(false);
		textFieldShowServiceDate_1.setColumns(10);
		textFieldShowServiceDate_1.setBackground(Color.WHITE);
		textFieldShowServiceDate_1.setBounds(173, 136, 114, 20);
		panelFacit_2.add(textFieldShowServiceDate_1);
		
		JLabel label_5 = new JLabel("CCSD");
		label_5.setBounds(12, 14, 36, 16);
		panelFacit_2.add(label_5);
		
		textFieldShowCcsd_1 = new JTextField();
		textFieldShowCcsd_1.setForeground(Color.BLACK);
		textFieldShowCcsd_1.setEditable(false);
		textFieldShowCcsd_1.setColumns(10);
		textFieldShowCcsd_1.setBackground(Color.WHITE);
		textFieldShowCcsd_1.setBounds(173, 12, 114, 20);
		panelFacit_2.add(textFieldShowCcsd_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "TSO Statement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(8, 266, 283, 113);
		panelFacit_2.add(panel_3);
		
		textAreaShowTsoStatement_1 = new JTextArea();
		textAreaShowTsoStatement_1.setWrapStyleWord(true);
		textAreaShowTsoStatement_1.setToolTipText("Enter Extra Comments Here");
		textAreaShowTsoStatement_1.setLineWrap(true);
		textAreaShowTsoStatement_1.setEditable(false);
		textAreaShowTsoStatement_1.setBounds(12, 23, 259, 78);
		panel_3.add(textAreaShowTsoStatement_1);
		
		JLabel lblReportDate = new JLabel("Report Date");
		lblReportDate.setBounds(12, 198, 67, 16);
		panelFacit_2.add(lblReportDate);
		
		textFieldShowReportDate = new JTextField();
		textFieldShowReportDate.setForeground(Color.BLACK);
		textFieldShowReportDate.setEditable(false);
		textFieldShowReportDate.setColumns(10);
		textFieldShowReportDate.setBackground(Color.WHITE);
		textFieldShowReportDate.setBounds(173, 196, 114, 20);
		panelFacit_2.add(textFieldShowReportDate);
		
		chckbxCompletionReportRequired = new JCheckBox("Completion Report Required");
		chckbxCompletionReportRequired.setEnabled(false);
		chckbxCompletionReportRequired.setBackground(Color.WHITE);
		chckbxCompletionReportRequired.setBounds(55, 234, 184, 24);
		panelFacit_2.add(chckbxCompletionReportRequired);
		
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
				frmTsoHelper.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				WindowCore.collect();
				ShowTracker.show();
				ShowFacit.show();
				frmTsoHelper.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				Collection.debugGeneral();
				Collection.debugTabs();
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
	public static JCheckBox getChckbxStart1539() {
		return chckbxStart1539;
	}
	public static JCheckBox getChckbxStartSams() {
		return chckbxStartSams;
	}
	public static JCheckBox getChckbxStartAnalog() {
		return chckbxStartAnalog;
	}
	public static JCheckBox getChckbxStartPassthrough() {
		return chckbxStartPassthrough;
	}
	public static JToggleButton getTglbtnStartCHF() {
		return tglbtnStartCHF;
	}
	public static JTextField getTextFieldStartDataRate() {
		return textFieldStartDataRate;
	}
	public static JTextField getTextFieldStartServiceAvailability() {
		return textFieldStartServiceAvailability;
	}
	public static JComboBox getComboBoxStartTsp() {
		return comboBoxStartTsp;
	}
	public static JTextField getTextFieldStartTsr() {
		return textFieldStartTsr;
	}
	public static JTextField getTextFieldStartReportDate() {
		return textFieldStartReportDate;
	}
	public static JTextArea getTextAreaStartTsoStatement() {
		return textAreaStartTsoStatement;
	}
	public static JCheckBox getChckbxChange1539() {
		return chckbxChange1539;
	}
	public static JCheckBox getChckbxChangeSams() {
		return chckbxChangeSams;
	}
	public static JCheckBox getChckbxChangeAnalog() {
		return chckbxChangeAnalog;
	}
	public static JCheckBox getChckbxChangePassthrough() {
		return chckbxChangePassthrough;
	}
	public static JTextField getTextFieldChangeTsrNumber() {
		return textFieldChangeTsrNumber;
	}
	public static JTextField getTextFieldChangeReportDate() {
		return textFieldChangeReportDate;
	}
	public static JTextArea getTextAreaChangeTsoStatement() {
		return textAreaChangeTsoStatement;
	}
	public static JToggleButton getTglbtnChangeCHF() {
		return tglbtnChangeCHF;
	}
	public static JCheckBox getChckbxDisco1539() {
		return chckbxDisco1539;
	}
	public static JCheckBox getChckbxDiscoSams() {
		return chckbxDiscoSams;
	}
	public static JCheckBox getChckbxDiscoAnalog() {
		return chckbxDiscoAnalog;
	}
	public static JCheckBox getChckbxDiscoPassthrough() {
		return chckbxDiscoPassthrough;
	}
	public static JToggleButton getTglBtnDiscoCHF() {
		return tglBtnDiscoCHF;
	}
	public static JTextField getTextFieldDiscoTsrNumber() {
		return textFieldDiscoTsrNumber;
	}
	public static JTextField getTextFieldDiscoReportDate() {
		return textFieldDiscoReportDate;
	}
	public static JTextArea getTextAreaDiscoTsoStatement() {
		return textAreaDiscoTsoStatement;
	}
	public static JTextField getTextFieldShowAction() {
		return textFieldShowAction;
	}
	public static JTextField getTextFieldShowTrackerFullCcsd() {
		return textFieldShowTrackerFullCcsd;
	}
	public static JTextField getTextFieldShowChfLink() {
		return textFieldShowChfLink;
	}
	public static JTextField getTextFieldShowCmo() {
		return textFieldShowCmo;
	}
	public static JTextField getTextFieldShowCmoDsn() {
		return textFieldShowCmoDsn;
	}
	public static JTextField getTextFieldShowCmoComm() {
		return textFieldShowCmoComm;
	}
	public static JTextField getTextFieldShowServiceDate() {
		return textFieldShowServiceDate;
	}
	public static JTextPane getTextAreaShowComment() {
		return textAreaShowComment;
	}
	public static JCheckBox getChckbxAncs() {
		return chckbxAncs;
	}
	public static JTextField getTextFieldShowCcsd() {
		return textFieldShowCcsd;
	}
	public static JTextField getTextFieldShowTsp() {
		return textFieldShowTsp;
	}
	public static JTextField getTextFieldShowPurpose() {
		return textFieldShowPurpose;
	}
	public static JTextField getTextFieldShowRate() {
		return textFieldShowRate;
	}
	public static JTextField getTextFieldShowServiceAvailable() {
		return textFieldShowServiceAvailable;
	}
	public static JTextField getTextFieldShowFacitFullCcsd() {
		return textFieldShowFacitFullCcsd;
	}
	public static JTextArea getTextAreaShowTsoStatement() {
		return textAreaShowTsoStatement;
	}
	public static JTextField getTextFieldShowCcsd_1() {
		return textFieldShowCcsd_1;
	}
	public static JTextField getTextFieldShowAction_1() {
		return textFieldShowAction_1;
	}
	public static JTextField getTextFieldShowTsoNumber() {
		return textFieldShowTsoNumber;
	}
	public static JTextField getTextFieldShowTsrNumber() {
		return textFieldShowTsrNumber;
	}
	public static JTextField getTextFieldShowServiceDate_1() {
		return textFieldShowServiceDate_1;
	}
	public static JTextField getTextFieldShowTsoSubject() {
		return textFieldShowTsoSubject;
	}
	public static JTextField getTextFieldShowReportDate() {
		return textFieldShowReportDate;
	}
	public static JCheckBox getChckbxCompletionReportRequired() {
		return chckbxCompletionReportRequired;
	}
	public static JTextArea getTextAreaShowTsoStatement_1() {
		return textAreaShowTsoStatement_1;
	}
}
