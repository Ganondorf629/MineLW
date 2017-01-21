package net.sociuris.minelw.network.packet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import net.sociuris.minelw.util.math.Location;
import net.sociuris.minelw.util.math.Rotation;

public class PacketOutputStream extends DataOutputStream {

	public PacketOutputStream(OutputStream outputStream) {
		super(outputStream);
	}

	public void writeUnsignedByte(int b) throws IOException {
		if (b >= 0 && b <= 255)
			this.write(b);
	}

	public void writeUnsignedShort(int s) throws IOException {
		if (s >= 0 && s <= 65535)
			this.writeShort(s);
	}

	public void writeString(String s) throws IOException {
		byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
		this.writeVarInt(bytes.length);
		this.write(bytes);
	}

	public void writeVarInt(int i) throws IOException {
		do {
			byte temp = (byte) (i & 0b01111111);
			i >>>= 7;
			if (i != 0) {
				temp |= 0b10000000;
			}
			this.writeByte(temp);
		} while (i != 0);
	}

	public void writeVarLong(long l) throws IOException {
		do {
			byte temp = (byte) (l & 0b01111111);
			l >>>= 7;
			if (l != 0) {
				temp |= 0b10000000;
			}
			this.writeByte(temp);
		} while (l != 0);
	}

	public void writeByteArray(byte[] a) throws IOException {
		this.writeVarInt(a.length);
		for (byte b : a)
			this.write(b);
	}

	public void writeUUID(UUID u) throws IOException {
		this.writeLong(u.getMostSignificantBits());
		this.writeLong(u.getLeastSignificantBits());
	}

	public void writeEnumValue(Enum<?> v) throws IOException {
		this.writeVarInt(v.ordinal());
	}

	public void writeLocation(Location l) throws IOException {
		this.writeDouble(l.getX());
		this.writeDouble(l.getY());
		this.writeDouble(l.getZ());
	}

	public void writeRotation(Rotation r) throws IOException {
		this.writeByte((int) r.getPitch());
		this.writeByte((int) r.getYaw());
	}
}
