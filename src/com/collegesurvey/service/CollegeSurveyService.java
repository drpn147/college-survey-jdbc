package com.collegesurvey.service;

import java.util.Scanner;

import com.collegesurvey.exception.ExceedingStrength;

public interface CollegeSurveyService {

	void setCollegeDetails();

	void setStudentDetails();

	void getStudentDetails(String collegeName, String collegeSubject);

	void showStudentData();
	
	static String selectSubject() {
		Scanner scan=new Scanner(System.in);
		byte choice;
		System.out.println("1. Arts");
		System.out.println("2. Commerce");
		System.out.println("3. Science");
		
		choice = scan.nextByte();
		switch (choice) {
		case 1:
			return "Arts";
		case 2:
			return "Commerce";
		case 3:
			return "Science";
		}
		return null;
	}

	void searchStudentByAge(String collegeName);

}
