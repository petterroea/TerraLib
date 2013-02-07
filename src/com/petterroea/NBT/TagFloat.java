package com.petterroea.NBT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TagFloat extends Tag {
	private float data;
	public TagFloat(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public TagFloat(String name, float data)
	{
		super(name);
		this.data = data;
	}

	@Override
	public void read(DataInputStream is) throws IOException {
		data = is.readFloat();
	}

	@Override
	public void write(DataOutputStream os) throws IOException {
		os.writeFloat(data);
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Tag.TAG_Float;
	}

	@Override
	public Tag copy() {
		// TODO Auto-generated method stub
		return new TagFloat(this.getName(), data);
	}
	public float getData()
	{
		return data;
	}
	public void setData(float data)
	{
		this.data = data;
	}
	@Override
	public boolean equals(Object o)
	{
		if(super.equals(o))
		{
			TagFloat b = (TagFloat)o;
			return (b.getData()==this.getData());
		}
		return false;
	}
	public void print(int indices)
	{
		System.out.println(getSpacing(indices) + "TAG_Float('" + this.getName() + "'): " + this.data);
	}

}