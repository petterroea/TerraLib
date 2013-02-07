package com.petterroea.NBT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Tag {
	public static final byte TAG_End = 0;
    public static final byte TAG_Byte = 1;
    public static final byte TAG_Short = 2;
    public static final byte TAG_Int = 3;
    public static final byte TAG_Long = 4;
    public static final byte TAG_Float = 5;
    public static final byte TAG_Double = 6;
    public static final byte TAG_Byte_Array = 7;
    public static final byte TAG_String = 8;
    public static final byte TAG_List = 9;
    public static final byte TAG_Compound = 10;
    public static final byte TAG_Int_Array = 11;
    
    public static final boolean FILE_SERVER_DAT = false;
	public static final boolean FILE_PLAYER_DAT = true;
	public static final boolean FILE_IDCOUNTS_DAT = false;
	public static final boolean FILE_VILLAGES_DAT = true;
	//public static final boolean FILE_MAP_X_
	public static final boolean FILE_SERVERS_DAT = false;
	public static final boolean FILE_SCOREBOARD_DAT = true;
	
	private String name;
	
	public abstract void read(DataInputStream is) throws IOException;
	public abstract void write(DataOutputStream os) throws IOException;
	public abstract byte getId();
	public abstract Tag copy();
	public Tag(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		if(name==null) return "";
		return name;
	}
	public void setName(String name)
	{
		if(name==null) { this.name=""; return; }
		this.name=name;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o==null) return false;
		if(!(o instanceof Tag)) return false;
		Tag t = (Tag)o;
		if(t.getName()==null&&this.getName()!=null) return false;
		if(t.getName()!=null&&this.getName()==null) return false;
		if(!t.getName().equals(this.getName())) return false;
		if(t.getId()!=this.getId()) return false;
		return true;
	}
	public static Tag readNamedTag(DataInputStream dis)
	{
		try {
			byte b = dis.readByte();
			Tag t = null;
			if(b==TAG_End) t = new TagEnd();
			String name = dis.readUTF();
			if(b==TAG_Byte) t = new TagByte(name);
			if(b==TAG_Short) t = new TagShort(name);
			if(b==TAG_Int) t = new TagInt(name);
			if(b==TAG_Long) t = new TagLong(name);
			if(b==TAG_Float) t = new TagFloat(name);
			if(b==TAG_Double) t = new TagDouble(name);
			if(b==TAG_Byte_Array) t = new TagByteArray(name);
			if(b==TAG_String) t = new TagString(name);
			if(b==TAG_List) t = new TagList(name);
			if(b==TAG_Compound) t = new TagCompound(name);
			if(b==TAG_Int_Array) t = new TagIntArray(name);
			t.read(dis);
			return t;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Use with caution, does not load data
	 * @param type The id of the tag we want a object of
	 * @param name The name of the tag we want a object of
	 * @return A tag of correct type according to <type>, or null if the type is not of a tag.
	 */
	public static Tag getNewTag(byte type, String name) {
		if(type==TAG_End) return new TagEnd();
		if(type==TAG_Byte) return new TagByte(name);
		if(type==TAG_Short) return new TagShort(name);
		if(type==TAG_Int) return new TagInt(name);
		if(type==TAG_Long) return new TagLong(name);
		if(type==TAG_Float) return new TagFloat(name);
		if(type==TAG_Double) return new TagDouble(name);
		if(type==TAG_Byte_Array) return new TagByteArray(name);
		if(type==TAG_String) return new TagString(name);
		if(type==TAG_List) return new TagList<Tag>(name);
		if(type==TAG_Compound) return new TagCompound(name);
		if(type==TAG_Int_Array) return new TagIntArray(name);
 		return null;
	}

}
