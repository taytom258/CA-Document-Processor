package taytom258.core.security;

import taytom258.config.Config;
import taytom258.core.util.LogHelper;

public class Check {

	public static void call(){
		if(Config.debug){
			LogHelper.debug("Integrity Checking Skipped: Debug Mode On");
		}else{
			if(FingerprintCheck.selfIntegrityChecking()){
				LogHelper.debug("Jar Verified");
			}else{
				LogHelper.warning("Program has been modified. Please contact support");
			}
		}
	}
}
