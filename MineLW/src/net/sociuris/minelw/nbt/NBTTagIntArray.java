package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagIntArray extends NBTElement {

	private int[] data;

	public NBTTagIntArray() {
	}

	public NBTTagIntArray(int[] dataArray) {
		this.data = dataArray;
	}

	@Override
	public void readData(DataInput in) throws IOException {
		this.data = new int[in.readInt()];
		for (int i = 0; i < data.length; i++)
			this.data[i] = in.readInt();
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		out.writeInt(data.length);
		for (int data : data)
			out.writeInt(data);
	}

	@Override
	public int[] getData() {
		return data;
	}

	@Override
	public byte getID() {
		return 11;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("[");
		for (int i : data) {
			stringBuilder.append(data[i]);
			if (i != data.length - 1)
				stringBuilder.append(',');
		}
		return stringBuilder.append(']').toString();
	}

}
