package taytom258.testing.testCode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

import taytom258.core.log.LogHelper;
import taytom258.core.util.DateUtils;
import taytom258.core.util.db.TSOCommit;
import taytom258.core.util.parsers.TSOParser;
import taytom258.core.util.popups.TSOInputNeededPop;
import taytom258.lib.Collection;
import taytom258.show.tso.ShowCHF;

public class InsertPop extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6535607711564379825L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCCSD;
	private JTextField txtTSONum;
	private static InsertPop dialog = null;
	final JToggleButton tglbtnQuit = new JToggleButton("Quit");

	/**
	 * Launch the application.
	 */
	public static void appear(File[] list) {
		try {
			Collection.tsoNum = DateUtils.fileDate();
			Collection.fullCcsd = "00000000";
			dialog = new InsertPop(list);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private InsertPop(final File[] list) {
		setBounds(100, 100, 450, 522);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblProcessingTso = new JLabel("Processing TSO "
					+ String.valueOf(MassInsert.currentNum + 1) + " of "
					+ String.valueOf(list.length));
			lblProcessingTso.setBounds(46, 29, 378, 14);
			contentPanel.add(lblProcessingTso);
		}

		String text = MassInsert.readFile(list[MassInsert.currentNum]
				.toString());
		try {
			TSOParser.parseTSO(text);
			ShowCHF.show();
			if (Collection.inputNeeded.size() > 0) {
				String[] temp = Collection.inputNeeded.get(0).split(":");
				TSOInputNeededPop pop = new TSOInputNeededPop(temp[1], temp[0]);
				pop.appear();
			} else {
				TSOCommit.run();
			}
		} catch (Exception e1) {
			// LogHelper.severe(e1.getMessage());
			e1.printStackTrace();
		}

		JLabel lblCcsd = new JLabel("CCSD");
		lblCcsd.setBounds(46, 64, 46, 14);
		contentPanel.add(lblCcsd);

		JLabel lblTsoNumber = new JLabel("TSO Number");
		lblTsoNumber.setBounds(46, 102, 60, 14);
		contentPanel.add(lblTsoNumber);

		txtCCSD = new JTextField();
		txtCCSD.setText(Collection.fullCcsd.substring(4));
		txtCCSD.setEditable(false);
		txtCCSD.setBackground(Color.WHITE);
		txtCCSD.setBounds(102, 61, 308, 20);
		contentPanel.add(txtCCSD);
		txtCCSD.setColumns(10);
		StringSelection ss = new StringSelection(txtCCSD.getText());
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(ss, null);

		txtTSONum = new JTextField();
		txtTSONum.setText(Collection.tsoNum);
		txtTSONum.setEditable(false);
		txtTSONum.setBackground(Color.WHITE);
		txtTSONum.setBounds(116, 99, 308, 20);
		contentPanel.add(txtTSONum);
		txtTSONum.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
		.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(46, 163, 336, 258);
		contentPanel.add(scrollPane);

		JTextArea txtrTsoText = new JTextArea(text);
		txtrTsoText.setEditable(false);
		txtrTsoText.setWrapStyleWord(true);
		txtrTsoText.setLineWrap(true);
		scrollPane.setViewportView(txtrTsoText);

		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

			{
				JButton btnNewButton_2 = new JButton("Completed");
				btnNewButton_2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						File f = new File(MassInsert.root + "\\Completed");
						File txt = new File(f.toString() + "\\"
								+ Collection.tsoNum + ".txt");
						try {
							FileUtils
							.moveFile(list[MassInsert.currentNum], txt);
						} catch (IOException e1) {
							LogHelper.severe(e1.getMessage());
							e1.printStackTrace();
						}
						dialog.dispose();
						dialog = null;
						Collection.fullCcsd = null;
						MassInsert.currentNum++;
						if (MassInsert.currentNum != list.length
								&& !tglbtnQuit.isSelected()) {
							InsertPop.appear(list);
						}
					}
				});
				buttonPane.add(btnNewButton_2);
			}
			{
				JButton btnNewButton_1 = new JButton("Hold");
				btnNewButton_1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						File f = new File(MassInsert.root + "\\Hold");
						File txt = new File(f.toString() + "\\"
								+ Collection.tsoNum + ".txt");
						try {
							FileUtils
							.moveFile(list[MassInsert.currentNum], txt);
						} catch (IOException e1) {
							LogHelper.severe(e1.getMessage());
							e1.printStackTrace();
						}
						dialog.dispose();
						dialog = null;
						Collection.fullCcsd = null;
						MassInsert.currentNum++;
						if (MassInsert.currentNum != list.length - 1
								&& !tglbtnQuit.isSelected()) {
							InsertPop.appear(list);
						}
					}
				});
				{
					JButton btnNewButton = new JButton("Facit");
					btnNewButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							File f = new File(MassInsert.root + "\\Facit");
							File txt = new File(f.toString() + "\\"
									+ Collection.tsoNum + ".txt");
							try {
								FileUtils.moveFile(list[MassInsert.currentNum],
										txt);
							} catch (IOException e1) {
								LogHelper.severe(e1.getMessage());
								e1.printStackTrace();
							}
							dialog.dispose();
							dialog = null;
							Collection.fullCcsd = null;
							MassInsert.currentNum++;
							;
							if (MassInsert.currentNum != list.length
									&& !tglbtnQuit.isSelected()) {
								InsertPop.appear(list);
							}
						}
					});
					buttonPane.add(btnNewButton);
				}
				buttonPane.add(btnNewButton_1);
			}

			{
				buttonPane.add(tglbtnQuit);
			}
		}
	}
}
