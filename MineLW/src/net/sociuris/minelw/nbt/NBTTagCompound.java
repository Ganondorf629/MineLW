package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import net.sociuris.logger.Logger;

public class NBTTagCompound extends NBTBase {

	private final Logger logger = Logger.getLogger();
	private final Map<String, NBTBase> tagMap = new HashMap<String, NBTBase>();

	@Override
	public void readData(DataInput in) throws IOException {
		this.tagMap.clear();
		byte b;

		while ((b = in.readByte()) != 0) {
			String str = in.readUTF();
			NBTBase nbtBase = NBTUtils.getNbtFromID(b);
			nbtBase.readData(in);
			if (this.tagMap.put(str, nbtBase) != null) {

			}
		}
	}

	@Override
	public void writeData(DataOutput out) throws IOException {
		for (Entry<String, NBTBase> mapEntry : this.tagMap.entrySet()) {
			NBTBase nbtBase = mapEntry.getValue();
			out.writeByte(nbtBase.getID());

			if (nbtBase.getID() != 0) {
				out.writeUTF(mapEntry.getKey());
				nbtBase.writeData(out);
			}
		}

		out.writeByte(0);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("{");
		int i = 0;
		for (Entry<String, NBTBase> entry : tagMap.entrySet()) {
			stringBuilder.append(entry.getKey()).append(';').append(entry.getValue().toString());
			if (i != tagMap.size() - 1)
				stringBuilder.append(',');
			i++;
		}
		return stringBuilder.append('}').toString();
	}

	@Override
	public byte getID() {
		return 10;
	}

	public void setTag(String key, NBTBase value) {
		this.tagMap.put(key, value);
	}

	public void setByte(String key, byte value) {
		this.tagMap.put(key, new NBTTagByte(value));
	}

	public void setShort(String key, short value) {
		this.tagMap.put(key, new NBTTagShort(value));
	}

	public void setInteger(String key, int value) {
		this.tagMap.put(key, new NBTTagInt(value));
	}

	public void setLong(String key, long value) {
		this.tagMap.put(key, new NBTTagLong(value));
	}

	public void setUniqueId(String key, UUID value) {
		this.setLong(key + "Most", value.getMostSignificantBits());
		this.setLong(key + "Least", value.getLeastSignificantBits());
	}

	public UUID getUniqueId(String key) {
		return new UUID(this.getLong(key + "Most"), this.getLong(key + "Least"));
	}

	public boolean hasUniqueId(String key) {
		return this.hasKey(key + "Most", 99) && this.hasKey(key + "Least", 99);
	}

	public void setFloat(String key, float value) {
		this.tagMap.put(key, new NBTTagFloat(value));
	}

	public void setDouble(String key, double value) {
		this.tagMap.put(key, new NBTTagDouble(value));
	}

	public void setString(String key, String value) {
		this.tagMap.put(key, new NBTTagString(value));
	}

	public void setByteArray(String key, byte[] value) {
		this.tagMap.put(key, new NBTTagByteArray(value));
	}

	public void setIntArray(String key, int[] value) {
		this.tagMap.put(key, new NBTTagIntArray(value));
	}

	public void setBoolean(String key, boolean value) {
		this.setByte(key, (byte) (value ? 1 : 0));
	}

	public NBTBase getTag(String key) {
		return this.tagMap.get(key);
	}

	public byte getTagId(String key) {
		NBTBase nbtbase = this.tagMap.get(key);
		return nbtbase == null ? 0 : nbtbase.getID();
	}

	public boolean hasKey(String key) {
		return this.tagMap.containsKey(key);
	}

	public boolean hasKey(String key, int type) {
		int i = this.getTagId(key);
		return i == type ? true : (type != 99 ? false : i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6);
	}

	public byte getByte(String key) {
		try {
			if (this.hasKey(key, 99)) {
				return ((NBTPrimitive) this.tagMap.get(key)).getByte();
			}
		} catch (ClassCastException e) {
			;
		}

		return (byte) 0;
	}

	public short getShort(String key) {
		try {
			if (this.hasKey(key, 99)) {
				return ((NBTPrimitive) this.tagMap.get(key)).getShort();
			}
		} catch (ClassCastException e) {
			;
		}

		return (short) 0;
	}

	public int getInteger(String key) {
		try {
			if (this.hasKey(key, 99)) {
				return ((NBTPrimitive) this.tagMap.get(key)).getInt();
			}
		} catch (ClassCastException e) {
			;
		}

		return 0;
	}

	public long getLong(String key) {
		try {
			if (this.hasKey(key, 99)) {
				return ((NBTPrimitive) this.tagMap.get(key)).getLong();
			}
		} catch (ClassCastException e) {
			;
		}

		return 0L;
	}

	public float getFloat(String key) {
		try {
			if (this.hasKey(key, 99)) {
				return ((NBTPrimitive) this.tagMap.get(key)).getFloat();
			}
		} catch (ClassCastException e) {
			;
		}

		return 0.0F;
	}

	public double getDouble(String key) {
		try {
			if (this.hasKey(key, 99)) {
				return ((NBTPrimitive) this.tagMap.get(key)).getDouble();
			}
		} catch (ClassCastException e) {
			;
		}

		return 0.0D;
	}

	public String getString(String key) {
		try {
			if (this.hasKey(key, 8)) {
				return this.tagMap.get(key).toString();
			}
		} catch (ClassCastException e) {
			;
		}

		return "";
	}

	public byte[] getByteArray(String key) {
		try {
			if (this.hasKey(key, 7)) {
				return ((NBTTagByteArray) this.tagMap.get(key)).getByteArray();
			}
		} catch (ClassCastException e) {
			logger.printStackTrace(e);
			System.exit(1);
		}

		return new byte[0];
	}

	public int[] getIntArray(String key) {
		try {
			if (this.hasKey(key, 11)) {
				return ((NBTTagIntArray) this.tagMap.get(key)).getIntArray();
			}
		} catch (ClassCastException e) {
			logger.printStackTrace(e);
			System.exit(1);
		}

		return new int[0];
	}

	public NBTTagCompound getCompoundTag(String key) {
		try {
			if (this.hasKey(key, 10)) {
				return (NBTTagCompound) this.tagMap.get(key);
			}
		} catch (ClassCastException e) {
			logger.printStackTrace(e);
			System.exit(1);
		}

		return new NBTTagCompound();
	}

	/**
	 * Gets the NBTTagList object with the given name.
	 */
	public NBTTagList getTagList(String key, int type) {
		try {
			if (this.getTagId(key) == 9) {
				NBTTagList nbtTagList = (NBTTagList) this.tagMap.get(key);

				if (!nbtTagList.hasNoTags() && nbtTagList.getTagType() != type) {
					return new NBTTagList();
				}

				return nbtTagList;
			}
		} catch (ClassCastException e) {
			logger.printStackTrace(e);
			System.exit(1);
		}

		return new NBTTagList();
	}

	/**
	 * Retrieves a boolean value using the specified key, or false if no such
	 * key was stored. This uses the getByte method.
	 */
	public boolean getBoolean(String key) {
		return this.getByte(key) != 0;
	}

}
