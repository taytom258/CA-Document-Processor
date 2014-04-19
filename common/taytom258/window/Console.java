package taytom258.window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import taytom258.core.log.LogHandler;

public class Console {

	private static JFrame frmConsole;
	private JPanel panel;
	private JButton btnClear;
	private JButton btnClose;
	private JScrollPane scrollPane;
	private static JTextPane textPane;
	private JButton btnCopyLogTo;
	
	/**
	 * Launch the application.
	 */
	public static void init() {
		try {
			Console window = new Console();
			frmConsole.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public Console() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConsole = new JFrame();
		frmConsole.setAlwaysOnTop(true);
		frmConsole.setTitle("Console");
		frmConsole.setBounds(100, 100, 650, 500);
		frmConsole.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frmConsole.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		frmConsole.getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("");
				LogHandler.consoleList.clear();
			}
		});
		btnClear.setToolTipText("Clear console of all text");
		panel.add(btnClear);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmConsole.setVisible(false);
			}
		});
		
		btnCopyLogTo = new JButton("Copy Log to File");
		panel.add(btnCopyLogTo);
		btnClose.setToolTipText("Close this window");
		panel.add(btnClose);
		
		scrollPane = new JScrollPane();
		frmConsole.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
	}

	public static JFrame getFrame() {
		return frmConsole;
	}
	public static JTextPane getTextPane() {
		return textPane;
	}
}
