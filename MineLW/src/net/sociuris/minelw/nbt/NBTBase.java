package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class NBTBase {

	public abstract void readData(DataInput in) throws IOException;

	public abstract void writeData(DataOutput out) throws IOException;

	@Override
	public abstract String toString();

	public abstract byte getID();

}