package taytom258.windows.core;

import taytom258.lib.Collection;
import taytom258.windows.TSO;

public class TSOCore extends TSO {

	public static void save() {
		Collection.tsoText = getText().getText().trim();
	}
}
