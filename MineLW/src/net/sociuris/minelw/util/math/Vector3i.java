package net.sociuris.minelw.util.math;

public class Vector3i {

	public static final Vector3i ZERO = new Vector3i(0, 0, 0);

	private int x;
	private int y;
	private int z;

	public Vector3i() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3i add(Vector3i vec) {
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
		return this;
	}

	public Vector3i subtract(Vector3i vec) {
		this.x -= vec.x;
		this.y -= vec.y;
		this.z -= vec.z;
		return this;
	}

	public Vector3i multiply(Vector3i vec) {
		this.x *= vec.x;
		this.y *= vec.y;
		this.z *= vec.z;
		return this;
	}

	public Vector3i divide(Vector3i vec) {
		this.x /= vec.x;
		this.y /= vec.y;
		this.z /= vec.z;
		return this;
	}

	public Vector3i copy(Vector3i vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
		return this;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int getBlockZ() {
		return (int) Math.floor(z);
	}

	public Vector3i setX(int x) {
		this.x = x;
		return this;
	}

	public Vector3i setY(int y) {
		this.y = y;
		return this;
	}

	public Vector3i setZ(int z) {
		this.z = z;
		return this;
	}

}
