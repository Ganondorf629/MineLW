package net.sociuris.minelw.player;

import net.sociuris.minelw.server.MinecraftServer;

public class Player {

	private final MinecraftServer minecraftServer;

	private final PlayerConnection connection;

	protected Player(MinecraftServer server, PlayerConnection connection) {
		this.minecraftServer = server;
		this.connection = connection;
	}

	public PlayerConnection getConnection() {
		return connection;
	}

	public MinecraftServer getServer() {
		return minecraftServer;
	}

}