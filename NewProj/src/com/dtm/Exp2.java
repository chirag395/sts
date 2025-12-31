package com.dtm;

import java.util.ArrayList;
import java.util.List;

public class Exp2 {

	public static void main(String[] args) {
		
		ArrayList<String> data = new ArrayList<>();
		
		data.add("php");
		data.add("java");
		data.add("python");
		data.add("c++");
		data.add(".net");
		data.add("javascript");
		data.add("ai");
		data.add("spring");
		

		List<String> startsWithP = data.stream().filter(s -> s.startsWith("p")).toList(); 
        System.out.println(startsWithP);

	}
	
	

}
