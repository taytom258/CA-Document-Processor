/**
 * 
 */
package taytom258.windows.main.tsoTab;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Class for TSO DB tab
 * @author taytom258
 *
 */
public class TSO_DB_TabPane extends JTabbedPane {

	private static final long serialVersionUID = 5832577867024082207L;
	private JPanel panel_Circuit;
	protected static JTextField textFieldFullCcsdCircuit;
	private JLabel lblFullCcsd;
	private JLabel lblTrunkId;
	private JLabel lblFullTsp;
	private JLabel lblTsp;
	private JLabel lblToLocation;
	private JLabel lblFromLocation;
	private JLabel lblDepartment;
	private JLabel lblType;
	private JLabel lblUse;
	private JLabel lblSecurity;
	private JLabel lblDataRate;
	private JLabel lblFlow;
	private JLabel lblAvailable;
	private JLabel lblCmo;
	private JLabel lblCmoDsn;
	private JLabel lblCmoComm;
	private JLabel lblSignaling;
	private JLabel lblQcc;
	protected static JTextField textFieldtrunkId;
	protected static JTextField textFieldfullTsp;
	protected static JTextField textFieldtsp;
	protected static JTextField textFieldto;
	protected static JTextField textFieldfrom;
	protected static JTextField textFieldreqDepartment;
	protected static JTextField textFieldtype;
	protected static JTextField textFielduse;
	protected static JTextField textFieldsecurity;
	protected static JTextField textFieldrate;
	protected static JTextField textFieldflow;
	protected static JCheckBox chckbxAndrewsCmo;
	protected static JCheckBox chckbxAndrewsEndpoint;
	protected static JTextField textFieldavail;
	protected static JTextField textFieldcmo;
	protected static JTextField textFieldcmoDsn;
	protected static JTextField textFieldcmoComm;
	protected static JTextField textFieldsignal;
	protected static JTextField textFieldqcc;
	private JPanel panel_Tso;
	protected static JTextField textFieldTsoNum;
	private JLabel lblMrc;
	private JLabel lblReportDate;
	private JLabel lblNrc;
	private JLabel lblSvcDate;
	private JLabel lblFullCcsd2;
	private JLabel lblAction;
	private JLabel lblTsoSuffix;
	private JLabel lblTsoNumber;
	protected static JTextField textFieldTsoSuff;
	protected static JTextField textFieldaction;
	protected static JTextField textFieldFullCcsdTso;
	protected static JTextField textFieldsvcDate;
	protected static JCheckBox chckbxcrr;
	protected static JTextField textFieldreportDate;
	protected static JTextField textFieldMrc;
	protected static JTextField textFieldNrc;

	/**
	 * Create tabbed pane and add tabs to it
	 */
	public TSO_DB_TabPane() {

		this.addTab("Circuit", null, panelCircuit(), "Circuit Table");
		this.addTab("TSO", null, panelTso(), "TSO Table");
	}

