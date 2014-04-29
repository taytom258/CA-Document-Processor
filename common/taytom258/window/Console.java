package taytom258.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import taytom258.core.WriteHandler;
import taytom258.core.log.LogHandler;
import taytom258.core.util.LogHelper;
import taytom258.core.util.PopHelper;
import taytom258.lib.Reference;

public class Console {

	private static JFrame frmConsole;
	private JPanel panel;
	private JButton btnClear;
	private JButton btnClose;
	private JButton btnCopyLogTo;
	private JScrollPane scrollPane;
	private static JTextPane textPane = new JTextPane();
	private static HTMLEditorKit textPane_html_kit = new HTMLEditorKit();
	private JTextArea textArea;
	
	/**
	 * Launch the application.
	 */
	public static void init() {
		try {
			Console window = new Console();
			frmConsole.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.severe(e.getMessage());
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
		frmConsole.setAlwaysOnTop(false);
		frmConsole.setTitle("Console");
		frmConsole.setBounds(100, 100, 650, 500);
		frmConsole.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frmConsole.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		frmConsole.getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("");
				LogHandler.consoleList.clear();
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnClear.setToolTipText("Clear console of all text");
		panel.add(btnClear);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmConsole.setVisible(false);
			}
		});
		
		final File LOG = new File(Reference.USER_ROOT.toString()+"\\log.txt");
		btnCopyLogTo = new JButton("Copy Log to File");
		btnCopyLogTo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LOG.exists()){
					LOG.delete();
				}
				WriteHandler.fileWriter(textPane.getText().trim().replaceAll("\\<.*?\\>", ""), LOG);
				PopHelper.appear("Saved", "Log file saved to "+LOG.toString());
			}
		});
		panel.add(btnCopyLogTo);
		btnClose.setToolTipText("Close this window");
		panel.add(btnClose);
		
		scrollPane = new JScrollPane();
		frmConsole.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textPane.setContentType("text/html");
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.menu);
		textPane.setEditorKit(textPane_html_kit);
		textPane.setDocument(new HTMLDocument());
		scrollPane.setViewportView(textPane);
		
	}
	
	public static void insertHTML (String str, String fontColor) throws BadLocationException, IOException{
		String line = "<div><font color="+fontColor+">"+str+"</font></div>";
		textPane_html_kit.insertHTML((HTMLDocument) textPane.getDocument(), textPane.getDocument().getLength(), line, 0, 0, null);
	}
	
	public static JFrame getFrame() {
		return frmConsole;
	}
}
