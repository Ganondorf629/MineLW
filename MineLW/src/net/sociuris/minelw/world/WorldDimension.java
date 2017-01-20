package net.sociuris.minelw.world;

public enum WorldDimension {

	NETHER(-1), OVERWORLD(0), THE_END(1);

	private final int id;

	private WorldDimension(int id) {
		this.id = id;
	}

	public int getDimensionId() {
		return id;
	}

}