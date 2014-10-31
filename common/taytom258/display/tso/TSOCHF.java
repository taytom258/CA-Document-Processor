package taytom258.display.tso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import taytom258.config.Config;
import taytom258.core.log.LogHelper;
import taytom258.core.util.IOUtils;
import taytom258.core.util.parsers.collections.TSOCollection;
import taytom258.lib.Strings;
import taytom258.windows.main.MainWindow;
import taytom258.windows.main.tsoTab.TSO_CHF_Panel;

/**
 * Class for displaying the information for the CHF tab
 * @author taytom258
 *
 */
public class TSOCHF extends TSO_CHF_Panel {

	private static final long serialVersionUID = -6033065540135216912L;

	/**
	 * Display information for text fields and pass information to the sub panels
	 */
	public static void display() {
		
		textField_TsoName.setText(TSOCommon.tsoName(TSOCollection.tsoNum));
		
		
		
		IOOperations(rootFolder());
		if (TSOCollection.ccsdChange) {
			checkDup();
		}
		LogHelper.info("TSO (CHF) Tab Complete");
	}

	private static void checkDup() {
		File root = new File(folderExist.toString());
		File oldRoot = null;
		String first = TSOCollection.ccsdList[0].trim().substring(0, 4);
		String second = TSOCollection.ccsdList[0].trim().substring(4, 8);
		String oldS = second + " (" + first + ")";
		if (Config.useChf) {
			oldRoot = new File(Config.chfPath + "\\" + oldS);
		} else {
			oldRoot = new File(Config.chfTest + "\\" + oldS);
		}
		if (oldRoot.exists()) {
			try {
				FileUtils.copyDirectory(oldRoot, root);
				FileUtils.deleteDirectory(oldRoot);
			} catch (IOException e) {
				LogHelper.severe(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private static void IOOperations(File path) {
		folder(path.toString());
		createTso(path.toString());
		// create1539(path.toString());
	}

	

	

	private static void folder(String path) {

		create.clear();

		currentText = "";
		getTextPane_ChfCurrent().setText("");
		creatingText = "";
		getTextPane_ChfCreating().setText("");

		for (String element : Strings.FOLDERS) {
			File folder = new File(path + "\\" + element);
			if (folder.exists() && folder.isDirectory()) {
				currentText = currentText.concat(element);
				currentText = currentText.concat("\n");
				getTextPane_ChfCurrent().setText(currentText);
				if (folder.toString().contains(Strings.FOLDERS[9])) {
					File newFolder = new File(path + "\\" + Strings.FOLDERS[1]);
					try {
						FileUtils.moveDirectory(folder, newFolder);
					} catch (IOException e) {
						e.printStackTrace();
						LogHelper.severe(e.getMessage());
					}
				}
			} else if (!folder.exists() && check(element)) {
				creatingText = creatingText.concat(element);
				creatingText = creatingText.concat("\n");
				getTextPane_ChfCreating().setText(creatingText);
				create.add(element);
			}
		}
		createFolders(path);
	}

	private static boolean check(String folder) {
		if (TSOCollection.hasSams && folder.equals(Strings.FOLDERS[4])) {
			return true;
		} else if (TSOCollection.isAnalog && folder.equals(Strings.FOLDERS[2])) {
			return true;
		} else if (TSOCollection.andrewsCmo && folder.equals(Strings.FOLDERS[5])) {
			return true;
		} else if (!TSOCollection.isPassthrough
				&& folder.equals(Strings.FOLDERS[8])) {
			return true;
		}
		if (folder.equals(Strings.FOLDERS[0])) {
			return true;
		} else if (folder.equals(Strings.FOLDERS[1])) {
			return true;
		} else if (folder.equals(Strings.FOLDERS[3])) {
			return true;
		} else if (folder.equals(Strings.FOLDERS[6])) {
			return true;
		} else if (folder.equals(Strings.FOLDERS[7])) {
			return true;
		}
		return false;
	}

	private static void createFolders(String path) {

		for (String s : create) {
			try {
				if (IOUtils.createDir(s, path)) {
					LogHelper.io("Created " + s + " in: " + path);
				}
			} catch (IOException e) {
				e.printStackTrace();
				LogHelper.severe(e.getMessage());
			}
		}
	}

	private static void createTso(String path) {

		String text = tsoName;

		File file = new File(path + "\\" + Strings.FOLDERS[6] + "\\" + text);

		if (!file.exists()) {
			if (IOUtils.fileWriter(TSOCollection.tsoText, file)) {
				LogHelper.io("Created file " + text + " in " + path + "\\"
						+ Strings.FOLDERS[6]);
			}
		} else {
			LogHelper.info("TSO File Already Exists");
		}
	}

	@Deprecated
	private static void create1539(String path) {

		if (TSOCollection.is1539) {
			try {
				if (Config.useChf) {
					File folder = new File(Config.chfPath + "\\"
							+ Strings.ONLY_1539);
					File root = new File(path);
					File newRoot = new File(folder.toString() + "\\"
							+ TSOCollection.chfRootFolder);

					if (!folder.exists()) {
						if (IOUtils.createDir(Strings.ONLY_1539,
								Config.chfPath)) {
							LogHelper.io("Created 1539 only root folder in: "
									+ Config.chfPath);
						}
					}
					if (!root.equals(newRoot)) {
						FileUtils.copyDirectory(root, newRoot, false);
						FileUtils.deleteQuietly(root);
						LogHelper.io("Moved folder from: " + root + " to: "
								+ newRoot);
					}

				} else {
					File folder = new File(Config.chfTest + "\\"
							+ Strings.ONLY_1539);
					File root = new File(path);
					File newRoot = new File(folder.toString() + "\\"
							+ TSOCollection.chfRootFolder);

					if (!folder.exists()) {
						if (IOUtils.createDir(Strings.ONLY_1539,
								Config.chfTest)) {
							LogHelper.io("Created 1539 only root folder in: "
									+ Config.chfTest);
						}
					}
					if (!root.equals(newRoot)) {
						FileUtils.copyDirectory(root, newRoot, false);
						FileUtils.deleteQuietly(root);
						LogHelper.io("Moved folder from: " + root + " to: "
								+ newRoot);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				LogHelper.severe(e.getMessage());
			}
		}
	}
}
