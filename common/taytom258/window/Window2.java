package taytom258.window;

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
import taytom258.window.core.WindowCore;

public class Window2 {

	protected JFrame frmTsoHelper;
	protected JTextField textField_TsoSubject;
	protected JTextField textField_FullCcsd;
	protected JTextField textField_ServiceDate;
	protected static JTextField textField_CmoName;
	protected static JTextField textField_Dsn;
	protected static JTextField textField_Comm;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPopupMenu popup = WindowCore.createPopup();
	private JRadioButton radioButton_Other;
	private JRadioButton radioButton_Andrews;
	private JCheckBox checkBox_Ancs;
	private JCheckBox checkBox_Logical;
	private JCheckBox checkBox_Crr;
	private JTextArea textArea_Comments;
	
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_4, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_5, null);
	}
	protected JRadioButton getRadioButton_Other() {
		return radioButton_Other;
	}
	protected JRadioButton getRadioButton_Andrews() {
		return radioButton_Andrews;
	}
	protected JCheckBox getCheckBox_Ancs() {
		return checkBox_Ancs;
	}
	protected JCheckBox getCheckBox_Logical() {
		return checkBox_Logical;
	}
	protected JCheckBox getCheckBox_Crr() {
		return checkBox_Crr;
	}
	protected JTextArea getTextArea_Comments() {
		return textArea_Comments;
	}
}
