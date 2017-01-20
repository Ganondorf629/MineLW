package net.sociuris.minelw.util.math;

public class Vector3d {

	public static final Vector3d ZERO = new Vector3d(0.0D,0.0D,0.0D);
	
	private double x;
	private double y;
	private double z;

	public Vector3d() {
		this.x = 0.0D;
		this.y = 0.0D;
		this.z = 0.0D;
	}

	public Vector3d(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3d(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3d add(Vector3d vec) {
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
		return this;
	}

	public Vector3d subtract(Vector3d vec) {
		this.x -= vec.x;
		this.y -= vec.y;
		this.z -= vec.z;
		return this;
	}

	public Vector3d multiply(Vector3d vec) {
		this.x *= vec.x;
		this.y *= vec.y;
		this.z *= vec.z;
		return this;
	}

	public Vector3d divide(Vector3d vec) {
		this.x /= vec.x;
		this.y /= vec.y;
		this.z /= vec.z;
		return this;
	}

	public Vector3d copy(Vector3d vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
		return this;
	}

	public double getX() {
		return x;
	}

	public int getBlockX() {
		return (int) Math.floor(x);
	}

	public double getY() {
		return y;
	}

	public int getBlockY() {
		return (int) Math.floor(y);
	}

	public double getZ() {
		return z;
	}

	public int getBlockZ() {
		return (int) Math.floor(z);
	}

	public Vector3d setX(int x) {
		this.x = x;
		return this;
	}

	public Vector3d setX(double x) {
		this.x = x;
		return this;
	}

	public Vector3d setX(float x) {
		this.x = x;
		return this;
	}

	public Vector3d setY(int y) {
		this.y = y;
		return this;
	}

	public Vector3d setY(double y) {
		this.y = y;
		return this;
	}

	public Vector3d setY(float y) {
		this.y = y;
		return this;
	}

	public Vector3d setZ(int z) {
		this.z = z;
		return this;
	}

	public Vector3d setZ(double z) {
		this.z = z;
		return this;
	}

	public Vector3d setZ(float z) {
		this.z = z;
		return this;
	}

	public Vector3d addY(int y) {
		this.y += y;
		return this;
	}

	public Vector3d addY(double y) {
		this.y += y;
		return this;
	}

	public Vector3d addY(float y) {
		this.y += y;
		return this;
	}

}
