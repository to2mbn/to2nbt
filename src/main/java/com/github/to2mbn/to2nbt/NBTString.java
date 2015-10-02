package com.github.to2mbn.to2nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class NBTString extends NBT {

	static final int ID = 8;

	private String data;

	protected NBTString() {
	}

	public NBTString(String data) {
		Objects.requireNonNull(data);
		this.data = data;
	}

	@Override
	protected void write(DataOutput out) throws IOException {
		out.writeUTF(data);
	}

	@Override
	protected void read(DataInput in) throws IOException {
		data = in.readUTF();
	}

	@Override
	public byte getId() {
		return ID;
	}

	@Override
	public NBT clone() {
		return new NBTString(data);
	}

	@Override
	public boolean equals(Object another) {
		return super.equals(another) && data.equals(((NBTString) another).data);
	}

	@Override
	public int hashCode() {
		return super.hashCode() ^ data.hashCode();
	}

	public String getString() {
		return data;
	}

	@Override
	public String unwrap() {
		return data;
	}

}
