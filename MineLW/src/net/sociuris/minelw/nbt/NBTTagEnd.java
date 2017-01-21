package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagEnd extends NBTElement {

	@Override
	public void readData(DataInput in) throws IOException {
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
	}

	@Override
	public String getData() {
		return this.toString();
	}

	@Override
	public byte getID() {
		return 0;
	}

	@Override
	public String toString() {
		return "END";
	}

}
