package net.sociuris.minelw.nbt;

import java.io.DataInputStream;
import java.io.IOException;

import net.sociuris.minelw.crash.CrashReport;
import net.sociuris.minelw.nbt.exception.NBTFormatException;

public class NBTUtils {

	public static NBTBase getNbtFromID(byte id) {
		switch (id) {
		case 0:
			return new NBTTagEnd();

		case 1:
			return new NBTTagByte();

		case 2:
			return new NBTTagShort();

		case 3:
			return new NBTTagInt();

		case 4:
			return new NBTTagLong();

		case 5:
			return new NBTTagFloat();

		case 6:
			return new NBTTagDouble();

		case 7:
			return new NBTTagByteArray();

		case 8:
			return new NBTTagString();

		case 9:
			return new NBTTagList();

		case 10:
			return new NBTTagCompound();

		case 11:
			return new NBTTagIntArray();

		default:
			return null;
		}
	}

	public static NBTTagCompound readNBTCompound(DataInputStream in) throws NBTFormatException, IOException {
		NBTBase nbtBase = NBTUtils.readNBT(in);
		if (nbtBase instanceof NBTTagCompound)
			return (NBTTagCompound) nbtBase;
		else
			throw new NBTFormatException("Root tag must be a named compound tag");
	}

	public static NBTBase readNBT(DataInputStream in) throws IOException {
		byte nbtTagID = in.readByte();
		if (nbtTagID == 0)
			return new NBTTagEnd();
		else {
			in.readUTF();
			NBTBase nbt = NBTUtils.getNbtFromID(nbtTagID);
			try {
				nbt.readData(in);
				return nbt;
			} catch (IOException e) {
				CrashReport.makeCrashReport("Error while loading NBT data", e, 6);
			}
		}
		return null;
	}

}