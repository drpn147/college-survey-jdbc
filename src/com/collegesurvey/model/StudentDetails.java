package com.collegesurvey.model;

public class StudentDetails {
	private int studentId;
	private String studentName;
	private int studentAge;
	private String studentSubject;
	private String studentCollegeName;
	private CollegeDetails college;
	public CollegeDetails getCollege() {
		return college;
	}
	public void setCollege(CollegeDetails college) {
		this.college = college;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}
	public String getStudentSubject() {
		return studentSubject;
	}
	public void setStudentSubject(String studentSubject) {
		this.studentSubject = studentSubject;
	}
	public String getStudentCollegeName() {
		return studentCollegeName;
	}
	public void setStudentCollegeName(String studentCollegeName) {
		this.studentCollegeName = studentCollegeName;
	}
	public StudentDetails(int studentId, String studentName, int studentAge, String studentSubject,
			String studentCollegeName) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentAge = studentAge;
		this.studentSubject = studentSubject;
		this.studentCollegeName = studentCollegeName;
	}
	public StudentDetails() {
		// TODO Auto-generated constructor stub
	}
	
	

}
