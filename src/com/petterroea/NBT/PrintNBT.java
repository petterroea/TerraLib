package com.petterroea.NBT;

import java.io.File;

/**
 * Prints an NBT file to the console.
 * @author petterroea
 *
 */
public class PrintNBT {

	public static void main(String[] args) {
		if(args.length!=1) { System.out.println("<NBT file>"); return;}
		File f = new File(args[0]);
		if(!f.exists()) { System.out.println("Nbt file does not exist!"); }

	}

}
