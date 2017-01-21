package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sociuris.logger.Logger;

public class NBTTagList extends NBTBase {

	private final Logger logger = Logger.getLogger();

	private List<NBTBase> tagList = new ArrayList<NBTBase>();
	private byte tagType = 0;

	public NBTTagList() {
	}

	public NBTTagList(List<NBTBase> tagList) {
		this.tagList = tagList;
	}

	@Override
	public void readData(DataInput in) throws IOException {
		this.tagType = in.readByte();
		int i = in.readInt();

		if (this.tagType == 0 && i > 0)
			throw new RuntimeException("Missing type on ListTag");
		else {
			this.tagList = new ArrayList<NBTBase>();

			for (int j = 0; j < i; ++j) {
				NBTBase nbtBase = NBTUtils.getNbtFromID(this.tagType);
				nbtBase.readData(in);
				this.tagList.add(nbtBase);
			}
		}
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		if (tagList.isEmpty())
			this.tagType = 0;
		else
			this.tagType = this.tagList.get(0).getID();

		out.writeByte(tagType);
		out.writeInt(tagList.size());

		for (NBTBase nbt : tagList)
			nbt.writeData(out);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("[");

		for (int i = 0; i < tagList.size(); i++) {
			stringBuilder.append(tagList.get(i).toString());
			if (i != tagList.size() - 1)
				stringBuilder.append(',');
		}
		return stringBuilder.append(']').toString();
	}

	@Override
	public byte getID() {
		return 9;
	}

	public NBTBase get(int i) {
		return i >= 0 && i < this.tagList.size() ? (NBTBase) this.tagList.get(i) : new NBTTagEnd();
	}

	public void set(int i, NBTBase nbtBase) {
		if (nbtBase.getID() == 0)
			logger.warn("Invalid TagEnd added to ListTag");
		else if (i >= 0 && i < this.tagList.size()) {
			if (this.tagType == 0)
				this.tagType = nbtBase.getID();
			else if (this.tagType != nbtBase.getID()) {
				logger.warn("Adding mismatching tag types to tag list");
				return;
			}

			this.tagList.set(i, nbtBase);
		} else
			logger.warn("index out of bounds to set tag in tag list");
	}

	public NBTBase removeTag(int i) {
		return tagList.remove(i);
	}

	public boolean hasNoTags() {
		return tagList.isEmpty();
	}

	public int getTagType() {
		return tagType;
	}

}
