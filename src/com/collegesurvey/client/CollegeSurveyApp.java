package com.collegesurvey.client;

import java.util.Scanner;

import com.collegesurvey.service.CollegeSurveyService;
import com.collegesurvey.service.CollegeSurveyServiceImpl;

public class CollegeSurveyApp {
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		CollegeSurveyService collegeSurvey = new CollegeSurveyServiceImpl();
		byte choice;
		do {
			menu();
			choice = scan.nextByte();
			switch (choice) {
			case 1:
				collegeSurvey.setCollegeDetails();
				break;
			case 2:
				collegeSurvey.setStudentDetails();
				break;
			case 3:
				scan.nextLine();
				System.out.println("Enter the College Name");
				String collegeName = scan.nextLine();
				System.out.println("Enter the Subject");
				String collegeSubject = CollegeSurveyService.selectSubject();
				collegeSurvey.getStudentDetails(collegeName, collegeSubject);
				break;
			case 4:
				collegeSurvey.showStudentData();
				break;
			case 5:
				scan.nextLine();
				System.out.println("Enter the college Name");
				String cName=scan.nextLine();
				collegeSurvey.searchStudentByAge(cName);
				break;
			case 6:
				System.out.println("Exited Successfully");
				return;
			default:
				System.out.println("Invalid choice");
			}
		} while (choice != 0);
	}

	private static void menu() {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("1. Add College Details");
		System.out.println("2. Add Student Details");
		System.out.println("3. Display student Details based on college Name and subject");
		System.out.println("4. Display Complete Data");
		System.out.println("5. Search College Code");
		System.out.println("6. Exit");
		System.out.println("--------------------------------------------------------------------");

	}

}
