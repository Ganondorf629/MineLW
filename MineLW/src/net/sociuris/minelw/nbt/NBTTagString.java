package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.sociuris.util.StringUtils;

public class NBTTagString extends NBTElement {

	private String data;

	public NBTTagString() {
		this.data = "";
	}

	public NBTTagString(String data) {
		if (data != null)
			this.data = data;
		else
			throw new NullPointerException("String is null");
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		out.writeUTF(data);
	}

	@Override
	public void readData(DataInput in) throws IOException {
		this.data = in.readUTF();
	}

	@Override
	public String getData() {
		return data;
	}

	@Override
	public byte getID() {
		return 8;
	}

	@Override
	public String toString() {
		return "\"" + StringUtils.escape(data, '"') + "\"";
	}

}