package net.sociuris.minelw.api;

import net.sociuris.minelw.server.MinecraftServer;

public abstract class Plugin {

	private MinecraftServer minecraftServer;

	public Plugin() {
	}

	protected void init(MinecraftServer minecraftServer) {

	}

	public abstract void onEnable();

	public abstract void onDisable();

	public MinecraftServer getServer() {
		return minecraftServer;
	}

}