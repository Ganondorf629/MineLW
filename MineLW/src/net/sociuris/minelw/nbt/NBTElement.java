package net.sociuris.minelw.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class NBTElement {

	public abstract void readData(DataInput in) throws IOException;

	public abstract void writeData(DataOutput out) throws IOException;

	public boolean isByte() {
		return (this instanceof NBTTagByte);
	}

	public NBTTagByte getAsByte() {
		if (isByte())
			return (NBTTagByte) this;
		else
			throw new IllegalStateException(this.toString() + " is not a NBTTagByte");
	}

	public boolean isShort() {
		return (this instanceof NBTTagShort);
	}

	public NBTTagShort getAsShort() {
		if (isShort())
			return (NBTTagShort) this;
		else
			throw new IllegalStateException(this.toString() + " is not a NBTTagShort");
	}

	public boolean isInt() {
		return (this instanceof NBTTagInt);
	}

	public NBTTagInt getAsInt() {
		if (isInt())
			return (NBTTagInt) this;
		else
			throw new IllegalStateException(this.toString() + " is not a NBTTagInt");
	}

	public boolean isLong() {
		return (this instanceof NBTTagLong);
	}

	public NBTTagLong getAsLong() {
		if (isLong())
			return (NBTTagLong) this;
		else
			throw new IllegalStateException(this.toString() + " is not a NBTTagLong");
	}

	public boolean isFloat() {
		return (this instanceof NBTTagFloat);
	}

	public NBTTagFloat getAsFloat() {
		if (isFloat())
			return (NBTTagFloat) this;
		else
			throw new IllegalStateException(this.toString() + " is not a NBTTagFloat");
	}

	public boolean isDouble() {
		return (this instanceof NBTTagDouble);
	}

	public NBTTagDouble getAsDouble() {
		if (isDouble())
			return (NBTTagDouble) this;
		else
			throw new IllegalStateException(this.toString() + " is not a NBTTagDouble");
	}

	public boolean isByteArray() {
		return (this instanceof NBTTagByteArray);
	}

	public NBTTagByteArray getAsByteArray() {
		if (isByteArray())
			return (NBTTagByteArray) this;
		else
			throw new IllegalStateException(this.toString() + " is not a NBTTagByteArray");
	}

	public boolean isString() {
		return (this instanceof NBTTagString);
	}

	public NBTTagString getAsString() {
		if (isString())
			return (NBTTagString) this;
		else
			throw new IllegalStateException(this.toString() + " is not a NBTTagString");
	}

	public boolean isList() {
		return (this instanceof NBTTagList);
	}

	public NBTTagList getAsList() {
		if (isList())
			return (NBTTagList) this;
		else
			throw new IllegalStateException(this.toString() + " is not a NBTTagList");
	}

	public boolean isCompound() {
		return (this instanceof NBTTagCompound);
	}

	public NBTTagCompound getAsCompound() {
		if (isCompound())
			return (NBTTagCompound) this;
		else
			throw new IllegalStateException(this.toString() + " is not a NBTTagCompound");
	}

	public boolean isIntArray() {
		return (this instanceof NBTTagIntArray);
	}

	public NBTTagIntArray getAsIntArray() {
		if (isIntArray())
			return (NBTTagIntArray) this;
		else
			throw new IllegalStateException(this.toString() + " is not a NBTTagIntArray");
	}

	public boolean isBoolean() {
		return isByte();
	}
	
	public Boolean getAsBoolean() {
		if(isBoolean())
			return getAsByte().getData() != 0;
		else
			throw new IllegalStateException(this.toString() + " is not a NBTTagByte");
	}

	public abstract Object getData();

	public abstract byte getID();

	@Override
	public abstract String toString();

}