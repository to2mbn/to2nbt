package com.github.to2mbn.to2nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTDouble extends NBTNumber {

	static final int ID = 6;

	private double data;

	protected NBTDouble() {
	}

	public NBTDouble(double data) {
		this.data = data;
	}

	@Override
	protected void write(DataOutput out) throws IOException {
		out.writeDouble(data);
	}

	@Override
	protected void read(DataInput in) throws IOException {
		data = in.readDouble();
	}

	@Override
	public byte getId() {
		return ID;
	}

	@Override
	public NBT clone() {
		return new NBTDouble(data);
	}

	@Override
	public boolean equals(Object another) {
		return super.equals(another) && data == ((NBTDouble) another).data;
	}

	@Override
	public int hashCode() {
		long longbits = Double.doubleToLongBits(data);
		return super.hashCode() ^ (int) (longbits ^ longbits >>> 32);
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
		return (float) data;
	}

	@Override
	public Double unwrap() {
		return data;
	}
}
