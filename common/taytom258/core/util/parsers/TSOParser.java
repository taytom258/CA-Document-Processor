package taytom258.core.util.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import taytom258.core.util.LogHelper;
import taytom258.core.util.db.Database;
import taytom258.lib.Collection;
import taytom258.lib.Strings;

/**
 * Parse TSO text
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
	
	private static ArrayList<String> matcher(String regex, String input){
		ArrayList<String> match = new ArrayList<String>();
		Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(input);
	    	while(m.find()) {
	    		match.add(input.substring(m.start(), m.end()));
	    	}
	    return match;
	}
	
	public static void parseTSO(String t) {
		 if (t.equals("") || t.equals(null)) {return;} //we ain't messin' around here kid

		 //the TSO object
		 TreeMap<String, String> tso = new TreeMap<String, String>();

		 //useful Static Strings (for search and other things)
		 String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
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
		 //These are search strings to identify if POC information is for our local TCF (try to keep as specific as possible)
		 String[] STRLOCALID = {
		  "ANDWSAFB/24/TCF",
		  "ANDWSAFB",
		  "BLLNGAFB",
		  "-4187",
		  "-5150",
		  "-9257"
		 };
		 //These are search strings that remove false positives for local CCO (for when the above search strings are too general)
		 String[] STRNOTCCO = {
		  "-5555"
		 };

		 //text sanitization:
		 t = t.replaceAll("\\r\\n|\\n|\\r", " ");      //remove line breaks and replace with LBC (see //replace strings)
		 t = t.replaceAll("\\s\\s+", " ");            //remove double spaces and replace with a single space
		 t = t.replaceAll("['\"]", "\\$&");             //escape apostrophes and quotes (replace string might need double the backslashes)
		 
		 //get basics from the start
		 int sEnd = t.indexOf(REF);
		 if (sEnd < 0) {sEnd = t.indexOf(PURPOSE);}
		 tso.put("Subject", t.substring(t.indexOf(SUBJ) + SUBJ.length(), sEnd));
		 String[] test = tso.get("Subject").split("-");
		 if(test[1].length() > 2){
			 sEnd = 4;
		 }else{
			 sEnd = 3;
		 }
		 String number = tso.get("Subject").substring(4, tso.get("Subject").indexOf(Dash) + sEnd).replaceAll("[/\\s]", "");
		 tso.put("TSO Number", number);
		 if(t.indexOf(DTG) == -1){
			 tso.put("Report Date", t.substring(t.indexOf(FM) - ReportLength, t.indexOf(FM)));
		 }else if(t.indexOf(DTG) > -1){
			 tso.put("DTG", t.substring(t.indexOf(DTG) + DTG.length(), t.indexOf(DTG) + DTG.length() + DTGLENGTH));
			 tso.put("Report Date", t.substring(t.indexOf(FM) - ReportLength, t.indexOf(FM)));
		 }
		 

		 //Look for evidence that a completion report is NOT required
		 String[] crsp = {
		  "COMPLETION REPORT NOT REQUIRED",
		  "COMPLETION REPORT IS NOT REQUIRED"
		 };
		 boolean crr = true;
		 for (int i=0; i<crsp.length; i++) {
		  if (t.toUpperCase().indexOf(crsp[i]) > -1) {crr = false;}
		 }
		 tso.put("Completion Report Required", Boolean.valueOf(crr).toString());

		 //separate into the primary five sections
		 ArrayList<String> sections = new ArrayList<String>();
		 sections.add(" " + t.substring(t.indexOf(PURPOSE) + PURPOSE.length(), t.indexOf(GENINFO)));    //section 1
		 sections.add(" " + t.substring(t.indexOf(GENINFO) + GENINFO.length(), t.indexOf(FACINFO)));    //section 2
		 sections.add(" " + t.substring(t.indexOf(FACINFO) + FACINFO.length(), t.indexOf(NUMCONT)));    //section 3
		 sections.add(" " + t.substring(t.indexOf(NUMCONT) + NUMCONT.length(), t.indexOf(OTHERSD)));    //section 4
		 sections.add(" " + t.substring(t.indexOf(OTHERSD) + OTHERSD.length(), t.length() - 4));          //section 5
		 
		 //format section one into a single statement (without the A, B, 1, 2 outline crap)
		 boolean found = true;  //boolean for if the object was found
		 String[] sc = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
		 //sc = ArrayUtils.addAll(sc, ALPHABET);
		 for (int i=sc.length; i<9 + ALPHABET.length; i++) {sc = addIn(sc, ALPHABET[sc.length - i]);}
		 int sci = 0;           //search character index
		 int so = 9;            //start search over at 9 (which will be "A") after numbers fail
		 String ss = ""; 		//search string
		 while (found) {
		  ss = " " + sc[sci] + ". ";
		  found = false;
		  while (sections.get(0).indexOf(ss) > -1) {found = true; sections.set(0, sections.get(0).replace(ss, " "));}
		  if (found) {sci++;}
		  if (!found && sci < so) {found = true; sci = so;}
		 }
		 while (sections.get(0).substring(0, 1) == " ") {sections.set(0, sections.get(0).substring(1, sections.get(0).length()));}
		 tso.put("Purpose", sections.get(0).replaceAll("\\s{2,}", " "));

		 //save a spot for these:
		 tso.put("Requesting Department", "");
		 tso.put("Circuit Use", "");
		 tso.put("Type of Service", "");
		 
		 /*extract important data from section two (sections[1])
		 * the "skippy" entries are for lettered sections that are unimportant or I
		 * just didn't know what they were
		 */
		 String skippy = "";
		 String[] items = {
		  "Full CCSD",
		  "TSP",
		  "TSO Action",
		  "Expected In Effect Date",
		  "POC Information",
		  "Quality Control Code",
		  "Traffic Flow",
		  "CCO or CMO",
		  "Data Rate",
		  "Security",
		  "Term",
		  "Signaling",
		  skippy,
		  "TSR Number",
		  "Alternate CCSD"
		 };
		 String as = "";
		 String bs = "";
		 String st = sections.get(1);
		 for (int i=0; i<items.length; i++) {
		  if (items[i] != skippy) {
		   as = " " + ALPHABET[i] + ". ";
		   bs = " " + ALPHABET[i + 1] + ". ";
		   if (st.indexOf(bs) - (st.indexOf(as) + as.length()) > 0) {
			   tso.put(items[i], st.substring(st.indexOf(as) + as.length(), st.indexOf(bs)));
			   st = st.substring(st.indexOf(tso.get(items[i])) + tso.get(items[i]).length(), st.length());
		   }else{
			   tso.put(items[i], "N/A");
			   st = st.substring(st.indexOf(bs)); 
		   }
		  }
		 }
		 
		 if(tso.get("TSP").equals("NA")){
			 tso.put("TSP Number", tso.get("TSP"));
		 }else if(tso.get("TSP").contains("-")){
			 String[] TspNumber = tso.get("TSP").split("-");
			 tso.put("TSP Number", TspNumber[1]); 
		 }
		 String[] TsoSuffix = tso.get("TSO Number").split("-");
		 tso.put("TSO Suffix", TsoSuffix[1]);
		 
		 
		 
		 //Tomlin Request #1
		 String ccsd = tso.get("Full CCSD").trim();
		 if (ccsd.length() == 8) {
			 tso.put("Standardized CCSD", ccsd);
		 }else if(ccsd.length() > 8 && ccsd.indexOf("/") > -1 || ccsd.length() < 8) {
			 tso.put("Standardized CCSD", tso.get("Alternate CCSD"));
		 }
		 
		 //Tomlin Request #2
		 String tftxt = tso.get("POC Information");
		 String ep = " CONTACT:";
		 String sstr = " (1) ";
		 if (tftxt.indexOf(sstr) < 0) {sstr = " 1. ";}
		 tso.put("From", tftxt.substring(tftxt.indexOf(sstr) + sstr.length(), tftxt.indexOf(ep)));
		 tftxt = tftxt.substring(tftxt.indexOf(ep) + ep.length(), tftxt.length());
		 sstr = " (2) ";
		 if (tftxt.indexOf(sstr) < 0) {sstr = " 2. ";}
		 tso.put("To", tftxt.substring(tftxt.indexOf(sstr) + sstr.length(), tftxt.indexOf(ep)));

		 tso.put("We Are Endpoint", "false");
		 tso.put("We Are CCO", "false");
		 for (int i=0; i<STRLOCALID.length; i++) {
		  if (tso.get("From").indexOf(STRLOCALID[i]) > -1) {tso.put("We Are Endpoint", "true");}
		  if (tso.get("To").indexOf(STRLOCALID[i]) > -1) {tso.put("We Are Endpoint", "true");}
		  if (tso.get("CCO or CMO").indexOf(STRLOCALID[i]) > -1) {tso.put("We Are CCO", "true");}
		 }
		 
		 for (int i=0; i<STRNOTCCO.length; i++) {
		  if (tso.get("CCO or CMO").indexOf(STRNOTCCO[i]) > -1) {tso.put("We Are CCO", "false");}
		 }
		 
		 //Tomlin Request #3 (Redone completely, not java compatible cause of 'match')
		 
