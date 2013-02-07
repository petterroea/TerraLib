package com.petterroea.NBT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TagInt extends Tag {
	private int data;
	public TagInt(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public TagInt(String name, int data)
	{
		super(name);
		this.data = data;
	}

	@Override
	public void read(DataInputStream is) throws IOException {
		data = is.readInt();
	}

	@Override
	public void write(DataOutputStream os) throws IOException {
		os.writeInt(data);
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Tag.TAG_Int;
	}

	@Override
	public Tag copy() {
		// TODO Auto-generated method stub
		return new TagInt(this.getName(), data);
	}
	public int getData()
	{
		return data;
	}
	public void setData(int data)
	{
		this.data = data;
	}
	@Override
	public boolean equals(Object o)
	{
		if(super.equals(o))
		{
			TagInt b = (TagInt)o;
			return (b.getData()==this.getData());
		}
		return false;
	}
	public void print(int indices)
	{
		System.out.println(getSpacing(indices) + "TAG_Int('" + this.getName() + "'): " + this.data);
	}

}
