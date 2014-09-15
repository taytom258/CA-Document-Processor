package taytom258.show.tso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import taytom258.config.Config;
import taytom258.core.DirHandler;
import taytom258.core.WriteHandler;
import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;
import taytom258.lib.Strings;
import taytom258.window.Window;

public class ShowCHF extends Window {

	private static String currentText, creatingText, tsoName;
	private static ArrayList<String> create = new ArrayList<String>();
	private static File folderExist = null;

	public static void show() {
		tsoName();
		IOOperations(rootFolder());
		if (Collection.ccsdChange) {
			checkDup();
		}
		LogHelper.info("TSO (CHF) Tab Complete");
	}

	private static void checkDup() {
		File root = new File(folderExist.toString());
		File oldRoot = null;
		String first = Collection.ccsdList[0].trim().substring(0, 4);
		String second = Collection.ccsdList[0].trim().substring(4, 8);
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

	private static void tsoName() {
		Pattern p2 = Pattern.compile("\\d{2}");
		Pattern p3 = Pattern.compile("[\\d{2}][A-Z]");
		int index = Collection.tsoSubject.indexOf("-");
		String[] split = Collection.tsoSubject.split("-");
		Matcher m2 = p2.matcher(split[1]);
		Matcher m3 = p3.matcher(split[1]);
		if (m3.find()) {
			index += 3;
		} else if (m2.find()) {
			index += 2;
		}
		String pre = Collection.tsoSubject.trim().replace("/", "")
				.substring(0, index);
		tsoName = pre.trim() + ".txt";

		getTextField_ChfTsoName().setText(tsoName);
	}

	private static File rootFolder() {
		File only = null;

		if (Collection.tsoAction.equals("DISCONTINUE")
				&& Collection.trunkId.length() == 6) {
			getTextField_ChfRoot().setText(
					Collection.chfRootFolder + " " + "(" + Collection.trunkId
							+ ")" + " " + Strings.DISCO_PEND);
			Collection.chfRootFolder += " " + "(" + Collection.trunkId + ")"
					+ " " + Strings.DISCO_PEND;
		} else if (Collection.tsoAction.equals("DISCONTINUE")) {
			getTextField_ChfRoot().setText(
					Collection.chfRootFolder + " " + Strings.DISCO_PEND);
			Collection.chfRootFolder += " " + Strings.DISCO_PEND;
		} else if (Collection.trunkId.length() == 6) {
			getTextField_ChfRoot().setText(
					Collection.chfRootFolder + " " + "(" + Collection.trunkId
							+ ")");
			Collection.chfRootFolder += " " + "(" + Collection.trunkId + ")";
		} else {
			getTextField_ChfRoot().setText(Collection.chfRootFolder);
		}

		if (Config.useChf) {
			folderExist = new File(Config.chfPath + "\\"
					+ Collection.chfRootFolder);
			only = new File(Config.chfPath + "\\" + Strings.ONLY_1539 + "\\"
					+ Collection.chfRootFolder);
		} else {
			folderExist = new File(Config.chfTest + "\\"
					+ Collection.chfRootFolder);
			only = new File(Config.chfTest + "\\" + Strings.ONLY_1539 + "\\"
					+ Collection.chfRootFolder);
		}

		File discoPend = new File(folderExist.toString() + " "
				+ Strings.DISCO_PEND);
		String folderName = Collection.chfRootFolder;

		if (Collection.tsoAction.equals("CANCEL") && discoPend.exists()) {
			discoPend.renameTo(folderExist);
		} else if (Collection.tsoAction.equals("DISCONTINUE")) {
			folderExist.renameTo(discoPend);
			folderName += " " + Strings.DISCO_PEND;
			folderExist = discoPend;
		} else if (discoPend.exists()) {
			folderName += " " + Strings.DISCO_PEND;
			folderExist = discoPend;
		}

		if (folderExist.exists() && folderExist.isDirectory()) {
			getRdbtn_ChfRoot().setSelected(true);
		} else {
			try {
				if (Config.useChf && !only.equals(folderExist)) {
					if (DirHandler.createDir(folderName, Config.chfPath)) {
						LogHelper.io("Created Root in: " + Config.chfPath);
					}
					getRdbtn_ChfRootCreated().setSelected(true);
				} else if (!only.equals(folderExist)) {
					if (DirHandler.createDir(folderName, Config.chfTest)) {
						LogHelper.io("Created Root in: " + Config.chfTest);
					}
					getRdbtn_ChfRootCreated().setSelected(true);
				}
			} catch (IOException e) {
				e.printStackTrace();
				LogHelper.severe(e.getMessage());
			}
		}
		getTextField_chfLink().setText(folderExist.toString());
		Collection.chfLink = folderExist.toString();
		return folderExist;

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
		if (Collection.hasSams && folder.equals(Strings.FOLDERS[4])) {
			return true;
		} else if (Collection.isAnalog && folder.equals(Strings.FOLDERS[2])) {
			return true;
		} else if (Collection.andrewsCmo && folder.equals(Strings.FOLDERS[5])) {
			return true;
		} else if (!Collection.isPassthrough
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
				if (DirHandler.createDir(s, path)) {
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
			if (WriteHandler.fileWriter(Collection.tsoText, file)) {
				LogHelper.io("Created file " + text + " in " + path + "\\"
						+ Strings.FOLDERS[6]);
			}
		} else {
			LogHelper.info("TSO File Already Exists");
		}
	}

	@Deprecated
	private static void create1539(String path) {

		if (Collection.is1539) {
			try {
				if (Config.useChf) {
					File folder = new File(Config.chfPath + "\\"
							+ Strings.ONLY_1539);
					File root = new File(path);
					File newRoot = new File(folder.toString() + "\\"
							+ Collection.chfRootFolder);

					if (!folder.exists()) {
						if (DirHandler.createDir(Strings.ONLY_1539,
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
							+ Collection.chfRootFolder);

					if (!folder.exists()) {
						if (DirHandler.createDir(Strings.ONLY_1539,
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
