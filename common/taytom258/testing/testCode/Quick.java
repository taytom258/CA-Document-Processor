package taytom258.testing.testCode;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

import taytom258.core.util.DateUtils;
import taytom258.core.util.db.TSOCommit;
import taytom258.core.util.parsers.TSOParser;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.show.tso.ShowCHF;

public class Quick extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1642258689533915773L;

	private static Quick dialog = null;
	JLabel lblProcessingTso = null;
	JProgressBar progressBar = null;
	Thread runner = null;

	private final JPanel contentPanel = new JPanel();
	private JToggleButton tglbtnDvilleBwine = null;
	private JToggleButton tglbtnBolling = null;

	/**
	 * Launch the application.
	 */
	public static void show(File[] list) {
		try {
			dialog = new Quick(list);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Quick(final File[] list) {
		setBounds(100, 100, 450, 137);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			progressBar = new JProgressBar();
			progressBar.setBounds(10, 41, 414, 14);
			progressBar.setMinimum(0);
			progressBar.setMaximum(list.length - 1);
			progressBar.setIndeterminate(true);
			contentPanel.add(progressBar);
		}
		{
			lblProcessingTso = new JLabel("Processing TSO "
					+ String.valueOf(MassInsert.currentNum + 1) + " of "
					+ String.valueOf(list.length));
			lblProcessingTso.setBounds(10, 11, 414, 14);
			contentPanel.add(lblProcessingTso);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			{
				final JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dialog.dispose();
					}
				});
				{
					tglbtnBolling = new JToggleButton("Bolling");
					buttonPane.add(tglbtnBolling);
				}
				{
					tglbtnDvilleBwine = new JToggleButton("Dville & Bwine");
					buttonPane.add(tglbtnDvilleBwine);
				}
				buttonPane.add(btnCancel);

				final JButton btnRun = new JButton("Run");
				btnRun.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dialog.setCursor(Cursor
								.getPredefinedCursor(Cursor.WAIT_CURSOR));
						btnCancel.setVisible(false);
						btnRun.setVisible(false);
						runner = new Thread(new Runnable() {
							@Override
							public void run() {
								loopTexts(list);
							}
						});
						runner.setName("Process");
						runner.setPriority(Thread.NORM_PRIORITY);
						progressBar.setIndeterminate(false);
						runner.start();
					}
				});
				buttonPane.add(btnRun);
			}
		}
	}

	private void loopTexts(File[] list) {
		for (File element : list) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lblProcessingTso.setText("Processing TSO "
					+ String.valueOf(MassInsert.currentNum + 1) + " of "
					+ String.valueOf(list.length));
			progressBar.setValue(MassInsert.currentNum);
			String text = MassInsert.readFile(list[MassInsert.currentNum]
					.toString());

			try {
				Collection.tsoNum = DateUtils.fileDate();
				Collection.fullCcsd = "00000000";
				TSOParser.parseTSO(text);
				if (tglbtnDvilleBwine.isSelected()) {
					if (Collection.cmo.equals(Strings.ANDREWS_CMO)) {

						if (Collection.inputNeeded.size() > 0) {
							// String[] temp =
							// Collection.inputNeeded.get(0).split(":");
							// TSOPopHelper pop = new TSOPopHelper(temp[1],
							// temp[0]);
							// pop.appear();
							File f = new File(MassInsert.root + "\\InputNeeded");
							File txt = new File(f.toString() + "\\"
									+ Collection.tsoNum + ".txt");
							try {
								FileUtils.moveFile(list[MassInsert.currentNum],
										txt);
							} catch (IOException e1) {
								e1.printStackTrace();
							}

						} else {
							ShowCHF.show();
							TSOCommit.run();

							File f = new File(MassInsert.root + "\\Auto");
							File txt = new File(f.toString() + "\\"
									+ Collection.tsoNum + ".txt");
							try {
								FileUtils.moveFile(list[MassInsert.currentNum],
										txt);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
					MassInsert.currentNum++;
				} else if (tglbtnBolling.isSelected()) {
					if (Collection.cmo.equals(Strings.BOLLING_CMO) || !Collection.location.equals(Strings.LOCATIONS[5])) {

						if (Collection.inputNeeded.size() > 0) {
							// String[] temp =
							// Collection.inputNeeded.get(0).split(":");
							// TSOPopHelper pop = new TSOPopHelper(temp[1],
							// temp[0]);
							// pop.appear();
							File f = new File(MassInsert.root + "\\InputNeeded");
							File txt = new File(f.toString() + "\\"
									+ Collection.tsoNum + ".txt");
							try {
								FileUtils.moveFile(list[MassInsert.currentNum],
										txt);
							} catch (IOException e1) {
								e1.printStackTrace();
							}

						} else {
							ShowCHF.show();
							TSOCommit.run();

							File f = new File(MassInsert.root + "\\Auto");
							File txt = new File(f.toString() + "\\"
									+ Collection.tsoNum + ".txt");
							try {
								FileUtils.moveFile(list[MassInsert.currentNum],
										txt);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
					MassInsert.currentNum++;
				} else {
					if (Collection.inputNeeded.size() > 0) {
						// String[] temp =
						// Collection.inputNeeded.get(0).split(":");
						// TSOPopHelper pop = new TSOPopHelper(temp[1],
						// temp[0]);
						// pop.appear();
						File f = new File(MassInsert.root + "\\InputNeeded");
						File txt = new File(f.toString() + "\\"
								+ Collection.tsoNum + ".txt");
						try {
							FileUtils
							.moveFile(list[MassInsert.currentNum], txt);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					} else {
						ShowCHF.show();
						TSOCommit.run();

						File f = new File(MassInsert.root + "\\Auto");
						File txt = new File(f.toString() + "\\"
								+ Collection.tsoNum + ".txt");
						try {
							FileUtils
							.moveFile(list[MassInsert.currentNum], txt);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					MassInsert.currentNum++;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				File f = new File(MassInsert.root + "\\Error");
				File txt = new File(f.toString() + "\\" + Collection.tsoNum
						+ ".txt");
				try {
					FileUtils.moveFile(list[MassInsert.currentNum], txt);
				} catch (IOException e2) {
					e1.printStackTrace();
				}
				MassInsert.currentNum++;
			}
		}
		dialog.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		java.awt.Toolkit.getDefaultToolkit().beep();
		dialog.dispose();
	}
}
