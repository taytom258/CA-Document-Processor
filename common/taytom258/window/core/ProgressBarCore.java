package taytom258.window.core;

import javax.swing.JProgressBar;

public class ProgressBarCore {

	public static int value = 0;
	public static int pbMax = 1000;
	public static int pbMin = 0;
	
	
	private static int calc(int current, int max){
		float pbval = (current/max)*pbMax;
		int actualValue = Math.round(pbval);
		return actualValue;
	}
	
	public static void setProgress(int interval){
		value = interval;
	}
	
	public static JProgressBar setBar(int value, int current, int max){
		JProgressBar bar = new JProgressBar();
		bar.setBounds(10, 438, 611, 14);
		bar.setMaximum(ProgressBarCore.pbMax);
		bar.setMinimum(ProgressBarCore.pbMin);
		bar.setValue(calc(current, max));
		return bar;
		
	}
}
