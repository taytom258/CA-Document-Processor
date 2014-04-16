package taytom258.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import taytom258.lib.Strings;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Help extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6742154977741087647L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void appear() {
		try {
			Help dialog = new Help();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Help() {
		setBounds(100, 100, 450, 276);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			JTextPane textPane = new JTextPane();
			textPane.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if(e.isPopupTrigger()){
						e.getComponent().requestFocus();
						Window.popup.show(e.getComponent(), e.getX(), e.getY());
					}else if(e.getButton() == 1){
						e.getComponent().requestFocus();
					}
				}
			});
			textPane.setEditable(false);
			textPane.setText(Strings.CREDITS);
			textPane.setBackground(UIManager.getColor("Button.background"));
			contentPanel.add(textPane);
		}
		{
			JTextPane textPane = new JTextPane();
			textPane.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if(e.isPopupTrigger()){
						e.getComponent().requestFocus();
						Window.popup.show(e.getComponent(), e.getX(), e.getY());
					}else if(e.getButton() == 1){
						e.getComponent().requestFocus();
					}
				}
			});
			textPane.setEditable(false);
			textPane.setText(Strings.HELP);
			textPane.setBackground(UIManager.getColor("Button.background"));
			contentPanel.add(textPane);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
