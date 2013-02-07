package com.petterroea.NBT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TagDouble extends Tag {
	private double data;
	public TagDouble(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public TagDouble(String name, double data)
	{
		super(name);
		this.setData(data);
	}

	@Override
	public void read(DataInputStream is) throws IOException {
		data = is.readDouble();
	}

	@Override
	public void write(DataOutputStream os) throws IOException {
		os.writeDouble(data);
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Tag.TAG_Double;
	}

	@Override
	public Tag copy() {
		// TODO Auto-generated method stub
		return new TagDouble(this.getName(), data);
	}
	public double getData() {
		return data;
	}
	public void setData(double data) {
		this.data = data;
	}
	@Override
	public boolean equals(Object o)
	{
		if(super.equals(o))
		{
			TagDouble b = (TagDouble)o;
			return (b.getData()==this.getData());
		}
		return false;
	}
	public void print(int indices)
	{
		System.out.println(getSpacing(indices) + "TAG_Double('" + this.getName() + "'): " + this.data);
	}

}
