package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagFloat extends NBTPrimitive {

	private float data;

	public NBTTagFloat() {
	}

	public NBTTagFloat(float data) {
		this.data = data;
	}

	@Override
	public void readData(DataInput in) throws IOException {
		this.data = in.readFloat();
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		out.writeFloat(data);
	}

	@Override
	public Float getData() {
		return data;
	}

	@Override
	public byte getID() {
		return 5;
	}

	@Override
	public String toString() {
		return String.valueOf(data) + "f";
	}

}
