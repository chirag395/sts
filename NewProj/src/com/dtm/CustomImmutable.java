package com.dtm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//CUSTOM IMMUTABLE CLASS
final class TestImmutable
{
	private final String msg = "hello hi bye...";
	
	private final List<String> names = Arrays.asList("king");
	
	public TestImmutable()
	{
		
	}

	public String getMsg() {
		return msg;
	}

	public List<String> getNames() {
		return names;
	}
	
	public List<String> getListNames()
	{
		System.out.println(getMsg());
		return Collections.unmodifiableList(names);
	}
}

public class CustomImmutable {
	
	public static void main(String[] args) {
		
		TestImmutable obj = new TestImmutable();
		
		System.out.println(obj.getListNames());
	}
}
