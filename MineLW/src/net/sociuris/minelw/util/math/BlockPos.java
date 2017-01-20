package net.sociuris.minelw.util.math;

public class BlockPos extends Vector3i {
	
	public static final BlockPos ORIGIN = new BlockPos(0, 0, 0);

	public BlockPos(int x, int y, int z) {
		super(x, y, z);
	}

	public BlockPos(Location source) {
		this((int)source.getX(), (int)source.getY(), (int)source.getZ());
	}

	public BlockPos(Vector3i source) {
		this(source.getX(), source.getY(), source.getZ());
	}

	public BlockPos add(int x, int y, int z) {
		return x == 0 && y == 0 && z == 0 ? this : new BlockPos(this.getX() + x, this.getY() + y, this.getZ() + z);
	}

	@Override
	public BlockPos add(Vector3i vec3i) {
		return vec3i.getX() == 0 && vec3i.getY() == 0 && vec3i.getZ() == 0 ? this
				: new BlockPos(this.getX() + vec3i.getX(), this.getY() + vec3i.getY(), this.getZ() + vec3i.getZ());
	}

	public BlockPos subtract(int x, int y, int z) {
		return x == 0 && y == 0 && z == 0 ? this : new BlockPos(this.getX() - x, this.getY() - y, this.getZ() - z);
	}

	@Override
	public BlockPos subtract(Vector3i vec3i) {
		return vec3i.getX() == 0 && vec3i.getY() == 0 && vec3i.getZ() == 0 ? this
				: new BlockPos(this.getX() - vec3i.getX(), this.getY() - vec3i.getY(), this.getZ() - vec3i.getZ());
	}

	public long toLong() {
		return ((long) this.getX() & 67108863) << 38 | ((long) this.getY() & 4095) << 26
				| ((long) this.getZ() & 67108863) << 0;
	}

	public static BlockPos fromLong(long serialized) {
		return new BlockPos((int) (serialized << 0 >> 38), (int) (serialized << 26 >> 52),
				(int) (serialized << 38 >> 38));
	}

	@Override
	public String toString() {
		return "BlockPos(x=" + this.getX() + ",y=" + this.getY() + ",z=" + this.getZ() + ")";
	}
}
