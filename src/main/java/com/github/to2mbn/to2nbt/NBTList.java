package com.github.to2mbn.to2nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NBTList extends NBT {

	static final int ID = 9;

	private List<NBT> tags = new ArrayList<>();
	private List<NBT> unmodifiableView = Collections.unmodifiableList(tags);

	private byte tagsType = 0;

	@Override
	protected void write(DataOutput out) throws IOException {
		if (tags.isEmpty()) {
			tagsType = 0;
		} else {
			tagsType = tags.get(0).getId();
		}

		out.writeByte(tagsType);
		out.writeInt(tags.size());

		for (NBT nbt : tags) {
			nbt.write(out);
		}
	}

	@Override
	protected void read(DataInput in) throws IOException {
		tags.clear();

		tagsType = in.readByte();
		int size = in.readInt();

		for (int i = 0; i < size; ++i) {
			NBT nbt = NBT.createNewByType(tagsType);
			nbt.read(in);
			tags.add(nbt);
		}
	}

	@Override
	public byte getId() {
		return ID;
	}

	@Override
	public NBT clone() {
		NBTList copied = new NBTList();
		copied.tagsType = tagsType;
		for (NBT nbt : tags) {
			tags.add(nbt);
		}
		return copied;
	}

	@Override
	public boolean equals(Object another) {
		return super.equals(another) && tags.equals(((NBTList) another).tags);
	}

	@Override
	public int hashCode() {
		return super.hashCode() ^ tags.hashCode();
	}

	public void add(NBT nbt) {
		checkAndSetTagType(nbt);
		tags.add(nbt);
	}

	public void addByte(byte value) {
		add(new NBTByte(value));
	}

	public void addShort(short value) {
		add(new NBTShort(value));
	}

	public void addInt(int value) {
		add(new NBTInt(value));
	}

	public void addLong(long value) {
		add(new NBTLong(value));
	}

	public void addFloat(float value) {
		add(new NBTFloat(value));
	}

	public void addDouble(double value) {
		add(new NBTDouble(value));
	}

	public void addString(String value) {
		add(new NBTString(value));
	}

	public void addByteArray(byte[] value) {
		add(new NBTByteArray(value));
	}

	public void addIntArray(int[] value) {
		add(new NBTIntArray(value));
	}

	public void addBoolean(boolean value) {
		addByte((byte) (value ? 1 : 0));
	}

	public void add(int index, NBT nbt) {
		checkAndSetTagType(nbt);
		tags.add(index, nbt);
	}

	public void addByte(int index, byte value) {
		add(index, new NBTByte(value));
	}

	public void addShort(int index, short value) {
		add(index, new NBTShort(value));
	}

	public void addInt(int index, int value) {
		add(index, new NBTInt(value));
	}

	public void addLong(int index, long value) {
		add(index, new NBTLong(value));
	}

	public void addFloat(int index, float value) {
		add(index, new NBTFloat(value));
	}

	public void addDouble(int index, double value) {
		add(index, new NBTDouble(value));
	}

	public void addString(int index, String value) {
		add(index, new NBTString(value));
	}

	public void addByteArray(int index, byte[] value) {
		add(index, new NBTByteArray(value));
	}

	public void addIntArray(int index, int[] value) {
		add(index, new NBTIntArray(value));
	}

	public void addBoolean(int index, boolean value) {
		addByte(index, (byte) (value ? 1 : 0));
	}

	public void set(int index, NBT nbt) {
		checkAndSetTagType(nbt);
		tags.set(index, nbt);
	}

	public void setByte(int index, byte value) {
		set(index, new NBTByte(value));
	}

	public void setShort(int index, short value) {
		set(index, new NBTShort(value));
	}

	public void setInt(int index, int value) {
		set(index, new NBTInt(value));
	}

	public void setLong(int index, long value) {
		set(index, new NBTLong(value));
	}

	public void setFloat(int index, float value) {
		set(index, new NBTFloat(value));
	}

	public void setDouble(int index, double value) {
		set(index, new NBTDouble(value));
	}

	public void setString(int index, String value) {
		set(index, new NBTString(value));
	}

	public void setByteArray(int index, byte[] value) {
		set(index, new NBTByteArray(value));
	}

	public void setIntArray(int index, int[] value) {
		set(index, new NBTIntArray(value));
	}

	public void setBoolean(int index, boolean value) {
		setByte(index, (byte) (value ? 1 : 0));
	}

	public NBT remove(int i) {
		return tags.remove(i);
	}

	public boolean isEmpty() {
		return tags.isEmpty();
	}

	public NBT get(int index) {
		return tags.get(index);
	}

	public byte getByte(int index) {
		return ((NBTNumber) tags.get(index)).getByte();
	}

	public short getShort(int index) {
		return ((NBTNumber) tags.get(index)).getShort();
	}

	public int getInt(int index) {
		return ((NBTNumber) tags.get(index)).getInt();
	}

	public long getLong(int index) {
		return ((NBTNumber) tags.get(index)).getLong();
	}

	public float getFloat(int index) {
		return ((NBTNumber) tags.get(index)).getFloat();
	}

	public double getDouble(int index) {
		return ((NBTNumber) tags.get(index)).getDouble();
	}

	public String getString(int index) {
		return ((NBTString) tags.get(index)).getString();
	}

	public byte[] getByteArray(int index) {
		return ((NBTByteArray) tags.get(index)).getByteArray();
	}

	public int[] getIntArray(int index) {
		return ((NBTIntArray) tags.get(index)).getIntArray();
	}

	public NBTCompound getCompound(int index) {
		return (NBTCompound) tags.get(index);
	}

	public NBTList getList(int index) {
		return (NBTList) tags.get(index);
	}

	public boolean getBoolean(int index) {
		return getByte(index) != 0;
	}

	public int size() {
		return tags.size();
	}

	public int getTagType() {
		return tagsType;
	}

	private void checkAndSetTagType(NBT nbt) {
		if (tagsType == 0 || tags.isEmpty()) {
			tagsType = nbt.getId();
		} else if (tagsType != nbt.getId()) {
			throw new IllegalArgumentException("Mismatched tag type: " + nbt.getId());
		}
	}

	public List<NBT> tags() {
		return unmodifiableView;
	}

	@Override
	public List<Object> unwrap() {
		List<Object> result = new ArrayList<>(tags.size());
		for (NBT tag : tags) {
			result.add(tag.unwrap());
		}
		return result;
	}
}
