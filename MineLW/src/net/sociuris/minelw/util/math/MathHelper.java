package net.sociuris.minelw.util.math;

public final class MathHelper {

	private MathHelper() {}
	
	public static int clamp(int i, int min, int max) {
		return i < min ? min : (i > max ? max : i);
	}
	public static double clamp(double d, double min, double max) {
		return d < min ? min : (d > max ? max : d);
	}
}
