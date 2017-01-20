package net.sociuris.minelw.util.math;

public class Rotation {

	public static final Rotation ZERO = new Rotation(0F, 0F);

	private float yaw;
	private float pitch;

	public Rotation() {
	}

	public Rotation(float yaw, float pitch) {
		this.yaw = yaw;
		this.pitch = pitch;
	}

	/**
	 * Retourne la valeur horizontale de la vue
	 * @return Yaw
	 */
	public float getYaw() {
		return yaw;
	}

	/**
	 * Retourne la valeur verticale de la vue
	 * @return Pitch
	 */
	public float getPitch() {
		return pitch;
	}

	/**
	 * Définit la valeur vertical de la vue
	 * @param yaw
	 */
	public void setYaw(float yaw) {
		this.yaw = yaw % 360.0F;
	}

	/**
	 * Définit la valeur horizontale de la vue
	 * @param pitch
	 */
	public void setPitch(float pitch) {
		this.pitch = pitch % 360.0F;
	}

	@Override
	public String toString() {
		return "Rotation(yaw=" + yaw + ",pitch=" + pitch + ")";
	}
}
