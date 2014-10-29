/**
 * 
 */
package taytom258.windows.main.tsoTab;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import taytom258.config.Config;
import taytom258.core.util.popups.ContextMenu;
import taytom258.lib.Collection;

/**
 * Class for TSO Facit tab
 * @author taytom258
 *
 */
public class TSO_Facit_Panel extends JScrollPane {

	private static final long serialVersionUID = -5498484994513201035L;
	private JLabel label_Ccsd;
	private JLabel label_Tsp;
	private JLabel label_Purpose;
	private JLabel label_Rate;
	private JLabel label_SvcAvail;
	private JLabel label_FullCcsd;
	private JLabel label_Action;
	private JLabel label_TsoNum;
	private JLabel label_TsrNum;
	private JLabel label_ServiceDate;
	private JLabel label_TsoSubject;
	private JLabel label_ReportDate;
	private JTextField textField_Ccsd;
	private JPopupMenu popup = ContextMenu.copy();
	private JTextField textField_Tsp;
	private JTextField textField_Purpose;
	private JTextField textField_Rate;
	private JTextField textField_SvcAvailable;
	private JTextField textField_FullCcsd;
	private JTextField textField_Action;
	private JTextField textField_TsoNum;
	private JTextField textField_TsrNum;
	private JTextField textField_SvcDate;
	private JTextField textField_TsoSubject;
	private JTextField textField_ReportDate;
	private JCheckBox chckbx_Crr;
	private JCheckBox chckbx_IsTrunk;
	private JLabel label_Amending;
	private JButton btnTextToClipboard;
	private JButton btnStateToClipboard;
	private JTextField textField_Amending;
	private JTextField textField_TrunkID;
	private JLabel label_TrunkId;
	
	/**
	 * Create the panel
	 */
	
