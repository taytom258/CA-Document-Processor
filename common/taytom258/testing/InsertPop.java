package taytom258.testing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

import taytom258.core.util.LogHelper;
import taytom258.core.util.db.TSOCommit;
import taytom258.core.util.parsers.TSOParser;
import taytom258.lib.Collection;
import taytom258.show.tso.ShowCHF;

public class InsertPop extends JDialog {

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
			dialog = new InsertPop(list);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private InsertPop(final File[] list) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblProcessingTso = new JLabel("Processing TSO "+String.valueOf(MassInsert.currentNum+1)+" of "+String.valueOf(list.length));
			lblProcessingTso.setBounds(46, 29, 378, 14);
			contentPanel.add(lblProcessingTso);
		}
		
		
		String text = MassInsert.readFile(list[MassInsert.currentNum].toString());
		try {
			TSOParser.parseTSO(text);
			ShowCHF.show();
			TSOCommit.run();
		} catch (Exception e1) {
			LogHelper.severe(e1.getMessage());
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
		
		txtTSONum = new JTextField();
		txtTSONum.setText(Collection.tsoNum);
		txtTSONum.setEditable(false);
		txtTSONum.setBackground(Color.WHITE);
		txtTSONum.setBounds(116, 99, 308, 20);
		contentPanel.add(txtTSONum);
		txtTSONum.setColumns(10);
		
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			{
				JButton btnNewButton_2 = new JButton("Completed");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						File f = new File(MassInsert.root+"\\Completed");
						try {
							FileUtils.moveFileToDirectory(list[MassInsert.currentNum], f, false);
						} catch (IOException e1) {
							LogHelper.severe(e1.getMessage());
							e1.printStackTrace();
						}
						dialog.dispose();
						dialog = null;
						MassInsert.currentNum++;
						if(MassInsert.currentNum != (list.length) && !tglbtnQuit.isSelected()){
							InsertPop.appear(list);
						}
					}
				});
				buttonPane.add(btnNewButton_2);
			}
			{
				JButton btnNewButton_1 = new JButton("Hold");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						File f = new File(MassInsert.root+"\\Hold");
						try {
							FileUtils.moveFileToDirectory(list[MassInsert.currentNum], f, false);
						} catch (IOException e1) {
							LogHelper.severe(e1.getMessage());
							e1.printStackTrace();
						}
						dialog.dispose();
						dialog = null;
						MassInsert.currentNum++;
						if(MassInsert.currentNum != (list.length-1) && !tglbtnQuit.isSelected()){
							InsertPop.appear(list);
						}
					}
				});
				{
					JButton btnNewButton = new JButton("Facit");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							File f = new File(MassInsert.root+"\\Facit");
							try {
								FileUtils.moveFileToDirectory(list[MassInsert.currentNum], f, false);
							} catch (IOException e1) {
								LogHelper.severe(e1.getMessage());
								e1.printStackTrace();
							}
							dialog.dispose();
							dialog = null;
							MassInsert.currentNum++;;
							if(MassInsert.currentNum != (list.length) && !tglbtnQuit.isSelected()){
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
