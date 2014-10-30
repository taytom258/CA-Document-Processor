/**
 * 
 */
package taytom258.core.util.popups;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.DefaultEditorKit;

/**
 * Provides various context menus for use on things such as right click
 * @author taytom258
 *
 */
public class ContextMenu {

	public static JPopupMenu copy() {
		JMenuItem menuItem = null;
		JPopupMenu popup = new JPopupMenu();

		menuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
		menuItem.setText("Copy");
		popup.add(menuItem);

		return popup;
	}

	public static JPopupMenu paste() {
		JMenuItem menuItem = null;
		JPopupMenu popup = new JPopupMenu();

		menuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
		menuItem.setText("Paste");
		popup.add(menuItem);

		return popup;
	}

	public static JPopupMenu cut() {
		JMenuItem menuItem = null;
		JPopupMenu popup = new JPopupMenu();

		menuItem = new JMenuItem(new DefaultEditorKit.CutAction());
		menuItem.setText("Cut");
		popup.add(menuItem);

		return popup;
	}

	public static JPopupMenu selectAll() {
		JMenuItem menuItem = null;
		JPopupMenu popup = new JPopupMenu();

		new DefaultEditorKit();
		menuItem = new JMenuItem(DefaultEditorKit.selectAllAction);
		menuItem.setText("Select All");
		popup.add(menuItem);

		return popup;
	}

	public static JPopupMenu all() {
		JMenuItem menuItem = null;
		JPopupMenu popup = new JPopupMenu();

		menuItem = new JMenuItem(new DefaultEditorKit.CutAction());
		menuItem.setText("Cut");
		popup.add(menuItem);

		menuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
		menuItem.setText("Copy");
		popup.add(menuItem);

		menuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
		menuItem.setText("Paste");
		popup.add(menuItem);

		new DefaultEditorKit();
		menuItem = new JMenuItem(DefaultEditorKit.selectAllAction);
		menuItem.setText("Select All");
		popup.add(menuItem);

		return popup;
	}



}
