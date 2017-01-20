package net.sociuris.minelw.api.event.world;

import net.sociuris.minelw.api.event.Event;
import net.sociuris.minelw.world.World;

public class WorldEvent extends Event {

	private final World world;

	public WorldEvent(World world) {
		this.world = world;
	}

	public World getWorld() {
		return world;
	}

}