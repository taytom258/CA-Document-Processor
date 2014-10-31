package taytom258.windows;

import taytom258.core.util.parsers.collections.TSOCollection;

public class TSOCore extends TSO {

	public static void save() {
		TSOCollection.tsoText = getText().getText().trim();
	}
}
