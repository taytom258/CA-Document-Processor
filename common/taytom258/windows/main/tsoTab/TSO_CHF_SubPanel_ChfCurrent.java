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
 * Sub panel inside of TSO CHF Tab showing currently found folders
 * @author taytom258
 *
 */
public class TSO_CHF_SubPanel_ChfCurrent extends JPanel {

	private static final long serialVersionUID = -3329011259554746564L;
	private JTextPane textPane_ChfCurrent;

	/**
	 * Create the panel
	 */
	public TSO_CHF_SubPanel_ChfCurrent() {
		
		this.setBackground(Color.WHITE);
		this.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Current", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		this.setBounds(7, 164, 133, 191);
		this.setLayout(null);
		
		textPane_ChfCurrent = new JTextPane();
		textPane_ChfCurrent.setToolTipText("Folders currently in the root folder");
		textPane_ChfCurrent.setEditable(false);
		textPane_ChfCurrent.setBounds(12, 22, 109, 157);
		this.add(textPane_ChfCurrent);
	}

}
