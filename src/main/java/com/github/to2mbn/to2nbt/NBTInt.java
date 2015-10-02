package com.github.to2mbn.to2nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTInt extends NBTNumber {

	static final int ID = 3;

	private int data;

	protected NBTInt() {
	}

	public NBTInt(int data) {
		this.data = data;
	}

	@Override
	protected void write(DataOutput out) throws IOException {
		out.writeInt(data);
	}

	@Override
	protected void read(DataInput in) throws IOException {
		data = in.readInt();
	}

	@Override
	public byte getId() {
		return ID;
	}

	@Override
	public NBT clone() {
		return new NBTInt(data);
	}

	@Override
	public boolean equals(Object another) {
		return super.equals(another) && data == ((NBTInt) another).data;
	}

	@Override
	public int hashCode() {
		return super.hashCode() ^ data;
	}

	@Override
	public long getLong() {
		return data;
	}

	@Override
	public int getInt() {
		return data;
	}

	@Override
	public short getShort() {
		return (short) data;
	}

	@Override
	public byte getByte() {
		return (byte) data;
	}

	@Override
	public double getDouble() {
		return data;
	}

	@Override
	public float getFloat() {
		return data;
	}

	@Override
	public Integer unwrap() {
		return data;
	}
}
