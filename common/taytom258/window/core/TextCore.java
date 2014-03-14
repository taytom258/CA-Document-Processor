package taytom258.window.core;

import taytom258.lib.Collection;
import taytom258.window.Text;

public class TextCore extends Text{

	public static void save(){
		Collection.tsoText = getText().getText().trim();
	}
}
