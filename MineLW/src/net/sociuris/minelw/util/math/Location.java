package net.sociuris.minelw.util.math;

public class Location {

	public static final Location ZERO = new Location(0.0D,0.0D,0.0D);
	
	private double posX;
	private double posY;
	private double posZ;

	public Location() {
	}

	public Location(double x, double y, double z) {
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

	/**
	 * Retourne la valeur X
	 * 
	 * @return X
	 */
	public double getX() {
		return posX;
	}

	/**
	 * Retourne la valeur Y
	 * 
	 * @return Y
	 */
	public double getY() {
		return posY;
	}

	/**
	 * Retourne la valeur Z
	 * 
	 * @return Z
	 */
	public double getZ() {
		return posZ;
	}

	/**
	 * Retourne l'emplacement après l'addition des valeurs X, Y et Z
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return Nouvelle position
	 */
	public Location add(double x, double y, double z) {
		return new Location(this.posX + x, this.posY + y, this.posZ + z);
	}

	@Override
	public String toString() {
		return "Location(x=" + posX + ",y=" + posY + ",z=" + posZ + ")";
	}
}
