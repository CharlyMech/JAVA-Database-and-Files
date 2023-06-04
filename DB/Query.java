/*
	Again this is the way I do the JDBC connection. There are more ways to do this
	So keep in miund that the showed code in this file (and also in Conn.java) is the way
	personally execute querys using JDBC (and also the connection as said).
*/

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
	/*
		This class has no attributes, only methods
		
		All methods are static and public because there's no need to instanciate an object
		to be able to execute the querys from any file
	*/

	// EXAMPLE OF SELECT - Statement
	public static int returnUserID(String mail) {
		/*
			Imagine that the table Users has an unique attribute (mail) and wanted to get the field user_id
		*/
		// Create a variable for the returned data we want to get
		int user_id = -1; // Set it to a default value we know we'll never obtain from the DB to verify the returned data
		// Prepare the query in a String variable
		String query = "SELECT user_id FROM Users WHERE mail='" + mail + "'"; // Concatenate mail string
		// Try-Catch block to execute the query
		try {
			Statement stmt = Conn.conn.createStatement(); // Create the Statement from the conn attribute -> Create the cursor from the poiner
			// Since the query returns some data (even if it's void) we need to create a ResulSet
			ResultSet rs = stmt.executeQuery(query); // Store the query result in a ResultSet
			// Again, since we have returned data from the query, we need to loop over rs to get the data
			while (rs.next()) {
				// Even if there is no data returned we need to loop through the ResultSet for interpreting the data
				user_id = rs.getInt("user_id"); // Set user_id variable to the returned data using the table column name
				/* user_id = rs.getInt(0); */ // Alternatively we can use the index //! This way never worked properly in my case so I prefer to use the column name way
			}
		} catch (SQLException sqle) {
			// Print a message in console if an error is catched
			// I usually use the method name topic and the sqle catched error to be displayed so I can quickly find where the error is
			System.out.println("RETURN USER ID FROM MAIL QUERY ERROR: " + sqle);

			// In this block we also can use a JOptionPane in the case we're using SWING if we want to notify the user that something went wrong
		}

		// Return the data
		return user_id;
	}

	// EXAMPLE OF SELECT - PreparedStatement
	public static int returnUserIDPrepared(String mail) {
		/*
			This is the same method as the previous one, but this time with a PreparedStatement instead of a Statement
		
			Usually the PreparedStatement is used for DELETE-UPDATE-INSERT actions but they have multiples benefits to be used always (no SQLInjection, etc)
			So I personally think that they should be used always (again, this is my opinion)
		*/

		// Create a variable for the returned data we want to get
		int user_id = -1; // Set it to a default value we know we'll never obtain from the DB to verify the returned data
		// Prepare the query in a String variable with '?' where the parameters are
		// We'll set all ? as many parameters the query needs
		String query = "SELECT user_id FROM Users WHERE mail=?";
		// Try-Catch block to execute the query
		try {
			PreparedStatement stmt = Conn.conn.prepareStatement(query); // Create the Statement from the conn attribute -> Create the cursor from the poiner
			stmt.setString(1, mail); // Set the values to the parameters in the query: Notice that 'indexes' start with 1 and they need to be set in the query order
			/*stmt.setInt(2, number) */ // Example of setInt() method
			// Since the query returns some data (even if it's void) we need to create a ResulSet
			ResultSet rs = stmt.executeQuery(); // Store the query result in a ResultSet -> This time there's no need to call the method with the query String as a parameter
			// Again, since we have returned data from the query, we need to loop over rs to get the data
			while (rs.next()) {
				// Even if there is no data returned we need to loop through the ResultSet for interpreting the data
				user_id = rs.getInt("user_id"); // Set user_id variable to the returned data using the table column name
				/* user_id = rs.getInt(0); */ // Alternatively we can use the index //! This way never worked properly in my case so I prefer to use the column name way
			}
		} catch (SQLException sqle) {
			// Print a message in console if an error is catched
			// I usually use the method name topic and the sqle catched error to be displayed so I can quickly find where the error is
			System.out.println("RETURN USER ID FROM MAIL PREPARED QUERY ERROR: " + sqle);

			// In this block we also can use a JOptionPane in the case we're using SWING if we want to notify the user that something went wrong
		}

		// Return the data
		return user_id;
	}

	/*
		Remember that these are some examples to understand the way to execute querys in JDBC
	
		Also remember that inside the rs.next() loop you can filter data or select only the data needed for the method
	
		As I said, the following CRUD actions are usually executed with PreparedStatement so I'll not show the Statement way
	*/

	// EXAMPLE OF INSERT - PreparedStatement
	public static boolean createNewUser(String username, String mail, String passwd) {
		// This method could be void perfectly, but is usefull to verify in the source code if the query is completely successful
		String query = "INSERT INTO Users (username, mail, passwd) VALUES (?, ?, ?)"; // This query example is meant to have an auto_increment primary key
		// Try-Catch block to execute the query
		try {
			PreparedStatement stmt = Conn.conn.prepareStatement(query); // Create the PreparedStatement
			stmt.setString(1, username); // Set all query parameters
			stmt.setString(2, mail);
			stmt.setString(3, passwd);
			// In the INSERT action, the db-manager does not return any data so there's no need to create a ResulSet
			// For this types of querys, we just need to execute the query
			stmt.executeUpdate(); // For DELETE-UPDATE-INSERT, this method from PreparedStatement class must be executed
			// In this case we need to return a boolean value (true in this try-catch block), but remember that is not completely necessary
			return true;
		} catch (SQLException sqle) {
			// Display an error in the terminal and return a false value -> This indicates that the query execution was not successful
			System.out.println("CREATE NEW USER QUERY ERROR: " + sqle);
			return false;
		}
	}

	// EXAMPLE OF UPDATE - PreparedStatement
	public static void updateUsernameFromMail(String newUsername, String mail) {
		// This method will update the username
		String query = "UPDATE Users SET username=? WHERE mail=?";
		// Try-Catch block to execute the query
		try {
			PreparedStatement stmt = Conn.conn.prepareStatement(query); // Create the PreparedStatement
			stmt.setString(1, newUsername); // Set all query parameters
			stmt.setString(2, mail);
			// Since the UPDATE action does not return any data, we only need to execute the query
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println("UPDATE USERNAME FROM MAIL QUERY ERROR: " + sqle);
		}
	}

	// EXAMPLE OF DELETE - PreparedStatement
	public static void deleteUsernameFromMail(String mail) {
		// This method will delete a user from the database
		String query = "DELETE FROM Users WHERE mail=?";
		// Try-Catch block to execute the query
		try {
			PreparedStatement stmt = Conn.conn.prepareStatement(query); // Create the PreparedStatement
			stmt.setString(1, mail); // Set all query parameters
			// Since the UPDATE action does not return any data, we only need to execute the query
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println("DELETE USER FROM MAIL QUERY ERROR: " + sqle);
		}
	}
}
