package taytom258.window.core;

import taytom258.lib.Collection;
import taytom258.window.TSO_Text;

public class TSO_TextCore extends TSO_Text{

	public static void save(){
		Collection.tsoText = getText().getText().trim();
	}
}
