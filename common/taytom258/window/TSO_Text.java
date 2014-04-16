package taytom258.window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.window.core.TSO_TextCore;

public class TSO_Text{

	public JFrame frmEnterTso;
	private static JTextArea text;

	/**
	 * Launch the application.
	 */
	public static void appear() {
		LogHelper.info("Text Window");
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TSO_Text window = new TSO_Text();
					window.frmEnterTso.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TSO_Text() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEnterTso = new JFrame();
		frmEnterTso.setTitle("Enter TSO");
		frmEnterTso.setBounds(100, 100, 594, 507);
		frmEnterTso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEnterTso.getContentPane().setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(493, 431, 73, 26);
		frmEnterTso.getContentPane().add(btnCancel);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TSO_TextCore.save();
				frmEnterTso.dispose();
			}
		});
		btnAccept.setBounds(12, 431, 74, 26);
		frmEnterTso.getContentPane().add(btnAccept);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 11, 556, 406);
		frmEnterTso.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		text = new JTextArea(); 
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setText(Collection.tsoText);
		JScrollPane scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scroll, BorderLayout.CENTER); 
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.setText("");
			}
		});
		btnClear.setBounds(250, 431, 64, 26);
		frmEnterTso.getContentPane().add(btnClear);
		
		
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmEnterTso.dispose();
			}
		});
	}
	protected static JTextArea getText() {
		return text;
	}
}
