package net.sociuris.minelw.network.handshake;

import java.io.IOException;

import net.sociuris.minelw.network.NetworkHandler;

public interface NetworkHandshakeServerHandler extends NetworkHandler {

	void handleHandshake(PacketClientHandshake packet) throws IOException;

}