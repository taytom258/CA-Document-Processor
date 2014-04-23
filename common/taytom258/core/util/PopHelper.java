package taytom258.core.util;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Font;

public class PopHelper extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2605535464107680020L;
	private static final JPanel contentPanel = new JPanel();
	private static final JTextPane textPane = new JTextPane();
	private static PopHelper dialog = new PopHelper();
	
	/**
	 * Launch the application.
	 */
	public static void appear(String title, String text) {
		
		dialog.setTitle(title);
		textPane.setText(text);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public PopHelper() {
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 400, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		textPane.setEditable(false);
		textPane.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setBounds(12, 12, 370, 92);
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		contentPanel.add(textPane);
			
		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}
}
