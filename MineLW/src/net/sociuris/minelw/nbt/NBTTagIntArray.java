package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagIntArray extends NBTBase {

	private int[] dataArray;

	public NBTTagIntArray() {
	}

	public NBTTagIntArray(int[] dataArray) {
		this.dataArray = dataArray;
	}

	@Override
	public void readData(DataInput in) throws IOException {
		this.dataArray = new int[in.readInt()];
		for (int i = 0; i < dataArray.length; i++)
			this.dataArray[i] = in.readInt();
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		out.writeInt(dataArray.length);
		for (int data : dataArray)
			out.writeInt(data);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("[");
		for (int i : dataArray) {
			stringBuilder.append(dataArray[i]);
			if (i != dataArray.length - 1)
				stringBuilder.append(',');
		}
		return stringBuilder.append(']').toString();
	}

	@Override
	public byte getID() {
		return 11;
	}

	public int[] getIntArray() {
		return dataArray;
	}

}
