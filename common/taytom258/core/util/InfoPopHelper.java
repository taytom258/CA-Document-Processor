package taytom258.core.util;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class InfoPopHelper extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2605535464107680020L;
	private final JPanel contentPanel = new JPanel();
	private static JTextArea textArea = new JTextArea();
	private static InfoPopHelper dialog = new InfoPopHelper();
	
	/**
	 * Launch the application.
	 */
	public static void appear(String title, String text) {
		
		dialog.setTitle(title);
		textArea.setText(text);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public InfoPopHelper() {
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 400, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textArea.setBounds(12, 42, 370, 62);
			textArea.setBackground(UIManager.getColor("Button.background"));
			textArea.setEditable(false);
			textArea.setWrapStyleWord(true);
			textArea.setLineWrap(true);
			textArea.setFont(new Font("Dialog", Font.BOLD, 13));
			contentPanel.add(textArea);
		}
		{
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
}
