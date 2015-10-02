package com.github.to2mbn.to2nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

abstract public class NBT implements Cloneable {

	protected static NBT createNewByType(byte id) {
		switch (id) {
			case NBTByte.ID:
				return new NBTByte();

			case NBTShort.ID:
				return new NBTShort();

			case NBTInt.ID:
				return new NBTInt();

			case NBTLong.ID:
				return new NBTLong();

			case NBTFloat.ID:
				return new NBTFloat();

			case NBTDouble.ID:
				return new NBTDouble();

			case NBTByteArray.ID:
				return new NBTByteArray();

			case NBTString.ID:
				return new NBTString();

			case NBTList.ID:
				return new NBTList();

			case NBTCompound.ID:
				return new NBTCompound();

			case NBTIntArray.ID:
				return new NBTIntArray();

			default:
				return null;
		}
	}

	@Override
	abstract public NBT clone();

	abstract protected byte getId();

	abstract protected void write(DataOutput out) throws IOException;

	abstract protected void read(DataInput in) throws IOException;

	abstract public Object unwrap();

	@Override
	public String toString() {
		Object unwrapped = unwrap();
		if (unwrapped instanceof Map) {
			return new JSONObject((Map<?, ?>) unwrapped).toString();
		} else if (unwrapped instanceof Collection) {
			return new JSONArray((List<?>) unwrapped).toString();
		} else if (unwrapped.getClass().isArray()) {
			return new JSONArray((List<?>) unwrapped).toString();
		} else {
			return new JSONObject(unwrapped).toString();
		}
	}

	@Override
	public boolean equals(Object another) {
		if (!(another instanceof NBT)) {
			return false;
		}
		return getId() == ((NBT) another).getId();
	}

	@Override
	public int hashCode() {
		return getId();
	}

}
