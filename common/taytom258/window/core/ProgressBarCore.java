package taytom258.window.core;

import javax.swing.JProgressBar;

import taytom258.window.Window;

public class ProgressBarCore extends Window {

	private static int current = 0;
	private static JProgressBar bar = null;

	private static void setBar(int value) {
		bar.setValue(value);
	}

	public static void init(int max, int min) {
		bar.setMaximum(max);
		bar.setMinimum(min);
		setBar(current);
	}

	public static int progress() {
		current++;
		setBar(current);
		return current;
	}

	public static int progress(int number) {
		current += number;
		setBar(current);
		return current;
	}

	public static void clear() {
		current = 0;
		setBar(current);
	}

}
