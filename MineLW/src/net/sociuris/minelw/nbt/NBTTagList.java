package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sociuris.logger.Logger;
import net.sociuris.util.ArrayUtils;

public class NBTTagList extends NBTElement {

	private final Logger logger = Logger.getLogger();

	private final List<NBTElement> members;
	private byte tagType = 0;

	public NBTTagList() {
		this.members = new ArrayList<NBTElement>();
	}

	public NBTTagList(NBTElement[] nbtElements) {
		this.members = ArrayUtils.arrayToList(nbtElements);
	}

	public NBTTagList(List<NBTElement> nbtElements) {
		this.members = nbtElements;
	}

	@Override
	public void readData(DataInput in) throws IOException {
		this.tagType = in.readByte();
		int i = in.readInt();
		if (this.tagType == 0 && i > 0)
			throw new RuntimeException("Missing type on ListTag");
		else {
			this.members.clear();
			for (int j = 0; j < i; ++j) {
				NBTElement nbtBase = NBTUtils.getNBTFromID(this.tagType);
				nbtBase.readData(in);
				this.members.add(nbtBase);
			}
		}
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		if (members.isEmpty())
			this.tagType = 0;
		else
			this.tagType = this.members.get(0).getID();

		out.writeByte(tagType);
		out.writeInt(members.size());

		for (NBTElement nbt : members)
			nbt.writeData(out);
	}

	public NBTElement get(int i) {
		return (i >= 0 && i < this.members.size()) ? this.members.get(i) : new NBTTagEnd();
	}

	public void set(int i, NBTElement nbtBase) {
		if (nbtBase.getID() == 0)
			logger.warn("Invalid TagEnd added to ListTag");
		else if (i >= 0 && i < this.members.size()) {
			if (this.tagType == 0)
				this.tagType = nbtBase.getID();
			else if (this.tagType != nbtBase.getID()) {
				logger.warn("Adding mismatching tag types to tag list");
				return;
			}

			this.members.set(i, nbtBase);
		} else
			logger.warn("index out of bounds to set tag in tag list");
	}

	public NBTElement removeTag(int i) {
		return members.remove(i);
	}

	public boolean hasTags() {
		return !members.isEmpty();
	}

	public int getTagType() {
		return tagType;
	}

	@Override
	public List<NBTElement> getData() {
		return members;
	}

	@Override
	public byte getID() {
		return 9;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("[");
		for (int i = 0; i < members.size(); i++) {
			stringBuilder.append(members.get(i).toString());
			if (i != members.size() - 1)
				stringBuilder.append(',');
		}
		return stringBuilder.append(']').toString();
	}

}
