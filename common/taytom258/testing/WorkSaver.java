package taytom258.testing;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class WorkSaver {

	private static final String rootR = "F:\\";
	private static final String developR = "F:\\common";
	private static final String tsosR = "F:\\TSOs";
	private static final String developL = "C:\\Users\\taylor.tomlin\\Desktop\\Work Programs\\TSO-Helper\\common";
	private static final String tsosL = "\\\\ASPARAGUS\\Systems Control\\Circuit Actions\\CA Continuity\\Roles\\Document Tracker\\Document Processor\\TSOs";
	private static final String dbL = "\\\\ASPARAGUS\\Database";
	private static final String dbR = "F:\\Database";
	
	public static void offload(){
		try {
			FileUtils.copyDirectoryToDirectory(new File(developL), new File(rootR));
			FileUtils.copyDirectoryToDirectory(new File(tsosL), new File(rootR));
			FileUtils.copyDirectoryToDirectory(new File(dbL), new File(rootR));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void download(){
		try {
			FileUtils.moveDirectoryToDirectory(new File(developR), new File(developL), false);
			FileUtils.moveDirectoryToDirectory(new File(tsosR), new File(tsosL), false);
			File [] list = new File(rootR).listFiles();
			if(list.length > 0){
				for(File element:list){
					FileUtils.deleteQuietly(element);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
