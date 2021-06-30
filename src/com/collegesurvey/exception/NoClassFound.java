package com.collegesurvey.exception;

public class NoClassFound extends Exception{
	String result;
	public NoClassFound(String string) {
		super(string);
	}
	public String toString() {
		return result;
	}

}
