/**
 * 
 */
package taytom258.windows.main.tsoTab;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Class for sub panel inside of TSO CHF Tab showing missing folders being created
 * @author taytom258
 *
 */
public class TSO_CHF_SubPanel_ChfCreating extends JPanel {

	private static final long serialVersionUID = 8007600310999959973L;
	private JTextPane textPane_ChfCreating;

	/**
	 * Create the panel
	 */
	public TSO_CHF_SubPanel_ChfCreating() {

		this.setBackground(Color.WHITE);
		this.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Created", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
		this.setBounds(376, 164, 133, 191);
		this.setLayout(null);

		textPane_ChfCreating = new JTextPane();
		textPane_ChfCreating.setToolTipText("Folders created inside root folder");
		textPane_ChfCreating.setEditable(false);
		textPane_ChfCreating.setBounds(12, 22, 109, 157);
		this.add(textPane_ChfCreating);
	}

}