//		 String[] contact = {};
//		 String cmo = tso.get("CCO or CMO");
//		 boolean dsnF = false;
//		 contact = cmo.split("/");
//		 tso.put("CCO or CMO", contact[0]);
//		 if(contact[1].indexOf("D") > contact[1].indexOf("C")){
//			 dsnF = false;
//		 }else if(contact[1].indexOf("D") < contact[1].indexOf("C")){
//			 dsnF = true;
//		 }
//		 ArrayList<String> arrayMatch = matcher("\\d{2}[\\s\\d-]+", contact[1]);
//		 for(String element : arrayMatch){
//			 if(element.length() < 12 || dsnF){
//				 tso.put("CMO DSN", element);
//				 dsnF = false;
//			 }else{
//				 tso.put("CMO Comm", element);
//			 }
//		 }
		 
		String cco = tso.get("CCO or CMO");
		ArrayList<String> cpi = matcher("\\d{2}[\\s\\d\\-]+", cco);
		
		String[] temp = cco.split("/");
		temp[0] = temp[0].replaceAll("CMO", "");
		temp[0] = temp[0].replaceAll("CCO", "");
		tso.put("CCO or CMO", temp[0]);
		
		for (int i=0; i<cpi.size(); i++) {
			int ti = cco.indexOf(cpi.get(i)) - 1;
//			System.out.println(ti + " : " + cco);
			if (cco.substring(ti,  ti+1).equals(")")){
				ti--;
			}
			if(cco.substring(ti, ti+1).equals("D")){
//				System.out.println(cpi.get(i));
				tso.put("CMO DSN",cpi.get(i));
			}else if(cco.substring(ti, ti+1).equals("C")){
				tso.put("CMO Comm",cpi.get(i));
			}else{
				if (!cco.substring(ti, ti+1).equals(" ") && cpi.get(i).length() > 4) {
					tso.put("CCO Contact Numbers_"+cco.substring(ti, ti+1), cpi.get(i));
				}
			}
		}
		 
		 

		 //break POC information into an array (because I can)
		 String fs = tso.get("POC Information");
		 String[] array = {};
		 int sn = 2;
		 as = "(" + (sn - 1) + ") ";
		 bs = "(" + sn + ") ";
		 found = (fs.indexOf(as) > -1);
		 int end = fs.indexOf(bs);
		 while (found) {
		  String element = fs.substring(fs.indexOf(as) + as.length(), end);
		  array = addIn(array, element);
		  sn++;
		  as = "(" + (sn - 1) + ") ";
		  bs = "(" + sn + ") ";
		  found = (fs.indexOf(as) > -1);
		  end = fs.indexOf(bs);
		  if (end < 0) {end = fs.length();}
		 }
		 String arrayText = Arrays.toString(array).replace(",", "\n").replace("[", "").replace("]", "");
		 tso.put("POC Information", arrayText);

		 //break the full CCSD down
		 //info from DISAC 310-65-1
		 TreeMap<String, String> DEPT_CODES = new TreeMap<String, String>();
		 DEPT_CODES.put("A", "DOS: Department of State");
		 DEPT_CODES.put("B", "NAVY: Department of the Navy");
		 DEPT_CODES.put("C", "JCS: National Command Authorities");
		 DEPT_CODES.put("D", "DISA: Defense Information Systems Agency");
		 DEPT_CODES.put("E", "JTF: Joint Tactical Force Headquarters");
		 DEPT_CODES.put("F", "NCS: Minor operating agencies; e.g., Department of Energy, United States Information Agency, Department of Commerce, Department of Interior");
		 DEPT_CODES.put("G", "GSA: General Services Administration");
		 DEPT_CODES.put("H", "DOS: Diplomatic Telecommunications System");
		 DEPT_CODES.put("I", "FORGN: Allied Governments - for circuits required by allied governments and provided over some DCS facilities");
		 DEPT_CODES.put("J", "AIR FORCE: Department of the Air Force");
		 DEPT_CODES.put("K", "TRI: Technical Research Institute");
		 DEPT_CODES.put("L", "FAA: Federal Aviation Administration");
		 DEPT_CODES.put("M", "NASA: National Aeronautics and Space Administration");
		 DEPT_CODES.put("N", "DOD: DOD Agencies not listed; e.g., DIA, NSA, DLA, DNA");
		 DEPT_CODES.put("O", "FORGN: Host country - for all circuits required by any country which is host to the United States");
		 DEPT_CODES.put("P", "NCS: Other U.S. departments, agencies, commissions, or commercial companies not listed; e.g., Department of Justice, requirement by a commercial company");
		 DEPT_CODES.put("Q", "FEMA: Federal Emergency Management Agency");
		 DEPT_CODES.put("R", "CINCs: Commanders in Chief Command and Control Circuits");
		 DEPT_CODES.put("S", "OSD: Office of the Secretary of Defense");
		 DEPT_CODES.put("T", "FORGN: Treaty organizations; e.g., NATO");
		 DEPT_CODES.put("U", "ARMY: Department of the Army");
		 DEPT_CODES.put("W", "WHCA: White House Communications Agency");
		 DEPT_CODES.put("X", "DOC: Department of Commerce");
		 DEPT_CODES.put("Y", "JUWTF: Joint Unconventional Warfare Tactical Forces Headquarters");
		 DEPT_CODES.put("Z", "MARCOR: Marine Corps");
		 DEPT_CODES.put("1", "SFOB: Special Forces Operations Base");
		 DEPT_CODES.put("2", "AFSOB: Air Force Special Operations Base");
		 DEPT_CODES.put("3", "NSWTG: Navy Special Warfare Task Group");
		 DEPT_CODES.put("4", "COSCOMFSSG: Tactical Support Command; eg., FSSG Component Combat Service Support Element, Force Service Support Group");
		 DEPT_CODES.put("5", "TCA: Teltran Communications Analysis");		 
		 
		 TreeMap<String, String> USE_CODE = new TreeMap<String, String>();
		 USE_CODE.put("01", "PAT-OP SPT DSN PRECEDENCE ACCESS THRESHOLDER (PAT) OP. SUPPORT USER ACCESS");
		 USE_CODE.put("02","PAT-C2 DSN PRECEDENCE ACCESS THRESHOLDER USER ACCESS");
		 USE_CODE.put("A2","AMACTY ADMINISTRATIVE ACTIVITIES-MISC");
		 USE_CODE.put("A3","AIM AQUISTION INFORMATION MANAGEMENT");
		 USE_CODE.put("A4","TACCS TACTICAL ARMY COMBAT SERVICE SUPPORT COMPUTER SYSTEM");
		 USE_CODE.put("A5","ACIRS ARMY CRIMINAL INVESTIGATION REPORTING SYSTEM");
		 USE_CODE.put("A7","AIN ARMY INTEROPERABILITY NETWORK");
		 USE_CODE.put("AA","ANRNET ALASKAN NORAD REGION NETWORK");
		 USE_CODE.put("AB","COMUSE IN COUNTRY COMMON USE V/TTY NETWORK");
		 USE_CODE.put("AC","AACCC ALASKAN AIR COMMAND AND CONTROL");
		 USE_CODE.put("AD","DOE-RDO DEPARTMENT OF ENERGY RADIO NETWORK");
		 USE_CODE.put("AE","DOESC DEPARTMENT OF ENERGY SUPPORT CIRCUITS");
		 USE_CODE.put("AF","HHS ASFH HHS; ASSISTANT SECRETARY FOR HEALTH");
		 USE_CODE.put("AG","ATFAD AIR TRAFFIC & AIR FACILITIES ADMINISTRATION");
		 USE_CODE.put("AH","DOE DEPARTMENT OF ENERGY");
		 USE_CODE.put("AI","AFC2N AIR FORCE COMMAND AND CONTROL NETWORK");
		 USE_CODE.put("AJ","DTRA DEFENSE THREAT REDUCTION AGENCY");
		 USE_CODE.put("AK","AFRCC AIR FORCE REMOTE COMPUTER CIRCUITS");
		 USE_CODE.put("AL","ADNET ANTI-DRUG NETWORK ATM");
		 USE_CODE.put("AM","ACIRCUITS IN SUPPORT OF AUTOMATIC MESSAGE PROCESSING SYSTEMS");
		 USE_CODE.put("AN","NASA ADMINISTRATIVE CIRCUITS");
		 USE_CODE.put("AP","NAOC VOICE NETWORK");
		 USE_CODE.put("AR","AMERICAN RED CROSS");
		 USE_CODE.put("AS","ADMINISTRATIVE SUPPORT-RECRUITING");
		 USE_CODE.put("AT","TRANSPORTATION COORDINATION NETWORK");
		 USE_CODE.put("AU","AUTOMATIC SYSTEM FOR TRANSPORTATION DATA (AUTOSTRAD)");
		 USE_CODE.put("AV","TRANSPORTATION COORDINATOR AUTO COMMAND AND CONTROL INFO SYS");
		 USE_CODE.put("AW","ATM USER ACCESS");
		 USE_CODE.put("AZ","DEFENSE STANDARD AMMUNITION COMPUTER SYSTEM");
		 USE_CODE.put("B1","TRACK SUPERVISION NET");
		 USE_CODE.put("B2","INTERFACE COORDINATION NET");
		 USE_CODE.put("B3","DATA COORDINATION NET");
		 USE_CODE.put("B4","VOICE PRODUCT NET");
		 USE_CODE.put("BB","AFCC COMMAND NETWORK");
		 USE_CODE.put("BC","CRIMINAL INVESTIGATION COMMAND MNGEMNT INFO SYS (CIDOMIS)");
		 USE_CODE.put("BD","FLEET BROADCAST");
		 USE_CODE.put("BE","SUBMARINE COMMUNICATIONS");
		 USE_CODE.put("BN","JOINT BROADCAST SYSTEM (JBS) NATO - COMMAND AND CONTROL AUGMENTATION");
		 USE_CODE.put("BS","JOINT BROADCAST SYSTEM (JBS) - COMMAND AND CONTROL AUGMENTATION");
		 USE_CODE.put("C1","CLAIMS NETWORK");
		 USE_CODE.put("C2","EUROPEAN COMMISSARY REGION-82");
		 USE_CODE.put("C3","COMMUNICATIONS/ELECTRONICS ACTIVITIES-MISC");
		 USE_CODE.put("C4","COMPTROLLER ACTIVITIES-MISC");
		 USE_CODE.put("C5","CONTRACTOR SUPPORT SERVICES-MISC");
		 USE_CODE.put("C6","COMPUTER ASSISTED FORCE MANAGEMENT SYSTEM");
		 USE_CODE.put("C7","CA CON APPROVAL REQS (PSEUDO CCSDS ASSOC WITH EQUIP-ONLY TSRS)");
		 USE_CODE.put("C8","USAREUR COMMUNITY AUTOMATION SYSTEM SUPPORT");
		 USE_CODE.put("C9","ARMY COMSEC COMMODITY LOGISTICS ACCOUNTING INFO MANAGEMENT SYS");
		 USE_CODE.put("CA","JOINT SURVEILLANCE SYSTEM");
		 USE_CODE.put("CB","DEFENSE SIMULATION AND INTEGRATION NETWORK");
		 USE_CODE.put("CC","CRIMINAL INVESTIGATION COMMAND MANAGEMENT INFORMATION SYSTEM");
		 USE_CODE.put("CD","NATIONAL WARNING SYSTEM");
		 USE_CODE.put("CE","DEPARTMENT OF TREASURY");
		 USE_CODE.put("CF","PACFLT COMMAND AND CONTROL");
		 USE_CODE.put("CG","DSI BACKBONE TRUNK/CIRCUIT");
		 USE_CODE.put("CH","DSI ACCESS CIRCUIT");
		 USE_CODE.put("CI","DEPARTMENT OF TREASURY - CUSTOMS (TECS)");
		 USE_CODE.put("CJ","COMMERCIAL SATELLITE COMMUNICATIONS INITIATIVE");
		 USE_CODE.put("CL","CONTROL LINE");
		 USE_CODE.put("CM","COMMUNICATIONS MANAGEMENT OFFICES REPORTING NETWORK");
		 USE_CODE.put("CN","DEF ADV RESEARCH PROJ AGCY RESOURCES SHARING COMPUTER NETWORK");
		 USE_CODE.put("CO","CHIEF OF NAVAL OPERATIONS FLASH NETWORK");
		 USE_CODE.put("CS","SPACE COMMAND DIGITAL INTEGRATED NETWORK");
		 USE_CODE.put("CT","CONTINGENCIES");
		 USE_CODE.put("CV","DEFENSE COMMISSARY AGENCY - FTS2000 TO 2001 TRANSITION");
		 USE_CODE.put("CW","DEPARTMENT OF COMMERCE");
		 USE_CODE.put("CY","CINCEUR COMMAND AND CONTROL NETWORK");
		 USE_CODE.put("CZ","DEFENSE FEDERAL CREDIT UNIONS");
		 USE_CODE.put("D4","DEFENSE CONTRACT AUDIT AGENCY");
		 USE_CODE.put("D5","DATA PROCESSING/SOFTWARE DEVELOPMENT #NAME?");
		 USE_CODE.put("D9","SIPRNET DIAL-IN-SERVICE - COMMSERVER");
		 USE_CODE.put("DA","DEFENSE INFORMATION SYSTEMS AGENCY CIRCUITS");
		 USE_CODE.put("DB","THIRD AF OPERATIONAL NETWORK");
		 USE_CODE.put("DC","AUTODIN COORDINATION CIRCUITS (VALIDATED BY HQ); DISA)");
		 USE_CODE.put("DD","DISA OPERATIONS CONTROL COMPLEX NETWORK");
		 USE_CODE.put("DE","17TH AF OPERATIONAL NETWORK");
		 USE_CODE.put("DF","NAVY DIRECTION FINDING NETWORK");
		 USE_CODE.put("DG","DEFENSE TECHNICAL INFORMATION CENTER ACCESS");
		 USE_CODE.put("DH","DEFENSE ENROLLMENT/ELIGIBILITY REPORTING SYSTEM");
		 USE_CODE.put("DI","DEFENSE INTELLIGENCE AGENCY COMMUNICATIONS");
		 USE_CODE.put("DJ","NATIONAL MILITARY COMMAND AND CONTROL SUPPORT");
		 USE_CODE.put("DK","IMPROVED EMERGENCY MESSAGE AUTOMATIC TRANSMISSION SYSTEM-PHASE");
		 USE_CODE.put("DL","DEFENSE SATELLITE COMMUNICATIONS SYSTEM");
		 USE_CODE.put("DM","EMERGENCY MESSAGE AUTOMATIC TRANSMISSION SYSTEM (JCS)");
		 USE_CODE.put("DN","CRITICAL INTELLIGENCE COMMUNICATIONS");
		 USE_CODE.put("DO","OFFICE OF THE SECRETARY OF DEFENSE");
		 USE_CODE.put("DP","NSA OPERATIONAL SUPPORT");
		 USE_CODE.put("DQ","USAFE AIR WEAPONS CONTROL NETWORK (412L)");
		 USE_CODE.put("DR","DOD DEPENDENT SCHOOL COMPUTER INTERCONNECT");
		 USE_CODE.put("DS","DIPLOMATIC TELECOMMUNICATIONS SERVICE");
		 USE_CODE.put("DT","PENTAGON MINUTEMAN NETWORK");
		 USE_CODE.put("DU","ALLIED TACTICAL OPERATIONS CENTER COMMAND & CONTROL NETWORK");
		 USE_CODE.put("DV","USAFE COMPUTER-REMOTE/COMPUTER-COMPUTER");
		 USE_CODE.put("DW","USAFE HQ COMMAND AND CONTROL");
		 USE_CODE.put("DX","DSS CONUS ACCESS T-1");
		 USE_CODE.put("DY","USAFE OPERATIONS SUPPORT VOICE");
		 USE_CODE.put("DZ","NIPRNET DIAL-IN-SERVICE - COMMSERVER");
		 USE_CODE.put("E2","TRAINING-MISCELLANEOUS");
		 USE_CODE.put("E3","FACILITIES ENGINEERING ACTIVITIES-MISC");
		 USE_CODE.put("E4","US ARMY ENGINEERING COMMUNICATIONS");
		 USE_CODE.put("E5","ENHANCED FREQUENCY RESOURCE RECORDS SYSTEM");
		 USE_CODE.put("E7","CORPS OF ENGINEERS AUTOMATED PROCESSING (CEAP) NETWORK E8 D EISNET ENGINEER INFORMATION COMMUNICATIONS SYSTEMS");
		 USE_CODE.put("E8","ENGINEER INFORMATION COMMUNICATIONS SYSTEMS");
		 USE_CODE.put("EA","AIR INTELLIGENCE AGENCY");
		 USE_CODE.put("EB","EMERGENCY BROADCAST SYSTEM");
		 USE_CODE.put("EK","ELECTRONIC KEY MANAGEMENT SYSTEM");
		 USE_CODE.put("EM","ENHANCED MOBILE SATELLITE SYSTEM (EMSS)");
		 USE_CODE.put("ET","NESS); TIROS-N SATELLITE SYSTEM");
		 USE_CODE.put("EU","EUCOM CONTINGENCY CIRCUITS");
		 USE_CODE.put("EV","EUROPEAN TELEPHONE SYSTEM CIRCUITS");
		 USE_CODE.put("EW","EUROPEAN TELEPHONE SYSTEM TRUNK CONVERSION");
		 USE_CODE.put("EX","EURO EXERCISE CIRCUITS (USED FOR TEMPORARY CIRCUITS ONLY)");
		 USE_CODE.put("EY","EXECUTIVE SECURE MOBILE COMMUNICATIONS SYSTEM");
		 USE_CODE.put("F1","INTERCENTER AIR TRAFFIC MOVEMENT AND CONTROL - OVERSEAS F2 L ATMCR AIR TRAFFIC MOVEMENT AND CONTROL INTERCENTER RADAR HANDOFF");
		 USE_CODE.put("F3","AIR TRAFFIC MOVEMENT AND CONTROL INTRA-AREA NONRADAR");
		 USE_CODE.put("F4","AIR TRAFFIC MOVEMENT AND CONTROL INTRA-AREA RADAR HANDOFF");
		 USE_CODE.put("F5","AIR TRAFFIC MOVEMENT AND CONTROL TOWER TO TOWER");
		 USE_CODE.put("F6","PREFLIGHT BRIEFING FILE AND CLOSE (FLIGHT ASSISTANCE)");
		 USE_CODE.put("F7","AIR TRAFFIC MOVEMENT AND CONTROL INTERCENTER NONRADAR");
		 USE_CODE.put("F8","EXCHANGE OF MILITARY FLIGHT MOVEMENT INFORMATION");
		 USE_CODE.put("F9","SERVICE F AIR TRAFFIC MOVEMENT AND CONTROL - INTERPHONE");
		 USE_CODE.put("FA","AFLC VOICE TIELINE NETWORK");
		 USE_CODE.put("FB","FOREIGN BROADCAST INFORMATION");
		 USE_CODE.put("FC","FEDERAL BUREAU OF INVESTIGATION");
		 USE_CODE.put("FE","AFLC AIR LOGISTICS NETWORK");
		 USE_CODE.put("FI","DEPARTMENT OF JUSTICE); ADMIN CIRCUITS");
		 USE_CODE.put("FJ","U.S. DEPARTMENT OF JUSTICE TELECOMMUNICATIONS SYSTEM (JUST)");
		 USE_CODE.put("FK","U.S. DEPARTMENT OF JUSTICE DRUG ENFORCEMENT ADMINISTRATION");
		 USE_CODE.put("FO","FLEET OPERATIONS CONTROL NETWORK");
		 USE_CODE.put("FP","FTS CIRCUITS OF THE GSA");
		 USE_CODE.put("FZ","FACILITY ENGINEERS SUPPLY SYSTEM");
		 USE_CODE.put("G1","TOWER-TO-TOWER EXTERNAL OVERRIDE CIRCUIT");
		 USE_CODE.put("G2","WEATHER MESSAGE SYSTEM CENTER HIGH-SPEED DATA CIRCUITS");
		 USE_CODE.put("G3","SERVICE A CENTER TO FSS COLLECT AND DISSEMINATE WEATHER");
		 USE_CODE.put("G4","SERVICE A FROM WMSC TO NON-GOVERNMENTAL WEATHER SERVICE");
		 USE_CODE.put("G5","SERVICE A FROM WMSC TO MILITARY STATION OF WEATHER INFO");
		 USE_CODE.put("G6","SERVICE A FROM WMSC TO WEATHER BUREAU OFFICE G7 L SVCNW SERVICE COLLECT & DISSEMINATE NONAVIATION WEATHER INFO");
		 USE_CODE.put("G8","SVC O COLLECT & DISSEMINATE INTRNTL FLIGHT WEATHER-DOMESTIC");
		 USE_CODE.put("G9","RCAG EMERGENCY COMMUNICATIONS (BUEC)");
		 USE_CODE.put("GB","TABS FAST FILE AND OPEN-ENDED SERVICE");
		 USE_CODE.put("GC","FAA SERVICE A");
		 USE_CODE.put("GD","SERVICE B MISCELLANEOUS CIRCUITS");
		 USE_CODE.put("GF","CENTRAL FLOW CONTROL BETWEEN ATC FACILITIES (COMMAND/CONTROL)");
		 USE_CODE.put("GG","FAA SERVICE O");
		 USE_CODE.put("GJ","FAA REMOTE-CONTROLLED AIR GROUND");
		 USE_CODE.put("GK","NATIONAL WEATHER SERVICE INFLIGHT BRIEFING - SERVICE F");
		 USE_CODE.put("GL","FAA COMMAND AND CONTROL");
		 USE_CODE.put("GM","WMCS TELETYPE CIRCUITS (MISCELLANEOUS)");
		 USE_CODE.put("GP","GENERAL FINANCE ACCOUNTING");
		 USE_CODE.put("GS","TURKISH NATIONAL CIRCUITS");
		 USE_CODE.put("GU","U.S. COAST GUARD");
		 USE_CODE.put("GV","GLOBAL COMMAND AND CONTROL SYSTEM (GCCS) BACKBONE");
		 USE_CODE.put("GW","GLOBAL COMMAND AND CONTROL SYSTEM (GCCS) ACCESS LINE");
		 USE_CODE.put("H1","DIRECTION FINDER REMOTING");
		 USE_CODE.put("H2","AERONAUTICAL FIXED TELECOM NETWORK (AFTN)");
		 USE_CODE.put("H3","FLIGHT PLAN TRANSMISSION (HIGH-SPEED) SERVICE B");
		 USE_CODE.put("H4","FLIGHT PLAN TRANSMISSION (LOW-SPEED) OVERLOAD AREA & SUPP.");
		 USE_CODE.put("H5","FLIGHT PLAN & MILITARY PLANNING FLIGHT INFO SERVICE B");
		 USE_CODE.put("H6","ATM&C CENTER-TO-CENTER COMPUTER DATA");
		 USE_CODE.put("H7","AIRCRAFT DEPARTURE & ARRIVAL INFO CENTER TO TOWER");
		 USE_CODE.put("H8","FLIGHT PLAN INFO COMPUTER FDEP CENTER TO TOWER");
		 USE_CODE.put("H9","IFR FLIGHT PLAN ENTRY (CIVILIAN OR MILITARY) SERVICE B");
		 USE_CODE.put("HA","AFSC VOICE TIELINE NETWORK");
		 USE_CODE.put("HB","AFSC PROJECT SUPPORT NETWORK");
		 USE_CODE.put("HC","16 AF OPERATIONAL CIRCUITS");
		 USE_CODE.put("HD","TACTICAL AIR CONTROL SYSTEM (407L) CIRCUITS");
		 USE_CODE.put("HE","USCENTAF COMMAND AND CONTROL COMMUNICATIONS");
		 USE_CODE.put("HF","HF GLOBAL COMMUNICATIONS SYSTEM");
		 USE_CODE.put("HN","MEDICAL EXPENSE AND PERFORMANCE REPORTING SYSTEM");
		 USE_CODE.put("HS","HEALTH RESOURCES AND SERVICES ADMINISTRATION");
		 USE_CODE.put("HV","AFSC MULTIPLE SATELLITE TRACKING NETWORK");
		 USE_CODE.put("HW","FLIGHT DATA INPUT/OUTPUT");
		 USE_CODE.put("I1","CLASSIFIED INTERGRATED TACTICAL STRATEGIC DATA NETWORK");
		 USE_CODE.put("I2","UNCLASSIFIED INTERGRATED TACTICAL STRATEGIC DATA NETWORK");
		 USE_CODE.put("IA","U.S. INFORMATION AGENCY");
		 USE_CODE.put("IC","DSN ACCESS LINE (SEE DISAC 310-65-1); CHAPTER 14)");
		 USE_CODE.put("ID","DOD AND NON-DOD INMARSAT DITCO-PROCURED");
		 USE_CODE.put("IG","NOS GEODETIC DATA");
		 USE_CODE.put("IM","AUTOMATED INSTRUCTIONAL MANAGEMENT SYSTEM");
		 USE_CODE.put("IN","IMMIGRATION AND NATIONALIZATION SERVICE");
		 USE_CODE.put("IP","NIPRNET ACCESS LINE");
		 USE_CODE.put("IR","DISN BACKBONE ROUTER INTER-NODAL CIRCUIT (INC)");
		 USE_CODE.put("IT","ARMY INSPECTOR GENERAL NETWORK");
		 USE_CODE.put("IU","DISN XMN RESOURCE MUX (BLACK) INTER-NODAL NON-CHANNELIZED CIRCUIT INTER-NODAL (BACKBONE) INFRASTRUCTURE");
		 USE_CODE.put("IV","INTER-NODAL BROADBAND TRANSMISSION MANAGER CIRCUIT");
		 USE_CODE.put("IW","DISN MULTIPLEXER PACKAGE SYSTEM");
		  USE_CODE.put("IX","DISN STAT MULTIPLEXER CONTROL SYSTEM");
		  USE_CODE.put("IY","DISN SONET TRANSMISSION");
		  USE_CODE.put("IZ","DISN POP-SPINE ROUTER INTER-NODAL CIRCUIT (INC)");
		  USE_CODE.put("J1","LOCAL TELETYPE CIRCUITS");
		  USE_CODE.put("J2","RCAG REMOTE COMMUNICATIONS AIR-GROUND PRIMARY CIRCUIT");
		  USE_CODE.put("J3","RCAG LASS CIRCUITS");
		  USE_CODE.put("J4","LONG-RANGE RADAR REMOTING NARROWBAND CIRCUITS");
		  USE_CODE.put("J5","AIRPORT SURVEILLANCE LOCAL ON-BASE RADAR");
		  USE_CODE.put("J6","NATIONAL WEATHER SERVICE RADAR FAX");
		  USE_CODE.put("J7","RADIO & TV MICROWAVE LINK DATA TRANSMITTED BETWEEN FACILITIES");
		  USE_CODE.put("J8","RCAG SS-1 CIRCUIT");
		  USE_CODE.put("J9","RCAG REDUNDANT OR DIVERSE ROUTING CIRCUITS");
		  USE_CODE.put("JA","ACC PRIMARY ALERT SYSTEM");
		  USE_CODE.put("JC","ACC SHORT ORDER NETWORK");
		  USE_CODE.put("JD","ACC COMMAND AND CONTROL NETWORK (465L)");
		  USE_CODE.put("JE","ACC TELETYPEWRITER NETWORK");
		  USE_CODE.put("JF","DEFENSE METEOROLOGICAL SATELLITE PROGRAM (DMSP)");
		  USE_CODE.put("JG","ACC TELEPHONE NETWORK");
		  USE_CODE.put("JH","JOINT OPERATIONAL TACTICAL SYSTEM");
		  USE_CODE.put("JI","JOINT WORLDWIDE INTELLIGENCE COMMUNICATIONS SYSTEM BACKBONE");
		  USE_CODE.put("JJ","ACC VOICE ORDERWIRE JK A COMUSKORCC COMUSKOREA COMMAND); CONTROL); AND OPERATIONAL NETWORK");
		  USE_CODE.put("JL","ACC POST ATTACK COMMAND & CONTROL SYSTEM (GROUND ENVIRONMENT)");
		  USE_CODE.put("JM","ACC ICBM SUPPORT COMMUNICATIONS");
		  USE_CODE.put("JN","JOINT INTERFACE TEST FORCE-JOINT INTEROPERBLY TECH CMD/CTRL SYS");
		  USE_CODE.put("JP","PACIFIC COMMAND JOINT NETWORK");
		  USE_CODE.put("JQ","MILITARY PAY REDESIGN-JOINT SERVICES SOFTWARE");
		  USE_CODE.put("JS","JOINT STUDIES GROUP");
		  USE_CODE.put("JT","JOINT 'STEP' PROGRAM");
		  USE_CODE.put("JU","JUMPS INQUIRY TELEPROCESSING SYSTEM");
		  USE_CODE.put("JX","COMUSJAPAN COMMAND AND CONTROL COMMUNICATIONS");
		  USE_CODE.put("K1","NAVIGATIONAL HOMING BEACON TO FLIGHT SERVICE CENTER");
		  USE_CODE.put("K2","ILS LOCAL APPROACH AIDE TO TOWER - LOCAL SERVICE");
		  USE_CODE.put("K3","REMOTE TRANSMITTER RECEIVER LOCAL AIRPORT SERVICE");
		  USE_CODE.put("K4","REMOTE COMMUNICATIONS OUTLET MULTIPLE FREQUENCY");
		  USE_CODE.put("K5","INTERNATIONAL FLIGHT SERVICE RECEIVE-TRANSMIT AIR-GROUND");
		  USE_CODE.put("K6","MISCELLANEOUS REMOTE FACILITY CIRCUITS");
		  USE_CODE.put("K7","RVR.RRH.RBC. MISCELLANEOUS WEATHER MEASURING EQUIPMENT (LOCAL)");
		  USE_CODE.put("K8","ENROUTE FLIGHT ADVISORY SERVICE (EFAS)");
		  USE_CODE.put("K9","AIRPORT LIGHTING SYSTEM (LOCAL)");
		  USE_CODE.put("KA","INTELLIGENCE");
		  USE_CODE.put("KB","KOREAN WIDEBAND AREA NETWORK (KWAN)");
		  USE_CODE.put("KC","KPREAN BATTLE SIMULATION CENTER (KBSC)");
		  USE_CODE.put("KE","NMFS SOUTHWEST TELECOMMUNICATIONS SYSTEM");
		  USE_CODE.put("KG","CENTRAL INFORMATION REFERENCE AND CONTROL SYSTEM");
		  USE_CODE.put("KK","COMMAND AND CONTROL MISC");
		  USE_CODE.put("KL","KEYING LINES");
		  USE_CODE.put("KM","COMBINED FORCES REPUBLIC OF KOREA COMMAND AND CONTROL (CFROK)");
		  USE_CODE.put("KN","NAOC TELETYPEWRITER NETWORK");
		  USE_CODE.put("KR","ANMCC SUPPORT");
		  USE_CODE.put("KS","RELOCATION COPAN");
		  USE_CODE.put("KT","ANMCC SUPPORT (FIRST ALTERNATE)");
		  USE_CODE.put("KU","USAF RELOCATION VOICE NETWORK");
		  USE_CODE.put("KW","NCA/JCS MINIMUM ESSENTIAL EMERGENCY COMMUNICATIONS NETWORK");
		  USE_CODE.put("KZ","NMCS DATA TRANSMISSION");
		  USE_CODE.put("L1","REIMBURSABLE NAFEC");
		  USE_CODE.put("L2","REIMBURSABLE R&D");
		  USE_CODE.put("L3","REIMBURSABLE ALASKA ADMINISTRATION");
		  USE_CODE.put("L4","REIMBURSABLE MANAGEMENT SYSTEM");
		  USE_CODE.put("L6","STAFF COMMUNICATIONS COMMAND AND CONTROL (STAFC)");
		  USE_CODE.put("L7","TELEWRITER); TALOS CIRCUITS");
		  USE_CODE.put("L8","ALASKA TELEPHONE SWITCHING CENTER");
		  USE_CODE.put("LB","LEGAL ACTIVITIES - MISC");
		  USE_CODE.put("LC","PUBLIC-OFFERING TELEPHONE/TELEGRAPH NETWORK");
		  USE_CODE.put("LE","USAREUR LIBRARY EDUCATION NTWK AND PATRON ORIENTED LIBRARY SYS");
		  USE_CODE.put("LH","USATACCOM U.S. ARMY ALASKA TACTICAL COMMUNICATIONS");
		  USE_CODE.put("LP","DSN LOOP-AROUND TRUNKS");
		  USE_CODE.put("LR","LIVE OAK CIRCUITS BETWEEN NON-U.S. COMPONENTS");
		  USE_CODE.put("LS","JOINT COMPUTER-AIDED ACQUISTION AND LOGISTIC SUPPORT");
		  USE_CODE.put("LT","LOGISTICS/SUPPLY ACTIVITIES-MISC");
		  USE_CODE.put("LW","LIGHTWAVE DIVISION MULTIPLEX PACKAGE SYSTEM");
		  USE_CODE.put("MA","EASTERN MISSILE RANGE MISSILE SPACE NETWORK");
		  USE_CODE.put("MB","MANPOWER/PERSONNEL ACTIVITIES-MISC");
		  USE_CODE.put("MC","U.S. MARINE CORPS");
		  USE_CODE.put("MD","MOBILIZATION");
		  USE_CODE.put("MF","MEDICAL ACTIVITY (MEDACT)");
		  USE_CODE.put("MH","TRANSPORTATION ACTIVITIES-MISC");
		  USE_CODE.put("MI","MANAGEMENT INFORMATION SYSTEMS (MIS)");
		  USE_CODE.put("ML","COMMON USER ELECTRONIC MAIL SERVICE");
		  USE_CODE.put("MN","METROPOLITAN AREA NETWORK");
		  USE_CODE.put("MP","ARMY MATERIAL PLAN-MODERNIZATION NETWORK");
		  USE_CODE.put("MR","WESTERN MISSILE/SPACE SUPPORT NETWORK");
		  USE_CODE.put("MU","ARMY TEST & EVALUATION MANAGEMENT NETWORK");
		  USE_CODE.put("MV","U.S. MILITARY ASSISTANCE NETWORK");
		  USE_CODE.put("MW","MORALE); WELFARE AND RECREATION ACTIVITIES-MISC");
		  USE_CODE.put("MZ","NATIONAL IMAGERY AND MAPPING AGENCY COMMUNICATIONS");
		  USE_CODE.put("N1","NORTHSTAR NETWORK (NAOC); LOOKING GLASS); AF-1)");
		  USE_CODE.put("N2","COMMSERVER ACCESS - NIPRNET");
		  USE_CODE.put("NA","USREDCOM COMMAND AND CONTROL CIRCUITS");
		  USE_CODE.put("NB","USCENTCOM COMMAND AND CONTROL CIRCUITS");
		  USE_CODE.put("NC","NETWORK MANAGEMENT CIRCUITS (NETWORK OPERATION CENTERS)");
		  USE_CODE.put("NF","WASHINGTON AREA WARNING SYSTEM");
		  USE_CODE.put("NG","NATIONAL GUARD - TRAINING");
		  USE_CODE.put("NH","DOD SATELLITE EDUCATION NETWORK");
		  USE_CODE.put("NJ","NATIONAL GUARD NETWORK");
		  USE_CODE.put("NK","INFORMATION DISSEMINATION");
		  USE_CODE.put("NM","NMCI NETWORK ACCESS TRUNK");
		  USE_CODE.put("NN","FEMA NATIONAL VOICE SYSTEM");
		  USE_CODE.put("NP","FEDERAL EMERGENCY MANAGEMENT AGENCY SUPPORT");
		  USE_CODE.put("NS","NAVY SECURITY GROUP");
		  USE_CODE.put("NT","FEMA NATIONAL TELETYPE SYSTEM");
		  USE_CODE.put("OC","NAVAL COMMUNICATIONS MASTER CONTROL STATION COMMAND/CORD");
		  USE_CODE.put("OJ","NASCOM OPERATIONAL DSN");
		  USE_CODE.put("OL","LINK ORDERWIRE");
		  USE_CODE.put("OM","TELEMETRY ORDERWIRE");
		  USE_CODE.put("ON","NON-GIG ORDERWIRE");
		  USE_CODE.put("OO","SYSTEM ORDERWIRE");
		  USE_CODE.put("OR","TELETYPE ORDERWIRE");
		  USE_CODE.put("OU","ORDINANCE ACTIVITIES-MISC");
		  USE_CODE.put("OX","EXPRESS ORDERWIRE");
		  USE_CODE.put("PA","AF COMMAND POST VOICE NETWORK");
		  USE_CODE.put("PB","AF ALTERNATE COMMAND NETWORK");
		  USE_CODE.put("PC","AF COMMAND NETWORK");
		  USE_CODE.put("PD","PROGRAMMABLE INDICATOR DATA PROCESSOR CIRCUITS");
		  USE_CODE.put("PE","AF FLIGHT DATA ENTRY PRINT-OUT NETWORK");
		  USE_CODE.put("PF","PUBLIC AFFAIRS ACTIVITIES-MISC");
		  USE_CODE.put("PG","AF PROJECT MANAGEMENT NETWORK");
		  USE_CODE.put("PH","PAC ARMY); AIR FORCE); NAVY NETWORK");
		  USE_CODE.put("PI","PANAMA IDNX NETWORK");
		  USE_CODE.put("PJ","PERSONNEL DEPLOYMENT AND DISTRIBUTION MGMT NETWORK");
		  USE_CODE.put("PK","PANAMA MICROWAVE SYSTEM");
		  USE_CODE.put("PL","PARTNERSHIP FOR PEACE INFORMATION MANAGEMENT SYSTEM (PIMS)");
		  USE_CODE.put("PM","PASSENGER TRAFFIC MANAGEMENT SYSTEM");
		  USE_CODE.put("PP","ARMY CONTINUITY OF OPERATIONS NETWORK");
		  USE_CODE.put("PQ","ACQUISITION/PROCUREMENT ACTIVITIES-MISC");
		  USE_CODE.put("PR","CIVILIAN PERSONNEL NETWORK");
		  USE_CODE.put("PS","COMMERCIAL PRESS SERVICES");
		  USE_CODE.put("PT","PETROLEUM AND DISTRIBUTION COMMAND NETWORK");
		  USE_CODE.put("PV","PLANS AND POLICY ACTIVITIES-MISC");
		  USE_CODE.put("PW","PROPERTY DISPOSAL OFFICE/RESALE ACTIVITIES-MISC");
		  USE_CODE.put("PX","ARMY AND AIR FORCE EXCHANGE SERVICE");
		  USE_CODE.put("Q1","AUTOMATED WEATHER DISTRIBUTION SYSTEM");
		  USE_CODE.put("Q2","NEXT GENERATION WEATHER RADAR");
		  USE_CODE.put("Q3","HIGH FREQUENCY REGIONAL BROADCAST");
		  USE_CODE.put("QA","MAC COMMAND CONTROL RECORD COMMUNICATIONS SYSTEM");
		  USE_CODE.put("QD","WEATHER ACTIVITIES-MISC");
		  USE_CODE.put("QE","WEATHER TELETYPEWRITER 1 (CIVIL FAA A); C); O)");
		  USE_CODE.put("QG","WEATHER TELETYPEWRITER 2");
		  USE_CODE.put("QI","WEATHER FAX 1 (CIVIL); U.S. WEATHER BUREAU)");
		  USE_CODE.put("QJ","WEATHER FAX 2");
		  USE_CODE.put("QM","MAC COMMAND CONTROL VOICE CIRCUITS");
		  USE_CODE.put("QN","NATO CIRCUITS BETWEEN U.S. COMPONENTS");
		  USE_CODE.put("QO","NATO CIRCUITS BETWEEN NON-U.S. COMPONENTS");
		  USE_CODE.put("QR","NATO CIRCUITS BETWEEN NON-U.S. AND A U.S. COMPONENT");
		  USE_CODE.put("QS","MAC OPERATIONAL SUPPORT CIRCUITS");
		  USE_CODE.put("QU","TACTICAL DIGITAL INTERSWITCH TRUNK. (NON GIG)");
		  USE_CODE.put("RA","ARMY RECRUITING AND ACCESSION DATA SYSTEM");
		  USE_CODE.put("RC","C&C OF RESERVE FORCES WITHIN RESERVE COMMAND ORGANIZATION");
		  USE_CODE.put("RF","PACAF COMMAND AND CONTROL NETWORK");
		  USE_CODE.put("RH","RESEARCH AND DEVELOPMENT - MISC");
		  USE_CODE.put("RI","TECHNICAL RESEARCH INSTITUTE");
		  USE_CODE.put("RM","REMOTE ALARM/INTRUSION ALERT SYSTEMS-MISC");
		  USE_CODE.put("RN","FOREIGN CIRCUITS BETWEEN U.S. COMPONENTS");
		  USE_CODE.put("RP","RANDOM ACCESS PERSONNEL INFORMATION DATA SYSTEM");
		  USE_CODE.put("RQ","MONITORING AND CONTROL CKT FOR THE DEFENSE RED SWITCH NETWORK");
		  USE_CODE.put("RR","FOREIGN CIRCUITS BETWEEN NON-U.S. AND U.S. COMPONENTS");
		  USE_CODE.put("RS","AFRTS/STARS AND STRIPES");
		  USE_CODE.put("RT","ARMY TRAINING REQUIREMENTS AND RESOURCES SYSTEM");
		  USE_CODE.put("RU","INTERSWITCH TRUNK BETWEEN MILDEP DRSN SWITCHES");
		  USE_CODE.put("RV","ACCESS CIRCUIT INTO MILDEP DEFENSE RED SWITCH NETWORK (DRSN)");
		  USE_CODE.put("RW","INTERSWITCH TRUNK BETWEEN GIG DRSN SWITCHES");
		  USE_CODE.put("RX","ACCESS CIRCUIT INTO GIG DEFENSE RED SWITCH NETWORK (DRSN)");
		  USE_CODE.put("RY","INTERSWITCH TRUNK BETWEEN GIG DRSN AND DSN SWITCH");
		  USE_CODE.put("RZ","RESERVE COMPONENT AUTOMATION SYSTEM");
		  USE_CODE.put("S1","ARMY SUPERCOMPUTER NETWORK");
		  USE_CODE.put("S2","SECURITY ASSISTANCE TRAINING MANAGEMENT SYSTEM");
		  USE_CODE.put("S3","INTELLIGENCE AND SECURITY AUTOMATED NETWORK (AMC)");
		  USE_CODE.put("S5","CORPS THEATER ADP SERVICE CENTER NETWORK-SECOND GENERATION");
		  USE_CODE.put("S6","STREAMLING INFORMATION SVC OPS CONSOLIDATION STUDY (SISOCS)");
		  USE_CODE.put("S7","SIPRNET ACCESS LINE");
		  USE_CODE.put("S8","SIPRNET CONTROL CIRCUITS");
		  USE_CODE.put("S9","SUSTAINING BASE INFORMATION SERVICE");
		  USE_CODE.put("SA","SERIOUS INCIDENT REPORTING");
		  USE_CODE.put("SB","SPECIAL COMMUNICATIONS SUPPORT TO SAUDI ARABIA");
		  USE_CODE.put("SC","SCP NETWORK CONTROL CIRCUIT");
		  USE_CODE.put("SD","ISDN TERMINAL");
		  USE_CODE.put("SE","SCIENTIFIC AND ENGINEERING (S&E) COMMUNICATIONS SUPPORT");
		  USE_CODE.put("SF","STANDARD ARMY FINANCIAL SYSTEM (STANFINS)");
		  USE_CODE.put("SL","DEFENSE SIMULATIONS INTERNET/SYNTHETIC THEATER OF WAR-EUROPE");
		  USE_CODE.put("SM","STRATEGIC DEFENSE INITIATIVES (SDI) SUPPORT");
		  USE_CODE.put("SN","SHARED LOGIC OFFICE AUTOMATION NETWORK");
		  USE_CODE.put("SO","SPARE CHANNEL");
		  USE_CODE.put("SP","SPARE PATCH/INTERCONNECT");
		  USE_CODE.put("SR","AN/USC-28 RECEIVE 'PN' SELECT CONTROL");
		  USE_CODE.put("SS","SPECIAL SECURITY OFFICE NETWORK");
		  USE_CODE.put("ST","SIPRNET BACKBONE");
		  USE_CODE.put("SU","DEFENSE LOGISTICS AGENCY LEASED COMMUNICATIONS SERVICE");
		  USE_CODE.put("SV","SHIP REPAIR/ENGINEERING ACTIVITIES-MISC");
		  USE_CODE.put("SW","COMMSERVER ACCESS - SIPRNET");
		  USE_CODE.put("SX","ARMY SYS WIDE PROJECT FOR ELECTRONIC EOPT AT DEPOTS-EXTENDED");
		  USE_CODE.put("SY","SONET TRANSMISSION SYSTEM");
		  USE_CODE.put("SZ","AIR FORCE SUPPORT DATA NETWORK");
		  USE_CODE.put("T1","GIG STATISTICAL TDM PACKAGE SYSTEM");
		  USE_CODE.put("T2","NON-GIG TDM PKG SYS (FOR USE WITH TYPE SERVICE CODE 'M'/'X')");
		  USE_CODE.put("T3","TELTRAN COMMUNICATIONS ANALYSIS");
		  USE_CODE.put("T4","NON-GIG TDM PKG SYSTEM (FOR USE WITH TYPE SERVICE CODE 'M'/'X')");
		  USE_CODE.put("T5","NON-GIG STATISTICAL TDM PKG SYS (USE WITH TYPE SVC CODE 'M')");
		  USE_CODE.put("T6","TACTICAL DIGITAL INFORMATION LINK");
		  USE_CODE.put("T7","TACTICAL VOICE INFORMATION LINK");
		  USE_CODE.put("T8","CARIBBEAN REGIONAL COMMUNICATIONS NETWORK");
		  USE_CODE.put("T9","DSN SWITCH-TO-SWITCH/SWITCH-TO-DPAS TDM PACKAGE SYSTEM");
		  USE_CODE.put("TA","TAC OPERATIONS SUPPORT TTY NETWORK");
		  USE_CODE.put("TB","TAC COMMAND AND CONTROL VOICE ALERTING SYSTEM");
		  USE_CODE.put("TC","TAC OPERATIONS SUPPORT VOICE SYSTEM NETWORK");
		  USE_CODE.put("TD","TAC REMOTE COMPUTER CIRCUITS");
		  USE_CODE.put("TE","TEMP ARMY); AF); NAVY TEMP (SEE DISAC 310-65-1); CHAPTER 14)");
		  USE_CODE.put("TF","DEPARTMENT OF STATE");
		  USE_CODE.put("TG","TAC TACTICAL AIR CONTROL SYSTEM IN-GARRISON TRAINING NETWORK");
		  USE_CODE.put("TH","TRI-CARE HEALTH SYSTEM");
		  USE_CODE.put("TJ","CRITICOM RED TDM PACKAGE SYSTEM");
		  USE_CODE.put("TK","CRITICOM BLACK TDM PACKAGE SYSTEM");
		  USE_CODE.put("TL","US ARMY TRADOC LIBRARY AND INFORMATION NETWORK (TRAILNET)");
		  USE_CODE.put("TM","GIG AN/FCC-100 PKG SYS(DTN ONLY)(CODE TN FOR OTHR FCC-100TRKS)");
		  USE_CODE.put("TN","GIG TIME DIVISION MULTILIEX PACKAGE SYSTEM");
		  USE_CODE.put("TO","TELEMETRY/ORDERWIRE PACKAGE SYSTEM TRUNK");
		  USE_CODE.put("TP","SPEECH PLUS SYSTEM");
		  USE_CODE.put("TQ","FREQUENCY SUBDIVIDED MULTIPLE MODEM SYSTEM (DIGITAL)");
		  USE_CODE.put("TR","TRACKING NETWORK");
		  USE_CODE.put("TS","DSS INTERNODAL TRUNKS");
		  USE_CODE.put("TT","DTC/DSS TRANSITION CUTOVER TRUNKS");
		  USE_CODE.put("TU","U.S. DEPARTMENT OF THE INTERIOR");
		  USE_CODE.put("TV","SYSTEM TIMING");
		  USE_CODE.put("TW","FAST PACKET SWITCHING ACCESS PACKAGE SYSTEM");
		  USE_CODE.put("TX","VFCT SYSTEM");
		  USE_CODE.put("TZ","AFTAC DATA NETWORK");
		  USE_CODE.put("U2","REMOTE DATA LINK BTWN AN EOF HOST SWITCH AND A REMOTE SWITCH");
		  USE_CODE.put("U4","DIAL SERVICE ASSISTANCE (DSA) TRUNK");
		  USE_CODE.put("U5","OPERATOR BUSY VERIFICATION TRUNK");
		  USE_CODE.put("U6","CENTRALIZED COMMERCIAL DIAL ACCESS TRUNK");
		  USE_CODE.put("U7","DSN COMMON CHANNEL SIGNALING DATA LINK");
		  USE_CODE.put("U8","EMERGENCY FACILITIES TRUNK");
		  USE_CODE.put("U9","DSA OPERATOR DEFLECT TRUNK");
		  USE_CODE.put("UA","COMMON USER TELETYPEWRITER SERVICE");
		  USE_CODE.put("UB","COMMON USER VOICE SERVICE");
		  USE_CODE.put("UC","TRUNK CIRCUIT BETWEEN VOICE CONCENTRATOR SYSTEM EQUIPMENT");
		  USE_CODE.put("UD","DRSN INTERSWITCH TRUNK");
		  USE_CODE.put("UE","COMMON USER DIGITAL DATA");
		  USE_CODE.put("UF","COMMON USER FAX (OTHER THAN WEATHER)");
		  USE_CODE.put("UH","WIDEBAND SECURE VOICE DIGITAL TRUNK (FOR USE WITH TYPE SVC M)");
		  USE_CODE.put("UI","USAREUR INSTALLATION COMMUNITY AUTOMATION SYSTEM");
		  USE_CODE.put("UJ","ARMY SIPRNET NETWORK (A-SIPR) (EUROPE USE ONLY)");
		  USE_CODE.put("UL","GIG AUTOMATIC RECORD COMMUNICATIONS NETWORK CIRCUITS");
		  USE_CODE.put("UM","SPECIAL PURPOSE NETWORK");
		  USE_CODE.put("UO","AF AIR OPERATIONS NETWORK");
		  USE_CODE.put("UQ","1ST FROM A DSN MFS TO A TACTICAL GATEWAY SWITCH");
		  USE_CODE.put("US","DSN IST FM END OFC SW (INCL END OFC SLIDE OF MF SW)TO REMOTE SW");
		  USE_CODE.put("UT","DSN ACCESS LINE FM NODE SW/END OFC SW TO NON-DSN (SVC/AGCY) SW");
		  USE_CODE.put("UU","DSN IST CONNECTING NODE SWITCHES");
		  USE_CODE.put("UV","DSN IST CONNECTING END OFC SWITCHES (COMMUNITY OF INTEREST)");
		  USE_CODE.put("UW","INTERDEPARTMENTAL DIAL TELEPHONE NETWORK");
		  USE_CODE.put("UX","DSN IST FM NODE SW TO END OFC SW");
		  USE_CODE.put("UY","DSN DIAL SUBSCRIBER");
		  USE_CODE.put("UZ","TANDEM SW INTERSITE TRUNK CKT (I.E. EPABX - EPABX)");
		  USE_CODE.put("VA","VETERANS ADMINISTRATION");
		  USE_CODE.put("VC","TRUNK CIRCUIT BETWEEN VOICE CONCENTRATOR SYSTEM EQUIPMENT");
		  USE_CODE.put("VM","VERTICAL FORCE DEVELOPMENT MANAGEMENT INFO SYSTEM(VFDMIS)");
		  USE_CODE.put("VN","PUBLIC DATA NETWORK SERVICE");
		  USE_CODE.put("VP","SPECIAL SUPPORT NETWORK (DISA USE ONLY)");
		  USE_CODE.put("VQ","MYSTIC STAR NETWORK");
		  USE_CODE.put("VS","VIDEO TELECONFERENCING SWITCHABLE TO DATA");
		  USE_CODE.put("VT","VIDEO TELECONFERENCING NETWORK");
		  USE_CODE.put("VV","VERY SMALL APERTURE (VSAT) - COMMAND AND CONTROL AUGMENTATION");
		  USE_CODE.put("VW","SATELLITE NETWORK - COMMAND AND CONTROL AUGMENTATION");
		  USE_CODE.put("VX","VIDEO TELECONFERENCING NETWORK");
		  USE_CODE.put("WA","ANTISUBMARINE WARFARE COMMUNICATIONS");
		  USE_CODE.put("WF","WASHFAX HIGH SPEED DIGITAL FACSIMILE");
		  USE_CODE.put("WH","WHEELHOUSE NETWORK");
		  USE_CODE.put("WL","WATER CONTROL); LOCK AND DAM FACILITIES");
		  USE_CODE.put("WN","WIDE AREA NETWORK");
		  USE_CODE.put("WT","WHCA TEMPORARY TRAVEL");
		  USE_CODE.put("WX","NAVY WEATHER");
		  USE_CODE.put("WY","THEATER AUTOMATED COMMAND AND CONTROL INFO MANAGEMENT SYSTEM");
		  USE_CODE.put("WZ","USAREUR TACTICAL COMMAND AND CONTROL SYSTEM");
		  USE_CODE.put("XA","AUTOMATION OF FIELD OPERATION AND SERVICES SYSTEM");
		  USE_CODE.put("XC","NWS COMPUTER PROGRAMING SUPPORT SERVICES");
		  USE_CODE.put("XD","NWS DIGITAL FACSIMILE NETWORK (DIFAX)");
		  USE_CODE.put("XF","NWS FORECAST OFFICE FACSIMILE SYSTEM (FOFAX)");
		  USE_CODE.put("XG","NWS INTERNATIONAL WEATHER COMMUNICATIONS SYSTEM");
		  USE_CODE.put("XH","NWS HYDROLOGIC FORECAST SYSTEM");
		  USE_CODE.put("XI","NWS NOAA FIRE WEATHER SERVICE");
		  USE_CODE.put("XJ","NWS INTRA-ALASKA FAX NETWORK");
		  USE_CODE.put("XL","NWS LOCAL PUBLIC SERVICE COMMUNICATIONS FACILITIES");
		  USE_CODE.put("XM","NWS NATIONAL & AVIATION METEOROLOGICAL FACSIMILE SYSTEM(NAMFAX)");
		  USE_CODE.put("XN","NWS NATIONAL FACSIMILE NETWORK (NAFAX)");
		  USE_CODE.put("XP","NWS RRS (RADAR REMOTE SYSTEM)");
		  USE_CODE.put("XQ","GOES); TELEPHOTO FACSIMILE SYSTEM (GOESFAX)");
		  USE_CODE.put("XR","NWS RADAR REPORTING & WARNING COORDINATION SYSTEM (RAWARC)");
		  USE_CODE.put("XS","NWS HURRICANE AND SEVERE STORM WARNING SYSTEM");
		  USE_CODE.put("XU","NWS SEISMIC AND TSUNAMI TELECOMMUNICATIONS SYSTEM");
		  USE_CODE.put("XW","NOAA WEATHER WIRE SERVICE (NWWS)");
		  USE_CODE.put("XY","RADAR DATA PROCESSOR SYSTEM (RADAP)");
		  USE_CODE.put("XZ","MISCELLANEOUS WEATHER COMMUNICATIONS SYSTEM");
		  USE_CODE.put("YA","FLEET SHIP-SHORE ACCESS");
		  USE_CODE.put("YB","ALASKA COMMAND AND CONTROL");
		  USE_CODE.put("YC","CINCLANT CINCUSJFCOM COMMAND AND CONTROL NETWORK");
		  USE_CODE.put("YD","CINCSOUTH COMMAND AND CONTROL NETWORK");
		  USE_CODE.put("YE","SPACE DEFENSE SYSTEM");
		  USE_CODE.put("YF","DEW LINE/BMEWS SUPPORT NETWORK");
		  USE_CODE.put("YI","NORAD/ADC OVER-THE-HORIZON NETWORK");
		  USE_CODE.put("YK","SAE DSN SWITCHED NETWORK");
		  USE_CODE.put("YM","SEA-LAUNCH BALLISTIC MISSILE (SLBM) DETECTION AND WARNING SYS");
		  USE_CODE.put("YN","NORAD/ADC GROUND COMMUNICATIONS NETWORK");
		  USE_CODE.put("YP","NORAD SURVEILLANCE AND TACTICAL NETWORK");
		  USE_CODE.put("YQ","NORAD ADC POINT-TO-POINT");
		  USE_CODE.put("YS","OCEAN SYSTEMS NETWORK");
		  USE_CODE.put("YW","BCC DATA CONFERENCE CIRCUIT");
		  USE_CODE.put("YX","BCC VOICE CONFERENCE CIRCUIT");
		  USE_CODE.put("YY","AN/USC-28 CONTROL CIRCUIT");
		  USE_CODE.put("YZ","NAVY SPACE SURVEILLANCE NETWORK");
		  USE_CODE.put("ZA","SATELLITE CONTROL/REPORTING COMMUNICATIONS");
		  USE_CODE.put("ZB","TACTICAL COMMAND AND CONTROL");
		  USE_CODE.put("ZD","SEARCH AND RESCUE");
		  USE_CODE.put("ZE","ENTERPRISE FLIGHT ASSISTANCE SERVICE");
		  USE_CODE.put("ZF","WATS FLIGHT ASSISTANCE SERVICE");
		  USE_CODE.put("ZG","MANAGEMENT/COORDINATION NETWORK");
		  USE_CODE.put("ZH","MULTIPLE SUBSCRIBER EQUIPMENT (MSE)");
		  USE_CODE.put("ZK","GROUND FORCES AIR SUPPORT NETWORK");
		  USE_CODE.put("ZM","MILITARY AIR TRAFFIC CONTROL AND FLIGHT FACILITIES NETWORK");
		  USE_CODE.put("ZN","INTELLIGENCE COLLECTION/DISSEMINATION NETWORK");
		  USE_CODE.put("ZP","CANADIAN CIRCUITS");
		  USE_CODE.put("ZQ","LOGISTICS NETWORK");
		  USE_CODE.put("ZR","FLIGHT FOLLOWING/AIR OPS SERVICE NETWORK");
		  USE_CODE.put("ZS","AIR TRAFFIC CONTROL/FLIGHT FACILITIES");
		  USE_CODE.put("ZW","BALLISTIC MISSILE EARLY WARNING SYSTEM");
		 
		 
		 TreeMap<String,String> SVC_TYPE = new TreeMap<String, String>();
		 SVC_TYPE.put("A","Teletype service other than GIG Switched Networks");
		 SVC_TYPE.put("B","DSN Access Line");
		 SVC_TYPE.put("C","DSN Interswitch Trunk");
		 SVC_TYPE.put("D","Data other than GIG Switched Networks");
		 SVC_TYPE.put("E","Defense Message System Transition Hub (DTH) Access Line");
		 SVC_TYPE.put("F","Defense Message System Transition Hub Interswitch Trunk");
		 SVC_TYPE.put("G","Permanent Virtual Circuits");
		 SVC_TYPE.put("H","SONET/SDH Package System. (No Channel Accounting by DISA)");
		 SVC_TYPE.put("I","Non-Red Switch secure voice circuit that does not access DSN (For non-Red Switch secure voice circuits that access DSN); type service code B); DSN Access Line); will be used)");
		 SVC_TYPE.put("J","Facsimile or Telephoto other than GIG Switched Networks");
		 SVC_TYPE.put("K","Continuous Wave");
		 SVC_TYPE.put("L","DSSCS DTH Access Line");
		 SVC_TYPE.put("M","Package System. No channel accounting by DISA");
		 SVC_TYPE.put("N","DSN Access Line servicing an Red Switch subscriber or switch");
		 SVC_TYPE.put("O","Switched Virtual Circuits");
		 SVC_TYPE.put("P","Interswitch trunk/circuit for switched networks other than DISN networks");
		 SVC_TYPE.put("Q","DTH Interchange Circuits); circuits between DTH and other switched networks); except DSN");
		 SVC_TYPE.put("R","Alternate Voice/Record other than GIG Switched Networks");
		 SVC_TYPE.put("S","Video");
		 SVC_TYPE.put("T","Telemetry other than GIG Switched Networks");
		 SVC_TYPE.put("U","SONET");
		 SVC_TYPE.put("V","Voice other than GIG Switched Networks");
		 SVC_TYPE.put("W","Defense Switched Network - Europe");
		 SVC_TYPE.put("X","Package System. Channel accounting by DISA");
		 SVC_TYPE.put("Y","Control); Management); Signaling); D.C. or data); other than GIG Switched Networks");
		 SVC_TYPE.put("Z","Non-DSN Intersite Trunk Circuit");
		 SVC_TYPE.put("1","NIPRNET Access Line");
		 SVC_TYPE.put("2","DISN Inter-Nodal Circuit (INC)");
		 SVC_TYPE.put("3","FTS Access Line");
		 SVC_TYPE.put("4","FTS Interswitch Trunk");
		 SVC_TYPE.put("5","Broadband Inter-Nodal Circuit");
		 SVC_TYPE.put("6","Indirect NIPRNET/SIPRNET Access through a Concentrator (Note, This code will only be used to identify long-haul dedicated circuits which access a concentrator and will not be Used for Camp/Post/Station/Base circuits. Also); this code will not be used to identify NIPRNET/SIPRNET resources)");
		 SVC_TYPE.put("7","Indirect DTH Access through an intermediate relay (automatic or manual)");
		 SVC_TYPE.put("8","NIPRNET/SIPRNET Backbone");
		 SVC_TYPE.put("9","SIPRNET Access Line");
		 SVC_TYPE.put("0","Package System. Channel/Fabric/Slot/Port. Accounting by DISA");


		 String fullCCSD = "";
		 if (tso.get("Full CCSD").length() > 6 || (tso.get("Full CCSD").indexOf("/") > -1 && tso.get("Full CCSD").length() == 17)) {fullCCSD = tso.get("Full CCSD");}
		 else {fullCCSD = tso.get("Alternate CCSD");}
		 tso.put("Requesting Department", DEPT_CODES.get(fullCCSD.substring(0, 1)));
		 tso.put("Circuit Use", USE_CODE.get(fullCCSD.substring(1, 3)));
		 tso.put("Type of Service", SVC_TYPE.get(fullCCSD.substring(3, 4)));

		 for(Map.Entry<String, String> entry : tso.entrySet()){
			 String key = entry.getKey().trim();
			 String value = entry.getValue().trim();
//			 System.out.println("(" + key + ")" + " : " + value);
			 
			 if(key.equals("Standardized CCSD")){
				 Collection.fullCcsd = value;
				 String first = value.trim().substring(0, 4);
				 String second = value.trim().substring(4, 8);
				 Collection.chfRootFolder = second + " (" + first + ")";
			 }else if(key.equals("Full CCSD") && value.length() == 6){
				 Collection.trunkId = value;
			 }else if(key.equals("Full CCSD") && value.length() != 6){
				 Collection.trunkId = "N/A";
			 }else if(key.equals("TSP Number")){
				 Collection.tsp = value;
			 }else if(key.equals("TSP")){
				 if(key.equals("NA")){
					 Collection.fullTsp = "Not Assigned";
				 }else{
					 Collection.fullTsp = value;
				 }
			 }else if(key.equals("To")){
				 String tempGEO = "'" +value.substring(0, value.indexOf(' ')).trim()+ "'";
				 String tempState = "'" +value.substring(value.indexOf('/')-2, value.indexOf('/')).trim()+ "'";
				 String temprs = LoadDB.testGEOLOC(tempGEO, tempState);
				 String[] name = temprs.split(":");
				 Collection.toLocation = name[1] + ": " + name[0];
				 Collection.toCode = value;
			 }else if(key.equals("From")){
				 String tempGEO = "'" +value.substring(0, value.indexOf(' ')).trim()+ "'";
				 String tempState = "'" +value.substring(value.indexOf('/')-2, value.indexOf('/')).trim()+ "'";
				 String temprs = LoadDB.testGEOLOC(tempGEO, tempState);
				 String[] name = temprs.split(":");
				 Collection.fromLocation = name[1] + ": " + name[0];
				 Collection.fromCode = value;
			 }else if(key.equals("Requesting Department")){
				 Collection.requestingDept = value;
			 }else if(key.equals("Type of Service")){
				 Collection.serviceType = value;
			 }else if(key.equals("Circuit Use")){
				 Collection.circuitUse = value;
			 }else if(key.equals("Security")){
				 Collection.security = value;
			 }else if(key.equals("Data Rate")){
				 Collection.dataRate = value;
			 }else if(key.equals("Traffic Flow")){
				 Collection.trafficFlow = value;
			 }else if(key.equals("Term")){
				 Collection.serviceAvail = value;
			 }else if(key.equals("We Are CCO")){
				 Collection.andrewsCmo = Boolean.valueOf(value);
			 }else if(key.equals("CCO or CMO")){
				 Collection.cmo = value;
			 }else if(key.equals("Signaling")){
				 Collection.signaling = value;
			 }else if(key.equals("Quality Control Code")){
				 Collection.qcc = value;
			 }else if(key.equals("We Are Endpoint")){
				 Collection.endPoint = Boolean.valueOf(value);
				 Collection.isPassthrough = !Collection.endPoint;
			 }else if(key.equals("TSO Number")){
				 Collection.tsoNum = value;
			 }else if(key.equals("TSO Suffix")){
				 Collection.tsoSuffix = value;
			 }else if(key.equals("TSO Action")){
				 Collection.tsoAction = value;
			 }else if(key.equals("Expected In Effect Date")){
				 Collection.svcDate = value;
			 }else if(key.equals("Completion Report Required")){
				 Collection.crr = Boolean.valueOf(value);
			 }else if(key.equals("Purpose")){
				 Collection.purpose = value;
			 }else if(key.equals("Subject")){
				 Collection.tsoSubject = value;
			 }else if(key.equals("TSR Number")){
				 Collection.tsrNum = value;
			 }else if(key.equals("Report Date")){
				 Collection.reportDate = value;
			 }else if(key.equals("CMO Comm")){
				 Collection.cmoComm = value;
			 }else if(key.equals("CMO DSN")){
				 Collection.cmoDsn = value;
			 }
		 }
		 LogHelper.info("TSO (Parser) Complete");
	}
}

class LoadDB extends Database{
	
	public static String testGEOLOC(String GEOLOC, String StateCode){
		
		String re = "";
		String sql = "SELECT * "+
						"FROM "+Strings.GEOLOC_TABLE+
						" WHERE GEOLOC = "+GEOLOC+" AND StateCountryCode = "+StateCode;
		ArrayList<String> al = new ArrayList<String>();
		al = sqlQuery(sql);
		for(int i=0;i<al.size();i++){
			String temp = al.get(i);
			if(i == 5){
				re += temp+":";
			}else if(i == 7){
				re += temp;
			}
		}
		return re;
	}
}
