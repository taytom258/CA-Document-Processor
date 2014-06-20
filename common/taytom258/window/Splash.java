package taytom258.window;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;

import java.awt.Color;

import javax.swing.UIManager;

import taytom258.core.util.LogHelper;
import taytom258.lib.Reference;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.SystemColor;

public class Splash extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6590549531899950560L;
	private final JPanel contentPanel = new JPanel();
	private static JProgressBar progressBar = new JProgressBar();
	public static Splash dialog = new Splash();
	private final JLabel lblNewLabel = new JLabel(Reference.APPLICATION_NAME + " : " + Reference.APPLICATION_VERSION);

	/**
	 * Launch the application.
	 */
	public static void appear() {
		try {
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			progressBar.setMinimum(1);
			progressBar.setMaximum(6);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Splash() {
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setUndecorated(true);
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 233, 198);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, SystemColor.activeCaption, SystemColor.scrollbar));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		contentPanel.add(progressBar, BorderLayout.SOUTH);
		progressBar.setIndeterminate(true);
		
		JLabel lblImage = new JLabel(new ImageIcon(Splash.class.getResource("/taytom258/lib/resources/splash.jpg")));
		lblImage.setBackground(Color.WHITE);
		contentPanel.add(lblImage, BorderLayout.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPanel.add(lblNewLabel, BorderLayout.NORTH);
	}
	
	public static void incrementBar(){
		try {
		Thread.sleep(800);
		progressBar.setIndeterminate(false);
		progressBar.setValue(progressBar.getValue()+1);
		} catch (InterruptedException e) {
			e.printStackTrace();
			LogHelper.severe(e.getMessage());
		}
	}

}
