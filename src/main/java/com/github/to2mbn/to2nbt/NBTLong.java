package com.github.to2mbn.to2nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTLong extends NBTNumber {

	static final int ID = 4;

	private long data;

	protected NBTLong() {
	}

	public NBTLong(long data) {
		this.data = data;
	}

	@Override
	protected void write(DataOutput out) throws IOException {
		out.writeLong(data);
	}

	@Override
	protected void read(DataInput in) throws IOException {
		data = in.readLong();
	}

	@Override
	public byte getId() {
		return ID;
	}

	@Override
	public NBT clone() {
		return new NBTLong(data);
	}

	@Override
	public boolean equals(Object another) {
		return super.equals(another) && data == ((NBTLong) another).data;
	}

	@Override
	public int hashCode() {
		return super.hashCode() ^ (int) (data ^ data >>> 32);
	}

	@Override
	public long getLong() {
		return data;
	}

	@Override
	public int getInt() {
		return (int) data;
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
	public Long unwrap() {
		return data;
	}
}
