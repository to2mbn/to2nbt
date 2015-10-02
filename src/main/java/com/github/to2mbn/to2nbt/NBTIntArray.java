package com.github.to2mbn.to2nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class NBTIntArray extends NBT {

	static final int ID = 11;

	private int[] data;

	protected NBTIntArray() {
	}

	public NBTIntArray(int[] data) {
		Objects.requireNonNull(data);
		this.data = data;
	}

	@Override
	protected void write(DataOutput out) throws IOException {
		out.writeInt(data.length);

		for (int i = 0; i < data.length; i++) {
			out.writeInt(data[i]);
		}
	}

	@Override
	protected void read(DataInput in) throws IOException {
		int length = in.readInt();
		data = new int[length];

		for (int i = 0; i < length; i++) {
			data[i] = in.readInt();
		}
	}

	@Override
	public byte getId() {
		return ID;
	}

	@Override
	public NBT clone() {
		int[] copied = new int[data.length];
		System.arraycopy(data, 0, copied, 0, data.length);
		return new NBTIntArray(copied);
	}

	@Override
	public boolean equals(Object another) {
		return super.equals(another) && Arrays.equals(data, ((NBTIntArray) another).data);
	}

	@Override
	public int hashCode() {
		return super.hashCode() ^ Arrays.hashCode(data);
	}

	public int[] getIntArray() {
		return data;
	}

	@Override
	public int[] unwrap() {
		return data;
	}
}
