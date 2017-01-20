package net.sociuris.minelw.api.event.server;

import net.sociuris.minelw.server.MinecraftServer;

public class ServerTickEvent extends ServerEvent {

	private int currentTick;
	
	public ServerTickEvent(MinecraftServer server, int currentTick) {
		super(server);
	}
	
	public int getCurrentTick() {
		return currentTick;
	}

}