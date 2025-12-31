package com.voter;

public class VoterData {
	public void checkAge(int age) throws ValidAgeException {
		if(age >= 18) {
			ValidAgeException ve = new ValidAgeException("valid age");
			throw(ve);
		}
		else {
			ValidAgeException ve = new ValidAgeException("invalid age");
			
//			throw()
			
			throw(ve);
		}
	}
}
