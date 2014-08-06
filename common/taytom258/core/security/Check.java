package taytom258.core.security;

import taytom258.config.Config;
import taytom258.core.util.LogHelper;
import taytom258.lib.Collection;

public class Check {

	public static void call(){
		if(Collection.develop){
			LogHelper.debug("Integrity Checking Skipped: Development Mode On");
		}else{
			if(FingerprintCheck.selfIntegrityChecking()){
				LogHelper.debug("Jar Verified");
			}else{
				LogHelper.warning("Program has been modified. Please contact support");
			}
		}
	}
}
