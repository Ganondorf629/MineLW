package net.sociuris.minelw.network;

public enum ConnectionState {

	HANDSHAKING(-1), IN_GAME(0), STATUS(1), LOGIN(2);

	private final int id;

	private ConnectionState(int id) {
		this.id = id;
	}

	public int getID() {
		return this.id;
	}

	public static ConnectionState getConnectionState(int id) {
		for (ConnectionState state : ConnectionState.values())
			if (state.getID() == id)
				return state;
		return null;
	}

}