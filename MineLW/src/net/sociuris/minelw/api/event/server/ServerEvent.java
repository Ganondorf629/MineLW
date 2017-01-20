package net.sociuris.minelw.api.event.server;

import net.sociuris.minelw.api.event.Event;
import net.sociuris.minelw.server.MinecraftServer;

public class ServerEvent extends Event {

	private final MinecraftServer minecraftServer;
	
	public ServerEvent(MinecraftServer server) {
		this.minecraftServer = server;
	}
	
	public MinecraftServer getServer() {
		return minecraftServer;
	}
	
}