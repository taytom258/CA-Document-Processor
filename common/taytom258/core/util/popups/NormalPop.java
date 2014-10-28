package taytom258.core.util.popups;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
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

public class NormalPop extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2605535464107680020L;
	private static final JPanel contentPanel = new JPanel();
	private static final JTextPane textPane = new JTextPane();
	private static NormalPop dialog = new NormalPop();

	/**
	 * Launch the application.
	 */
	public static NormalPop appear(String title, String text) {

		dialog.setTitle(title);
		textPane.setText(text);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		return dialog;
	}

	/**
	 * Create the dialog.
	 */
	public NormalPop() {
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
