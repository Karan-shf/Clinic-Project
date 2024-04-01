import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
	
	static Connection connection;
	
	static Connection Connect() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/Clinic";
		
		String username = "root";
		String password = "root1234";
		// Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(url, username , password);
		
		return connection;	
	}

	static Statement statement() {
		Statement statement = null;
		try {
			statement = Connect().createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statement;
	}

	static void close_connection() throws SQLException{
		connection.close();
	}
	
}
