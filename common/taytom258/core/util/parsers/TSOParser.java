package taytom258.core.util.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import taytom258.core.log.LogHelper;
import taytom258.core.util.db.DatabaseUtils;
import taytom258.core.util.parsers.collections.TSOCollection;
import taytom258.lib.Strings;
import taytom258.testing.testCode.IER;

/**
 * Parse TSO text
 * 
 * @author Brian Haddad, Taylor Tomlin
 * @param t
 */

public class TSOParser {

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

	public static boolean parseTSO(String t) throws Exception {
		if (t.equals("") || t.equals(null)) {
			return false;
		} // we ain't messin' around here kid
		TSOCollection.init();
		DatabaseUtils.init(false);
		// the TSO object
		TreeMap<String, String> tso = new TreeMap<String, String>();

		// useful Static Strings (for search and other things)
		String[] ALPHABET = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		String SUBJ = " SUBJ: ";
		String REF = " REF: ";
		String DTG = " DTG ";
		int DTGLENGTH = 14;
		String FM = " FM ";
		String Dash = "-";
		int ReportLength = 14;
		String PURPOSE = " 1. PURPOSE ";
		String GENINFO = " 2. GENERAL CIRCUIT/TRUNK INFORMATION ";
		String FACINFO = " 3. FACILITY AND EQUIPMENT INFORMATION ";
		String NUMCONT = " 4. NUMBER CONTROL ";
		String OTHERSD = " 5. OTHER SPECIFIC DIRECTIONS ";
		String BONA = " (BONA FIDE NEED FY) ";
		String MRC = " (NTE MRC) ";
		String NRC = " (NTE NRC) ";
		String FUND = " (FUNDING OFFICE) ";
		// These are search strings to identify if POC information is for our
		// local TCF (try to keep as specific as possible)
		String[] STRLOCALID = { "ANDWSAFB/24/TCF", "ANDWSAFB", "BLLNGAFB",
				"-4187", "-5150", "-9257" };
		// These are search strings that remove false positives for local CCO
		// (for when the above search strings are too general)
		String[] STRNOTCCO = { "-5555" };

		// text sanitization:
		t = t.replaceAll("\\r\\n|\\n|\\r", " "); // remove line breaks and
		// replace with LBC (see
		// //replace strings)
		t = t.replaceAll("\\s\\s+", " "); // remove double spaces and replace
		// with a single space
		t = t.replaceAll("['\"]", "\\$&"); // escape apostrophes and quotes
		// (replace string might need double
		// the backslashes)

		// get basics from the start
		int sEnd = t.indexOf(REF);
		if (sEnd < 0) {
			sEnd = t.indexOf(PURPOSE);
		}
		tso.put("Subject", t.substring(t.indexOf(SUBJ) + SUBJ.length(), sEnd));
		String[] test = tso.get("Subject").split("-");
		if (test[1].length() > 2) {
			sEnd = 4;
		} else {
			sEnd = 3;
		}
		String number = tso.get("Subject")
				.substring(4, tso.get("Subject").indexOf(Dash) + sEnd)
				.replaceAll("[/\\s]", "");
		tso.put("TSO Number", number);
		if (t.indexOf(DTG) == -1) {
			tso.put("Report Date",
					t.substring(t.indexOf(FM) - ReportLength, t.indexOf(FM)));
		} else if (t.indexOf(DTG) > -1) {
			tso.put("DTG",
					t.substring(t.indexOf(DTG) + DTG.length(), t.indexOf(DTG)
							+ DTG.length() + DTGLENGTH));
			tso.put("Report Date",
					t.substring(t.indexOf(FM) - ReportLength, t.indexOf(FM)));
		}

		// Look for evidence that a completion report is NOT required
		String[] crsp = { "COMPLETION REPORT NOT REQUIRED",
		"COMPLETION REPORT IS NOT REQUIRED" };
		boolean crr = true;
		for (String element : crsp) {
			if (t.toUpperCase().indexOf(element) > -1) {
				crr = false;
			}
		}
		tso.put("Completion Report Required", Boolean.valueOf(crr).toString());

		// separate into the primary five sections
		ArrayList<String> sections = new ArrayList<String>();
		sections.add(" "
				+ t.substring(t.indexOf(PURPOSE) + PURPOSE.length(),
						t.indexOf(GENINFO))); // section 1
		sections.add(" "
				+ t.substring(t.indexOf(GENINFO) + GENINFO.length(),
						t.indexOf(FACINFO))); // section 2
		sections.add(" "
				+ t.substring(t.indexOf(FACINFO) + FACINFO.length(),
						t.indexOf(NUMCONT))); // section 3
		sections.add(" "
				+ t.substring(t.indexOf(NUMCONT) + NUMCONT.length(),
						t.indexOf(OTHERSD))); // section 4
		sections.add(" "
				+ t.substring(t.indexOf(OTHERSD) + OTHERSD.length(),
						t.length() - 4)); // section 5

		// format section one into a single statement (without the A, B, 1, 2
		// outline crap)
		boolean found = true; // boolean for if the object was found
		String[] sc = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		// sc = ArrayUtils.addAll(sc, ALPHABET);
		for (int i = sc.length; i < 9 + ALPHABET.length; i++) {
			sc = addIn(sc, ALPHABET[sc.length - i]);
		}
		int sci = 0; // search character index
		int so = 9; // start search over at 9 (which will be "A") after numbers
		// fail
		String ss = ""; // search string
		while (found) {
			ss = " " + sc[sci] + ". ";
			found = false;
			while (sections.get(0).indexOf(ss) > -1) {
				found = true;
				sections.set(0, sections.get(0).replace(ss, " "));
			}
			if (found) {
				sci++;
			}
			if (!found && sci < so) {
				found = true;
				sci = so;
			}
		}
		while (sections.get(0).substring(0, 1) == " ") {
			sections.set(0,
					sections.get(0).substring(1, sections.get(0).length()));
		}
		tso.put("Purpose", sections.get(0).replaceAll("\\s{2,}", " "));

		// save a spot for these:
		tso.put("Requesting Department", "");
		tso.put("Circuit Use", "");
		tso.put("Type of Service", "");

		/*
		 * extract important data from section two (sections[1]) the "skippy"
		 * entries are for lettered sections that are unimportant or I just
		 * didn't know what they were
		 */
		String skippy = "";
		String[] items = { "Full CCSD", "TSP", "TSO Action",
				"Expected In Effect Date", "POC Information",
				"Quality Control Code", "Traffic Flow", "CCO or CMO",
				"Data Rate", "Security", "Term", "Signaling", skippy,
				"TSR Number", "Alternate CCSD" };
		String as = "";
		String bs = "";
		String st = sections.get(1);
		for (int i = 0; i < items.length; i++) {
			if (items[i] != skippy) {
				as = " " + ALPHABET[i] + ". ";
				bs = " " + ALPHABET[i + 1] + ". ";
				if (st.indexOf(bs) - (st.indexOf(as) + as.length()) > 0) {
					tso.put(items[i],
							st.substring(st.indexOf(as) + as.length(),
									st.indexOf(bs)));
					st = st.substring(
							st.indexOf(tso.get(items[i]))
							+ tso.get(items[i]).length(), st.length());
				} else {
					tso.put(items[i], Strings.NOTSET);
					st = st.substring(st.indexOf(bs));
				}
			}
		}

		if (tso.get("TSP").equals("NA")) {
			tso.put("TSP Number", tso.get("TSP"));
		} else if (tso.get("TSP").contains("-")) {
			String[] TspNumber = tso.get("TSP").split("-");
			tso.put("TSP Number", TspNumber[1]);
		}
		String[] TsoSuffix = tso.get("TSO Number").split("-");
		tso.put("TSO Suffix", TsoSuffix[1]);

		// Tomlin Request #1
		String ccsd = tso.get("Full CCSD").trim();
		TSOCollection.ccsdChange = false;
		if (ccsd.length() == 8) {
			tso.put("Standardized CCSD", ccsd);
		} else if (ccsd.length() < 8) {
			tso.put("Standardized CCSD", tso.get("Alternate CCSD"));
		} else if (ccsd.length() > 8 && ccsd.indexOf('/') > -1) {
			TSOCollection.ccsdChange = true;
			String[] split = ccsd.split("/");
			tso.put("Standardized CCSD", split[1]);
		}

		// Tomlin Request #2
		String tftxt = tso.get("POC Information");
		String ep = " CONTACT:";
		String sstr = " (1) ";
		if (tftxt.indexOf(sstr) < 0) {
			sstr = " 1. ";
		}
		tso.put("From",
				tftxt.substring(tftxt.indexOf(sstr) + sstr.length(),
						tftxt.indexOf(ep)));
		tftxt = tftxt
				.substring(tftxt.indexOf(ep) + ep.length(), tftxt.length());
		sstr = " (2) ";
		if (tftxt.indexOf(sstr) < 0) {
			sstr = " 2. ";
		}
		tso.put("To",
				tftxt.substring(tftxt.indexOf(sstr) + sstr.length(),
						tftxt.indexOf(ep)));

		tso.put("We Are Endpoint", "false");
		tso.put("We Are CCO", "false");
		for (String element : STRLOCALID) {
			if (tso.get("From").indexOf(element) > -1) {
				tso.put("We Are Endpoint", "true");
			}
			if (tso.get("To").indexOf(element) > -1) {
				tso.put("We Are Endpoint", "true");
			}
			if (tso.get("CCO or CMO").indexOf(element) > -1) {
				tso.put("We Are CCO", "true");
			}
		}

		for (String element : STRNOTCCO) {
			if (tso.get("CCO or CMO").indexOf(element) > -1) {
				tso.put("We Are CCO", "false");
			}
		}

		// Tomlin Request #3 (Redone completely, not java compatible cause of
		// 'match')

		// String[] contact = {};
		// String cmo = tso.get("CCO or CMO");
		// boolean dsnF = false;
		// contact = cmo.split("/");
		// tso.put("CCO or CMO", contact[0]);
		// if(contact[1].indexOf("D") > contact[1].indexOf("C")){
		// dsnF = false;
		// }else if(contact[1].indexOf("D") < contact[1].indexOf("C")){
		// dsnF = true;
		// }
		// ArrayList<String> arrayMatch = matcher("\\d{2}[\\s\\d-]+",
		// contact[1]);
		// for(String element : arrayMatch){
		// if(element.length() < 12 || dsnF){
		// tso.put("CMO DSN", element);
		// dsnF = false;
		// }else{
		// tso.put("CMO Comm", element);
		// }
		// }

		String cco = tso.get("CCO or CMO");
		ArrayList<String> cpi = matcher("\\d{2}[\\s\\d\\-]+", cco);

		String[] temp = cco.split("/");
		temp[0] = temp[0].replaceAll("CMO", "");
		temp[0] = temp[0].replaceAll("CCO", "");
		tso.put("CCO or CMO", temp[0]);

		for (int i = 0; i < cpi.size(); i++) {
			int ti = cco.indexOf(cpi.get(i)) - 1;
			// System.out.println(ti + " : " + cco);
			if (cco.substring(ti, ti + 1).equals(")")) {
				ti--;
			}
			if (cco.substring(ti, ti + 1).equals("D")) {
				// System.out.println(cpi.get(i));
				tso.put("CMO DSN", cpi.get(i));
			} else if (cco.substring(ti, ti + 1).equals("C")) {
				tso.put("CMO Comm", cpi.get(i));
			} else {
				if (!cco.substring(ti, ti + 1).equals(" ")
						&& cpi.get(i).length() > 4) {
					tso.put("CCO Contact Numbers_" + cco.substring(ti, ti + 1),
							cpi.get(i));
				}
			}
		}

		// get facilities from section 3 and set ENR table with location
		// information
		ArrayList<String> sec3 = new ArrayList<String>();
		String elc = "BLDG/DIRECTIONS/ADDRESS:";
		Pattern p = Pattern.compile("\\s[A-Z]\\.\\s");
		Matcher mat = p.matcher(sections.get(2));
		while (mat.find()) {
			int start = mat.end();
			int end = sections.get(2).indexOf(elc, mat.end()) - 1;
			// FIXME fix if match matches something other than title letters
			String add = sections
					.get(2)
					.substring(mat.end(),
							sections.get(2).indexOf(elc, mat.end()) - 1).trim();
			sec3.add(add);
		}
		for (int i = 0; i < sec3.size(); i++) {
			String num = String.valueOf(i + 1);
			String s = sec3.get(i);
			int space = 0;
			for (int i2 = 0; i2 < s.length(); i2++) {
				if (s.charAt(i2) == ' ') {
					space++;
				}
			}
			String geo = "";
			String state = "";
			if (space == 2) {
				geo = s.substring(0, s.indexOf(" ", s.indexOf(" ") + 1));
				state = s.substring(s.indexOf(" ", s.indexOf(" ") + 1) + 1,
						s.indexOf(" ", s.indexOf(" ") + 1) + 3);
			} else if (space == 1) {
				geo = s.substring(0, s.indexOf(" "));
				state = s.substring(s.indexOf(" ") + 1, s.indexOf(" ") + 3);
			}

			TSOCollection.facilities.add(num + ". " + LoadDB.testGEOLOC(geo, state));
		}

		for (String ele : sec3) {
			String[] temp1 = ele.split("\\s");
			if (temp1[0].equals(Strings.ANDREWS_CMO)
					|| temp1[0].equals(Strings.BOLLING_CMO)
					|| temp1[0].equals(Strings.DVILLE_CMO)
					|| temp1[0].equals(Strings.BWINE_CMO)) {
				TSOCollection.enrCode.add(temp1[0] + ":"
						+ temp1[1].substring(3).trim());
			}
		}

		for (String element : TSOCollection.enrCode) {
			String[] temp1 = element.split(":");
			if (LoadDB.testENRCODE(temp1[1], temp1[0]).length() < 1) {
				TSOCollection.location = "NOT SET";
				TSOCollection.inputNeeded.add(element);
			} else {
				String[] sa = LoadDB.testENRCODE(temp1[1], temp1[0]).split(":");
				if (sa[0].equals(Strings.ANDREWS_CMO)) {
					for (String element2 : Strings.LOCATIONS) {
						if (sa[1].equals(element2)) {
							TSOCollection.location = element2;
						}
					}
				} else if (sa[0].equals(Strings.BOLLING_CMO)
						&& !TSOCollection.location.equals(Strings.LOCATIONS[0])) {
					for (String element2 : Strings.LOCATIONS) {
						if (sa[1].equals(element2)) {
							TSOCollection.location = element2;
						}
					}
				} else if (sa[0].equals(Strings.DVILLE_CMO)
						&& !TSOCollection.location.equals(Strings.LOCATIONS[1])
						&& !TSOCollection.location.equals(Strings.LOCATIONS[0])) {
					for (String element2 : Strings.LOCATIONS) {
						if (sa[1].equals(element2)) {
							TSOCollection.location = element2;
						}
					}
				} else if (sa[0].equals(Strings.BWINE_CMO)
						&& !TSOCollection.location.equals(Strings.LOCATIONS[1])
						&& !TSOCollection.location.equals(Strings.LOCATIONS[0])
						&& !TSOCollection.location.equals(Strings.LOCATIONS[4])) {
					for (String element2 : Strings.LOCATIONS) {
						if (sa[1].equals(element2)) {
							TSOCollection.location = element2;
						}
					}
				}

				if (TSOCollection.location.equals(Strings.LOCATIONS[0])
						|| TSOCollection.location.equals(Strings.LOCATIONS[1])) {
					TSOCollection.careq = true;
				} else {
					TSOCollection.careq = false;
				}
			}
		}

		// break POC information into an array (because I can)
		String fs = tso.get("POC Information");
		String[] array = {};
		int sn = 2;
		as = "(" + (sn - 1) + ") ";
		bs = "(" + sn + ") ";
		found = fs.indexOf(as) > -1;
		int end = fs.indexOf(bs);
		while (found) {
			String element = fs.substring(fs.indexOf(as) + as.length(), end);
			array = addIn(array, element);
			sn++;
			as = "(" + (sn - 1) + ") ";
			bs = "(" + sn + ") ";
			found = fs.indexOf(as) > -1;
			end = fs.indexOf(bs);
			if (end < 0) {
				end = fs.length();
			}
		}
		String arrayText = Arrays.toString(array).replace(",", "\n")
				.replace("[", "").replace("]", "");
		tso.put("POC Information", arrayText);

		// pull out MRC and NRC from section 5
		Double zero = 0.00;
		String text = sections.get(4);
		Pattern pat = Pattern.compile("[\\$](\\d+(?:\\.\\d{1,2})?)");
		if (text.indexOf(BONA) > -1) {
			String BonaText = text.substring(
					text.indexOf(BONA) + BONA.length() + 1, text.indexOf(FUND))
					.trim();
			Matcher match = pat.matcher(BonaText);
			boolean mrcf = false;
			String MRCText = "";
			String NRCText = "";
			if (BonaText.indexOf(MRC) < BonaText.indexOf(NRC)) {
				mrcf = true;
			}
			while (match.find()) {
				if (mrcf) {
					MRCText = match.group(1);
					mrcf = false;
				} else {
					NRCText = match.group(1);
					mrcf = true;
				}
			}
			TSOCollection.mrc = Double.parseDouble(MRCText);
			TSOCollection.nrc = Double.parseDouble(NRCText);
		} else {
			TSOCollection.mrc = zero;
			TSOCollection.nrc = zero;
		}

		// break the full CCSD down
		String fullCCSD = "";
		if (tso.get("Full CCSD").length() > 6
				|| tso.get("Full CCSD").indexOf("/") > -1
				&& tso.get("Full CCSD").length() == 17) {
			fullCCSD = tso.get("Full CCSD");
		} else {
			fullCCSD = tso.get("Alternate CCSD");
		}
		tso.put("Requesting Department",
				LoadDB.testDEPTCODE("'" + fullCCSD.substring(0, 1) + "'"));
		tso.put("Circuit Use",
				LoadDB.testUSECODE("'" + fullCCSD.substring(1, 3) + "'"));
		tso.put("Type of Service",
				LoadDB.testSVCTYPE("'" + fullCCSD.substring(3, 4) + "'"));

		for (Map.Entry<String, String> entry : tso.entrySet()) {
			String key = entry.getKey().trim();
			String value = entry.getValue().trim();
			// System.out.println("(" + key + ")" + " : " + value);

			if (key.equals("Standardized CCSD")) {
				TSOCollection.fullCcsd = value;
				String first = value.trim().substring(0, 4);
				String second = value.trim().substring(4, 8);
				TSOCollection.chfRootFolder = second + " (" + first + ")";
			} else if (key.equals("Full CCSD") && value.length() == 6) {
				TSOCollection.trunkId = value;
			} else if (key.equals("Full CCSD") && value.length() != 6) {
				TSOCollection.trunkId = Strings.NOTSET;
			} else if (key.equals("TSP Number")) {
				TSOCollection.tsp = value;
			} else if (key.equals("TSP")) {
				if (key.equals("NA")) {
					TSOCollection.fullTsp = "Not Assigned";
				} else {
					TSOCollection.fullTsp = value;
				}
			} else if (key.equals("To")) {
				String tempGEO = "";
				String testS = value.substring(value.indexOf(" ") + 1);
				String testS2 = testS.substring(0, testS.indexOf(" "));
				if (testS2.length() < 3) {
					tempGEO = value.substring(0, value.indexOf(' ')).trim()
							+ " " + testS2.trim();
				} else {
					tempGEO = value.substring(0, value.indexOf(' ')).trim();
				}
				String tempState = value
						.substring(value.indexOf('/') - 2, value.indexOf('/'))
						.trim().replaceFirst("^0+(?!$)", "");
				String temprs = LoadDB.testGEOLOC(tempGEO, tempState);
				TSOCollection.toLocation = temprs;
				TSOCollection.toCode = value;
			} else if (key.equals("From")) {
				String tempGEO = value.substring(0, value.indexOf(' ')).trim();
				String tempState = value
						.substring(value.indexOf('/') - 2, value.indexOf('/'))
						.trim().replaceFirst("^0+(?!$)", "");
				String temprs = LoadDB.testGEOLOC(tempGEO, tempState);
				TSOCollection.fromLocation = temprs;
				TSOCollection.fromCode = value;
			} else if (key.equals("Requesting Department")) {
				TSOCollection.requestingDept = value;
			} else if (key.equals("Type of Service")) {
				TSOCollection.serviceType = value;
			} else if (key.equals("Circuit Use")) {
				TSOCollection.circuitUse = value;
			} else if (key.equals("Security")) {
				TSOCollection.security = value;
			} else if (key.equals("Data Rate")) {
				TSOCollection.dataRate = value;
			} else if (key.equals("Traffic Flow")) {
				TSOCollection.trafficFlow = value;
			} else if (key.equals("Term")) {
				TSOCollection.serviceAvail = value;
			} else if (key.equals("We Are CCO")) {
				TSOCollection.andrewsCmo = Boolean.valueOf(value);
			} else if (key.equals("CCO or CMO")) {
				TSOCollection.cmo = value;
			} else if (key.equals("Signaling")) {
				TSOCollection.signaling = value;
			} else if (key.equals("Quality Control Code")) {
				TSOCollection.qcc = value;
			} else if (key.equals("We Are Endpoint")) {
				TSOCollection.endPoint = Boolean.valueOf(value);
				TSOCollection.passthrough = !TSOCollection.endPoint;
			} else if (key.equals("TSO Number")) {
				TSOCollection.tsoNum = value;
			} else if (key.equals("TSO Suffix")) {
				TSOCollection.tsoSuffix = value;
			} else if (key.equals("TSO Action")) {
				TSOCollection.action = value;
			} else if (key.equals("Expected In Effect Date")) {
				TSOCollection.svcDate = value;
			} else if (key.equals("Completion Report Required")) {
				TSOCollection.crr = Boolean.valueOf(value);
			} else if (key.equals("Purpose")) {
				TSOCollection.purpose = value;
			} else if (key.equals("Subject")) {
				TSOCollection.tsoSubject = value;
			} else if (key.equals("TSR Number")) {
				TSOCollection.tsrNum = value;
			} else if (key.equals("Report Date")) {
				TSOCollection.reportDate = value;
			} else if (key.equals("CMO Comm")) {
				TSOCollection.cmoComm = value;
			} else if (key.equals("CMO DSN")) {
				TSOCollection.cmoDsn = value;
			}
		}
		DatabaseUtils.init(true);
		LogHelper.info("TSO (Parser) Complete");
//		IER.run();
		return true;
	}
}

