package net.sociuris.minelw.util.math;

public class ChunkPos {

	private final int posX;
	private final int posZ;

	public ChunkPos(int x, int z) {
		this.posX = x;
		this.posZ = z;
	}

	public ChunkPos(BlockPos pos) {
		this.posX = pos.getX() >> 4;
		this.posZ = pos.getZ() >> 4;
	}

	public int getCenterX() {
		return (this.posX << 4) + 8;
	}

	public int getCenterZ() {
		return (this.posZ << 4) + 8;
	}
	
	public BlockPos getCenterBlock(int y)
    {
        return new BlockPos(this.getCenterX(), y, this.getCenterZ());
    }

	public int getX() {
		return this.posX << 4;
	}

	public int getZ() {
		return this.posZ << 4;
	}

	public int getXEnd() {
		return (this.posX << 4) + 15;
	}

	public int getZEnd() {
		return (this.posZ << 4) + 15;
	}

	public BlockPos getBlock(int x, int y, int z) {
		return new BlockPos((this.posX << 4) + x, y, (this.posZ << 4) + z);
	}

	@Override
    public String toString()
    {
        return "Chunk(posX=" + this.posX + ",posZ=" + this.posZ + ")";
    }
}
