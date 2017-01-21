package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagEnd extends NBTBase {

	@Override
	public void readData(DataInput in) throws IOException {
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
	}

	@Override
	public String toString() {
		return "END";
	}

	@Override
	public byte getID() {
		return 0;
	}

}
