package taytom258.core.security;

import taytom258.core.log.LogHelper;
import taytom258.core.util.parsers.collections.TSOCollection;
import taytom258.lib.Reference;

public class Check {

	public static void call() {
		if (Reference.develop) {
			LogHelper.debug("Integrity Checking Skipped: Development Mode On");
		} else {
			if (FingerprintCheck.selfIntegrityChecking()) {
				LogHelper.debug("Jar Verified");
			} else {
				LogHelper.warning("Program has been modified. Please contact support");
			}
		}
	}
}