class LoadDB {

	public static String testGEOLOC(String GEOLOC, String StateCode) {
		String re = "";
		ArrayList<String> sa = new ArrayList<String>();
		String sql = "SELECT * " + "FROM " + Strings.GEOLOC_TABLE
				+ " WHERE GEOLOC = '" + GEOLOC + "' AND StateCountryCode = '"
				+ StateCode + "'";
		ArrayList<String> al = new ArrayList<String>();
		al = DatabaseUtils.dbQuery(sql);
		for (int i = 0; i < al.size(); i++) {
			if (i == 5) {
				sa.add(al.get(i));
			} else if (i == 7) {
				sa.add(al.get(i));
			}
		}
		if (sa.size() > 0) {
			re = sa.get(1) + ":" + sa.get(0);
		}
		return re;
	}

	public static String testDEPTCODE(String DEPTCODE) {
		String re = "";
		String sql = "SELECT * " + "FROM " + Strings.DEPTCODE_TABLE
				+ " WHERE DeptCode = " + DEPTCODE;
		ArrayList<String> al = new ArrayList<String>();
		al = DatabaseUtils.dbQuery(sql);
		for (int i = 0; i < al.size(); i++) {
			String temp = al.get(i);
			if (i == 2) {
				re = temp;
			}
		}
		return re;
	}

