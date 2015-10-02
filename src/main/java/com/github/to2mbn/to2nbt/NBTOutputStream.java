package com.github.to2mbn.to2nbt;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NBTOutputStream extends DataOutputStream {

	public NBTOutputStream(OutputStream out) {
		super(out);
	}

	public void writeNBT(NBT nbt) throws IOException {
		writeByte(nbt.getId());
		writeUTF("");
		nbt.write(this);
	}
}
