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
	public static JTree tree;
	
	
	private JFrame frmTsoHelper;
	public static JTextField textField;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_4;
	public static JTextField textField_5;
	public static JTextArea textArea;
	
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
		textField_2.setBounds(171, 136, 114, 20);
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
					textField_2.setEditable(true);
					textField_2.setBackground(Color.WHITE);
				}else{
					textField_2.setEnabled(false);
					textField_2.setText(null);
					textField_2.setBackground(Color.LIGHT_GRAY);
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
		chckbxComReport.setBounds(94, 164, 191, 24);
		panel.add(chckbxComReport);
		
		chckbxLogical = new JCheckBox("Logical?");
		chckbxLogical.setBounds(8, 164, 73, 24);
		panel.add(chckbxLogical);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Extra Comments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(7, 196, 283, 185);
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setToolTipText("Enter Extra Comments Here");
		textArea.setBounds(12, 23, 259, 150);
		panel_7.add(textArea);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Start", null, panel_2, null);
		panel_2.setLayout(null);
		
		chckbxSams = new JCheckBox("Sams?");
		chckbxSams.setBounds(8, 66, 65, 24);
		panel_2.add(chckbxSams);
		
		chckbxAnalog = new JCheckBox("Analog?");
		chckbxAnalog.setBounds(8, 94, 71, 24);
		panel_2.add(chckbxAnalog);
		
		chckbxPassthrough = new JCheckBox("Passthrough?");
		chckbxPassthrough.setBounds(8, 122, 105, 24);
		panel_2.add(chckbxPassthrough);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Folder Preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(121, 49, 164, 327);
		panel_2.add(panel_8);
		panel_8.setLayout(null);
		
		FolderTree.defaultInit();
		tree = new JTree(FolderTree.model);
		tree.setBounds(12, 18, 140, 304);
		panel_8.add(tree);
		
		tglbtnCHF = new JToggleButton("CHF Creation Active");
		tglbtnCHF.setBounds(81, 12, 145, 26);
		panel_2.add(tglbtnCHF);
		
		JButton btnPreview = new JButton("Preview");
		btnPreview.setBounds(8, 154, 98, 26);
		panel_2.add(btnPreview);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Change", null, panel_3, null);
		panel_3.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setBounds(171, 12, 114, 20);
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("TSO Folder Location");
		lblNewLabel_2.setBounds(12, 14, 114, 16);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNyi = new JLabel("NYI");
		lblNyi.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNyi.setBounds(138, 158, 39, 32);
		panel_3.add(lblNyi);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Disco", null, panel_6, null);
		panel_6.setLayout(null);
		
		JLabel label = new JLabel("TSO Folder Location");
		label.setBounds(12, 14, 114, 16);
		panel_6.add(label);
		
		textField_4 = new JTextField();
		textField_4.setBounds(171, 12, 114, 20);
		textField_4.setColumns(10);
		panel_6.add(textField_4);
		
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
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		tabbedPane_1.addTab("CHF", null, panel_4, null);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane_1.addTab("Facit", null, panel_5, null);
		panel_5.setLayout(null);
		
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
