package taytom258.testing.testCode;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class TSOManual extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7332832452153271642L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtFullccsdd;
	private JTextField txtTrunkID;
	private JTextField txtFulltsp;
	private JTextField txtTocode;
	private JTextField txtFromcode;

	/**
	 * Launch the application.
	 */
	public static void appear(String[] args) {
		try {
			TSOManual dialog = new TSOManual();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TSOManual() {
		setBounds(100, 100, 450, 517);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			txtFullccsdd = new JTextField();
			txtFullccsdd
			.setToolTipText("Full CCSD (8 characters) Get from 2A or 2O if 2A is trunk ID");
			contentPanel.add(txtFullccsdd);
			txtFullccsdd.setColumns(10);
		}
		{
			txtTrunkID = new JTextField();
			txtTrunkID
			.setToolTipText("Trunk ID (5 characters) Get from 2A if available");
			contentPanel.add(txtTrunkID);
			txtTrunkID.setColumns(10);
		}
		{
			txtFulltsp = new JTextField();
			txtFulltsp.setToolTipText("Full TSP Get from section 2B");
			contentPanel.add(txtFulltsp);
			txtFulltsp.setColumns(10);
		}
		{
			txtTocode = new JTextField();
			txtTocode
			.setToolTipText("To code, get from section 2E. All info before \"contact\"");
			contentPanel.add(txtTocode);
			txtTocode.setColumns(10);
		}
		{
			txtFromcode = new JTextField();
			txtFromcode
			.setToolTipText("From code, get from section 2E. All info before \"contact\"");
			contentPanel.add(txtFromcode);
			txtFromcode.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
