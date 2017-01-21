package net.sociuris.minelw.network.packet;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import net.sociuris.minelw.util.math.Location;
import net.sociuris.minelw.util.math.Rotation;

public class PacketInputStream extends DataInputStream {

	public PacketInputStream(InputStream inputStream) {
		super(inputStream);
	}

	public String readString() throws IOException {
		int length = readVarInt();
		byte[] bytes = new byte[length];
		this.read(bytes);
		return new String(bytes, StandardCharsets.UTF_8);
	}

	public int readVarInt() throws IOException {
		int numRead = 0;
		int result = 0;
		byte read;
		do {
			read = this.readByte();
			int value = (read & 0b01111111);
			result |= (value << (7 * numRead));

			numRead++;
			if (numRead > 5) {
				throw new RuntimeException("VarInt is too big");
			}
		} while ((read & 0b10000000) != 0);

		return result;
	}

	public long readVarLong() throws IOException {
		int numRead = 0;
		long result = 0;
		byte read;
		do {
			read = this.readByte();
			int value = (read & 0b01111111);
			result |= (value << (7 * numRead));

			numRead++;
			if (numRead > 10) {
				throw new RuntimeException("VarLong is too big");
			}
		} while ((read & 0b10000000) != 0);
		return result;
	}

	public byte[] readByteArray() throws IOException {
		int size = this.readVarInt();
		byte[] array = new byte[size];
		for (int i = 0; i < size; i++)
			array[i] = this.readByte();
		return array;
	}

	public UUID readUUID() throws IOException {
		return new UUID(this.readLong(), this.readLong());
	}

	public <T extends Enum<T>> T readEnumValue(Class<T> enumClass) throws IOException {
		return enumClass.getEnumConstants()[this.readVarInt()];
	}

	public Location readLocation() throws IOException {
		return new Location(this.readDouble(), this.readDouble(), this.readDouble());
	}

	public Rotation readRotation() throws IOException {
		return new Rotation(this.readByte(), this.readByte());
	}

}