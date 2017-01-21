package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagByteArray extends NBTBase {

	private byte[] data;

	public NBTTagByteArray() {
	}

	public NBTTagByteArray(byte[] data) {
		this.data = data;
	}

	@Override
	public void readData(DataInput in) throws IOException {
		this.data = new byte[in.readInt()];
		in.readFully(data);
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		out.writeInt(data.length);
		out.write(data);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder('[');
		for (int i = 0; i < data.length; i++) {
			builder.append(String.valueOf(data[i]));
			if (i + 1 < data.length)
				builder.append(',');
		}
		return builder.append(']').toString();
	}

	@Override
	public byte getID() {
		return 7;
	}

	public byte[] getByteArray() {
		return this.data;
	}

}
