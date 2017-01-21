package net.sociuris.minelw.player;

public enum GameMode {

	SURVIVAL, CREATIVE, ADVENTURE, SPECTATOR;
	
	public static GameMode getGameMode(int id) {
		GameMode[] gameModes = GameMode.values();
		if(id < gameModes.length)
			return gameModes[id];
		else
			return null;
	}

}