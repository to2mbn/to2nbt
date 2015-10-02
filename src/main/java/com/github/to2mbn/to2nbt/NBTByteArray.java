package com.github.to2mbn.to2nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class NBTByteArray extends NBT {

	static final int ID = 7;

	private byte[] data;

	protected NBTByteArray() {
	}

	public NBTByteArray(byte[] data) {
		Objects.requireNonNull(data);
		this.data = data;
	}

	@Override
	protected void write(DataOutput out) throws IOException {
		out.writeInt(data.length);
		out.write(data);
	}

	@Override
	protected void read(DataInput in) throws IOException {
		data = new byte[in.readInt()];
		in.readFully(data);
	}

	@Override
	public byte getId() {
		return ID;
	}

	@Override
	public NBT clone() {
		byte[] copied = new byte[data.length];
		System.arraycopy(data, 0, copied, 0, data.length);
		return new NBTByteArray(copied);
	}

	@Override
	public boolean equals(Object another) {
		return super.equals(another) && Arrays.equals(data, ((NBTByteArray) another).data);
	}

	@Override
	public int hashCode() {
		return super.hashCode() ^ Arrays.hashCode(data);
	}

	public byte[] getByteArray() {
		return data;
	}

	@Override
	public byte[] unwrap() {
		return data;
	}
}
