package com.github.to2mbn.to2nbt;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class NBTInputStream extends DataInputStream {

	public NBTInputStream(InputStream in) {
		super(in);
	}

	public NBT readNBT() throws IOException {
		byte type = readByte();
		readUTF();

		NBT nbt = NBT.createNewByType(type);
		if (nbt == null) {
			throw new IOException("Unexpected tag type: " + type);
		}

		nbt.read(this);
		return nbt;
	}

}
