/**
 * 
 */
package taytom258.display.tso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Class for common code between TSO tab classes
 * @author taytom258
 */
public class TSOCommon {

	/**
	 * Determine what action amend tso is amending.
	 * @param tsoSuffix from collected tso
	 * @param tsoAction from collected tso
	 * @return tso action that collected tso is amending, if it is an amend.<br>
	 * If not an amend, returns "N/A".
	 */
	public static String facitAmending(String tsoSuffix, String tsoAction) {
		String action = "N/A";
		if (tsoAction.equals("AMEND")) {
			String s = tsoSuffix.substring(0, 2);
			if (s.equals("01")) {
				action = "START";
			} else if (s.equals("99")) {
				action = "DISCO";
			} else {
				action = "CHANGE";
			}
		}
		return action;
	}
	
	/**
	 * Test an identifier to see if it is a trunk ID based on length
	 * @param identifier to test if trunk ID.
	 * @return is identifier a trunk ID
	 */
	public static boolean facitIsTrunk(String identifier){
		if (identifier.trim().length() == 6) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Get TSO document name from TSO subject
	 * @param input TSO Subject
	 * @return TSO document name
	 */
	public static String tsoName(String input) {
		input = input.trim();
		String s = "";
		Pattern p2 = Pattern.compile("\\d{2}");
		Pattern p3 = Pattern.compile("[\\d{2}][A-Z]");
		int index = input.indexOf("-");
		String[] split = input.split("-");
		Matcher m2 = p2.matcher(split[1]);
		Matcher m3 = p3.matcher(split[1]);
		if (m3.find()) {
			index += 3;
		} else if (m2.find()) {
			index += 2;
		}
		String pre = input.replace("/", "").substring(0, index);
		s = pre.trim() + ".txt";

		return s;
	}
}
