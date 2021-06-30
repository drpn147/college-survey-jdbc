package com.collegesurvey.utlity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static String URL = "jdbc:mysql://localhost:3306/surveyjdbc";
	private static String NAME = "root";
	private static String PASSWORD = "root@123";
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";

	/********** load driver *****************/
	public static void loadDriver() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid Driver name");
		}
	}

	/**************** establish connection **********************/
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, NAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid prameter in getConnection method");
		}
		return conn;
	}
	
	

}
