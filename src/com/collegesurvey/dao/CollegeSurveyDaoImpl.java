package com.collegesurvey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.collegesurvey.exception.NoClassFound;
import com.collegesurvey.model.CollegeDetails;
import com.collegesurvey.model.StudentDetails;
import com.collegesurvey.utlity.DatabaseConnection;

public class CollegeSurveyDaoImpl implements CollegeSurveyDao {
	Connection conn = null;

	@Override
	public int insert(CollegeDetails college) {
		DatabaseConnection.loadDriver();
		conn = DatabaseConnection.getConnection();
		int result = 0;
		String QUERY = "INSERT INTO `surveyjdbc`.`college` (`college_code`, `college_name`, `college_capacity`) VALUES (?, ?, ?);";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(QUERY);
			ps.setString(1, college.getCollegeCode());
			ps.setString(2, college.getCollegeName());
			ps.setInt(3, college.getCollegeCapacity());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int insert(StudentDetails student) {
		DatabaseConnection.loadDriver();
		conn = DatabaseConnection.getConnection();
		int result = 0;
		int collegeCapacity = 0;
		int studentCount = 0;
		String QUERY1 = "INSERT INTO `surveyjdbc`.`student` (`student_id`, `student_name`, `student_age`, `student_subject`, `student_college_name`) VALUES (?, ?, ?, ?, ?);";
		String QUERY2 = "SELECT college_capacity FROM surveyjdbc.college WHERE college_name=?;";
		String QUERY3 = "SELECT count(student_college_name) as student_in_college FROM surveyjdbc.student WHERE student_college_name=?;";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(QUERY2);
			ps.setString(1, student.getStudentCollegeName());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				collegeCapacity = rs.getInt("COLLEGE_CAPACITY");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps = conn.prepareStatement(QUERY3);
			ps.setString(1, student.getStudentCollegeName());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				studentCount = rs.getInt("STUDENT_IN_COLLEGE");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (collegeCapacity > studentCount) {
			try {
				ps = conn.prepareStatement(QUERY1);
				ps.setInt(1, student.getStudentId());
				ps.setString(2, student.getStudentName());
				ps.setInt(3, student.getStudentAge());
				ps.setString(4, student.getStudentSubject());
				ps.setString(5, student.getStudentCollegeName());
				result = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return result;
		}

		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<StudentDetails> selectStudent(String collegeName, String collegeSubject) {
		DatabaseConnection.loadDriver();
		conn = DatabaseConnection.getConnection();
		int result = 0;
		List<StudentDetails> studentList = new ArrayList<StudentDetails>();
		String QUERY = "SELECT count(student_college_name) as college_count FROM surveyjdbc.student WHERE student_college_name=?;";
		String QUERY1 = "SELECT * FROM surveyjdbc.student WHERE student_college_name=? AND student_subject=?;";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(QUERY);
			ps.setString(1, collegeName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("COLLEGE_COUNT");
			}
			if (result > 0) {
				ps = conn.prepareStatement(QUERY1);
				ps.setString(1, collegeName);
				ps.setString(2, collegeSubject);
				ResultSet rs1 = ps.executeQuery();
				while (rs1.next()) {
					int studentId = rs1.getInt("STUDENT_ID");
					String studentName = rs1.getString("STUDENT_NAME");
					int studentAge = rs1.getInt("STUDENT_AGE");
					String studentSubject = rs1.getString("STUDENT_SUBJECT");
					String studentCollegeName = rs1.getString("STUDENT_COLLEGE_NAME");
					StudentDetails student = new StudentDetails();
					student.setStudentId(studentId);
					student.setStudentName(studentName);
					student.setStudentAge(studentAge);
					student.setStudentSubject(studentSubject);
					student.setStudentCollegeName(studentCollegeName);
					studentList.add(student);

				}
			} else {
				throw new NoClassFound("No such college " + collegeName);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoClassFound e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentList;
	}

	@Override
	public List<CollegeDetails> selectJoin() {
		DatabaseConnection.loadDriver();
		conn=DatabaseConnection.getConnection();
		List<CollegeDetails> collegeList=new ArrayList<CollegeDetails>();
		String QUERY="call surveyjdbc.new_procedure();";
		PreparedStatement ps;
		try {
			ps=conn.prepareStatement(QUERY);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				String collegeCode=rs.getString("COLLEGE_CODE");
				String collegeName=rs.getString("COLLEGE_NAME");
				int collegeCapacity=rs.getInt("COLLEGE_CAPACITY");
				CollegeDetails college=new CollegeDetails(collegeCode,collegeName,collegeCapacity);
				collegeList.add(college);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return collegeList;
	}

	@Override
	public List<StudentDetails> retrive(String collegeName) {
		DatabaseConnection.loadDriver();
		conn=DatabaseConnection.getConnection();
		List<StudentDetails> studentList=new ArrayList<StudentDetails>();
		String QUERY="SELECT * FROM surveyjdbc.student WHERE student_college_name=?;";
		PreparedStatement ps;
		try {
			ps=conn.prepareStatement(QUERY);
			ps.setString(1, collegeName);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int studentId = rs.getInt("STUDENT_ID");
				String studentName = rs.getString("STUDENT_NAME");
				int studentAge = rs.getInt("STUDENT_AGE");
				String studentSubject = rs.getString("STUDENT_SUBJECT");
				String studentCollegeName = rs.getString("STUDENT_COLLEGE_NAME");
				StudentDetails student = new StudentDetails();
				student.setStudentId(studentId);
				student.setStudentName(studentName);
				student.setStudentAge(studentAge);
				student.setStudentSubject(studentSubject);
				student.setStudentCollegeName(studentCollegeName);
				studentList.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentList;
	}

}