	/**
	 * Create circuit panel inside of tab pane
	 * @return panel created
	 */
	private JPanel panelCircuit(){

		/*
		 * Create Panel
		 */
		panel_Circuit = new JPanel();
		panel_Circuit.setToolTipText("");
		panel_Circuit.setBackground(Color.WHITE);
		panel_Circuit.setLayout(null);

		/*
		 * Create and layout labels
		 */
		lblFullCcsd = new JLabel("Full CCSD");
		lblFullCcsd.setHorizontalAlignment(SwingConstants.LEFT);
		lblFullCcsd.setBounds(12, 12, 55, 16);
		panel_Circuit.add(lblFullCcsd);

		lblTrunkId = new JLabel("Trunk ID");
		lblTrunkId.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrunkId.setBounds(12, 40, 55, 16);
		panel_Circuit.add(lblTrunkId);

		lblFullTsp = new JLabel("Full TSP");
		lblFullTsp.setBounds(12, 68, 55, 16);
		panel_Circuit.add(lblFullTsp);

		lblTsp = new JLabel("TSP");
		lblTsp.setBounds(12, 96, 55, 16);
		panel_Circuit.add(lblTsp);

		lblToLocation = new JLabel("To");
		lblToLocation.setToolTipText("To Location");
		lblToLocation.setBounds(217, 12, 66, 16);
		panel_Circuit.add(lblToLocation);

		lblFromLocation = new JLabel("From");
		lblFromLocation.setToolTipText("From Location");
		lblFromLocation.setBounds(217, 40, 81, 16);
		panel_Circuit.add(lblFromLocation);

		lblDepartment = new JLabel("Department");
		lblDepartment.setToolTipText("Requesting Department");
		lblDepartment.setBounds(12, 294, 67, 16);
		panel_Circuit.add(lblDepartment);

		lblType = new JLabel("Type");
		lblType.setToolTipText("Type of Service");
		lblType.setBounds(217, 70, 55, 16);
		panel_Circuit.add(lblType);

		lblUse = new JLabel("Use");
		lblUse.setToolTipText("Circuit Use");
		lblUse.setBounds(217, 98, 55, 16);
		panel_Circuit.add(lblUse);

		lblSecurity = new JLabel("Security");
		lblSecurity.setBounds(12, 124, 55, 16);
		panel_Circuit.add(lblSecurity);

		lblDataRate = new JLabel("Data Rate");
		lblDataRate.setBounds(12, 152, 55, 16);
		panel_Circuit.add(lblDataRate);

		lblFlow = new JLabel("Flow");
		lblFlow.setToolTipText("Traffic Flow");
		lblFlow.setBounds(12, 180, 55, 16);
		panel_Circuit.add(lblFlow);

		lblAvailable = new JLabel("Available");
		lblAvailable.setToolTipText("Service Avaliability");
		lblAvailable.setBounds(12, 208, 55, 16);
		panel_Circuit.add(lblAvailable);

		lblCmo = new JLabel("CMO");
		lblCmo.setToolTipText("CMO/CCO");
		lblCmo.setBounds(217, 126, 55, 16);
		panel_Circuit.add(lblCmo);

		lblCmoDsn = new JLabel("CMO DSN");
		lblCmoDsn.setBounds(217, 154, 55, 16);
		panel_Circuit.add(lblCmoDsn);

		lblCmoComm = new JLabel("CMO Comm");
		lblCmoComm.setBounds(217, 182, 67, 16);
		panel_Circuit.add(lblCmoComm);

		lblSignaling = new JLabel("Signaling");
		lblSignaling.setBounds(12, 236, 55, 16);
		panel_Circuit.add(lblSignaling);

		lblQcc = new JLabel("QCC");
		lblQcc.setToolTipText("Quality Control Code");
		lblQcc.setBounds(12, 264, 55, 16);
		panel_Circuit.add(lblQcc);

		/*
		 * Create and layout textfields
		 */
		textFieldFullCcsdCircuit = new JTextField();
		textFieldFullCcsdCircuit.setBackground(Color.WHITE);
		textFieldFullCcsdCircuit.setEditable(false);
		textFieldFullCcsdCircuit.setBounds(85, 10, 114, 20);
		panel_Circuit.add(textFieldFullCcsdCircuit);
		textFieldFullCcsdCircuit.setColumns(10);

		textFieldtrunkId = new JTextField();
		textFieldtrunkId.setEditable(false);
		textFieldtrunkId.setBackground(Color.WHITE);
		textFieldtrunkId.setBounds(85, 38, 114, 20);
		textFieldtrunkId.setColumns(10);
		panel_Circuit.add(textFieldtrunkId);

		textFieldfullTsp = new JTextField();
		textFieldfullTsp.setEditable(false);
		textFieldfullTsp.setBackground(Color.WHITE);
		textFieldfullTsp.setBounds(85, 66, 114, 20);
		textFieldfullTsp.setColumns(10);
		panel_Circuit.add(textFieldfullTsp);

		textFieldtsp = new JTextField();
		textFieldtsp.setEditable(false);
		textFieldtsp.setBackground(Color.WHITE);
		textFieldtsp.setBounds(85, 94, 114, 20);
		textFieldtsp.setColumns(10);
		panel_Circuit.add(textFieldtsp);

		textFieldto = new JTextField();
		textFieldto.setEditable(false);
		textFieldto.setBackground(Color.WHITE);
		textFieldto.setBounds(301, 10, 203, 20);
		textFieldto.setColumns(10);
		panel_Circuit.add(textFieldto);

		textFieldfrom = new JTextField();
		textFieldfrom.setEditable(false);
		textFieldfrom.setBackground(Color.WHITE);
		textFieldfrom.setBounds(301, 38, 203, 20);
		panel_Circuit.add(textFieldfrom);
		textFieldfrom.setColumns(10);

		textFieldreqDepartment = new JTextField();
		textFieldreqDepartment.setEditable(false);
		textFieldreqDepartment.setBackground(Color.WHITE);
		textFieldreqDepartment.setBounds(96, 292, 403, 20);
		textFieldreqDepartment.setColumns(10);
		panel_Circuit.add(textFieldreqDepartment);

		textFieldtype = new JTextField();
		textFieldtype.setEditable(false);
		textFieldtype.setBackground(Color.WHITE);
		textFieldtype.setBounds(301, 68, 203, 20);
		textFieldtype.setColumns(10);
		panel_Circuit.add(textFieldtype);

		textFielduse = new JTextField();
		textFielduse.setEditable(false);
		textFielduse.setBackground(Color.WHITE);
		textFielduse.setBounds(301, 96, 203, 20);
		textFielduse.setColumns(10);
		panel_Circuit.add(textFielduse);

		textFieldsecurity = new JTextField();
		textFieldsecurity.setEditable(false);
		textFieldsecurity.setBackground(Color.WHITE);
		textFieldsecurity.setBounds(85, 122, 114, 20);
		textFieldsecurity.setColumns(10);
		panel_Circuit.add(textFieldsecurity);

		textFieldrate = new JTextField();
		textFieldrate.setEditable(false);
		textFieldrate.setBackground(Color.WHITE);
		textFieldrate.setBounds(85, 150, 114, 20);
		textFieldrate.setColumns(10);
		panel_Circuit.add(textFieldrate);

		textFieldflow = new JTextField();
		textFieldflow.setEditable(false);
		textFieldflow.setEnabled(true);
		textFieldflow.setBackground(Color.WHITE);
		textFieldflow.setText("");
		textFieldflow.setBounds(85, 178, 114, 20);
		textFieldflow.setColumns(10);
		panel_Circuit.add(textFieldflow);

		textFieldavail = new JTextField();
		textFieldavail.setEditable(false);
		textFieldavail.setBackground(Color.WHITE);
		textFieldavail.setBounds(85, 206, 114, 20);
		textFieldavail.setColumns(10);
		panel_Circuit.add(textFieldavail);

		textFieldcmo = new JTextField();
		textFieldcmo.setEditable(false);
		textFieldcmo.setBackground(Color.WHITE);
		textFieldcmo.setBounds(301, 124, 203, 20);
		textFieldcmo.setColumns(10);
		panel_Circuit.add(textFieldcmo);

		textFieldcmoDsn = new JTextField();
		textFieldcmoDsn.setEditable(false);
		textFieldcmoDsn.setBackground(Color.WHITE);
		textFieldcmoDsn.setBounds(301, 152, 203, 20);
		textFieldcmoDsn.setColumns(10);
		panel_Circuit.add(textFieldcmoDsn);

		textFieldcmoComm = new JTextField();
		textFieldcmoComm.setEditable(false);
		textFieldcmoComm.setBackground(Color.WHITE);
		textFieldcmoComm.setBounds(301, 180, 203, 20);
		textFieldcmoComm.setColumns(10);
		panel_Circuit.add(textFieldcmoComm);

		textFieldsignal = new JTextField();
		textFieldsignal.setEditable(false);
		textFieldsignal.setBackground(Color.WHITE);
		textFieldsignal.setBounds(85, 234, 114, 20);
		textFieldsignal.setColumns(10);
		panel_Circuit.add(textFieldsignal);

		textFieldqcc = new JTextField();
		textFieldqcc.setEditable(false);
		textFieldqcc.setBackground(Color.WHITE);
		textFieldqcc.setBounds(85, 262, 114, 20);
		textFieldqcc.setColumns(10);
		panel_Circuit.add(textFieldqcc);

		/*
		 * Create and layout Check Boxes
		 */
		chckbxAndrewsCmo = new JCheckBox("Andrews or Bolling CMO");
		chckbxAndrewsCmo.setEnabled(false);
		chckbxAndrewsCmo.setToolTipText("Are we the CMO?");
		chckbxAndrewsCmo.setBackground(Color.WHITE);
		chckbxAndrewsCmo.setBounds(217, 208, 166, 24);
		panel_Circuit.add(chckbxAndrewsCmo);

		chckbxAndrewsEndpoint = new JCheckBox("Andrews or Bolling Endpoint");
		chckbxAndrewsEndpoint.setEnabled(false);
		chckbxAndrewsEndpoint.setBackground(Color.WHITE);
		chckbxAndrewsEndpoint.setToolTipText("Are we an endpoint of this circuit?");
		chckbxAndrewsEndpoint.setBounds(217, 236, 184, 24);
		panel_Circuit.add(chckbxAndrewsEndpoint);

		return panel_Circuit;

	}