	public TSO_Facit_Panel() {
		
		this.setBackground(Color.WHITE);
		this.setLayout(null);

		/*
		 * Create and layout labels
		 */
		label_Ccsd = new JLabel("CCSD");
		label_Ccsd.setBounds(12, 12, 32, 16);
		this.add(label_Ccsd);

		label_Tsp = new JLabel("TSP");
		label_Tsp.setBounds(12, 68, 23, 16);
		this.add(label_Tsp);

		label_Purpose = new JLabel("Purpose");
		label_Purpose.setBounds(12, 96, 48, 16);
		this.add(label_Purpose);

		label_Rate = new JLabel("Rate");
		label_Rate.setBounds(12, 124, 26, 16);
		this.add(label_Rate);

		label_SvcAvail = new JLabel("Service Availability");
		label_SvcAvail.setBounds(12, 152, 106, 16);
		this.add(label_SvcAvail);

		label_FullCcsd = new JLabel("Full CCSD");
		label_FullCcsd.setBounds(261, 14, 54, 16);
		this.add(label_FullCcsd);

		label_Action = new JLabel("Action");
		label_Action.setBounds(261, 42, 36, 16);
		this.add(label_Action);

		label_TsoNum = new JLabel("TSO Number");
		label_TsoNum.setBounds(12, 228, 72, 16);
		this.add(label_TsoNum);

		label_TsrNum = new JLabel("TSR Number");
		label_TsrNum.setBounds(261, 98, 71, 16);
		this.add(label_TsrNum);

		label_ServiceDate = new JLabel("Service Date");
		label_ServiceDate.setBounds(261, 126, 72, 16);
		this.add(label_ServiceDate);

		label_TsoSubject = new JLabel("TSO Subject");
		label_TsoSubject.setBounds(12, 258, 70, 16);
		this.add(label_TsoSubject);

		label_ReportDate = new JLabel("Report Date");
		label_ReportDate.setBounds(261, 154, 67, 16);
		this.add(label_ReportDate);
		
		label_Amending = new JLabel("Amending");
		label_Amending.setBounds(261, 68, 57, 16);
		this.add(label_Amending);
		
		label_TrunkId = new JLabel("Trunk ID");
		label_TrunkId.setBounds(12, 40, 47, 16);
		this.add(label_TrunkId);

		/*
		 * Create and layout textfields with actions on mouse release
		 */
		textField_Ccsd = new JTextField();
		textField_Ccsd.setToolTipText("4 Character circuit ID");
		textField_Ccsd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_Ccsd.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_Ccsd.setBackground(Color.WHITE);
		textField_Ccsd.setEditable(false);
		textField_Ccsd.setBounds(136, 10, 106, 20);
		textField_Ccsd.setColumns(10);
		this.add(textField_Ccsd);

		textField_Tsp = new JTextField();
		textField_Tsp.setToolTipText("Circuit Priority");
		textField_Tsp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_Tsp.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_Tsp.setBackground(Color.WHITE);
		textField_Tsp.setEditable(false);
		textField_Tsp.setBounds(136, 66, 106, 20);
		textField_Tsp.setColumns(10);
		this.add(textField_Tsp);

		textField_Purpose = new JTextField();
		textField_Purpose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_Purpose.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_Purpose.setBackground(Color.WHITE);
		textField_Purpose.setEditable(false);
		textField_Purpose.setBounds(136, 94, 106, 20);
		textField_Purpose.setColumns(10);
		this.add(textField_Purpose);

		textField_Rate = new JTextField();
		textField_Rate.setToolTipText("Data Rate");
		textField_Rate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_Rate.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_Rate.setBackground(Color.WHITE);
		textField_Rate.setEditable(false);
		textField_Rate.setBounds(136, 122, 106, 20);
		textField_Rate.setColumns(10);
		this.add(textField_Rate);

		textField_SvcAvailable = new JTextField();
		textField_SvcAvailable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_SvcAvailable.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_SvcAvailable.setBackground(Color.WHITE);
		textField_SvcAvailable.setEditable(false);
		textField_SvcAvailable.setBounds(136, 150, 106, 20);
		textField_SvcAvailable.setColumns(10);
		this.add(textField_SvcAvailable);

		textField_FullCcsd = new JTextField();
		textField_FullCcsd.setToolTipText("8 Character circuit ID");
		textField_FullCcsd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_FullCcsd.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_FullCcsd.setBackground(Color.WHITE);
		textField_FullCcsd.setEditable(false);
		textField_FullCcsd.setBounds(385, 12, 106, 20);
		textField_FullCcsd.setColumns(10);
		this.add(textField_FullCcsd);

		textField_Action = new JTextField();
		textField_Action.setToolTipText("Action of TSO");
		textField_Action.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_Action.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_Action.setBackground(Color.WHITE);
		textField_Action.setEditable(false);
		textField_Action.setBounds(385, 40, 106, 20);
		textField_Action.setColumns(10);
		this.add(textField_Action);

		textField_TsoNum = new JTextField();
		textField_TsoNum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_TsoNum.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_TsoNum.setBackground(Color.WHITE);
		textField_TsoNum.setEditable(false);
		textField_TsoNum.setBounds(136, 226, 355, 20);
		textField_TsoNum.setColumns(10);
		this.add(textField_TsoNum);

		textField_TsrNum = new JTextField();
		textField_TsrNum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_TsrNum.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_TsrNum.setBackground(Color.WHITE);
		textField_TsrNum.setEditable(false);
		textField_TsrNum.setBounds(385, 96, 106, 20);
		textField_TsrNum.setColumns(10);
		this.add(textField_TsrNum);

		textField_SvcDate = new JTextField();
		textField_SvcDate.setToolTipText("");
		textField_SvcDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_SvcDate.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_SvcDate.setBackground(Color.WHITE);
		textField_SvcDate.setEditable(false);
		textField_SvcDate.setBounds(385, 124, 106, 20);
		textField_SvcDate.setColumns(10);
		this.add(textField_SvcDate);

		textField_TsoSubject = new JTextField();
		textField_TsoSubject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_TsoSubject.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_TsoSubject.setBackground(Color.WHITE);
		textField_TsoSubject.setEditable(false);
		textField_TsoSubject.setBounds(136, 256, 355, 20);
		textField_TsoSubject.setColumns(10);
		this.add(textField_TsoSubject);

		textField_ReportDate = new JTextField();
		textField_ReportDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_ReportDate.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_ReportDate.setBackground(Color.WHITE);
		textField_ReportDate.setEditable(false);
		textField_ReportDate.setBounds(385, 152, 106, 20);
		textField_ReportDate.setColumns(10);
		this.add(textField_ReportDate);
		
		textField_Amending = new JTextField();
		textField_Amending.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_Amending.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_Amending.setToolTipText("");
		textField_Amending.setEditable(false);
		textField_Amending.setColumns(10);
		textField_Amending.setBackground(Color.WHITE);
		textField_Amending.setBounds(385, 66, 106, 20);
		this.add(textField_Amending);

		textField_TrunkID = new JTextField();
		textField_TrunkID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && Config.autoSelection) {
					e.getComponent().requestFocus();
					textField_TrunkID.selectAll();
					popup.show(e.getComponent(), e.getX(), e.getY());
				} else if (e.isPopupTrigger()) {
					e.getComponent().requestFocus();
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		textField_TrunkID.setToolTipText("");
		textField_TrunkID.setEditable(false);
		textField_TrunkID.setColumns(10);
		textField_TrunkID.setBackground(Color.WHITE);
		textField_TrunkID.setBounds(136, 40, 106, 20);
		this.add(textField_TrunkID);

		/*
		 * Create and layout checkboxes
		 */
		chckbx_Crr = new JCheckBox("Completion Report Required");
		chckbx_Crr.setEnabled(false);
		chckbx_Crr.setBackground(Color.WHITE);
		chckbx_Crr.setBounds(307, 327, 184, 24);
		this.add(chckbx_Crr);
		
		chckbx_IsTrunk = new JCheckBox("Circuit is Trunk");
		chckbx_IsTrunk.setEnabled(false);
		chckbx_IsTrunk.setBackground(Color.WHITE);
		chckbx_IsTrunk.setBounds(307, 299, 112, 24);
		this.add(chckbx_IsTrunk);

		/*
		 * Create and layout buttons with actions
		 */
		btnTextToClipboard = new JButton("TSO Text to Clipboard");
		btnTextToClipboard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection ss = new StringSelection(Collection.tsoText);
				Clipboard clpbrd = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				clpbrd.setContents(ss, null);
			}
		});
		btnTextToClipboard.setBounds(12, 288, 160, 26);
		this.add(btnTextToClipboard);

		btnStateToClipboard = new JButton("TSO Statement to Clipboard");
		btnStateToClipboard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection ss = new StringSelection(Collection.purpose);
				Clipboard clpbrd = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				clpbrd.setContents(ss, null);
			}
		});
		btnStateToClipboard.setBounds(12, 326, 191, 26);
		this.add(btnStateToClipboard);
		
	}
}
