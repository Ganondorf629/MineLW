package net.sociuris.minelw.block;

import net.sociuris.minelw.util.math.Vector3d;

public enum Facing {

	DOWN(new Vector3d(0, -1, 0)),
    UP(new Vector3d(0, 1, 0)),
    NORTH(new Vector3d(0, 0, -1)),
    SOUTH(new Vector3d(0, 0, 1)),
    WEST(new Vector3d(-1, 0, 0)),
    EAST(new Vector3d(1, 0, 0));
	
	private final Vector3d targetBlockCoords;
	private Facing(Vector3d targetBlockCoords) {
		this.targetBlockCoords = targetBlockCoords;
	}
	
	public Vector3d getTargetBlockCoords() {
		return targetBlockCoords;
	}

}