	public static String testUSECODE(String USECODE) {
		String re = "";
		String sql = "SELECT * " + "FROM " + Strings.USECODE_TABLE
				+ " WHERE PurposeCode = " + USECODE;
		ArrayList<String> al = new ArrayList<String>();
		al = DatabaseUtils.dbQuery(sql);
		for (int i = 0; i < al.size(); i++) {
			String temp = al.get(i);
			if (i == 3) {
				re = temp;
			}
		}
		return re;
	}

	public static String testSVCTYPE(String SVCTYPE) {
		String re = "";
		String sql = "SELECT * " + "FROM " + Strings.SVCTYPE_TABLE
				+ " WHERE Code = " + SVCTYPE;
		ArrayList<String> al = new ArrayList<String>();
		al = DatabaseUtils.dbQuery(sql);
		for (int i = 0; i < al.size(); i++) {
			String temp = al.get(i);
			if (i == 2) {
				re = temp;
			}
		}
		return re;
	}

	public static String testENRCODE(String ENRCODE, String GEOLOC) {
		String re = "";
		String sql = "SELECT * " + "FROM " + Strings.ENRCODE_TABLE
				+ " WHERE ENR = '" + ENRCODE + "' AND Location = '" + GEOLOC
				+ "'";
		ArrayList<String> al = new ArrayList<String>();
		al = DatabaseUtils.dbQuery(sql);
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i).equals(ENRCODE)) {
				if (al.get(i + 1).equals(GEOLOC)) {
					re = al.get(i + 1) + ":" + al.get(i + 2);
					return re;
				}
			}
		}
		return "";
	}
}
