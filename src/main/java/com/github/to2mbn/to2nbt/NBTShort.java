package com.github.to2mbn.to2nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTShort extends NBTNumber {

	static final int ID = 2;

	private short data;

	protected NBTShort() {
	}

	public NBTShort(short data) {
		this.data = data;
	}

	@Override
	protected void write(DataOutput out) throws IOException {
		out.writeShort(data);
	}

	@Override
	protected void read(DataInput in) throws IOException {
		data = in.readShort();
	}

	@Override
	public byte getId() {
		return ID;
	}

	@Override
	public NBT clone() {
		return new NBTShort(data);
	}

	@Override
	public boolean equals(Object another) {
		return super.equals(another) && data == ((NBTShort) another).data;
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
		return data;
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
	public Short unwrap() {
		return data;
	}
}
