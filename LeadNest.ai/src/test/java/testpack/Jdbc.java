package testpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tarun","root","Tarun@321");
		System.out.println("connection created");
		
		Statement stmt = con.createStatement();
		
//		String query = "Select first_name,email,mobile_number from newusers";
		
//		ResultSet rs = stmt.executeQuery(query);
		
/*		while (rs.next()) {
		    String name = rs.getString("first_name");
		    String email = rs.getString("email");
		    String number = rs.getString("mobile_number");

		    System.out.println(name + " | " + email + " | " + number);
		}
		rs.close();
        stmt.close();
        con.close();
*/		

/*		if (rs.next()) {
		    String name = rs.getString("first_name");
		    String email = rs.getString("email");
		    String number = rs.getString("mobile_number");

		    System.out.println(name);
		    System.out.println(email);
		    System.out.println(number);
		}
*/		
		String query =
			    "SELECT first_name, email, mobile_number FROM newusers WHERE email = ?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "tarun@gmail.com");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
			    System.out.println(
			        rs.getString("first_name") + " | " +
			        rs.getString("email") + " | " +
			        rs.getString("mobile_number")
			    );
			}


	}
}
