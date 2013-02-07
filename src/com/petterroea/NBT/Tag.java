package com.petterroea.NBT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

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
	/**
	 * Used for printing. Ignore
	 * @param indices
	 * @return As many spaces as the int <indices>
	 */
	protected String getSpacing(int indices)
	{
		String s = "";
		for(int i = 0; i < indices; i++)
		{
			s=s+" ";
		}
		return s;
	}
	/**
	 * Prints the tag to stdout
	 * @param indices Amount of spaces before the text
	 */
	public void print(int indices)
	{
		System.out.println(getSpacing(indices) + "(ERROR: The tag of id " + getId() + " does not have a print() function!)");
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
			if(b==TAG_End) { t = new TagEnd(); return t; }
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
			System.exit(0);
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
	public static TagCompound readCompressed(DataInputStream dis)
	{
		try {
			GZIPInputStream stream = new GZIPInputStream(dis);
			DataInputStream str = new DataInputStream(stream);
			byte type = str.readByte();
			if(type!=TAG_Compound) return null;
			String name = str.readUTF();
			TagCompound compound = new TagCompound(name);
			compound.read(str);
			return compound;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static TagCompound readFile(File f, boolean compressed) throws IOException
	{
		if(!f.exists()) throw new IOException("The file specified cannot be found");
		DataInputStream is = null;
		if(compressed)
		{
			is = new DataInputStream(new GZIPInputStream(new FileInputStream(f)));
		}
		else
		{
			is = new DataInputStream(new FileInputStream(f));
		}
		byte type = is.readByte();
		if(type != TAG_Compound) return null;
		String name = is.readUTF();
		TagCompound tag = new TagCompound(name);
		tag.read(is);
		return tag;
	}
	public static String getTagName(byte type) {
		if(type==TAG_End) return "tag_end";
		if(type==TAG_Byte) return "tag_byte";
		if(type==TAG_Short) return "tag_short";
		if(type==TAG_Int) return "tag_int";
		if(type==TAG_Long) return "tag_long";
		if(type==TAG_Float) return "tag_float";
		if(type==TAG_Double) return "tag_double";
		if(type==TAG_Byte_Array) return "tag_byte_array";
		if(type==TAG_String) return "tag_string";
		if(type==TAG_List) return "tag_list";
		if(type==TAG_Compound) return "tag_compound";
		if(type==TAG_Int_Array) return "tag_int_array";
		return "UNKNOWN";
	}
}
