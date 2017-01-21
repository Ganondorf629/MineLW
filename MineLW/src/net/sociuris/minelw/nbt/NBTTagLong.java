package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagLong extends NBTPrimitive {

	private long data;

	public NBTTagLong() {
	}

	public NBTTagLong(long data) {
		this.data = data;
	}

	@Override
	public void readData(DataInput in) throws IOException {
		this.data = in.readLong();
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		out.writeLong(data);
	}

	@Override
	public Long getData() {
		return data;
	}

	@Override
	public byte getID() {
		return 4;
	}

	@Override
	public String toString() {
		return String.valueOf(data) + "l";
	}

}
