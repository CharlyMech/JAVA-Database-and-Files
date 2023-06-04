
// Import Needed Packages
// ? Ignore error about ClassPath, do not execute this file, is just an example
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	// ATTRIBUTE
	protected static Connection conn = null; // Connector attribute -> set null by default
	/*
		This attribute is set to protected and static for the  following reasons:
			- Encapsulation set to protected: the attribute conn (pointer) will only be accessed from DB package. Could be set with other encapsulation
			- Static: the attribute is needed for statements/preparedstatements so, because is not needed to instanciate an object, the attribute can be access statically
	*/

	// CONSTRUCTOR
	// Only void constructor is neede
	public Conn() {
		this.connect(); // When the constructor is called (an oibject is instanciated), the connect() method is called
		// IMPORTANT -> Notice that conn is set to null by default, so if the connection succeeds its value will change
	}

	// Create DB Connection Method
	private void connect() {
		// This method is not static because is only used whenever a constructor is called (object instanciation)
		try { // CONNECTION TRY-CATCH BLOCK
			try { // CHECK IF CHARGE DRIVER IS SUCCESSFUL TRY-CATCH BLOCK
				Class.forName("com.mysql.cj.jdbc.Driver"); // Do not ask, just copy this -> The driver (.jar file) must be in lib directory (Java Libraries/Packages)
			} catch (ClassNotFoundException exc) {
				System.out.println("No driver detected! " + exc);
			}
			// The info about the DataBase (ip, Schema, user, passwd) could be set as an attribute and concatenate a full string
			Conn.conn = DriverManager.getConnection("jdbc:mysql://[SERVER_IP]:3306/Database", "user", "passwd");
		} catch (java.sql.SQLException sqle) {
			System.out.println("ERROR: " + sqle);
		}
	}

	// Connection test method for login error
	public boolean connectionValid() { // Not 100% needed but useful
		// This method is not static because is only used whenever a constructor is called (object instanciation)
		// When you have defined a Conn object, it is recommended to check if the connection is made under a certain time
		try {
			return Conn.conn.isValid(50000); // seconds
		} catch (SQLException sqle) {
			System.out.println("CONNECTION ERROR: " + sqle);
			return false;
		}
	}

	// Close DB Connection Method
	public static void closeConnection() { // IMPORTANT -> This method is important to close connection whenever the app is closed (finish exedcution)
		if (Conn.conn != null) { // Check if conn is not null
			// If conn == null means that the connection was not successful so not needed to check or close anything
			try {
				Conn.conn.close(); // Connection close method
			} catch (SQLException sqle) {
				System.out.println("CLOSE CONN ERROR: " + sqle);
			}
		}
	}
}
