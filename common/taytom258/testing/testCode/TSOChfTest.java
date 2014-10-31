package taytom258.testing.testCode;

import java.awt.List;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import taytom258.config.Config;
import taytom258.core.log.LogHelper;
import taytom258.core.util.IOUtils;
import taytom258.core.util.parsers.TSOParser;
import taytom258.core.util.parsers.collections.TSOCollection;
import taytom258.lib.Strings;

public class TSOChfTest {

	int currentNum = 1;
	private static File folder = new File("\\\\ASPARAGUS\\Systems Control\\Circuit Actions\\CA Continuity\\Roles\\Document Tracker\\Document Processor\\TSOs\\Auto");
	private static File[] files = null;
	
	public static void test(){
		
		Thread test = new Thread(new Runnable() {
			public void run() {
				files = readFolder(folder);
				if (files.length != 0) {
					for(int i=0;i<files.length;i++){
						System.out.println("---------------TSO "+i+1+" of "+files.length+1+"---------------");
						try {
							TSOParser.parseTSO(IOUtils.textReader(files[i].toString()));
						} catch (Exception e) {
							
						}
						System.out.println(rootFolder());
						System.out.println("---------------End of TSO---------------");
					}
					
				}
			}
		});
		test.start();
		
	}
	
	private static File[] readFolder(File path) {
		File[] files = path.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return !pathname.isDirectory();
			}
		});
		return files;
	}
	
	
	private static String rootFolder() {
		String sc = " ";
		String rootName = "";
		File folderExist = null;

		/*
		 * Determine root folder name
		 */
		if (TSOCollection.action.equals("DISCONTINUE") && !TSOCollection.trunkId.equals(Strings.NOTSET)) {
			rootName = TSOCollection.chfRootFolder + sc + "(" + TSOCollection.trunkId + ")" + sc + Strings.DISCO_PEND;
		} else if (TSOCollection.action.equals("DISCONTINUE")) {
			rootName = TSOCollection.chfRootFolder + sc + Strings.DISCO_PEND;
		} else if (!TSOCollection.trunkId.equals(Strings.NOTSET)) {
			rootName = TSOCollection.chfRootFolder + sc + "(" + TSOCollection.trunkId + ")";
		} else {
			rootName = TSOCollection.chfRootFolder;
		}

		/*
		 * Determine folder to use for CHF
		 */
		if (Config.useChf) {
			folderExist = new File(Config.chfPath + "\\" + rootName);
		} else {
			folderExist = new File(Config.chfTest + "\\" + rootName);
		}

		/*
		 * Remove Disco Pending tag on a cancel of requirement
		 */
		File discoPend = new File(folderExist.toString() + " " + Strings.DISCO_PEND);

		if (TSOCollection.action.equals("CANCEL") && discoPend.exists()) {
			discoPend.renameTo(folderExist);
		}

		
		
		
		
		if (folderExist.exists() && folderExist.isDirectory()) {
//			return true;
		} else {
			try {
				if (Config.useChf) {
					if (IOUtils.createDir(rootName, Config.chfPath)) {
						LogHelper.io("Created Root in: " + Config.chfPath);
					}
//					return false;
				} else {
					if (IOUtils.createDir(rootName, Config.chfTest)) {
						LogHelper.io("Created Root in: " + Config.chfTest);
					}
//					return false;
				}
			} catch (IOException e) {
				e.printStackTrace();
				LogHelper.severe(e.getMessage());
			}
		}
		return rootName;
	}
}
