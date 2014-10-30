/**
 * 
 */
package taytom258.display.tso;



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
}
