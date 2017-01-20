package net.sociuris.minelw.network.handshake;

import java.io.IOException;

import net.sociuris.minelw.network.packet.Packet;
import net.sociuris.minelw.network.packet.PacketInputStream;
import net.sociuris.minelw.network.packet.PacketOutputStream;

public class PacketClientHandshake implements Packet<NetworkHandshakeServerHandler> {

	@Override
	public void readPacketData(PacketInputStream in) throws IOException {

	}

	@Override
	public void writePacketData(PacketOutputStream out) throws IOException {

	}

	@Override
	public void processPacket(NetworkHandshakeServerHandler handler) throws IOException {
		handler.handleHandshake(this);
	}

}
