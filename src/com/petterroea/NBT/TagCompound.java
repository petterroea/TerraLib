package com.petterroea.NBT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class TagCompound extends Tag {
	private HashMap<String, Tag> map = new HashMap<String, Tag>();
	public TagCompound(String name) {
		super(name);
	}
	
	public TagCompound()
	{
		super("");
	}

	@Override
	public void read(DataInputStream is) throws IOException {
		map.clear();
		Tag t = Tag.readNamedTag(is);
		while(t.getId()!=Tag.TAG_End)
		{
			map.put(t.getName(), t);
			t = Tag.readNamedTag(is);
		}
	}

	@Override
	public void write(DataOutputStream os) throws IOException {
		for(Tag t : map.values())
		{
			os.writeByte(t.getId());
			os.writeUTF(t.getName());
			t.write(os);
		}
		os.writeByte(Tag.TAG_End);
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Tag.TAG_Compound;
	}

	@Override
	public Tag copy() {
		TagCompound comp = new TagCompound(this.getName());
		for(String key : map.keySet())
		{
			comp.put(key, map.get(key).copy());
		}
		return comp;
	}
	public void put(String key, Tag t)
	{
		map.put(key, t);
	}
	public Tag get(String key)
	{
		return map.get(key);
	}
	public boolean hasKey(String key)
	{
		return map.containsKey(key);
	}
	@Override
	public boolean equals(Object o)
	{
		if(super.equals(o))
		{
			TagCompound t = (TagCompound)o;
			return t.map.equals(this.map);
		}
		return false;
	}
}