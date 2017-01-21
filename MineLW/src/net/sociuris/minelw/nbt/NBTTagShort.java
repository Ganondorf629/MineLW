package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagShort extends NBTBase {

	private short data;

	public NBTTagShort() {
	}

	public NBTTagShort(short data) {
		this.data = data;
	}

	@Override
	public void readData(DataInput in) throws IOException {
		this.data = in.readShort();
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		out.writeShort(data);
	}

	@Override
	public String toString() {
		return String.valueOf(this.data) + "s";
	}

	@Override
	public byte getID() {
		return 2;
	}

}
