package com.github.to2mbn.to2nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class NBTCompound extends NBT {

	static final int ID = 10;

	private Map<String, NBT> tags = new LinkedHashMap<>();
	private Map<String, NBT> unmodifiableView = Collections.unmodifiableMap(tags);

	@Override
	protected void write(DataOutput out) throws IOException {
		Iterator<String> it = tags.keySet().iterator();

		while (it.hasNext()) {
			String key = it.next();
			writeEntry(key, tags.get(key), out);
		}

		out.writeByte(0);
	}

	@Override
	protected void read(DataInput in) throws IOException {
		tags.clear();
		byte type;

		while ((type = readType(in)) != 0) {
			String key = readKey(in);
			tags.put(key, readNBT(type, key, in));
		}
	}

	@Override
	public byte getId() {
		return ID;
	}

	@Override
	public NBT clone() {
		NBTCompound copied = new NBTCompound();
		for (Entry<String, NBT> tag : tags.entrySet()) {
			copied.set(tag.getKey(), tag.getValue().clone());
		}
		return copied;
	}

	@Override
	public boolean equals(Object another) {
		return super.equals(another) && tags.equals(((NBTCompound) another).tags);
	}

	@Override
	public int hashCode() {
		return super.hashCode() ^ tags.hashCode();
	}

	public void set(String key, NBT value) {
		tags.put(key, value);
	}

	public void setByte(String key, byte value) {
		tags.put(key, new NBTByte(value));
	}

	public void setShort(String key, short value) {
		tags.put(key, new NBTShort(value));
	}

	public void setInt(String key, int value) {
		tags.put(key, new NBTInt(value));
	}

	public void setLong(String key, long value) {
		tags.put(key, new NBTLong(value));
	}

	public void setFloat(String key, float value) {
		tags.put(key, new NBTFloat(value));
	}

	public void setDouble(String key, double value) {
		tags.put(key, new NBTDouble(value));
	}

	public void setString(String key, String value) {
		tags.put(key, new NBTString(value));
	}

	public void setByteArray(String key, byte[] value) {
		tags.put(key, new NBTByteArray(value));
	}

	public void setIntArray(String key, int[] value) {
		tags.put(key, new NBTIntArray(value));
	}

	public void setBoolean(String key, boolean value) {
		setByte(key, (byte) (value ? 1 : 0));
	}

	public NBT get(String key) {
		return tags.get(key);
	}

	public byte getByte(String key) {
		return ((NBTNumber) tags.get(key)).getByte();
	}

	public short getShort(String key) {
		return ((NBTNumber) tags.get(key)).getShort();
	}

	public int getInt(String key) {
		return ((NBTNumber) tags.get(key)).getInt();
	}

	public long getLong(String key) {
		return ((NBTNumber) tags.get(key)).getLong();
	}

	public float getFloat(String key) {
		return ((NBTNumber) tags.get(key)).getFloat();
	}

	public double getDouble(String key) {
		return ((NBTNumber) tags.get(key)).getDouble();
	}

	public String getString(String key) {
		return ((NBTString) tags.get(key)).getString();
	}

	public byte[] getByteArray(String key) {
		return ((NBTByteArray) tags.get(key)).getByteArray();
	}

	public int[] getIntArray(String key) {
		return ((NBTIntArray) tags.get(key)).getIntArray();
	}

	public NBTCompound getCompound(String key) {
		return (NBTCompound) tags.get(key);
	}

	public NBTList getList(String key) {
		return (NBTList) tags.get(key);
	}

	public boolean getBoolean(String key) {
		return getByte(key) != 0;
	}

	public boolean containsKey(String key) {
		return tags.containsKey(key);
	}

	public boolean containsKey(String key, int type) {
		NBT nbt = tags.get(key);
		return nbt != null && type == nbt.getId();
	}

	public void remove(String key) {
		tags.remove(key);
	}

	public boolean isEmpty() {
		return tags.isEmpty();
	}

	public Set<String> keySet() {
		return tags.keySet();
	}

	private void writeEntry(String key, NBT nbt, DataOutput out) throws IOException {
		out.writeByte(nbt.getId());

		if (nbt.getId() != 0) {
			out.writeUTF(key);
			nbt.write(out);
		}
	}

	private byte readType(DataInput in) throws IOException {
		return in.readByte();
	}

	private String readKey(DataInput in) throws IOException {
		return in.readUTF();
	}

	private NBT readNBT(byte id, String key, DataInput in) throws IOException {
		NBT nbt = NBT.createNewByType(id);
		nbt.read(in);
		return nbt;
	}

	public Map<String, NBT> tagsMap() {
		return unmodifiableView;
	}

	@Override
	public Map<String, Object> unwrap() {
		Map<String, Object> result = new HashMap<>(tags.size());
		for (Entry<String, NBT> tag : tags.entrySet()) {
			result.put(tag.getKey(), tag.getValue().unwrap());
		}
		return result;
	}
}
