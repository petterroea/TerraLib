package com.petterroea.NBT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TagList<T extends Tag> extends Tag {
	private List<T> list = new ArrayList<T>();
    private byte type;

	public TagList(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public TagList(String name, byte type)
	{
		super(name);
		this.type = type;
	}

	@Override
	public void read(DataInputStream is) throws IOException {
		type = is.readByte();
		int len = is.readInt();
		list = new ArrayList<T>();
		for(int i = 0; i < len; i++)
		{
			Tag t = Tag.getNewTag(type, null);
			t.read(is);
			list.add((T)t);
		}
	}

	@Override
	public void write(DataOutputStream os) throws IOException {
		if(list.size()>0)
		{
			type=list.get(0).getId();
		}
		else
		{
			type = 1;
		}
		os.writeByte(type);
		os.writeInt(list.size());
		for(int i = 0; i < list.size(); i++)
		{
			list.get(i).write(os);
		}

	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Tag.TAG_List;
	}

	@Override
	public Tag copy() {
		TagList<T> newList = new TagList<T>(this.getName());
		newList.type = type;
		for(int i = 0; i < list.size(); i++)
		{
			newList.add(list.get(i));
		}
		return newList;
	}
	public void add(T tag) {
        type = tag.getId();
        list.add(tag);
    }

    public T get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }
    @Override
	public boolean equals(Object o)
	{
		if(super.equals(o))
		{
			TagList b = (TagList)o;
			return (b.list.equals(this.list));
		}
		return false;
	}
}
