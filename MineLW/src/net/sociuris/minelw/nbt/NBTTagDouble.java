package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagDouble extends NBTPrimitive {

	private double data;

	public NBTTagDouble() {
	}

	public NBTTagDouble(double data) {
		this.data = data;
	}

	@Override
	public void readData(DataInput in) throws IOException {
		this.data = in.readDouble();
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		out.writeDouble(data);
	}

	@Override
	public Double getData() {
		return data;
	}

	@Override
	public byte getID() {
		return 6;
	}

	@Override
	public String toString() {
		return String.valueOf(data) + "d";
	}

}
