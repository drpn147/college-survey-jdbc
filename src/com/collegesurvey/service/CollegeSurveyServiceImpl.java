package com.collegesurvey.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import com.collegesurvey.dao.CollegeSurveyDao;
import com.collegesurvey.dao.CollegeSurveyDaoImpl;
import com.collegesurvey.exception.ExceedingStrength;
import com.collegesurvey.exception.NoClassFound;
import com.collegesurvey.model.CollegeDetails;
import com.collegesurvey.model.StudentDetails;

public class CollegeSurveyServiceImpl implements CollegeSurveyService {
	private static Scanner scan = new Scanner(System.in);

	@Override
	public void setCollegeDetails() {
		System.out.println("Enter the College Code");
		String collegeCode = scan.nextLine();
		System.out.println("Enter the College Name");
		String collegeName = scan.nextLine();
		System.out.println("Enter the College Capacity");
		int collegeCapacity = scan.nextInt();
		CollegeDetails college = new CollegeDetails(collegeCode, collegeName, collegeCapacity);
		CollegeSurveyDao cDao = new CollegeSurveyDaoImpl();
		int result = cDao.insert(college);
		if (result > 0) {
			System.out.println("Stored Successfully");
		} else {
			System.out.println("Failed");
		}

	}

	@Override
	public void setStudentDetails() {
		System.out.println("Enter the Student Id");
		int studentId = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the Student Name");
		String studentName = scan.nextLine();
		System.out.println("Enter the Student Age");
		int studentAge = scan.nextInt();
		System.out.println("Enter the Student Subject");
		String studentSubject = CollegeSurveyService.selectSubject();
		scan.nextLine();
		System.out.println("Enter the Student College name");
		String studentCollegeName = scan.nextLine();
		StudentDetails student = new StudentDetails(studentId, studentName, studentAge, studentSubject,
				studentCollegeName);
		CollegeSurveyDao cDao = new CollegeSurveyDaoImpl();
		int result = cDao.insert(student);
		try {
			if (result > 0) {
				List<StudentDetails> studentList = new ArrayList<StudentDetails>();
				studentList.add(student);
				CollegeDetails college = new CollegeDetails();
				college.setStudent(studentList);
				List<StudentDetails> s = college.getStudent();
				s.forEach(stud -> {
					System.out.println(stud.getStudentName() + "'s Data Stored successfully");
				});
//				System.out.println(s.get(0).getStudentName() + "'s Data Stored successfully");
			} else {
				throw new ExceedingStrength("Exceeding total Strength");
			}
		} catch (ExceedingStrength e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

	@Override
	public void getStudentDetails(String collegeName, String collegeSubject) {
		CollegeSurveyDao cDao = new CollegeSurveyDaoImpl();
		List<StudentDetails> student = cDao.selectStudent(collegeName, collegeSubject);
		if (student.isEmpty()) {
			System.out.println();
		} else {
			Collections.sort(student, new Comparator<StudentDetails>() {

				@Override
				public int compare(StudentDetails o1, StudentDetails o2) {
					int result = Integer.compare(o2.getStudentAge(), o1.getStudentAge());
					if (result == 0) {
						result = o2.getStudentName().compareTo(o1.getStudentName());
					}
					return result;
				}

			});

			System.out.println("-----------------------------------------------------------");
			System.out.println("Student_Id\tStudent_Name\tStudent_Age\tStudent_Subject\t\tStudent_College_Name ");
			System.out.println(
					"           " + "             " + "            " + "                " + "                     ");
			displayStudent(student);

			System.out.println("-----------------------------------------------------------");

		}
	}

	private void displayStudent(List<StudentDetails> student) {
		student.forEach(s -> {
			System.out.println(s.getStudentId() + "\t\t" + s.getStudentName() + "\t\t" + s.getStudentAge() + "\t\t"
					+ s.getStudentSubject() + "\t\t\t" + s.getStudentCollegeName());
		});

		/*
		 * for (int i = 0; i < student.size(); i++) {
		 * System.out.println(student.get(i).getStudentId() + "\t\t" +
		 * student.get(i).getStudentName() + "\t\t" + student.get(i).getStudentAge() +
		 * "\t\t" + student.get(i).getStudentSubject() + "\t\t\t" +
		 * student.get(i).getStudentCollegeName()); }
		 */
	}

	@Override
	public void showStudentData() {
		CollegeSurveyDao cDao = new CollegeSurveyDaoImpl();
		List<CollegeDetails> college = cDao.selectJoin();
		Collections.sort(college, new Comparator<CollegeDetails>() {

			@Override
			public int compare(CollegeDetails o1, CollegeDetails o2) {
				int result = Integer.compare(o1.getCollegeCapacity(), o2.getCollegeCapacity());
				if (result == 0) {
					result = o1.getCollegeName().compareTo(o2.getCollegeName());
				}
				return result;
			}

		});
		System.out.println("-----------------------------------------------------------");
		System.out.println("Student_Id\tStudent_Name\tStudent_Age\tStudent_Subject\t\tStudent_College_Name ");
		System.out.println(
				"           " + "             " + "            " + "                " + "                     ");

		for (int i = 0; i < college.size(); i++) {
			String collegeName = college.get(i).getCollegeName();
			List<StudentDetails> student = cDao.retrive(collegeName);

			displayStudent(student);
		}
		System.out.println("-----------------------------------------------------------");

	}

	@Override
	public void searchStudentByAge(String collegeName) {
		CollegeSurveyDao cDao = new CollegeSurveyDaoImpl();
		List<CollegeDetails> college = cDao.selectJoin();
		college.stream().filter(c -> c.getCollegeName().equals(collegeName))
				.forEach(c -> System.out.println(c.getCollegeCode() + "\t\t" + c.getCollegeName()));
		/*
		Integer sum = college.stream().map(CollegeDetails::getCollegeCapacity).reduce(0, (a, b) -> {
			return a + b;
		});
		System.out.println("Total Seats: " + sum);
		*/
	}

}
