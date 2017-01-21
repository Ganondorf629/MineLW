package net.sociuris.minelw.player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import net.sociuris.logger.Logger;
import net.sociuris.minelw.network.ConnectionState;
import net.sociuris.minelw.network.NetworkHandler;
import net.sociuris.minelw.network.NetworkManager;
import net.sociuris.minelw.network.packet.Packet;
import net.sociuris.minelw.network.packet.PacketDirection;
import net.sociuris.minelw.network.packet.PacketInputStream;
import net.sociuris.minelw.network.packet.PacketOutputStream;

public class PlayerConnection extends Socket {

	private final Logger logger = Logger.getLogger();
	private final NetworkManager networkManager;
	private final PacketInputStream packetInputStream;
	private final PacketOutputStream packetOutputStream;

	private Player player;
	private ConnectionState state;
	private NetworkHandler handler;
	private boolean connected = true;
	private boolean autoRead = false;

	public PlayerConnection(NetworkManager networkManager) throws IOException {
		this.networkManager = networkManager;
		this.packetInputStream = new PacketInputStream(super.getInputStream());
		this.packetOutputStream = new PacketOutputStream(super.getOutputStream());
	}

	public boolean sendPacket(Packet<? extends NetworkHandler> packet) throws IOException {
		if (isConnected() && !super.isClosed()) {
			int id = networkManager.getPacketManager().getPacketID(state, PacketDirection.CLIENTBOUND, packet);
			if (id > -1) {
				ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
				PacketOutputStream buffer = new PacketOutputStream(byteArray);
				buffer.writeVarInt(id);
				packet.writePacketData(buffer);

				int size = byteArray.size();
				packetOutputStream.writeVarInt(size);
				packetOutputStream.write(byteArray.toByteArray(), 0, size);

				logger.debug("Sending packet %s to %s (id=%d,length=%d)", packet.getClass().getSimpleName(),
						this.toString(), id, byteArray.size());

				byteArray.reset();
				byteArray.flush();
				buffer.flush();
				buffer.close();
				packetOutputStream.flush();
				return true;
			} else
				logger.error(
						"Try sending a unregistered packet[name=%s] to player connection[connectionState=%s,packetDirection=%s]",
						packet.getClass().getSimpleName(), state.toString(), PacketDirection.CLIENTBOUND.name());
		} else
			logger.error("Try to send packet[name=%s] to a disconnected user", packet.getClass().getSimpleName());
		return false;
	}

	public Packet<? extends NetworkHandler> readPacket() throws IOException {
		if (isConnected() && !super.isClosed()) {
			final int length = packetInputStream.readVarInt();
			if (length > -1) {
				byte[] byteArray = new byte[length];
				packetInputStream.read(byteArray);
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
				PacketInputStream inputStream = new PacketInputStream(byteArrayInputStream);
				try {
					final int id = inputStream.readVarInt();
					if (id > -1) {
						Packet<? extends NetworkHandler> packet = networkManager.getPacketManager().getPacket(state,
								PacketDirection.SERVERBOUND, id);
						if (packet != null) {
							logger.debug("Reading packet \"%s\" data (id=%d,length=%d)",
									packet.getClass().getSimpleName(), id, length);
							packet.readPacketData(inputStream);
							return packet;
						} else {
							logger.error("Unable to read packet because it is not registered (id: %d)", id);
						}
					} else
						logger.error("Unable to read packet: Invalid id!");

				} finally {
					byteArrayInputStream.close();
					inputStream.close();
				}
			} else
				logger.error("Unable to read packet: Invalid length!");

		} else
			logger.error("Try to read packet from a disconnected user");
		return null;
	}

	public void setAutoRead(boolean enable) {
		if (enable != autoRead) {
			this.autoRead = enable;
			if (enable) {
				logger.debug("Enable auto read");
				new Thread("Auto Read " + this.toString()) {
					@SuppressWarnings("unchecked")
					@Override
					public void run() {
						while (autoRead && connected) {
							try {
								logger.debug("Wait for next packet...");
								((Packet<NetworkHandler>) readPacket()).processPacket(handler);
							} catch (IOException e) {
								if (logger.isDebug())
									logger.printStackTrace(e);
								if (isConnected())
									closeConnection("An error occurred while reading packet : " + e.getClass().getName()
											+ " (" + e.getLocalizedMessage() + ")");
							}
						}
					}
				}.start();
			} else
				logger.debug("Disable auto read");
		} else
			logger.error("Auto read already %s!", (enable) ? "enabled" : "disabled");
	}

	public void closeConnection(String reason) {
		try {
			autoRead = false;
			connected = false;
			logger.debug("%s closed: %s", this.toString(), reason);
			packetOutputStream.flush();
			packetOutputStream.close();
			packetInputStream.close();
			super.close();
		} catch (IOException e) {
			logger.printStackTrace(e);
		} finally {
			networkManager.updateConnection(this);
			if (player != null)
				networkManager.getMinecraftServer().getPlayerManager().updatePlayer(player);
		}
	}

	@Override
	public boolean isConnected() {
		return connected;
	}

	@Override
	public PacketInputStream getInputStream() {
		return packetInputStream;
	}

	@Override
	public PacketOutputStream getOutputStream() {
		return packetOutputStream;
	}

	public void setConnectionState(ConnectionState state) {
		logger.debug("Set connection state of %s to %s", this.toString(), state.name());
		this.state = state;
	}

	public ConnectionState getConnectionState() {
		return state;
	}

	public void setHandler(NetworkHandler handler) {
		logger.debug("Set handler of %s to %s", this.toString(), handler.toString());
		this.handler = handler;
	}

	public NetworkHandler getHandler() {
		return handler;
	}

	@Override
	public String toString() {
		return "PlayerConnection[ip=" + super.getInetAddress().toString() + ",port=" + String.valueOf(super.getPort())
				+ "]";
	}

}