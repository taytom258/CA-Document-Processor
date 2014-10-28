package taytom258.window;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import taytom258.core.log.LogHelper;
import taytom258.core.util.parsers.TSOParser;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.show.tso.ShowCHF;
import taytom258.show.tso.ShowDatabase;
import taytom258.show.tso.ShowFacit;
import taytom258.window.core.TSOCore;

public class TSO {

	public JFrame frmEnterTso;
	private static JTextArea text;

	/**
	 * Launch the application.
	 */
	public static void appear() {
		LogHelper.info("Text Window");
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TSO window = new TSO();
					window.frmEnterTso.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					LogHelper.severe(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TSO() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEnterTso = new JFrame();
		frmEnterTso.setTitle("Enter TSO");
		frmEnterTso.setBounds(100, 100, 594, 507);
		frmEnterTso.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frmEnterTso.getContentPane().setLayout(null);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(493, 431, 73, 26);
		frmEnterTso.getContentPane().add(btnCancel);

		JButton btnAccept = new JButton("Accept & Run");
		btnAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TSOCore.save();
				Window.getFrmTsoHelper().getRootPane()
				.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				frmEnterTso.getRootPane().setCursor(
						new Cursor(Cursor.WAIT_CURSOR));
				if (Collection.tsoText.equals("")) {
					LogHelper.warning(Strings.FOUND_NOTHING);
				} else {
					try {
						TSOParser.parseTSO(Collection.tsoText);
						ShowCHF.show();
						ShowFacit.show();
						ShowDatabase.show();
						Collection.runClicked = true;
					} catch (Exception e1) {
						e1.printStackTrace();
						LogHelper.severe(e1.getClass() + ": " + e1.getMessage());
						Window.getFrmTsoHelper().getRootPane()
						.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						frmEnterTso.getRootPane().setCursor(
								new Cursor(Cursor.DEFAULT_CURSOR));
					}
				}
				Window.getFrmTsoHelper().getRootPane()
				.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				frmEnterTso.getRootPane().setCursor(
						new Cursor(Cursor.DEFAULT_CURSOR));
				frmEnterTso.dispose();
			}
		});
		btnAccept.setBounds(12, 431, 111, 26);
		frmEnterTso.getContentPane().add(btnAccept);

		JPanel panel = new JPanel();
		panel.setBounds(12, 11, 556, 406);
		frmEnterTso.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		text = new JTextArea();
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setText(Collection.tsoText);
		text.setDropMode(DropMode.INSERT);
		JScrollPane scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scroll, BorderLayout.CENTER);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.setText("");
			}
		});
		btnClear.setBounds(250, 431, 64, 26);
		frmEnterTso.getContentPane().add(btnClear);

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmEnterTso.dispose();
			}
		});
	}

	protected static JTextArea getText() {
		return text;
	}
}
