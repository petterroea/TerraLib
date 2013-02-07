package com.petterroea.NBT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TagByte extends Tag {
	private byte data = 0;
	public TagByte(String name)
	{
		super(name);
	}
	public TagByte(String name, byte data)
	{
		super(name);
		this.data = data;
	}
	public byte getData()
	{
		return data;
	}
	public void setData(byte data)
	{
		this.data=data;
	}
	@Override
	public void read(DataInputStream is) throws IOException {
		data = is.readByte();
	}

	@Override
	public void write(DataOutputStream os) throws IOException {
		os.writeByte(data);
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Tag.TAG_Byte;
	}
	@Override
	public Tag copy() {
		// TODO Auto-generated method stub
		return new TagByte(this.getName(), data);
	}
	@Override
	public boolean equals(Object o)
	{
		if(super.equals(o))
		{
			TagByte b = (TagByte)o;
			if (b.getData()!=this.data ) return false;
			return true;
		}
		return false;
	}
	public void print(int indices)
	{
		System.out.println(getSpacing(indices) + "TAG_Byte('" + this.getName() + "'): " + this.data);
	}

}
