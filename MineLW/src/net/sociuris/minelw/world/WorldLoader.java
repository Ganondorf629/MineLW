package net.sociuris.minelw.world;

public class WorldLoader {

	private final World world;
	
	protected WorldLoader(World world) {
		this.world = world;
	}
	
	public World getWorld() {
		return world;
	}
	
}