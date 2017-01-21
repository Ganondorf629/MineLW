package net.sociuris.minelw.nbt;

public enum NBTType {

	TAG_END(NBTTagEnd.class),
	TAG_BYTE(NBTTagByte.class),
	TAG_SHORT(NBTTagShort.class),
	TAG_INT(NBTTagInt.class),
	TAG_LONG(NBTTagLong.class),
	TAG_FLOAT(NBTTagFloat.class),
	TAG_DOUBLE(NBTTagDouble.class),
	TAG_BYTE_ARRAY(NBTTagByteArray.class),
	TAG_STRING(NBTTagString.class),
	TAG_LIST(NBTTagList.class),
	TAG_COMPOUND(NBTTagCompound.class),
	TAG_INT_ARRAY(NBTTagIntArray.class);

	private final Class<? extends NBTElement> nbtClass;
	
	private NBTType(Class<? extends NBTElement> nbtClass) {
		this.nbtClass = nbtClass;
	}
	
	public int getID() {
		return this.ordinal();
	}
	
	public NBTElement create() {
		try {
			return nbtClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}
	
	public static NBTType getFromNBTElement(NBTElement nbtElement) {
		Class<? extends NBTElement> elementClass = nbtElement.getClass();
		for (NBTType nbtType : NBTType.values()) {
			if (nbtType.nbtClass == elementClass) {
				return nbtType;
			}
		}
		return null;
	}

}