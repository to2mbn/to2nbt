package com.github.to2mbn.to2nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTFloat extends NBTNumber {

	static final int ID = 5;

	private float data;

	protected NBTFloat() {
	}

	public NBTFloat(float data) {
		this.data = data;
	}

	@Override
	protected void write(DataOutput out) throws IOException {
		out.writeFloat(data);
	}

	@Override
	protected void read(DataInput in) throws IOException {
		data = in.readFloat();
	}

	@Override
	public byte getId() {
		return ID;
	}

	@Override
	public NBT clone() {
		return new NBTFloat(data);
	}

	@Override
	public boolean equals(Object another) {
		return super.equals(another) && data == ((NBTFloat) another).data;
	}

	@Override
	public int hashCode() {
		return super.hashCode() ^ Float.floatToIntBits(data);
	}

	@Override
	public long getLong() {
		return (long) data;
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
	public Float unwrap() {
		return data;
	}
}
