package net.sociuris.minelw.network.packet;

import java.io.IOException;

import net.sociuris.minelw.network.NetworkHandler;

public interface Packet<T extends NetworkHandler> {

	public void readPacketData(PacketInputStream in) throws IOException;

	public void writePacketData(PacketOutputStream out) throws IOException;

	public void processPacket(T handler) throws IOException;
	
}