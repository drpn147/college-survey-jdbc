package com.collegesurvey.exception;

public class ExceedingStrength extends Exception{

	String result;
	public ExceedingStrength(String string) {
		super(string);
	}
	public String toString() {
		return result;
	}

}
