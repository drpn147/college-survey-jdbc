package com.collegesurvey.dao;

import java.util.List;

import com.collegesurvey.model.CollegeDetails;
import com.collegesurvey.model.StudentDetails;

public interface CollegeSurveyDao {

	int insert(CollegeDetails college);

	int insert(StudentDetails student);

	List<StudentDetails> selectStudent(String collegeName, String collegeSubject);

	List<CollegeDetails> selectJoin();

	List<StudentDetails> retrive(String collegeName);

}
