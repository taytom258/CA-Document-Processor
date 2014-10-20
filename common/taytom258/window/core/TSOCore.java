package taytom258.window.core;

import taytom258.lib.Collection;
import taytom258.window.TSO;

public class TSOCore extends TSO {

	public static void save() {
		Collection.tsoText = getText().getText().trim();
	}
}
