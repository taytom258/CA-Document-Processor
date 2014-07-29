package taytom258.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import taytom258.config.Config;
import taytom258.core.util.LogHelper;
import taytom258.lib.Reference;

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
			if(!Config.debug){
				Thread.sleep(600);
			}
			progressBar.setIndeterminate(false);
			progressBar.setValue(progressBar.getValue()+1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				LogHelper.severe(e.getMessage());
		}
	}

}
