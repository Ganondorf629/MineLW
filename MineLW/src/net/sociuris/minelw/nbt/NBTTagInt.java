package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagInt extends NBTPrimitive {

	private int data;

	public NBTTagInt() {
	}

	public NBTTagInt(int data) {
		this.data = data;
	}

	@Override
	public void readData(DataInput in) throws IOException {
		this.data = in.readInt();
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		out.writeInt(data);
	}

	@Override
	public Integer getData() {
		return data;
	}

	@Override
	public byte getID() {
		return 3;
	}

	@Override
	public String toString() {
		return String.valueOf(data);
	}

}
