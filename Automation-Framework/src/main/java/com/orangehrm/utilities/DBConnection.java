package com.orangehrm.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class DBConnection {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/orangehrm";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "";

	public static Connection getDBConnection() {
		try {
			System.out.println("Starting DB Connection...");
			Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			System.out.println("DB Connected Successful");
			return conn;
		} catch (SQLException e) {
			System.out.println("Error while establishing the DB Connection");
			e.printStackTrace();
			return null;
		}
	}

	// Get the employee details from DB and store in a map
	public static Map<String, String> getEmployeeDetails(String employee_id) {

		String query = "SELECT emp_firstname, emp_middle_name, emp_lastname from hs_hr_employee where employee_id ="
				+ employee_id;
		Map<String, String> employeeDetails = new HashMap<String, String>();

		try (Connection conn = getDBConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			System.out.println("Executing query: " + query);
			if (rs.next()) {
				String firstName = rs.getString("emp_firstname");
				String middleName = rs.getString("emp_middle_name");
				String lastName = rs.getString("emp_lastname");

				// Store in Map
				employeeDetails.put("firstName", firstName);
				employeeDetails.put("middleName", middleName != null ? middleName : "");
				employeeDetails.put("lastName", lastName);
				System.out.println(employeeDetails);

				System.out.println("Query Executed Sucessfully");
				System.out.println("Employee Data Fetched");
			} else {
				System.out.println("Employee not found");
			}
		} catch (Exception e) {
			System.out.println("Err while executing query");
			e.printStackTrace();
		}
		return employeeDetails;
	}
}
