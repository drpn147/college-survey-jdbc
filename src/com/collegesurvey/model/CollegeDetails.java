package com.collegesurvey.model;

import java.util.List;

public class CollegeDetails {
	private String collegeName;
	private String collegeCode;
	private int collegeCapacity;
	private List<StudentDetails> student;

	public List<StudentDetails> getStudent() {
		return student;
	}

	public void setStudent(List<StudentDetails> student) {
		this.student = student;
	}

	public CollegeDetails(String collegeCode, String collegeName, int collegeCapacity) {
		this.collegeName = collegeName;
		this.collegeCode = collegeCode;
		this.collegeCapacity = collegeCapacity;
	}

	public CollegeDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getCollegeCode() {
		return collegeCode;
	}

	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}

	public int getCollegeCapacity() {
		return collegeCapacity;
	}

	public void setCollegeCapacity(int collegeCapacity) {
		this.collegeCapacity = collegeCapacity;
	}

}
