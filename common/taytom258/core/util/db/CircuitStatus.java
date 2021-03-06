package taytom258.core.util.db;

import java.util.ArrayList;

public class CircuitStatus extends Database {

	private static ArrayList<String> al = new ArrayList<String>();
	
	public static void circuitStatusRepair(){
		init(false);
		String circuitStatus = "";
		
		String query = "SELECT FullCCSD, Ignore, TSONumber FROM TSO";
		ArrayList<String> al = dbQuery(query);
		if(al.size()>0){
			for(int i=1;i<al.size();i+=3){
				System.out.println(al.get(i-1)+" : "+al.get(i));
				if(Integer.parseInt(al.get(i)) == 0){
					String[] sa = al.get(i+1).split("-");
					if(sa[1].equals("01")){
						circuitStatus = "Inactive";
					}else if(sa[1].equals("01Z")){
						circuitStatus = "Cancelled";
					}else if(sa[1].equals("99")){
						circuitStatus = "Pending Disco";
					}else if(sa[1].equals("99Z")){
						circuitStatus = "Pending Change";
					}else{
						circuitStatus = "Pending Change";
					}
					String update = "UPDATE Circuits SET CircuitStatus = '"+circuitStatus+"' WHERE FullCCSD = '"+al.get(i-1)+"'";
					dbUpdate(update);
//					dbUpdate("Circuits", "CircuitStatus", circuitStatus, al.get(i-1), "FullCCSD");
				}
			}
		}
		init(true);
	}
}
