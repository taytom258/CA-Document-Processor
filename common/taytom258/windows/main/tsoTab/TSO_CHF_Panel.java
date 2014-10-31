/**
 * 
 */
package taytom258.windows.main.tsoTab;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import taytom258.config.Config;
import taytom258.core.util.popups.ContextMenu;

/**
 * Class for TSO CHF Tab
 * @author taytom258
 *
 */
public class TSO_CHF_Panel extends JPanel {

	private static final long serialVersionUID = -3500353515226347640L;
	public static JTextField textField_TsoName;
	private JLabel label_TsoName;
	private JLabel lblRoot;
	public JTextField textField_Root;
	private JPanel panelCreating = new TSO_CHF_SubPanel_ChfCreating();
	private JPanel panelCurrent = new TSO_CHF_SubPanel_ChfCurrent();
	private JRadioButton rdbtn_Root;
	private ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JRadioButton rdbtn_RootCreated;
	public JTextField textField_Link;
	private JPopupMenu popup = ContextMenu.copy();

	/**
	 * Create the panel
	 */
	public TSO_CHF_Panel() {

		setToolTipText("");
		setLayout(null);
		setBackground(Color.WHITE);

		label_TsoName = new JLabel("TSO Name");
		label_TsoName.setBounds(12, 14, 60, 16);
		this.add(label_TsoName);

		lblRoot = new JLabel(" Root");
		lblRoot.setBounds(12, 42, 51, 16);
		this.add(lblRoot);

		JLabel lblLink = new JLabel(" Link");
		lblLink.setBounds(12, 68, 55, 16);
		this.add(lblLink);

		textField_TsoName = new JTextField();
		textField_TsoName.setToolTipText("TSO file name");
		textField_TsoName.setBackground(Color.WHITE);
		textField_TsoName.setEditable(false);
		textField_TsoName.setBounds(102, 12, 407, 20);
		textField_TsoName.setColumns(10);
		this.add(textField_TsoName);

		textField_Root = new JTextField();
		textField_Root.setToolTipText("Root folder name");
		textField_Root.setBackground(Color.WHITE);
		textField_Root.setEditable(false);
		textField_Root.setBounds(102, 40, 407, 20);
		textField_Root.setColumns(10);
		this.add(textField_Root);

		textField_Link = new JTextField();
		textField_Link.setToolTipText("Root folder location");
		textField_Link.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_Link.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_Link.setBackground(Color.WHITE);
		textField_Link.setEditable(false);
		textField_Link.setBounds(102, 66, 407, 20);
		textField_Link.setColumns(10);
		this.add(textField_Link);

		rdbtn_Root = new JRadioButton("Root Exists");
		rdbtn_Root.setToolTipText("Does the root folder exist?");
		buttonGroup_1.add(rdbtn_Root);
		rdbtn_Root.setEnabled(false);
		rdbtn_Root.setBackground(Color.WHITE);
		rdbtn_Root.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtn_Root.setBounds(27, 132, 89, 24);
		this.add(rdbtn_Root);

		rdbtn_RootCreated = new JRadioButton("Root Created");
		rdbtn_RootCreated.setToolTipText("Was the root folder created?");
		buttonGroup_1.add(rdbtn_RootCreated);
		rdbtn_RootCreated.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtn_RootCreated.setEnabled(false);
		rdbtn_RootCreated.setBackground(Color.WHITE);
		rdbtn_RootCreated.setBounds(394, 132, 99, 24);
		this.add(rdbtn_RootCreated);

		this.add(panelCreating);
		this.add(panelCurrent);
	}


}
