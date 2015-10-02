package com.github.to2mbn.to2nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTByte extends NBTNumber {

	static final int ID = 1;

	private byte data;

	protected NBTByte() {
	}

	public NBTByte(byte data) {
		this.data = data;
	}

	@Override
	protected void write(DataOutput out) throws IOException {
		out.writeByte(data);
	}

	@Override
	protected void read(DataInput in) throws IOException {
		data = in.readByte();
	}

	@Override
	public byte getId() {
		return ID;
	}

	@Override
	public NBT clone() {
		return new NBTByte(data);
	}

	@Override
	public boolean equals(Object another) {
		return super.equals(another) && data == ((NBTByte) another).data;
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
		return data;
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
	public Byte unwrap() {
		return data;
	}
}
