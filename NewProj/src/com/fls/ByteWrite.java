package com.fls;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteWrite {

	public static void main(String[] args) throws Exception {
		
		File file = new File("src/bytetest.txt");
		
		FileOutputStream fos = new FileOutputStream(file);
		
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		String msg = "dujujujujdududi djd jdi dijfjf";
		
		bos.write(msg.getBytes());
		
		bos.flush();
		
		System.out.println("Done");

	}

}
