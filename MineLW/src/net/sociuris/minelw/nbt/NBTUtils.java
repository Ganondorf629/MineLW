package net.sociuris.minelw.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.sociuris.minelw.crash.CrashReport;

public class NBTUtils {

	public static NBTElement getNBTFromID(byte id) {
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

	public static NBTTagCompound readCompressed(InputStream in) throws IOException {
		DataInputStream dataInputStream = new DataInputStream(new GZIPInputStream(in));
		NBTTagCompound nbtTagCompound;
		try {
			nbtTagCompound = NBTUtils.readNBTCompound(dataInputStream);
		} finally {
			dataInputStream.close();
		}
		return nbtTagCompound;
	}

	public static void writeCompressed(NBTTagCompound compound, OutputStream out) throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(new GZIPOutputStream(out));
		try {
			writeNBTCompound(compound, dataOutputStream);
		} finally {
			dataOutputStream.close();
		}
	}

	public static NBTTagCompound readNBTCompound(DataInputStream in) throws IOException {
		NBTElement nbtBase = NBTUtils.read(in);
		if (nbtBase instanceof NBTTagCompound)
			return (NBTTagCompound) nbtBase;
		else
			throw new IOException("Root tag must be a named compound tag");
	}
	
	public static void writeNBTCompound(NBTTagCompound compound, DataOutputStream out) throws IOException {
		write(compound, out);
	}

	public static NBTElement read(DataInputStream in) throws IOException {
		byte nbtTagID = in.readByte();
		if (nbtTagID == 0)
			return new NBTTagEnd();
		else {
			in.readUTF();
			NBTElement nbt = NBTUtils.getNBTFromID(nbtTagID);
			try {
				nbt.readData(in);
				return nbt;
			} catch (IOException e) {
				CrashReport.makeCrashReport("Error while loading NBT data", e, 6);
			}
		}
		return null;
	}
	
	public static void write(NBTElement tag, DataOutputStream out) throws IOException {
		out.writeByte(tag.getID());
		if(tag.getID() != 0) {
			out.writeUTF("");
			tag.writeData(out);
		}
	}
	

}