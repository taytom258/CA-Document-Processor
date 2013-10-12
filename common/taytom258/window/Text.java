package taytom258.window;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import taytom258.lib.util.LogHelper;
import taytom258.window.core.TextCore;

public class Text extends TextCore{

	private JFrame frmEnterTso;

	/**
	 * Launch the application.
	 */
	public static void appear() {
		LogHelper.info("Text Window");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Text window = new Text();
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
	public Text() {
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
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(493, 431, 73, 26);
		frmEnterTso.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Accept");
		btnNewButton.setBounds(12, 431, 74, 26);
		frmEnterTso.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Enter TSO", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 554, 393);
		frmEnterTso.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setToolTipText("Enter TSO Here");
		textArea.setBounds(12, 23, 530, 358);
		panel.add(textArea);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEnterTso.dispose();
			}
		});
	}
}
