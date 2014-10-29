package taytom258.windows.console;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import taytom258.config.Config;
import taytom258.core.log.LogHandler;
import taytom258.core.log.LogHelper;

/**
 * Class containing all code to create the visuals for a console window.
 * @author taytom258
 *
 */
public class Console extends ConsoleCore{
 
	private static JFrame frmConsole;
	private static JPanel panel;
	private static JButton btnClear;
	private static JButton btnClose;
	private static JScrollPane scrollPane;
	private static JTextPane textPane;
	private static HTMLEditorKit textPane_html_kit = new HTMLEditorKit();;
	private static JButton btnCopyLogTo;

	/**
	 * Initialize console window that is initially invisible.
	 */
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Console();
			}
		});
	}
	
	/**
	 * Show the console window.
	 * @param setVisible visibility state
	 */
	public static void show(boolean setVisible){
		frmConsole.setVisible(setVisible);
		LogHelper.info("Console window");
	}

	/**
	 * Constructs a new console window.
	 * @see JFrame
	 */
	private Console() {
		frmConsole = new JFrame();
		frmConsole.setAlwaysOnTop(false);
		frmConsole.setTitle("Console");
		frmConsole.setBounds(100, 100, 650, 500);
		frmConsole.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frmConsole.getContentPane().setLayout(new BorderLayout(0, 0));
		
		frmConsole.getContentPane().add(createScroll(), BorderLayout.CENTER);
		frmConsole.getContentPane().add(createPanel(), BorderLayout.SOUTH);
		
	}
	
	/**
	 * Create the console button panel.
	 * @return Console button panel
	 */
	private static JPanel createPanel(){
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		btnClear = new JButton("Clear");
		btnClear.setToolTipText("Clear console of all text");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("");
				LogHandler.consoleList.clear();
			}
		});
		panel.add(btnClear);
		
		btnClose = new JButton("Close");
		btnClose.setToolTipText("Close this window");
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmConsole.setVisible(false);
			}
		});
		panel.add(btnClose);
		
		btnCopyLogTo = new JButton("Copy Log to File");
		btnCopyLogTo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConsoleCore.logCopy(textPane.getText(), Config.LOG_PATH);
			}
		});
		panel.add(btnCopyLogTo);
		
		return panel;
	}
	
	/**
	 * Create the console scroll pane with a text pane as the viewport.
	 * @return Console scroll pane
	 */
	private static JScrollPane createScroll(){
		scrollPane = new JScrollPane();
		textPane = new JTextPane();
		
		textPane.setContentType("text/html");
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.menu);
		textPane.setEditorKit(textPane_html_kit);
		textPane.setDocument(new HTMLDocument());
		
		scrollPane.setViewportView(textPane);
		
		return scrollPane;
	}
	
	/**
	 * Insert text as HTML to console text pane
	 * @param text to be inserted
	 */
	public static void insertText(String text, Color color){
        StyledDocument doc = textPane.getStyledDocument();

        Style style = textPane.addStyle("Colors", null);
        StyleConstants.setForeground(style, color);

        try { doc.insertString(doc.getLength(), text + System.lineSeparator(), style); }
        catch (BadLocationException e){}
	}
}
