//package com.fls;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//
//public class ByteReadTest {
//
//	public static void main(String[] args) {
//		
//		try(FileInputStream fis = new FileInputStream("src/bytetest.txt");
//				BufferedInputStream bis = new BufferedInputStream(fis)){
//			
//		}
//
//	}
//
//}








package com.fls;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;    
import java.nio.charset.StandardCharsets;

public class ByteReadTest {

    public static void main(String[] args) {
        byte[] buffer = new byte[4096]; // 4 KB buffer
        int bytesRead;

        StringBuilder sb = new StringBuilder();

        try (FileInputStream fis = new FileInputStream("src/by etest.txt");
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            // Read until EOF (read() returns -1)
            while ((bytesRead = bis.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, bytesRead, StandardCharsets.UTF_8));
            }

            System.out.println(sb.toString());

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
