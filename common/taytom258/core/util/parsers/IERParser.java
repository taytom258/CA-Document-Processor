package taytom258.core.util.parsers;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import taytom258.core.log.LogHelper;
import taytom258.core.util.db.CircuitStatus;
import taytom258.core.util.db.DatabaseUtils;
import taytom258.lib.Collection;

/**
 * Parse TSO text
 * 
 * @author Brian Haddad, Taylor Tomlin
 * @param t
 */

public class IERParser {

	private static String[] addIn(String[] origArray, String element) {
		String[] newArray = new String[origArray.length + 1];
		System.arraycopy(origArray, 0, newArray, 0, origArray.length);
		newArray[newArray.length - 1] = element;
		return newArray;
	}

	private static ArrayList<String> matcher(String regex, String input) {
		ArrayList<String> match = new ArrayList<String>();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		while (m.find()) {
			match.add(input.substring(m.start(), m.end()));
		}
		return match;
	}

	public static TreeMap<String, String> parseIER(String t, boolean auto) {
		if (t.equals("") || t.equals(null)) {
			return null;
		} // we ain't messin' around here kid
		Collection.init();
		DatabaseUtils.init(false);
		// the TSO object
		TreeMap<String, String> ier = new TreeMap<String, String>();

		// useful Static Strings (for search and other things)
		String[] ALPHABET = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		String[] NUMBERS = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String SUBJ = " SUBJ: ";
		String REPORT = " REPORT ";
		String DTG = " DTG ";
		int DTGLENGTH = 14;
		String FM = " FM ";
		String Dash = "-";
		int ReportLength = 14;
		String LBC = " ";

		// text sanitization:
		t = t.replaceAll("\\r\\n|\\n|\\r", LBC); // remove line breaks and
		// replace with LBC (see
		// //replace strings)
		t = t.replaceAll("\\s\\s+", " "); // remove double spaces and replace
		// with a single space
		t = t.replaceAll("['\"]", "\\$&"); // escape apostrophes and quotes
		// (replace string might need double
		// the backslashes)

		// get basics from the start
		int sbegin = t.indexOf(SUBJ) + SUBJ.length();
		int sEnd = t.indexOf(REPORT, sbegin) + REPORT.length() - 1;
		ier.put("Subject", t.substring(sbegin, sEnd));
		if (t.indexOf(DTG) == -1) {
			ier.put("Report Date",
					t.substring(t.indexOf(FM) - ReportLength, t.indexOf(FM)));
		} else if (t.indexOf(DTG) > -1) {
			ier.put("DTG",
					t.substring(t.indexOf(DTG) + DTG.length(), t.indexOf(DTG)
							+ DTG.length() + DTGLENGTH));
			ier.put("Report Date",
					t.substring(t.indexOf(FM) - ReportLength, t.indexOf(FM)));
		}

		ArrayList<String> sections = new ArrayList<String>();
		sections.add(" "
				+ t.substring(t.indexOf(REPORT, sbegin) + REPORT.length())); // section
		// 1

		/*
		 * extract important data the "skippy" entries are for lettered sections
		 * that are unimportant or I just didn't know what they were
		 */
		String skippy = "";
		String[] items = { "TSO Number", "TSR Number", "Full CCSD", skippy,
				"TSO Action", skippy, skippy, "POC Info", skippy };
		String as = "";
		String bs = "";
		String st = sections.get(0);
		ArrayList<String> numbersFound = new ArrayList<String>();
		for (String element : NUMBERS) {
			as = " " + element + ". ";
			if (st.indexOf(as) > 0) {
				numbersFound.add(as);
			}
		}
		for (int i = 0; i < items.length; i++) {
			if (items[i] != skippy && i < numbersFound.size()) {
				if (i + 1 == numbersFound.size()) {
					as = numbersFound.get(i);
					ier.put(items[i],
							st.substring(st.indexOf(as) + as.length()));
					st = st.substring(
							st.indexOf(ier.get(items[i]))
							+ ier.get(items[i]).length(), st.length());
				} else {
					as = numbersFound.get(i);
					bs = numbersFound.get(i + 1);
					if (st.indexOf(bs) - (st.indexOf(as) + as.length()) > 0) {
						ier.put(items[i],
								st.substring(st.indexOf(as) + as.length(),
										st.indexOf(bs)));
						st = st.substring(st.indexOf(ier.get(items[i]))
								+ ier.get(items[i]).length(), st.length());
					}
				}
			}
		}

		if (auto) {
			for (Map.Entry<String, String> entry : ier.entrySet()) {
				if (entry.getKey().equals("Subject")) {
					Collection.ierSubject = entry.getValue();
				} else if (entry.getKey().equals("Report Date")) {
					Collection.ierReportDate = entry.getValue();
				} else if (entry.getKey().equals("TSO Number")) {
					Collection.ierTSONum = entry.getValue().replaceAll("/", "");
				} else if (entry.getKey().equals("TSR Number")) {
					Collection.ierTSRNum = entry.getValue();
				} else if (entry.getKey().equals("Full CCSD")) {
					Collection.ierFullCCSD = entry.getValue();
				} else if (entry.getKey().equals("TSO Action")) {
					Collection.ierTSOAct = entry.getValue();
				} else if (entry.getKey().equals("POC Info")) {
					Collection.ierPOCInfo = entry.getValue();
				}
			}
			CircuitStatus.circuitStatusUpdateIER(Collection.ierTSONum);
			LogHelper.info("IER (Parser) Complete");
		}

		DatabaseUtils.init(true);
		return ier;
	}
}
