package net.sociuris.minelw.network.packet;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.sociuris.logger.Logger;
import net.sociuris.minelw.network.ConnectionState;
import net.sociuris.minelw.network.NetworkHandler;
import net.sociuris.minelw.network.handshake.PacketClientHandshake;

public class PacketManager {

	private final Logger logger = Logger.getLogger();

	private final Map<Integer, Class<? extends Packet<? extends NetworkHandler>>> handshakeServerBoundPackets = new HashMap<Integer, Class<? extends Packet<? extends NetworkHandler>>>();
	private final Map<Integer, Class<? extends Packet<? extends NetworkHandler>>> handshakeClientBoundPackets = new HashMap<Integer, Class<? extends Packet<? extends NetworkHandler>>>();
	private final Map<Integer, Class<? extends Packet<? extends NetworkHandler>>> statusServerBoundPackets = new HashMap<Integer, Class<? extends Packet<? extends NetworkHandler>>>();
	private final Map<Integer, Class<? extends Packet<? extends NetworkHandler>>> statusClientBoundPackets = new HashMap<Integer, Class<? extends Packet<? extends NetworkHandler>>>();
	private final Map<Integer, Class<? extends Packet<? extends NetworkHandler>>> loginServerBoundPackets = new HashMap<Integer, Class<? extends Packet<? extends NetworkHandler>>>();
	private final Map<Integer, Class<? extends Packet<? extends NetworkHandler>>> loginClientBoundPackets = new HashMap<Integer, Class<? extends Packet<? extends NetworkHandler>>>();
	private final Map<Integer, Class<? extends Packet<? extends NetworkHandler>>> inGameServerBoundPackets = new HashMap<Integer, Class<? extends Packet<? extends NetworkHandler>>>();
	private final Map<Integer, Class<? extends Packet<? extends NetworkHandler>>> inGameClientBoundPackets = new HashMap<Integer, Class<? extends Packet<? extends NetworkHandler>>>();

	public PacketManager() {
		this.registerPacket(ConnectionState.HANDSHAKING, 0x00, PacketDirection.SERVERBOUND,
				PacketClientHandshake.class);
	}

	private Map<Integer, Class<? extends Packet<? extends NetworkHandler>>> getMapFromConnectionState(
			ConnectionState connectionState, PacketDirection packetDirection) {
		if (PacketDirection.SERVERBOUND == packetDirection) {
			switch (connectionState) {
			case HANDSHAKING:
				return handshakeServerBoundPackets;
			case STATUS:
				return statusServerBoundPackets;
			case LOGIN:
				return loginServerBoundPackets;
			case IN_GAME:
				return inGameServerBoundPackets;
			}
		} else if (PacketDirection.CLIENTBOUND == packetDirection) {
			switch (connectionState) {
			case HANDSHAKING:
				return handshakeClientBoundPackets;
			case STATUS:
				return statusClientBoundPackets;
			case LOGIN:
				return loginClientBoundPackets;
			case IN_GAME:
				return inGameClientBoundPackets;
			}
		}
		return null;
	}

	public void registerPacket(ConnectionState connectionState, int id, PacketDirection direction,
			Class<? extends Packet<? extends NetworkHandler>> packet) {
		Map<Integer, Class<? extends Packet<? extends NetworkHandler>>> packetMap = getMapFromConnectionState(
				connectionState, direction);
		if (!packetMap.containsKey(id) && !packetMap.containsValue(packet)) {
			logger.debug("Register packet %s[id=%d,connectionState=%s,direction=%s]", packet.getSimpleName(), id,
					connectionState.toString(), direction.toString());
			packetMap.put(id, packet);
		} else
			logger.error("%s[id=%d,connectionState=%s,direction=%s] already registered!", packet.getSimpleName(), id,
					connectionState.toString(), direction.toString());
	}

	public Packet<? extends NetworkHandler> getPacket(ConnectionState connectionState, PacketDirection direction,
			int id) {
		logger.debug("Look for packet[id=%d,connectionState=%s,direction=%s]", id, connectionState.toString(),
				direction.toString());
		try {
			Class<? extends Packet<? extends NetworkHandler>> packetClass = getMapFromConnectionState(connectionState,
					direction).get(id);
			if (packetClass != null)
				return packetClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			logger.printStackTrace(e);
		}
		return null;
	}

	public int getPacketID(ConnectionState connectionState, PacketDirection direction,
			Packet<? extends NetworkHandler> packet) {
		logger.debug("Look for packet[id=?,connectionState=%s,direction=%s,name=%s] id", connectionState.toString(),
				direction.toString(), packet.getClass().getSimpleName());
		Map<Integer, Class<? extends Packet<? extends NetworkHandler>>> packetsMap = getMapFromConnectionState(
				connectionState, direction);
		for (Entry<Integer, Class<? extends Packet<? extends NetworkHandler>>> packetData : packetsMap.entrySet()) {
			if (packetData.getValue().equals(packet.getClass()))
				return packetData.getKey();
		}
		return -1;
	}

}