	/**
	 * Create tso panel inside of tab pane
	 * @return panel created
	 */
	private JPanel panelTso(){

		/*
		 * Create Panel
		 */
		panel_Tso = new JPanel();
		panel_Tso.setBackground(Color.WHITE);
		panel_Tso.setLayout(null);


		textFieldTsoNum = new JTextField();
		textFieldTsoNum.setBackground(Color.WHITE);
		textFieldTsoNum.setEditable(false);
		textFieldTsoNum.setBounds(102, 10, 153, 20);
		textFieldTsoNum.setColumns(10);
		panel_Tso.add(textFieldTsoNum);

		textFieldTsoSuff = new JTextField();
		textFieldTsoSuff.setBackground(Color.WHITE);
		textFieldTsoSuff.setEditable(false);
		textFieldTsoSuff.setBounds(102, 38, 153, 20);
		textFieldTsoSuff.setColumns(10);
		panel_Tso.add(textFieldTsoSuff);

		textFieldaction = new JTextField();
		textFieldaction.setBackground(Color.WHITE);
		textFieldaction.setEditable(false);
		textFieldaction.setBounds(102, 66, 153, 20);
		textFieldaction.setColumns(10);
		panel_Tso.add(textFieldaction);

		textFieldFullCcsdTso = new JTextField();
		textFieldFullCcsdTso.setBackground(Color.WHITE);
		textFieldFullCcsdTso.setEditable(false);
		textFieldFullCcsdTso.setBounds(102, 94, 153, 20);
		textFieldFullCcsdTso.setColumns(10);
		panel_Tso.add(textFieldFullCcsdTso);

		textFieldsvcDate = new JTextField();
		textFieldsvcDate.setBackground(Color.WHITE);
		textFieldsvcDate.setEditable(false);
		textFieldsvcDate.setBounds(102, 150, 153, 20);
		textFieldsvcDate.setColumns(10);
		panel_Tso.add(textFieldsvcDate);

		textFieldreportDate = new JTextField();
		textFieldreportDate.setBackground(Color.WHITE);
		textFieldreportDate.setEditable(false);
		textFieldreportDate.setBounds(102, 122, 153, 20);
		textFieldreportDate.setColumns(10);
		panel_Tso.add(textFieldreportDate);

		textFieldMrc = new JTextField();
		textFieldMrc.setForeground(Color.BLACK);
		textFieldMrc.setBackground(Color.WHITE);
		textFieldMrc.setEditable(false);
		textFieldMrc.setBounds(317, 10, 72, 20);
		textFieldMrc.setColumns(10);
		panel_Tso.add(textFieldMrc);

		textFieldNrc = new JTextField();
		textFieldNrc.setForeground(Color.BLACK);
		textFieldNrc.setBackground(Color.WHITE);
		textFieldNrc.setEditable(false);
		textFieldNrc.setColumns(10);
		textFieldNrc.setBounds(317, 38, 72, 20);
		panel_Tso.add(textFieldNrc);

		/*
		 * Create and layout labels
		 */
		lblMrc = new JLabel("MRC");
		lblMrc.setToolTipText("Monthly Recurring Cost");
		lblMrc.setBounds(273, 12, 27, 16);
		panel_Tso.add(lblMrc);

		lblReportDate = new JLabel("Report Date");
		lblReportDate.setBounds(12, 124, 67, 16);
		panel_Tso.add(lblReportDate);

		lblNrc = new JLabel("NRC");
		lblNrc.setToolTipText("Non Recurring Cost");
		lblNrc.setBounds(273, 40, 27, 16);
		panel_Tso.add(lblNrc);

		lblSvcDate = new JLabel("Svc Date");
		lblSvcDate.setToolTipText("Service Date");
		lblSvcDate.setBounds(12, 152, 55, 16);
		panel_Tso.add(lblSvcDate);

		lblFullCcsd2 = new JLabel("Full CCSD");
		lblFullCcsd2.setBounds(12, 96, 58, 16);
		panel_Tso.add(lblFullCcsd2);

		lblAction = new JLabel("Action");
		lblAction.setBounds(12, 68, 36, 16);
		panel_Tso.add(lblAction);

		lblTsoSuffix = new JLabel("TSO Suffix");
		lblTsoSuffix.setBounds(12, 40, 60, 16);
		panel_Tso.add(lblTsoSuffix);

		lblTsoNumber = new JLabel("TSO Number");
		lblTsoNumber.setBounds(12, 12, 72, 16);
		panel_Tso.add(lblTsoNumber);

		/*
		 * Create and layout checkbox
		 */
		chckbxcrr = new JCheckBox("Completion Report Required?");
		chckbxcrr.setEnabled(false);
		chckbxcrr.setBackground(Color.WHITE);
		chckbxcrr.setBounds(12, 176, 191, 24);
		panel_Tso.add(chckbxcrr);

		return panel_Tso;

	}
}
