package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class NBTTagCompound extends NBTElement {

	private final Map<String, NBTElement> members = new HashMap<String, NBTElement>();

	@Override
	public void readData(DataInput in) throws IOException {
		this.members.clear();
		byte b;
		while ((b = in.readByte()) != 0) {
			String str = in.readUTF();
			NBTElement NBTElement = NBTUtils.getNBTFromID(b);
			NBTElement.readData(in);
			if (this.members.put(str, NBTElement) != null) {

			}
		}
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		for (Entry<String, NBTElement> mapEntry : this.members.entrySet()) {
			NBTElement NBTElement = mapEntry.getValue();
			out.writeByte(NBTElement.getID());

			if (NBTElement.getID() != 0) {
				out.writeUTF(mapEntry.getKey());
				NBTElement.writeData(out);
			}
		}
		out.writeByte(0);
	}

	public boolean hasTag(String key) {
		return members.containsKey(key);
	}

	public void setTag(String key, NBTElement NBTElement) {
		this.members.put(key, NBTElement);
	}

	public NBTElement getTag(String key) {
		return members.get(key);
	}

	public boolean hasPrimitiveTag(String key) {
		if (hasTag(key)) {
			return (getTag(key) instanceof NBTPrimitive);
		} else
			return false;
	}

	// byte
	public boolean hasByte(String key) {
		return (hasTag(key) && getTag(key).isByte());
	}

	public void setByte(String key, byte value) {
		setTag(key, new NBTTagByte(value));
	}

	public byte getByte(String key) {
		return getTag(key).getAsByte().getData();
	}

	// short
	public boolean hasShort(String key) {
		return (hasTag(key) && getTag(key).isShort());
	}

	public void setShort(String key, short value) {
		setTag(key, new NBTTagShort(value));
	}

	public short getShort(String key) {
		return getTag(key).getAsShort().getData();
	}

	// int
	public boolean hasInt(String key) {
		return (hasTag(key) && getTag(key).isInt());
	}

	public void setInt(String key, int value) {
		setTag(key, new NBTTagInt(value));
	}

	public int getInt(String key) {
		return getTag(key).getAsInt().getData();
	}

	// long
	public boolean hasLong(String key) {
		return (hasTag(key) && getTag(key).isLong());
	}

	public void setLong(String key, long value) {
		setTag(key, new NBTTagLong(value));
	}

	public long getLong(String key) {
		return getTag(key).getAsLong().getData();
	}

	// float
	public boolean hasFloat(String key) {
		return (hasTag(key) && getTag(key).isFloat());
	}

	public void setFloat(String key, float value) {
		setTag(key, new NBTTagFloat(value));
	}

	public float getFloat(String key) {
		return getTag(key).getAsFloat().getData();
	}

	// double
	public boolean hasDouble(String key) {
		return (hasTag(key) && getTag(key).isDouble());
	}

	public void setDouble(String key, double value) {
		setTag(key, new NBTTagDouble(value));
	}

	public double getDouble(String key) {
		return getTag(key).getAsDouble().getData();
	}

	// byte array
	public boolean hasByteArray(String key) {
		return (hasTag(key) && getTag(key).isByteArray());
	}

	public void setByteArray(String key, byte[] value) {
		setTag(key, new NBTTagByteArray(value));
	}

	public NBTTagByteArray getByteArray(String key) {
		return getTag(key).getAsByteArray();
	}

	// string
	public boolean hasString(String key) {
		return (hasTag(key) && getTag(key).isString());
	}

	public void setString(String key, String value) {
		setTag(key, new NBTTagString(value));
	}

	public String getString(String key) {
		return getTag(key).getAsString().getData();
	}

	// list
	public boolean hasList(String key) {
		return (hasTag(key) && getTag(key).isList());
	}

	public void setList(String key, List<NBTElement> nbtElements) {
		setTag(key, new NBTTagList(nbtElements));
	}

	public NBTTagList getList(String key) {
		return getTag(key).getAsList();
	}

	// compound
	public boolean hasCompound(String key) {
		return (hasTag(key) && getTag(key).isCompound());
	}

	public void setCompound(String key, NBTTagCompound value) {
		setTag(key, value);
	}

	public NBTTagCompound getCompound(String key) {
		return getTag(key).getAsCompound();
	}

	// int array
	public boolean hasIntArray(String key) {
		return (hasTag(key) && getTag(key).isIntArray());
	}

	public void setIntArray(String key, int[] value) {
		setTag(key, new NBTTagIntArray(value));
	}

	public NBTTagIntArray getIntArray(String key) {
		return getTag(key).getAsIntArray();
	}

	// boolean
	public boolean hasBoolean(String key) {
		return hasByte(key);
	}

	public void setBoolean(String key, boolean value) {
		setByte(key, ((byte) (value ? 1 : 0)));
	}

	public boolean getBoolean(String key) {
		return (getByte(key) == 1);
	}

	public boolean hasUniqueId(String key) {
		return (hasLong(key + "Most") && hasLong(key + "Least"));
	}

	public void setUniqueId(String key, UUID value) {
		setLong(key + "Most", value.getMostSignificantBits());
		setLong(key + "Least", value.getLeastSignificantBits());
	}

	public UUID getUniqueId(String key) {
		return new UUID(getLong(key + "Most"), getLong(key + "Least"));
	}

	@Override
	public Map<String, NBTElement> getData() {
		return members;
	}

	@Override
	public byte getID() {
		return 10;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("{");
		int i = 0;
		for (Entry<String, NBTElement> entry : members.entrySet()) {
			stringBuilder.append(entry.getKey()).append(';').append(entry.getValue().toString());
			if (i != members.size() - 1)
				stringBuilder.append(',');
			i++;
		}
		return stringBuilder.append('}').toString();
	}

}
