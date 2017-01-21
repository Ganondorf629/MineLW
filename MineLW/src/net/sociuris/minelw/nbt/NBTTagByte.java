package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagByte extends NBTBase {

	private byte data;

	public NBTTagByte() {
	}

	public NBTTagByte(byte data) {
		this.data = data;
	}

	@Override
	public void readData(DataInput in) throws IOException {
		this.data = in.readByte();
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		out.writeByte(data);
	}

	@Override
	public String toString() {
		return String.valueOf(this.data) + "b";
	}

	@Override
	public byte getID() {
		return 1;
	}

}
