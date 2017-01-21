package net.sociuris.minelw.player;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import net.sociuris.logger.Logger;
import net.sociuris.minelw.server.MinecraftServer;

public class PlayerManager {

	private final Logger logger = Logger.getLogger();
	private final MinecraftServer minecraftServer;

	private final Map<UUID, Player> players = new HashMap<UUID, Player>();

	public PlayerManager(MinecraftServer server) {
		this.minecraftServer = server;
	}
	
	protected void updatePlayer(Player player) {
		if(!player.getConnection().isConnected())
			players.remove(player);
	}

	public Player getPlayer(UUID uuid) {
		return players.get(uuid);
	}

	public Set<Entry<UUID, Player>> getPlayers() {
		return players.entrySet();
	}

	public MinecraftServer getServer() {
		return minecraftServer;
	}

}